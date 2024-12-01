//https://school.programmers.co.kr/learn/courses/30/lessons/176962


/**
 * 이거 기준으로 2~7번 실패, 19번 실패
 * 24 TC 중 7개 실패
 */
class 제출_내역 {

    data class TaskInfo(
        val planName: String,
        val startMinute: Int,
        val duration: Int,
        var remainMinute: Int
    )

    fun solution(plans: Array<Array<String>>): Array<String> {
        val answer = ArrayList<String>()

        fun getMinute(timeString: String): Int {
            val (hour, min) = timeString.split(":").map { it.toInt() }

            return hour * 60 + min
        }

        val planMap = HashMap<String, Pair<String, String>>()

        val taskQueue = ArrayDeque<TaskInfo>()

        // 시작시간으로 정렬
        plans.sortBy {
            it[1]
        }
        plans.forEach {
            taskQueue.add(
                TaskInfo(
                    it[0],
                    getMinute(it[1]),
                    it[2].toInt(),
                    remainMinute = it[2].toInt()
                )
            )
        }

        var currentMinute = 0

        while (true) {
            if (taskQueue.isEmpty()) {
                break
            }

            val first = taskQueue.first()

            if (currentMinute == 0) {
                currentMinute = first.startMinute
            }

            val currentTask = if (first.remainMinute != first.duration || currentMinute < first.startMinute) {
                taskQueue.removeLast()
            } else {
                taskQueue.removeFirst()
            }


            val nextTask = if (taskQueue.isEmpty() || taskQueue.first().remainMinute != taskQueue.first().duration) {
                null
            } else {
                taskQueue.firstOrNull()
            }



            if (nextTask != null) {
                when {
                    // 다음 작업 진행 전까지 현제의 작업을 완료할 수 있다면
                    currentTask.remainMinute + currentMinute <= nextTask.startMinute -> {
                        answer.add(currentTask.planName)
                        currentMinute += currentTask.remainMinute
                    }
                    // 다음 직업 진행 전까지 모두 마칠 수 없는 경우
                    currentTask.remainMinute + currentMinute > nextTask.startMinute -> {
                        val delta = nextTask.startMinute - currentMinute
                        currentTask.remainMinute -= delta
                        taskQueue.add(currentTask)
                        currentMinute += delta
                    }
                }

            } else {
                // 지금 작업이 마지막 작업임
                answer.add(currentTask.planName)
                currentMinute += currentTask.remainMinute
            }
        }

        return answer.toTypedArray()
    }
}

fun main(args: Array<String>) {
    val test = 제출_내역()

    val plans1 = arrayOf(
        arrayOf("korean", "11:40", "30"),
        arrayOf("english", "12:10", "20"),
        arrayOf("math", "12:30", "40")
    )
    val result1 = arrayOf("korean", "english", "math")

    val plans2 = arrayOf(
        arrayOf("science", "12:40", "50"),
        arrayOf("music", "12:20", "40"),
        arrayOf("history", "14:00", "30"),
        arrayOf("computer", "12:30", "100")
    )
    val result2 = arrayOf("science", "history", "computer", "music")

    val plans3 = arrayOf(
        arrayOf("aaa", "12:00", "20"),
        arrayOf("bbb", "12:10", "30"),
        arrayOf("ccc", "12:40", "10")
    )
    val result3 = arrayOf("bbb", "ccc", "aaa")


//    println("result1 ${test.solution(plans1).contentDeepToString()}")
    println("result2 ${test.solution(plans2).contentDeepToString()}")
//    println("result3 ${test.solution(plans3).contentDeepToString()}")
}