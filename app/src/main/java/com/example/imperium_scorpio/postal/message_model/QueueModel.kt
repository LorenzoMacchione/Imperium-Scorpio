package com.example.imperium_scorpio.postal.message_model

class QueueModel {
    var user:String?=null
    var status:String?=null


    constructor()

    constructor(user:String?, status:String?){
        this.user=user
        this.status=status
    }
}