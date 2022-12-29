package com.example.projectmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarrecyclerview.R
import com.example.belajarrecyclerview.databinding.ActivityMainBinding
import com.example.projectmap.fragment.AddReviewFragment
import com.example.projectmap.fragment.HomepageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val fragmentHome : Fragment = HomepageFragment()
    val fragmentAddReview : Fragment = AddReviewFragment()
    val fm : FragmentManager = supportFragmentManager
    var active : Fragment = HomepageFragment()
    lateinit var binding:ActivityMainBinding

    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonNavigation()

    }

    private fun buttonNavigation() {
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentAddReview).hide(fragmentAddReview).commit()

        bottomNavigationView = binding.bottomNavigationBar
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.navigation_homepage -> {
                    callFragment(0, fragmentHome)
                }
                R.id.naavigation_add_review -> {
                    callFragment(1, fragmentAddReview)
                }
            }
            false
        }


    }

    private fun callFragment(index : Int, fragment : Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}