package com.continue_jump.retrorpg001

import android.graphics.*
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurfaceView : SurfaceHolder.Callback, Runnable {

    val _holder : SurfaceHolder
    val _surface : SurfaceView

    var _thread : Thread? = null
    var _isRunning : Boolean = true

    var element : MutableList<Element> = mutableListOf()

    var _x : Float = 100.0f
    var _y : Float = 100.0f

    constructor(surface: SurfaceView, bitmap: Bitmap) {
        for (x in 0..3) {
            for (y in 0..3) {
                element.add(Element(bitmap, 300.0f * x.toFloat(), 300.0f * y.toFloat()) )
            }
        }

        _holder = surface.holder
        _holder.addCallback(this)
        _surface = surface
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
        var cnt = 0
//        val runner = Runner()

        while (_isRunning) {
            val canvas = _holder.lockCanvas()

//            val bitmap = runner.createBitmap()
//            var x = - bitmap.width
//            if (_surface.width < (x + cnt * 50 )) {
//                cnt = 0
//            } else {
//                x += cnt * 50
//            }

            val paint = Paint()
            canvas.drawColor(0, PorterDuff.Mode.CLEAR)
            paint.setColor(Color.GREEN)
            canvas.drawRect(0.0f,0.0f, _surface.width.toFloat(), _surface.height.toFloat(), paint)
            for (el in element) {
                el.x += 1.0f
                el.y += 1.0f
                canvas.drawBitmap(
                        el.bitmap,
                        Rect(0,0,512,512),
                        Rect(el.x.toInt(),el.y.toInt(),el.x.toInt() + 256,el.y.toInt() + 256),
                        paint
                )
            }
//            canvas.drawBitmap(bitmap, x.toFloat(), (_surface.height - bitmap.height).toFloat(), paint)

            _x += 1.0f
            _y += 1.0f

            _holder.unlockCanvasAndPost(canvas)
//            Thread.sleep(150L)
            cnt++

        }
    }
}