package com.wekomodo.huntshowdownwiki.domain.firebase

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.consumables.Consumable
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.weapons.Weapon
import com.wekomodo.huntshowdownwiki.data.model.firebase.traits.Trait
import com.wekomodo.huntshowdownwiki.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class FirebaseViewModel : ViewModel() {
   // val response : MutableState<FirebaseDataState> = mutableStateOf(FirebaseDataState.Empty)
    private val _traits = MutableStateFlow<Resource<MutableList<Trait>>>(Resource.Loading())
    val traits = _traits.asStateFlow()

    private val _weapons = MutableStateFlow<Resource<MutableList<Weapon>>>(Resource.Loading())
    val weapons = _weapons.asStateFlow()
/*
    private val _tools = MutableStateFlow<Resource<MutableList<Tool>>>(Resource.Loading())
    val tools = _tools.asStateFlow()*/

    private val _consumables = MutableStateFlow<Resource<MutableList<Consumable>>>(Resource.Loading())
    val consumables = _consumables.asStateFlow()

 /*   private val _arsenal = MutableStateFlow<Resource<ArsenalObject>>(Resource.Loading())
    val arsenal = _arsenal.asStateFlow()*/
    init{
        fetchTraits()
        fetchArsenal()
    }

    private fun fetchTraits() {
        val tempList = mutableListOf<Trait>()
        FirebaseDatabase.getInstance().getReference().child("traitsNew").orderByChild("name").get().addOnSuccessListener {
            for (traits in it.children){
                val trait = traits.getValue(Trait::class.java)
              //  tempList.clear()
                trait?.let {
                    tempList.add(trait)
                }
            }
            tempList.sortBy { sort ->
                sort.name }
            _traits.value = Resource.Success(tempList)
        }
    }

    private fun fetchArsenal(){
       // var arsenalList: List<Arsenal>
        val weaponsList = mutableListOf<Weapon>()
      //  val toolsList = mutableListOf<Tools>()
        val consumablesList = mutableListOf<Consumable>()
        FirebaseDatabase.getInstance().getReference().child("items").get().addOnSuccessListener {
            for (weapons in it.child("weapons").children){
                val weapon = weapons.getValue(Weapon::class.java)
                weapon?.let {
                    weaponsList.add(weapon)
                }
            }
            _weapons.value = Resource.Success(weaponsList)
         /*   for (tools in it.child("tools").children){
                val tool = tools.getValue(Tool::class.java)
                tool?.let {
                    toolsList.add(tool)
                }
            }
            _tools.value = Resource.Success(toolsList)*/
            for (consumables in it.child("consumables").children){
                val consumable = consumables.getValue(Consumable::class.java)
                consumable?.let {
                    consumablesList.add(consumable)
                }
            }
            _consumables.value = Resource.Success(consumablesList)
//            arsenalList = copyToArsenal(weaponsList) + copyToArsenal(toolsList) + copyToArsenal(consumablesList)
//            val arsenalObject = ArsenalObject(arsenalList,weaponsList,toolsList,consumablesList)
//            _arsenal.value = Resource.Success(arsenalObject)
        }
    }
}