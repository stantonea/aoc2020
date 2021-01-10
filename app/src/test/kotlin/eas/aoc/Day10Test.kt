package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    val test_file = "src/test/resources/day10-test-data"
    val actual_file = "src/test/resources/day10data"

    @Test
    fun testDay10Pt1Test() {
        val file = test_file
        val values = importTextFileToInt(file).toMutableList()
        val answer = 220
        val ans = findJoltageDiffsPt1(values)
        assertEquals(answer, ans)
    }

    @Test
    fun testDay10Pt1Actual() {
        val file = actual_file
        val values = importTextFileToInt(file).toMutableList()
        val answer = 1836
        val ans = findJoltageDiffsPt1(values)
        assertEquals(answer, ans)
    }

    @Test
    fun testDay10Pt12Test() {
        val file = test_file
        val values = importTextFileToInt(file).toMutableList()
//        val values = mutableListOf<Int>(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
        val answer = 19208L
        val ans = findJoltageCombinationsPt2_B(values)
        assertEquals(answer, ans)
    }

    @Test
    fun testDay10Pt12Actual() {
        val file = actual_file
        val values = importTextFileToInt(file).toMutableList()
        val answer = 43406276662336L
        val ans = findJoltageCombinationsPt2_B(values)
        assertEquals(answer, ans)
    }

}

