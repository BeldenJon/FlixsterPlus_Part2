package com.example.flixsterplus_part2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/** The MainActivity for the flixsterplus app launches a [InTheaterMovieFragment]. */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, InTheaterMovieFragment(), null).commit()
    }
}