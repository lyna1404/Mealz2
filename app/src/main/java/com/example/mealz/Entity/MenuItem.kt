package com.example.mealz.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mealz.Restaurant

@Entity(tableName = "MenuItem", foreignKeys = [

    ForeignKey(entity= Restaurant::class,
        parentColumns=["idRes"],childColumns = ["id_res"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE )])
data class MenuItem(
                    @PrimaryKey(autoGenerate = true)
                    val idMenu:Int = 0,
                    val name:String,
                    val description:String,
                    var image:Int,
                    var type:String,
                    val quantity:Int,
                    val price:Int,
                    val id_res:Int
)