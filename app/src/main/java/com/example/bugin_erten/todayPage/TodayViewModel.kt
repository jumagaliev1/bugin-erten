package com.example.bugin_erten.todayPage

import android.app.Application
import android.graphics.Typeface
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.network.QaraSozProperty
import com.example.bugin_erten.repository.QaraSozRepository
import kotlinx.coroutines.launch

class TodayViewModel(
    val repository: QaraSozRepository,
    application: Application
) : AndroidViewModel(application) {

    private var textColor = "#000000"
    private var style = "bold"

    private val _color = MutableLiveData<String>()
    val color: MutableLiveData<String> get() = _color

    private val _textSize = MutableLiveData<Float>()
    val textSize: MutableLiveData<Float> get() = _textSize
    private val _qaraSoz = MutableLiveData<QaraSoz?>()
    val qaraSoz: MutableLiveData<QaraSoz?> get() = _qaraSoz

    //Font Family vars
    private val _fontFamily = MutableLiveData<String>()
    val fontFamily: MutableLiveData<String> get() = _fontFamily

    private val _titleFont = MutableLiveData<String>()
    val titleFont: MutableLiveData<String> get() = _titleFont

    private val fonts: Array<String> = arrayOf(
        "sans-serif",
        "sans-serif-light",
        "sans-serif-condensed",
        "sans-serif-black",
        "sans-serif-thin",
        "sans-serif-medium"
    )
    private var fontIdx = 0

    //Font Style vars
    private val _fontStyle = MutableLiveData<Int>()
    val fontStyle: MutableLiveData<Int> get() = _fontStyle

    private val _styleTitle = MutableLiveData<String>()
    val styleTitle: MutableLiveData<String> get() = _styleTitle

    private val fontStyles: Array<Int> = arrayOf(Typeface.NORMAL, Typeface.ITALIC)
    private var fontStyleIdx = 0

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _property = MutableLiveData<QaraSozProperty>()

    val property: LiveData<QaraSozProperty>
        get() = _property

    init {
        _textSize.value = 20.0f
        _fontFamily.value = fonts[fontIdx]
        _fontStyle.value = fontStyles[fontStyleIdx]
        _titleFont.value = "NORMAL"
        _styleTitle.value = "NORMAL"
        initializeQaraSoz()
        _color.value = "#FFFFFFFF"
        getProperties()

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

    private fun initializeQaraSoz() {
        viewModelScope.launch {
            _qaraSoz.value = getQaraSozFromDatabase()
        }
    }

    private suspend fun getQaraSozFromDatabase(): QaraSoz? {
        var soz = repository.getById(1)

        return soz
    }

    fun changeFontFamily() {
        fontIdx = (fontIdx + 1) % fonts.size
        _fontFamily.value = fonts[fontIdx]
        titleFont.value = fontFamily.value?.replace("sans-serif-", "")
        if (titleFont.value == "sans-serif")
            titleFont.value = "normal"
    }

    fun changeFontStyle() {
        fontStyleIdx = (fontStyleIdx + 1) % fontStyles.size
        _fontStyle.value = fontStyles[fontStyleIdx]
        if (_fontStyle.value == Typeface.NORMAL)
            _styleTitle.value = "NORMAL"
        else
            _styleTitle.value = "ITALIC"
    }

    fun changeColor2White() {
        _color.value = "#FFFFFFFF"
    }

    fun changeColor2Gray() {
        _color.value = "#C9DDCE9B"
    }

    private fun getProperties() {
        viewModelScope.launch {
            try {
                val listResult = repository.getFromNetwork()
                _property.value = listResult

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }


}