package com.example.imperium_scorpio.match.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Resource {
    private val _res = MutableLiveData<Int>(5)
            val res: LiveData<Int>
            get() = _res

    fun useRes (i: Int=1){
        _res.value = _res.value?.minus(i)
    }

    fun minRes (i: Int=1){
        _res.value = _res.value?.plus(i)
    }

    fun read ():Int{
        return _res.value!!
    }

}