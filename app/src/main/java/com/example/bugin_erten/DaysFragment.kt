package com.example.bugin_erten

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.bugin_erten.database.QaraSozDatabase
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

    private lateinit var viewModel: DaysViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        Timber.i("DaysFragment onCreateView called")
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_days, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = QaraSozDatabase.getInstance(application).qaraSozDao
        val viewModelFactory = DaysViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            )[DaysViewModel::class.java]


        binding.root.btn_increase.setOnClickListener {
            viewModel.increaseSize()
            if (viewModel.textSize.value == 50.0f)
                showChangedSizeDialog()
        }
        binding.root.btn_decrease.setOnClickListener {
            viewModel.decreaseSize()
            if (viewModel.textSize.value == 20.0f)
                showChangedSizeDialog()
        }
        binding.root.btn_fontChange.setOnClickListener {
            viewModel.changeFontFamily()
            //binding.mainWords.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC)
            //binding.mainWords.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC)
            binding.mainWords.setTypeface(
                Typeface.create(
                    viewModel.fontFamily.value,
                    viewModel.fontStyle.value!!
                )
            )
            //binding.btnFontChange.setText(viewModel.fontFamily.value)
        }
        binding.root.btn_fontStyle.setOnClickListener {
            viewModel.changeFontStyle()
            binding.mainWords.setTypeface(
                Typeface.create(
                    viewModel.fontFamily.value,
                    viewModel.fontStyle.value!!
                )
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.daysViewModel = viewModel
        binding.newSize = viewModel.textSize.value
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

    private fun showChangedSizeDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Limitation!")
            .setMessage("Your Size ${viewModel.textSize.value} (Max:50 Min:20)")
            .setCancelable(true)
            .show()

    }

    fun changeSize() {
        viewModel.changeSize()
        showChangedSizeDialog()
    }
}