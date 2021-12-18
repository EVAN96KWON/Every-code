package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityResultBinding
import java.util.ArrayList

/**
 *  written by Kwon, 11-29
 *  @author EVAN96KWON
 *  @version 1.0
 *  @see
 *  시험 결과(TestActivity.kt)를 토대로 answerNum 과 testTime 을 채워주세요 !
 */

class ResultActivity : AppCompatActivity() {
    private lateinit var view: ActivityResultBinding
    private val TAG: String = "ResultActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityResultBinding.inflate(layoutInflater)
        setContentView(view.root)

        var currentDate = intent.getStringExtra("current_date").toString()
        val answerCount = intent.getStringExtra("answer_count").toString()
        val problemNums = intent.getStringExtra("problem_nums").toString()
        val endTime = intent.getStringExtra("end_time").toString()

        Log.e(TAG, "넘어온 string : $currentDate $answerCount $problemNums $endTime")

        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        var userProblemsNums = sps.getString("user_problems_nums", "1")

        val answerNum: Int = 0; // = 맞춘 문제 수
        val testTime: Int = 0; // = 걸린 시간
        val resultScore: Float = (answerCount!!.toFloat() / problemNums!!.toFloat()) * 100 // = (맞춘 문제 수 / 전체 문제 수)

        // TextView에 점수 표시
        view.resultComment.text = setComment(resultScore)
        view.resultScore.text = resultScore.toString() + "점"
        //결과값 userLog.txt에 저장
        writeResult(resultScore, testTime)


    }

    @SuppressLint("SetTextI18n")
    private fun setComment(resultScore: Float): String {
        view.resultScore.text = "${resultScore}점"
        return when {
            resultScore == 100f -> "완벽해요! \uD83D\uDE0D"
            resultScore >= 90f -> "휼륭해요! \uD83D\uDE01"
            resultScore >= 70f -> "잘했어요!\n다음 단계에 도전해볼까요? \uD83E\uDDD0"
            resultScore >= 40f -> "잘하고 있어요! 화이팅입니다. \uD83D\uDE0E"
            else -> "천리길도 한 걸음부터!\n잘하고 있습니다. \uD83E\uDD70"
        }
    }

    private fun writeResult(resultScore: Float, testTime: Int) {
        val userLog = UserLogManager(filesDir).readTextFile()

        val tmpList = userLog.split("\n")
        val userLogList = ArrayList<List<String>>()
        for (i in tmpList.indices) userLogList.add(tmpList[i].split(","))
        userLogList.removeAt(userLogList.size - 1)

        // check log size
        while (userLogList.size < 7) {
            userLogList.add(0, listOf("2001-01-01","0","10","0"))
        }

        UserLogManager(filesDir).writeTextFile(userLog)
    }
}