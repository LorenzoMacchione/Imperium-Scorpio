package com.example.imperium_scorpio.match.viewmodels
import com.example.imperium_scorpio.database.Cards

class Hand{

    //Carte del giocatore
    val HC1_model = SmallCard()
    val HC2_model = SmallCard()
    val HC3_model = SmallCard()
    val HC4_model = SmallCard()
    val HC5_model = SmallCard()
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

    fun Randomtoss(){
        if (size()>0){
            val i = (0 until size()).random()
            hand.removeAt(i)
            refresh()}
    }




    fun refresh (){
        val m = mutableListOf<Cards>()
        for (c in hand) {
            if (c.r1.value != -1) {
                m.add(c.card)
            }
            var i = 0
            for (c in m) {
                hand[i].newCard(c)
                i++
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

    fun TakeCard(i: Int) :Cards {
        val c = hand[i].read()
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