/**
 * Classe che gestisce l'intro all'avvio, l'inserimento del nome del giocatore,
 * l'iinizio del matchmaking e l'ingresso nella sezione delle regole
 */

package com.example.imperium_scorpio.home

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.postal.Ermes


class Menu : Fragment(R.layout.menu) {

    lateinit var mediaPlayer: MediaPlayer
    val ermes = Ermes()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mediaPlayer = MediaPlayer.create(activity, R.raw.avvio)

        getView()?.findViewById<ConstraintLayout>(R.id.menu)?.visibility = View.INVISIBLE
        getView()?.findViewById<ImageView>(R.id.logo)?.visibility = View.VISIBLE
        getView()?.findViewById<ImageButton>(R.id.imageButton)?.setOnClickListener { ermes.cleanDB() }

        val listener =
            Listener(requireView().findViewById(R.id.logo), requireView().findViewById(R.id.menu))
        mediaPlayer.setOnCompletionListener(listener)
        mediaPlayer.start()

        getView()?.findViewById<ImageView>(R.id.logo)?.setOnClickListener {
            mediaPlayer.release()
            getView()?.findViewById<ImageView>(R.id.logo)?.visibility = View.INVISIBLE
            getView()?.findViewById<ConstraintLayout>(R.id.menu)?.visibility = View.VISIBLE
        }

        requireView().findViewById<ImageView>(R.id.new_game).setOnClickListener {
            val user = requireView().findViewById<EditText>(R.id.Player_name).text.toString()
            val bundle = bundleOf("user" to user)
        view.findNavController().navigate(R.id.action_menu_to_waiting_Room, bundle)
        }

        requireView().findViewById<ImageView>(R.id.collection).setOnClickListener {
            view.findNavController().navigate(R.id.action_menu_to_rules)
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