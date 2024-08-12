package com.satyaprakash.xml_implementation.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.satyaprakash.xml_implementation.data.model.ListItems
import com.satyaprakash.xml_implementation.data.repo.MainRepository

// MainViewModel.kt
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainRepository()

    private val _images = MutableLiveData<List<Int>>()
    val images: LiveData<List<Int>> get() = _images

    private val _items = MutableLiveData<List<ListItems>>()
    val items: LiveData<List<ListItems>> get() = _items




    init {
        _images.value = repository.getImages()
        _items.value = repository.getItems()
    }

    fun filterFruits(query: String) {
        _items.value = if (query.isEmpty()) {
            repository.getItems()
        } else {
            repository.getItems().filter { it.title.contains(query, ignoreCase = true) }
        }
    }
}
