package com.example.mealz.Adapter

import com.example.mealz.Entity.MenuItem

interface MenuItemClickListener {
    fun onCellClickListener(data: MenuItem)
}