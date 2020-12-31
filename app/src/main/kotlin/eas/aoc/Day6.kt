package eas.aoc

data class GroupData (val group: List<Char>)

fun processCustomsList(inputFile: String): List<Int> {
    val seatList = D6_readCustomsData(inputFile)

    return seatList.map {
        it.distinct().count()
    }.toList()
}


