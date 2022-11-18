package com.example.bugin_erten

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bugin_erten.database.QaraSozDao

class DaysViewModel(val database: QaraSozDao,
            application: Application): AndroidViewModel(application) {

    private var textColor = "#000000"
    private var style = "bold"
    private val _textSize = MutableLiveData<Float>()
    val textSize: MutableLiveData<Float> get() = _textSize

    init {
        _textSize.value = 20.0f
    }
    fun changeSize() {
        _textSize.value = 25.0f
    }
    fun increaseSize() {
        if (_textSize.value!! == 50.0f) {
            return
        }

        _textSize.value = _textSize.value?.plus(1)
    }

    fun decreaseSize() {
        if (_textSize.value!! == 20f) {
            return
        }
        _textSize.value = _textSize.value?.minus(1)
    }
}