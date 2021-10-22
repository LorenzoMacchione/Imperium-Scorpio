package com.example.imperium_scorpio.match.viewmodels

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imperium_scorpio.Lock

class MessageLock(private val msgLock: String, private val msgUnLock: String) : Lock() {

    private val _msg= MutableLiveData<String>("")
    val msg: LiveData<String>
        get() = _msg

    private val _color= MutableLiveData<Int>(0)
    val color:LiveData<Int>
        get()=_color

    override fun lock() {
        super.lock()
        _msg.value=msgLock
        _color.value= Color.RED
    }

    override fun unlock(){
        super.unlock()
        _msg.value=msgUnLock
        _color.value= Color.CYAN
    }

}