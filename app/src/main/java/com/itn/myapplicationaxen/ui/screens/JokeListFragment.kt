package com.itn.myapplicationaxen.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.itn.myapplicationaxen.ui.viewmodels.JokeCategoriesViewModel
import com.itn.myapplicationaxen.core.utils.observe
import com.itn.myapplicationaxen.databinding.FragmentJokeListBinding
import com.itn.myapplicationaxen.ui.components.ListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokeListFragment : Fragment() {

    private val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")

    private var _binding: FragmentJokeListBinding? = null
    private val binding get() = _binding!!
    var onItemSelected: ((String) -> Unit)? = null

    private val categoriesViewModel: JokeCategoriesViewModel by viewModels()
    private lateinit var itemsAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeListBinding.inflate(layoutInflater)
        itemsAdapter = ListAdapter(items) { selectedItem ->
            onItemSelected?.invoke(selectedItem)
        }
        setUpObservers()
        return binding.root
    }

    private fun setUpObservers() {
        categoriesViewModel.state.observe(viewLifecycleOwner) {state ->
            when(state.state) {
                JokeCategoriesViewModel.State.ListLoaded -> {
                    itemsAdapter.items = state.itemList
                    itemsAdapter.notifyDataSetChanged()
                }

                is JokeCategoriesViewModel.State.Error -> {}
                JokeCategoriesViewModel.State.Idle -> {}
                JokeCategoriesViewModel.State.Loading -> {}
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.jokeRV.layoutManager = LinearLayoutManager(requireContext())
        binding.jokeRV.adapter = itemsAdapter
        super.onViewCreated(view, savedInstanceState)
    }

}