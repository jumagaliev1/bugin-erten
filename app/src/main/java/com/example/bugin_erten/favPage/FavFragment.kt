package com.example.bugin_erten.favPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bugin_erten.RecyclerAdapter
import com.example.bugin_erten.database.QaraSozDatabase
import com.example.bugin_erten.databinding.FragmentFavBinding
import com.example.bugin_erten.listPage.ListFragmentDirections
import timber.log.Timber

class FavFragment : Fragment() {
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavViewModel
    private var adapter: RecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("FavFragment onCreateView called")
        val application = requireNotNull(this.activity).application
        val dataSource = QaraSozDatabase.getInstance(application).qaraSozDao
        val viewModelFactory = FavViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            )[FavViewModel::class.java]

        _binding = FragmentFavBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)


        adapter = RecyclerAdapter { model ->
            this@FavFragment.findNavController()
                .navigate(FavFragmentDirections.actionFavFragmentToTextFragment(model.sozId))
        }
        binding.recyclerView2.adapter = adapter
        binding.recyclerView2.layoutManager = LinearLayoutManager(activity)

        viewModel.qaraSozList.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }

    }
}