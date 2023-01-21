package com.seif.fwdshoestore.presentation.view_pager

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.seif.fwdshoestore.presentation.onboarding_1.OnBoarding1Fragment
import com.seif.fwdshoestore.presentation.onboarding_2.OnBoarding2Fragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnBoarding1Fragment()
            1 -> OnBoarding2Fragment()
            else -> { throw Resources.NotFoundException("Position Not Found") }
        }
    }
}