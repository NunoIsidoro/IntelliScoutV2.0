package com.universe.intelliscout.Equipment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.universe.intelliscout.Models.Equipment
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListEditEquipmentActivity : AppCompatActivity() {

    var equipments : MutableList<Equipment> = ArrayList()
    var equipmentAdapter : EquipmentEditAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.equipment_edit_list_activity)

        val listViewEquipment = findViewById<ListView>(R.id.listViewEquipment)

        equipmentAdapter = EquipmentEditAdapter()
        listViewEquipment.adapter = equipmentAdapter

        GlobalScope.launch(Dispatchers.IO){

            EquipmentRequest.getAllEquipment{

                equipments.addAll(it)

            }

            GlobalScope.launch(Dispatchers.Main) {
                equipmentAdapter?.notifyDataSetChanged()

            }
        }
    }

    inner class EquipmentEditAdapter: BaseAdapter() {
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
            val rowView = layoutInflater.inflate(R.layout.equipment_edit_row, parent, false)

            //declaração das textViews e botões
            val textViewEquipmentName = rowView.findViewById<TextView>(R.id.textViewEquipmentName)
            val textViewQuantityValue = rowView.findViewById<TextView>(R.id.textViewQuantityValue)
            val buttonEditEquipment = rowView.findViewById<Button>(R.id.buttonEditEquipment)

            //enviar os dados da classe equipamento para as textViews
            textViewEquipmentName.text = equipments[position].name
            textViewQuantityValue.text = equipments[position].quantity.toString()

            // ao ser pressionado o botão irá seer aberta uma página para editar os equipamentos
            buttonEditEquipment.setOnClickListener {
                openEditEquipmentActivity(equipments[position].id!!, equipments[position].name!!,
                    equipments[position].quantity!!, equipments[position].descr!!)
                finish()
            }


            return rowView
        }

    }

    private fun openEditEquipmentActivity(id: Int, name: String, quantity: Int, descr: String) {
        val intent = Intent(this, EditEquipmentActivity :: class.java)
        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("quantity", quantity)
        intent.putExtra("descr", descr)
        startActivity(intent)
    }
}