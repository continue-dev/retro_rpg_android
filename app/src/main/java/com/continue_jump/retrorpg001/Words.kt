package com.continue_jump.retrorpg001

import android.content.res.AssetManager
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader

class Words {

    var text : TextView? = null
    var bufferReader : BufferedReader? = null
    var assetManager : AssetManager? = null

    constructor(textView: TextView) {
        text = textView

        text?.text = """
父さん・・・。
""".trimMargin()
        text?.setPadding(512, 512 + 128 + 64, 0, 0)
        text?.textSize = 24.0f

    }

    fun setAssetsManager(assetsManager: AssetManager) {
        assetManager = assetsManager
    }

    fun setScenarioContinue(scenarioCSVName: String, continueNumber: Int) {
        val inputStream = assetManager?.open(scenarioCSVName)
        bufferReader = BufferedReader(InputStreamReader(inputStream))

        if (continueNumber >= 1) {
            for (i in 0..continueNumber) {
                bufferReader?.readLine()
            }
        }
    }

    fun nextSerifu() : String {
        val line = bufferReader?.readLine()
        if (line == null || line == "") {
            return "SceneEnd"
        }
        val scenario = line?.split(",")!!.map{ it.trim('"') }
        text?.text = scenario[3] + "\n" + scenario[4] + "\n" + scenario[5]
        return scenario[0]
    }
}