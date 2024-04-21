package com.wekomodo.huntshowdownwiki.ui.screens.traits

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.wekomodo.huntshowdownwiki.data.model.firebase.traits.Trait
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp

@Composable
fun TraitsScreen() {
    val context = LocalContext.current
    val emptyObject = Trait("", 0, "", "", "", "", 0, 0, "")
    val traitsList = remember {
        mutableStateListOf(emptyObject)
    }
    var base by remember {
        mutableStateOf(true)
    }
    var burn by remember {
        mutableStateOf(true)
    }
    var event by remember {
        mutableStateOf(true)
    }
    var filteredList: List<Trait> = emptyList()
    LaunchedEffect(Unit) {
        val database = Firebase.database.reference.child("traits")
        Log.d("firebaseResult", database.toString())
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (items in p0.children) {
                    val trait = items.getValue(Trait::class.java)
                    traitsList.remove(emptyObject)
                    trait?.let {
                        traitsList.add(trait)
                    }
                    filteredList = traitsList.toList()
                    Log.d("firebasetraits", trait.toString())

                }

            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Some Error Occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FilterChipComp(enabled = base, name = "Base") {
                base = it
            }
            FilterChipComp(enabled = burn, name = "Burn") {
                burn = it
            }
            FilterChipComp(enabled = event, name = "Event") {
                event = it
            }
        }
        if (base) {
            filteredList = filteredList + traitsList.filter {
                it.type == "Base Trait"
            }
        }
        if (burn) {
            filteredList = filteredList + traitsList.filter {
                it.type == "Burn Trait"
            }
        }
        if (event) {
            filteredList = filteredList + traitsList.filter {
                it.type == "Event Trait"
            }
        }

        LazyColumn {
            itemsIndexed(filteredList) { _, item ->
                TraitsItem(link = item.image, name = item.name, item.desc, item.cost,item.rank_unlocked)
            }
        }
        Text("Nothing Selected?")
    }
}

@Preview
@Composable
fun TraitsScreenPreview() {
    TraitsScreen()
}