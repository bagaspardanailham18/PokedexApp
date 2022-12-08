package com.bagaspardanailham.pokedexapp.ui.mypokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bagaspardanailham.pokedexapp.databinding.FragmentMypokemonBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPokemonFragment : Fragment() {

    private var _binding: FragmentMypokemonBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyPokemonViewModel by viewModels()
    private lateinit var adapter: MyPokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyPokemonAdapter()

        setPokemonListData()
    }

    private fun setPokemonListData() {
        lifecycleScope.launch {
            binding.progressCircular.visibility = View.VISIBLE
            viewModel.getAllPokemonCollection().observe(viewLifecycleOwner) { result ->
                if (result.isNotEmpty()) {
                    adapter.submitList(result)
                    binding.rvMyPokemon.layoutManager = GridLayoutManager(requireActivity(), 2)
                    binding.rvMyPokemon.adapter = adapter
                    binding.rvMyPokemon.setHasFixedSize(true)
                    binding.progressCircular.visibility = View.GONE
                } else {
                    binding.progressCircular.visibility = View.GONE
                    binding.textNotFound.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}