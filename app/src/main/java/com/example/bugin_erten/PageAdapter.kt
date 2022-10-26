package com.example.bugin_erten

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bugin_erten.DetailFragment.Companion.ARG_POSITION


class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 3
    }


    override fun getItem(position: Int): Fragment {
        return DetailFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
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