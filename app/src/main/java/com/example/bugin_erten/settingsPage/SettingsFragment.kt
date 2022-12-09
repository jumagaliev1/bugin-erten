package com.example.bugin_erten.settingsPage

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil.setContentView
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.bugin_erten.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val darkModeString = getString(R.string.dark_mode)

        if (key == darkModeString) {

            val pref = sharedPreferences?.getString(key, "1")

            when (pref?.toInt()) {
                1 ->{ AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    )
                }

                2 -> AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                3 -> AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                4 -> AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    )
            }
        }

    }

}
