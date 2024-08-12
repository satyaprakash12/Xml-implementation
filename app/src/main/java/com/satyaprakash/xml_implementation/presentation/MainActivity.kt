package com.satyaprakash.xml_implementation.presentation

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.satyaprakash.xml_implementation.databinding.ActivityMainBinding
import com.satyaprakash.xml_implementation.databinding.BottomSheetBinding
import com.satyaprakash.xml_implementation.presentation.adpaters.ImagePagerAdapter
import com.satyaprakash.xml_implementation.presentation.adpaters.ListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupObservers()
        setupSearchView()
        setupFloatingActionButton()
    }

    private fun setupObservers() {
        viewModel.images.observe(this) { images ->
            val adapter = ImagePagerAdapter(images)
            binding.viewPager.adapter = adapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
                //Some implementation
            }.attach()
        }



        viewModel.items.observe(this, Observer {
            adapter = ListAdapter(it)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)

        })


        viewModel.items.observe(this, Observer {
            adapter.filter(it)

        })


    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterFruits(newText ?: "")
                return false
            }
        })
    }

    private fun setupFloatingActionButton() {
        binding.fab.setOnClickListener {
            val bottomSheet = BottomSheetDialog(this)
            val bottomSheetBinding = BottomSheetBinding.inflate(layoutInflater)
            bottomSheet.setContentView(bottomSheetBinding.root)

            val layoutParams = bottomSheetBinding.BottomSheet.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(100, 0, 100, 0) // left, top, right, bottom margins in pixels
            bottomSheetBinding.BottomSheet .layoutParams = layoutParams

            windowManager
            viewModel.items.observe(this, Observer { items->
                val fruitsTitle= items.map { it ->
                    it.title }

                val charOccurrences = calculateCharacterOccurrences(fruitsTitle)
                val topCharacters = charOccurrences.entries.sortedByDescending { it.value }.take(3)

                bottomSheetBinding.tvTopCharacters.text = topCharacters.joinToString(separator = "\n") {
                    "${it.key} = ${it.value}"
                }
            })




            bottomSheet.show()
        }
    }

    private fun calculateCharacterOccurrences(fruits: List<String>): Map<Char, Int> {
        val occurrences = mutableMapOf<Char, Int>()
        for (fruit in fruits) {
            for (char in fruit) {
                occurrences[char] = occurrences.getOrDefault(char, 0) + 1
            }
        }
        return occurrences
    }
}


