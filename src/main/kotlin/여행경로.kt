// https://school.programmers.co.kr/learn/courses/30/lessons/43164?language=kotlin

fun main(args: Array<String>) {
    val tickets: Array<Array<String>> = arrayOf(
        arrayOf("ICN", "A"),
        arrayOf("A", "B"),
        arrayOf("A", "C"),
        arrayOf("B", "A"),
        arrayOf("C", "A")
    )


    solution(tickets).also {
        println(it.contentDeepToString())
    }
}

fun solution(tickets: Array<Array<String>>): Array<String> {
    var answer = ArrayList<String>()


    val visited = BooleanArray(tickets.size)

    println("Before ${tickets.contentDeepToString()}")

    tickets.sortWith(compareBy({ it[0] }, { it[1] }))

    println("After ${tickets.contentDeepToString()}")

    dfs(tickets, visited, "ICN", answer, 0)


    return answer.toTypedArray()
}

fun dfs(
    tickets: Array<Array<String>>,
    visited: BooleanArray,
    current: String,
    answer: ArrayList<String>,
    count: Int
): Boolean {
    answer.add(current)

    if (count == tickets.size) {
        return true
    }

    for (i in tickets.indices) {
        if (!visited[i] && tickets[i][0] == current) {
            visited[i] = true
            if (dfs(tickets, visited, tickets[i][1], answer, count + 1)) return true
            visited[i] = false
        }
    }

    // 실패했을 경우 경로에서 제거 (백트래킹)
    answer.removeLast()
    return false
}