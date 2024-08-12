package com.satyaprakash.xml_implementation.data.repo

import com.satyaprakash.xml_implementation.R
import com.satyaprakash.xml_implementation.data.model.ListItems

// MainRepository.kt
class MainRepository {
    fun getImages(): List<Int> {
        return listOf(R.drawable.image, R.drawable.image, R.drawable.image, R.drawable.image, R.drawable.image )
    }

    fun getItems(): List<ListItems> {
        return listOf(
            ListItems(R.drawable.image, "Apple", "subTitle"),
            ListItems(R.drawable.image, "Banana", "subTitle"),
            ListItems(R.drawable.image, "Orange", "subTitle"),
            ListItems(R.drawable.image, "Blueberry", "subTitle"),
            ListItems(R.drawable.image, "Mango", "subTitle"),
            ListItems(R.drawable.image, "Lemon", "subTitle"),
            ListItems(R.drawable.image, "Grapes", "subTitle"),
            ListItems(R.drawable.image, "Kiwi", "subTitle")

        )
    }


}
