package com.example.bugin_erten.data

import com.example.bugin_erten.R
import com.example.bugin_erten.model.Daystext

class Datasource {
    fun loadDaystext(): List<Daystext> {
        return listOf<Daystext>(
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1),
            Daystext(R.string.qarasoz1)
        )
    }
}