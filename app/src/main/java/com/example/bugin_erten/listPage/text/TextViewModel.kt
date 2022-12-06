package com.example.bugin_erten.listPage.text


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao

class TextViewModel(
    private val sozKey: Long = 0L,
    dataSource: QaraSozDao
) : ViewModel() {

    val database = dataSource
    val soz: LiveData<QaraSoz> = database.getSozWithId(sozKey)

}