package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {
    val test_file = "src/test/resources/day8-test-data"
    val actual_file = "src/test/resources/day8data"

    @Test
    fun testDay8Pt1() {
        val file = actual_file
        val opCodes = D8_readInstructions(file)
        val answer = 1600
        val (ans, cycle) = accumulatePt1(opCodes)
        assertEquals(answer, ans)
    }

    @Test
    fun testDay8AcutualPt1() {
//        val file = test_file
//        val actual = 8
        val file = actual_file
        val actual = 1543
        val ans = accumulatePt2(file)
        print("Day 8 Pt2, accumulator value: ${ans}\n")
        assertEquals(actual, ans)
    }

}
