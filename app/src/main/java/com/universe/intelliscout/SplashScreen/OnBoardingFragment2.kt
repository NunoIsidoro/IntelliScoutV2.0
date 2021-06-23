package ipca.example.projetosemestre.ui.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.universe.intelliscout.Authentic.LoginActivity
import com.universe.intelliscout.R

class OnBoardingFragment2 : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root : ViewGroup = inflater.inflate(R.layout.fragment_on_boarding2, container, false) as ViewGroup

        val textViewSkipBoarding2 : TextView = root.findViewById(R.id.textViewSkipBoarding2)

        textViewSkipBoarding2.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        return root
    }

}