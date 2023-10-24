package com.wekomodo.huntshowdownwiki.domain.steam

import com.wekomodo.huntshowdownwiki.data.model.News
import com.wekomodo.huntshowdownwiki.util.Resource
import javax.inject.Inject

class SteamNewsUseCase @Inject constructor(
    private val repository: SteamNewsRepository
){

    suspend operator fun invoke( appId: String,
                                 count: String,
                                 maxLength: String,
                                 format: String) : Resource<News?>{
        val result = repository.getNews(appId,count,maxLength,format)
        result?.let{
            return  Resource.Success(result)
        } ?: run{
            return Resource.Error("Failed")
        }
    }
}