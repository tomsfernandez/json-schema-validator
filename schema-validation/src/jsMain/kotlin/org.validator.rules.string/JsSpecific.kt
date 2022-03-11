package org.validator.rules.string

@JsModule("utfstring")
@JsNonModule
external object UtfString {

    fun length(str: String): Int
}

actual fun characterCount(value: String): Int {
    return UtfString.length(value)
}
