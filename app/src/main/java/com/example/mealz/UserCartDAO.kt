package com.example.mealz

import androidx.room.*
import com.example.mealz.Entity.Menu

import com.example.mealz.Entity.UserCart

@Dao
interface UserCartDAO {
        @Insert()
        fun insertUserCart(userCart: UserCart):Long

        @Delete
        fun deleteUserCart(userCart: UserCart)

        @Query("SELECT * FROM CartMenu WHERE IDUtilisateur = :userId")
        fun getUserCart(userId: Int): List<UserCart>

        @Query("DELETE FROM CartMenu WHERE IDUtilisateur = :userId")
        fun deleteAllUserCart(userId: Int):Int

        @Query("DELETE FROM CartMenu WHERE IDMenu = :menuId")
        fun deleteUserCart(menuId: Int):Int



        @Query("DELETE FROM CartMenu")
        fun deleteAll():Int
}

