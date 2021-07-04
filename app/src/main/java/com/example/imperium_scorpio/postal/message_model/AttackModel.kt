package com.example.imperium_scorpio.postal.message_model

class AttackModel {
    var striker:Int?=null
    var defender:Int?=null


    constructor()

    constructor(striker:Int?, defender:Int?){
        this.striker=striker
        this.defender=defender
    }
}