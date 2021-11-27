package kr.ac.kumoh.s20170419.everydaymath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityAnalysisBinding

class AnalysisActivity : AppCompatActivity() {
    private lateinit var view: ActivityAnalysisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(view.root)

        setSupportActionBar(view.appToolbar)
        val dataSet = (1..50).toList()
        view.recyclerView.adapter = SampleAdapter(dataSet)
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