package com.itn.myapplicationaxen.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itn.myapplicationaxen.ui.viewmodels.RandomJokeViewModel
import com.itn.myapplicationaxen.core.utils.observe
import com.itn.myapplicationaxen.databinding.FragmentJokeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokeFragment : Fragment() {

    private val randomJokeViewModel: RandomJokeViewModel by viewModels()

    companion object {
        private const val ARG_ITEM = "selected_item"

        fun newInstance(item: String): JokeFragment {
            val fragment = JokeFragment()
            val args = Bundle()
            args.putString(ARG_ITEM, item)
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeBinding.inflate(layoutInflater)
        setUpObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = arguments?.getString(ARG_ITEM)
        randomJokeViewModel.getRandomJoke(item.toString())
        binding.detailTextView.text = item
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpObservers(){
        randomJokeViewModel.state.observe(viewLifecycleOwner) {state ->
            when(state.state) {
                is RandomJokeViewModel.State.Error -> {}
                RandomJokeViewModel.State.Idle -> {}
                RandomJokeViewModel.State.JokeLoaded -> {
                    binding.detailTextView.text = state.randomJokeResponse?.value
                }
                RandomJokeViewModel.State.Loading -> {}
            }
        }
    }
}