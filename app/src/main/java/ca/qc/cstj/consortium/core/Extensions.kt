package ca.qc.cstj.consortium.core

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime


val String.toColor
    get() = Color(android.graphics.Color.parseColor(this))

fun LocalDateTime.toDateFormat() : String {

    return "$date $hour:$minute"
}

