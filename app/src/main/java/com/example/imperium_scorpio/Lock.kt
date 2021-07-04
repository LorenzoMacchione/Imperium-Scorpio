package com.example.imperium_scorpio

class Lock {
    private var locked=false

    fun lock (){
        locked=true
    }

    fun unlock (){
        locked=false
    }

    fun read (): Boolean {
        return locked
    }
}