package com.example.laundryapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.laundryapp.application.LaundryApplication
import com.example.laundryapp.databinding.FragmentSecondBinding
import com.example.laundryapp.model.LaundryModell

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private lateinit var applicationContext : Context
    private val LaundryViewModel : LaundryViewModel by viewModels {
        LaundryViewModelFactory((applicationContext as LaundryApplication).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var LaundryModell : LaundryModell? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LaundryModell = args.laundry
        //kita cek jika LaundryModell null maka tampilan default nambah laundry
        //jika LaundryModell tidak null maka tampilan sedikit berubah ada tombol hapus dan ubah
        if (LaundryModell != null){
            binding.deleteButtone.visibility = View.VISIBLE
            binding.saveButton.text = "Ubah"
            //untuk menampilkan data yang mau di ubah
            binding.nameEditText.setText(LaundryModell?.name)
            binding.addressEditText.setText(LaundryModell?.address)
            binding.paketEditText.setText(LaundryModell?.paket)
            binding.weightEditText.setText(LaundryModell?.weight)
            binding.priceEditText.setText(LaundryModell?.price)
        }
        val name = binding.nameEditText.text
        val address = binding.addressEditText.text
        val paket =binding.paketEditText.text
        val weight = binding.weightEditText.text
        val price = binding.priceEditText.text

        binding.saveButton.setOnClickListener {
            //kondisi untuk data yang tidak diisi tidak dapat di simpan
            if (name.isEmpty()){
                Toast.makeText(context, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (address.isEmpty()){
                Toast.makeText(context, "Alamat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (paket.isEmpty()){
                Toast.makeText(context, "Paket Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (weight.isEmpty()){
                Toast.makeText(context, "Berat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (price.isEmpty()){
                Toast.makeText(context, "Harga Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else{
                if (LaundryModell == null){
                    val LaundryModell = LaundryModell(0, name.toString(), address.toString(),
                        paket.toString(), weight.toString(), price.toString())
                    LaundryViewModel.insert(LaundryModell)
                }else{
                    val LaundryModell = LaundryModell(LaundryModell?.id!!, name.toString(), address.toString(),
                        paket.toString(), weight.toString(), price.toString())
                    LaundryViewModel.update(LaundryModell)
                }
                findNavController().popBackStack() // untuk dismis halaman ini
            }

//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.deleteButtone.setOnClickListener {
            LaundryModell?.let { LaundryViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}