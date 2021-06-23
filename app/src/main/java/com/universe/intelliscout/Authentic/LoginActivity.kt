package com.universe.intelliscout.Authentic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.universe.intelliscout.R

class LoginActivity : AppCompatActivity() {

    lateinit var tabLayout : TabLayout
    lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Iniciar Sessão"))
        tabLayout.addTab(tabLayout.newTab().setText("Registar"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginAdapter(supportFragmentManager, this, tabLayout.tabCount)
        viewPager.adapter = adapter


        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.translationY = 300F
        tabLayout.alpha = 0F

        tabLayout.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(100).start()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
}