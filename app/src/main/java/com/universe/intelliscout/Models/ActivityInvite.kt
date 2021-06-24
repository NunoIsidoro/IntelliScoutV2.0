package com.universe.intelliscout.Models

import org.json.JSONObject

class ActivityInvite {

    var idScout: Int? = null
    var idActivity: Int? = null

    constructor()

    constructor(idScout: Int?, idActivity: Int?) {

        this.idScout = idScout
        this.idActivity = idActivity

    }

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idScout", idScout)
        jsonObject.put("idActivity", idActivity)
        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): ActivityInvite {
            val activityInvite = ActivityInvite()

            activityInvite.idScout = jsonArticle.getInt("idScout")
            activityInvite.idActivity = jsonArticle.getInt("idActivity")
            return activityInvite
        }
    }

}