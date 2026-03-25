package com.skybook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skybook.SkyBookApplication
import com.skybook.adapters.FlightAdapter
import com.skybook.databinding.FragmentRecommendationsBinding
import com.skybook.viewmodels.FlightViewModel

class RecommendationsFragment : Fragment() {
    private var _binding: FragmentRecommendationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FlightViewModel
    private lateinit var adapter: FlightAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val app = requireActivity().application as SkyBookApplication
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FlightViewModel(app.flightRepository) as T
            }
        })[FlightViewModel::class.java]

        adapter = FlightAdapter {}
        binding.rvRecommendations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecommendations.adapter = adapter

        viewModel.recommendations.observe(viewLifecycleOwner) { res ->
            adapter.submitList(res.data)
        }
        viewModel.getRecommendations()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
