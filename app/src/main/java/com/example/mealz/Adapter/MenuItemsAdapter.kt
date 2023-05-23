package com.example.mealz.Adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealz.Entity.Menu
import com.example.mealz.Entity.Restaurant

import com.example.mealz.HomePage
import com.example.mealz.databinding.MenuItemsLayoutBinding

class MenuItemsAdapter(
                       var ctx:Context,
                       val cellClickListener: MenuItemClickListener
):RecyclerView.Adapter<MenuItemsAdapter.MyViewHolder>() {
    var data = mutableListOf<Menu>()
    fun setMenu(Menus: List<Menu>) {
        this.data = Menus.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MenuItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Name.text = data[position].Nom
            Category.text = data[position].Nom_TMenu
            HomePage().setGradientTextColor(Category,Color.parseColor("#DC220F"),Color.parseColor("#F05600"))
            quantity.text = "1 person"
            Price.text = data[position].Prix_unitare.toString()+" DA"
            HomePage().setGradientTextColor(Price, Color.parseColor("#DC220F"), Color.parseColor("#F05600"))
            Glide.with(ctx).load(data[position].Image).into(platePic)
        }
        holder.binding.root.setOnClickListener{
            cellClickListener.onCellClickListener(data[position])
        }

    }




    class MyViewHolder(val binding: MenuItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



