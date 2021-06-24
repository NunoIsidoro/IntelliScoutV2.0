package com.universe.intelliscout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.universe.intelliscout.Authentic.LoginRequest
import com.universe.intelliscout.Equipment.AddEquipmentActivity
import com.universe.intelliscout.Equipment.ListEquipmentActivity
import com.universe.intelliscout.Models.Login
import com.universe.intelliscout.Profile.EditProfileActivity
import com.universe.intelliscout.Profile.ProfileActivity
import com.universe.intelliscout.Profile.ProfileGetAllActivity
import com.universe.intelliscout.Profile.ProfileRequest
import ipca.example.projetosemestre.Models.ScoutUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var user: ScoutUser
    lateinit var loginUser: Login
    private var idScout : Int? = null
    private var gmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        val headerView = navView.getHeaderView(0)
        val textViewUserEmail = headerView.findViewById<TextView>(R.id.textViewHeaderEmail)
        val textViewUserName = headerView.findViewById<TextView>(R.id.textviewHeaderUserName)

        val textViewName: TextView = findViewById(R.id.textViewName)


        val bundle = intent.extras

        bundle?.let {
            idScout = it.getInt("idScout")
            gmail = it.getString("gmail")
        }


        GlobalScope.launch(Dispatchers.IO) {

            user = ProfileRequest.getScoutUser(idScout!!)

            if(gmail != null)
                loginUser = LoginRequest.getLoginByGmail(gmail!!)

            GlobalScope.launch(Dispatchers.Main) {

                if(loginUser.role == 3)
                    navView.inflateMenu(R.menu.menu_scout)
                else
                    navView.inflateMenu(R.menu.menu_main)

                textViewName.text = user.name
                textViewUserName.text = user.name
                textViewUserEmail.text = gmail

                toggle = ActionBarDrawerToggle(
                    this@HomeActivity,
                    drawerLayout,
                    R.string.open,
                    R.string.close
                )
                drawerLayout.addDrawerListener(toggle)
                toggle.syncState()

                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                supportActionBar?.setTitle("Página Principal")


                //supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
                //supportActionBar?.setCustomView(R.layout.abs_layout)

                navView.setNavigationItemSelectedListener {

                    when (it.itemId) {

                        R.id.nav_profile -> {

                        val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
                        intent.putExtra("idScout", idScout)
                        intent.putExtra("gmail", gmail)
                        startActivity(intent)

                        }

                        R.id.nav_edit_profile -> {

                        val intent = Intent(this@HomeActivity, EditProfileActivity::class.java)
                        intent.putExtra("idScout", idScout)
                        startActivity(intent)

                        }

                        R.id.nav_manage_profile -> {

                            val intent = Intent(this@HomeActivity, ProfileGetAllActivity::class.java)
                            startActivity(intent)


                        }

                        R.id.nav_logout -> {

                            finish()

                        }

                        /*
                R.id.nav_activities ->{



                }

                 */

                        R.id.nav_edit_activities -> {


                        }

                        R.id.nav_manage_activities -> {
                            /*
                    val intent = Intent(this, ListActivities::class.java)
                    startActivity(intent)

                     */


                        }

                        R.id.nav_calendar -> {


                        }

                        R.id.nav_manual -> {

                            /*
                    val intent = Intent(this, ListReadInstructionsActivity::class.java)
                    startActivity(intent)

                     */

                        }

                        R.id.nav_edit_instructions -> {


                        }

                        R.id.nav_add_instructions -> {

                            /*
                    val intent = Intent(this, AddInstructionActivity::class.java)
                    startActivity(intent)

                     */

                        }

                        R.id.nav_add_equipment -> {


                            val intent = Intent(this@HomeActivity, AddEquipmentActivity::class.java)
                            startActivity(intent)



                        }

                        R.id.nav_edit_equipment -> {


                            val intent = Intent(this@HomeActivity, ListEquipmentActivity::class.java)
                            startActivity(intent)


                        }

                        R.id.nav_manage_equipment -> {

                            /*
                    val intent = Intent(this, EditProfileActivity::class.java)
                    startActivity(intent)
                     */


                        }
                    }
                    true
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
