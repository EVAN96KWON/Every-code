package kr.ac.kumoh.s20170419.everydaymath

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kr.ac.kumoh.s20170419.everydaymath.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var view: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        //setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        startAnimations()

        view.btnSettings.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SettingsActivity::class.java
                )
            )
            overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_up_exit)
        }

        view.btnGotoTest.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    TestActivity::class.java
                )
            )
            overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_up_exit)
        }

        view.barChart.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AnalysisActivity::class.java
                )
            )
            overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_up_exit)
        }

        view.btnGotoAnalysis.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AnalysisActivity::class.java
                )
            )
            overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_up_exit)
        }
    }

    private fun startAnimations() {
        val translateUpAnim1 = AnimationUtils.loadAnimation(this, R.anim.translate_up)
        val translateUpAnim2 = AnimationUtils.loadAnimation(this, R.anim.translate_up)
        translateUpAnim2.startOffset = 100

        view.mainContents1.startAnimation(translateUpAnim1)
        view.btnGotoTest.startAnimation(translateUpAnim2)
    }

    override fun onResume() {
        super.onResume()

        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        val userNickname = sps.getString("user_nickname", "사용자")
        val userGrade = sps.getString("user_grade", "1학년")
        val userProblemsNums = sps.getString("user_problems_nums", "10")
        val appTheme = sps.getBoolean("app_theme", false)

        view.mainTitle.text = "${userNickname}님 안녕하세요\uD83D\uDE0E"
        view.mainSubTitle.text = "${userGrade}, ${userProblemsNums}문제"

        if (appTheme) ThemeManager.applyTheme(ThemeManager.ThemeMode.DARK)
        else ThemeManager.applyTheme(ThemeManager.ThemeMode.LIGHT)

        initChart()
    }

    private fun initChart() {
        val entryList = mutableListOf<BarEntry>()
        val am = AnalysisManager(UserLogManager(filesDir).readTextFile())
        val recentGrades = am.getRecentGrades() as List<Float>
        val labels = am.getRecentDaysLabel() as ArrayList<String>

        for ((index, value) in recentGrades.withIndex()) {
            entryList.add(BarEntry(index.toFloat(), value))
        }

        // #2 바 데이터 셋
        val barDataSet = BarDataSet(entryList, "MyBarDataSet")
        barDataSet.color = ColorTemplate.rgb("#F2CED1")

        // #3 바 데이터
        val barData = BarData(barDataSet)
        barData.barWidth = 0.7f
        view.barChart.data = barData
        view.barChart.apply {
            //터치, Pinch 상호작용
            setTouchEnabled(false)
            setScaleEnabled(true)
            setPinchZoom(false)

            legend.isEnabled = false

            // Chart 가 그려질때 애니메이션
            animateXY(300, 500)

            //Chart 밑에 description 표시 유무
            description = null

            //Legend는 차트의 범례를 의미합니다
            //범례가 표시될 위치를 설정
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT

            //차트의 좌, 우측 최소/최대값을 설정합니다.
            //차트 제일 밑이 0부터 시작하고 싶은 경우 설정합니다.
            axisLeft.axisMinimum = 0f
            axisRight.axisMinimum = 0f

            //기본적으로 차트 우측 축에도 데이터가 표시됩니다
            //이를 활성화/비활성화 하기 위함
            axisRight.setDrawLabels(false)

            //xAxis, yAxis 둘다 존재하여 따로 설정이 가능합니다
            //차트 내부에 Grid 표시 유무
            xAxis.setDrawGridLines(false)

            //x축 데이터 표시 위치
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            //x축 데이터 갯수 설정
            xAxis.labelCount = entryList.size

            xAxis.granularity = 1f
        }

        view.barChart.xAxis.valueFormatter = object: ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return labels[value.toInt()]
            }
        }

        view.barChart.invalidate()
    }
}