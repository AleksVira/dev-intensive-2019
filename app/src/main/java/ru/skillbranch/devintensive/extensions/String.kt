package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    val firstStep = this.replace("\\s+", " ").trim()
    val secondStep = firstStep.take(value).trim()
    println(secondStep)
    return if (secondStep.length < 2) {
        secondStep
    } else
        "$secondStep..."
}


fun String.stripHtml(): String =
    this
        .replace("""<.*?>""".toRegex(), "")
        .replace("""&(#\d+?|\w+?);""".toRegex(), "")
        .split(""" +""".toRegex()).joinToString(" ")
