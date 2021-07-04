package com.example.imperium_scorpio.postal.message_model

class PlayCardModel {
    var card:Int?=null
    var planet:Int?=null


    constructor()

    constructor(card:Int?, planet:Int?){
        this.card=card
        this.planet=planet
    }
}