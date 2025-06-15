import java.util.*

fun main(args: Array<String>) {
    리코쳇()
        .solution(
            arrayOf(
                "...D..R",
                ".D.G...",
                "....D.D",
                "D....D.",
                "..D...."
            )
        )
}

class 리코쳇 {

    data class Node(
        var x: Int, var y: Int, var count: Int
    )

    fun solution(board: Array<String>): Int {
        var answer: Int = -1

        val visited = Array(board.size) {
            BooleanArray(board[0].length) { false }
        }

        val queue = LinkedList<Node>()

        val directions = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0),
        )

        loop@ for (y in board.indices) {
            for (x in board[0].indices) {
                if (board[y][x] == 'R') {
                    queue.add(
                        Node(
                            x = x, y = y, count = 0
                        )
                    )
                    break@loop
                }
            }
        }



        while (queue.isNotEmpty()) {
            val (x, y, count) = queue.poll()

            if (board[y][x] == 'G') return count

            for ((dy, dx) in directions) {
                val (ny, nx) = move(board, dy to dx, y to x)

                if (ny in board.indices && nx in board[0].indices && !visited[ny][nx]) {
                    visited[ny][nx] = true
                    queue.add(Node(nx, ny, count + 1))
                }
            }
        }


        return answer
    }

    fun move(board: Array<String>, direction: Pair<Int, Int>, start: Pair<Int, Int>): Pair<Int, Int> {
        var y = start.first
        var x = start.second

        while (true) {
            val nextY = y + direction.first
            val nextX = x + direction.second

            // 경계 또는 장애물 체크
            if (nextY !in board.indices || nextX !in board[0].indices || board[nextY][nextX] == 'D') {
                break
            }

            y = nextY
            x = nextX
        }

        return y to x
    }
}