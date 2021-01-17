package eas.aoc


val tree = '#'

fun findValidTreeCount(inputFile: String, xIncrement:Int = 3, yIncrement:Int = 1): Int {
    val treeMap = importCharList(inputFile)

    var treeTotal = 0
    val ylimit = treeMap.size  // limit on vertical rows
    val xlimit = treeMap[0].size // limit on horizontal index
    var xidx = 0
    var yidx = 0

    done@while(yidx < ylimit) {
        for(i in 1 .. xIncrement) {
            xidx++
            if (xidx != 0 && xidx % xlimit == 0) {
                // reset back to the zeroth index
                xidx = 0
            }
        }

        for (y in 1 .. yIncrement) {
            yidx++
            if (yidx != 0 && yidx % ylimit == 0) {
                // we're done
                break@done
            }
        }
        if (treeMap[yidx][xidx] == tree) treeTotal++
    }

    return treeTotal
}
