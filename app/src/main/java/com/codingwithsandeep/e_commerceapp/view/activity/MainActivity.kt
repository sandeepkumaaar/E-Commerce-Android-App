package com.codingwithsandeep.e_commerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.codingwithsandeep.e_commerceapp.databinding.ActivityMainBinding
import com.codingwithsandeep.e_commerceapp.databinding.AppBarMainBinding
import com.codingwithsandeep.e_commerceapp.databinding.ContentMainBinding
import com.codingwithsandeep.e_commerceapp.R
import com.codingwithsandeep.e_commerceapp.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding
    lateinit var appBarMainBinding: AppBarMainBinding
    lateinit var contentMainBinding: ContentMainBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        appBarMainBinding = AppBarMainBinding.inflate(layoutInflater)
        contentMainBinding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        bottomNavigationView = binding.appBar.contentMain.bottomNav

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.categoryFragment,
                R.id.profileFragment,
                R.id.seeAllProductsFragment,
                R.id.productDetailsFragment

            ), binding.drawerLayout
        )

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBar.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close,
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setupActionBarWithNavController(navController, binding.drawerLayout)
        binding.navView.setupWithNavController(navController)
        bottomNavigationView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menus, menu)
        val notification = menu?.findItem(R.id.action_notification)
        val addToCart = menu?.findItem(R.id.action_myCart)
        val favorite = menu?.findItem(R.id.action_favorite)
        favorite?.isVisible = false
        return true
    }

    fun setBottomNavigationVisibility(visibility:Int){
        bottomNavigationView.visibility = visibility
    }

    fun setToolbarVisibility(visibility:Int){
        binding.appBar.toolbar.visibility = visibility
    }
}