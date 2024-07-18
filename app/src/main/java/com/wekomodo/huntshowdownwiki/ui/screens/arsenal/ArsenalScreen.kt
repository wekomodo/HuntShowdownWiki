package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.wekomodo.huntshowdownwiki.data.model.firebase.weapons.Weapons
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp


@Composable
fun ArsenalScreen() {
    val context = LocalContext.current
    val emptyObject = Weapons()
    val itemList = remember {
        mutableStateListOf(emptyObject)
    }
    var weapons by remember {
        mutableStateOf(true)
    }
    var tools by remember {
        mutableStateOf(true)
    }
    var consumables by remember {
        mutableStateOf(true)
    }
    //var filteredList: List<Item> = emptyList()
    LaunchedEffect(Unit) {
        val database = Firebase.database.reference.child("items").child("weapons")
        Log.d("firebaseResult", database.toString())
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (items in p0.children) {
                    Log.d("firebaseItems",items.value.toString())
                    val item = items.getValue(Weapons::class.java)
                    itemList.remove(emptyObject)
                    item?.let {
                        itemList.add(item)
                    }
                 //   filteredList = itemList.toList()
                   // Log.d("firebaseItems", item.toString())
                }

            }
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Some Error Occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FilterChipComp(enabled = weapons, name = "Weapons") {
                weapons = it
            }
            FilterChipComp(enabled = tools, name = "Tools") {
                tools = it
            }
            FilterChipComp(enabled = consumables, name = "Consumables") {
                consumables = it
            }
        }
    //    Log.d("firebaseList",filteredList.toString())
        Log.d("firebaselistItem",itemList.toList().toString())
        LazyColumn {
            itemsIndexed(itemList) { _, item ->
                ArsenalItem(name = item.name, image = item.image_url,desc = item.desc)
            }
        }
       }
}
@Preview
@Composable
fun ItemScreenPreview() {
    ArsenalScreen()
}