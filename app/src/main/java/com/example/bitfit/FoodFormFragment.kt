package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class FoodFormFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var addFoodButton: Button
    private lateinit var foodNameEntry: EditText
    private lateinit var caloriesEntry: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_form, container, false)
        // Initialize views
        addFoodButton = view.findViewById(R.id.entrySubmitButton)
        foodNameEntry = view.findViewById(R.id.foodNameEntry)
        caloriesEntry = view.findViewById(R.id.caloriesEntry)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFoodButton.setOnClickListener {
            val foodName = foodNameEntry.text.toString()
            val foodCalories = caloriesEntry.text.toString()

            if (foodName.isNotBlank() && foodCalories.isNotBlank()) {
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    (requireActivity().application as FoodsApplication).db.foodDao().insert(
                        FoodEntity(
                            name = foodName,
                            calories = foodCalories
                        )
                    )


                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                        // Update UI elements
                        foodNameEntry.setText("")
                        caloriesEntry.setText("")
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): FoodFormFragment {
            return FoodFormFragment()
        }
    }
}