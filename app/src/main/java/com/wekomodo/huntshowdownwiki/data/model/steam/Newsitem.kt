package com.wekomodo.huntshowdownwiki.data.model.steam

data class Newsitem(
    val appid: Int = 0,
    val author: String = "",
    val contents: String = "",
    val date: Int = 0,
    val feed_type: Int = 0,
    val feedlabel: String = "",
    val feedname: String = "",
    val gid: String = "",
    val is_external_url: Boolean = false,
    val title: String = "",
    val url: String = ""
)