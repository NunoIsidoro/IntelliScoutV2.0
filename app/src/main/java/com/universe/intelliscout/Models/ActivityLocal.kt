package com.universe.intelliscout.Models

import org.json.JSONObject

class ActivityLocal {

    var id : Int? = null
    var longitude : String? = null
    var latitude : String? = null
    var urlActivity : String? = null
    var idZipCode : Int? = null

    constructor(){

    }

    constructor(id: Int?, longitude: String?, latitude: String?, urlActivity: String?, idZipCode: Int?){

        this.id = id
        this.longitude = longitude
        this.latitude = latitude
        this.urlActivity = urlActivity
        this.idZipCode = idZipCode

    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("longitude", longitude)
        jsonObject.put("latitude", latitude)
        jsonObject.put("url_activity", urlActivity)
        jsonObject.put("zip_code", idZipCode)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : ActivityLocal {
            val activityLocal = ActivityLocal()

            activityLocal.id = jsonArticle.getInt("id")
            activityLocal.longitude = jsonArticle.getString("longitude")
            activityLocal.latitude = jsonArticle.getString("latitude")
            activityLocal.urlActivity = jsonArticle.getString("url_activity")
            activityLocal.idZipCode = jsonArticle.getInt("zip_code")
            return activityLocal
        }
    }

}