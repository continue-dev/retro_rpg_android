package com.continue_jump.retrorpg001

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.*
import android.os.Handler
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.TextView
import com.continue_jump.retrorpg001.scene.Opening
import com.continue_jump.retrorpg001.scene.Quest
import com.continue_jump.retrorpg001.scene.SceneInterface
import com.continue_jump.retrorpg001.scene.Title

class GameSurfaceView : SurfaceHolder.Callback, Runnable {

    val _holder : SurfaceHolder
    val _surface : SurfaceView

    var _thread : Thread? = null
    var _isRunning : Boolean = true
    var isSceneStart : Boolean = false

    val handler: Handler = Handler()

    var textView : TextView? = null
    var button1 : Button? = null
    var button2 : Button? = null
    var button3 : Button? = null
    var button4 : Button? = null
//    var title : Title = Title()
//    var opening : Opening = Opening()
//    var scenario : Scenario = Scenario()
//    var quest : Quest = Quest()
//    var battle : Battle = Battle()

    lateinit var scene : SceneInterface
    var sceneNumber : Int = 0

    lateinit var assetManager : AssetManager
    lateinit var resource : Resources

    constructor(surface: SurfaceView, asset: AssetManager, res: Resources) {

        _holder = surface.holder
        _holder.addCallback(this)
        _surface = surface

        assetManager = asset
        resource = res

        scene = Title(assetManager, resource)
    }

//    fun addBitmapTitle(bitmap: Bitmap) {
//        title.bitmap = bitmap
//    }
//    fun addBitmapOpening(bitmap: Bitmap) {
//        opening.bitmap = bitmap
//    }
//    fun addBitmapQuest(bitmap: Bitmap) {
//        quest.bitmap = bitmap
//    }
//
//    fun addBitmapRen(bitmap: Bitmap) {
//        scenario.place.ren = Element(bitmap, 1024.0f, 128.0f)
//        scenario.place.messageCharacter = bitmap
//
//        battle.ren = Element(bitmap, 1024.0f, 128.0f)
//
//    }
//    fun addBitmapSara(bitmap: Bitmap) {
//        scenario.place.sara = Element(bitmap, 512.0f, 128.0f)
//        battle.sara = Element(bitmap, 512.0f, 128.0f)
//
//    }
//    fun addBitmapToushu(bitmap: Bitmap) {
//        scenario.place.toushu = Element(bitmap, 512.0f + 256.0f - 64.0f, 64.0f)
//    }
//
//    fun addBitmapPolice(bitmap: Bitmap) {
//        battle.police = Element(bitmap, 64.0f, 0.0f)
//    }
//
//    fun addBitmapScene001_butsuma(bitmap: Bitmap) {
//        scenario.place.place = Element(bitmap, 0.0f, 0.0f)
//        scenario.place.butsumaBitmap = bitmap
//    }
//    fun addBitmapScene001_byouin(bitmap: Bitmap) {
//        scenario.place.byouinBitmap = bitmap
//    }
//
//    fun addBitmapScene001_battle(bitmap: Bitmap) {
//        battle.bitmap = bitmap
//    }
//
//
//    fun addBitmapTextFrame(bitmap: Bitmap) {
//        scenario.place.textFrame = Element(bitmap, 512.0f - 64.0f, 512.0f + 128.0f)
//        battle.textFrame = Element(bitmap, 512.0f - 64.0f, 512.0f + 128.0f)
//    }
    fun addTextView(text_View: TextView) {
        textView = text_View
    }

    fun addButton(button_1: Button, button_2: Button, button_3: Button, button_4: Button) {
        button1 = button_1
        button2 = button_2
        button3 = button_3
        button4 = button_4
    }

    fun onTouch(): Int {
        sceneNumber += 1

        if (sceneNumber == 1) {
            scene.audioStop()
            scene = Opening(assetManager, resource)
        }
        if (sceneNumber == 2) {
            scene.audioStop()
            scene = Scenario(assetManager, resource)
            scene.setViews(textView!!, button1!!, button2!!, button3!!, button4!!)
            scene.setScenario(1, 0)

        }

        return sceneNumber
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

            if (scene.background != null) {
                isSceneStart = true
            }

            if (isSceneStart) {
                val paint = Paint()
                canvas.drawColor(0, PorterDuff.Mode.CLEAR)
//                paint.setColor(Color.GREEN)
//                canvas.drawRect(0.0f, 0.0f, _surface.width.toFloat(), _surface.height.toFloat(), paint)


                val sceneNum = scene.draw(canvas, paint)

                if (sceneNum != 0) {
                    if (sceneNum == 3) {
//                        scene.setViews(textView!!, button1!!, button2!!, button3!!)
                        scene.setScenario(2, 0)
                    } else if (sceneNum == 4) {
                        scene = Quest(assetManager, resource)

                        Thread { // Handlerを使用してメイン(UI)スレッドに処理を依頼する
                            handler.post(Runnable {
                                scene.setViews(textView!!, button1!!, button2!!, button3!!, button4!!)
                            })
                        }.start()

                    } else if (sceneNum == 5) {
                        scene.audioStop()
                        scene.finalize()
                        scene = Scenario(assetManager, resource)
                        Thread { // Handlerを使用してメイン(UI)スレッドに処理を依頼する
                            handler.post(Runnable {
                                scene.setViews(textView!!, button1!!, button2!!, button3!!, button4!!)
                                scene.setScenario(2, 23)
                            })
                        }.start()

                    }
                }
//                if (sceneNumber == 0) {
//                    title.draw(canvas, paint)
//                }
//                if (sceneNumber == 1) {
//                    opening.draw(canvas, paint)
//                }
//                if (sceneNumber == 2) {
//                    scenario.draw(canvas, paint)
//                }
//                if (sceneNumber == 3) {
//                    quest.draw(canvas, paint)
//                }
//                if (sceneNumber == 4) {
//                    battle.draw(canvas, paint)
//                }

            }

            _holder.unlockCanvasAndPost(canvas)

        }
    }
}