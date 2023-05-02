package com.example.mealz

import androidx.room.*

@Dao
interface UserDAO {


        @Query("select * from UserEItem where email=:mail")
        fun getUserByemail(mail:String):User

        @Query("DELETE FROM UserEItem")
        fun deleteAll()

        @Insert
        fun addUser(vararg User:User)
        @Update
        fun updateUser(User:User)

}
