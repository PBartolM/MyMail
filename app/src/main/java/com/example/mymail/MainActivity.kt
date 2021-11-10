package com.example.mymail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.navigation.NavigationView

import android.widget.Spinner

import android.widget.ArrayAdapter





class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        //La NavigationUI usa un objeto AppBarConfiguration para administrar el comportamiento del botón Navigation en la esquina superior izquierda de la ToolBar.
        // Definimos varios destinos de nivel superior, ya que no guardan relación entre ellos.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_inbox, R.id.nav_outbox, R.id.nav_trash,R.id.nav_spam), drawerLayout)

        //El controlador de navegación posiciona el botón de navegación sobre la ToolBar y le da funcionalidad.
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val header= navView.getHeaderView(0)
        val correos = arrayOf("jonymelavo@unmail.es","ejemplo@unmail.es","holaquetal@unmail.es")

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, correos)

        val list_correo = header.findViewById(R.id.spinner) as Spinner
        list_correo.adapter = adaptador
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }


    //Método para desplegar el navegador lateral deslizante al hacer click sobre el panel lateral o botón de navegación de la ToolBar
    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}