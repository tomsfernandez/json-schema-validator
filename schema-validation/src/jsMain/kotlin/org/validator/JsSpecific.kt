package org.validator

external fun require(name: String): dynamic
val url = require("url")

@JsModule("utfstring")
@JsNonModule
external object UtfString {

    fun length(str: String): Int
}

actual fun characterCount(value: String): Int {
    return UtfString.length(value)
}

actual fun decodeUri(value: String): String {
    return js("url.decodeUri(value)") as String
}

actual fun encodeUri(value: String): String {
    return js("url.encodeUri(value)") as String
}

actual fun resolveUri(base: String, value: String): String {
    return url.resolve(base, value) as String
}
