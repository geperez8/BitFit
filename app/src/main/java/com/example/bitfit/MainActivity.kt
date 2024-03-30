package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val foods = ArrayList<DisplayFood>()
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var addNewFoodButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // declare the variables associated with this class
        foodsRecyclerView = findViewById(R.id.foods)

        addNewFoodButton = findViewById(R.id.addFoodButton)

        addNewFoodButton.setOnClickListener {
            val intent = Intent(this, FoodsActivity::class.java)
            this.startActivity(intent)
        }

        val foodAdapter = FoodAdapter(this, foods)
        foodsRecyclerView.adapter = foodAdapter

        foodsRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as FoodsApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.calories
                    )
                }.also { mappedList ->
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}