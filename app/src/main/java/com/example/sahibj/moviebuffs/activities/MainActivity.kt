package com.example.sahibj.moviebuffs.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.fragments.PopularMoviesFragment
import com.example.sahibj.moviebuffs.services.MovieService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val SORT = "popular"
    private lateinit var movieRecyclerView: RecyclerView

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        setupNavigationView()

        addFragment(PopularMoviesFragment(), PopularMoviesFragment.TAG)
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private lateinit var drawerLayout: DrawerLayout

    private fun setupNavigationView() {
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView != null) {
            setupDrawerContent(navigationView)
        }
        navigationView.menu.getItem(0).isChecked = true
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener(
                { menuItem ->
                    when (menuItem.itemId) {
                        R.id.pop_movies_nav_menu_item -> {
                            addFragment(PopularMoviesFragment(), PopularMoviesFragment.TAG)
                        }
                        else -> {
                        }
                    }
                    // Do nothing, we're already on that screen
                    // Close the navigation drawer when an item is selected.
                    menuItem.isChecked = true
                    drawerLayout.closeDrawers()
                    true
                })
    }

    private fun addFragment(fragmentToAdd: Fragment, tag: String?) {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)

        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.contentFrame, fragmentToAdd, tag).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Open the navigation drawer when the home icon is selected from the toolbar.
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = MainActivity.javaClass.simpleName
    }
}
