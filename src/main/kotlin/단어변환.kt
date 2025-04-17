import java.util.*

fun main(args: Array<String>) {

    val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")

    solution("hit", "cog", words)

}

fun solution(begin: String, target: String, words: Array<String>): Int {
    // target이 반드시 words에 포함되어 있어야만 유효한 변환이 가능함
    // dfs로 도달 가능성에 대해서 검사해야하나? 라고 생각했는데 그럴 필요가 없었음
    // 최단 거리를 찾아야하니 bfs로 접근하는것이 맞는 방법
    if (target !in words) return 0

    val visited = mutableSetOf<String>()
    // 2차원 배열의 경우 DP처럼 별도의 배열을 만들어 count 누적하면 되는데
    // 이번 같은 경우처럼 Pair을 통해 카운트 하는것도 좋은 방법으로 보임
    val queue: LinkedList<Pair<String, Int>> = LinkedList()
    queue.add(begin to 0)

    while (queue.isNotEmpty()) {
        val (current, count) = queue.poll()

        if (current == target) return count

        for (word in words) {
            if (word !in visited && isConvertible(current, word)) {
                visited.add(word)
                queue.add(word to count + 1)
            }
        }
    }

    return 0
}

fun isConvertible(a: String, b: String): Boolean {
    return a.zip(b).count { it.first != it.second } == 1
}