package com.miguel.core.extension

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.converterParaStringData(padrao: String = "dd/MM/yyyy HH:mm"): String =
    SimpleDateFormat(padrao, Locale("pt", "BR")).format(this)

fun Double.converterParaString(): String =
    NumberFormat.getInstance().let {
        it.maximumFractionDigits = 2
        it.format(this)
    }