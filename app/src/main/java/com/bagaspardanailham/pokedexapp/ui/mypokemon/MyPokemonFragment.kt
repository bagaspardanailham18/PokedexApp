package com.bagaspardanailham.pokedexapp.ui.mypokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity
import com.bagaspardanailham.pokedexapp.databinding.FragmentMypokemonBinding
import com.bagaspardanailham.pokedexapp.databinding.ItemMypokemonModalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
        setAction()
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
                    adapter.submitList(result)
                }
            }
        }
    }

    private fun setAction() {
        adapter.setOnItemClickCallback(object : MyPokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MyPokeCollectionEntity) {
                val modalBottomSheet = MyPokemonModalBottomSheet(data)
                modalBottomSheet.show(childFragmentManager, MyPokemonModalBottomSheet::class.java.simpleName)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

@AndroidEntryPoint
class MyPokemonModalBottomSheet(private val data: MyPokeCollectionEntity): BottomSheetDialogFragment() {

    private var _binding: ItemMypokemonModalBottomSheetBinding? = null
    private val binding get() = _binding

    private val viewModel: MyPokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemMypokemonModalBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnDeleteItem?.setOnClickListener {
            viewModel.deletePokemon(data)
            dismiss()
            return@setOnClickListener
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}