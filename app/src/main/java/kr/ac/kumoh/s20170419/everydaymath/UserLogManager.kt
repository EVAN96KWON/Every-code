package kr.ac.kumoh.s20170419.everydaymath

import java.io.*

/**
 *  written by Kwon, 11-29
 *  @author EVAN96KWON
 *  @version 1.0, added readTextFile(), writeTextFile(content:String)
 *  @see
 *  fun readTextFile() -> user_log.txt 파일 전체를 읽어 옵니다
 *  fun writeTextFile(content:String) -> user_log.txt 에 파라미터로 넣은 content를 작성합니다.
 *  사용하실 때 :
 *  1. 액티비티 클래스에서 UserLogManager(filesDir) 클래스 선언하시고,
 *  클래스 내부에 함수 불러 쓰시면 됩니다.
 *  2. 파일 위치랑 파일 명은 사용상 편의를 위해 고정시켜 놓았습니다.
 *  3. 사용하실 때 파일 전체 불러온 다음에 그 다음에 추가로 작성하셔야 합니다!
 *  이미 있는 파일에 쓰면 전에 있던 내용 다 날아감.
**/

class UserLogManager(private val filesDir: File) {
    private val directoryName = "user_log"
    private val fileName = "user_log.txt"
    private val path = "${directoryName}/${fileName}"

    /**
     * @param None
     * @return 읽은 파일의 내용
     */
    fun readTextFile(): String {
        val fullPath = File(filesDir.path + "/" + path)
        if(!fullPath.exists()) return ""
        val reader = FileReader(fullPath)
        val buffer = BufferedReader(reader)
        val result = StringBuilder() // StringBuffer()
        var temp:String? = ""
        while(true) {
            temp = buffer.readLine()
            if(temp == null) break
            result.append(temp).append("\n")
        }
        buffer.close()
        reader.close()
        return result.toString()
    }

    /**
     * @param 작성할 내용
     * @return None
     */
    fun writeTextFile(content:String) {
        // 앱 기본경로 / files / memo
        val dir = File(filesDir.path + "/" + directoryName)
        if(!dir.exists()) dir.mkdirs()
        val fullPath = dir.path + "/" + fileName
        val writer = FileWriter(fullPath)
        val buffer = BufferedWriter(writer)
        buffer.write(content)
        buffer.close()
        writer.close()
    }
}