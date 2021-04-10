package com.continue_jump.retrorpg001

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import java.io.IOException

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
        gameSurfaceView = GameSurfaceView(surfaceView, resources.assets, getResources())
        gameSurfaceView?.addTextView( text )
        gameSurfaceView?.addButton1( button1 )
        gameSurfaceView?.addButton2( button2 )
        gameSurfaceView?.addButton3( button3 )
//
//        gameSurfaceView?.addBitmapTitle(BitmapFactory.decodeResource(getResources(), R.drawable.title))
//        gameSurfaceView?.addBitmapOpening(BitmapFactory.decodeResource(getResources(), R.drawable.opening))
//        gameSurfaceView?.addBitmapQuest(BitmapFactory.decodeResource(getResources(), R.drawable.quest))
//
//        gameSurfaceView?.addBitmapRen(BitmapFactory.decodeResource(getResources(), R.drawable.ren))
//        gameSurfaceView?.addBitmapSara(BitmapFactory.decodeResource(getResources(), R.drawable.sara))
//        gameSurfaceView?.addBitmapToushu(BitmapFactory.decodeResource(getResources(), R.drawable.toushu))
//        gameSurfaceView?.addBitmapPolice(BitmapFactory.decodeResource(getResources(), R.drawable.police))
//
//        gameSurfaceView?.addBitmapScene001_butsuma(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_butsuma))
//        gameSurfaceView?.addBitmapScene001_byouin(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_byouin))
//        gameSurfaceView?.addBitmapScene001_battle(BitmapFactory.decodeResource(getResources(), R.drawable.scene001_battle))
//        gameSurfaceView?.addBitmapTextFrame(BitmapFactory.decodeResource(getResources(), R.drawable.textframe))


//        audioPlay("thema_of_nakada.mp3")
        volumeControlStream = AudioManager.STREAM_MUSIC

    }

    var beforeSceneNumber = 0
    override fun onTouchEvent(event: MotionEvent) :Boolean {
        when(event.getAction()) {
            MotionEvent.ACTION_UP -> {
                val sceneNumber = gameSurfaceView?.onTouch()
                if (sceneNumber == 0) {
                } else if (sceneNumber == 2) {
//                    audioPlay("sentimental_mode.mp3")
                } else if (sceneNumber == 4) {
//                    audioPlay("dead_or_live.mp3")
                } else if (beforeSceneNumber == 4 && sceneNumber == 1) {
//                    audioStop()
                } else if (sceneNumber == 3) {
//                    audioStop()
                } else if (sceneNumber == 1) {
//                    audioStop()
                }
                beforeSceneNumber = sceneNumber!!
            }
        }
        return super.onTouchEvent(event)
    }

    private var mediaPlayer: MediaPlayer? = MediaPlayer()

    private fun audioSetup(bgmName: String): Boolean {
        // インタンスを生成
        mediaPlayer = MediaPlayer()

        //音楽ファイル名, あるいはパス
        val filePath = bgmName
        var fileCheck = false

        // assetsから mp3 ファイルを読み込み
        try {
            assets.openFd(filePath).use { afdescripter ->
                // MediaPlayerに読み込んだ音楽ファイルを指定
                mediaPlayer?.setDataSource(
                    afdescripter.fileDescriptor,
                    afdescripter.startOffset,
                    afdescripter.length
                )
                // 音量調整を端末のボタンに任せる
                volumeControlStream = AudioManager.STREAM_MUSIC
                mediaPlayer?.prepare()
                fileCheck = true
            }
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return fileCheck
    }

    private fun audioPlay(bgmName: String) {
//        if (mediaPlayer == null) {
        // audio ファイルを読出し
        if (audioSetup(bgmName)) {
//                Toast.makeText(application, "Rread audio file", Toast.LENGTH_SHORT).show()
        } else {
//                Toast.makeText(application, "Error: read audio file", Toast.LENGTH_SHORT)
//                    .show()
            return
        }
//        } else {
//            // 繰り返し再生する場合
//            mediaPlayer!!.stop()
//            mediaPlayer!!.reset()
//            // リソースの解放
//            mediaPlayer!!.release()
//        }

        // 再生する
        mediaPlayer?.isLooping = true
        mediaPlayer!!.start()

        // 終了を検知するリスナー
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Log.d("debug","end of audio");
//                audioStop();
//            }
//        });
        // lambda
        mediaPlayer!!.setOnCompletionListener { mp: MediaPlayer? ->
//            Log.d("debug", "end of audio")
            audioStop()
        }
    }

    private fun audioStop() {
        // 再生終了
        mediaPlayer!!.stop()
        // リセット
        mediaPlayer!!.reset()
        // リソースの解放
        mediaPlayer!!.release()
        mediaPlayer = null
    }

    override fun onRestart() {
        super.onRestart()
        audioStop()
        audioPlay("sentimental_mode.mp3")
    }
    override fun onPause() {
        super.onPause()
        audioStop()
    }
    override fun onDestroy() {
        super.onDestroy()
        audioStop()
    }

}