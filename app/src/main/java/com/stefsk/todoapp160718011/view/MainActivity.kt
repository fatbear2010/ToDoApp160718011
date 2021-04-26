 package com.stefsk.todoapp160718011.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.stefsk.todoapp160718011.R

 class MainActivity : AppCompatActivity() {
     private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this,R.id.fragmentHost)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

     override fun onSupportNavigateUp(): Boolean {
         return NavigationUI.navigateUp(navController,null)
     }
}