package com.example.mealz.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.Fragment.CartFragment
import com.example.mealz.Entity.MenuItem
import com.example.mealz.HomePage
import com.example.mealz.appDataBase
import com.example.mealz.databinding.CartItemsLayoutBinding

class cartItemsAdapter(val data: kotlin.collections.MutableList<MenuItem>,
                       var ctx:Context):RecyclerView.Adapter<cartItemsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CartItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Name.text = data[position].name
            platePic.setImageResource(data[position].image)
            HomePage().setGradientTextColor(
                Price,
                Color.parseColor("#DC220F"),
                Color.parseColor("#F05600")
            )
            Price.text = data[position].price.toString() + " DA"
            imageButton.setOnClickListener() {
//                appDataBase.buildDatabase(ctx)?.getMenuItemDAO()?.getItemById(data[position].idMenu)
//                    ?.let { it1 ->
//                        appDataBase.buildDatabase(ctx)?.getMenuItemDAO()?.deleteItem(
//                            it1
//                        )
            }
            val totalFees = (ctx as? CartFragment)?.countTotalFees(data)
            data.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
            totalFees?.let { it1 -> (ctx as? CartFragment)?.updateTotalFees(it1) }
        }
    }


    class MyViewHolder(val binding: CartItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root)


}
