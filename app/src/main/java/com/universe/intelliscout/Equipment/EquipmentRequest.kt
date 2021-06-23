/*package com.universe.intelliscout.Equipment

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.universe.intelliscout.Models.Activity
import com.universe.intelliscout.Models.Login
import com.universe.intelliscout.Models.Instruction
import com.universe.intelliscout.Models.Equipment
import ipca.example.projetosemestre.Models.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
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


    fun addEquipment(equipment: Equipment) {

        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            equipment.toJson().toString()
        )

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun editEquipment(equipment: Equipment) {

        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            equipment.toJson().toString()
        )

        val request = Request.Builder()
            .url(url + "${equipment.id}")
            .put(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun removeEquipment(idEquipment: Int) {

        val request = Request.Builder()
            .url(url +"$idEquipment")
            .delete()
            .build()

        OkHttpClient().newCall(request).execute().use { }

    }

