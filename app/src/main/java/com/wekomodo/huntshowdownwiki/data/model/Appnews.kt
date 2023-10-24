package com.wekomodo.huntshowdownwiki.data.model

data class Appnews(
    val appid: Int,
    val count: Int,
    val newsitems: List<Newsitem>
)