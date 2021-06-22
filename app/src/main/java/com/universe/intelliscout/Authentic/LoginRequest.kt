package com.universe.intelliscout.Authentic

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.universe.intelliscout.Models.Equipment
import com.universe.intelliscout.Models.Instruction
import com.universe.intelliscout.Models.Login
import ipca.example.projetosemestre.Models.*
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import java.lang.reflect.Method

class LoginRequest {

    private var queue: RequestQueue? = null

    //Function responsible for the account register: POST
    fun register(context: Context, user: Login, registerEvent: ((Boolean) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + AUTHENTIC + REGISTER,
                user.toJson(),
                Response.Listener {
                    Log.d("Requests", it.toString())

                    Log.d("auth", it.toString())


                    if (it.getBoolean("auth"))
                        registerEvent.invoke(true)
                    else
                        registerEvent.invoke(false)


                }, Response.ErrorListener {

                    registerEvent.invoke(false)
                    Log.d("Requests", it.toString())

                }


            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    //Function responsible for the account login: POST
    fun login(context: Context, user: Login, loginEvent: ((Boolean) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + AUTHENTIC + LOGIN,
                user.toJson(),
                Response.Listener {

                    Log.d("Requests", it.toString())

                    Log.d("auth", it.toString())


                    if (it.getBoolean("auth"))
                        loginEvent.invoke(true)
                    else
                        loginEvent.invoke(false)


                }, Response.ErrorListener {

                    Log.d("Requests", it.toString())
                    loginEvent.invoke(false)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    //Function responsible for receive the login account with the gmail selected: GET
    fun getLoginByGmail(
        context: Context,
        gmail: String,
        getLoginByGmailEvent: ((JSONArray?) -> Unit)
    ) {

        println(gmail)
        doAsync {
            queue = Volley.newRequestQueue(context)
            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + AUTHENTIC + "/gmail/$gmail",
                Response.Listener {

                    Log.d("Request", it.toString())
                    getLoginByGmailEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("Request", it.toString())
                    getLoginByGmailEvent.invoke(null)
                }
            ) {
                override fun getHeaders() = mutableMapOf("Content-Type" to "application/json")
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for receive all the login accounts: GET
    fun getUserById(context: Context, id: Int, getUserByIdEvent: ((JSONArray?) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + SCOUT_USER + "/${id}",
                Response.Listener {

                    getUserByIdEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("VolleyHelper", it.toString())
                    getUserByIdEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for receive all the login accounts: GET
    fun getAllLogin(context: Context, getAllLoginEvent: ((JSONArray?) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + AUTHENTIC,
                Response.Listener {

                    getAllLoginEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("VolleyHelper", it.toString())
                    getAllLoginEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for delete any login account: DELETE
    fun deleteLogin(context: Context, id: Int, deleteLoginEvent: ((Boolean) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.DELETE,
                BASE_API + AUTHENTIC + id,
                Response.Listener<String> {
                    deleteLoginEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                }, Response.ErrorListener {
                    deleteLoginEvent.invoke(false)
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(stringRequest)
        }
    }

    //Function responsible for receive all the scout accounts: GET
    fun getAllScoutUsers(context: Context, getAllScoutUsersEvent: ((JSONArray?) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + SCOUT_USER,
                Response.Listener {

                    getAllScoutUsersEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("VolleyHelper", it.toString())
                    getAllScoutUsersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for the account creation: POST
    fun createScout(context: Context, user: ScoutUser, createScoutEvent: ((Boolean) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            println(user.toJson())

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + SCOUT_USER,
                user.toJson(),
                Response.Listener {

                    createScoutEvent.invoke(true)
                    Log.d("Requests", it.toString())

                }, Response.ErrorListener {

                    createScoutEvent.invoke(false)
                    Log.d("Requests", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    fun updateScoutUser(
        context: Context,
        newUser: ScoutUser,
        updateScoutUserEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            println("newUser.tojson() = " + newUser.toJson())


            val jsonObjectRequest = object : JsonObjectRequest(
                Method.PUT,
                BASE_API + SCOUT_USER + "/" + newUser.id,
                newUser.toJson(),
                Response.Listener {

                    updateScoutUserEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())

                }, Response.ErrorListener {

                    updateScoutUserEvent.invoke(false)
                    Log.d("VolleyHelper", it.toString())

                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    companion object{
        //const val BASE_API = "intelliscout.amipca.xyz:60000"
        //const val BASE_API = "http://192.168.1.82:60000"
        const val BASE_API = "http://intelliscout.ml:60000"
        const val AUTHENTIC = "/authentic"
        const val REGISTER = "/register"
        const val LOGIN = "/login"
        const val SCOUT_USER = "/scoutUser"



        private var mInstance : LoginRequest? = LoginRequest()

        val instance : LoginRequest
            @Synchronized get(){
                if(null == mInstance){
                    mInstance = LoginRequest()
                }
                return mInstance!!
            }

    }

}