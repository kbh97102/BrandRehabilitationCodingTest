fun main(args: Array<String>) {
    호텔대실()
        .solution(
            arrayOf(
                arrayOf("15:00", "17:00"),
                arrayOf("16:40", "18:20"),
                arrayOf("14:20", "15:20"),
                arrayOf("14:10", "19:20"),
                arrayOf("18:20", "21:20")
            )
        )
        .also {
            println("Answer $it")
        }
}


class 호텔대실 {

    fun solution(book_time: Array<Array<String>>): Int {
        var answer: Int = 0

        val time = book_time.map {
            var startTime = 0
            var endTime = 0
            it.first().split(":")
                .forEachIndexed { index, s ->
                    startTime += if (index == 0) {
                        s.toInt() * 60
                    } else {
                        s.toInt()
                    }
                }

            it[1].split(":")
                .forEachIndexed { index, s ->
                    endTime += if (index == 0) {
                        s.toInt() * 60
                    } else {
                        s.toInt()
                    }
                }


            Pair(startTime, endTime)
        }.sortedBy { it.first }

        val roomMap = mutableMapOf<Int, Int>() // 방번호 -> 끝나는 시간(+청소)
        var roomCount = 0

        time.forEach { (start, end) ->
            var assigned = false

            for ((room, endTime) in roomMap) {
                if (start >= endTime + 10) { // 청소 끝난 후에 시작 가능
                    roomMap[room] = end
                    assigned = true
                    break
                }
            }

            if (!assigned) {
                roomCount++
                roomMap[roomCount] = end
            }
        }

        return roomCount
    }

}