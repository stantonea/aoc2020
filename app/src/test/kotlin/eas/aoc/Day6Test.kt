package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    val test_file = "src/test/resources/day6-test-data"
    val actual_file = "src/test/resources/day6data"

    @Test
    fun testDay6Pt1() {
        val file = test_file
        val answer = 11
        val ans = processCustomsList_rev2(file)
        assertEquals(answer, ans)
    }

    @Test
    fun testDay6AcutualPt1() {
        val file = actual_file
        val actual = 6703
        val ans = processCustomsList_rev2(file)
        assertEquals(actual, ans)
        print("Day 6 Pt1 customs forms count ${ans}\n")
    }

    @Test
    fun testDay6ActualPt2() {
        val file = actual_file
        val ans = processCustomsListPt2(file)
        println("Day 6 Pt2 customs forms count $ans")
    }


}
