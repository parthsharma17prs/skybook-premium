package com.skybook.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybook.R
import com.skybook.local.AppDatabase
import com.skybook.local.FlightEntity
import kotlinx.coroutines.launch

class FlightResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_results)

        val from = intent.getStringExtra("FROM_CITY") ?: "New York"
        val to = intent.getStringExtra("TO_CITY") ?: "Los Angeles"

        findViewById<ImageView>(R.id.btn_back).setOnClickListener { onBackPressed() }

        val rv = findViewById<RecyclerView>(R.id.rv_flights)
        rv.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@FlightResultsActivity).dao()
            val flights = dao.searchFlights(from, to)
            rv.adapter = FlightListAdapter(flights) { flightId ->
                val intent = Intent(this@FlightResultsActivity, SeatSelectorActivity::class.java)
                intent.putExtra("FLIGHT_ID", flightId)
                startActivity(intent)
            }
        }
    }
}

class FlightListAdapter(
    private val flights: List<FlightEntity>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<FlightListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ImageView = view.findViewById(R.id.iv_airline_logo)
        val fromCity: TextView = view.findViewById(R.id.tv_from_city)
        val toCity: TextView = view.findViewById(R.id.tv_to_city)
        val fromCode: TextView = view.findViewById(R.id.tv_from_code)
        val toCode: TextView = view.findViewById(R.id.tv_to_code)
        val duration: TextView = view.findViewById(R.id.tv_duration)
        val price: TextView = view.findViewById(R.id.tv_price)
        val classType: TextView = view.findViewById(R.id.tv_class_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flight_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flight = flights[position]
        holder.fromCity.text = flight.fromCity
        holder.toCity.text = flight.toCity
        holder.fromCode.text = flight.fromCode
        holder.toCode.text = flight.toCode
        holder.duration.text = flight.duration
        holder.price.text = "$${flight.price}"
        holder.classType.text = "${flight.classType} class"
        
        // Dynamically get logo resource
        val resId = holder.itemView.context.resources.getIdentifier(flight.airlineLogo, "drawable", holder.itemView.context.packageName)
        if (resId != 0) holder.logo.setImageResource(resId)

        holder.itemView.setOnClickListener { onClick(flight.id) }
    }

    override fun getItemCount() = flights.size
}
