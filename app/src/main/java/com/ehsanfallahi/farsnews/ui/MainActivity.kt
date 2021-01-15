package com.ehsanfallahi.farsnews.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    var navigationPosition: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        drawerLayout=binding.drawerLayout
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        setSupportActionBar(toolbar)
        val actionBar=supportActionBar
//        actionBar?.title="Navigation Drawer"



        initView()

    }


    private fun initView() {
        toolbar.title = "FARS 24"
//        toolbar.setNavigationOnClickListener {
//            navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
//        }
        setSupportActionBar(toolbar)
        setUpDrawerLayout()

        navigationPosition = 1
//        navigateToFragment(InboxFragment.newInstance())
        nav_view.setCheckedItem(navigationPosition)


        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.political_news -> {
                    Toast.makeText(this,"خبرهای سیاسی",Toast.LENGTH_LONG).show()
                }
                R.id.social_news -> {
//                    toolbar.title = getString(R.string.draft)
//                    navigationPosition = R.id.navItemDraft
//                    navigateToFragment(DraftFragment.newInstance())
                    Toast.makeText(this,"خبرهای اجتماعی",Toast.LENGTH_LONG).show()
                }
                R.id.economic_news -> {
                    Toast.makeText(this,"خبرهای اقتصادی",Toast.LENGTH_LONG).show()
                }
                R.id.sport_news -> {
                    Toast.makeText(this,"fragment sport_news",Toast.LENGTH_LONG).show()
                }
            }
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
        changeNavigationHeaderInfo()

    }
    private fun changeNavigationHeaderInfo() {
        val headerView = nav_view.getHeaderView(0)
    }

    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }
}