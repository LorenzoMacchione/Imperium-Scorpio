package com.example.imperium_scorpio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.media.tv.TvContract
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ciao
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        mediaPlayer = MediaPlayer.create(this, R.raw.avvio)
        if (!sharedPref.getBoolean("first run",true)){
            sharedPref.edit().putBoolean("first run",true).commit()
            val intent = Intent(this,installer::class.java)
            startActivity(intent)
        } else {

        findViewById<ConstraintLayout>(R.id.menu).visibility=View.INVISIBLE
        findViewById<ImageView>(R.id.logo).visibility=View.VISIBLE

        val listener= Listener(findViewById(R.id.logo), findViewById(R.id.menu))
        mediaPlayer.setOnCompletionListener(listener)
        mediaPlayer.start()

        findViewById<ImageView>(R.id.logo).setOnClickListener{
            mediaPlayer.release()
            findViewById<ImageView>(R.id.logo).visibility=View.INVISIBLE
            findViewById<ConstraintLayout>(R.id.menu).visibility=View.VISIBLE
        }
        }
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


    override fun onStop() {
        super.onStop()
            mediaPlayer.release()
        }
    }

class Listener(val logo: ImageView, val menu: ConstraintLayout): MediaPlayer.OnCompletionListener{
    override fun onCompletion(mp: MediaPlayer?) {
        logo.visibility=View.INVISIBLE
        menu.visibility=View.VISIBLE
       }
    }



