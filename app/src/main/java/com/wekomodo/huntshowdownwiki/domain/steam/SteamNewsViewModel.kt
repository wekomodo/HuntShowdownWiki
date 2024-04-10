package com.wekomodo.huntshowdownwiki.domain.steam

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.wekomodo.huntshowdownwiki.data.model.steam.News
import com.wekomodo.huntshowdownwiki.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SteamNewsViewModel @Inject constructor(
    private val respository : SteamNewsRepository,
    private val useCase: SteamNewsUseCase
) : ViewModel(){


    private val _steamNews = MutableStateFlow<Resource<News?>>(Resource.Loading())
    val steamNews  = _steamNews.asStateFlow()

    fun getNews(  appId: String,
                  count: String,
                  maxLength: String,
                  format: String) {
        viewModelScope.launch{
            _steamNews.value = useCase(appId,count,maxLength,format)
        }
    }
}