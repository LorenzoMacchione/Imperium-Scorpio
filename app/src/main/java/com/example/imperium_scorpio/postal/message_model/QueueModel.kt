package com.example.imperium_scorpio.postal.message_model

class QueueModel {
    var user:String?=null
    var gameId:Int?=null


    constructor()

    constructor(user:String?, gameId:Int?){
        this.user=user
        this.gameId=gameId
    }
}