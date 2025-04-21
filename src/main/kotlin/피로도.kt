fun main(args: Array<String>) {

    val dungeons = arrayOf(
        intArrayOf(80, 20),
        intArrayOf(50, 40),
        intArrayOf(30, 10)
    )


    solution2(80, dungeons ).also {
        println(it)
    }
}

fun solution2(k: Int, dungeons: Array<IntArray>): Int {
    val visited = BooleanArray(dungeons.size)
    return dfs(k, 0, dungeons, visited)
}

fun dfs(k: Int, count: Int, dungeons: Array<IntArray>, visited: BooleanArray): Int {
    var maxCount = count

    for (i in dungeons.indices) {
        val (minRequired, cost) = dungeons[i]

        if (!visited[i] && k >= minRequired) {
            visited[i] = true
            val result = dfs(k - cost, count + 1, dungeons, visited)
            maxCount = maxOf(maxCount, result)
            visited[i] = false // 백트래킹
        }
    }

    return maxCount
}
