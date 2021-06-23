package ipca.example.projetosemestre.ui.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.universe.intelliscout.MainActivity
import com.universe.intelliscout.R

class OnBoardingFragment3 : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root : ViewGroup = inflater.inflate(R.layout.fragment_on_boarding3, container, false) as ViewGroup

        val fabNext : FloatingActionButton = root.findViewById(R.id.fabNext)

        fabNext.setOnClickListener {

            startActivity(Intent(activity, MainActivity::class.java))

        }

        return root
    }

}