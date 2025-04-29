import java.util.*

fun main(args: Array<String>) {

    val subjects = arrayOf(
        arrayOf("korean", "11:40", "30"),
        arrayOf("english", "12:10", "20"),
        arrayOf("math", "12:30", "40")
    )

    val subjects2 = arrayOf(
        arrayOf("science", "12:40", "50"),
        arrayOf("music", "12:20", "40"),
        arrayOf("history", "14:00", "30"),
        arrayOf("computer", "12:30", "100")
    )

    과제진행하기().solution(
        subjects2
    ).also {
        println(it.contentDeepToString())
    }
}


class 과제진행하기 {

    data class Plan(
        val name: String,
        val startMin: Int,
        val duration: Int,
        var remain: Int
    )

    fun solution(plans: Array<Array<String>>): Array<String> {
        val answer = ArrayList<String>()

        val queue = LinkedList<Plan>()
        val suspendQueue = LinkedList<Plan>()


        plans.sortedBy { it[1] }.forEach {
            val min = getMin(it[1])
            queue.add(
                Plan(it[0], min, it[2].toInt(), it[2].toInt())
            )
        }

        var currentTime = queue.peek().startMin

        while (queue.isNotEmpty()) {
            val currentPlan = queue.poll()
            // 중단된 과제를 모두 수행했는데도 다음 과제 시간까지 남은 경우를 위한 if문
            // 과제 시작 시간에 과제를 시작
            if (currentTime < currentPlan.startMin) {
                currentTime = currentPlan.startMin
            }

            val nextStart = queue.peek()?.startMin ?: Int.MAX_VALUE
            val availableTime = nextStart - currentTime

            // 다음 과제 시작 시간 전에 과제 완료
            if (currentPlan.remain <= availableTime) {
                currentTime += currentPlan.remain
                answer.add(currentPlan.name)
            } else {
                // 다음 과제 시작 전까지 모두 못마친 경우 , + 이 경우에는 중단된 과제를 할 시간이 없음
                currentPlan.remain -= availableTime
                suspendQueue.add(currentPlan)
                currentTime = nextStart
                continue
            }

            // 다음 과제 시작 전까지 중단된 과제들 처리
            while (suspendQueue.isNotEmpty()) {
                val pausedPlan = suspendQueue.pollLast()
                val nextStartTime = queue.peek()?.startMin ?: Int.MAX_VALUE
                val suspendAvailable = nextStartTime - currentTime

                if (pausedPlan.remain <= suspendAvailable) {
                    currentTime += pausedPlan.remain
                    answer.add(pausedPlan.name)
                } else {
                    pausedPlan.remain -= suspendAvailable
                    suspendQueue.addLast(pausedPlan)
                    currentTime = nextStartTime
                    break
                }
            }
        }

        while (suspendQueue.isNotEmpty()) {
            answer.add(suspendQueue.pollLast().name)
        }

        return answer.toTypedArray()
    }

    private fun getMin(date: String): Int {
        val tokens = date.split(":")

        return tokens.first().toInt() * 60 + tokens[1].toInt()
    }

}