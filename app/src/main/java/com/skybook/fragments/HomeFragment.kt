package com.skybook.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import com.skybook.activities.FlightResultsActivity
import com.skybook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Greeting card slides in from top
        binding.tvGreeting.translationY = -150f
        binding.tvGreeting.animate().translationY(0f).setDuration(400).start()

        binding.btnSearch.setOnClickListener {
            val from = binding.etFrom.text.toString()
            val to = binding.etTo.text.toString()
            
            val intent = Intent(requireContext(), FlightResultsActivity::class.java).apply {
                putExtra("FROM", from)
                putExtra("TO", to)
            }
            startActivity(intent)
        }

        binding.btnSwap.setOnClickListener {
            it.animate().rotationBy(180f).setDuration(300).start()
            val temp = binding.etFrom.text
            binding.etFrom.text = binding.etTo.text
            binding.etTo.text = temp
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
