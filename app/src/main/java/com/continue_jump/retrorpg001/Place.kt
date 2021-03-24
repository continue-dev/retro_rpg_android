package com.continue_jump.retrorpg001

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.widget.Button

class Place {

    var ren : Element? = null
    var sara : Element? = null
    var toushu : Element? = null

    var police : Element? = null

    var place : Element? = null
    var messageCharacter : Bitmap? = null
    var byouinBitmap : Bitmap? = null
    var butsumaBitmap : Bitmap? = null
    var textFrame : Element? = null

    var battleBitmap : Bitmap? = null


    constructor() {

    }

    fun nextPlace(textNumber : Int) {
        if (textNumber == 1) {
            ren?.x = 1024.0f + 128.0f
            ren?.y = 64.0f
            sara?.x = 1024.0f - 64.0f
            sara?.y = 128.0f
            place?.bitmap = byouinBitmap!!
            messageCharacter = toushu?.bitmap
        } else if (textNumber == 2) {
            messageCharacter = ren?.bitmap
        } else if (textNumber == 3) {
            messageCharacter = sara?.bitmap
        } else if (textNumber == 4) {
            messageCharacter = toushu?.bitmap
        } else if (textNumber == 8) {
            messageCharacter = ren?.bitmap
        } else if (textNumber == 9) {
            messageCharacter = toushu?.bitmap
        } else if (textNumber == 10) {
            messageCharacter = ren?.bitmap
            place?.bitmap = butsumaBitmap!!
            ren?.x = 1024.0f
            ren?.y = 128.0f
            sara?.x = 512.0f
            sara?.y = 128.0f
        } else if (textNumber == 11) {
            messageCharacter = sara?.bitmap
        } else if (textNumber == 12) {
            messageCharacter = ren?.bitmap
            place?.bitmap = battleBitmap!!
            ren?.x = 1200.0f
            sara?.x = 900.0f

        }
    }

    fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawBitmap(
                place?.bitmap!!,
                Rect(0, 0, 512 * 8, 512 * 4),
                Rect(place?.x!!.toInt(), place?.y!!.toInt(), place?.x!!.toInt() + 256 * 26, place?.y!!.toInt() + 256 * 13),
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

        if (place?.bitmap == battleBitmap) {
            canvas.drawBitmap(
                    police?.bitmap!!,
                    Rect(0, 0, 256, 256),
                    Rect(police?.x!!.toInt(), police?.y!!.toInt(), police?.x!!.toInt() + 256 * 4, police?.y!!.toInt() + 256 * 4),
                    paint
            )

        }
        if (place?.bitmap == byouinBitmap) {
            canvas.drawBitmap(
                    toushu?.bitmap!!,
                    Rect(0, 0, 512, 512),
                    Rect(toushu?.x!!.toInt(), toushu?.y!!.toInt(), toushu?.x!!.toInt() + 256 * 3, toushu?.y!!.toInt() + 256 * 3),
                    paint
            )
        }
        canvas.drawBitmap(
                messageCharacter!!,
                Rect(128 - 16, 0, 256 - 16, 128),
                Rect(textFrame?.x!!.toInt() - 512, textFrame?.y!!.toInt() - 256 + 64, textFrame?.x!!.toInt() + 128 * 5 - 512 - 128 - 64, textFrame?.y!!.toInt() + 128 * 5 - 256),
                paint
        )

    }
}