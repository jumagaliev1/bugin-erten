package com.example.bugin_erten.listPage

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
import com.example.bugin_erten.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*
import timber.log.Timber

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel
    private var adapter: RecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("ListFragment onCreateView called")
        val application = requireNotNull(this.activity).application
        val dataSource = QaraSozDatabase.getInstance(application).qaraSozDao
        val viewModelFactory = ListViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            )[ListViewModel::class.java]

        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)


        adapter = RecyclerAdapter { model ->
            this@ListFragment.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToTextFragment(model.sozId))
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.qaraSozList.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }

    }
}