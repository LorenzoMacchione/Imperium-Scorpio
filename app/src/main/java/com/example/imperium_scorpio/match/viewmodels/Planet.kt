/**
 * Classe che estende SmallCard per aggiungere le propietà e i metodi dei pianeti
 */

package com.example.imperium_scorpio.match.viewmodels

import android.content.Context
import com.example.imperium_scorpio.database.Cards


class Planet(val position: Int, val danger: Int, val res1:Boolean, val res2:Boolean,
             val res3:Boolean, val res4:Boolean,context: Context, var controlled: Boolean = false): SmallCard(context) {

    val range1 = mutableListOf<Int>()
    var rotation =0;


    val pRes = mutableListOf<Boolean>()

    init{
        pRes.add(res1)
        pRes.add(res2)
        pRes.add(res3)
        pRes.add(res4)

        when (position) {
            0 -> {
                range1.add(1)
                range1.add(2)
            }

            1 -> {
                range1.add(0)
                range1.add(2)
                range1.add(3)
                range1.add(4)
            }

            2 -> {
                range1.add(0)
                range1.add(1)
                range1.add(5)
                range1.add(4)
            }

            3 -> {
                range1.add(6)
                range1.add(1)
                range1.add(4)
            }

            4 -> {
                range1.add(1)
                range1.add(2)
                range1.add(3)
                range1.add(5)
                range1.add(6)
                range1.add(7)
            }

            5 -> {
                range1.add(7)
                range1.add(2)
                range1.add(4)
            }

            6 -> {
                range1.add(8)
                range1.add(7)
                range1.add(3)
                range1.add(4)
            }

            7 -> {
                range1.add(8)
                range1.add(6)
                range1.add(5)
                range1.add(4)
            }

            8 -> {
                range1.add(7)
                range1.add(6)

            }
        }

    }



    fun takeRes(): Array<Int> {

        val IA = arrayOf(0, 0, 0, 0)
        if (pRes[0]) IA[0] = card.mining
        if (pRes[1]) IA[1] = card.mining
        if (pRes[2]) IA[2] = card.mining
        if (pRes[3]) IA[3] = card.mining
        takeDamage(danger)

        return IA
    }

    fun getRange(i: Int): MutableList<Int> {
        return range1

    }

    override fun blank(){
        controlled = false
        newCard( Cards(-1,"0",-1,-1,-1,-1,"-1",-1,-1,-1,-1))
        rotation=0
    }


    fun moveTo(c: SmallCard){
        if (!controlled)
        {
            newCard(c.card)
            _attack.value = c.attack.value
            _hp.value = c.hp.value
            _mining.value = c.mining.value
            _r1.value = c.r1.value
            _r2.value = c.r2.value
            _r3.value = c.r3.value
            _r4.value = c.r4.value
            rotation = 180*(c.card.player+(c.card.player%2))
            controlled=true
        }
    }

    fun moveTo(c: Cards){
        if (!controlled)
        {
            newCard(c)
            rotation = 180*(c.player+(c.player%2))

            controlled=true
        }
    }

    fun moveFrom():SmallCard{
        val c = copy()
        blank()
        return c
    }
}