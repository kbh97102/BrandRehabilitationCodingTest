fun main(args: Array<String>) {

}
// https://school.programmers.co.kr/learn/courses/19344/lessons/242258?language=java
class 붕대감기 {

    fun solution(bandage: IntArray, health: Int, attacks: Array<IntArray>): Int {

        val castingTime = bandage.first()
        val healPerSecond = bandage[1]
        val additionalHealingAmount = bandage[2]

        var timer = 0

        var castingTimer = 0

        var hp = health

        for (attack in attacks) {

            timer++

            val attackTime = attack.first()
            val attackDamage = attack[1]

            // 공격을 당함
            if (attackTime == timer) {
                // 공격을 당해 죽은 경우
                if (hp - attackDamage <= 0) {
                    return -1
                }
                hp -= attackDamage
                castingTimer = 0
            } else {
                if (castingTimer == castingTime) {
                    hp += additionalHealingAmount
                }
                hp += healPerSecond

                if (hp > health) {
                    hp = health
                }

                castingTimer++
            }

        }



        return hp
    }


}