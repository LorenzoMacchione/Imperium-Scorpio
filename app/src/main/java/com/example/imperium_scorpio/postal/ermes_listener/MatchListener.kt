package com.example.imperium_scorpio.postal.ermes_listener

import com.example.imperium_scorpio.database.CardDAO
import com.example.imperium_scorpio.match.MatchViewModel
import com.example.imperium_scorpio.postal.Ermes
import com.example.imperium_scorpio.postal.message_model.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class MatchListener(val mvm: MatchViewModel, val cardDAO: CardDAO, val ermes: Ermes) : ChildEventListener {

    private var move = ""


    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        if (move.equals("")) {
            move = snapshot.value.toString()
        }else{
            when (move){
                "moveModel"->{
                    val msg = snapshot.getValue(MoveModel::class.java)
                    mvm.planets[msg?.end!!].moveTo(mvm.planets[msg?.start!!].moveFrom())

                    if (msg.end==0){
                        mvm.enemy.win=2
                    }
                    mvm.turn.unlock()
                }
                "attackModel"->{
                    val msg = snapshot.getValue(AttackModel::class.java)
                    val defenderAtk = mvm.planets[msg?.defender!!].attack.value
                    mvm.planets[msg?.defender!!].takeDamage(mvm.planets[msg?.striker!!].attack.value!!)
                    mvm.planets[msg?.striker!!].takeDamage(defenderAtk!!)
                    mvm.turn.unlock()
                }
                "drawModel"->{
                    val msg = snapshot.getValue(DrawModel::class.java)
                    mvm.enemy.eRes[msg?.res!!].useRes()
                    mvm.enemy.draw()
                    mvm.turn.unlock()
                }
                "miningModel"->{
                    val msg = snapshot.getValue(MiningModel::class.java)
                    mvm.enemy.enemyMining(mvm.planets[msg?.planet!!].takeRes())
                    mvm.turn.unlock()
                }
                "playCardModel"->{
                    val msg = snapshot.getValue(PlayCardModel::class.java)
                    val c = cardDAO.getCardById(msg?.card!!)
                    c.player = 1
                    mvm.planets[msg?.planet!!].moveTo(c)
                    mvm.enemy.playCard(c)
                    mvm.turn.unlock()
                }
                "randomModel"->{
                    val msg = snapshot.getValue(RandomModel::class.java)
                    if (msg?.value!! < ermes.initiative) mvm.turn.unlock()
                    if (msg?.value!! > ermes.initiative) mvm.turn.lock()
                    if (msg?.value!! == ermes.initiative) ermes.randomModel()
                }
            }
            move = ""
        }
    }

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        ermes.clearGame()

    }

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }

}
