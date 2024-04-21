package com.wekomodo.huntshowdownwiki.domain.firebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wekomodo.huntshowdownwiki.data.model.firebase.traits.Trait
import com.wekomodo.huntshowdownwiki.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class FirebaseViewModel : ViewModel() {
   // val response : MutableState<FirebaseDataState> = mutableStateOf(FirebaseDataState.Empty)
    private val _response = MutableStateFlow<Resource<MutableList<Trait>>>(Resource.Loading())
    val response = _response.asStateFlow()
    init{
        fetchData()
    }

    private fun fetchData() {
        val tempList = mutableListOf(Trait())
        FirebaseDatabase.getInstance().getReference().child("traits").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (items in snapshot.children) {
                    val trait = items.getValue(Trait::class.java)
                    tempList.remove(Trait())
                    trait?.let {
                        tempList.add(trait)
                    }
                }
                Log.d("firebasetraits", tempList.toString())
                _response.value = Resource.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase Call", error.message)
            }

        })
    }
}