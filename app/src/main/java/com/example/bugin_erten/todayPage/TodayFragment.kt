package com.example.bugin_erten.todayPage

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bugin_erten.R
import com.example.bugin_erten.database.QaraSozDatabase
import com.example.bugin_erten.databinding.FragmentDaysBinding
import com.example.bugin_erten.repository.QaraSozRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_days.view.*
import timber.log.Timber

class TodayFragment : Fragment() {

    private var _binding: FragmentDaysBinding? = null
    private val binding: FragmentDaysBinding get() = _binding!!

    private lateinit var viewModel: TodayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        Timber.i("DaysFragment onCreateView called")
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_days, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = QaraSozDatabase.getInstance(application).qaraSozDao
        val repository = QaraSozRepository(dataSource)
        val viewModelFactory = TodayViewModelFactory(repository, application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            )[TodayViewModel::class.java]


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
        binding.root.btn_gray.setOnClickListener {
            viewModel.changeColor2Gray()
        }
        binding.root.btn_white.setOnClickListener {
            viewModel.changeColor2White()
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
        viewModel.color.observe(viewLifecycleOwner) { newColor ->
            binding.frameLayout.setBackgroundColor(Color.parseColor(newColor))
        }
        Timber.i("DaysFragment onViewCreated called")
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