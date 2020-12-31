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
        val ans = processCustomsList(file)
        assertEquals(answer, ans.sum())
    }

    @Test
    fun testDay6AcutualPt1() {
        val file = actual_file
        val ans = processCustomsList(file)
        print("Day 6 Pt1 customs forms count ${ans.sum()}\n")
    }


}
