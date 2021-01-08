package eas.aoc

import kotlin.streams.toList

data class BagData (val number:Int, val bagName: String)

var bagMap = mapOf<String, List<BagData>>()
var bagFound = false
fun findNumberBagsReferencedPt1(inputFile: String, bagName: String): Int {
    bagMap = D7_readBagDataPt1(inputFile)
    val numEntries = bagMap.entries.map {
        bagFound = false
        val bags = mutableListOf<String>()
        findBag2(it.key, bagName, bags)
        bags
    }
        .filter {it.isNotEmpty()}
        .flatMap{ it.stream().toList() }
        .toSet()

    return numEntries.size
}

fun findNumberBagsContainedPt2(inputFile: String, bagName: String): Int {
    bagMap = D7_readBagDataPt1(inputFile)
    val number = sumBags(bagName)

    return number
}

var count = 0
fun sumBags(target: String): Int {
    val bags = bagMap[target]
    if (bags!!.isEmpty())
        return 0
    else
        return bags.map {
            it.number * (1 + sumBags(it.bagName))
        }.sum()
}

fun findBag2(parent: String, target: String, bags: MutableList<String>) {
    if (parent == target || bagFound) {
        bagFound = true
        return
    }

    bagMap[parent]!!.forEach {
        findBag2(it.bagName, target, bags)
        if (bagFound) bags.add(parent)
    }

}

//fun findBag(parent: String, target: String, bags: MutableList<String>): Boolean  {
//    bags.add(parent)
//    val children = bagMap[parent]!!
//    for (c in children) {
////        println("Testing parent: $parent, child ${c.bagName}")
//        if (c.bagName == target)
//            true
//    }
//    for (c in children) {
//        findBag(c.bagName, target, bags)
//    }
//    return false
//}
