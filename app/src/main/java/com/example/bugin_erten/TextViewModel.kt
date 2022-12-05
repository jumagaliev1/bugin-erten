package com.example.bugin_erten

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao

class TextViewModel(
    private val sozKey: Long = 0L,
    dataSource: QaraSozDao) : ViewModel() {

    val database = dataSource
    val soz: LiveData<QaraSoz> = database.getSozWithId(sozKey)

}