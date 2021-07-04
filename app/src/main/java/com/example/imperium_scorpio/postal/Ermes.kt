package com.example.imperium_scorpio.postal

import com.example.imperium_scorpio.database.CardDAO
import com.example.imperium_scorpio.home.Waiting_Room
import com.example.imperium_scorpio.match.MatchViewModel
import com.example.imperium_scorpio.postal.ermes_listener.MatchListener
import com.example.imperium_scorpio.postal.ermes_listener.WaitingRoomListener
import com.example.imperium_scorpio.postal.message_model.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Ermes(val cardDAO: CardDAO?=null){

    private val dbRoot = FirebaseDatabase.getInstance("https://imperium-scorpio-default-rtdb.europe-west1.firebasedatabase.app/")
    private lateinit var dbPlayer: DatabaseReference

    fun readyToPlay(user:String, wait:Waiting_Room){
        val dbWaiting = dbRoot.reference.child("wait")

        dbWaiting.addChildEventListener(WaitingRoomListener(wait))
        dbWaiting.push().setValue(QueueModel(user,0))
    }

    fun setGame(player:Int, mvm:MatchViewModel){
        dbPlayer = dbRoot.reference.child("game/$player")
        if(player==1) dbRoot.reference.child("game/2").addChildEventListener(MatchListener(mvm,cardDAO!!))
        if(player==2) dbRoot.reference.child("game/1").addChildEventListener(MatchListener(mvm,cardDAO!!))
    }

    fun playCard(card:Int, planet:Int){
        dbPlayer.push().setValue("playCardModel")
        dbPlayer.push().setValue(PlayCardModel(card, Math.abs(planet-8)))
    }

    fun attackMsg(striker:Int, defender:Int){
        dbPlayer.push().setValue("attackModel")
        dbPlayer.push().setValue(AttackModel(Math.abs(striker-8), Math.abs(defender-8)))
    }

    fun move(start:Int, end:Int){
        dbPlayer.push().setValue("moveModel")
        dbPlayer.push().setValue(MoveModel(Math.abs(start-8), Math.abs(end-8)))
    }

    fun mining(planet:Int){
        dbPlayer.push().setValue("miningModel")
        dbPlayer.push().setValue(MiningModel(Math.abs(planet-8)))
    }

    fun drawMsg(res:Int){
        dbPlayer.push().setValue("drawModel")
        dbPlayer.push().setValue(DrawModel(res))
    }
}