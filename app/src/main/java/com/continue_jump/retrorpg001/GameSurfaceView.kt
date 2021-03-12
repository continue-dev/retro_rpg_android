package com.continue_jump.retrorpg001

import android.graphics.*
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable

class GameSurfaceView : SurfaceHolder.Callback, Runnable {

    val _holder : SurfaceHolder
    val _surface : SurfaceView

    var _thread : Thread? = null
    var _isRunning : Boolean = true
    var isSceneStart : Boolean = false



    var place : Place = Place()
    var words : Words? = null
    var textNumber : Int = 0

    constructor(surface: SurfaceView) {

        _holder = surface.holder
        _holder.addCallback(this)
        _surface = surface
    }

    fun addBitmapRen(bitmap: Bitmap) {
        place.ren = Element(bitmap, 1024.0f, 128.0f)
        place.messageCharacter = bitmap
    }
    fun addBitmapSara(bitmap: Bitmap) {
        place.sara = Element(bitmap, 512.0f, 128.0f)
    }
    fun addBitmapToushu(bitmap: Bitmap) {
        place.toushu = Element(bitmap, 512.0f + 256.0f - 64.0f, 64.0f)
    }
    fun addBitmapScene001_butsuma(bitmap: Bitmap) {
        place.place = Element(bitmap, 64.0f, 16.0f)
        place.butsumaBitmap = bitmap
    }
    fun addBitmapScene001_byouin(bitmap: Bitmap) {
        place.byouinBitmap = bitmap
    }
    fun addBitmapTextFrame(bitmap: Bitmap) {
        place.textFrame = Element(bitmap, 512.0f - 64.0f, 512.0f + 128.0f)
    }
    fun addTextView(textView: TextView) {
        words = Words(textView)

    }

    fun onTouch() {
        textNumber += 1
        words?.nextWords(textNumber)
        place.nextPlace(textNumber)

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

            if (place.textFrame?.bitmap != null) {
                isSceneStart = true
            }

            if (isSceneStart) {
                val paint = Paint()
                canvas.drawColor(0, PorterDuff.Mode.CLEAR)
                paint.setColor(Color.GREEN)
                canvas.drawRect(0.0f, 0.0f, _surface.width.toFloat(), _surface.height.toFloat(), paint)
                place.draw(canvas, paint)
            }

            _holder.unlockCanvasAndPost(canvas)

        }
    }
}