package com.skybook.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skybook.SkyBookApplication
import com.skybook.activities.CheckInActivity
import com.skybook.adapters.BookingAdapter
import com.skybook.databinding.FragmentBookingsBinding
import com.skybook.models.ApiResponse
import com.skybook.viewmodels.BookingViewModel

class BookingsFragment : Fragment() {
    private var _binding: FragmentBookingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BookingViewModel
    private lateinit var adapter: BookingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val app = requireActivity().application as SkyBookApplication
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BookingViewModel(app.bookingRepository) as T
            }
        })[BookingViewModel::class.java]

        setupRecyclerView()
        observeViewModel()
        viewModel.getMyBookings()
    }

    private fun setupRecyclerView() {
        adapter = BookingAdapter { booking ->
            val intent = Intent(requireContext(), CheckInActivity::class.java).apply {
                putExtra("BOOKING_REF", booking.bookingRef)
            }
            startActivity(intent)
        }
        binding.rvBookings.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBookings.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.userBookings.observe(viewLifecycleOwner) { response ->
            if (response.status == ApiResponse.Status.SUCCESS) {
                adapter.submitList(response.data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
