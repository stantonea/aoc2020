package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {
    val test_file = "src/test/resources/day5-test-data"
    val actual_file = "src/test/resources/day5data"

    @Test
    fun testDay5Pt1() {
        val file = test_file // reads 3 rows
        val seatAnswers = listOf(357, 567, 119, 820)
        val ans = processSeatList(file)
        for(i in 0..2) {
            assertEquals(seatAnswers[i], ans[i].seatId)
        }

    }

    @Test
    fun testDay5ActualPt1() {
        val file = actual_file
        val test_answer = 2
        val ans = processSeatList(file)
        // find the hightest seat ID from the returned list
        val maxID = ans.maxBy { it.seatId }!!.seatId
        print("Day 5 Pt 1 max seat ID: $maxID \n")
        val sorted = ans.sortedBy { it.seatId }
        var id = sorted[0].seatId
        var missingSeat = -1
        sorted.reduce { _, rowInfo ->
            if (id + 1 == rowInfo.seatId) {
                id++
            }
            else missingSeat = id + 1
            rowInfo
        }
        print("Missing seat ID: $missingSeat\n")
    }

}
