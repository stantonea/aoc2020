package eas.aoc

import java.text.NumberFormat

enum class PPFields(val required: Boolean) {
    byr(true),
    iyr(true),
    eyr(true),
    hgt(true),
    hcl(true),
    ecl(true),
    pid(true),
    cid(false)
}

fun findValidPassportCountPt1(inputFile: String): Int {
    val passports = readPassportData(inputFile)

    return passports.filter { validPassport(it) }.count()
}

fun findValidPassportCountPt2(inputFile: String): Int {
    val passports = readPassportData(inputFile)

    return passports.filter { validPassportPt2(it) }.count()
}

fun validPassport(map: Map<PPFields, String>):Boolean {
    var answer = true

    PPFields.values().forEach { f ->
        if (map.get(f) == null && f.required) {
            return false
        }
    }

    return answer
}

fun validPassportPt2(map: Map<PPFields, String>):Boolean {
    var answer = true
    val eyecolors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    PPFields.values().forEach { f ->
        val item = map.get(f)
        if (item == null && f.required) {
            return false
        }
        when(f) {
            PPFields.cid -> {
                true
            }
            PPFields.byr -> {
                if (! item!!.isInteger() || item.toInt() !in 1920..2002) return false
            }
            PPFields.iyr -> {
                if (! item!!.isInteger() || item.toInt() !in 2010..2020) return false
            }
            PPFields.eyr -> {
                if (! item!!.isInteger() || item.toInt() !in 2020..2030) return false
            }
            PPFields.hgt -> {
                val unit = item!!.substring(item.length-2..item.length-1)
                val value = item.substring(0..item.length-3)
                if (! value.isInteger()) return false
                when (unit) {
                    "cm" -> if (value.toInt() !in 150..193) return false
                    "in" -> if (value.toInt() !in 59..76) return false
                    else -> return false
                }
            }
            PPFields.hcl -> {
                val pfx = item!!.substring(0..0)
                val value = item.substring(1..item.length-1)
                if (pfx != "#") return false
                if (value.length != 6 ||
                    ! value.matches(Regex("[0-9a-f].*"))) return false
            }
            PPFields.ecl -> {
                if (item !in eyecolors) return false
            }
            PPFields.pid -> {
                if (! item!!.isInteger() || item.length != 9) return false
            }
        }
    }

    return answer
}
