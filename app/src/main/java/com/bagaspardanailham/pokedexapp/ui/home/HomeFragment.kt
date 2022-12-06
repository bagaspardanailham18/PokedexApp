package com.bagaspardanailham.pokedexapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bagaspardanailham.pokedexapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import com.bagaspardanailham.pokedexapp.data.remote.Result
import com.bagaspardanailham.pokedexapp.data.remote.response.PokemonByIdResponse
import com.bagaspardanailham.pokedexapp.data.remote.response.ResultsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var listPokeAdapter: ListPokeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listPokeAdapter = ListPokeAdapter()

        setPokeData()
    }

    private fun setPokeData() {
        lifecycleScope.launch {
            viewModel.getAllPoke().observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Result.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        val data = response.data.results
                        setPokeRv(data)
                        binding.progressCircular.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Log.d("error", "Error Code: ${response.errorCode}")
                        when (response.errorCode) {
                            400 -> {
                                Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_LONG).show()
                                binding.progressCircular.visibility = View.GONE
                            }
                            404 -> {
                                Toast.makeText(requireActivity(), "Not Found", Toast.LENGTH_LONG).show()
                                binding.progressCircular.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setPokeRv(data: List<ResultsItem?>?) {
        binding.rvPokemon.apply {
            listPokeAdapter.submitList(data)
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = listPokeAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}