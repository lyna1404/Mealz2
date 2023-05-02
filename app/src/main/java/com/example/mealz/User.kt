package com.example.mealz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserEItem")
data class User(
                    @PrimaryKey(autoGenerate = true)
                    val idUser:Int = 0,
                    val email:String,
                    val password:String,
                    val phoneNum:String,

)