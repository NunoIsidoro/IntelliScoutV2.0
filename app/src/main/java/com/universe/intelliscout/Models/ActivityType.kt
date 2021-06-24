package com.universe.intelliscout.Models

import org.json.JSONObject

class ActivityType {

    var id: Int? = null
    var name: String? = null
    var descr: String? = null

    constructor()

    constructor(id: Int?, name: String?, descr: String?) {

        this.id = id
        this.name = name
        this.descr = descr

    }


    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("descr", descr)
        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): ActivityType {
            val activityType = ActivityType()

            activityType.id = jsonArticle.getInt("id")
            activityType.name = jsonArticle.getString("name")
            activityType.descr = jsonArticle.getString("descr")

            return activityType
        }
    }

}