package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.split(" ")
        val strFirst = parts?.getOrNull(0)
        val strSecond = parts?.getOrNull(1)
        val firstName = if (strFirst.isNullOrEmpty()) null else strFirst
        val lastName = if (strSecond.isNullOrEmpty()) null else strSecond
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val words = payload.split(" ").map {
            it.replace(Regex("[$it]")) { result -> charToTranslit(result) }
        }
        return words.joinToString(divider)
    }

    private fun charToTranslit(result: MatchResult): String {
        var stringToConvert = result.value
        val isCap = stringToConvert[0].isUpperCase()
        if (isCap) {
            stringToConvert = stringToConvert.toLowerCase()
        }
        var converted = when (stringToConvert) {
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "e"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "i"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "t"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "h"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "sh'"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "yu"
            "я" -> "ya"
            else -> stringToConvert
        }
        if (isCap) {
            converted = converted.capitalize()
        }
        return converted
    }


    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstLetter: String = (firstName?.getOrNull(0)?.toUpperCase() ?: "").toString().trim()
        val secondLetter: String = (lastName?.getOrNull(0)?.toUpperCase() ?: "").toString().trim()
        val letterSum = "$firstLetter$secondLetter"
        return if (letterSum.isBlank()) {
            null
        } else {
            letterSum
        }
    }
}