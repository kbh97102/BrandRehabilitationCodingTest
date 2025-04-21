// https://school.programmers.co.kr/learn/courses/30/lessons/86491

fun main(args: Array<String>) {
    val sizes: Array<IntArray> = arrayOf(
        intArrayOf(60, 50),
        intArrayOf(30, 70),
        intArrayOf(60, 30),
        intArrayOf(80, 40)
    )

    solution(sizes).let {
        print("result $it")
    }
}

fun solution(sizes: Array<IntArray>): Int {
    var maxWidth = 0
    var maxHeight = 0

    for ((w, h) in sizes) {
        val width = maxOf(w, h)
        val height = minOf(w, h)

        maxWidth = maxOf(maxWidth, width)
        maxHeight = maxOf(maxHeight, height)
    }

    return maxWidth * maxHeight
}