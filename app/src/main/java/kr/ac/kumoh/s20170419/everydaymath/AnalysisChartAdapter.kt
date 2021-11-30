package kr.ac.kumoh.s20170419.everydaymath

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList


class AnalysisChartAdapter(
    private val dataSet: List<Any>
) : RecyclerView.Adapter<AnalysisChartAdapter.ChartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val layoutView: LinearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chart, parent, false) as LinearLayout

        return ChartViewHolder(layoutView)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        if(position < dataSet.size) {
//            holder.number.text = dataSet[position].toString()
            val entryList = mutableListOf<BarEntry>()
            // 여기서 엔트리 리스트 작성
            when (position) {
                0 ->  {
                    holder.title.text = "주간 성적"

                    val recentGrades = dataSet[0] as List<Float>

                    for ((index, value) in recentGrades.withIndex()) {
                        entryList.add(BarEntry(index.toFloat(), value))
                    }
                }

                1 -> {
                    holder.title.text = "선호하는 문제 수(시행한 횟수)"

                    val problemNums = dataSet[1] as HashMap<String, Int>
                    val keySet = problemNums.keys
                    val labels = ArrayList<String>()

                    for ((index, key) in keySet.withIndex()) {
                        entryList.add(BarEntry(index.toFloat(), problemNums[key]!!.toFloat()))
                        labels.add(key + "문제")
                    }

                    holder.barChart.xAxis.valueFormatter = object: ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return labels[value.toInt()]
                        }
                    }
                }

                2 -> {
                    holder.title.text = "한 문제당 걸리는 시간(초)"

                    val recentGrades = dataSet[2] as List<Float>
                    val labels = arrayOf("최장", "평균", "최단")

                    for ((index, value) in recentGrades.withIndex()) {
                        entryList.add(BarEntry(index.toFloat(), value))
                    }

                    holder.barChart.xAxis.valueFormatter = object: ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return labels[value.toInt()]
                        }
                    }
                }

                else -> {
                    holder.title.text = "나머지"
                }
            }
            initChart(holder, entryList)
        }
    }

    private fun initChart(
        holder: ChartViewHolder,
        entryList: MutableList<BarEntry>
    ) {
        // #2 바 데이터 셋
        val barDataSet = BarDataSet(entryList, "MyBarDataSet")
        barDataSet.color = ColorTemplate.rgb("#F2CED1")
        // #3 바 데이터
        val barData = BarData(barDataSet)
        barData.barWidth = 0.7f
        holder.barChart.data = barData
        holder.barChart.apply {
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
        holder.barChart.invalidate()
    }

    inner class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.chart_title) as TextView
        val barChart = itemView.findViewById(R.id.bar_chart) as BarChart
    }
}