package com.example.imperium_scorpio

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class menu : Fragment(R.layout.menu) {

    lateinit var mediaPlayer: MediaPlayer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(activity, R.raw.avvio)

        getView()?.findViewById<ConstraintLayout>(R.id.menu)?.visibility = View.INVISIBLE
        getView()?.findViewById<ImageView>(R.id.logo)?.visibility = View.VISIBLE

        val listener =
            Listener(requireView().findViewById(R.id.logo), requireView().findViewById(R.id.menu))
        mediaPlayer.setOnCompletionListener(listener)
        mediaPlayer.start()

        getView()?.findViewById<ImageView>(R.id.logo)?.setOnClickListener {
            mediaPlayer.release()
            getView()?.findViewById<ImageView>(R.id.logo)?.visibility = View.INVISIBLE
            getView()?.findViewById<ConstraintLayout>(R.id.menu)?.visibility = View.VISIBLE
        }
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