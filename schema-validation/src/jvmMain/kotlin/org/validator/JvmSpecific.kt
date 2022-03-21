package org.validator

import java.net.URI
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.streams.toList

actual fun characterCount(value: String): Int {
    return value.codePoints().toList().size
}

actual fun decodeUri(value: String): String {
    return URLDecoder.decode(value, "utf-8")
}

actual fun encodeUri(value: String): String {
    return URLEncoder.encode(value, "utf-8")
}

actual fun resolveUri(base: String, value: String): String {
    return URI(base).resolve(value).toString()
}
