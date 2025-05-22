import java.util.*

fun main(args: Array<String>) {

}

class 석유시추 {

    fun solution(land: Array<IntArray>): Int {
        val n = land.size
        val m = land[0].size

        val visited = Array(n) { BooleanArray(m) }

        val colToAreas = mutableMapOf<Int, MutableSet<Int>>() // X열을 기준으로 몇개의 석유영역이 있는지에 대한 map, key는 x index, value는 걸려있는 석유영역들의 번호
        val areaSize = mutableListOf<Int>()
        var areaIndex = 0

        // 석유 영역을 구하면서 사이즈와 한 Column에 몇개의 석유 영역이 걸려있는지 체크
        for (y in 0 until n) {
            for (x in 0 until m) {
                if (!visited[y][x] && land[y][x] == 1) {
                    val size = bfs(land, x, y, visited, areaIndex, colToAreas)
                    areaSize.add(size)
                    areaIndex++
                }
            }
        }

        var maxOil = 0
        for (x in 0 until m) {
            val areas = colToAreas[x] ?: continue
            val total = areas.sumOf { areaSize[it] }
            maxOil = maxOf(maxOil, total)
        }

        return maxOil
    }


    fun bfs(
        land: Array<IntArray>,
        startX: Int,
        startY: Int,
        visited: Array<BooleanArray>,
        areaIndex: Int,
        colToAreas: MutableMap<Int, MutableSet<Int>>
    ): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(startX to startY)
        visited[startY][startX] = true

        // 이동할 방향
        val directions = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        var count = 0
        val usedCols = mutableSetOf<Int>()

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            count++
            usedCols.add(x)

            for ((dx, dy) in directions) {
                val nx = x + dx
                val ny = y + dy
                if (ny in land.indices && nx in land[0].indices &&
                    !visited[ny][nx] && land[ny][nx] == 1
                ) {
                    visited[ny][nx] = true
                    queue.add(nx to ny)
                }
            }
        }

        for (col in usedCols) {
            colToAreas.getOrPut(col) { mutableSetOf() }.add(areaIndex)
        }

        return count
    }

}