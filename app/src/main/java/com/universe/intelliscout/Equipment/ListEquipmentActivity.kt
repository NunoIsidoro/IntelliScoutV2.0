package com.universe.intelliscout.Equipment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.universe.intelliscout.Models.Equipment
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListEquipmentActivity : AppCompatActivity() {

    var equipments: MutableList<Equipment> = ArrayList()
    var equipmentAdapter: EquipmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.equipment_list_activity)

        val listViewEquipment = findViewById<ListView>(R.id.listViewEquipment)

        equipmentAdapter = EquipmentAdapter()
        listViewEquipment.adapter = equipmentAdapter

        GlobalScope.launch(Dispatchers.IO) {

            EquipmentRequest.getAllEquipment {

                equipments.addAll(it)

            }

            GlobalScope.launch(Dispatchers.Main) {
                equipmentAdapter?.notifyDataSetChanged()

            }
        }
    }

    inner class EquipmentAdapter : BaseAdapter() {
        override fun getCount(): Int {

            //tamanho do array equipamneto
            return equipments.size
        }

        override fun getItem(position: Int): Any {

            //posicao do array equipamneto
            return equipments[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.equipment_row, parent, false)

            //declaração das textViews e botões
            val textViewEquipmentName = rowView.findViewById<TextView>(R.id.textViewEquipmentName)
            val textViewQuantity = rowView.findViewById<TextView>(R.id.textViewQuantity)

            //enviar os dados da classe equipamento para as textViews
            textViewEquipmentName.text = equipments[position].name
            textViewQuantity.text = equipments[position].quantity.toString()

            return rowView
        }
    }
}