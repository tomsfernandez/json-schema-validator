package org.validator

expect fun characterCount(value: String): Int
expect fun decodeUri(value: String): String
expect fun encodeUri(value: String): String
expect fun resolveUri(base: String, value: String): String
