package com.example.bugin_erten

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return tomorrow()
            }
            1 -> {
                return today()
            }
            2 -> {
                return yesterday()
            }
            else -> {
                return today()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tomorrow"
            }
            1 -> {
                return "Today"
            }
            2 -> {
                return "Yesterday"
            }
        }
        return super.getPageTitle(position)
    }
}