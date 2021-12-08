package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityTestBinding
import java.time.LocalDate
import java.util.*
import kotlin.concurrent.timer

class TestActivity : AppCompatActivity() {

    private var currentDate = LocalDate.now()
    private lateinit var view : ActivityTestBinding
    private var time = 0
    private var timerTask : Timer? =  null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityTestBinding.inflate(layoutInflater)

        setContentView(view.root)

        startTimer()

        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        val userProblemsNums = sps.getString("user_problems_nums", "10")!!.toInt()

        var answerCount = 0
        var solvedCount = 1
        var num1 = (1..10).random()
        var num2 = (1..10).random()
        var currentAnswer = (num1 * num2).toString()

        view.testLine1.text = "$num1"
        view.testLine2.text = "$num2"
        view.currentCount.text = "$answerCount / $userProblemsNums"


        view.nextTest.setOnClickListener {
            // 현재 푼 문제 수가 유저가 제한한 문제 수를 넘으면 결과 액티비티로 넘어감
            if (solvedCount >= userProblemsNums) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("current_date", currentDate.toString())
                intent.putExtra("answer_count", answerCount.toString())
                intent.putExtra("problem_nums", userProblemsNums.toString())
                intent.putExtra("end_time", (time / 100).toString())
                setResult(RESULT_OK, intent)
                startActivity(intent)
                stopTimer()
                finish()
            }

            if ( view.inputAnswer1.text.toString() == currentAnswer) {
                answerCount++
            }


//            view.currentScore.text = "${view.inputAnswer1.text}, $currentAnswer"

            view.testText.text = view.inputAnswer1.text // textview에 edittext값 넣기

            solvedCount += 1 // 푼 문제 수
            num1 = (1..10).random() // 문제 다시 랜덤 배정
            num2 = (1..10).random()
            currentAnswer = (num1 * num2).toString()

            view.testLine1.text = "$num1" // textview에 set
            view.testLine2.text = "$num2"
            view.currentCount.text = "$solvedCount / $userProblemsNums" // 현재 문제 수
        }

    }

    private fun startTimer() {
        timerTask = timer(period = 10) {
            time++

            val sec = time / 100
        }
    }

    private fun stopTimer() {
        timerTask?.cancel()
    }
}

