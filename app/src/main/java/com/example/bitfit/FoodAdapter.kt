package com.example.bitfit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter (private val context: Context, private val items: ArrayList<DisplayFood>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val foodNameTextView: TextView
        val caloriesTextView: TextView

        init {
            foodNameTextView = itemView.findViewById<TextView>(R.id.entryName)
            caloriesTextView = itemView.findViewById<TextView>(R.id.entryValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.food_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items.get(position)
        // Set item views based on views and data model
        holder.foodNameTextView.text = item.foodName
        holder.caloriesTextView.text = item.calories

    }
}
//
//const val ARTICLE_EXTRA = "ARTICLE_EXTRA"
//private const val TAG = "ArticleAdapter"
//
//class ArticleAdapter(private val context: Context, private val articles: List<DisplayFood>) :
//    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val article = articles[position]
//        holder.bind(article)
//    }
//
//    override fun getItemCount() = articles.size
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
//        View.OnClickListener {
//
//        private val foodNameTextView = itemView.findViewById<TextView>(R.id.entryName)
//        private val caloriesTextView = itemView.findViewById<TextView>(R.id.entryValue)
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        fun bind(foodItem: DisplayFood) {
//            foodNameTextView.text = foodItem.foodName
//            caloriesTextView.text = foodItem.calories.toString()
//
//        }
//
////        override fun onClick(v: View?) {
////            // Get selected article
////            val article = articles[absoluteAdapterPosition]
////
////            // Navigate to Details screen and pass selected article
////            val intent = Intent(context, DetailActivity::class.java)
////            intent.putExtra(ARTICLE_EXTRA, article)
////            context.startActivity(intent)
////        }
//    }
//}