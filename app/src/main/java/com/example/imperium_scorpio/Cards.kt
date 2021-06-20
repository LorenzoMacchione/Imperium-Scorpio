package com.example.imperium_scorpio

class Cards {

    var name : String = ""
    var faction : String = ""
    var ind1 : Int = 0
    var ind2 : Int = 0
    var ind3 : Int = 0

    constructor(name:String, faction:String, ind1:Int, ind2:Int, ind3:Int){
        this.name=name
        this.faction=faction
        this.ind1=ind1
        this.ind2=ind2
        this.ind3=ind3
    }
}