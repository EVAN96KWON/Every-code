package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var view : ActivityTestBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityTestBinding.inflate(layoutInflater)

        setContentView(view.root)

        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        val userProblemsNums = sps.getString("user_problems_nums", "10")!!.toInt()

        var answerCount = 0
        var solvedCount = 1
        var num1 = (1..10).random()
        var num2 = (1..10).random()

        view.testLine1.text = "$num1"
        view.testLine2.text = "$num2"
        view.currentCount.text = "$answerCount / $userProblemsNums"


        view.nextTest.setOnClickListener {
            // 현재 푼 문제 수가 유저가 제한한 문제 수를 넘으면 결과 액티비티로 넘어감
            if (solvedCount > userProblemsNums) {
                startActivity(Intent(this, ResultActivity::class.java))
                finish()
            }

            view.testText.text = view.inputAnswer1.text // textview에 edittext값 넣기

            solvedCount += 1 // 푼 문제 수
            num1 = (1..10).random() // 문제 다시 랜덤 배정
            num2 = (1..10).random()

            view.testLine1.text = "$num1" // textview에 set
            view.testLine2.text = "$num2"
            view.currentCount.text = "$solvedCount / $userProblemsNums" // 현재 문제 수
        }

    }
}

