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
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.ehsanfallahi.farsnews.R
import com.ehsanfallahi.farsnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    var navigationPosition: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        drawerLayout=binding.drawerLayout
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        setSupportActionBar(toolbar)
//        val actionBar=supportActionBar
//        actionBar?.title="Navigation Drawer"



        initView()

    }


    private fun initView() {
        setSupportActionBar(toolbar)
        setUpDrawerLayout()

        navigationPosition = 1
//        navigateToFragment(InboxFragment.newInstance())
        nav_view.setCheckedItem(navigationPosition)
        toolbar.title = "Navigation View"

        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cultural_news -> {
//                    toolbar.title = getString(R.string.inbox)
//                    navigationPosition = R.id.navItemInbox
                    Toast.makeText(this,"fragment cultural_news",Toast.LENGTH_LONG).show()
                }
                R.id.political_news -> {
//                    toolbar.title = getString(R.string.sent)
//                    navigationPosition = R.id.navItemSent
//                    navigateToFragment(SentFragment.newInstance())
                    Toast.makeText(this,"fragment cultural_news",Toast.LENGTH_LONG).show()
                }
                R.id.social_news -> {
//                    toolbar.title = getString(R.string.draft)
//                    navigationPosition = R.id.navItemDraft
//                    navigateToFragment(DraftFragment.newInstance())
                    Toast.makeText(this,"fragment social_news",Toast.LENGTH_LONG).show()
                }
                R.id.economic_news -> {
//                    toolbar.title = getString(R.string.trash)
//                    navigationPosition = R.id.navItemTrash
//                    navigateToFragment(TrashFragment.newInstance())
                    Toast.makeText(this,"fragment economic_news",Toast.LENGTH_LONG).show()
                }
                R.id.sport_news -> {
//                    toolbar.title = getString(R.string.settings)
//                    navigationPosition = R.id.navItemSettings
//                    navigateToFragment(SettingsFragment.newInstance())
                    Toast.makeText(this,"fragment sport_news",Toast.LENGTH_LONG).show()
                }
            }
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            true
        }
        changeNavigationHeaderInfo()

        drawerLayout.addDrawerListener(object:DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(p0: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerSlide(p0: View, p1: Float) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerClosed(p0: View) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerOpened(p0: View) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
    private fun changeNavigationHeaderInfo() {
        val headerView = nav_view.getHeaderView(0)
//        headerView.textEmail.text = "lokeshdesai@android4dev.com"
    }

    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}