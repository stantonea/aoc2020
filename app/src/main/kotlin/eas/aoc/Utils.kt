package eas.aoc

import java.io.File
import java.nio.file.Files
import kotlin.streams.toList

fun String.isInteger():Boolean {
    return toIntOrNull() != null
}

fun importTextFileToInt (fileName: String): List<Int> {
    return Files.lines(File(fileName).toPath())
        .map { x -> x.trim().toInt() }
        .toList()
}

fun importPwData(fileName: String): List<PWPolicy> {
    return Files.lines(File(fileName).toPath())
        .map { x ->
            val values = x.split("-", " ", ":")
            PWPolicy(values[0].toInt(), values[1].toInt(), values[2][0], values[4])
        }
        .toList()
}

fun importTobogganData(fileName:String): List<List<Char>> {
    var dat = mutableListOf<MutableList<Char>>()
    var idx = 0

    Files.lines(File(fileName).toPath())
        .forEach { line ->
            dat.add(mutableListOf())
            line.chars().forEach { dat[idx].add(it.toChar()) }
            idx++
        }

    return dat
}

fun readPassportData(fileName: String): List<Map<PPFields, String>> {
    var ppList = mutableListOf<Map<eas.aoc.PPFields, String>>()
    var curMap = mutableMapOf<PPFields, String>()
    Files.lines(File(fileName).toPath())
        .filter {
            if (it.trim().isEmpty()) {
                // blank line, save current map and build a new one
                ppList.add(curMap)
                curMap = mutableMapOf()
                false
            }
            else true
        }
        .flatMap { line ->
            val items = line.split(" ")
            items.stream()
        }
        .forEach {
            // convert items and stash in the map
            val pair = it.split(":")
            val ppField = PPFields.valueOf(pair[0])
            val ppValue = pair[1]
            curMap.put(ppField, ppValue)
        }

    // stash the last passport map
    if (! curMap.isEmpty()) ppList.add(curMap)
    return ppList
}

fun D5_readSeatingData(fileName:String): List<CharArray> {
    return Files.lines(File(fileName).toPath()).map { it.toCharArray() }.toList()
}

fun D6_readCustomsData(fileName: String): List<List<GroupData>> {
    var groupList = mutableListOf<List<GroupData>>()
    var curGroupList = mutableListOf<GroupData>()
    Files.lines(File(fileName).toPath())
        .filter {
            if (it.trim().isEmpty()) {
                // blank line, save current map and build a new one
                groupList.add(curGroupList)
                curGroupList = mutableListOf()
                false
            }
            else true
        }
        .forEach {
            // convert items and stash in the list
            val items = GroupData(it.toList())
            curGroupList.add(items)
        }

    if (! curGroupList.isEmpty()) groupList.add(curGroupList)
    return groupList
}

fun D6_readCustomsData2(fileName: String): List<List<String>> {
    return Files.readString(File(fileName).toPath())
        .split("\n\n")
        .map { it.lines().filter { it.isNotBlank() }}
}

/**
 * Sample input
    light red bags contain 1 bright white bag, 2 muted yellow bags.
    dark orange bags contain 3 bright white bags, 4 muted yellow bags.
    bright white bags contain 1 shiny gold bag.
    muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
    shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
    dark olive bags contain 3 faded blue bags, 4 dotted black bags.
    vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
    faded blue bags contain no other bags.
    dotted black bags contain no other bags.
 */
fun D7_readBagDataPt1(fileName: String): Map<String, List<BagData>> {
    val map = mutableMapOf<String, List<BagData>>()
    Files.lines(File(fileName).toPath())
        .forEach {l ->
            val items = l.split("contain")
            val bagName = items[0].split(" ").take(2).zipWithNext { a, b -> a+b }.first()
            val internalBags = items[1].split(",")
                .filter {it != " no other bags."}
                .map {bag ->
                    val baginfo = bag.trim().split(" ")
                    BagData(baginfo[0].toInt(), baginfo[1] + baginfo[2])
                }
            map.put(bagName, internalBags)
        }

    return map
}

fun D8_readInstructions(fileName: String): List<OpCode> {
    return Files.lines(File(fileName).toPath())
        .filter {it.isNotEmpty()}
        .map {
            val items = it.split(" ")
            OpCode(items[0], items[1].toInt())
        }.toList()
}
