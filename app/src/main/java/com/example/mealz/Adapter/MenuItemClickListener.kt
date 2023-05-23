package com.example.mealz.Adapter

import com.example.mealz.Entity.Menu


interface MenuItemClickListener {
    fun onCellClickListener(data: Menu)
}