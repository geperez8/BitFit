package com.example.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment(), CoroutineScope by MainScope() {

    private val foods = ArrayList<DisplayFood>()

    private lateinit var averageCaloriesText : TextView
    private lateinit var maxCaloriesText : TextView
    private lateinit var minCaloriesText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)


        averageCaloriesText = view.findViewById(R.id.averageValue)
        maxCaloriesText = view.findViewById(R.id.maximumValue)
        minCaloriesText = view.findViewById(R.id.minimumValue)
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
                populateScreen()
                // Now you can update UI or do other operations with 'foods' list
            }
        }
    }

    private fun populateScreen(){
        var total = 0
        var count = 0
        var min = 1000000
        var max = -100000000

        for (food in foods){
            val currCal = food.calories?.toInt()

            total += currCal ?: 0

            count += 1

            if (currCal!! > max ){
                max = currCal
            }

            if (currCal!! < min){
                min = currCal
            }
        }
        val averageCalories : Number
        if (count != 0) {
            averageCalories = total / count
        }
        else{
            averageCalories = 0
        }

        averageCaloriesText.setText(averageCalories.toString())
        minCaloriesText.setText(min.toString())
        maxCaloriesText.setText(max.toString())
    }

    companion object {
        fun newInstance(): BitFitFragment{
            return BitFitFragment()
        }
    }
}