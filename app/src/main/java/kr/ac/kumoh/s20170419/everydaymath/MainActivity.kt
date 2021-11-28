package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var view: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        val userNickname = sps.getString("user_nickname", "defalut_nickname")
        val userGrade = sps.getString("user_grade", "")
        val userProblemsNums = sps.getString("user_problems_nums", "")
        val appTheme = sps.getBoolean("app_theme", false)

        view.main1111.text = "$userGrade ${userNickname}님 안녕하세요!" +
                "현재 문제수 $userProblemsNums, 다크 테마 $appTheme 입니다."

        view.main1131.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

        view.main1121.setOnClickListener {
            val intent = Intent(this, AnalysisActivity::class.java)
            startActivity(intent)
        }

        view.main1141.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }
}