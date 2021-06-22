package com.universe.intelliscout.SplashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.universe.intelliscout.R
import ipca.example.projetosemestre.ui.SplashScreen.OnBoardingFragment1
import ipca.example.projetosemestre.ui.SplashScreen.OnBoardingFragment2
import ipca.example.projetosemestre.ui.SplashScreen.OnBoardingFragment3

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val imageViewBackground : ImageView = findViewById(R.id.imageViewBackground)
        val imageViewLogo : ImageView = findViewById(R.id.imageViewLogo)
        val textViewIntelliScout : TextView = findViewById(R.id.textViewIntelliScout)
        val animationsplash : LottieAnimationView = findViewById(R.id.animationsplash)
        val viewPager : ViewPager = findViewById(R.id.viewPager)

        imageViewBackground.animate().translationY(-4000F).setDuration(1000).setStartDelay(2500)
        imageViewLogo.animate().translationY(-1000F).setDuration(1000).setStartDelay(2500)
        textViewIntelliScout.animate().translationY(2000F).setDuration(1000).setStartDelay(2500)
        animationsplash.animate().translationX(800F).setDuration(2500)
        //animationsplash.loop(false)

        val adapter = ScreenPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

    }

    private class ScreenPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {

            when(position){
                0-> return OnBoardingFragment1()
                1-> return OnBoardingFragment2()
                2-> return OnBoardingFragment3()
                else-> return OnBoardingFragment1()
            }
        }

    }

}