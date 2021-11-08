/**
 *Classe che gestisce i dati e le azioni dell'avversario
 */

package com.example.imperium_scorpio.match.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imperium_scorpio.database.Cards
import kotlin.properties.Delegates

class Enemy {
    var win: Int by Delegates.observable(0) { property, oldValue, newValue ->
      when(newValue){
          1 -> _winVisibility.value = View.VISIBLE
          2 -> _loseVisibility.value = View.VISIBLE
      }
    }

    private val _winVisibility = MutableLiveData<Int>(View.INVISIBLE)
    val winVisibility: LiveData<Int>
        get() = _winVisibility

    private val _loseVisibility = MutableLiveData<Int>(View.INVISIBLE)
    val loseVisibility: LiveData<Int>
        get() = _loseVisibility


    var hand: Int by Delegates.observable(0) { property, oldValue, newValue ->

        if (oldValue<newValue){
            card[newValue-1].value = View.VISIBLE
        }
        else{
            card[oldValue-1].value = View.INVISIBLE
        }
    }

    private val eRes1 = Resource()
    private val eRes2 = Resource()
    private val eRes3 = Resource()
    private val eRes4 = Resource()
    val eRes = mutableListOf<Resource>()

    val card = mutableListOf<MutableLiveData<Int>>()

    private val _card1 = MutableLiveData<Int>(View.INVISIBLE)
    val card1: LiveData<Int>
        get() = _card1

    private val _card2 = MutableLiveData<Int>(View.INVISIBLE)
    val card2: LiveData<Int>
        get() = _card2

    private val _card3 = MutableLiveData<Int>(View.INVISIBLE)
    val card3: LiveData<Int>
        get() = _card3

    private val _card4 = MutableLiveData<Int>(View.INVISIBLE)
    val card4: LiveData<Int>
        get() = _card4

    private val _card5 = MutableLiveData<Int>(View.INVISIBLE)
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

    fun enemyMining(a:Array<Int>){
        eRes1.minRes(a[0])
        eRes2.minRes(a[1])
        eRes3.minRes(a[2])
        eRes4.minRes(a[3])
    }


}