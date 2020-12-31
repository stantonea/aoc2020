package eas.aoc

data class PWPolicy (
    val low: Int,
    val high: Int,
    val char: Char,
    val password: String
)

fun findValidPwCount(inputFile: String): Int {
    val policies = importPwData(inputFile)
    var total = 0
    policies.forEach { p ->
        val count = p.password.count { c -> c == p.char }
        if (count >= p.low && count <= p.high) total++
    }

    return total
}

fun findValidPwCountPosition(inputFile: String): Int {
    val policies = importPwData(inputFile)
    var total = 0
    policies.forEach { p ->
        val p1 = p.password[p.low - 1]
        val p2 = p.password[p.high - 1]
        if ((p1 == p.char || p2 == p.char) && (p1 != p2)) total++
    }

    return total
}