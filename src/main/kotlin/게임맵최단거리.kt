import java.util.*

fun main(args: Array<String>) {
    val board = arrayOf(
        intArrayOf(1, 0, 1, 1, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 1),
        intArrayOf(1, 1, 1, 0, 1),
        intArrayOf(0, 0, 0, 0, 1)
    )
    게임맵최단거리().bfs(
        maps = board,
        queue = LinkedList()
    )
}

//https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
class 게임맵최단거리 {

    fun solution(maps: List<List<Int>>): Int {

        val queue = LinkedList<Pair<Int, Int>>()
        val visited = ArrayList<BooleanArray>()

        return 0
    }

    fun bfs(maps: Array<IntArray>, queue: LinkedList<Pair<Int, Int>>) {

        val n = maps.size
        val m = maps.first().size
        val visited = Array(n) { BooleanArray(m) }
        val distance = Array(n) { IntArray(m) { -1 } }

        queue.push(Pair(0, 0))
        visited[0][0] = true
        distance[0][0] = 1

        val directions = listOf(
            Pair(-1, 0),    // 상
            Pair(1, 0),     // 하
            Pair(0, -1),    // 좌
            Pair(0, 1)      // 우
        )

        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            for ((dx, dy) in directions) {
                val nx = x + dx
                val ny = y + dy

                if (nx in 0 until n && ny in 0 until m && !visited[nx][ny] && maps[nx][ny] == 1) {
                    visited[nx][ny] = true
                    distance[nx][ny] = distance[x][y] + 1
                    queue.add(Pair(nx, ny))
                }
            }
        }
    }


}