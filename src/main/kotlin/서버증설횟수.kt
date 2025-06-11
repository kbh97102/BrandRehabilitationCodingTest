fun main(args: Array<String>) {
    서버증설횟수().solution(
        intArrayOf(
            0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5
        ),
        m = 3,
        k = 5
    )
        .also {
            println(it)
        }
}

class 서버증설횟수 {

    fun solution(players: IntArray, m: Int, k: Int): Int {
        var answer: Int = 0

        val serverMap = HashMap<Int, Int>()

        for (i in players.indices) {
            val playerCount = players[i]

            // 만료된 서버 삭제
            val serverExpired = serverMap.containsKey(i - k)

            if (serverExpired) {
                serverMap.remove(i - k)
            }

            // 증설이 필요함
            if (playerCount >= m) {
                // 몇개의 서버가 필요한지
                val targetServer = playerCount / m

                // 지금 있는 서버
                val currentServer = serverMap.values.sum()

                // 수용량을 넘는 경우 증설
                if (targetServer > currentServer) {
                    val increase = targetServer - currentServer
                    serverMap[i] = increase
                    answer += increase
                }
            }
        }


        return answer
    }

}