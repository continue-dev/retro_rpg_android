package com.continue_jump.retrorpg001

import android.graphics.Bitmap

class Element {
    var bitmap : Bitmap
    public var x : Float = 0.0f
    public var y : Float = 0.0f

    var visible : Boolean = true
    var damageCount : Int = 0
    var damageTime : Int = 0
    val DAMAGE_TIME : Int = 1

    var attackCount : Int = 0
    var attackSpeed : Float = -8.0f
    var attackTime : Int = 0
    val ATTACK_TIME : Int = 10


    var idleSpeed : Float = 4.0f
    var idleTime : Int = 0
    val IDLE_TIME : Int = 10

    constructor(_bitmap: Bitmap, _x: Float, _y: Float) {
        bitmap = _bitmap
        x = _x
        y = _y
    }

    fun damage(): Boolean {
        if (damageCount < 16) {
            damageTime += 1
            if (damageTime >= DAMAGE_TIME) {
                damageTime = 0
                if (damageCount % 2 == 0) {
                    visible = false
                } else {
                    visible = true
                }
                damageCount += 1
            }
            return true
        } else {
            return false
        }
    }
    fun attack(): Boolean {
        if (attackCount < 2) {
            attackTime += 1
            if (attackTime >= ATTACK_TIME) {
                attackSpeed *= -1.0f
                attackTime = 0
                attackCount += 1
            }
            x += attackSpeed
            return false
        } else {
            return true
        }
    }
    fun idle() {
        idleTime += 1
        if (idleTime >= IDLE_TIME) {
            idleSpeed *= -1.0f
            idleTime = 0
        }
        y += idleSpeed
    }

}