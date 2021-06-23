package ipca.example.projetosemestre.ui.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.common.SignInButton
import com.universe.intelliscout.Authentic.LoginRequest
import com.universe.intelliscout.HomeActivity
import com.universe.intelliscout.Models.Login
import com.universe.intelliscout.Profile.ProfileRequest
import com.universe.intelliscout.R
import ipca.example.projetosemestre.Models.ScoutUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterTabFragment : Fragment() {

    lateinit var editTextEmail : EditText
    lateinit var editTextPassword : EditText
    lateinit var editTextConfirmPassword : EditText
    lateinit var buttonRegister : Button
    lateinit var signIn : SignInButton
    lateinit var root : ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        root = inflater.inflate(R.layout.register_fragment, container, false) as ViewGroup

        editTextEmail = root.findViewById(R.id.editTextEmail)
        editTextPassword = root.findViewById(R.id.editTextPassword)
        editTextConfirmPassword = root.findViewById(R.id.editTextConfirmPassword)
        buttonRegister = root.findViewById(R.id.buttonRegister)
        signIn = root.findViewById(R.id.signIn)

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonRegister.setOnClickListener {

            if (editTextEmail.text.isEmpty())
            {
                editTextEmail.setError("Por favor, preencha este campo!")
            }
            else if (editTextPassword.text.isEmpty())
            {
                editTextPassword.setError("Por favor, preencha este campo!")
            }
            else if (editTextConfirmPassword.text.isEmpty())
            {
                editTextConfirmPassword.setError("Por favor, preencha este campo!")
            }
            else if (!(editTextConfirmPassword.text.toString().equals(editTextPassword.text.toString()))) {

                editTextConfirmPassword.setError("Esta palavra-passe não coincide com a que inseriu!")
            } else if (!editTextEmail.text.toString().contains("@")) {

                editTextEmail.setError("O email tem de conter '@'")

            } else {

                val register = Login(editTextEmail.text.toString(), editTextPassword.text.toString(),
                    null, 3)

                var stopRequest = false
                val loginList : MutableList<Login> = ArrayList()
                GlobalScope.launch(Dispatchers.IO) {
                    LoginRequest.getAllLogin() {
                        loginList.addAll(it)
                    }

                    for (index in loginList) {
                        println("${index.gmail} = ${register.gmail}")
                        if (index.gmail == register.gmail) {
                            println("passou")
                            stopRequest = true
                        }
                    }

                    println(stopRequest)

                    if (stopRequest) {
                        GlobalScope.launch(Dispatchers.Main) {
                            Toast.makeText(context, "Conta gmail já está em uso!", Toast.LENGTH_LONG).show()
                        }
                    } else {

                        val registerResult = LoginRequest.registerRequest(register)

                        if (registerResult) {

                            val newUser = ScoutUser(register.id, "Utilizador", null, null, null, null, 0, null, null, null
                            ,register.id, 1, 1)

                            ProfileRequest.addScoutUser(newUser)

                            GlobalScope.launch(Dispatchers.Main) {

                                Toast.makeText(context, "Registado com sucesso!", Toast.LENGTH_SHORT).show()

                                val intent = Intent(context, HomeActivity::class.java)
                                intent.putExtra("idScout", register.id)
                                intent.putExtra("gmail", register.gmail)
                                startActivity(intent)

                            }

                        } else {

                            GlobalScope.launch(Dispatchers.Main) {

                                Toast.makeText(context, "Problema ao registar, por favor tente novamente!", Toast.LENGTH_LONG).show()

                            }
                        }
                    }
                }
            }
        }
    }

    companion object{

        const val RC_SIGN_IN = 123

    }
}