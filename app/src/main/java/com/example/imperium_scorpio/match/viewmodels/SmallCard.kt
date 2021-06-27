package com.example.imperium_scorpio.match.viewmodels

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imperium_scorpio.database.Cards

class SmallCard : ViewModel() {

    private val _attack= MutableLiveData<Int>(0)
    val attack: LiveData<Int>
            get() = _attack

    private val _hp= MutableLiveData<Int>(0)
    val hp: LiveData<Int>
        get() = _hp

    private val _mining= MutableLiveData<Int>(0)
    val mining: LiveData<Int>
        get() = _mining


    private val _r1= MutableLiveData<Int>(-1)
    val r1: LiveData<Int>
        get() = _r1

    private val _r2= MutableLiveData<Int>(0)
    val r2: LiveData<Int>
        get() = _r2

    private val _r3= MutableLiveData<Int>(0)
    val r3: LiveData<Int>
        get() = _r3

    private val _r4= MutableLiveData<Int>(0)
    val r4: LiveData<Int>
        get() = _r4


    fun newCard(c:Cards){
        _attack.value = c.attack
        _hp.value = c.hp
        _mining.value = c.mining
        _r1.value = c.res1
        _r2.value = c.res2
        _r3.value = c.res3
        _r4.value = c.res4

    }
}