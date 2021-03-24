package com.continue_jump.retrorpg001

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

class Title {

    var bitmap : Bitmap? = null

    constructor() {}

    fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawBitmap(
                bitmap!!,
                Rect(0, 0, 512 * 8, 512 * 4),
                Rect(0, 0, 256 * 26,256 * 13),
                paint
        )

    }
}