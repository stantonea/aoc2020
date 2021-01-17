package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    val test_file = "src/test/resources/day11-test-data"
    val actual_file = "src/test/resources/day11data"

    @Test
    fun testDay11Pt1Test() {
        val file = test_file
        val seatMap = importCharList(file)
        val answer = 37
        val seats = transformSeatMap(seatMap)
        assertEquals(answer, seats)
    }

    @Test
    fun testDay11ActualPt1Test() {
        val file = actual_file
        val seatMap = importCharList(file)
        val answer = 2164
        val seats = transformSeatMap(seatMap)
        assertEquals(answer, seats)
    }

    @Test
    fun testDay11Pt2Test() {
        val file = test_file
        val seatMap = importCharList(file)
        val answer = 26
        val seats = transformSeatMap(seatMap, true)
        assertEquals(answer, seats)
    }

    @Test
    fun testDay11ActualPt2Test() {
        val file = actual_file
        val seatMap = importCharList(file)
        val answer = 1974
        val seats = transformSeatMap(seatMap, true)
        println("Day11 pt2 occupied seats $seats")
        assertEquals(answer, seats)
    }


}

