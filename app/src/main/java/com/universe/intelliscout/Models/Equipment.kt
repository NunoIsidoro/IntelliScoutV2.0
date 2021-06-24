package com.universe.intelliscout.Models

import org.json.JSONObject

class Equipment {

    var id: Int? = null
    var name: String? = null
    var quantity: Int? = null
    var descr: String? = null
    var imgUrl: String? = null

    constructor()

    constructor(id: Int?, name: String?, quantity: Int?, descr: String?, imgUrl: String?) {

        this.id = id
        this.name = name
        this.quantity = quantity
        this.descr = descr
        this.imgUrl = imgUrl

    }

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("quantity", quantity)
        jsonObject.put("descr", descr)
        jsonObject.put("img_url", imgUrl)
        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): Equipment {
            val equipment = Equipment()

            equipment.id = jsonArticle.getInt("id_equipment")
            equipment.name = jsonArticle.getString("name_equipment")
            equipment.quantity = jsonArticle.getInt("quantity_equipment")
            equipment.descr = jsonArticle.getString("descr_equipment")
            equipment.imgUrl = jsonArticle.getString("img_url_equipment")
            return equipment
        }
    }

}