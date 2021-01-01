package eas.aoc

data class GroupData (val group: List<Char>)

fun processCustomsList(inputFile: String): List<Int> {
    val seatList = D6_readCustomsData(inputFile)

    return seatList.map {
        it.distinct().count()
    }.toList()
}

fun processCustomsList_rev2(inputFile: String):Int {
    val seatList = D6_readCustomsData2(inputFile)
    return seatList.sumBy {
        it.joinToString("").toSet().size }
}

fun processCustomsListPt2(inputFile: String): Int {
    val seatList = D6_readCustomsData2(inputFile)

    return seatList.sumBy { group ->
        group.joinToString("")
            .groupingBy {it}
            .eachCount()
            .count{ it.value == group.size }
    }
}