package com.seif.fwdshoestore.presentation.add_shoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seif.fwdshoestore.databinding.FragmentAddShoeBinding
import com.seif.fwdshoestore.domain.model.Shoe

class AddShoeFragment : Fragment() {
    lateinit var binding: FragmentAddShoeBinding
    private lateinit var shoe: Shoe
    private val addShoeViewModel: AddShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddShoeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAddShoe.setOnClickListener {
            getShoeInfo()
            addShoeViewModel.addShoe(shoe)
            findNavController().navigateUp()
        }
    }

    private fun getShoeInfo() {
        shoe = Shoe(
            name = binding.etName.text.toString(),
            company = binding.etCompany.text.toString(),
            size = binding.etSize.text.toString().toDouble(),
            price = binding.etPrice.text.toString()
        )
    }

}