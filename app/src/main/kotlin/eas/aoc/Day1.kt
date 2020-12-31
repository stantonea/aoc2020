package eas.aoc

fun findMatchingSumTwo(fileName:String): Int {
    val input = importTextFileToInt(fileName)
    var answer = 0
    var index = 0

    for (i in input) {
        for (k in input.subList(index, input.size)){
            if (i + k == 2020) {
                answer = i * k
                return answer
            }
        }
    }
    return -1

}
fun findMatchingSumThree(fileName:String): Int {
    val input = importTextFileToInt(fileName)
    var answer = 0
    var index1 = 0
    var index2 = 0

    for (i in input) {
        for (k in input.subList(index1, input.size)) {
            for (j in input.subList(index2, input.size)) {
                if (i + k + j == 2020) {
                    answer = i * k * j
                    return answer
                }
            }
        }
    }
    return -1
}