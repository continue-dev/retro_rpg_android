package com.continue_jump.retrorpg001

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.*
import android.media.MediaPlayer
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.continue_jump.retrorpg001.scene.SceneInterface
import java.io.IOException

class Scenario : SceneInterface {

    override var assetManager : AssetManager
    override var resource : Resources
    override var background : Bitmap
    override var characters : Array<CharacterInterface?> = arrayOfNulls(0)

    override var button1: Button? = null
    override var button2: Button? = null
    override var button3: Button? = null
    override var button4: Button? = null

    var words : Words? = null
    var textNumber : Int = 0

    var textFrame : Bitmap? = null


    override var returnSceneNumber: Int = 0
    override var handler: Handler? = null

    constructor(asset: AssetManager, res: Resources) {
        assetManager = asset
        resource = res
        background = BitmapFactory.decodeResource(resource, R.drawable.scene001_butsuma)

        textFrame =  BitmapFactory.decodeResource(resource, R.drawable.textframe)

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
        button4?.visibility = Button.INVISIBLE

        button1?.text = "話す"
        button2?.text = "次へ"
        button3?.text = "戻る"

        button1?.setOnClickListener {
            textNumber += 1

            val place = words?.nextSerifu()

            if (place!![0] == "Byoin") {
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_byouin)
            } else if (place!![0] == "Butsuma") {
                if (place!![1] == "0") {
                    audioStop()
                    audioPlay("sentimental_mode.mp3")
                }
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_butsuma)
            } else if (place!![0] == "Genkan") {
                audioStop()
                audioPlay("thema_of_nakada.mp3")
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_genkan)
            } else if (place!![0] == "Chanoma") {
                background = BitmapFactory.decodeResource(resource, R.drawable.scene001_chanoma)
            } else if (place!![0] == "Mahina") {
                audioPlay("cave_road.mp3")
                background = BitmapFactory.decodeResource(resource, R.drawable.scene002_mahina)
                returnSceneNumber = 0
            } else if (place!![0] == "SceneEnd") {
                audioStop()
                returnSceneNumber = 3
            } else if (place!![0] == "Quest") {
                audioStop()
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

}