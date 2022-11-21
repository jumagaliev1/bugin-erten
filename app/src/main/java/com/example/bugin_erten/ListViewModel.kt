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
    private val _data = MutableLiveData<List<ItemsModel>>(emptyList())
    val data: LiveData<List<ItemsModel>> get() = _data
    val qaraSozList: LiveData<List<QaraSoz?>> = database.getAll()
    private val _qaraSoz = MutableLiveData<QaraSoz?>()
    val qaraSoz: MutableLiveData<QaraSoz?> get() = _qaraSoz

    init {
        itemCount()
    }

    private fun itemCount() {
        viewModelScope.launch {
            _qaraSoz.value = getQaraSozFromDatabase()
        }
        for (i in 0..3) {
            var title: String = _qaraSoz.value?.qaraSozTitle ?: "bilmim"
            _data.value = _data.value?.plus(
                (ItemsModel(
                    R.drawable.abay,
                    title
                ))
            ) as ArrayList<ItemsModel>?
        }


    }

    private suspend fun getQaraSozFromDatabase(): QaraSoz? {
        var soz = database.get(1)

        return soz
    }
}