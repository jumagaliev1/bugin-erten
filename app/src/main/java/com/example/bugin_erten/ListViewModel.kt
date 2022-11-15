package com.example.bugin_erten

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private val _data = MutableLiveData<List<ItemsModel>>(emptyList())
    val data: LiveData<List<ItemsModel>> get() = _data

    init {
        itemCount()
    }

    private fun itemCount() {
        for (i in 1..45) {
            _data.value = _data.value?.plus((ItemsModel(R.drawable.abay, "Qara soz " + i))) as ArrayList<ItemsModel>?
        }
    }

}