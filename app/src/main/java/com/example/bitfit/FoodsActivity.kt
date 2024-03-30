package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodsActivity : AppCompatActivity() {

    private lateinit var addFoodButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.food_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addFoodButton = findViewById(R.id.entrySubmitButton)

        addFoodButton.setOnClickListener {

           val foodName = findViewById<EditText>(R.id.foodNameEntry).text.toString()
           val foodCalories =  findViewById<EditText>(R.id.caloriesEntry).text.toString()

            if (foodName != "" && foodCalories != ""){
                lifecycleScope.launch(Dispatchers.IO) {
                    (application as FoodsApplication).db.foodDao().insert(
                        FoodEntity(
                            name = foodName,
                            calories = foodCalories
                        )
                    )
                }

                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }
        }



    }
}