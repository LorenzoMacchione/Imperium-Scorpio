package com.example.imperium_scorpio.match

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.databinding.ActivityMainBinding
import com.example.imperium_scorpio.databinding.ActivityMatchBinding

class MatchActivity : AppCompatActivity() {

    val HC1_model: SmallCard by viewModels()
    val HC2_model: SmallCard by viewModels()
    val HC3_model: SmallCard by viewModels()
    val HC4_model: SmallCard by viewModels()
    val HC5_model: SmallCard by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding :ActivityMatchBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_match)
        binding.hc1 = HC1_model
        binding.hc2 = HC2_model
        binding.hc3 = HC3_model
        binding.hc4 = HC4_model
        binding.hc5 = HC5_model
        binding.lifecycleOwner = this


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}