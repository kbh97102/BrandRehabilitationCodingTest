import kotlin.math.abs

fun main(args: Array<String>) {
    val edges = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
        intArrayOf(4, 6),
        intArrayOf(4, 7),
        intArrayOf(7, 8),
        intArrayOf(7, 9)
    )
    전력망().solution(9, edges)
        .also {
            println("test $it")
        }
}


class 전력망 {

    var test = Int.MAX_VALUE

    fun solution(n: Int, wires: Array<IntArray>): Int {
        var answer: Int = -1

        val visited = BooleanArray(n)
        val current = wires.first()
        visited[current.first()] = true

        dfs(wires, visited, current = current.first())

        return test
    }

    /*
    트리가 끊어진 경우는 주어지지 않음
    하나씩 끊어보고 아니면 나아가는 방식을 해야할 것 같다
    백트래킹을 해야할 것 같은데 dfs를 어떻게 구성해야할까

     */


    fun dfs(wires: Array<IntArray>, visited: BooleanArray, current: Int) {

        wires.filter { node -> current == node.first() && !visited[node[1]-1] }
            .forEach {
                visited[it[1]-1] = true
                val route = visited.count { visitedNode -> visitedNode }
                val notVisited = visited.count { visitedNode -> !visitedNode }
                if (abs(route - notVisited) < test) {
                    test = abs(route - notVisited)
                }
                dfs(wires, visited, it[1]-1)
            }

    }

}