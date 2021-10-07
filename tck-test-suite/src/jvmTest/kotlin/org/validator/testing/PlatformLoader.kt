package org.validator.testing

import org.validator.testing.JsonParsingHelper

actual fun loadResourceText(path: String): String {
    return JsonParsingHelper::class.java.getResource(path).readText()
}
