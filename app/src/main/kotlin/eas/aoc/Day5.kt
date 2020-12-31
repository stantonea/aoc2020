package eas.aoc

import java.lang.IllegalStateException

data class RowInfo (
    var row: Int,
    var col: Int,
    var seatId: Int)

data class Tuple3 (
    var low: Int,
    var high: Int,
    var last: Char)

fun processSeatList(inputFile: String): List<RowInfo> {
    val seatList = D5_readSeatingData(inputFile)
    var rowData = mutableListOf<RowInfo>()
    return seatList.map {

        var rowTup = Tuple3(0, 127, 'x')
        var colTup = Tuple3(0, 7, 'x')
        it.forEach { v ->
            when(v) {
                'F' -> {
                    // row: take the lower half 0..63
                    val diff = rowTup.high - rowTup.low
                    val half = diff / 2
                    rowTup = Tuple3(rowTup.low, half+rowTup.low, 'F')
                }
                'B' -> {
                    // row: take the upper half 64..127
                    val diff = rowTup.high - rowTup.low
                    val half = diff / 2
                    rowTup = Tuple3(rowTup.low+half+1, rowTup.high, 'B')
                }
                'L' -> {
                    // col: take the lower half 4..5
                    val diff = colTup.high - colTup.low
                    val half = diff / 2
                    colTup = Tuple3(colTup.low, half+colTup.low, 'L')
                }
                'R' -> {
                    // col: take the upper half 4..7
                    val diff = colTup.high - colTup.low
                    val half = diff / 2
                    colTup = Tuple3(colTup.low+half + 1, colTup.high, 'R')
                }
                else -> {throw IllegalStateException("illegal value $v")}

            }
        }
        val row = when(rowTup.last) {
            'F' -> rowTup.low
            'B' -> {rowTup.high}
            else -> -1
        }
        val col = when(colTup.last) {
            'L' -> colTup.low
            'R' -> colTup.high
            else -> -1
        }
        RowInfo(row, col, (row * 8) + col)
    }
}

fun findHighestSeatPt2(inputFile: String): Int {
    val passports = readPassportData(inputFile)

    return passports.filter { validPassportPt2(it) }.count()
}

