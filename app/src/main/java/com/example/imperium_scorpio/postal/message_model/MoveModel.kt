package com.example.imperium_scorpio.postal.message_model

class MoveModel {
    var start:Int?=null
    var end:Int?=null


    constructor()

    constructor(start:Int?, end:Int?){
        this.start=start
        this.end=end
    }
}