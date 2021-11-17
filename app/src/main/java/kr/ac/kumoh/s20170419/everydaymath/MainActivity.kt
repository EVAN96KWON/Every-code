package kr.ac.kumoh.s20170419.everydaymath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var view: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

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