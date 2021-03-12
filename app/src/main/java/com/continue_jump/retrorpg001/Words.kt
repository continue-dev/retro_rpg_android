package com.continue_jump.retrorpg001

import android.widget.TextView

class Words {

    var text : TextView? = null

    constructor(textView: TextView) {
        text = textView

        text?.text = """
父さん・・・。
""".trimMargin()
        text?.setPadding(512, 512 + 128 + 64, 0, 0)
        text?.textSize = 24.0f

    }

    fun nextWords(textNumber: Int) {
        if (textNumber == 1) {
            text?.text = """
|レン。サラ。いいか、
|私の身に何かあったらお前達が道場を守るんだ。
|""".trimMargin()
        } else if (textNumber == 2) {
            text?.text = """
父さん・・・
そんな縁起でもない事
言わないでくれよ。
""".trimMargin()

        } else if (textNumber == 3) {
            text?.text = """
そうよ。
病気で倒れたからって弱気にならないで。
父さんにはもっと長生きしてもらわないといけないわ。
""".trimMargin()
        } else if (textNumber == 4) {
            text?.text = """
我が国が戦争に負けて武力廃棄令が下された。
大勢いた門下生も次々と離れていった。
このままでは道場をたたまなければいけなくなるだろう。
""".trimMargin()
        } else if (textNumber == 5) {
            text?.text = """
だが、武術とは心の在り方だ。
""".trimMargin()
        } else if (textNumber == 6) {
            text?.text = """
例え武道が廃れても先代達が
守ってきた道場だけは何か何でも守りたい。
""".trimMargin()
        } else if (textNumber == 7) {
            text?.text = """
それが出来るのは若いお前達なんだ。
私も出来る限り協力はする。頼んだぞ。
""".trimMargin()

        } else if (textNumber == 8) {
            text?.text = """
少年「父さん・・・。
わかったよ。
だから、死なないでくれよ」
""".trimMargin()
        } else if (textNumber == 9) {
            text?.text = """
父親「ああ、男同士の約束だぞ。」
""".trimMargin()
        } else if (textNumber == 10) {
            text?.text = """
少年「父さんの嘘つき！」
""".trimMargin()
        } else if (textNumber == 11) {
            text?.text = """
少女「レン・・・」
""".trimMargin()
        }


    }
}