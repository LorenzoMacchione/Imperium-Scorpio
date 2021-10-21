package com.example.imperium_scorpio

open class Lock {
    private var unlocked=true

    open fun lock (){
        unlocked=false
    }

    fun unlock (){
        unlocked=true
    }

    fun read (): Boolean {
        return unlocked
    }
}