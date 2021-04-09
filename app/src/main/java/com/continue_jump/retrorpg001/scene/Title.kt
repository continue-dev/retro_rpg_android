package com.continue_jump.retrorpg001.scene

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.*
import android.graphics.Bitmap
import android.widget.Button
import com.continue_jump.retrorpg001.CharacterInterface
import com.continue_jump.retrorpg001.R

class Title : SceneInterface {

    override var assetManager : AssetManager
    override var resource : Resources
    override var background : Bitmap
    override var characters : Array<CharacterInterface?> = arrayOfNulls(0)

    override var button1: Button? = null
    override var button2: Button? = null
    override var button3: Button? = null

    override var returnSceneNumber: Int = 0

    constructor(asset: AssetManager, res: Resources) {
        assetManager = asset
        resource = res
        background = BitmapFactory.decodeResource(resource, R.drawable.title)
    }

    override fun draw(canvas: Canvas, paint: Paint) : Int {
        canvas.drawBitmap(
                background,
                Rect(0, 0, 512 * 8, 512 * 4),
                Rect(0, 0, 256 * 26,256 * 13),
                paint
        )

        return returnSceneNumber
    }
}