package com.universe.intelliscout.Equipment

import com.universe.intelliscout.Models.Equipment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

object EquipmentRequest {

    const val url = "http://intelliscout.ml:60000/equipment/"

    fun getAllEquipment(callBack: (List<Equipment>) -> Unit) {

        val equipments: MutableList<Equipment> = arrayListOf()
        val request = Request.Builder().url(url).get().build()

        OkHttpClient().newCall(request).execute().use { response ->

            val equipmentJsonArrayStr: String = response.body!!.string()
            val equipmentJsonArray = JSONArray(equipmentJsonArrayStr)

            for (index in 0 until equipmentJsonArray.length()) {
                val jsonArticle = equipmentJsonArray.get(index) as JSONObject
                val equipment = Equipment.fromJson(jsonArticle)
                equipments.add(equipment)
            }

            callBack(equipments)
        }
    }

    /*
        This function receive an INT and return an Equipment (Json)
    */
    fun getEquipment(id: Int): Equipment {

        // declerate variables
        var equipment: Equipment? = null
        val request = Request.Builder().url(url + "$id").build()

        // make a request to api, and transform the answer (string) into (json)
        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                equipment = Equipment.fromJson(jsonArticle)
            }

            return equipment!!
        }
    }


    fun addEquipment(equipment: Equipment): Boolean {

        val requestBody = equipment.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {

            if (it.message == "OK")
                return true

        }

        return false

    }

    fun editEquipment(equipment: Equipment): Boolean {

        val requestBody = equipment.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        println(equipment.toJson())

        val request = Request.Builder()
            .url(url)
            .put(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {

            if (it.message == "OK")
                return true

        }

        return false

    }

    fun removeEquipment(idEquipment: Int) {

        val request = Request.Builder()
            .url(url + "$idEquipment")
            .delete()
            .build()

        OkHttpClient().newCall(request).execute().use { }

    }
}

