package kr.ac.kumoh.s20170419.everydaymath

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityAnalysisBinding

class AnalysisActivity : AppCompatActivity() {
    private lateinit var view: ActivityAnalysisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(view.root)

        setSupportActionBar(view.appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        val dataSet = (1..15).toList()
        UserLogManager(filesDir).writeTextFile(
            "2021-11-23,23,30,200\n" +
                    "2021-11-24,23,30,340\n" +
                    "2021-11-25,23,40,290\n" +
                    "2021-11-26,40,40,298\n" +
                    "2021-11-27,23,40,600\n" +
                    "2021-11-28,13,20,960\n" +
                    "2021-11-29,23,40,320\n"
        )
        val am = AnalysisManager(UserLogManager(filesDir).readTextFile())
        val dataSet = listOf<Any>(am.getRecentGrades(), am.getProblemNums(), am.getTimes())
        view.recyclerView.adapter = AnalysisChartAdapter(dataSet)
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
}