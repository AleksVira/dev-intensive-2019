package ru.virarnd.devintensive.models

import ru.virarnd.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String =
        "id:$id ${from?.firstName} ${if (isIncoming) "получил" else "отпправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}

