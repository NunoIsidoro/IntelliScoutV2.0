package com.universe.intelliscout.Profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.universe.intelliscout.Authentic.LoginRequest
import com.universe.intelliscout.R
import ipca.example.projetosemestre.Models.ScoutUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileGetAllActivity : AppCompatActivity() {


    var scoutUsers: MutableList<ScoutUser> = ArrayList()
    lateinit var scoutUserAdapter: ScoutUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_list_activity)

        val listView = findViewById<ListView>(R.id.listViewProfile)

        scoutUserAdapter = ScoutUserAdapter()
        listView.adapter = scoutUserAdapter

        GlobalScope.launch(Dispatchers.IO) {
            ProfileRequest.getAllScoutUser {
                scoutUsers.addAll(it)
            }
            GlobalScope.launch(Dispatchers.Main) {
                scoutUserAdapter.notifyDataSetChanged()

            }
        }
    }

    inner class ScoutUserAdapter : BaseAdapter() {
        override fun getCount(): Int {

            //tamanho do array equipamneto
            return scoutUsers.size
        }

        override fun getItem(position: Int): Any {

            //posicao do array equipamneto
            return scoutUsers[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.profile_row, parent, false)

            //declaração das textViews e botões
            val textViewProfileName = rowView.findViewById<TextView>(R.id.textViewName)
            val textViewProfileEmail = rowView.findViewById<TextView>(R.id.textViewEmail)
            val idLoguin: Int = scoutUsers[position].idScoutLogin!!

            GlobalScope.launch(Dispatchers.IO) {
                var login = LoginRequest.getLoginById(idLoguin)

                GlobalScope.launch(Dispatchers.Main) {
                    //enviar os dados da classe equipamento para as textViews
                    textViewProfileName.text = scoutUsers[position].name
                    textViewProfileEmail.text = login.gmail

                }

            }

            // ao ser pressionado o botão irá ser aberta uma página para editar os equipamentos
            rowView.setOnClickListener {
                openEditScoutUser(scoutUsers[position].id!!)
                finish()
            }


            return rowView
        }

    }

    // function used to open the editEquipmentActivity
    private fun openEditScoutUser(id: Int) {
        val intent = Intent(this, ProfileGetAllActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
