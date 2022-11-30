package com.example.bugin_erten

import android.app.Application
import androidx.lifecycle.*
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao
import kotlinx.coroutines.launch

class ListViewModel(
    val database: QaraSozDao,
    application: Application
) : AndroidViewModel(application) {
    val qaraSozList: LiveData<List<QaraSoz?>> = database.getAll()




}