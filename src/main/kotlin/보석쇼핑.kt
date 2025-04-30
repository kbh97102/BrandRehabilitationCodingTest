fun main(args: Array<String>) {
    val gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")
    val gems2 = arrayOf("AA", "AB", "AC", "AA", "AC")


    보석쇼핑().solution(
        gems2
    ).also {
        println(it.contentToString())
    }
}

class 보석쇼핑 {

    fun solution(gems: Array<String>): IntArray {
        val answer = intArrayOf(0, 1000001)

        val gemSet = gems.toSet()
        val gemMap = HashMap<String, Int>()

        var startIndex = 0
        var endIndex = 0

        while (startIndex <= endIndex && endIndex < gems.size) {

            val gem = gems[endIndex]

            gemMap[gem] = gemMap.getOrDefault(gem, 0) + 1
            while (gemMap.size == gemSet.size) {
                if (endIndex - startIndex < answer[1] - answer[0]) {
                    answer[0] = startIndex
                    answer[1] = endIndex
                }

                val startGem = gems[startIndex]
                gemMap[startGem] = gemMap.getOrDefault(startGem, 0) - 1
                if (gemMap[startGem] == 0) {
                    gemMap.remove(startGem)
                }
                startIndex++
            }
            endIndex++
        }

        answer[0]++
        answer[1]++

        return answer
    }

}