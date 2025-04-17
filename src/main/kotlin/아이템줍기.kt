import java.util.*

fun main(args: Array<String>) {

    val array = arrayOf(
        intArrayOf(1, 1, 7, 4),
        intArrayOf(3, 2, 5, 5),
        intArrayOf(4, 3, 6, 9),
        intArrayOf(2, 6, 8, 8)
    )

    bfs(array, 1, 3, 7, 8)
        .let {
            println("test $it")
        }
}

fun bfs(rectangle: Array<IntArray>, characterX: Int, characterY: Int, itemX: Int, itemY: Int): Int {


    val queue = LinkedList<IntArray>()

    /**
     * 맵 사이즈를 2배로 늘린 경우는 사이즈가 1칸짜리인 경우
     * 1 1 1 1
     * 1 2 2 1
     * 1 2 2 1
     * 1 1 1 1
     *
     * 0 0 0 0 0
     * 0 1 1 1 1
     * 0 1 1 1 1
     * 0 0 0 0 0
     *
     * 이 두개가 겹치는 경우 위 사각형의 오른쪽 변이 아래 사각형의 중간을 꿇고 지나가는데
     * 이 경우는 좌표가 딱 붙어있다보니 필터링이 안됨
     * 이 사각형들의 사이즈를 전체적으로 2배로 증가시키면 이렇게 딱 붙어있는 경우가 없어짐
     */

    val map = Array(102) { IntArray(102) }
    val visited = Array(102) { BooleanArray(102) }

    queue.add(intArrayOf(characterX * 2, characterY * 2, 0))

    val directions = listOf(
        Pair(-1, 0),    // 상
        Pair(1, 0),     // 하
        Pair(0, -1),    // 좌
        Pair(0, 1)      // 우
    )

    for (rect in rectangle) {
        val (lx, ly, rx, ry) = rect.map { it * 2 }

        for (x in lx..rx) {
            for (y in ly..ry) {
                // 테두리는 1, 내부는 2
                if (x == lx || x == rx || y == ly || y == ry) {
                    if (map[x][y] != 2) map[x][y] = 1
                } else {
                    map[x][y] = 2
                }
            }
        }
    }

    while (queue.isNotEmpty()) {

        val (x, y, distance) = queue.poll()

        if (itemX * 2 == x && itemY * 2 == y) {
            return distance/2
        }

        for ((dirX, dirY) in directions) {
            val nx = x + dirX
            val ny = y + dirY

            if (nx in 0..100 && ny in 0..100 && !visited[nx][ny] && map[nx][ny] ==1) {
                visited[nx][ny] = true
                queue.add(intArrayOf(nx, ny, distance + 1))
            }
        }
    }

    return -1


}
