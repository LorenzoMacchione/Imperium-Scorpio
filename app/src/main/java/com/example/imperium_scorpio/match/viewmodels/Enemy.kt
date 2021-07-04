package com.example.imperium_scorpio.match.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imperium_scorpio.database.Cards
import kotlin.properties.Delegates

class Enemy {

    var hand: Int by Delegates.observable(0) { property, oldValue, newValue ->

        for (i in 0..newValue){
            card[i].value = View.VISIBLE
        }
        for (i in 4 downTo newValue){
            card[i].value = View.INVISIBLE
        }
    }

    private val eRes1 = Resource()
    private val eRes2 = Resource()
    private val eRes3 = Resource()
    private val eRes4 = Resource()
    val eRes = mutableListOf<Resource>()

    val card = mutableListOf<MutableLiveData<Int>>()

    private val _card1 = MutableLiveData<Int>(0)
    val card1: LiveData<Int>
        get() = _card1

    private val _card2 = MutableLiveData<Int>(0)
    val card2: LiveData<Int>
        get() = _card2

    private val _card3 = MutableLiveData<Int>(0)
    val card3: LiveData<Int>
        get() = _card3

    private val _card4 = MutableLiveData<Int>(0)
    val card4: LiveData<Int>
        get() = _card4

    private val _card5 = MutableLiveData<Int>(0)
    val card5: LiveData<Int>
        get() = _card5

    init {
        card.add(_card1)
        card.add(_card2)
        card.add(_card3)
        card.add(_card4)
        card.add(_card5)

        eRes.add(eRes1)
        eRes.add(eRes2)
        eRes.add(eRes3)
        eRes.add(eRes4)
    }

    fun draw(){
        hand++
    }

    fun playCard(c: Cards){
        eRes1.useRes(c.res1)
        eRes2.useRes(c.res2)
        eRes3.useRes(c.res3)
        eRes4.useRes(c.res4)

        hand--
    }

    fun enemyMining(i:Int){
        eRes1.minRes(i)
        eRes2.minRes(i)
        eRes3.minRes(i)
        eRes4.minRes(i)
    }
}