package com.example.bugin_erten.listPage.text


import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao
import kotlinx.coroutines.launch
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import com.example.bugin_erten.R

class TextViewModel(
    private val sozKey: Long = 0L,
    dataSource: QaraSozDao
) : ViewModel() {

    val database = dataSource
    val soz: LiveData<QaraSoz> = database.getSozWithId(sozKey)


    fun changeFav() {
        var fav: Int
        if (soz.value?.qaraSozFav == 1)
            fav = 0
        else
            fav = 1
        viewModelScope.launch {
            database.update(QaraSoz(soz.value!!.sozId, soz.value!!.qaraSozTitle, soz.value!!.qaraSozText, fav))
        }
    }
}