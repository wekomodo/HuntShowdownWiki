package com.wekomodo.huntshowdownwiki.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdRequest.Builder
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.wekomodo.huntshowdownwiki.BuildConfig

fun loadNativeAd(context: Context, adUnitId: String, callback: (NativeAd?) -> Unit) {
    val builder = AdLoader.Builder(context, adUnitId).forNativeAd { nativeAd ->
        callback(nativeAd)
    }

    val adLoader = builder.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(adError: LoadAdError) {
            callback(null)
        }
    }).withNativeAdOptions(NativeAdOptions.Builder().build()).build()
    adLoader.loadAd(Builder().build())
}


@Composable
fun LoadSimpleAd(modifier: Modifier) {
    AndroidView(modifier = modifier.fillMaxWidth(), factory = { context ->
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
           adUnitId = BuildConfig.BANNER_AD_ID
            // calling load ad to load our ad.
            loadAd(Builder().build())
        }
    })
}

@Composable
fun CallNativeAd(nativeAd: NativeAd) {
    NativeAdView(ad = nativeAd) { ad, view ->
        LoadAdContent(ad, view)
    }
}

@Composable
fun NativeAdView(
    ad: NativeAd,
    adContent: @Composable (ad: NativeAd, contentView: View) -> Unit,
) {
    val contentViewId by remember { mutableIntStateOf(View.generateViewId()) }
    val adViewId by remember { mutableIntStateOf(View.generateViewId()) }
    AndroidView(factory = { context ->
        val contentView = ComposeView(context).apply {
            id = contentViewId
        }
        NativeAdView(context).apply {
            id = adViewId
            addView(contentView)
        }
    }, update = { view ->
        val adView = view.findViewById<NativeAdView>(adViewId)
        val contentView = view.findViewById<ComposeView>(contentViewId)

        adView.setNativeAd(ad)
        adView.callToActionView = contentView
        contentView.setContent { adContent(ad, contentView) }
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LoadAdContent(nativeAd: NativeAd?, composeView: View) {
    val maxWithModifier: Modifier = Modifier
    Card(
        modifier = maxWithModifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(CardDefaults.shape)
            .combinedClickable {
                composeView.performClick()
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        nativeAd?.let {
            Column(
                modifier = maxWithModifier.padding(8.dp)
            ) {
                Row(
                    modifier = maxWithModifier, horizontalArrangement = Arrangement.Start
                ) {
                    val icon: Drawable? = it.icon?.drawable
                    icon?.let { drawable ->
                        Image(
                            painter = rememberAsyncImagePainter(model = drawable),
                            contentDescription = "Ad"/*it.icon?.contentDescription*/,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column {
                        Text(
                            text = it.headline ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = it.body ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }

                it.callToAction?.let { cta ->
                    Button(modifier = maxWithModifier, onClick = {
                        composeView.performClick()
                    }, content = {
                        Text(
                            text = cta.uppercase(),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.titleMedium
                        )
                    })
                }
            }
        } ?: run {
            // Placeholder for loading state or error state
            Text("Loading ad...")
        }
    }
}