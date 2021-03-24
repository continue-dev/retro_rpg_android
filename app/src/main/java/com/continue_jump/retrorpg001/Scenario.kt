package com.continue_jump.retrorpg001

import android.graphics.Canvas
import android.graphics.Paint
import android.widget.Button

class Scenario {

    var place : Place = Place()
    var words : Words? = null
    var textNumber : Int = 0

    var button1 : Button? = null
    var button2 : Button? = null
    var button3 : Button? = null

    constructor() {

    }

    fun changeButton() {
        button1?.visibility = Button.VISIBLE
        button2?.visibility = Button.VISIBLE
        button3?.visibility = Button.VISIBLE

        button1?.text = "話す"
        button2?.text = "次へ"
        button3?.text = "戻る"

    }

    fun draw(canvas: Canvas, paint: Paint) {



        place.draw(canvas, paint)

    }

}