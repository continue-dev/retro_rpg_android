package com.continue_jump.retrorpg001.scene

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.*
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.widget.Button
import com.continue_jump.retrorpg001.CharacterInterface
import com.continue_jump.retrorpg001.R
import java.io.IOException

class Title : SceneInterface {

    override var assetManager : AssetManager
    override var resource : Resources
    override var background : Bitmap
    override var characters : Array<CharacterInterface?> = arrayOfNulls(0)

    override var button1: Button? = null
    override var button2: Button? = null
    override var button3: Button? = null

    override var returnSceneNumber: Int = 0

    constructor(asset: AssetManager, res: Resources) {
        assetManager = asset
        resource = res
        background = BitmapFactory.decodeResource(resource, R.drawable.title)

        audioPlay("dead_or_live.mp3")
    }

    override fun draw(canvas: Canvas, paint: Paint) : Int {
        canvas.drawBitmap(
                background,
                Rect(0, 0, 512 * 8, 512 * 4),
                Rect(0, 0, 256 * 26,256 * 13),
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