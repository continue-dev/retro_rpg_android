package com.continue_jump.retrorpg001

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.*
import android.widget.Button
import android.widget.TextView
import com.continue_jump.retrorpg001.scene.SceneInterface


enum class Background {
    BUTSUMA,
    BYOUIN,
    CHANOMA,
    GENKAN,
}

enum class Character {
    REN,
    SARA,
    TOUSHU,
    GRAD,
    ARISA,
}

class Scenario : SceneInterface {

    override var assetManager : AssetManager
    override var resource : Resources
    override var background : Bitmap
    override var characters : Array<CharacterInterface?> = arrayOfNulls(0)

    override var button1: Button? = null
    override var button2: Button? = null
    override var button3: Button? = null

    var words : Words? = null
    var textNumber : Int = 0

    var textFrame : Bitmap? = null

    override var returnSceneNumber : Int = 0

    constructor(asset: AssetManager, res: Resources) {
        assetManager = asset
        resource = res
        background = BitmapFactory.decodeResource(resource, R.drawable.scene001_butsuma)

        textFrame =  BitmapFactory.decodeResource(resource, R.drawable.textframe)

    }
    override fun setViews(textView: TextView,
                 button_1: Button,
                 button_2: Button,
                 button_3: Button) {
        button1 = button_1
        button2 = button_2
        button3 = button_3
        words = Words(textView)
        words?.setAssetsManager(assetManager)

        changeButton()
    }
    override fun setScenario(scenarioNumber: Int, continueNumber: Int) {
        if (scenarioNumber == 1) {
            words?.setScenarioContinue("scene001.csv", continueNumber)
        } else if (scenarioNumber == 2) {
            words?.setScenarioContinue("scene002.csv", continueNumber)
        }
    }
    override fun changeButton() {
        words?.text?.visibility = TextView.VISIBLE
        button1?.visibility = Button.VISIBLE
        button2?.visibility = Button.VISIBLE
        button3?.visibility = Button.VISIBLE

        button1?.text = "話す"
        button2?.text = "次へ"
        button3?.text = "戻る"

        button1?.setOnClickListener {
            textNumber += 1

            val place = words?.nextSerifu()
            if (place == "Byoin") {
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_byouin)
            } else if (place == "Butsuma") {
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_butsuma)
            } else if (place == "Genkan") {
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_genkan)
            } else if (place == "Chanoma") {
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_chanoma)
            } else if (place == "Mahina") {
                background = BitmapFactory.decodeResource(resource, R.drawable.scene002_mahina)
                returnSceneNumber = 0
            } else if (place == "SceneEnd") {
                returnSceneNumber = 3
            } else if (place == "Quest") {
                background = BitmapFactory.decodeResource(resource, R.drawable.quest)
                returnSceneNumber = 4
            }
        }
        button2?.setOnClickListener {
        }

    }

    override fun setAssetsManager(asset: AssetManager) {

        words?.setAssetsManager(asset)
    }

    override fun draw(canvas: Canvas, paint: Paint) : Int {
        canvas.drawBitmap(
            background,
            Rect(0, 0, 512 * 8, 512 * 4),
            Rect(0, 0, 256 * 26,256 * 13),
            paint
        )

        canvas.drawBitmap(
            textFrame!!,
            Rect(0, 0, 512 * 8, 512 * 2),
            Rect(512 - 64, 512 + 128, 512 - 64 + 256 * 10, 512 + 128 + 128 * 5),
            paint
        )
        canvas.drawBitmap(
            textFrame!!,
            Rect(0, 0, 512 * 8, 512 * 2),
            Rect(16, 512 + 128, 512 - 64 + 128 * 5 - 512 + 64 + 16, 512 + 128 + 128 * 5),
            paint
        )

        return returnSceneNumber
    }

}