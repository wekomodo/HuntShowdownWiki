package com.wekomodo.huntshowdownwiki.domain.steam

import com.wekomodo.huntshowdownwiki.data.model.steam.News
import com.wekomodo.huntshowdownwiki.data.remote.InterfaceAPI
import javax.inject.Inject

class SteamNewsRepository @Inject constructor(
    private val api: InterfaceAPI
) {


    suspend fun getNews(
        appId: String,
        count: String,
        maxLength: String,
        format: String
    ) : News? {
        return try {
            val response = api.getLatestNews(appId, count, maxLength, format)
            val result = response.body()
            return if (response.isSuccessful && result != null) {
                response.body()
            } else
                null
        }catch (e : Exception){
            null
        }
    }
}