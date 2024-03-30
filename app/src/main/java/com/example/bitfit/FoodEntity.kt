package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// declare table name
@Entity(tableName = "food_table")
data class FoodEntity(
    // add a primary key to each table row
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    // create columns with names and expected value types
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "calories") val calories: String?
)