package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// declare it as a Dao so room knows to process it
@Dao
interface FoodDao {

    // get all entries from db (Flow for asynch)
    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<FoodEntity>>


    // inserts list of ArticleEntity objects into article_table
    @Insert
    fun insert(food: FoodEntity)

    // deletes all entries in article_table
    @Query("DELETE FROM food_table")
    fun deleteAll()
}