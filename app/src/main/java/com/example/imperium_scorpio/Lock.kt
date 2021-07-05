package com.example.imperium_scorpio

class Lock {
    private var unlocked=true

    fun lock (){
        unlocked=false
    }

    fun unlock (){
        unlocked=true
    }

    fun read (): Boolean {
        return unlocked
    }
}