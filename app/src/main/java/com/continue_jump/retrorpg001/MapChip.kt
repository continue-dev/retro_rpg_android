package com.continue_jump.retrorpg001

import android.graphics.Bitmap

class MapChip : BackgroundInterface {
    override var bitmap: Bitmap? = null
    override var x: Int = 0
    override var y: Int = 0

    constructor(_bitmap: Bitmap, _x: Int, _y: Int) {
        bitmap = _bitmap
        x = _x
        y = _y
    }
}