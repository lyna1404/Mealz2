package com.example.mealz

import androidx.room.*

@Dao
interface restaurantDAO {

        @Query("select * from Restaurant")
        fun getRestaurantsAll():MutableList<Restaurant>
        @Query("select * from Restaurant where name=:Name")
        fun getRestaurantsByName(Name:String):List<Restaurant>
        @Query("select * from Restaurant where idRes=:Id")
        fun getRestaurantsById(Id:Int):Restaurant
        @Insert
        fun addRestaurant(vararg Restaurants:Restaurant)
        @Update
        fun updateRestaurant(Restaurants:Restaurant)
        @Delete
        fun deleteRestaurant(Restaurants:Restaurant)
        @Query("DELETE FROM Restaurant")
        fun deleteAll()
}
