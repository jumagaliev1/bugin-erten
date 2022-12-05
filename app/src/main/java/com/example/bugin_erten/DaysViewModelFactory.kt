package com.example.bugin_erten

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bugin_erten.database.QaraSozDao
import com.example.bugin_erten.repository.QaraSozRepository


class DaysViewModelFactory (
    private val dataSource: QaraSozRepository,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DaysViewModel::class.java)) {
                return DaysViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}