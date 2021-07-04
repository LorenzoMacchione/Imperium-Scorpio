package com.example.imperium_scorpio.postal

import com.example.imperium_scorpio.Lock
import com.example.imperium_scorpio.postal.message_model.QueueModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class Ermes(val lock: Lock) :ChildEventListener{

    private val dbRoot = FirebaseDatabase.getInstance("https://imperium-scorpio-default-rtdb.europe-west1.firebasedatabase.app/")

    fun readyToPlay(user:String): Boolean {
        val dbWaiting = dbRoot.reference.child("wait")
        val someOne= dbWaiting.get()
        if (someOne!=null) {
            val msg=QueueModel(user, "ready")
            dbWaiting.push().setValue(msg)
            return true
        }
        val msg=QueueModel(user, "wait")
        dbWaiting.push().setValue(msg)
        dbWaiting.addChildEventListener(this)
        return false
    }


    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        val msg = snapshot.getValue(QueueModel::class.java)
        val dbWaiting = dbRoot.reference.child("wait")
        dbWaiting.removeValue()
        dbWaiting.removeEventListener(this)
        lock.unlock()

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