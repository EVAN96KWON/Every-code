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
        val am = AnalysisManager(UserLogManager(filesDir).readTextFile())
        val dataSet = listOf<Any>(am.getCurrentGrades(), am.getProblemNums(), am.getTimes())
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