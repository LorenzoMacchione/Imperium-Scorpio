package com.example.imperium_scorpio

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDAO {
    @Query("SELECT*FROM Cards")
    fun getAll():Array<Cards>

    @Insert
    fun insert(vararg cards: Cards)

    @Query("SELECT*FROM Cards WHERE id=:id")
    fun getCardById(id: Int): Cards
}