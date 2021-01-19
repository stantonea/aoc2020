package eas.aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    val test_file = "src/test/resources/day12-test-data"
    val actual_file = "src/test/resources/day12data"

    @Test
    fun testDay12Pt1Test() {
        val file = test_file
        val coords = D12_readCoordinates(file)
        val answer = 25
        val dist = calcManhattanDistancePt1(coords)
        assertEquals(answer, dist)
    }

    @Test
    fun testDay12ActualPt1Test() {
        val file = actual_file
        val coords = D12_readCoordinates(file)
        val answer = 1956
        val dist = calcManhattanDistancePt1(coords)
        println("Day 12 Pt 1 Manhattan distance $dist")
        assertEquals(answer, dist)
    }

    @Test
    fun testDirections() {
        assertEquals(newNegInx(0, 0), 'N')
        assertEquals(newNegInx(0, 1), 'W')
        assertEquals(newNegInx(0, 2), 'S')
        assertEquals(newNegInx(0, 3), 'E')
        assertEquals(newNegInx(1, 0), 'E')
        assertEquals(newNegInx(1, 1), 'N')
        assertEquals(newNegInx(1, 2), 'W')
        assertEquals(newNegInx(1, 3), 'S')
        assertEquals(newNegInx(3, 0), 'W')
        assertEquals(newNegInx(3, 1), 'S')
        assertEquals(newNegInx(3, 2), 'E')
        assertEquals(newNegInx(3, 3), 'N')

        assertEquals(newPosIdx(0, 0), 'N')
        assertEquals(newPosIdx(0, 1), 'E')
        assertEquals(newPosIdx(0, 2), 'S')
        assertEquals(newPosIdx(0, 3), 'W')
        assertEquals(newPosIdx(1, 0), 'E')
        assertEquals(newPosIdx(1, 1), 'S')
        assertEquals(newPosIdx(1, 2), 'W')
        assertEquals(newPosIdx(1, 3), 'N')
        assertEquals(newPosIdx(3, 3), 'S')
        assertEquals(newPosIdx(3, 2), 'E')


    }


}

