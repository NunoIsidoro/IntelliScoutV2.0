package ipca.example.projetosemestre.ui.Login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.common.SignInButton
import com.universe.intelliscout.Authentic.LoginRequest
import com.universe.intelliscout.HomeActivity
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginTabFragment : Fragment() {

    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var textViewForgetPass: TextView
    lateinit var signIn: SignInButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.login_fragment, container, false) as ViewGroup

        editTextEmail = root.findViewById(R.id.editTextEmail)
        editTextPassword = root.findViewById(R.id.editTextPassword)
        buttonLogin = root.findViewById(R.id.buttonLogin)
        textViewForgetPass = root.findViewById(R.id.textViewForgetPass)
        signIn = root.findViewById(R.id.signIn)

        editTextEmail.translationY = 800F
        editTextPassword.translationY = 800F
        buttonLogin.translationY = 800F
        textViewForgetPass.translationY = 800F

        editTextEmail.alpha = 0F
        editTextPassword.alpha = 0F
        buttonLogin.alpha = 0F
        textViewForgetPass.alpha = 0F

        editTextEmail.animate().translationY(0F).alpha(1F).setDuration(800).setStartDelay(300)
            .start()
        editTextPassword.animate().translationY(0F).alpha(1F).setDuration(800).setStartDelay(500)
            .start()
        buttonLogin.animate().translationY(0F).alpha(1F).setDuration(800).setStartDelay(500).start()
        textViewForgetPass.animate().translationY(0F).alpha(1F).setDuration(800).setStartDelay(700)
            .start()

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin.setOnClickListener {

            if (editTextEmail.text.isEmpty()) {

                editTextEmail.error = "Por favor, preenche este campo!"

            } else if (editTextPassword.text.isEmpty()) {

                editTextPassword.error = "Por favor, preenche este campo!"

            } else {


                GlobalScope.launch(Dispatchers.IO) {

                    val resultLogin =
                        LoginRequest.getLoginByGmail(editTextEmail.text.toString())


                    if (resultLogin.password == editTextPassword.text.toString()) {

                        GlobalScope.launch(Dispatchers.Main) {

                            Toast.makeText(context, "Bem-vindo de volta!", Toast.LENGTH_SHORT)
                                .show()

                            val intent = Intent(context, HomeActivity::class.java)
                            intent.putExtra("idScout", resultLogin.id)
                            intent.putExtra("gmail", editTextEmail.text.toString())
                            startActivity(intent)

                        }

                    } else {

                        GlobalScope.launch(Dispatchers.Main) {

                            Toast.makeText(
                                context, "Login ou password incorretos!",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    }
                }
            }
        }
    }


    companion object {

        const val RC_SIGN_IN = 123

    }
}