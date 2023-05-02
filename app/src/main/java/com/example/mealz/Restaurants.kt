package com.example.mealz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Restaurant")
data class Restaurant(
                    @PrimaryKey(autoGenerate = true)
                      val idRes:Int = 0,
                      val name:String,
                      var image:Int,
                      var adress:String,
                      val fbPageLink:String,
                      val fbWebLink:String,
                      val instPageLink:String,
                      val instWebLink:String,
                      val emailAdress:String,
                      val type:String,
                      val phone:String,
                      val rating:Int
)