package com.skybook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skybook.R
import com.skybook.local.FlightEntity

class RecommendationAdapter(
    private val items: List<FlightEntity>,
    private val onClick: (FlightEntity) -> Unit
) : RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val route: TextView = view.findViewById(R.id.tv_rec_route)
        val price: TextView = view.findViewById(R.id.tv_rec_price)
        val viewDeal: View = view.findViewById(R.id.btn_rec_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommendation_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.route.text = "${item.fromCity} → ${item.toCity}"
        holder.price.text = "Starting from $${String.format("%.0f", item.price)}"
        
        holder.itemView.setOnClickListener { onClick(item) }
        holder.viewDeal.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size
}
