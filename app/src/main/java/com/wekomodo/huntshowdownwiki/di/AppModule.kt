package com.wekomodo.huntshowdownwiki.di

import com.wekomodo.huntshowdownwiki.data.remote.InterfaceAPI
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi(): InterfaceAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfaceAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideMainRepository(api: InterfaceAPI): SteamNewsRepository = SteamNewsRepository(api)

}