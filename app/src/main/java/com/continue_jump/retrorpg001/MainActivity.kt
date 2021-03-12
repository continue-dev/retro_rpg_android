package com.continue_jump.retrorpg001

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import android.view.MotionEvent
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    var gameSurfaceView: GameSurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val decorView = window.decorView
        decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val text = TextView(this)
        addContentView(text, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) )
        text.bringToFront()

        val surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
        gameSurfaceView = GameSurfaceView(surfaceView)
        gameSurfaceView?.addTextView( text )

        gameSurfaceView?.addBitmapRen(BitmapFactory.decodeResource(getResources(), R.drawable.ren))
        gameSurfaceView?.addBitmapSara(BitmapFactory.decodeResource(getResources(), R.drawable.sara))
        gameSurfaceView?.addBitmapToushu(BitmapFactory.decodeResource(getResources(), R.drawable.toushu))
        gameSurfaceView?.addBitmapScene001_butsuma(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_butsuma))
        gameSurfaceView?.addBitmapScene001_byouin(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_byouin))
        gameSurfaceView?.addBitmapTextFrame(BitmapFactory.decodeResource(getResources(), R.drawable.textframe))

    }

    override fun onTouchEvent(event: MotionEvent) :Boolean {
        when(event.getAction()) {
            MotionEvent.ACTION_UP -> {
                gameSurfaceView?.onTouch()
            }
        }
        return super.onTouchEvent(event)
    }
}