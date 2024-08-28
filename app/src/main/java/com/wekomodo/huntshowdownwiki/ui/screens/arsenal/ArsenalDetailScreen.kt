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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.preference.PreferenceManager
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.R
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.MeleeStats
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.Stats
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.consumables.Consumables
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.tools.Tools
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.weapons.Weapons
import com.wekomodo.huntshowdownwiki.ui.components.LoadSimpleAd


@Composable
fun ArsenalDetailScreen(item: Weapons) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
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
                ArsenalDetailedStats(it)
            }
            item.melee_stats?.let {
                ArsenalDetailedMeleeStats(it)
            }
        }
    }
}


@Composable
fun ArsenalDetailScreen(item: Tools) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
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
                ArsenalDetailedStats(it)
            }
            item.melee_stats?.let {
                ArsenalDetailedMeleeStats(it)
            }
        }
    }
}


@Composable
fun ArsenalDetailScreen(item: Consumables) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            model = item.image_url,
            contentDescription = "Consumable Image"
        )
        Text(text = item.name, style = MaterialTheme.typography.headlineMedium)
        Log.d("ARSENAL", item.toString())
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item.stats?.let {
                ArsenalDetailedStats(it)
            }
            item.melee_stats?.let {
                ArsenalDetailedMeleeStats(it)
            }
        }
    }
}

@Composable
fun ArsenalDetailedStats(stats: Stats) {
    stats.damage?.let {
        DetailedItem(
            icon = R.drawable.ic_damage,
            "Damage",
            value = it.toString(),
            maxValue = 364
        )
    }
    stats.cycle_time?.let {
        DetailedItem(
            icon = R.drawable.ic_cycle_time,
            "Cycle Time",
            value = it.toString(),
            maxValue = 7.3.toInt()
        )
    }
    if (stats.drop_range != 0)
        DetailedItem(
            icon = R.drawable.ic_effective_range,
            "Drop Range(meter)",
            value = stats.drop_range.toString(),
            maxValue = 347
        )
    stats.muzzle_velocity?.let {
        DetailedItem(
            icon = R.drawable.ic_muzzle_velocity,
            "Muzzle Velocity (m/s)",
            value = it.toString(),
            maxValue = 820
        )
    }
    stats.reload_speed?.let {
        DetailedItem(
            icon = R.drawable.ic_reload_speed,
            "Reload Speed",
            value = it.toString(),
            maxValue = 28.7.toInt()
        )
    }
    stats.rpm?.let {
        DetailedItem(
            icon = R.drawable.ic_rpm,
            "RPM",
            value = it.toString(),
            maxValue = 75
        )
    }
    stats.spread?.let {
        DetailedItem(
            icon = R.drawable.ic_spread,
            "Spread",
            value = it.toString(),
            maxValue = 115
        )
    }
    stats.sway?.let {
        DetailedItem(
            icon = R.drawable.ic_sway,
            "Sway",
            value = it.toString(),
            maxValue = 133
        )
    }
    stats.vertical_recoil?.let {
        DetailedItem(
            icon = R.drawable.ic_vertical_recoil,
            "Vertical Recoil",
            value = it.toString(),
            maxValue = 40
        )
    }
    stats.sighted_range?.let {
        DetailedItem(
            icon = R.drawable.ic_sway,
            "Sighted Range (m)",
            value = it.toString(),
            maxValue = 133
        )
    }

    stats.duration?.let {
        DetailedItem(
            icon = R.drawable.ic_cycle_time,
            type = "Duration (seconds)",
            value = it.toString(),
            maxValue = 600
        )
    }
    stats.fuse_timer?.let {
        DetailedItem(
            icon = R.drawable.bomb,
            type = "Fuse Timer (seconds)",
            value = it.toString(),
            maxValue = 8
        )
    }
    stats.radius?.let {
        DetailedItem(
            icon = R.drawable.ic_radius,
            type = "Radius(m)",
            value = it.toString(),
            maxValue = 11
        )
    }
    stats.throw_range?.let {
        DetailedItem(
            icon = R.drawable.ic_effective_range,
            type = "Range(m)",
            value = it.toString(),
            maxValue = 22
        )
    }
    stats.control_range?.let {
        DetailedItem(
            icon = R.drawable.ic_control_range,
            type = "Control Range(m)",
            value = it.toString(),
            maxValue = 150
        )
    }
}


@Composable
fun ArsenalDetailedMeleeStats(meleeStats: MeleeStats?) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current)
    val ADS_ENABLED = sharedPreferences.getBoolean("ADS_ENABLED",true)
    meleeStats?.let {
        DetailedItem(
            icon = R.drawable.ic_melee_light,
            "Light melee",
            value = it.melee.toString(),
            180
        )
        DetailedItem(
            icon = R.drawable.ic_melee_heavy,
            "Heavy melee",
            value = it.heavy_melee.toString(),
            360
        )
        if(ADS_ENABLED)
        LoadSimpleAd(modifier = Modifier.height(90.dp))    }
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
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Log.d("ARSENAL", type)
        Log.d("ARSENAL", value)
        LinearProgressIndicator(
            progress = { value.toFloat() / maxValue },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            modifier = Modifier.width(32.dp),
            text = value,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.End
        )
    }
}


// PREVIEWS

@Preview
@Composable
fun PreviewWeapon() {
    ArsenalDetailScreen(
        Weapons(
            name = "BASEBALL BAT",
            stats = Stats(
                sway = 50,
                vertical_recoil = 2.6,
                spread = 65.4,
                rpm = 50,
                damage = 120,
                cycle_time = 1.4,
                effective_range = "248m",
                reload_speed = 4.2,
                muzzle_velocity = 550,
                control_range = 100,
                throw_range = 10,
                radius = 5,
                fuse_timer = 5,
                duration = 4,
                sighted_range = 12,
            ),
            melee_stats = MeleeStats(melee = 57, heavy_melee = 120)
        )
    )
}

@Preview
@Composable
fun PreviewTool() {
    ArsenalDetailScreen(
        Tools(
            name = "BASEBALL BAT",
            stats = Stats(
                sway = 50,
                vertical_recoil = 2.6,
                spread = 65.4,
                rpm = 50,
                damage = 120,
                cycle_time = 1.4,
                effective_range = "248m",
                reload_speed = 4.2,
                muzzle_velocity = 550
            ),
            melee_stats = MeleeStats(melee = 57, heavy_melee = 120)
        )
    )
}

@Preview
@Composable
fun PreviewConsumable() {
    ArsenalDetailScreen(
        Consumables(
            name = "BASEBALL BAT",
            stats = Stats(
                duration = 20,
                damage = 120,
                fuse_timer = 3,
                radius = 2,
                throw_range = 15,
                control_range = 100
            ),
            melee_stats = MeleeStats(melee = 57, heavy_melee = 120)
        )
    )
}