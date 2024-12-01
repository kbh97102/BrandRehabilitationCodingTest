//https://school.programmers.co.kr/learn/courses/30/lessons/176963

class 추억_점수 {

    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        return name.withIndex().associate {
            it.value to yearning[it.index]
        }.let { map ->
            photo.map {
                it.sumOf {
                    map.getOrElse(key = it, defaultValue = { 0 })
                }
            }.toTypedArray().toIntArray()
        }
    }

}