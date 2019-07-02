package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val strFirst = parts?.getOrNull(0)
        val strSecond = parts?.getOrNull(0)
        val firstName = if (strFirst.isNullOrEmpty()) "null" else strFirst
        val lastName = if (strSecond.isNullOrEmpty()) "null" else strSecond
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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