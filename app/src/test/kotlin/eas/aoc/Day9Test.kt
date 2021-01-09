package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    val test_file = "src/test/resources/day9-test-data"
    val actual_file = "src/test/resources/day9data"

    @Test
    fun testDay8Pt1Test() {
        val file = test_file
        val values = importTextFileToLong(file)
        val preambleSize = 5
        val answer = listOf(127L).sorted()
        val ans = findNonConformingPt1(values, preambleSize).sorted()
        assertEquals(answer, ans)
    }

    @Test
    fun testDay8Pt1Actual() {
        val file = actual_file
        val values = importTextFileToLong(file)
        val preambleSize = 25
        val answer = listOf(23278925L).sorted()
        val ans = findNonConformingPt1(values, preambleSize)
        println("Day 8, non-comforming numbers: $ans")
        assertEquals(answer, ans)
    }

    @Test
    fun testDay8Pt2Test() {
        val file = test_file
        val values = importTextFileToLong(file)
        val target = 127L
        val answer = 62L
        val ans = findNumSequencePt2(values, target)
        assertEquals(answer, ans)
    }

    @Test
    fun testDay8Pt2Actual() {
        val file = actual_file
        val values = importTextFileToLong(file)
        val target = 23278925L
        val answer = 4011064L
        val ans = findNumSequencePt2(values, target)
        assertEquals(answer, ans)
    }
}
