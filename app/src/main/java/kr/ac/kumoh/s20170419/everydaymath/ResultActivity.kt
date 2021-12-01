package kr.ac.kumoh.s20170419.everydaymath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var view: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityResultBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_result)
    }
}