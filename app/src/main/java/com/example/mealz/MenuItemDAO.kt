package com.example.mealz



import androidx.room.*

@Dao
interface MenuItemDAO {

        @Query("SELECT * FROM MenuItem")
        fun getAllItems(): MutableList<MenuItem>
        @Query("SELECT * FROM MenuItem WHERE id_res = :idres")
        fun getMenuItemsByRes(idres:Int): List<MenuItem>

        @Query("select * from MenuItem where name=:Name")
        fun getItemByName(Name:String):List<MenuItem>
        @Query("select * from MenuItem where idMenu=:id")
        fun getItemById(id:Int):MenuItem
        @Insert
        fun addItem(vararg Restaurants:MenuItem)
        @Update
        fun updateItem(MenuItem:MenuItem)
        @Delete
        fun deleteItem(MenuItem:MenuItem)
        @Query("DELETE FROM MenuItem")
        fun deleteAll()
}
