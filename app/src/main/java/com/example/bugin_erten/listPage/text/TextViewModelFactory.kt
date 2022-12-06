package com.example.bugin_erten.listPage.text

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bugin_erten.database.QaraSozDao

class TextViewModelFactory(
    private val sozKey: Long,
    private val dataSource: QaraSozDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TextViewModel::class.java)) {
            return TextViewModel(sozKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}