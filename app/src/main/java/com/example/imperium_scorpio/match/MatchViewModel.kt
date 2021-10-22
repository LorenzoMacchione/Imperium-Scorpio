package com.example.imperium_scorpio.match

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.imperium_scorpio.Lock
import com.example.imperium_scorpio.R
import com.example.imperium_scorpio.database.CardDAO
import com.example.imperium_scorpio.match.viewmodels.*
import kotlin.properties.Delegates

class MatchViewModel() : ViewModel() {

    lateinit var hand: Hand

    val enemy = Enemy()

    val turn = MessageLock("Enemy turn",
        "Player turn")



    //Risorse giocatore
    private val pRes1 = Resource()
    private val pRes2 = Resource()
    private val pRes3 = Resource()
    private val pRes4 = Resource()
    val pRes = mutableListOf<Resource>()

    lateinit var explorer : Explorer



    //Pianeti
    val planets = mutableListOf<Planet>()
    private lateinit var p1 :Planet
    private lateinit var p2 :Planet
    private lateinit var p3 :Planet
    private lateinit var p4 :Planet
    private lateinit var p5 :Planet
    private lateinit var p6 :Planet
    private lateinit var p7 :Planet
    private lateinit var p8 :Planet
    private lateinit var p9 :Planet


    init {



        pRes.add(pRes1)
        pRes.add(pRes2)
        pRes.add(pRes3)
        pRes.add(pRes4)



    }


    fun setContext(context: Context){

        hand = Hand(context)

        explorer = Explorer(context)

        p1 =Planet(0,1,true,false,true,false,context)
        p2 = Planet(1,1,false,true,false,true,context)
        p3 = Planet(2,1,true,true,false,false,context)
        p4 = Planet(3,1,false,false,true,true,context)
        p5 = Planet(4,3,true,true,true,true,context)
        p6 = Planet(5,1,false,false,true,true,context)
        p7 = Planet(6,1,true,true,false,false,context)
        p8 = Planet(7,1,false,true,false,true,context)
        p9 = Planet(8,1,true,false,true,false,context)

        planets.add(p1)
        planets.add(p2)
        planets.add(p3)
        planets.add(p4)
        planets.add(p5)
        planets.add(p6)
        planets.add(p7)
        planets.add(p8)
        planets.add(p9)
    }

}