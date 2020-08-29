package com.example.padcx_podcast_monthly_assignment.activities

import android.accounts.Account
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.fragments.AccountFragment
import com.example.padcx_podcast_monthly_assignment.fragments.DownloadFragment
import com.example.padcx_podcast_monthly_assignment.fragments.HomeFragment
import com.example.padcx_podcast_monthly_assignment.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        changeFragment(HomeFragment.newInstance("", ""))

        nav_main.setOnNavigationItemSelectedListener(object :
        BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_home -> {
                        changeFragment(HomeFragment.newInstance("", ""))
                        return true
                    }
                    R.id.action_search -> {
                        changeFragment(SearchFragment.newInstance("1", ""))
                        return true
                    }
                    R.id.action_download -> {
                        changeFragment(DownloadFragment.newInstance("", ""))
                        return true
                    }
                    R.id.action_account -> {
                        changeFragment(AccountFragment.newInstance("", ""))
                        return true
                    }
                }

                return false
            }

        })

    }

    private fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer,fragment).commit()
    }
}