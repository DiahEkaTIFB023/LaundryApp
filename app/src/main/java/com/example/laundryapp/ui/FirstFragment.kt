package com.example.laundryapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundryapp.application.LaundryApplication
import com.example.laundryapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext : Context
    private val LaundryViewModel : LaundryViewModel by viewModels {
        LaundryViewModelFactory((applicationContext as LaundryApplication).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //list yang bisa di klik dan mendapatkan data LaundryModell jadi tidak null
        val adapter = LaundryListAdapter{LaundryViewModell ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(LaundryViewModell)
            findNavController().navigate(action)
        }

        binding.dataRecyclerView.adapter = adapter
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(context)
        LaundryViewModel.allLaundry.observe(viewLifecycleOwner) {LaundryModells ->
            LaundryModells.let {
                if (LaundryModells.isEmpty()){
                    binding.emptyTextView.visibility = View.VISIBLE
                    binding.ilustrationImageView.visibility = View.VISIBLE
                } else {
                    binding.emptyTextView.visibility = View.GONE
                    binding.ilustrationImageView.visibility = View.GONE
                }
                adapter.submitList(LaundryModells)
            }

        }

        binding.addFAB.setOnClickListener {
            //button tambah dan hasil Laundry pasti null
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}