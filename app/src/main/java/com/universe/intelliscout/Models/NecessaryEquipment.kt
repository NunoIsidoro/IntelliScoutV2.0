package ipca.example.projetosemestre.Models

import org.json.JSONObject

class NecessaryEquipment {

    var idActivity : Int? = null
    var idEquipment : Int? = null
    var quantity : Int? = null

    constructor(){

    }

    constructor(idActivity: Int?, idEquipment: Int?, quantity: Int?){

        this.idActivity = idActivity
        this.idEquipment = idEquipment
        this.quantity = quantity

    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idActivity", idActivity)
        jsonObject.put("idEquipment", idEquipment)
        jsonObject.put("quantity", quantity)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : NecessaryEquipment {
            val necessaryEquipment = NecessaryEquipment()

            necessaryEquipment.idActivity = jsonArticle.getInt("idActivity")
            necessaryEquipment.idEquipment = jsonArticle.getInt("idEquipment")
            necessaryEquipment.quantity = jsonArticle.getInt("quantity")
            return necessaryEquipment
        }
    }

}