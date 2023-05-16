package com.example.mealz.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealz.Adapter.cartItemsAdapter
import com.example.mealz.Entity.MenuItem
import com.example.mealz.LogIn
import com.example.mealz.appDataBase
import com.example.mealz.databinding.FragmentCartBinding

class CartFragment : Fragment(){
    lateinit var binding: FragmentCartBinding
    private lateinit var myAdapter: cartItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)



        return binding.root
        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myRecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager = layoutManager
        val data = loadData(1)
        myAdapter = data?.let { cartItemsAdapter(it, requireContext()) } ?: cartItemsAdapter(
            mutableListOf(),
            requireContext()
        )
        myRecyclerView.adapter = myAdapter
        data?.let { countTotalFees(it) }?.let { updateTotalFees(it) }
        //  binding.name6.text = data?.let { (countTotalFees(it)+500.00).toString() }
        binding.cart3.setOnClickListener() {
            val sharedPreferences = requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
            if (isLoggedIn) {
                // Delete cart if logged In
                val num: Int? = appDataBase.buildDatabase(requireContext())?.getUserCartDAO()
                    ?.deleteAllUserCart(1)
                if (num != null && num != 0) {
                    Toast.makeText(
                        requireContext(),
                        "Your command has been confirmed!",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Notify the adapter that the data has changed
                    myAdapter.data.clear()
                    myAdapter.notifyDataSetChanged()
                }

            } else {  // Start a new activity ... Log In
                val intent = Intent(activity, LogIn::class.java)
                intent.putExtra("screen", "cart")
                startActivity(intent)
            }

        }
    }
// Function to load data from local db
    fun loadData(id:Int): MutableList<MenuItem>? {
        var data = mutableListOf<MenuItem>()
        data = appDataBase.buildDatabase(requireContext())?.getUserCartDAO()
                ?.getItemsForUser(id) as MutableList<MenuItem>
        return data
    }


        fun countTotalFees(menuItems: List<MenuItem>): Double {
            var totalFees = 0.0
            for (menuItem in menuItems) {
                totalFees += menuItem.price
            }
            return totalFees
        }
    fun updateTotalFees(totalFees: Double) {
        if (totalFees == 0.0) {
            binding.name6.text = (totalFees).toString() + " DA"
        }
        else{
            binding.name6.text = (totalFees+500).toString() + " DA"
        }
        myAdapter.notifyDataSetChanged()
    }

}



