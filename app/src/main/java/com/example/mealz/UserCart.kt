package com.example.mealz

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "CartMenu",foreignKeys = [
    ForeignKey(
        entity = MenuItem::class,
        parentColumns = ["idMenu"],
        childColumns = ["id_menu"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = User::class,
        parentColumns = ["idUser"],
        childColumns = ["id_user"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    ),
])
data class UserCart(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_menu: Int,
    val id_user: Int
)