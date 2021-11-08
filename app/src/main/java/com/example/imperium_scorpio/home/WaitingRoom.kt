/**
 * gestisce il matchmaking cercando un utente con un nome diverso dal giocatore
 * viene fermata la ricerca se l'applicazione va in pausa e riprende quando viene riaperta
 */

package com.example.imperium_scorpio.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.match.MatchActivity
import com.example.imperium_scorpio.postal.Ermes


class Waiting_Room : Fragment(R.layout.fragment_waiting_room) {

    val ermes= Ermes()

   lateinit var user:String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments?.getString("user")!!
    }

    fun startGame(enemy: String?) {
        val intent= Intent(activity, MatchActivity::class.java)
        intent.putExtra("player", user)
        intent.putExtra("enemy", enemy)
        startActivityForResult(intent, 0)
    }

    override fun onPause() {
        super.onPause()
        ermes.stopSearch()
    }

    override fun onResume() {
        super.onResume()
        ermes.readyToPlay(user,this)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        view?.findNavController()?.navigate(R.id.action_waiting_Room_to_menu)
    }
}




