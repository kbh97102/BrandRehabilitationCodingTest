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


        val current = wires.first()


        val route = Array(n + 1) { ArrayList<Int>() }

        for ((start, end) in wires) {
            route[start].add(end)
            route[end].add(start)
        }


        for ((first, second) in wires) {

            route[first].remove(second)
            route[second].remove(first)

            val visited = BooleanArray(n + 1)

            println("Root #1 ($first , $second)")

            dfs(first, route, visited)

            println("Root #2 ($first , $second)")

            route[first].add(second)
            route[second].add(first)
        }


        return test
    }

    fun dfs(start: Int, route: Array<ArrayList<Int>>, visited: BooleanArray): Int {

        println("dfs #1 start $start")

        visited[start] = true

        println("Visited ${visited.contentToString()}")

        var count = 0

        return count
    }

}