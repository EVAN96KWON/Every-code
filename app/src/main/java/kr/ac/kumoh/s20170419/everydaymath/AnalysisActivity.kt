package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityAnalysisBinding

class AnalysisActivity : AppCompatActivity() {
    private lateinit var view: ActivityAnalysisBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(view.root)

        setSupportActionBar(view.appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTitle()
        startAnimations()

//        UserLogManager(filesDir).writeTextFile(
//            "2021-11-23,23,30,200\n" +
//                    "2021-11-24,23,30,340\n" +
//                    "2021-11-25,23,40,290\n" +
//                    "2021-11-26,40,40,298\n" +
//                    "2021-11-27,23,40,600\n" +
//                    "2021-11-28,13,20,960\n" +
//                    "2021-11-29,23,40,320\n"
//        )

//        val userLog = UserLogManager(filesDir).readTextFile()
//        val am = AnalysisManager(userLog)
//
//        UserLogManager(filesDir).writeTextFile(userLog)
//
//        val dataSet = listOf<Any>(
//            am.getRecentGrades(),
//            am.getProblemNums(),
//            am.getTimes(),
//        )
        view.recyclerView.adapter = AnalysisChartAdapter(filesDir)
    }

    private fun startAnimations() {
        val translateUpAnim = AnimationUtils.loadAnimation(this, R.anim.translate_up)
        translateUpAnim.startOffset = 100
        view.recyclerView.startAnimation(translateUpAnim)
    }

    @SuppressLint("SetTextI18n")
    private fun setTitle() {
        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        val userNickname = sps.getString("user_nickname", "defalut_nickname")
//        val userGrade = sps.getString("user_grade", "")
//        val userProblemsNums = sps.getString("user_problems_nums", "")
//        val appTheme = sps.getBoolean("app_theme", false)

        supportActionBar?.title = "${userNickname}님의 분석 결과에요 \uD83D\uDD75"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_down_enter, R.anim.slide_down_exit)
    }
}