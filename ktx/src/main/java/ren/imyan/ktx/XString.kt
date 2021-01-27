package ren.imyan.ktx

fun String.trimEmptyToNull(): String? = if (isBlank()) null else this