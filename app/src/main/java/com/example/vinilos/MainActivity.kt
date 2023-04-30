package com.example.vinilos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.vinilos.databinding.ActivityMainBinding
import com.example.vinilos.ui.home.HomeActivity
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hasAlbumRedirect = intent?.extras?.getString("setAlbum").toBoolean()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
   /*     supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#E4D3CD")))
        supportActionBar!!.elevation = 0F*/

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /* val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_album, R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)*/
        navView.setupWithNavController(navController)

        if (hasAlbumRedirect) {
            navController.navigate(R.id.navigation_album)
        }

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<MaterialToolbar>(R.id.topAppBar)
            .setupWithNavController(navController, appBarConfiguration)


        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                }
            }
        }

        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        /*

                val fragment = intent.getStringExtra("fragment")
                if (fragment == "AlbumFragment") {
                    navController.navigate(R.id.navigation_album)
                }
                else if(fragment == "ColeccionistaFragment"){
                    navController.navigate(R.id.navigation_dashboard)
                }

                navView.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_home -> {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            true
                        }
                        else -> {
                            NavigationUI.onNavDestinationSelected(item, navController)
                        }
                    }
                }

        */


    }
}