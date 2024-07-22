package com.wekomodo.huntshowdownwiki.domain.firebase.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.weapons.Weapons
import com.wekomodo.huntshowdownwiki.data.model.firebase.traits.Trait
import com.wekomodo.huntshowdownwiki.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class FirebaseViewModel : ViewModel() {
   // val response : MutableState<FirebaseDataState> = mutableStateOf(FirebaseDataState.Empty)
    private val _traits = MutableStateFlow<Resource<MutableList<Trait>>>(Resource.Loading())
    val traits = _traits.asStateFlow()

    private val _weapons = MutableStateFlow<Resource<MutableList<Weapons>>>(Resource.Loading())
    val weapons = _weapons.asStateFlow()

    private val _tools = MutableStateFlow<Resource<MutableList<Trait>>>(Resource.Loading())
    val tools = _tools.asStateFlow()

    private val _consumables = MutableStateFlow<Resource<MutableList<Trait>>>(Resource.Loading())
    val consumables = _consumables.asStateFlow()
    init{
        fetchTraits()
    }

    private fun fetchTraits() {
        val tempList = mutableListOf(Trait())
        FirebaseDatabase.getInstance().getReference().child("traits").get().addOnSuccessListener {
            for (traits in it.children){
                val trait = traits.getValue(Trait::class.java)
                tempList.remove(Trait())
                trait?.let {
                    tempList.add(trait)
                }
            }
            _traits.value = Resource.Success(tempList)
        }
    }
    private fun fetchWeapons() {
        val tempList = mutableListOf(Weapons())
        FirebaseDatabase.getInstance().getReference().child("items/weapons").get().addOnSuccessListener {
            for (weapons in it.children){
                val weapon = weapons.getValue(Weapons::class.java)
                tempList.remove(Weapons())
                weapon?.let {
                    tempList.add(weapon)
                }
            }
            _weapons.value = Resource.Success(tempList)
        }
    }

    private fun fetchTools() {
        val tempList = mutableListOf(Trait())
        FirebaseDatabase.getInstance().getReference().child("items/tools").get().addOnSuccessListener {
            for (tools in it.children){
                val trait = tools.getValue(Trait::class.java)
                tempList.remove(Trait())
                trait?.let {
                    tempList.add(trait)
                }
            }
            _traits.value = Resource.Success(tempList)
        }
    }
    private fun fetchConsumables() {
        val tempList = mutableListOf(Trait())
        FirebaseDatabase.getInstance().getReference().child("items/consumables").get().addOnSuccessListener {
            for (consumables in it.children){
                val trait = consumables.getValue(Trait::class.java)
                tempList.remove(Trait())
                trait?.let {
                    tempList.add(trait)
                }
            }
            _traits.value = Resource.Success(tempList)
        }
    }
}