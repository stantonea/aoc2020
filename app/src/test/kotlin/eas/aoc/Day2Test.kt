/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
    val test_file = "src/test/resources/day2-test-data"
    val actual_file = "src/test/resources/day2data"

    @Test
    fun testDay2() {
        val test_answer = 2
        val ans = findValidPwCount(test_file)
        assertEquals(ans, test_answer)

    }

    @Test
    fun testDay2Position() {
        val test_answer = 1
        val ans = findValidPwCountPosition(test_file)
        assertEquals(ans, test_answer)

    }

    @Test
    fun testRunDay2() {
        val test_answer = 2
        val ans = findValidPwCount(actual_file)
        print("Day 2 pt1 answer: $ans\n")

    }

    @Test
    fun testRunDay2Position() {
        val test_answer = 2
        val ans = findValidPwCountPosition(actual_file)
        print("Day 2 pt2 answer: $ans\n")

    }

}
