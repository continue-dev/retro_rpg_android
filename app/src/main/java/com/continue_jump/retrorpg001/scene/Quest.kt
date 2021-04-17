package com.continue_jump.retrorpg001.scene

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.*
import android.media.MediaPlayer
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.continue_jump.retrorpg001.BackgroundInterface
import com.continue_jump.retrorpg001.CharacterInterface
import com.continue_jump.retrorpg001.MapChip
import com.continue_jump.retrorpg001.R
import java.io.IOException
import kotlin.random.Random

class Quest : SceneInterface {

    override var assetManager : AssetManager
    override var resource : Resources
    override var background : Bitmap
    override var characters : Array<CharacterInterface?> = arrayOfNulls(0)

    override var button1: Button? = null
    override var button2: Button? = null
    override var button3: Button? = null
    override var button4: Button? = null

    override var returnSceneNumber: Int = 0
    override var handler: Handler? = null

    var leftButtonFlag : Boolean = false
    var rightButtonFlag : Boolean = false
    var downButtonFlag : Boolean = false
    var fieldMap : Bitmap?
    var treeMap : Bitmap?
    var mountainMap : Bitmap?
    var mapchip: Array<BackgroundInterface?> = arrayOf()
    var tempCounter: Int = 0
    constructor(asset: AssetManager, res: Resources) {
//        handler = Handler()
        assetManager = asset
        resource = res
//        background = BitmapFactory.decodeResource(resource, R.drawable.quest)
//        audioPlay("dead_or_live.mp3")

        fieldMap = BitmapFactory.decodeResource(resource, R.drawable.field)
        mountainMap = BitmapFactory.decodeResource(resource, R.drawable.field_mountain)
        treeMap = BitmapFactory.decodeResource(resource, R.drawable.field_tree)

        background = fieldMap!!
        for (y in 0..6) {
            for (x in 0..14) {
                val r = Random.nextInt(10)
                if (r == 0) {
                    mapchip += MapChip(mountainMap!!, 180 * x, 180 * y)
                } else if (r == 1) {
                    mapchip += MapChip(treeMap!!, 180 * x, 180 * y)
                } else {
                    mapchip += MapChip(fieldMap!!, 180 * x, 180 * y)
                }
            }
        }
    }

    override fun setViews(textView: TextView,
                          button_1: Button,
                          button_2: Button,
                          button_3: Button,
                          button_4: Button
    ) {
        button1 = button_1
        button2 = button_2
        button3 = button_3
        button4 = button_4

        changeButton()
    }
    override fun changeButton() {
        button1?.visibility = Button.VISIBLE
        button2?.visibility = Button.VISIBLE
        button3?.visibility = Button.VISIBLE
        button4?.visibility = Button.VISIBLE

        button1?.text = "↑"
        button2?.text = "←"
        button3?.text = "→"
        button4?.text = "↓"

        button1?.setOnClickListener {
            tempCounter += 1
            if (tempCounter >= 5) {
                returnSceneNumber = 5
            }
        }
        button2?.setOnTouchListener(
            View.OnTouchListener { v, event ->
                // タッチされた時に受ける関数を設定
                val action = event.action
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        leftButtonFlag = true
                        return@OnTouchListener true
                    }
                    MotionEvent.ACTION_UP -> {
                        leftButtonFlag = false
                        return@OnTouchListener true
                    }
                }
                false
            }
        )
        button3?.setOnTouchListener(
            View.OnTouchListener { v, event ->
                // タッチされた時に受ける関数を設定
                val action = event.action
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        rightButtonFlag = true
                        return@OnTouchListener true
                    }
                    MotionEvent.ACTION_UP -> {
                        rightButtonFlag = false
                        return@OnTouchListener true
                    }
                }
                false
            }
        )

        button4?.setOnTouchListener(
            View.OnTouchListener { v, event ->
                // タッチされた時に受ける関数を設定
                val action = event.action
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        downButtonFlag = true
                        return@OnTouchListener true
                    }
                    MotionEvent.ACTION_UP -> {
                        downButtonFlag = false
                        return@OnTouchListener true
                    }
                }
                false
            }
        )

    }

    override fun draw(canvas: Canvas, paint: Paint) : Int {

        if (leftButtonFlag) {
            for (map in mapchip) {
                map?.x = map?.x?.plus(10)!!

                if (map?.x > 2340) {
                    map?.x = -180
                }
            }
        }
        if (rightButtonFlag) {
            for (map in mapchip) {
                map?.x = map?.x?.minus(10)!!

                if (map?.x < -180) {
                    map?.x = 2340
                }
            }
        }
        if (downButtonFlag) {
            for (map in mapchip) {
                map?.y = map?.y?.plus(10)!!

                if (map?.y > 1080) {
                    map?.y = -180
                }
            }
        }

        if (background != null) {
//            canvas.drawBitmap(
//                background,
//                Rect(0, 0, 512 * 8, 512 * 4),
//                Rect(0, 0, 256 * 26, 256 * 13),
//                paint
//            )

            for (map in mapchip) {
                canvas.drawBitmap(
                    map?.bitmap!!,
                    Rect(0, 0, 512, 512),
                    Rect(map?.x, map?.y, 512 + map?.x, 512 + map?.y),
                    paint
                )
            }
        }
        return returnSceneNumber
    }

    override var mediaPlayer: MediaPlayer? = MediaPlayer()

    override fun audioSetup(bgmName: String): Boolean {
        // インタンスを生成
        mediaPlayer = MediaPlayer()

        //音楽ファイル名, あるいはパス
        val filePath = bgmName
        var fileCheck = false

        // assetsから mp3 ファイルを読み込み
        try {
            assetManager.openFd(filePath).use { afdescripter ->
                // MediaPlayerに読み込んだ音楽ファイルを指定
                mediaPlayer?.setDataSource(
                    afdescripter.fileDescriptor,
                    afdescripter.startOffset,
                    afdescripter.length
                )
                // 音量調整を端末のボタンに任せる
//                volumeControlStream = AudioManager.STREAM_MUSIC
                mediaPlayer?.prepare()
                fileCheck = true
            }
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return fileCheck
    }

    override fun audioPlay(bgmName: String) {
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

    override fun audioStop() {
        // 再生終了
        mediaPlayer!!.stop()
        // リセット
        mediaPlayer!!.reset()
        // リソースの解放
        mediaPlayer!!.release()
        mediaPlayer = null
    }

    override fun finalize() {
        Thread { // Handlerを使用してメイン(UI)スレッドに処理を依頼する
            handler?.post(Runnable {
                button1?.visibility = Button.INVISIBLE
                button2?.visibility = Button.INVISIBLE
                button3?.visibility = Button.INVISIBLE
                button4?.visibility = Button.INVISIBLE
            })
        }.start()



    }
}