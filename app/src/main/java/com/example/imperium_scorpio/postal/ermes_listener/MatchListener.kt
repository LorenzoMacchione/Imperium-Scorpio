package com.example.imperium_scorpio.postal.ermes_listener

import com.example.imperium_scorpio.database.CardDAO
import com.example.imperium_scorpio.match.MatchViewModel
import com.example.imperium_scorpio.postal.message_model.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class MatchListener(val mvm: MatchViewModel, val cardDAO: CardDAO) : ChildEventListener {

    private var move = ""

    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        if (move.equals("")) {
            move = snapshot.value.toString()
        }else{
            when (move){
                "moveModel"->{
                    val msg = snapshot.getValue(MoveModel::class.java)
                    mvm.planets[msg?.end!!].moveTo(mvm.planets[msg?.start!!].moveFrom())
                }
                "attackModel"->{
                    val msg = snapshot.getValue(AttackModel::class.java)
                    mvm.planets[msg?.striker!!].takeDamage(mvm.planets[msg?.defender!!].attack.value!!)
                    if (mvm.planets[msg?.defender!!].id!=-1)mvm.planets[msg?.defender!!].takeDamage(mvm.planets[msg?.striker!!].attack.value!!)
                }
                "drawModel"->{
                    val msg = snapshot.getValue(DrawModel::class.java)
                    mvm.enemy.eRes[msg?.res!!].useRes()
                    mvm.enemy.draw()
                }
                "miningModel"->{
                    val msg = snapshot.getValue(MiningModel::class.java)
                    mvm.enemy.enemyMining(mvm.planets[msg?.planet!!].takeRes())
                }
                "playCardModel"->{
                    val msg = snapshot.getValue(PlayCardModel::class.java)
                    val c = cardDAO.getCardById(msg?.card!!)
                    c.player = 1
                    mvm.planets[msg?.planet!!].moveTo(c)
                }
            }
            move = ""
        }
    }

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        TODO("Not yet implemented")
    }

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }

}
