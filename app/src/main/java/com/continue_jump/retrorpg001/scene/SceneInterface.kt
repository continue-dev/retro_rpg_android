package com.continue_jump.retrorpg001.scene

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.widget.Button
import android.widget.TextView
import com.continue_jump.retrorpg001.CharacterInterface

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

    fun changeButton() {}
    fun setViews(textView: TextView,
                 button_1: Button,
                 button_2: Button,
                 button_3: Button) {}
    fun setScenario(scenarioNumber : Int, continueNumber : Int) {}
    fun setAssetsManager(asset: AssetManager) {}
    fun draw(canvas: Canvas, paint: Paint) : Int { return 0 }

}