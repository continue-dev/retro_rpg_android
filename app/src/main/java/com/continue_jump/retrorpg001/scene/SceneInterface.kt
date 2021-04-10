package com.continue_jump.retrorpg001.scene

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Button
import android.widget.TextView
import com.continue_jump.retrorpg001.CharacterInterface
import java.io.IOException

interface SceneInterface {

//    var place : Place?
//    var words : Words?
//    var textNumber : Int
    var assetManager : AssetManager
    var resource : Resources

    var background : Bitmap
    var characters : Array<CharacterInterface?>
    var button1 : Button?
    var button2 : Button?
    var button3 : Button?

    var returnSceneNumber: Int

    var mediaPlayer: MediaPlayer?

    fun audioSetup(bgmName: String): Boolean {
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

    fun audioPlay(bgmName: String) {
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

    fun audioStop() {
        // 再生終了
        mediaPlayer!!.stop()
        // リセット
        mediaPlayer!!.reset()
        // リソースの解放
        mediaPlayer!!.release()
        mediaPlayer = null
    }






    fun changeButton() {}
    fun setViews(textView: TextView,
                 button_1: Button,
                 button_2: Button,
                 button_3: Button) {}
    fun setScenario(scenarioNumber : Int, continueNumber : Int) {}
    fun setAssetsManager(asset: AssetManager) {}
    fun draw(canvas: Canvas, paint: Paint) : Int { return 0 }

}