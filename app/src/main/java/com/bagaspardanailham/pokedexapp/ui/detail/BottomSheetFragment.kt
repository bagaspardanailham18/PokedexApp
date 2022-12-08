package com.bagaspardanailham.pokedexapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bagaspardanailham.pokedexapp.R
import com.bagaspardanailham.pokedexapp.data.local.model.MyPokeCollectionEntity
import com.bagaspardanailham.pokedexapp.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BottomSheetFragment(private val name: String?, private val pokeImg: String?, private val dominantColor: Int?) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding

    private val viewModel: PokeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnSave?.setOnClickListener {
            val nickname = binding?.edtNickname?.text.toString().trim()
            if (nickname.isNotEmpty()) {
                lifecycleScope.launch {
                    val data = MyPokeCollectionEntity(
                        name = name, nickname = nickname, imgUrl = pokeImg, dominantColor = dominantColor
                    )
                    viewModel.insertPokeToCollection(data)
                }
                Toast.makeText(requireActivity(), "Added to collection", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                binding?.layoutEdtNickname?.error = "Nickname is required"
            }
        }

        binding?.btnCancelDialog?.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}