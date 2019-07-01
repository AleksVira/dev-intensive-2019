package ru.virarnd.devintensive.extensions

import ru.virarnd.devintensive.models.User
import ru.virarnd.devintensive.models.UserView
import ru.virarnd.devintensive.utils.Utils

fun User.toUserView(): UserView{

    val nickName: String = Utils.transliteration("$firstName $lastName", " ")
    val initials =  Utils.toInitials(firstName, lastName)
    val status = if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit!!.humanizeDiff()}"
//    val status = if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar =  avatar,
        nickName = nickName ,
        initials = initials ,
        status = status)
}


