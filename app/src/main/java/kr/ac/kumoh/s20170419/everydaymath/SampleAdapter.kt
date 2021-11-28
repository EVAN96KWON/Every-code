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
import com.github.mikephil.charting.utils.ColorTemplate


class SampleAdapter(
    private val dataSet: List<Int>
) : RecyclerView.Adapter<SampleAdapter.NumberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val layoutView: LinearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chart, parent, false) as LinearLayout

        return NumberViewHolder(layoutView)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        if(position < dataSet.size) {
//            holder.number.text = dataSet[position].toString()
            // #1 엔트리 리스트
            val entryList = mutableListOf<BarEntry>()
            entryList.add(BarEntry(0f,1f))
            entryList.add(BarEntry(1f,5f))
            entryList.add(BarEntry(2f,8f))
            entryList.add(BarEntry(3f,3f))
            entryList.add(BarEntry(4f,6f))
            entryList.add(BarEntry(5f,2f))
            // #2 바 데이터 셋
            val barDataSet = BarDataSet(entryList, "MyBarDataSet")
            barDataSet.color = ColorTemplate.rgb("#ff7b22")
            // #3 바 데이터
            val barData = BarData(barDataSet)
            barData.barWidth = 0.5f
            holder.number.data = barData
            holder.number.apply {
                //터치, Pinch 상호작용
                setScaleEnabled(false)
                setPinchZoom(false)

                //Chart가 그려질때 애니메이션
                animateXY(300,400)

                //Chart 밑에 description 표시 유무
                description=null

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
            }
            holder.number.invalidate()
        }
    }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number = itemView.findViewById(R.id.bar_chart) as BarChart
    }
}