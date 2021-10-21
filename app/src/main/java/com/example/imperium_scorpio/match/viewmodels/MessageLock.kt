package com.example.imperium_scorpio.match.viewmodels

import com.example.imperium_scorpio.Lock

class MessageLock(val msgLock: String, val msgUnLock: String) : Lock() {
    var msg = ""

    override fun lock() {
        super.lock()
        msg=msgLock
    }

    override fun unlock(){
        super.unlock()
        msg=msgUnLock
    }

}