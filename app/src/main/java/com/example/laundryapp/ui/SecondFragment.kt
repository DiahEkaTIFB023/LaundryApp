package com.example.laundryapp.ui

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.laundryapp.R
import com.example.laundryapp.application.LaundryApplication
import com.example.laundryapp.databinding.FragmentSecondBinding
import com.example.laundryapp.model.LaundryModell
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private lateinit var applicationContext : Context
    private val LaundryViewModel : LaundryViewModel by viewModels {
        LaundryViewModelFactory((applicationContext as LaundryApplication).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var LaundryModell : LaundryModell? = null
    private lateinit var mMap: GoogleMap
    private var currentLatLang : LatLng? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
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
            binding.numberEditText.setText(LaundryModell?.no)
            binding.emailEditText.setText(LaundryModell?.email)
            binding.branchEditTextt.setText(LaundryModell?.branch)
            binding.addressEditText.setText(LaundryModell?.address)
        }

        //binding google map
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkPermission()

        val name = binding.nameEditText.text
        val no = binding.numberEditText.text
        val email =binding.emailEditText.text
        val branch = binding.branchEditTextt.text
        val address = binding.addressEditText.text

        binding.saveButton.setOnClickListener {
            //kondisi untuk data yang tidak diisi tidak dapat di simpan
            if (name.isEmpty()){
                Toast.makeText(context, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (no.isEmpty()){
                Toast.makeText(context, "Nomor Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (email.isEmpty()){
                Toast.makeText(context, "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (branch.isEmpty()){
                Toast.makeText(context, "Cabang Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else if (address.isEmpty()){
                Toast.makeText(context, "Alamat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            }else{
                if (LaundryModell == null){
                    val LaundryModell = LaundryModell(0, name.toString(), no.toString(),
                        email.toString(),
                        branch.toString(), address.toString(), currentLatLang?.latitude , currentLatLang?.longitude)
                    LaundryViewModel.insert(LaundryModell)
                }else{
                    val LaundryModell = LaundryModell(LaundryModell?.id!!, name.toString(),
                        no.toString(),
                        email.toString(),
                        branch.toString(), address.toString(), currentLatLang?.latitude, currentLatLang?.longitude)
                    LaundryViewModel.update(LaundryModell)
                }
                findNavController().popBackStack() // untuk dismis halaman ini
            }

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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Implement drag marker
        mMap.setOnMarkerDragListener(this)

        val uiSettings = mMap.uiSettings
        uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerDragListener(this)
    }

    override fun onMarkerDrag(p0: Marker) {}

    override fun onMarkerDragEnd(marker: Marker) {
        val newPosition = marker.position
        currentLatLang = LatLng(newPosition.latitude, newPosition.longitude)
        Toast.makeText(context, currentLatLang.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDragStart(p0: Marker) {
    }
    private fun checkPermission() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        if (ContextCompat.checkSelfPermission(
            applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            getCurrentLocation()
        } else{
            Toast.makeText(applicationContext, "Akses Lokasi Ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCurrentLocation() {
        // Mengecek jika permission tidak disetujui maka akan berhenti di kondisi if
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }

        // untuk test current location
        fusedLocationClient.lastLocation
            .addOnSuccessListener {location ->
                if (location != null) {
                    var LatLang = LatLng(location.latitude, location.longitude)
                    currentLatLang = LatLang
                    var title = "MyLaundry"

                    //Menampilkan lokasi sesuai koordinat yang sudah disimpan atau update

                    if (LaundryModell != null) {
                        title = LaundryModell?.name.toString()
                        val newCurrentLocation = LatLng(LaundryModell?.latitude!!, LaundryModell?.longitude!!)
                        LatLang = newCurrentLocation
                    }

                    val markerOptions = MarkerOptions()
                        .position(LatLang)
                        .title(title)
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_laundry))
                    mMap.addMarker(markerOptions)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLang, 15f))

                }
            }

    }
}