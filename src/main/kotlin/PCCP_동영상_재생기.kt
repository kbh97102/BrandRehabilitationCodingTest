
//https://school.programmers.co.kr/learn/courses/30/lessons/340213

class PCCP_동영상_재생기 {
    fun solution(
        video_len: String,
        pos: String,
        op_start: String,
        op_end: String,
        commands: Array<String>
    ): String {
        // 문자열 시간을 초 단위로 변환
        fun toSeconds(time: String): Int {
            val (mm, ss) = time.split(":").map { it.toInt() }
            return mm * 60 + ss
        }

        // 초를 "mm:ss" 형식으로 변환
        fun toTimeFormat(seconds: Int): String {
            val mm = seconds / 60
            val ss = seconds % 60
            return "%02d:%02d".format(mm, ss)
        }

        val videoLength = toSeconds(video_len)
        val openingStart = toSeconds(op_start)
        val openingEnd = toSeconds(op_end)
        var currentPos = toSeconds(pos)

        for (command in commands) {

            when (command) {
                "prev" -> {
                    if (currentPos in openingStart..openingEnd) {
                        currentPos = openingEnd
                    }
                    currentPos = maxOf(0, currentPos - 10)
                }
                "next" -> {
                    if (currentPos in openingStart..openingEnd) {
                        currentPos = openingEnd
                    }
                    currentPos = minOf(videoLength, currentPos + 10)
                }
            }

        }

        if (currentPos in openingStart..openingEnd) {
            currentPos = openingEnd
        }

        return toTimeFormat(currentPos)
    }

}

fun main(args: Array<String>) {
    val test = PCCP_동영상_재생기()

//    test.solution("34:33", "13:00", "00:55", "02:55", arrayOf("next", "prev")).also {
//        println("result1 $it")
//    }
//
//    test.solution(video_len = "10:55", pos = "00:05", op_start = "00:15", op_end = "06:55", commands = arrayOf("prev", "next", "next")).also {
//        println("result2 $it")
//    }

//    test.solution(	"07:22", "04:05", "00:15", "04:07", arrayOf("next")).also {
//        println("result3 $it")
//    }

    test.solution("30:00", "15:00", "15:10", "15:30", arrayOf("next", "next")).also {
        println("result3 $it")
    }

}