package kr.ac.kumoh.s20170419.everydaymath
import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt

class AnalysisManager(private val userLog: String) {
    //            ulm.writeTextFile("2021-11-23,23,40,200\n"
//                                + "2021-11-24,23,40,340\n"
//                                + "2021-11-25,23,40,290\n"
//                                + "2021-11-26,23,40,298\n"
//                                + "2021-11-27,23,40,600\n"
//                                + "2021-11-28,23,40,960\n"
//                                + "2021-11-29,23,40,320\n")

    private val userLogList = initList()

    private fun initList(): ArrayList<List<String>> {
        val tmpList = userLog.split("\n")
        var userLogList = ArrayList<List<String>>()
        for (i in tmpList.indices) userLogList.add(tmpList[i].split(","))
        userLogList.removeAt(userLogList.size - 1)
        return userLogList
    }

    fun getTimes(): List<Float> {
        var timeList = ArrayList<Float>()
        for (i in userLogList) timeList.add((i[3].toFloat() / i[2].toFloat() * 100).roundToInt() / 100f)
        return listOf<Float>(
            timeList.maxOrNull()!!,
            timeList.minOrNull()!!,
            (timeList.average() * 100).roundToInt() / 100f)
    }

    fun getProblemNums(): HashMap<String, Int> {
        var problemNumsList = ArrayList<String>()
        var result = HashMap<String, Int>()
        for (i in userLogList) problemNumsList.add(i[2])
        for (i in problemNumsList) {
            if (!result.containsKey(i)) result[i] = 1
            else result.replace(i, result[i]!! + 1)
        }
        return result
    }

    fun getCurrentGrades(): ArrayList<Float> {
        var gradeList = ArrayList<Float>()
        for (i in userLogList) gradeList.add(((i[1].toFloat() / i[2].toFloat()) * 10000).roundToInt() / 100f)
        return gradeList
    }
}