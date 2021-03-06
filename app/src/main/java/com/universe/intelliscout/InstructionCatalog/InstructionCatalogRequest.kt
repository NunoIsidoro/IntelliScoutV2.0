package com.universe.intelliscout.InstructionCatalog

import com.universe.intelliscout.Models.InstructionCatalog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder

object InstructionCatalogRequest {

    const val URL = "http://intelliscout.ml:60000/instructioncatalog/"

    fun getAllInstCatalog(callBack: (List<InstructionCatalog>) -> Unit) {

        val instCatalogs: MutableList<InstructionCatalog> = arrayListOf()
        val request = Request.Builder().url(URL).get().build()

        OkHttpClient().newCall(request).execute().use { response ->

            val instCatalogJsonArrayStr: String = response.body!!.string()
            val instCatalogJsonArray = JSONArray(instCatalogJsonArrayStr)

            for (index in 0 until instCatalogJsonArray.length()) {
                val jsonArticle = instCatalogJsonArray.get(index) as JSONObject
                val instructionCatalog = InstructionCatalog.fromJson(jsonArticle)
                println(instructionCatalog)
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
        val request = Request.Builder().url(URL + "$id").build()

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

        val requestBody = instCatalog.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(URL)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}


    }

    fun editInstCatalog(instCatalog: InstructionCatalog) {

        val requestBody = instCatalog.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(URL)
            .put(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun removeInstCatalog(idInstruction: Int) {

        val request = Request.Builder()
            .url(URL + "$idInstruction")
            .delete()
            .build()

        OkHttpClient().newCall(request).execute().use { }

    }

}