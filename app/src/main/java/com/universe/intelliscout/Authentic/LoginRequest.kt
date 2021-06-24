package com.universe.intelliscout.Authentic

import com.universe.intelliscout.Models.Login
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

object LoginRequest {

    const val BASE_API = "http://intelliscout.ml:60000/authentic"
    const val LOGIN = "/login"
    const val REGISTER = "/register"

    fun loginRequest(login: Login): Boolean {

        val requestBody = login.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        println(login.toJson().toString())

        val request = Request.Builder()
            .url(BASE_API + LOGIN)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use { response ->

            println("response = ${response.message}")

            if (response.message == "ok")
                return true

        }

        return false

    }


    fun registerRequest(register: Login): Boolean {

        println(register.toJson().toString())

        val requestBody = register.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(BASE_API + REGISTER)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use { response ->

            println(response)

            if (response.message == "OK")
                return true

        }

        return false

    }


    fun getLoginByGmail(gmail: String): Login {

        var login: Login? = null

        val request = Request.Builder()
            .url(BASE_API + "/gmail/$gmail")
            .get()
            .build()

        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                println(jsonArticle)
                login = Login.fromJson(jsonArticle)
            }

            return login!!
        }
    }


    fun getLoginById(id: Int): Login {

        var login: Login? = null

        val request = Request.Builder()
            .url(BASE_API + "/$id")
            .get()
            .build()

        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                println(jsonArticle)
                login = Login.fromJson(jsonArticle)
            }

            return login!!
        }
    }


    fun getAllLogin(callBack: (List<Login>) -> Unit) {

        val loginList: MutableList<Login> = arrayListOf()

        val request = Request.Builder().url(BASE_API).build()

        OkHttpClient().newCall(request).execute().use { response ->

            val loginJsonArrayStr: String = response.body!!.string()
            val loginJsonArray = JSONArray(loginJsonArrayStr)

            for (index in 0 until loginJsonArray.length()) {
                val jsonArticle = loginJsonArray.get(index) as JSONObject
                val login = Login.fromJson(jsonArticle)
                loginList.add(login)
            }

            callBack(loginList)
        }
    }

    fun removeLogin(id: Int) {

        val request = Request.Builder()
            .url(BASE_API + "/$id")
            .delete()
            .build()

        OkHttpClient().newCall(request).execute().use { }

    }


}