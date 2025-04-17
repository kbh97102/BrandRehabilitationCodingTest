// https://school.programmers.co.kr/learn/courses/30/lessons/43162?language=kotlin

fun main(args: Array<String>) {

    val computers = arrayOf(
        intArrayOf(1, 1, 0),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 0, 1)
    )

    solution(n = 3, computers)

}


fun solution(n: Int, computers: Array<IntArray>): Int {
    var answer = 0

    val visited = BooleanArray(computers.size)

    for (i in 0 until n) {
        if (!visited[i]) {
            dfs(currentComputer = i, computers, visited)
            answer++
        }
    }

    return answer
}

fun dfs(currentComputer: Int, computers: Array<IntArray>, visited: BooleanArray) {

    visited[currentComputer] = true

    for (index in computers[currentComputer].indices) {
        if (computers[currentComputer][index] == 1 && !visited[index]) {
            dfs(index, computers, visited)
        }
    }

}