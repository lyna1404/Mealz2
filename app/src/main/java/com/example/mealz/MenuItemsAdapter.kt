package com.example.mealz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.databinding.MenuItemsLayoutBinding

class MenuItemsAdapter(val data: kotlin.collections.List<MenuItem>,
                       var ctx:Context,
                       val cellClickListener: MenuItemClickListener):RecyclerView.Adapter<MenuItemsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemsAdapter.MyViewHolder {
        return MyViewHolder(MenuItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MenuItemsAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            Name.text = data[position].name
            platePic.setImageResource(data[position].image)
            Category.text = data[position].type
            HomePage().setGradientTextColor(Category,Color.parseColor("#DC220F"),Color.parseColor("#F05600"))
            quantity.text = data[position].quantity.toString()+" persons"
            Price.text = data[position].price.toString()+" DA"
            HomePage().setGradientTextColor(Price, Color.parseColor("#DC220F"), Color.parseColor("#F05600"))
        }

        holder.binding.root.setOnClickListener{
            cellClickListener.onCellClickListener(data[position])
        }

    }




    class MyViewHolder(val binding: MenuItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



