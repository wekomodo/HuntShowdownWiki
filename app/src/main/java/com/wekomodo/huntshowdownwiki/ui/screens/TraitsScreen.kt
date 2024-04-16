package com.wekomodo.huntshowdownwiki.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.wekomodo.huntshowdownwiki.data.model.firebase.BaseTrait
import com.wekomodo.huntshowdownwiki.data.model.firebase.Traits
import com.wekomodo.huntshowdownwiki.ui.components.TraitsItem

@Composable
fun TraitsScreen() {
    val baseTraitList = remember {
        mutableStateListOf(BaseTrait("", 0, "", "", 0))
    }
    val database = Firebase.database.reference.child("traits")
    Log.d("firebaseResult", database.toString())
    LaunchedEffect(Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val traits = p0.getValue(Traits::class.java)
                Log.d("firebasetraits", traits.toString())
                if (traits != null) {
                    for (baseTraits in traits.base_traits) {
                        baseTraitList.remove(BaseTrait("", 0, "", "", 0))
                        baseTraitList.add(baseTraits)
                    }
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                // Handle Cancelled Data
            }
        })
    }
    Column {
        LazyColumn {
            itemsIndexed(baseTraitList) { _, item ->
                TraitsItem(link = item.image, name = item.name, item.desc)
            }
        }
        Text("hello?")
    }
}

/*fun readTraits(database: DatabaseReference)  {
    database.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            val traits = p0.getValue(Traits::class.java)
            Log.d("firebasetraits", traits.toString())
            if (traits != null) {
                baseTraitList = traits.base_traits

            }

        }

        override fun onCancelled(p0: DatabaseError) {
            // Handle Cancelled Data
        }
    })
}*/


@Preview
@Composable
fun TraitsScreenPreview() {
    TraitsScreen()
}