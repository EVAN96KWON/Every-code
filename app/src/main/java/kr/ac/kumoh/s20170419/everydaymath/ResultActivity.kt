package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityResultBinding

/**
 *  written by Kwon, 11-29
 *  @author EVAN96KWON
 *  @version 1.0
 *  @see
 *  시험 결과(TestActivity.kt)를 토대로 resultScore: Float 을 꼭 채워주세요!!
 */

class ResultActivity : AppCompatActivity() {
    private lateinit var view: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityResultBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_result)

        val resultScore: Float = 0f
        view.resultComment.text = setComment(resultScore)
    }

    @SuppressLint("SetTextI18n")
    private fun setComment(resultScore: Float): String {
        view.resultScore.text = "${resultScore}점"
        return when {
            resultScore == 100f -> "완벽해요! \uD83D\uDE0D"
            resultScore >= 90f -> "휼륭해요! \uD83D\uDE01"
            resultScore >= 70f -> "잘했어요! 더 잘할 수 있나요? \uD83E\uDDD0"
            resultScore >= 40f -> "잘하고 있어요! 화이팅입니다. \uD83D\uDE0E"
            else -> "천리길도 한 걸음부터! 잘하고 있습니다. \uD83E\uDD70"
        }
    }
}