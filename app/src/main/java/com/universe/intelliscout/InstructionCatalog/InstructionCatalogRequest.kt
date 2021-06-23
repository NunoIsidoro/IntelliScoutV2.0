package com.universe.intelliscout.InstructionCatalog

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.universe.intelliscout.Models.Login
import com.universe.intelliscout.Models.Equipment
import com.universe.intelliscout.Models.Instruction
import com.universe.intelliscout.Models.InstructionCatalog
import ipca.example.projetosemestre.Models.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject

object InstructionCatalogRequest {

    const val url = "http://intelliscout.ml:60000/instructioncatalog/"

    fun getAllInstCatalog(callBack: (List<InstructionCatalog>) -> Unit) {

        val instCatalogs: MutableList<InstructionCatalog> = arrayListOf()
        val request = Request.Builder().url(url).get().build()

        OkHttpClient().newCall(request).execute().use { response ->

            val instCatalogJsonArrayStr: String = response.body!!.string()
            val instCatalogJsonArray = JSONArray(instCatalogJsonArrayStr)

            for (index in 0 until instCatalogJsonArray.length()) {
                val jsonArticle = instCatalogJsonArray.get(index) as JSONObject
                val instructionCatalog = InstructionCatalog.fromJson(jsonArticle)
                instCatalogs.add(instructionCatalog)
            }

            callBack(instCatalogs)
        }
    }

    /*
        This function receive an INT and return an Equipment (Json)
    */
    fun getInstCatalog(id: Int): InstructionCatalog {

        // declerate variables
        var instCatalog: InstructionCatalog? = null
        val request = Request.Builder().url(url + "$id").build()

        // make a request to api, and transform the answer (string) into (json)
        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                instCatalog = InstructionCatalog.fromJson(jsonArticle)
            }

            return instCatalog!!
        }
    }


    fun addInstCatalog(instCatalog: InstructionCatalog) {

        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            instCatalog.toJson().toString()
        )

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun editInstCatalog(instCatalog: InstructionCatalog) {

        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            instCatalog.toJson().toString()
        )

        val request = Request.Builder()
            .url(url + "${instCatalog.id}")
            .put(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun removeInstCatalog(idInstruction: Int) {

        val request = Request.Builder()
            .url(url + "$idInstruction")
            .delete()
            .build()

        OkHttpClient().newCall(request).execute().use { }

    }

}