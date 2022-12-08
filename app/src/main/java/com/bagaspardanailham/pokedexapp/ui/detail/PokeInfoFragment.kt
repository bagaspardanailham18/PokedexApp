package com.bagaspardanailham.pokedexapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagaspardanailham.pokedexapp.R
import com.bagaspardanailham.pokedexapp.data.remote.Result
import com.bagaspardanailham.pokedexapp.data.remote.response.TypesItem
import com.bagaspardanailham.pokedexapp.databinding.FragmentPokeInfoBinding
import com.bagaspardanailham.pokedexapp.databinding.FragmentPokeStatsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeInfoFragment : Fragment() {

    private var _binding: FragmentPokeInfoBinding? = null
    private val binding get() = _binding

    private val pokeDetailViewModel by viewModels<PokeDetailViewModel>()
    private lateinit var adapter: ListPokeTypesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPokeInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListPokeTypesAdapter()
        setContent()
    }

    private fun setContent() {
        val name = arguments?.getString(PokeDetailActivity.EXTRA_NAME).toString()
        pokeDetailViewModel.getPokeByName(name).observe(viewLifecycleOwner) { response ->
            when (response) {
                is Result.Loading -> {
                    binding?.progressCircular?.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    val typesData = response.data.types
                    setTypesRv(typesData)
                    binding?.progressCircular?.visibility = View.GONE
                }
                is Result.Error -> {
                    Log.d("error", "Error Code: ${response.errorCode}")
                    when (response.errorCode) {
                        400 -> {
                            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_LONG).show()
                            binding?.progressCircular?.visibility = View.GONE
                        }
                        404 -> {
                            Toast.makeText(requireActivity(), "Not Found", Toast.LENGTH_LONG).show()
                            binding?.progressCircular?.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun setTypesRv(types: List<TypesItem?>?) {
        adapter.submitList(types)
        binding?.apply {
            rvStats.layoutManager = LinearLayoutManager(requireActivity())
            rvStats.adapter = adapter
            rvStats.setHasFixedSize(true)
        }
    }
}