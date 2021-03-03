package com.continue_jump.retrorpg001

import android.graphics.Bitmap

class Element {
    var bitmap : Bitmap
    var x : Float = 0.0f
    var y : Float = 0.0f

    constructor(_bitmap: Bitmap, _x: Float, _y: Float) {
        bitmap = _bitmap
        x = _x
        y = _y
    }
}