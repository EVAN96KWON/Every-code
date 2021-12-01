package kr.ac.kumoh.s20170419.everydaymath

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SettingsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val appToolbar = findViewById<Toolbar>(R.id.app_actionbar)
        setSupportActionBar(appToolbar)

        // 유저 정보 불러오기
//        val sps = PreferenceManager.getDefaultSharedPreferences(this)
//        val userGrade = sps.getString("user_grade", "")
//        val userProblemsNums = sps.getString("user_problems_nums", "")
//        val appTheme = sps.getBoolean("app_theme", false)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F

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