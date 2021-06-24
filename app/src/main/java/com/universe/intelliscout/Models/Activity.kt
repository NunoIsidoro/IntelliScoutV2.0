package com.universe.intelliscout.Models

import org.json.JSONObject

class Activity {

    var id: Int? = null
    var name: String? = null
    var dtStart: String? = null
    var hourStart: String? = null
    var idLocal: Int? = null
    var idActivityType: Int? = null

    constructor()

    constructor(
        id: Int?,
        name: String?,
        dtStart: String?,
        dtEnd: String?,
        hourStart: String?,
        hourEnd: String?,
        idActivityLocal: Int?,
        idActivityType: Int?
    ) {

        this.id = id
        this.name = name
        this.dtStart = dtStart
        this.hourStart = hourStart
        this.idLocal = idLocal
        this.idActivityType = idActivityType

    }

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("dtStart", dtStart)
        jsonObject.put("hourStart", hourStart)
        jsonObject.put("idLocal", idLocal)
        jsonObject.put("idActivityType", idActivityType)
        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): Activity {
            val activity = Activity()

            activity.id = jsonArticle.getInt("id_activity")
            activity.name = jsonArticle.getString("name_activity")
            activity.dtStart = jsonArticle.getString("dt_start_activity")
            activity.hourStart = jsonArticle.getString("hour_start_activity")
            activity.idLocal = jsonArticle.getInt("id_local")
            activity.idActivityType = jsonArticle.getInt("id_activity_type")
            return activity
        }
    }

}