package com.mohsinziabutt.newsapp.responses

import com.mohsinziabutt.newsapp.responseModels.UserModel

//data class LoginResponse(val error: Boolean, val message:String, val user: User)
data class LoginResponse(
    val error: Boolean,
    val message:String,
    val user: UserModel
    )