package com.itn.myapplicationaxen.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itn.myapplicationaxen.R
import com.itn.myapplicationaxen.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFragment = JokeListFragment()
        listFragment.onItemSelected = { selectedItem ->
            showDetailFragment(selectedItem)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, listFragment)
            .commit()
    }

    private fun showDetailFragment(item: String) {
        val detailFragment = JokeFragment.newInstance(item)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}