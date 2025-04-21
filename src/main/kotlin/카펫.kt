// https://school.programmers.co.kr/learn/courses/30/lessons/42842

fun main(args: Array<String>) {
    solution(10, 2)
    solution(8, 1)
    solution(24, 24)
}

fun solution(brown: Int, yellow: Int): IntArray {

    var column = 1
    var row = 0

    while (true) {
        if (yellow % column == 0) {

            row = yellow / column

            if (brown == column * 2 + row * 2 + 4) {
                break
            }
        }
        column++
    }
    return intArrayOf(row + 2, column + 2)
}