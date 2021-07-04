package com.example.imperium_scorpio.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imperium_scorpio.Lock
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.match.MatchActivity
import com.example.imperium_scorpio.postal.Ermes


class Waiting_Room : Fragment(R.layout.fragment_waiting_room) {

    val lock= Lock()
    val ermes= Ermes(lock)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent= Intent(activity, MatchActivity::class.java)
        if (ermes.readyToPlay("prova")){
            startActivity(intent)
        }else{
            while (lock.read())
                startActivity(intent)
        }
    }
}


