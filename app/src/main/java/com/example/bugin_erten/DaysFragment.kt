package com.example.bugin_erten

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings.TextSize
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.bugin_erten.databinding.FragmentDaysBinding
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnIncrease.setOnClickListener {
            viewModel.increaseSize()
           // showChangedSizeDialog()
        }
        binding.btnDecrease.setOnClickListener {
            viewModel.decreaseSize()
           // showChangedSizeDialog()
        }

        binding.daysViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.textSize.observe(viewLifecycleOwner) { newSize ->
            binding.mainWords.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize)
        }

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

//    private fun showChangedSizeDialog() {
//        MaterialAlertDialogBuilder(requireContext())
//            .setTitle("Information!")
//            .setMessage("Your new Size ${viewModel.textSize.value}",)
//            .setCancelable(true)
//            .show()
//
//    }
//    fun changeSize() {
//        viewModel.changeSize()
//        showChangedSizeDialog()
//    }
}