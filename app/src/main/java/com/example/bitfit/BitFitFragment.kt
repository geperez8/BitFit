package com.example.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch



class BitFitFragment : Fragment(), CoroutineScope by MainScope(){

    private val foods = ArrayList<DisplayFood>()
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var foodsAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_bit_fit, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        foodsRecyclerView = view.findViewById(R.id.food_recycler_view)
        foodsRecyclerView.layoutManager = layoutManager
        foodsRecyclerView.setHasFixedSize(true)
        foodsAdapter = FoodAdapter(view.context, foods)
        foodsRecyclerView.adapter = foodsAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()
        // Launching a coroutine in the lifecycle scope

    }

    private fun updateData() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Access the database within the coroutine
            (requireActivity().application as FoodsApplication).db.foodDao().getAll().collect { databaseList ->
                // Mapping database entities to DisplayFood objects
                val mappedList = databaseList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.calories
                    )
                }
                // Adding mapped items to your list
                foods.clear()
                foods.addAll(mappedList)
                // Now you can update UI or do other operations with 'foods' list
                foodsAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        fun newInstance(): BitFitFragment{
            return BitFitFragment()
        }
    }
}