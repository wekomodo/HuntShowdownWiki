package com.wekomodo.huntshowdownwiki.data.model.steam

data class Appnews(
    val appid: Int,
    val count: Int,
    val newsitems: List<Newsitem>
)