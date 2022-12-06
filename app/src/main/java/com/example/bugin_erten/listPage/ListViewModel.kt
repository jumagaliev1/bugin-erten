package com.example.bugin_erten.listPage

import android.app.Application
import androidx.lifecycle.*
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao

class ListViewModel(
    val database: QaraSozDao,
    application: Application
) : AndroidViewModel(application) {
    val qaraSozList: LiveData<List<QaraSoz?>> = database.getAll()
}