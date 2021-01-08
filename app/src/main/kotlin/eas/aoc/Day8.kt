package eas.aoc

import java.lang.IllegalStateException

data class OpCode (var inst: String, var arg: Int, var visited: Int = 0)


fun accumulatePt1(opCodes: List<OpCode>): Pair<Int,Boolean> {

    var ip = 0
    var acc = 0
    while (ip < opCodes.size && opCodes[ip].visited == 0) {
        val op = opCodes[ip]
        acc += when(op.inst) {
            "nop" -> {ip++; 0}
            "jmp" -> {ip+=op.arg; 0}
            "acc" -> {ip++; op.arg}
            else -> throw IllegalStateException("bad opcode ${op.inst}")
        }
        op.visited+=1
    }
    val cycle = if (ip >= opCodes.size) false else true
    return Pair(acc, cycle)
}

fun accumulatePt2(inputFile: String): Int {
    val opCodes = mutableListOf<OpCode>()
    opCodes.addAll(D8_readInstructions(inputFile))

    opCodes
        .mapIndexed { index, opCode ->  Pair(index,opCode)}
        .filter {
            it.second.inst in listOf("jmp","nop")
        }
        .forEach {
            val copyOfOps = opCodes.map{it.copy()}.toMutableList()
            copyOfOps[it.first].inst = if (it.second.inst == "jmp") "nop" else "jmp"
            val (acc, cycle) = accumulatePt1(copyOfOps)
            if (! cycle) return acc
        }
    return -1
}
