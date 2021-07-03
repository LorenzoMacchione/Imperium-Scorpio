package com.example.imperium_scorpio.match

import android.app.Activity
import com.example.imperium_scorpio.database.CardDAO
import com.example.imperium_scorpio.database.CardDB
import com.example.imperium_scorpio.database.Cards

class Deck(val code:String, cardDAO: CardDAO) {
    val deck = mutableListOf<Cards>()

    init {
        for (i in code.indices step 2) {
            val s = code.subSequence(i, i + 2)
            deck.add(cardDAO.getCardById(s.toString().toInt(16)))
        }
    }

    fun draw () : Cards{
        if (deck.size>0){
            val i = (0 until deck.size).random()
            val c = deck.removeAt(i)
            return c}
        val c = Cards(-1,"0",-1,-1,-1,-1,"-1",-1,-1,-1,-1)
        return c

    }

    fun takeCard(i:Int):Cards{
        val c=deck[i]
        return c
    }


}