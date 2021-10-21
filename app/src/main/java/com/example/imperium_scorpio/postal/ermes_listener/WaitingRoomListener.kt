package com.example.imperium_scorpio.postal.ermes_listener

import com.example.imperium_scorpio.home.Waiting_Room
import com.example.imperium_scorpio.postal.message_model.QueueModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class WaitingRoomListener(val wait: Waiting_Room, val userName: String): ChildEventListener {
    private val dbRoot = FirebaseDatabase.getInstance("https://imperium-scorpio-default-rtdb.europe-west1.firebasedatabase.app/")



    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        val msg = snapshot.getValue(QueueModel::class.java)
        val dbWaiting = dbRoot.reference.child("wait")


        if(!msg!!.user.equals(userName)) {

            dbWaiting.removeValue()
            dbWaiting.removeEventListener(this)
            wait.startGame(msg.user)
        }

    }

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        val dbWaiting = dbRoot.reference.child("wait")
        dbWaiting.removeEventListener(this)
    }

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
}