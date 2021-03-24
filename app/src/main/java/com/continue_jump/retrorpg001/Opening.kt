package com.continue_jump.retrorpg001

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.widget.Button

class Opening {

    var bitmap : Bitmap? = null

    var button1 : Button? = null
    var button2 : Button? = null
    var button3 : Button? = null

    constructor() {

    }

    fun changeButton() {
        button1?.visibility = Button.INVISIBLE
        button2?.visibility = Button.INVISIBLE
        button3?.visibility = Button.INVISIBLE
    }

    fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawBitmap(
                bitmap!!,
                Rect(0, 0, 512 * 8, 512 * 4),
                Rect(0, 0, 256 * 26,256 * 13),
                paint
        )

    }

}