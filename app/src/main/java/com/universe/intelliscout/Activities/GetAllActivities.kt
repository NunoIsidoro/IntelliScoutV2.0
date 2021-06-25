package com.universe.intelliscout.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.universe.intelliscout.Models.Activity
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetAllActivities : AppCompatActivity() {

    var activities: MutableList<Activity> = ArrayList()
    lateinit var activitiesAdapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activities_list_activity)

        val listView = findViewById<ListView>(R.id.listViewActivities)

        activitiesAdapter = ActivitiesAdapter()
        listView.adapter = activitiesAdapter

        GlobalScope.launch(Dispatchers.IO) {
            ActivitiesRequest.getAllActivities {
                activities.addAll(it)
            }
            GlobalScope.launch(Dispatchers.Main) {
                activitiesAdapter.notifyDataSetChanged()

            }
        }
    }

    inner class ActivitiesAdapter : BaseAdapter() {
        override fun getCount(): Int {

            //tamanho do array equipamneto
            return activities.size
        }

        override fun getItem(position: Int): Any {

            //posicao do array equipamneto
            return activities[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.activities_row, parent, false)

            //declaração das textViews e botões
            val textViewActivitieName = rowView.findViewById<TextView>(R.id.textViewActivityName)
            val textViewActivitieLocal = rowView.findViewById<TextView>(R.id.textViewActivityLocal)
            val textViewActivitieDate = rowView.findViewById<TextView>(R.id.textViewActivityDate)

            //enviar os dados da classe atividades para as textViews
            textViewActivitieName.text = activities[position].name
            textViewActivitieLocal.text = activities[position].idLocal.toString()
            textViewActivitieDate.text = activities[position].dtStart


            // ao ser pressionado o botão irá ser aberta uma página para editar os equipamentos
<<<<<<< Updated upstream
           // rowView.setOnClickListener {
            //    openEditActivities(activities[position].id!!)
            //    finish()
            //}
=======
            //rowView.setOnClickListener {
            //    openEditActivities(activities[position].id!!)
            //    finish()
           // }
>>>>>>> Stashed changes


            return rowView
        }

    }

    // function used to open the editEquipmentActivity
    private fun openEditActivities(id: Int, name: String, idLocal: Int, dtStart: String) {
        val intent = Intent(this, EditActivities :: class.java)
        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("idlocal", idLocal)
        intent.putExtra("dtStart", dtStart)
        startActivity(intent)
    }
}

