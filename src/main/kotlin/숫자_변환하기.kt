//https://school.programmers.co.kr/learn/courses/30/lessons/154538
class 숫자_변환하기 {

    fun solution(x: Int, y: Int, n: Int): Int {
        // dp 배열: 초기값은 큰 값으로 설정
        val dp = IntArray(y + 1) { Int.MAX_VALUE }
        dp[x] = 0 // 시작점은 연산 필요 없음

        // index에 데이터가 있다는건 이전 연산이 있었다는 뜻임 이걸 반복하면서 i를 쭉 증가시켜 나감
        // 이때 i에 +1을 하는건 해당 값에 도달한 루트의 수를 뜻한다
        // 이제 루프의 끝인 y 값까지 도달했을 때 dp 어레이의 y 인덱스에 있는 값은 어떤 루트로든 해당값을 만들어낸 카운트가 된다

        for (i in x..y) {
            if (dp[i] == Int.MAX_VALUE) continue // 도달 불가능한 상태는 건너뜀

            if (i + n <= y) {
                dp[i + n] = minOf(dp[i + n], dp[i] + 1)
            }
            if (i * 2 <= y) {
                dp[i * 2] = minOf(dp[i * 2], dp[i] + 1)
            }
            if (i * 3 <= y) {
                dp[i * 3] = minOf(dp[i * 3], dp[i] + 1)
            }
        }

        // 결과 반환
        return if (dp[y] == Int.MAX_VALUE) -1 else dp[y]
    }

}

fun main(args: Array<String>) {
    val solution = 숫자_변환하기()
    solution.solution(10, 40, 5)
}