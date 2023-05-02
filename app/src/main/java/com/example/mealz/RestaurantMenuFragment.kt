package com.example.mealz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.databinding.FragmentRestaurantMenuBinding

class RestaurantMenuFragment : Fragment() , MenuItemClickListener{

    lateinit var binding: FragmentRestaurantMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_menu, container, false)
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager = layoutManager
        val restaurantId = arguments?.getInt("Res")
        val myAdapter =
            restaurantId?.let { loadData(it)?.let { MenuItemsAdapter(it,requireContext(),this) } }
        myRecyclerView.adapter = myAdapter

        return view
    }

    fun loadData(idRes:Int): List<MenuItem>? {
        var data = mutableListOf<MenuItem>()
        data = appDataBase.buildDatabase(requireContext())?.getMenuItemDAO()?.getMenuItemsByRes(idRes) as MutableList<MenuItem>

    return data
    }
    override fun onCellClickListener(data: MenuItem) {
        var bundle = bundleOf("mId" to data.idMenu)

        this.findNavController().navigate(R.id.action_restaurantMenuFragment_to_menuItemDetailsFragment,bundle)
    }
}

