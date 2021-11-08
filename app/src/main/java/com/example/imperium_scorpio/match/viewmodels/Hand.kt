/**
 * Classe per la gestione della mano del giocatore
 */

package com.example.imperium_scorpio.match.viewmodels
import android.content.Context
import com.example.imperium_scorpio.database.Cards

class Hand(context: Context){

    //Carte del giocatore
    val HC1_model = SmallCard(context)
    val HC2_model = SmallCard(context)
    val HC3_model = SmallCard(context)
    val HC4_model = SmallCard(context)
    val HC5_model = SmallCard(context)
    val hand = mutableListOf<SmallCard>()



    init {
        hand.add(HC1_model)
        hand.add(HC2_model)
        hand.add(HC3_model)
        hand.add(HC4_model)
        hand.add(HC5_model)
    }

    fun toss(i : Int){

        if (size()>i) {
            hand[i].blank()
            refresh()
        }
    }

    fun randomtoss(){
        if (size()>0){
            val i = (0 until size()).random()
            hand.removeAt(i)
            refresh()}
    }




    private fun refresh (){
        val m = mutableListOf<Cards>()
        for (c in hand) {
            if (c.r1.value != -1) {
                m.add(c.card)
            }
            for ((i, c) in m.withIndex()) {
                hand[i].newCard(c)
            }

        }

        for(i in 4 downTo  m.size){
            hand[i].blank()
        }

    }

    fun addCard (c: Cards){
        if (c.id!=-1){
            if (size()<5) {hand.last().newCard(c)
                refresh()}

        }
    }

    fun read(i: Int): Cards {
        return hand[i].read()
    }

    fun takeCard(i: Int) :SmallCard {
        val c = hand[i].copy()
        hand[i].blank()
        refresh()
        return c

    }

    fun size():Int{
        var i =0
        for (c in hand){
            if (c.r1.value!=-1) i++
        }
        return i
    }


}