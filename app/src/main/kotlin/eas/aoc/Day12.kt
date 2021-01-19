package eas.aoc

import java.lang.IllegalStateException
import java.lang.Math.abs
import kotlin.math.absoluteValue

val directions = listOf('N','E','S','W')

data class Coord(val direction: Char, val x: Int, val y: Int)
data class ShipAndWP(val ship: Coord, val wp: Coord)

typealias Mover = (Coord, Int ) -> Coord
val eastMover: Mover = { coord, value ->
    Coord(coord.direction, coord.x + value, coord.y)
}
val westMover: Mover = { coord, value ->
    Coord(coord.direction, coord.x - value, coord.y)
}
val southMover: Mover = { coord, value ->
    Coord(coord.direction, coord.x, coord.y - value)
}
val northMover: Mover = { coord, value ->
    Coord(coord.direction, coord.x, coord.y + value)
}
val moveMap = mapOf(
    'N' to northMover,
    'W' to westMover,
    'E' to eastMover,
    'S' to southMover
)

/**
 * FNN - forward N values (shift position in x or y direction)
 * NNN - North N values (y + n)
 * SNN - South N values (y - n)
 * ENN - East N values  (x + n)
 * WNN - West N values  (x - n)
 * RNN - Turn right N degrees (shift direction, not position)
 * LNN - Turn left N degrees (shift direction, not position)
 *
 */
fun calcManhattanDistancePt1(inputs: List<Pair<Char, Int>>): Int {
    val lastCoord = inputs.fold(Coord('E', 0, 0)) { accum, ele ->
        sequenceOf(ele)
            .map{ nextDirection(accum, ele) }
            .map { moveNext(it, ele) }
            .last()
    }

    return lastCoord.x.absoluteValue + lastCoord.y.absoluteValue
}

fun calcManhattanDistancePt2(inputs: List<Pair<Char,Int>>): Int {
    val start = ShipAndWP(Coord('E', 0, 0), Coord('E', -1, 1))
    val lastCoord = inputs.fold(start) { accum, ele ->
        sequenceOf(ele)
            .map { moveWayPoint(accum, ele) }
            .map { rotateWayPoint(it, ele) }
            .map { moveShip(it, ele) }
            .last()
    }


    return 0

}

fun moveShip(from: ShipAndWP, to: Pair<Char,Int>): ShipAndWP {
    if (to.first != 'F') return from

    val increment = to.second
    val xMove = from.wp.x * increment
    val yMove = from.wp.y * increment
    val newShip = Coord('E', from.ship.x + xMove, from.ship.y + yMove)
    return ShipAndWP(newShip, from.wp)
}

fun moveWayPoint(from: ShipAndWP, to: Pair<Char, Int>): ShipAndWP {
    if (to.first !in directions) return from

    val newWp = moveNext(from.wp, to)
    return ShipAndWP(from.ship, newWp)
}

fun nextDirection(from: Coord, to: Pair<Char, Int>): Coord {
    return if (to.first in listOf('L', 'R')) {
        val moveTo = to.second / 90
        val start = directions.indexOf(from.direction)
        val newDir = when (to.first) {
            'R' -> newPosIdx(start, moveTo)
            'L' -> newNegInx(start, moveTo)
            else -> throw IllegalStateException("Unknown direction ${to.first}")
        }
        // new coordinate facing a different direction, but unmoved
        println("Chg direction ${to.first}${to.second}, from: ${from.direction} to: $newDir")
        Coord(newDir, from.x, from.y)
    }
    else {
        from
    }
}

fun moveNext(from: Coord, to: Pair<Char, Int>): Coord {
    if (to.first !in directions + 'F') return from

    val moveDirection = when(to.first) {
        'F' -> from.direction
        else -> to.first
    }

    val next = moveMap.getValue(moveDirection).invoke(from, to.second)
    println(
        "moveNext from: ${from.direction}[${from.x}:${from.y}] to ${next.direction}[${next.x}:${next.y}]")
    return next
}

fun newPosIdx(from: Int, to: Int): Char {
    return directions[(from + to) % directions.size]
}

fun newNegInx(from: Int, to: Int): Char {

    val calced = from - to
    if (calced < 0)
        return directions[calced + 4]
    else
        return directions[calced]
}
