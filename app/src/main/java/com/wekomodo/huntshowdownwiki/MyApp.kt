package com.wekomodo.huntshowdownwiki

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger
import com.google.android.material.color.DynamicColors
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp : Application(), ImageLoaderFactory {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate() {
        super.onCreate()
        Firebase.database.setPersistenceEnabled(true)
        val ref = Firebase.database.getReference()
        ref.keepSynced(true)
        DynamicColors.applyToActivitiesIfAvailable(this)
        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.20)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .logger(DebugLogger())
            .respectCacheHeaders(false)
            .build()
    }
}