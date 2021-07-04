package com.example.imperium_scorpio.postal.message_model

class MsgModel {
    var type: String? =null
    var msg: String? =null

    constructor()

    constructor(msg:String?,type:String?){

        this.msg = msg
        this.type = type


    }
}