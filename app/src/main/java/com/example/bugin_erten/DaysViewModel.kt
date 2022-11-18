package com.example.bugin_erten

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao
import kotlinx.coroutines.launch

class DaysViewModel(val database: QaraSozDao,
            application: Application): AndroidViewModel(application) {

    private var textColor = "#000000"
    private var style = "bold"
    private val _textSize = MutableLiveData<Float>()
    val textSize: MutableLiveData<Float> get() = _textSize
    private val _qaraSoz = MutableLiveData<QaraSoz?>()
    val qaraSoz: MutableLiveData<QaraSoz?> get() = _qaraSoz
    init {
        _textSize.value = 20.0f
//        viewModelScope.launch {
//            database.insert(QaraSoz(qaraSozTitle = "Қара сөз - 18", qaraSozText = "Адам баласына жыртықсыз, кірсіз, сыпайы киініп, һәм ол киімін былғап, былжыратып кимей, таза кимек - дұрыс іс. Ләкин өз дәулетінен артық киінбек, не киімі артық болмаса да, көңіліне қуат тұтып, тым айналдырмақ - кербездің ісі.\n" +
//                    "\n" +
//                    "Кербездің екі түрлі қылығы болады: бірі бет-пішінін, мұртын, мүшесін, жүрісін, қас-қабағын қолдан түзетіп, шынтағын көтеріп, қолын тарақтап әуре болмақ. Біреуі атын, киімін «айран ішерім» деп, солардың арқасында сыпайы, жұғымды жігіт атанбаққа, өзінен ілгерілерге елеулі болып, өзі қатардағының ішін күйдіріп, өзінен кейіншілерге «әттең, дүние-ай, осылардың атындай ат мініп, киіміндей киім кигеннің не арманы бар екен?!» - дейтұғын болмаққа ойланбақ.\n" +
//                    "\n" +
//                    "Мұның бәрі - масқаралық, ақымақтық. Мұны адам бір ойламасын, егерде бір ойласа, қайта адам болмағы - қиын іс. Кербез дегенді осындай кер, кердең немеден безіңдер деген сөзге ұқсатамын. Тегінде, адам баласы адам баласынан ақыл, ғылым, ар, мінез деген нәрселермен озбақ. Онан басқа нәрсеменен оздым ғой демектің бәрі де - ақымақтық."))
//        }
        initializeQaraSoz()
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
        println(qaraSoz.value?.qaraSozText)
    }

    private suspend fun getQaraSozFromDatabase(): QaraSoz? {
        var soz = database.get(1)

        return soz
    }


}