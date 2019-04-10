package com.xiaoai.zhao.khttpcall.entities

data class LoginResultEntity(
        var email: String,
        var icon: String,
        var id: Int,
        var password: String,
        var token: String,
        var type: Int,
        var username: String,
        var chapterTops: List<*>,
        var collectIds: List<*>
)