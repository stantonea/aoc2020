package eas.aoc

fun findJoltageDiffsPt1(joltages: MutableList<Int>): Int {
    val joltageDiff = 3
    var ones = 0
    var threes = 0
    joltages.add(0, 0)
    joltages.sorted().zipWithNext().forEach { (a,b) ->
        val diff = b - a
        when (diff) {
            1 -> ones++
            3 -> threes++
            else -> {}
        }
    }

    return ones * (threes + 1)
}

/**
 * from tginsberg
 */
fun findJoltageCombinationsPt2_B(adapters: List<Int>): Long {
    val adapterMap: MutableMap<Int,Long> = mutableMapOf(0 to 1L)
    val adaptersSorted = adapters.sorted()
    adaptersSorted
        .forEach{ adapter ->
            adapterMap[adapter] = (1..3).map { lookback ->
                adapterMap.getOrDefault(adapter - lookback, 0)
            }.sum()
        }

    return adapterMap.getValue(adaptersSorted.last())
}