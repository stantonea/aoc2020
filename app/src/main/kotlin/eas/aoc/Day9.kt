package eas.aoc

fun findNonConformingPt1(numbers: List<Long>, sampleSize: Int): List<Long> {
    val ranges = mutableListOf<List<Long>>()
    ranges.add(numbers.subList(0, sampleSize))
    for(i in 1 until numbers.size-sampleSize+1) {
        ranges.add(numbers.subList(i, i + sampleSize))
    }

    val badList = numbers.subList(sampleSize, numbers.size)
        .mapIndexed { index, i -> Pair(i, ranges[index]) }
        .filter {
            ! numberFound(it.first, it.second)
        }
        .map { it.first }

    return badList
}

fun findNumSequencePt2(numbers: List<Long>, target: Long): Long {
    var start = 0L
    var end = 0L

    run done@{
        numbers
            .mapIndexed{ index, l ->
                numbers.subList(index, numbers.size)
            }
            .forEach { n ->
                var counter = 0L
                var idx = 0
                n.forEach {
                    idx++
                    if (counter > target) {
                        /* no-op */
                    } else {
                        counter += it
                        if (counter == target) {
                            val range = n.subList(0, idx)
                            start = range.min()!!
                            end = range.max()!!
                            return@done
                        }
                    }
                }

            }
    }
    return start + end
}

fun numberFound(num: Long, list: List<Long>): Boolean {
    for(i in list) {
        for (j in list) {
            if (i != j && i + j == num ) return true
        }
    }

    println("number failed $num, list: $list")
    return false
}

