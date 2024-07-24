package com.wekomodo.huntshowdownwiki.domain.firebase

import com.wekomodo.huntshowdownwiki.data.model.firebase.traits.Trait

sealed class FirebaseDataState {
    class Success(val data : MutableList<Trait>) : FirebaseDataState()
    class Failure(val message : String) : FirebaseDataState()

    object Loading : FirebaseDataState()
    object Empty : FirebaseDataState()
}