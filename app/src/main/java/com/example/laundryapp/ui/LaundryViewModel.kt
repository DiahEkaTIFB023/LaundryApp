package com.example.laundryapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.laundryapp.model.LaundryModell
import com.example.laundryapp.repository.LaundryRepository
import kotlinx.coroutines.launch

class LaundryViewModel (private val repository: LaundryRepository): ViewModel() {
    val allLaundry: LiveData<List<LaundryModell>> = repository.allLaundry.asLiveData()


    fun insert(LaundryModell : LaundryModell) = viewModelScope.launch {
        repository.insert(LaundryModell)
    }
    fun delete(LaundryModell : LaundryModell) = viewModelScope.launch {
        repository.delete(LaundryModell)
    }
    fun update(LaundryModell : LaundryModell) = viewModelScope.launch {
        repository.update(LaundryModell)
    }
}

class LaundryViewModelFactory( private val repository: LaundryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((LaundryViewModel::class.java))){
            return LaundryViewModel(repository) as T
        }
        throw IllegalArgumentException ("Unknow ViewModel class")
    }
}