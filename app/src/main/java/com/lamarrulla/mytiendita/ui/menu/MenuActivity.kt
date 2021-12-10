package com.lamarrulla.mytiendita.ui.menu

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lamarrulla.mytiendita.R
import com.lamarrulla.mytiendita.api.data_source.firebase.DsLoginFirebase
import com.lamarrulla.mytiendita.api.repo_imp.firebase.RepoLoginFirebaseImpl
import com.lamarrulla.mytiendita.databinding.ActivityMenuBinding
import com.lamarrulla.mytiendita.utils.Utils
import com.lamarrulla.mytiendita.utils.VMFatory

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private val TAG = javaClass.name

    private val menuViewModel by viewModels<MenuViewModel>(){
        VMFatory(RepoLoginFirebaseImpl(DsLoginFirebase(this)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val utils = Utils(this)
        utils.showBar()*/
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_menu)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        menuViewModel.getValidaLogin()

        menuViewModel.validaLogin.observe(this, Observer { result ->
            result ?: return@Observer
            result.error?.let {
                Log.d(TAG, result.error.toString())
                navController.popBackStack(R.id.mobile_navigation, true)
                navController.navigate(R.id.loginFragment)

            }
            result.success?.let {
                if(it.displayName==""){
                    navController.popBackStack(R.id.mobile_navigation, true)
                    navController.navigate(R.id.loginFragment)
                }
                Log.d(TAG, "usuario se logeo correctamente")
                Log.d(TAG, it.displayName.toString());
            }
        })
    }
}