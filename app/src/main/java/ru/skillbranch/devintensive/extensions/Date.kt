package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

//const val SECOND = 1000L
//const val MINUTE = 60 * SECOND
//const val HOUR = 60 * MINUTE
//const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy", localeString: String = "ru"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale(localeString))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    time += value * units.value
/*
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * TimeUnits.SECOND.value
        TimeUnits.MINUTE -> value * TimeUnits.MINUTE.value
        TimeUnits.HOUR -> value * TimeUnits.HOUR.value
        TimeUnits.DAY -> value * TimeUnits.DAY.value
    }
    this.time = time
*/
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val tmpDiffInSeconds: Long = (date.time - this.time) / 1000
    val diffInSeconds = tmpDiffInSeconds.absoluteValue
    val past = tmpDiffInSeconds >= 0
    return when (diffInSeconds) {
        in 0..1 -> "только что"
        in 2..45 -> if (past) "несколько секунд назад" else "через несколько секунд"
        in 45..75 -> if (past) "минуту назад" else "через минуту"
        in 75..45.minutes -> if (past) "${diffInSeconds.toMinutes} минут${minEnding(diffInSeconds.toMinutes)} назад" else "через ${diffInSeconds.toMinutes} минут${minEnding(diffInSeconds.toMinutes)}"
        in 45.minutes..75.minutes -> if (past) "час назад" else "через час"
        in 75.minutes..22.hours -> if (past) "${diffInSeconds.toHours} час${hourEnding(diffInSeconds.toHours)} назад" else "через ${diffInSeconds.toHours} час${hourEnding(diffInSeconds.toHours)}"
        in 22.hours..26.hours -> if (past) "день назад" else "через день"
        in 26.hours..360.days -> if (past) "${diffInSeconds.toDays} ${dayEnding(diffInSeconds.toDays)} назад" else "через ${diffInSeconds.toDays} ${dayEnding(diffInSeconds.toDays)}"
        else -> if (past) "более года назад" else "более чем через год"
    }

}

fun minEnding(toMinutes: Long): String {
    val minutes = toMinutes.toInt()
    val lastDigit = minutes % 10
    return when {
        minutes in 6..20 -> ""
        lastDigit == 1 -> "у"   // 1, 21, 31, 41, 51, 61, 71
        lastDigit in 2..4 -> "ы"
        else -> ""
    }
}

fun hourEnding(toHours: Long): String {
    val hours = toHours.toInt()
    val lastDigit = hours % 10
    return when {
        hours in 5..20 -> "ов"
        lastDigit == 1 -> ""   // 21, 31, 41, 51, 61, 71
        lastDigit in 2..4 -> "а"
        else -> "ов"
    }
}

fun dayEnding(toDays: Long): String {
    val days = toDays.toInt()
    val lastDigit = days % 10
    return when {
        days % 100 in 5..20 -> "дней"
        lastDigit == 1 -> "день"   // 1, 21, 31, 41, 51, 61, 71
        lastDigit in 2..4 -> "дня"
        else -> "дней"
    }
}


enum class TimeUnits(val value: Long) {
    SECOND(1000L),
    MINUTE(60 * SECOND.value),
    HOUR(60 * MINUTE.value),
    DAY(24 * HOUR.value)
}


val Int.minutes get() = this * TimeUnits.MINUTE.value / 1000
val Long.toMinutes get() = this * 1000 / TimeUnits.MINUTE.value
val Int.hours get() = this * TimeUnits.HOUR.value / 1000
val Long.toHours get() = this * 1000 / TimeUnits.HOUR.value
val Int.days get() = this * TimeUnits.DAY.value / 1000
val Long.toDays get() = this * 1000 / TimeUnits.DAY.value
