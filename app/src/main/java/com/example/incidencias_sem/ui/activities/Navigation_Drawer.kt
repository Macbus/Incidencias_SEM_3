package com.example.incidencias_sem.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.incidencias_sem.R
import com.example.incidencias_sem.R.layout.nav_header_navigation_drawer
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.databinding.ActivityNavigationDrawerBinding
import com.example.incidencias_sem.databinding.NavHeaderNavigationDrawerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Navigation_Drawer : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.navigationDrawer.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        drawerLayout.addDrawerListener(object: ActionBarDrawerToggle(this,drawerLayout,binding.navigationDrawer.toolbar,0,0){
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                val navView = binding.navView
                val headerView = navView.getHeaderView(0)
                val headerBinding = NavHeaderNavigationDrawerBinding.bind(headerView)
                lifecycleScope.launch {
                   val usuario = withContext(Dispatchers.IO){
                        App.obtenerDB().usersDao().findOneById(App.getUserID())
                    }

                    headerBinding.tvNameUser.text = usuario.name
                    headerBinding.tvUserEmail.text = usuario.email
                }
            }
        })
        val navView: NavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_drawer) as NavHostFragment


        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_Inicio, R.id.nav_Crear, R.id.nav_Consultas, R.id.nav_Actualizar
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.navigation__drawer, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_close_session -> {
                App.closeSession()
                startActivity(
                    Intent(
                        this, SignActivity::class.java
                    )
                )
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }
}


