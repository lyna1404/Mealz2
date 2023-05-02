package com.example.mealz

import androidx.room.*

@Dao
interface UserCartDAO {
        @Insert()
        fun insertUserCart(userCart: UserCart):Long

        @Delete
        fun deleteUserCart(userCart: UserCart)

        @Query("SELECT * FROM CartMenu WHERE id_user = :userId")
        fun getUserCart(userId: Int): List<UserCart>

        @Query("DELETE FROM CartMenu WHERE id_user = :userId")
        fun deleteAllUserCart(userId: Int):Int

         @Query("SELECT * FROM CartMenu INNER JOIN MenuItem ON CartMenu.id_menu = MenuItem.idMenu WHERE CartMenu.id_user = :userId")
        fun getItemsForUser(userId: Int): List<MenuItem>

        @Query("DELETE FROM CartMenu")
        fun deleteAll():Int
}

