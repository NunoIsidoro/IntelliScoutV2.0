package com.universe.intelliscout.Profile

import ipca.example.projetosemestre.Models.ScoutUser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject


object ProfileRequest {

    const val url = "http://intelliscout.ml:60000/scoutuser/"

    fun getAllScoutUser(callBack: (List<ScoutUser>) -> Unit) {

        val scoutUsers: MutableList<ScoutUser> = arrayListOf()
        val request = Request.Builder().url(url).get().build()

        OkHttpClient().newCall(request).execute().use { response ->

            val scoutUserJsonArrayStr: String = response.body!!.string()
            val scoutUserJsonArray = JSONArray(scoutUserJsonArrayStr)

            for (index in 0 until scoutUserJsonArray.length()) {
                val jsonArticle = scoutUserJsonArray.get(index) as JSONObject
                val scoutUser = ScoutUser.fromJson(jsonArticle)
                scoutUsers.add(scoutUser)
            }

            callBack(scoutUsers)
        }
    }


    //This function receive an INT and return an Equipment (Json)

    fun getScoutUser(id: Int): ScoutUser {

        // declerate variables
        var scoutUser: ScoutUser? = null
        val request = Request.Builder().url(url + "$id").get().build()

        // make a request to api, and transform the answer (string) into (json)
        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                scoutUser = ScoutUser.fromJson(jsonArticle)
            }

            return scoutUser!!
        }
    }

    fun getLocal(id: Int): String {

        // declerate variables
        var local: String? = null
        val request = Request.Builder().url(url + "district/$id").get().build()

        // make a request to api, and transform the answer (string) into (json)
        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            println("jsonArrayStr = $jsonArrayStr")
            println("jsonArray = $jsonArray")

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                local = jsonArticle.getString("district_local")
            }

            return local!!
        }
    }

    fun getTeam(id: Int): String {

        // declerate variables
        var team: String? = null
        val request = Request.Builder().url(url + "team/$id").get().build()
        println(url + "district/$id")

        // make a request to api, and transform the answer (string) into (json)
        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            println("jsonArrayStr = $jsonArrayStr")
            println("jsonArray = $jsonArray")

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                team = jsonArticle.getString("name_scout_team")
            }

            return team!!
        }
    }

    fun addScoutUser(scoutUser: ScoutUser) {

        val requestBody = scoutUser.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        println("addScoutUser, scoutUSer = ${scoutUser.toJson().toString()}")

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun editScoutUser(scoutUser: ScoutUser) {


        val requestBody = RequestBody.create(
                "application/json".toMediaTypeOrNull(),
                scoutUser.toJson().toString()
        )
        println(scoutUser.toJson().toString())

        val request = Request.Builder()
            .url(url + "${scoutUser.id}")
            .put(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun removeScoutUser(idScoutUser: Int) {

        val request = Request.Builder()
            .url(url + "$idScoutUser")
            .delete()
            .build()

        OkHttpClient().newCall(request).execute().use { }

    }
}


