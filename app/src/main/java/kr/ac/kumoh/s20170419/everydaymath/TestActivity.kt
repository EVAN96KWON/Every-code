package kr.ac.kumoh.s20170419.everydaymath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityTestBinding
import kotlin.random.Random

class TestActivity : AppCompatActivity() {
    private lateinit var view : ActivityTestBinding

    private var num1 = (1..10).random()
    private var num2 = (1..10).random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityTestBinding.inflate(layoutInflater)

        setContentView(view.root)

        view.testLine1.setText("${num1}")
        view.testLine2.setText("${num2}")


        for (i in 1 until 20)
        {
            view.nextTest.setOnClickListener {
                var num3 = (1..10).random()
                var num4 = (1..10).random()

                view.testLine1.setText("${num3}")
                view.testLine2.setText("${num4}")
            }
        }

    }
}

