class 달리기_경주 {

    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val map = players.withIndex().associate {
            it.value to it.index
        }.toMutableMap()

        callings.forEach {
            val index = map[it]

            if (index != null) {
                map[it] = index - 1
                map[players[index - 1]] = index

                if (index > 0) {
                    val temp = players[index]
                    players[index] = players[index - 1]
                    players[index - 1] = temp
                }
            }
        }

        return map.entries.sortedBy { it.value }
            .map { it.key }.toTypedArray()
    }

}

fun main(args: Array<String>) {
    val test = 달리기_경주()

    test.solution(arrayOf("mumu", "soe", "poe", "kai", "mine"), arrayOf("kai", "kai", "mine", "mine")).also {
        println("result ${it.contentDeepToString()}")
    }
}