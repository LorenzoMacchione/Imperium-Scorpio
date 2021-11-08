/**
 * Un lock per poter gestire i turni
 */

package com.example.imperium_scorpio

open class Lock {
    private var unlocked=true

    open fun lock (){
        unlocked=false
    }

    open fun unlock (){
        unlocked=true
    }

    fun read (): Boolean {
        return unlocked
    }
}