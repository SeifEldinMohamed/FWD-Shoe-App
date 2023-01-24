package com.seif.fwdshoestore.presentation.shoe_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.seif.fwdshoestore.R
import com.seif.fwdshoestore.databinding.FragmentShoeListBinding
import com.seif.fwdshoestore.domain.model.Shoe
import com.seif.fwdshoestore.presentation.add_shoe.AddShoeViewModel
import com.seif.fwdshoestore.utils.hide
import timber.log.Timber

class ShoeListFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentShoeListBinding
    //    private val shoeAdapter: ShoeAdapter by lazy { ShoeAdapter() }
    private val addShoeViewModel: AddShoeViewModel by activityViewModels()
    private var shoeList: ArrayList<Shoe> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolBar()
        binding.fabAddShoe.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_addShoeFragment)
        }
        observeShoe()
//        binding.rvShoeList.adapter = shoeAdapter
    }

    private fun setUpToolBar() {
        val activity: AppCompatActivity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
    }

    private fun observeShoe() {
        addShoeViewModel.shoe.observe(viewLifecycleOwner) { shoe ->
            Timber.d("onViewCreated: $shoe")
            shoe?.let {
                shoeList.add(it)
                shoeList.forEach { item ->
                    addViews(item)
                }
//                shoeAdapter.addShoeList(shoeList)
            }
            binding.tvNoProducts.hide()
        }
    }

    private fun addViews(item: Shoe) {
        val tvShoeName = TextView(this.context)
        tvShoeName.text = item.name
        val tvCompany = TextView(this.context)
        tvCompany.text = item.company
        val tvSize = TextView(this.context)
        tvSize.text = item.size.toString()
        val tvPrice = TextView(this.context)
        tvPrice.text = item.price
        with(binding.llShoe) {
            addView(tvShoeName)
            addView(tvCompany)
            addView(tvSize)
            addView(tvPrice)
        }
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_logout -> {
                findNavController().navigateUp()
                true
            }
            else -> false
        }
    }
}