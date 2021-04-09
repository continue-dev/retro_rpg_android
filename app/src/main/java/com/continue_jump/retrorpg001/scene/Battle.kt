package com.continue_jump.retrorpg001.scene

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.widget.Button
import com.continue_jump.retrorpg001.Element
import com.continue_jump.retrorpg001.Words

class Battle {

    var words : Words? = null
    var bitmap : Bitmap? = null

    var ren : Element? = null
    var sara : Element? = null
    var police : Element? = null
    var textFrame : Element? = null

    var button1 : Button? = null
    var button2 : Button? = null
    var button3 : Button? = null

    var neutralFlag : Boolean = true

    var battleFlag : Boolean = false
    var attackFlag : Boolean = false
    var damageFlag : Boolean = false


    var idleCharacter : Int = -1

    constructor() {

    }

    fun changeButton() {
        button1?.visibility = Button.VISIBLE
        button2?.visibility = Button.VISIBLE
        button3?.visibility = Button.VISIBLE

        button1?.text = "戦う"
        button2?.text = "考える"
        button3?.text = "逃げる"


        button1?.setOnClickListener {
            if (battleFlag) {
                attackFlag = true
            }
            if (neutralFlag) {
                button1?.text = "攻撃"
                button2?.text = "魔法"
                button3?.text = "防御"
                battleFlag = true
                neutralFlag = false
                idleCharacter = 0
            }
        }

    }

    fun draw(canvas: Canvas, paint: Paint) {

        if (battleFlag) {
            if (idleCharacter == 0) {
                if (attackFlag) {
                    damageFlag = ren?.attack()!!
                } else {
                    ren?.idle()
                }
            }
            if (idleCharacter == 1) {
                if (attackFlag) {
                    damageFlag = sara?.attack()!!
                } else {
                    sara?.idle()
                }
            }

            if (damageFlag == true) {
                damageFlag = police?.damage()!!

                if (damageFlag == false) {
                    attackFlag = false
                    ren?.attackCount = 0
                    ren?.attackTime = 0
                    sara?.attackCount = 0
                    sara?.attackTime = 0
                    police?.damageCount = 0
                    police?.damageTime = 0

                    idleCharacter = (idleCharacter + 1) % 2
                }
            }
        }
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
        if (ren?.visible == true) {
            canvas.drawBitmap(
                ren?.bitmap!!,
                Rect(0, 0, 512, 512),
                Rect(
                    ren?.x!!.toInt(),
                    ren?.y!!.toInt(),
                    ren?.x!!.toInt() + 256 * 3,
                    ren?.y!!.toInt() + 256 * 3
                ),
                paint
            )
        }
        if (sara?.visible == true) {
            canvas.drawBitmap(
                sara?.bitmap!!,
                Rect(0, 0, 512, 512),
                Rect(
                    sara?.x!!.toInt(),
                    sara?.y!!.toInt(),
                    sara?.x!!.toInt() + 256 * 3,
                    sara?.y!!.toInt() + 256 * 3
                ),
                paint
            )
        }
        if (police?.visible == true) {
            canvas.drawBitmap(
                police?.bitmap!!,
                Rect(0, 0, 256, 256),
                Rect(
                    police?.x!!.toInt(),
                    police?.y!!.toInt(),
                    police?.x!!.toInt() + 256 * 4,
                    police?.y!!.toInt() + 256 * 4
                ),
                paint
            )
        }

//        canvas.drawBitmap(
//                messageCharacter!!,
//                Rect(128 - 16, 0, 256 - 16, 128),
//                Rect(textFrame?.x!!.toInt() - 512, textFrame?.y!!.toInt() - 256 + 64, textFrame?.x!!.toInt() + 128 * 5 - 512 - 128 - 64, textFrame?.y!!.toInt() + 128 * 5 - 256),
//                paint
//        )

    }

}