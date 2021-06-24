package com.example.imperium_scorpio

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cards")

data class Cards (@PrimaryKey val id:Int, val name:String, val res1:Int, val res2:Int, val res3:Int,
                  val res4:Int, val text:String, val attack:Int, val mining:Int, val hp:Int)

