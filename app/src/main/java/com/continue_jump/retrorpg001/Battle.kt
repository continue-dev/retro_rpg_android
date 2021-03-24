package com.continue_jump.retrorpg001

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.widget.Button

class Battle {

    var bitmap : Bitmap? = null

    var ren : Element? = null
    var sara : Element? = null
    var police : Element? = null
    var textFrame : Element? = null

    var button1 : Button? = null
    var button2 : Button? = null
    var button3 : Button? = null

    constructor() {

    }

    fun changeButton() {
        button1?.visibility = Button.VISIBLE
        button2?.visibility = Button.VISIBLE
        button3?.visibility = Button.VISIBLE

        button1?.text = "戦う"
        button2?.text = "考える"
        button3?.text = "逃げる"

    }

    fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawBitmap(
                bitmap!!,
                Rect(0, 0, 512 * 8, 512 * 4),
                Rect(0, 0, 256 * 26,256 * 13),
                paint
        )
        canvas.drawBitmap(
                textFrame?.bitmap!!,
                Rect(0, 0, 512 * 8, 512 * 2),
                Rect(textFrame?.x!!.toInt(), textFrame?.y!!.toInt(), textFrame?.x!!.toInt() + 256 * 10, textFrame?.y!!.toInt() + 128 * 5),
                paint
        )
        canvas.drawBitmap(
                textFrame?.bitmap!!,
                Rect(0, 0, 512 * 8, 512 * 2),
                Rect(textFrame?.x!!.toInt() - 512 + 64 + 16, textFrame?.y!!.toInt(), textFrame?.x!!.toInt() + 128 * 5 - 512 + 64 + 16, textFrame?.y!!.toInt() + 128 * 5),
                paint
        )
        canvas.drawBitmap(
                ren?.bitmap!!,
                Rect(0, 0, 512, 512),
                Rect(ren?.x!!.toInt(), ren?.y!!.toInt(), ren?.x!!.toInt() + 256 * 3, ren?.y!!.toInt() + 256 * 3),
                paint
        )

        canvas.drawBitmap(
                sara?.bitmap!!,
                Rect(0, 0, 512, 512),
                Rect(sara?.x!!.toInt(), sara?.y!!.toInt(), sara?.x!!.toInt() + 256 * 3, sara?.y!!.toInt() + 256 * 3),
                paint
        )

        canvas.drawBitmap(
                police?.bitmap!!,
                Rect(0, 0, 256, 256),
                Rect(police?.x!!.toInt(), police?.y!!.toInt(), police?.x!!.toInt() + 256 * 4, police?.y!!.toInt() + 256 * 4),
                paint
        )


//        canvas.drawBitmap(
//                messageCharacter!!,
//                Rect(128 - 16, 0, 256 - 16, 128),
//                Rect(textFrame?.x!!.toInt() - 512, textFrame?.y!!.toInt() - 256 + 64, textFrame?.x!!.toInt() + 128 * 5 - 512 - 128 - 64, textFrame?.y!!.toInt() + 128 * 5 - 256),
//                paint
//        )

    }

}