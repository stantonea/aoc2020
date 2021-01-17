package eas.aoc

/**
 * Seat change rules:
 * Empty seat(L): Changes to occupied (#) if there are no occupied seats adjacent
 * Occupied set(#): Changes to empty (L) if four or more seats adjacent to it are occupied
 * Otherwise no change
 */


typealias FindAdjFunc = (row: Int, col: Int, seatMap: SeatMapType) ->
    Map<Char?, List<Char?>>
typealias SeatMapType = List<MutableList<Char>>
typealias IntMod = (Int) ->  Int
val decrement: IntMod = {it - 1}
val increment: IntMod = {it + 1}
val neutral: IntMod = {it}

fun transformSeatMap(seatMap: SeatMapType, isPart2: Boolean = false): Int {
    var count = 0
    var newSeatMap = seatMap
    var iterationCount=0
    var occCountThreshold = 4
    var adjFunction = findAdjacentSeats
    if (isPart2) {
        occCountThreshold = 5
        adjFunction = findAdjacentSeatsExtended
    }

    while(true) {
        iterationCount++
        val transform = findSeatsPt1(newSeatMap, adjFunction, occCountThreshold)
        count = transform.first
        newSeatMap = transform.second
        if (count == 0) break
    }
    println("Iteration count $iterationCount")
    return newSeatMap.sumBy {
        it.count { it == '#'}
    }

}


// Take in a seat map, copy it, perform a transform, return a new map
fun findSeatsPt1(oldMap: SeatMapType, adjFunc: FindAdjFunc, occCountThreshold: Int): Pair<Int, SeatMapType>  {
    val seatMap = oldMap.map{ it.toMutableList() }

    val totChanged = seatMap.withIndex().sumBy{ row ->
        row.value.mapIndexed { idx, seat ->
            // find adjacent seats to this seat
            val adj = adjFunc(row.index, idx, oldMap)
            val occCount = adj.getOrDefault('#', emptyList()).size
            val chg = when(seat) {
                'L' -> {
                    if (occCount == 0) { row.value[idx] = '#'; 1 } else 0
                }
                '#' -> {
                    if (occCount >= occCountThreshold) { row.value[idx] = 'L'; 1 } else 0
                }
                else -> 0
            }
            chg
        }.sum()

    }

    return Pair(totChanged, seatMap)
}

/**
 * Find
 *        LUD U   RUD
 *         L  S   R
 *        LDD D   RDD
 */
val findAdjacentSeats: FindAdjFunc =  { row: Int, col: Int, seatMap: SeatMapType ->
    val lud = seatMap.getOrNull(row -1)?.getOrNull(col - 1)
    val u = seatMap.getOrNull(row -1)?.getOrNull(col)
    val rud = seatMap.getOrNull(row -1)?.getOrNull(col + 1)
    val l = seatMap.getOrNull(row)?.getOrNull(col - 1)
    val r = seatMap.getOrNull(row)?.getOrNull(col + 1)
    val ldd = seatMap.getOrNull(row + 1)?.getOrNull(col - 1)
    val d = seatMap.getOrNull(row + 1)?.getOrNull(col)
    val rdd = seatMap.getOrNull(row + 1)?.getOrNull(col + 1)
    listOf(lud, u, rud, l, r, ldd, d, rdd).groupBy { it }
}

val findAdjacentSeatsExtended: FindAdjFunc =  { row: Int, col: Int, seatMap: SeatMapType ->
    val lud = adjGenericFinder(row, col, decrement, decrement, seatMap)
    val u = adjGenericFinder(row, col, decrement, neutral, seatMap) //seatMap.getOrNull(row -1)?.getOrNull(col)
    val rud = adjGenericFinder(row, col, decrement, increment, seatMap) //seatMap.getOrNull(row -1)?.getOrNull(col + 1)
    val l = adjGenericFinder(row, col, neutral, decrement, seatMap) //seatMap.getOrNull(row)?.getOrNull(col - 1)
    val r = adjGenericFinder(row, col, neutral, increment, seatMap) //seatMap.getOrNull(row)?.getOrNull(col + 1)
    val ldd = adjGenericFinder(row, col, increment, decrement, seatMap) //seatMap.getOrNull(row + 1)?.getOrNull(col - 1)
    val d = adjGenericFinder(row, col, increment, neutral, seatMap) //seatMap.getOrNull(row + 1)?.getOrNull(col)
    val rdd = adjGenericFinder(row, col, increment, increment, seatMap) //seatMap.getOrNull(row + 1)?.getOrNull(col + 1)
    listOf(lud, u, rud, l, r, ldd, d, rdd).groupBy { it }
}

fun adjGenericFinder(row: Int, col: Int, rowTrans: IntMod, colTrans: IntMod, seatMap: SeatMapType): Char? {
    var r = row
    var c = col
    while(true) {
        r = rowTrans(r)
        c = colTrans(c)
        val s = seatMap.getOrNull(r)?.getOrNull(c) ?: return null
        if (s != '.') return s
    }
}

