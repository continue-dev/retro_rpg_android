package com.continue_jump.retrorpg001.scene

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.*
import android.widget.Button
import android.widget.TextView
import com.continue_jump.retrorpg001.CharacterInterface
import com.continue_jump.retrorpg001.R

class Quest : SceneInterface {

    override var assetManager : AssetManager
    override var resource : Resources
    override var background : Bitmap
    override var characters : Array<CharacterInterface?> = arrayOfNulls(0)

    override var button1: Button? = null
    override var button2: Button? = null
    override var button3: Button? = null

    override var returnSceneNumber: Int = 0

    var tempCounter: Int = 0
    constructor(asset: AssetManager, res: Resources) {
        assetManager = asset
        resource = res
        background = BitmapFactory.decodeResource(resource, R.drawable.quest)
    }

    override fun setViews(textView: TextView,
                          button_1: Button,
                          button_2: Button,
                          button_3: Button) {
        button1 = button_1
        button2 = button_2
        button3 = button_3

        changeButton()
    }
    override fun changeButton() {
        button1?.visibility = Button.VISIBLE
        button2?.visibility = Button.VISIBLE
        button3?.visibility = Button.VISIBLE

        button1?.text = "進む"
        button2?.text = "曲がる"
        button3?.text = "戻る"

        button1?.setOnClickListener {
            tempCounter += 1
            if (tempCounter >= 5) {
                returnSceneNumber = 5
            }
        }
        button2?.setOnClickListener {
        }

    }




    override fun draw(canvas: Canvas, paint: Paint) : Int {
        if (background != null) {
            canvas.drawBitmap(
                background,
                Rect(0, 0, 512 * 8, 512 * 4),
                Rect(0, 0, 256 * 26, 256 * 13),
                paint
            )
        }
        return returnSceneNumber
    }
}