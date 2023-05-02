package com.example.mealz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.databinding.RestaurantsLayoutBinding

class RestaurantsListAdapter(
    val data: List<Restaurant>, var ctx: Context,
    val cellClickListener: CellClickListener):RecyclerView.Adapter<RestaurantsListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RestaurantsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Name.text = data[position].name
            restaurantLogo.setImageResource(data[position].image)
            Adress.text = data[position].adress
            phoneNumber.text = data[position].phone
            Type.text = data[position].type
            HomePage().setGradientTextColor(rating, Color.parseColor("#DC220F"), Color.parseColor("#F05600"))
            rating.text = data[position].rating.toString()
        }

        holder.binding.facebook.setOnClickListener {
            openPage(ctx, data[position].fbPageLink, data[position].fbWebLink)
        }
        holder.binding.instagram.setOnClickListener {
            openPage(ctx, data[position].instPageLink, data[position].instWebLink)
        }

        holder.binding.restaurantNumber.setOnClickListener{
            val data = Uri.parse("tel:021212121")
            val intent = Intent(Intent.ACTION_DIAL, data)
            ctx.startActivity(intent)
        }
        holder.binding.restaurantAdress.setOnClickListener {
            val latitude =  45.8099062
            val longitude = 15.9816518
            val data = Uri.parse("geo:$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW,data)
            ctx.startActivity(intent)
        }
        holder.binding.email.setOnClickListener{
            sendEmail(ctx,"jm_haddou@esi.dz")
        }


        holder.binding.root.setOnClickListener{
            cellClickListener.onCellClickListener(data[position])
        }

    }




    class MyViewHolder(val binding: RestaurantsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



