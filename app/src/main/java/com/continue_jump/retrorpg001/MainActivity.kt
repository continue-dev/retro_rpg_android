package com.continue_jump.retrorpg001

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout

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
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = android.view.Gravity.RIGHT

        var button1Layout = LinearLayout(this)
        var button2Layout = LinearLayout(this)
        var button3Layout = LinearLayout(this)

        val button1 = Button(this)
        val button2 = Button(this)
        val button3 = Button(this)

        text.visibility = TextView.INVISIBLE
        button1.visibility = Button.INVISIBLE
        button2.visibility = Button.INVISIBLE
        button3.visibility = Button.INVISIBLE

        button1Layout.addView(button1, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) )
        button2Layout.addView(button2, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) )
        button3Layout.addView(button3, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) )

//        button1Layout.scaleX = 1.6f
//        button2Layout.scaleX = 1.6f
//        button3Layout.scaleX = 1.6f
//        button1Layout.scaleY = 1.6f
//        button2Layout.scaleY = 1.6f
//        button3Layout.scaleY = 1.6f
        button1Layout.setPadding(0, 50, 100, 0)
        button2Layout.setPadding(0, 50, 100, 0)
        button3Layout.setPadding(0, 50, 100, 0)

        addContentView(text, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) )
        addContentView(linearLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) )
        linearLayout.addView(button1Layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) )
        linearLayout.addView(button2Layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) )
        linearLayout.addView(button3Layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) )
        text.bringToFront()

        val surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
        gameSurfaceView = GameSurfaceView(surfaceView)
        gameSurfaceView?.addTextView( text )
        gameSurfaceView?.addButton1( button1 )
        gameSurfaceView?.addButton2( button2 )
        gameSurfaceView?.addButton3( button3 )

        gameSurfaceView?.addBitmapTitle(BitmapFactory.decodeResource(getResources(), R.drawable.title))
        gameSurfaceView?.addBitmapOpening(BitmapFactory.decodeResource(getResources(), R.drawable.opening))
        gameSurfaceView?.addBitmapQuest(BitmapFactory.decodeResource(getResources(), R.drawable.quest))

        gameSurfaceView?.addBitmapRen(BitmapFactory.decodeResource(getResources(), R.drawable.ren))
        gameSurfaceView?.addBitmapSara(BitmapFactory.decodeResource(getResources(), R.drawable.sara))
        gameSurfaceView?.addBitmapToushu(BitmapFactory.decodeResource(getResources(), R.drawable.toushu))
        gameSurfaceView?.addBitmapPolice(BitmapFactory.decodeResource(getResources(), R.drawable.police))

        gameSurfaceView?.addBitmapScene001_butsuma(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_butsuma))
        gameSurfaceView?.addBitmapScene001_byouin(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_byouin))
        gameSurfaceView?.addBitmapScene001_battle(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_battle))
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