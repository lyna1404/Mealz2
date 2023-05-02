package com.example.mealz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.databinding.FragmentHomeBinding


class HomeFragment : Fragment(),CellClickListener {
    lateinit var binding: FragmentHomeBinding
    private lateinit var myAdapter: RestaurantsListAdapter // declare adapter here


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val screen = requireActivity().intent.getStringExtra("screen")
        if(screen == "cart"){
            view.findNavController().navigate(R.id.cartFragment)
        }

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        val myAdapter = loadData()?.let { RestaurantsListAdapter(it,requireContext(),this) }
        binding.recyclerView.adapter = myAdapter
    }

    fun loadData(): List<Restaurant>? {
var data = mutableListOf<Restaurant>()
        data = appDataBase.buildDatabase(requireContext())?.getRestaurantDao()?.getRestaurantsAll()!!
        return data

      }


    override fun onCellClickListener(data: Restaurant) {

        val bundle = bundleOf("Res" to data.idRes)
        this.findNavController().navigate(R.id.action_homeFragment_to_restaurantMenuFragment,bundle)
    }


}