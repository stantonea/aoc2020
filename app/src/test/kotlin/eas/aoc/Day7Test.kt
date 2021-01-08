package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    val test_file = "src/test/resources/day7-test-data"
    val actual_file = "src/test/resources/day7data"

    val targetBag = "shinygold"

    @Test
    fun testDay7Pt1() {
        val file = test_file
        val answer = 4
        val ans = findNumberBagsReferencedPt1(file, targetBag)
        assertEquals(answer, ans)
    }

    @Test
    fun testDay7AcutualPt1() {
        val file = actual_file
        val actual = 257
        val ans = findNumberBagsReferencedPt1(file, targetBag)
        print("Day 7 Pt1 bags containing $targetBag: ${ans}\n")
        assertEquals(actual, ans)
    }

    @Test
    fun testDay7Pt2() {
        val file = test_file
        val actual = 32
        val ans = findNumberBagsContainedPt2(file, targetBag)
        assertEquals(actual, ans)
    }

    @Test
    fun testDay7ActualPt2() {
        val file = actual_file
        val actual = 1038
        val ans = findNumberBagsContainedPt2(file, targetBag)
        assertEquals(actual, ans)
    }
}
