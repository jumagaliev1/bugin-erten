package com.example.bugin_erten

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private val _data = ArrayList<ItemsModel>()
    val data: ArrayList<ItemsModel> get() = _data

    fun itemCount() {
        for (i in 1..45) {
            _data.add(ItemsModel(R.drawable.abay, "Qara soz " + i))
        }
    }
}