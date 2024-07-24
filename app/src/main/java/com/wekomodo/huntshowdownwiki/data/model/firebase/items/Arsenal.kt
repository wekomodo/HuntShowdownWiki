package com.wekomodo.huntshowdownwiki.data.model.firebase.items

import com.wekomodo.huntshowdownwiki.data.model.firebase.items.consumables.Consumables
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.tools.Tools
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.weapons.Weapons
import java.util.ArrayList

data class Arsenal(
    val name : String = "",
    val desc : String = "",
    val cost : Int = 0,
    val image_url : String = "",
    val type : List<String> = emptyList()
)

data class ArsenalObject(
    val arsenal: List<Arsenal>,
    val weapons : List<Weapons>,
    val tools : List<Tools>,
    val consumables: List<Consumables>
)


@JvmName("copyWeaponsToArsenal")
fun copyToArsenal(weapons : List<Weapons>) : List<Arsenal>{
    val list = ArrayList<Arsenal>()
    for( weapon in weapons){
        list.add( Arsenal(weapon.name,weapon.desc,weapon.cost,weapon.image_url, weapon.type))
    }
    return list
}

@JvmName("copyToolsToArsenal")
fun copyToArsenal(tools : List<Tools>) : List<Arsenal>{
    val list = ArrayList<Arsenal>()
    for( tool in tools){
        list.add( Arsenal(tool.name,tool.desc,tool.cost,tool.image_url,tool.type))
    }
    return list
}

@JvmName("copyConsumablesToArsenal")
fun copyToArsenal(consumables : List<Consumables>) : List<Arsenal>{
    val list = ArrayList<Arsenal>()
    for( consumable in consumables){
        list.add( Arsenal(consumable.name,consumable.desc,consumable.cost,consumable.image_url,consumable.type))
    }
    return list
}