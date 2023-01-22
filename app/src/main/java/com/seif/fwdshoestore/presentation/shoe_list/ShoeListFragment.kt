package com.seif.fwdshoestore.presentation.shoe_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.seif.fwdshoestore.R
import com.seif.fwdshoestore.databinding.FragmentShoeListBinding
import com.seif.fwdshoestore.domain.model.Shoe
import com.seif.fwdshoestore.presentation.add_shoe.AddShoeViewModel
import com.seif.fwdshoestore.utils.hide
import timber.log.Timber

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val shoeAdapter: ShoeAdapter by lazy { ShoeAdapter() }
    private val addShoeViewModel: AddShoeViewModel by activityViewModels()
    private var shoeList: ArrayList<Shoe> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddShoe.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_addShoeFragment)
        }
        observeShoe()
        binding.rvShoeList.adapter = shoeAdapter
    }

    private fun observeShoe() {
        addShoeViewModel.shoe.observe(viewLifecycleOwner) { shoe ->
            Timber.d("onViewCreated: $shoe")
            shoe?.let {
                shoeList.add(it)
                shoeAdapter.addShoeList(shoeList)
            }
            binding.tvNoProducts.hide()
        }
    }
}