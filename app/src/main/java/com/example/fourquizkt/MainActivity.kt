package com.example.fourquizkt

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.util.Log

class MainActivity : AppCompatActivity() {
    // 配列の定義
    private val quizTitles = arrayOf("問題A:りんごの英語","ゴリラが正解です","正解をタップしてください","正解をタップしてください")
    private val quizData = arrayOf(
        arrayOf("アップル","りんご","あっぴー","ゴン理"),
        arrayOf("ゴリラ","ラッパ","パンツ","つみき"),
        arrayOf("正解","B","C","D"),
        arrayOf("正解","不正解A","不正解B","不正解C"),
    )
    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvCount: TextView = findViewById(R.id.tvCount)
        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
        val btn0 : Button = findViewById(R.id.btn0)
        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 : Button = findViewById(R.id.btn3)
        val nextBtn: Button = findViewById(R.id.nextBtn)

        // 表示する値を設定
        tvCount.text = "あと４問"
        tvQuestion.text = quizTitles[0]

        // シャッフル
        val list = listOf(0,1,2,3)
        val num = list.shuffled()

        // quizDataを表示
        btn0.text = quizData[0][num[0]]
        btn1.text = quizData[0][num[1]]
        btn2.text = quizData[0][num[2]]
        btn3.text = quizData[0][num[3]]
        // デフォルトはfalse
        nextBtn.isEnabled = false

        // btn0押下時の正解不正解判定
        btn0.setOnClickListener {
            if (btn0.text == quizData[i][0]) {
                // 正解
                successAns()
            } else {
                // 不正解
                incorrectAns()
            }
        }

        // btn1押下時の正解不正解判定
        btn1.setOnClickListener {
            if (btn1.text == quizData[i][0]) {
                // 正解
                successAns()
            } else {
                // 不正解
                incorrectAns()
            }
        }

        // btn2押下時の正解不正解判定
        btn2.setOnClickListener {
            if (btn2.text == quizData[i][0]) {
                // 正解
                successAns()
            } else {
                // 不正解
                incorrectAns()
            }
        }

        // btn3押下時の正解不正解判定
        btn3.setOnClickListener {
            if (btn3.text == quizData[i][0]) {
                // 正解
                successAns()
            } else {
                // 不正解
                incorrectAns()
            }
        }

        // nexボタン押下
        nextBtn.setOnClickListener {
            // indexをシャッフル
            val shuffleIndexes = list.shuffled()
            //
            onPressNextBtn(btn0, btn1, btn2, btn3, tvCount, tvQuestion, nextBtn, shuffleIndexes)
        }
    }

    // 正解処理の関数
    private fun successAns() {
        val btn0 : Button = findViewById(R.id.btn0)
        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 : Button = findViewById(R.id.btn3)
        val nextBtn: Button = findViewById(R.id.nextBtn)

        AlertDialog.Builder(this)
            .setTitle("正解!!")
            .setMessage(quizData[i][0])
            .setPositiveButton("OK!", null).show()
        btn0.isEnabled = false
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        nextBtn.isEnabled = true
    }

    // 不正解処理の関数
    private fun incorrectAns() {
        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
        val btn0 : Button = findViewById(R.id.btn0)
        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 : Button = findViewById(R.id.btn3)
        val nextBtn: Button = findViewById(R.id.nextBtn)
        // 不正解
        tvQuestion.text = "不正解"
        btn0.isEnabled = false
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        nextBtn.isEnabled = false
    }

    // nextボタン押下時の関数
    @SuppressLint("SetTextI18n", "StringFormatMatches")
    private fun onPressNextBtn(
        btn0: Button,
        btn1: Button,
        btn2: Button,
        btn3: Button,
        tvCount: TextView,
        tvQuestion: TextView,
        nextBtn: Button,
        shuffleIndexes: List<Int>
    ) {
        i++
        // NEXTボタンをdisabled
        if (i == 4) {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val count = 4 - i
            // strings.xmlを使う
            val cntText = getString(R.string.quizCnt, count.toString())
            tvCount.text = cntText
            tvQuestion.text = quizTitles[i]
            // 次に表示するデータをセット
            val nextQuizData = quizData[i]
            btn0.text = nextQuizData[shuffleIndexes[0]]
            btn0.isEnabled = true
            btn1.text = nextQuizData[shuffleIndexes[1]]
            btn1.isEnabled = true
            btn2.text = nextQuizData[shuffleIndexes[2]]
            btn2.isEnabled = true
            btn3.text = nextQuizData[shuffleIndexes[3]]
            btn3.isEnabled = true
            nextBtn.isEnabled = false
        }
    }

}