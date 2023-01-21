package com.seif.fwdshoestore.presentation.view_pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.seif.fwdshoestore.R
import com.seif.fwdshoestore.databinding.FragmentViewPagerBinding
import com.seif.fwdshoestore.utils.hide
import com.seif.fwdshoestore.utils.show

class ViewPagerFragment : Fragment() {
    lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewPager()
        binding.btnNext.setOnClickListener {
            handleNextButtonStates()
        }
        binding.btnPrev.setOnClickListener {
            handlePreviousButtonStates()
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handleOnPageSelectedStates(position)
            }
        })
    }

    private fun initializeViewPager() {
        viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(requireContext() as FragmentActivity)
        binding.dotsIndicator.attachTo(viewPager)
    }

    private fun handleNextButtonStates() {
        when (viewPager.currentItem) {
            0 -> viewPager.currentItem = 1
            1 -> findNavController().navigate(R.id.action_viewPagerFragment_to_shoeListFragment)

        }
    }

    private fun handlePreviousButtonStates() {
        when (viewPager.currentItem) {
            1 -> viewPager.currentItem = 0
        }
    }

    private fun handleOnPageSelectedStates(position: Int) {
        when (position) {
            0 -> {
                binding.btnNext.text = getString(R.string.next)
                binding.btnNext.show()
                binding.btnPrev.hide()
            }
            1 -> {
                binding.btnNext.text = getString(R.string.finish)
                binding.btnNext.show()
                binding.btnPrev.show()
            }
        }
    }
}