package com.continue_jump.retrorpg001

import android.graphics.Bitmap

interface CharacterInterface {
    var bitmap : Bitmap
    var x : Float
    var y : Float

    var visible : Boolean
    var damageCount : Int
    var damageTime : Int
    val DAMAGE_TIME : Int

    var attackCount : Int
    var attackSpeed : Float
    var attackTime : Int
    val ATTACK_TIME : Int


    var idleSpeed : Float
    var idleTime : Int
    val IDLE_TIME : Int

    fun damage(): Boolean {
        return false
    }
    fun attack(): Boolean {
        return false
    }
    fun idle() {
    }

}