package com.example.bugin_erten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.bugin_erten.databinding.FragmentDaysBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_days.*
import kotlinx.android.synthetic.main.fragment_days.view.*
import timber.log.Timber

class DaysFragment : Fragment() {

    private var _binding: FragmentDaysBinding? = null
    private val binding: FragmentDaysBinding get() = _binding!!

    private val viewModel: DaysViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        Timber.i("DaysFragment onCreateView called")
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_days, container, false)
        val btn_increase = binding.root.btn_increase as Button
        val btn_decrease = binding.root.btn_decrease as Button
        btn_increase.setOnClickListener {
            viewModel.increaseSize()
            showChangedSizeDialog()
        }
        btn_decrease.setOnClickListener {
            viewModel.decreaseSize()
            showChangedSizeDialog()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.daysViewModel = viewModel
        binding.newSize = viewModel.textSize.value
        binding.lifecycleOwner = viewLifecycleOwner

//        viewModel.textSize.observe(viewLifecycleOwner) { newSize ->
//                binding.root.main_words.textSize = newSize
//        }
        Timber.i("DaysFragment onViewCreated called")
//        binding.viewPager.adapter = PageAdapter(childFragmentManager)
//        binding.tabLayout.setupWithViewPager(viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("DaysFragment onDestroyView called")
        _binding = null
    }

    private fun showChangedSizeDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Information!")
            .setMessage("Your new Size ${viewModel.textSize.value}",)
            .setCancelable(true)
            .show()

    }
    fun changeSize() {
        viewModel.changeSize()
        showChangedSizeDialog()
    }
}