package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.R
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.MeleeStats
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.Stats
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.weapons.Weapons


@Composable
fun ArsenalDetailScreen(item: Weapons) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.height(250.dp).fillMaxWidth(),
            model = item.image_url,
            contentDescription = "weapon Image"
        )
        Text(text = item.name, style = MaterialTheme.typography.headlineMedium)
        Log.d("ARSENAL", item.toString())
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item.stats?.let {
                DetailedItem(
                    icon = R.drawable.ic_damage,
                    "Damage",
                    value = item.stats.damage.toString(),
                    maxValue = 364
                )
                DetailedItem(
                    icon = R.drawable.ic_cycle_time,
                    "Cycle Time",
                    value = item.stats.cycle_time.toString(),
                    maxValue = 7.3.toInt()
                )
                item.stats.effective_range?.let { range ->
                    DetailedItem(
                        icon = R.drawable.ic_effective_range,
                        "Effective Range(meter)",
                        value = range.substringBefore("m"),
                        maxValue = 347
                    )
                }
                item.stats.muzzle_velocity?.let {
                    DetailedItem(
                        icon = R.drawable.ic_muzzle_velocity,
                        "Muzzle Velocity (m/s)",
                        value = item.stats.muzzle_velocity.toString(),
                        maxValue = 820
                    )
                }
                item.stats.reload_speed?.let {
                    DetailedItem(
                        icon = R.drawable.ic_reload_speed,
                        "Reload Speed",
                        value = item.stats.reload_speed.toString(),
                        maxValue = 28.7.toInt()
                    )
                }
                DetailedItem(
                    icon = R.drawable.ic_rpm,
                    "RPM",
                    value = item.stats.rpm.toString(),
                    maxValue = 75
                )
                DetailedItem(
                    icon = R.drawable.ic_spread,
                    "Spread",
                    value = item.stats.spread.toString(),
                    maxValue = 115
                )
                item.stats.sway?.let {
                    DetailedItem(
                        icon = R.drawable.ic_sway,
                        "Sway",
                        value = item.stats.sway.toString(),
                        maxValue = 133
                    )
                }
                item.stats.vertical_recoil?.let {
                    DetailedItem(
                        icon = R.drawable.ic_vertical_recoil,
                        "Vertical Recoil",
                        value = item.stats.vertical_recoil.toString(),
                        maxValue = 40
                    )
                }
            }
            item.melee_stats?.let {
                DetailedItem(
                    icon = R.drawable.ic_melee_light,
                    "Light melee",
                    value = item.melee_stats.melee.toString(),
                    180
                )
                DetailedItem(
                    icon = R.drawable.ic_melee_heavy,
                    "Heavy melee",
                    value = item.melee_stats.heavy_melee.toString(),
                    360
                )
            }

        }
    }

}

@Composable
fun DetailedItem(icon: Int, type: String, value: String, maxValue: Int) {
    Row(
       // modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = icon),
            contentDescription = "damage Icon"
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = type)
    }
    Row(Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically) {
        Log.d("ARSENAL",type)
        Log.d("ARSENAL",value)
        LinearProgressIndicator(
            progress = { value.toFloat() / maxValue },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            modifier = Modifier.width(42.dp),
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End
        )
    }
}


@Preview
@Composable
fun Preview() {
    ArsenalDetailScreen(
        Weapons(
            name = "BASEBALL BAT",
            stats = Stats(sway = 50, vertical_recoil = 2.6, spread = 65.4, rpm = 50, damage = 120, cycle_time = 1.4, effective_range = "248m", reload_speed = 4.2, muzzle_velocity = 550),
            melee_stats = MeleeStats(melee = 57, heavy_melee = 120)
        )
    )
}