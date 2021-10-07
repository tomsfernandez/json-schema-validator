package org.validator.testing

import org.validator.*

interface JsonParsingHelper {

    fun parseJson(schemaFile: String): JsonObject {
        val schemaAsString = loadResourceText(schemaFile)
        return adapt(schemaAsString) as JsonObject
    }

    fun parseJsonScalar(schemaFile: String): JsonElement {
        val schemaAsString = loadResourceText(schemaFile)
        return DefaultJsonScalar(removeLeadingEscapedQuotes(schemaAsString.trim()))
    }

    private fun removeLeadingEscapedQuotes(value: String): String {
        return if (value.first() == '"' && value.last() == '"') value.substring(1, value.length-1)
        else if (value.first() == '\'' && value.last() == '\'') value.substring(1, value.length-1)
        else value
    }
}
