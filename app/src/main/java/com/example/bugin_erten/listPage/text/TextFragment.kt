package com.example.bugin_erten.listPage.text

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bugin_erten.R
import com.example.bugin_erten.database.QaraSozDatabase
import com.example.bugin_erten.databinding.FragmentTextBinding


class TextFragment : Fragment() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentTextBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_text, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = TextFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(requireContext(), arguments.id.toString(), Toast.LENGTH_LONG).show()

        // Create an instance of the ViewModel Factory.
        val dataSource = QaraSozDatabase.getInstance(application).qaraSozDao
        val viewModelFactory = TextViewModelFactory(arguments.id, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val textViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(TextViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.

        binding.textViewModel = textViewModel
        binding.setLifecycleOwner(this)
        binding.btnFav.setOnClickListener {
            var img: Drawable
            if (textViewModel.soz.value?.qaraSozFav == 1) {
                img = binding.btnFav.context.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24)
            } else {
                img = binding.btnFav.context.resources.getDrawable(R.drawable.ic_baseline_favorite_24)
            }

            textViewModel.changeFav()
            binding.btnFav.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null)
        }

        return binding.root
    }
}