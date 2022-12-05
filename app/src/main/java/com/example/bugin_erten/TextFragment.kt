package com.example.bugin_erten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bugin_erten.database.QaraSozDatabase
import com.example.bugin_erten.databinding.FragmentTextBinding


class TextFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentTextBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_text, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = TextFragmentArgs.fromBundle(requireArguments())
       Toast.makeText(requireContext(),  arguments.id.toString(), Toast.LENGTH_LONG).show()

        // Create an instance of the ViewModel Factory.
        val dataSource = QaraSozDatabase.getInstance(application).qaraSozDao
        val viewModelFactory = TextViewModelFactory(arguments.id, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val textViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(TextViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.textViewModel = textViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.

//        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
//            if (it == true) { // Observed state is true.
//                this.findNavController().navigate(
//                    SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
//                // Reset state to make sure we only navigate once, even if the device
//                // has a configuration change.
//                sleepDetailViewModel.doneNavigating()
//            }
//        })

        return binding.root
    }
}