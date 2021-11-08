/**
 * Classe che permette di lavorare su una coppia della carta mantenendo l'informazione originaria della carta
 */

package com.example.imperium_scorpio.match.viewmodels

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imperium_scorpio.database.Cards
import kotlin.properties.Delegates

open class SmallCard(val context: Context) {

     lateinit var card: Cards

    private val _img= MutableLiveData<Drawable>()
    val img: LiveData<Drawable>
        get() = _img

    protected val _attack= MutableLiveData<Int>(0)
    val attack: LiveData<Int>
            get() = _attack

    protected val _hp= MutableLiveData<Int>(0)
    val hp: LiveData<Int>
        get() = _hp

    protected val _mining= MutableLiveData<Int>(0)
    val mining: LiveData<Int>
        get() = _mining

    protected val _r1= MutableLiveData<Int>(-1)
    val r1: LiveData<Int>
        get() = _r1

    protected val _r2= MutableLiveData<Int>(0)
    val r2: LiveData<Int>
        get() = _r2

    protected val _r3= MutableLiveData<Int>(0)
    val r3: LiveData<Int>
        get() = _r3

    protected val _r4= MutableLiveData<Int>(0)
    val r4: LiveData<Int>
        get() = _r4

    private val _visibility = MutableLiveData<Int>(0)
    val visibility: LiveData<Int>
        get() = _visibility

    var id: Int by Delegates.observable(0){ property, oldValue, newValue ->
        if (newValue==-1){
            off()
        }else{
            on()
        }
    }

    init {
        blank()
    }

    fun read():Cards{
        return card
    }

    fun takeDamage(i: Int): Char{
        _hp.value = _hp.value?.minus(i)
        if (_hp.value!! <= 0) blank()
        if (_hp.value!! <card.hp) return '<'
        if (_hp.value!! >card.hp) return '>'
        return '='

    }



    fun heal(i: Int): Char{
        _hp.value = _hp.value?.plus(i)
        if (_hp.value!! >=card.hp){
            _hp.value = card.hp
            return '='
        }
        return '<'
    }

    fun off(){
        _visibility.value = View.INVISIBLE
    }

    fun on (){
        _visibility.value = View.VISIBLE
    }

    fun newCard(c:Cards){
        this.card=c
            id = c.id
            _attack.value = c.attack
            _hp.value = c.hp
            _mining.value = c.mining
            _r1.value = c.res1
            _r2.value = c.res2
            _r3.value = c.res3
            _r4.value = c.res4
        if(c.id!=-1){
            val resId: Int = context.resources.getIdentifier("card_"+c.id, "drawable", context.packageName)
                _img.value = context.getDrawable(resId)
        }
    }

    fun copy():SmallCard{
        val s = SmallCard(context)
        s.newCard(this.card)
        s._attack.value = this.attack.value
        s._hp.value = this.hp.value
        s._mining.value = this.mining.value
        s._r1.value = this.r1.value
        s._r2.value = this.r2.value
        s._r3.value = this.r3.value
        s._r4.value = this.r4.value
        return s
    }

    open fun blank(){
        newCard( Cards(-1,"0",-1,-1,-1,-1,"-1",-1,-1,-1,-1))
    }
}

