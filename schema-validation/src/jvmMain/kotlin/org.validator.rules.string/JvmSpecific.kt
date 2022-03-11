package org.validator.rules.string

import kotlin.streams.toList

actual fun characterCount(value: String): Int {
    return value.codePoints().toList().size
}
