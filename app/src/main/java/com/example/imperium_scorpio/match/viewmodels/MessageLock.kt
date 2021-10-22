package com.example.imperium_scorpio.match.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imperium_scorpio.Lock

class MessageLock(private val msgLock: String, private val msgUnLock: String) : Lock() {

    private val _msg= MutableLiveData<String>("")
    val msg: LiveData<String>
        get() = _msg

    override fun lock() {
        super.lock()
        _msg.value=msgLock
    }

    override fun unlock(){
        super.unlock()
        _msg.value=msgUnLock
    }

}