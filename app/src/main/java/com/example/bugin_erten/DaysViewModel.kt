package com.example.bugin_erten

import android.app.Application
import android.graphics.Typeface
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao
import com.example.bugin_erten.network.MarsApi
import com.example.bugin_erten.network.MarsProperty
import com.example.bugin_erten.network.QaraSozProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaysViewModel(val database: QaraSozDao,
            application: Application): AndroidViewModel(application) {

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

    private  val _titleFont = MutableLiveData<String>()
    val titleFont: MutableLiveData<String> get() = _titleFont

    private val fonts: Array<String> = arrayOf("sans-serif", "sans-serif-light", "sans-serif-condensed", "sans-serif-black", "sans-serif-thin","sans-serif-medium")
    private var fontIdx = 0
    //Font Style vars
    private val _fontStyle = MutableLiveData<Int>()
    val fontStyle: MutableLiveData<Int> get() = _fontStyle

    private  val _styleTitle = MutableLiveData<String>()
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
//        viewModelScope.launch {
//            database.insert(QaraSoz(qaraSozTitle = "Қара сөз - 18", qaraSozText = "Адам баласына жыртықсыз, кірсіз, сыпайы киініп, һәм ол киімін былғап, былжыратып кимей, таза кимек - дұрыс іс. Ләкин өз дәулетінен артық киінбек, не киімі артық болмаса да, көңіліне қуат тұтып, тым айналдырмақ - кербездің ісі.\n" +
//                    "\n" +
//                    "Кербездің екі түрлі қылығы болады: бірі бет-пішінін, мұртын, мүшесін, жүрісін, қас-қабағын қолдан түзетіп, шынтағын көтеріп, қолын тарақтап әуре болмақ. Біреуі атын, киімін «айран ішерім» деп, солардың арқасында сыпайы, жұғымды жігіт атанбаққа, өзінен ілгерілерге елеулі болып, өзі қатардағының ішін күйдіріп, өзінен кейіншілерге «әттең, дүние-ай, осылардың атындай ат мініп, киіміндей киім кигеннің не арманы бар екен?!» - дейтұғын болмаққа ойланбақ.\n" +
//                    "\n" +
//                    "Мұның бәрі - масқаралық, ақымақтық. Мұны адам бір ойламасын, егерде бір ойласа, қайта адам болмағы - қиын іс. Кербез дегенді осындай кер, кердең немеден безіңдер деген сөзге ұқсатамын. Тегінде, адам баласы адам баласынан ақыл, ғылым, ар, мінез деген нәрселермен озбақ. Онан басқа нәрсеменен оздым ғой демектің бәрі де - ақымақтық."))
//        }
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
        var soz = database.get(1)

        return soz
    }

    fun changeFontFamily() {
        fontIdx = (fontIdx + 1)%fonts.size
        _fontFamily.value = fonts[fontIdx]
        titleFont.value = fontFamily.value?.replace("sans-serif-", "")
        if (titleFont.value == "sans-serif")
            titleFont.value = "normal"
    }

    fun changeFontStyle() {
        fontStyleIdx = (fontStyleIdx + 1)%fontStyles.size
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
                val listResult = MarsApi.retrofitService.getProperties()
              //  _response.value = "Success: ${listResult} Mars properties retrieved"
//                if (listResult.isNotEmpty()) {
//                    _property.value = listResult[0]
//                }
                _property.value = listResult

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }



}