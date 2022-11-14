package com.example.bugin_erten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bugin_erten.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*
import timber.log.Timber


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by viewModels()
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("ListFragment onCreateView called")
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        layoutManager = LinearLayoutManager(this)
//
//    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)


//        recyclerView.apply {
//            val data = ArrayList<ItemsModel>()
//            for (i in 1..45) {
//                data.add(ItemsModel(R.drawable.abay, "Qara soz " + i))
//            }


        // set a LinearLayoutManager to handle Android
        // RecyclerView behavior
        viewModel.itemCount()
        // set the custom adapter to the RecyclerView
        adapter = RecyclerAdapter(viewModel.data) { model ->
            this@ListFragment.findNavController().navigate(R.id.action_listFragment_to_textFragment)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }
}
