package org.validator.json

import org.validator.JsonElement

object JsonParser {

    fun parse(json: String): JsonElement = adapt(json)
}
