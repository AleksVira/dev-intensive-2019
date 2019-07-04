package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy", localeString: String = "ru"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale(localeString))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    time += value * units.value
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
        in 75..45.minutes -> if (past) "${TimeUnits.MINUTE.plural(diffInSeconds.toMinutes)} назад" else "через ${TimeUnits.MINUTE.plural(diffInSeconds.toMinutes)}"
        in 45.minutes..75.minutes -> if (past) "час назад" else "через час"
        in 75.minutes..22.hours -> if (past) "${TimeUnits.HOUR.plural(diffInSeconds.toHours)} назад" else "через ${TimeUnits.HOUR.plural(diffInSeconds.toHours)}"
        in 22.hours..26.hours -> if (past) "день назад" else "через день"
        in 26.hours..360.days -> if (past) "${TimeUnits.DAY.plural(diffInSeconds.toDays)} назад" else "через ${TimeUnits.DAY.plural(diffInSeconds.toDays)}"
        else -> if (past) "более года назад" else "более чем через год"
    }

}

fun secEnding(seconds: Int): String {
    val lastDigit = seconds % 10
    return when {
        seconds in 6..20 -> ""
        lastDigit == 1 -> "у"   // 1, 21, 31, 41, 51, 61, 71
        lastDigit in 2..4 -> "ы"
        else -> ""
    }
}

fun minEnding(minutes: Int): String {
    val lastDigit = minutes % 10
    return when {
        minutes in 6..20 -> ""
        lastDigit == 1 -> "у"   // 1, 21, 31, 41, 51, 61, 71
        lastDigit in 2..4 -> "ы"
        else -> ""
    }
}

fun hourEnding(hours: Int): String {
    val lastDigit = hours % 10
    return when {
        hours in 5..20 -> "ов"
        lastDigit == 1 -> ""   // 21, 31, 41, 51, 61, 71
        lastDigit in 2..4 -> "а"
        else -> "ов"
    }
}

fun dayEnding(days: Int): String {
    val lastDigit = days % 10
    return when {
        days % 100 in 5..20 -> "дней"
        lastDigit == 1 -> "день"   // 1, 21, 31, 41, 51, 61, 71
        lastDigit in 2..4 -> "дня"
        else -> "дней"
    }
}


enum class TimeUnits(val value: Long) {
    SECOND(1000L) {
        override fun plural(value: Int): String = "$value секунд${secEnding(value)}"
    },
    MINUTE(60 * SECOND.value) {
        override fun plural(value: Int): String = "$value минут${minEnding(value)}"
    },
    HOUR(60 * MINUTE.value) {
        override fun plural(value: Int): String = "$value час${hourEnding(value)}"
    },
    DAY(24 * HOUR.value) {
        override fun plural(value: Int): String = "$value ${dayEnding(value)}"
    };

    abstract fun plural(value: Int): String
}


val Int.minutes get() = this * TimeUnits.MINUTE.value / 1000
val Long.toMinutes: Int get() = (this * 1000 / TimeUnits.MINUTE.value).toInt()
val Int.hours get() = this * TimeUnits.HOUR.value / 1000
val Long.toHours: Int get() = (this * 1000 / TimeUnits.HOUR.value).toInt()
val Int.days get() = this * TimeUnits.DAY.value / 1000
val Long.toDays:Int get() = (this * 1000 / TimeUnits.DAY.value).toInt()
