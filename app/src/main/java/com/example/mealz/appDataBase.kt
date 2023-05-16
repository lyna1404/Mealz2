package com.example.mealz

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mealz.Entity.MenuItem
import com.example.mealz.Entity.UserCart

@Database(entities = [UserCart::class], exportSchema = false,version = 1)
    abstract class appDataBase : RoomDatabase() {
    abstract fun getUserCartDAO(): UserCartDAO



    companion object {
        private var INSTANCE: appDataBase? = null
        fun buildDatabase(context: Context): appDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    appDataBase::class.java,
                    "food_db"
                ).allowMainThreadQueries().build()
            }

            return INSTANCE
        }

    }
}
