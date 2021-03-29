package com.continue_jump.retrorpg001

import android.graphics.*
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Button

class GameSurfaceView : SurfaceHolder.Callback, Runnable {

    val _holder : SurfaceHolder
    val _surface : SurfaceView

    var _thread : Thread? = null
    var _isRunning : Boolean = true
    var isSceneStart : Boolean = false


    var title : Title = Title()
    var opening : Opening = Opening()
    var scenario : Scenario = Scenario()
    var quest : Quest = Quest()
    var battle : Battle = Battle()

    var sceneNumber : Int = 0


    constructor(surface: SurfaceView) {

        _holder = surface.holder
        _holder.addCallback(this)
        _surface = surface
    }

    fun addBitmapTitle(bitmap: Bitmap) {
        title.bitmap = bitmap
    }
    fun addBitmapOpening(bitmap: Bitmap) {
        opening.bitmap = bitmap
    }
    fun addBitmapQuest(bitmap: Bitmap) {
        quest.bitmap = bitmap
    }

    fun addBitmapRen(bitmap: Bitmap) {
        scenario.place.ren = Element(bitmap, 1024.0f, 128.0f)
        scenario.place.messageCharacter = bitmap

        battle.ren = Element(bitmap, 1024.0f, 128.0f)

    }
    fun addBitmapSara(bitmap: Bitmap) {
        scenario.place.sara = Element(bitmap, 512.0f, 128.0f)
        battle.sara = Element(bitmap, 512.0f, 128.0f)

    }
    fun addBitmapToushu(bitmap: Bitmap) {
        scenario.place.toushu = Element(bitmap, 512.0f + 256.0f - 64.0f, 64.0f)
    }

    fun addBitmapPolice(bitmap: Bitmap) {
        battle.police = Element(bitmap, 64.0f, 0.0f)
    }

    fun addBitmapScene001_butsuma(bitmap: Bitmap) {
        scenario.place.place = Element(bitmap, 0.0f, 0.0f)
        scenario.place.butsumaBitmap = bitmap
    }
    fun addBitmapScene001_byouin(bitmap: Bitmap) {
        scenario.place.byouinBitmap = bitmap
    }

    fun addBitmapScene001_battle(bitmap: Bitmap) {
        battle.bitmap = bitmap
    }


    fun addBitmapTextFrame(bitmap: Bitmap) {
        scenario.place.textFrame = Element(bitmap, 512.0f - 64.0f, 512.0f + 128.0f)
        battle.textFrame = Element(bitmap, 512.0f - 64.0f, 512.0f + 128.0f)
    }
    fun addTextView(textView: TextView) {
        scenario.words = Words(textView)
        quest.words = scenario.words
        battle.words = scenario.words
    }

    fun addButton1(button: Button) {
        opening.button1 = button
        scenario.button1 = button
        quest.button1 = button
        battle.button1 = button
    }
    fun addButton2(button: Button) {
        opening.button2 = button
        scenario.button2 = button
        quest.button2 = button
        battle.button2 = button
    }
    fun addButton3(button: Button) {
        opening.button3 = button
        scenario.button3 = button
        quest.button3 = button
        battle.button3 = button
    }

    fun onTouch(): Int {
        sceneNumber += 1

        if (sceneNumber == 1) {
            opening.changeButton()
        }
        if (sceneNumber == 2) {
            scenario.changeButton()
        }
        if (sceneNumber == 3) {
            quest.changeButton()
        }
        if (sceneNumber == 4) {
            battle.changeButton()
        }

        if (sceneNumber >= 5) {
            sceneNumber = 1
        }

        return sceneNumber
//        textNumber += 1
//        words?.nextWords(textNumber)
//        place.nextPlace(textNumber)

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        _thread = Thread(this)
        _thread?.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        _isRunning = false
        _thread = null
    }

    override fun run() {

        while (_isRunning) {
            val canvas = _holder.lockCanvas()

            if (title.bitmap != null) {
                isSceneStart = true
            }

            if (isSceneStart) {
                val paint = Paint()
                canvas.drawColor(0, PorterDuff.Mode.CLEAR)
//                paint.setColor(Color.GREEN)
//                canvas.drawRect(0.0f, 0.0f, _surface.width.toFloat(), _surface.height.toFloat(), paint)

                if (sceneNumber == 0) {
                    title.draw(canvas, paint)
                }
                if (sceneNumber == 1) {
                    opening.draw(canvas, paint)
                }
                if (sceneNumber == 2) {
                    scenario.draw(canvas, paint)
                }
                if (sceneNumber == 3) {
                    quest.draw(canvas, paint)
                }
                if (sceneNumber == 4) {
                    battle.draw(canvas, paint)
                }

            }

            _holder.unlockCanvasAndPost(canvas)

        }
    }
}