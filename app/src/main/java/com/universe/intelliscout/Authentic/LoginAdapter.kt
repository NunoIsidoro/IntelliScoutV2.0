package com.universe.intelliscout.Authentic

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ipca.example.projetosemestre.ui.Login.LoginTabFragment
import ipca.example.projetosemestre.ui.Login.RegisterTabFragment

class LoginAdapter(fm: FragmentManager, private val context: Context, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                return LoginTabFragment()

            }

            1 -> {

                return RegisterTabFragment()

            }

            else -> {

                return LoginTabFragment()

            }

        }

    }
}