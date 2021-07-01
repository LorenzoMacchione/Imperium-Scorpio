package com.example.imperium_scorpio.match

import androidx.lifecycle.ViewModel
import com.example.imperium_scorpio.match.viewmodels.Explorer
import com.example.imperium_scorpio.match.viewmodels.Hand
import com.example.imperium_scorpio.match.viewmodels.Planet
import com.example.imperium_scorpio.match.viewmodels.Resource

class MatchViewModel : ViewModel() {

    val hand= Hand()

    //Risorse avversario
    private val eRes1 = Resource()
    private val eRes2 = Resource()
    private val eRes3 = Resource()
    private val eRes4 = Resource()
    val eRes = mutableListOf<Resource>()

    //Risorse giocatore
    private val pRes1 = Resource()
    private val pRes2 = Resource()
    private val pRes3 = Resource()
    private val pRes4 = Resource()
    val pRes = mutableListOf<Resource>()

    val explorer = Explorer()



    //Pianeti
    val planets = mutableListOf<Planet>()
    private val p1 =Planet(0,1,true,false,true,false)
    private val p2 = Planet(1,1,false,true,false,true)
    private val p3 = Planet(2,1,true,true,false,false)
    private val p4 = Planet(3,1,false,false,true,true)
    private val p5 = Planet(4,3,true,true,true,true)
    private val p6 = Planet(5,1,false,false,true,true)
    private val p7 = Planet(6,1,false,true,false,true)
    private val p8 = Planet(7,1,true,true,false,false)
    private val p9 = Planet(8,1,true,false,true,false)


    init {

        eRes.add(eRes1)
        eRes.add(eRes2)
        eRes.add(eRes3)
        eRes.add(eRes4)

        pRes.add(pRes1)
        pRes.add(pRes2)
        pRes.add(pRes3)
        pRes.add(pRes4)

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