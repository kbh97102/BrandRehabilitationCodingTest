//https://school.programmers.co.kr/learn/courses/30/lessons/148653

class 마법의_엘리베이터 {

    fun solution(storey: Int): Int {
        var answer: Int = 0

        /*
        dfs, bfs, dp가 아닌 자리수 별로 0, 10 중 가까운 쪽으로 이동시키면 되는 문제
        너무 복잡하게 생각하지말자
         */

        var tempStorey = storey
        while (tempStorey > 0) {
            val current = tempStorey%10
            tempStorey /= 10
            answer += if (current == 5){
                if(tempStorey%10 >=5) {
                    tempStorey++
                    10-current
                }
                else {
                    current
                }
            } else if (current < 5) {
                current
            } else {
                tempStorey += 1
                10 - current
            }
        }

        return answer
    }



}

fun main(args: Array<String>) {
    마법의_엘리베이터().solution(45).also {
        println("answer $it")
    }
}