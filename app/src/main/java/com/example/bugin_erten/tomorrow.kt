package com.example.bugin_erten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import timber.log.Timber


class tomorrow : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("tomorrow onCreateView called")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tomorrow, container, false)
    }

}