package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityResultBinding
import java.time.LocalDate
import java.util.ArrayList

class ResultActivity : AppCompatActivity() {
    private lateinit var view: ActivityResultBinding
    private val TAG: String = "ResultActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityResultBinding.inflate(layoutInflater)
        setContentView(view.root)

        var currentDate = intent.getStringExtra("current_date")
        val answerCount = intent.getStringExtra("answer_count")
        val problemNums = intent.getStringExtra("problem_nums")
        val endTime = intent.getStringExtra("end_time")

        val resultScore: Float = (answerCount!!.toFloat() / problemNums!!.toFloat()) * 100 // = (맞춘 문제 수 / 전체 문제 수)

        // TextView에 점수 표시
        view.resultComment.text = setComment(resultScore)
        view.resultScore.text = resultScore.toString() + "점"

        view.btnGotoMain.setOnClickListener { onBackPressed() }

        //결과값 userLog.txt에 저장
        writeResult(answerCount, endTime!!)
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

    private fun writeResult(answerCount: String, testTime: String) {
        //get user log
        val userLog = UserLogManager(filesDir).readTextFile()
        val tmpList = userLog.split("\n")
        val userLogList = ArrayList<List<String>>()
        for (i in tmpList.indices) userLogList.add(tmpList[i].split(","))
        userLogList.removeAt(userLogList.size - 1)

        //get problem nums
        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        var userProblemsNums = sps.getString("user_problems_nums", "1")

        // 새로 추가하는 리스트의 날짜가 제일 최근의 것과 같으면 이전의 것을 삭제
        if (userLogList[userLogList.size - 1][0] == LocalDate.now().toString()) {
            userLogList.removeAt(userLogList.size - 1)
        }

        //add current test result
        userLogList.add(
            listOf(
                LocalDate.now().toString(),
                answerCount,
                userProblemsNums.toString(),
                testTime
            )
        )

        // adjust list size
        while (userLogList.size != 7) {
            if (userLogList.size > 7) userLogList.removeAt(0)
            else userLogList.add(0, listOf("2001-01-01","0","11","0"))
        }


        // list to String, and write to log.txt
        var content = ""
        for (list in userLogList) content += list.joinToString(",", "", "\n")
        UserLogManager(filesDir).writeTextFile(content)
    }
}