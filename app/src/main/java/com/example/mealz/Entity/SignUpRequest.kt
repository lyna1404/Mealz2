package com.example.mealz.Entity

data class SignUpRequest(
    val username:String,
    val email:String,
    val password:String,
    val address:String?,
)
