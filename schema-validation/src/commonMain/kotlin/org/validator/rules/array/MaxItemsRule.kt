package org.validator.rules.array

import org.validator.*

data class MaxItemsRule(val maximum: Int) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array().map { assertSize(it) }.rightOrDefault(emptyList())
    }

    private fun assertSize(array: JsonArray): List<Error> {
        return if (array.elements().size > maximum) listOf(Error("Array must have less than $maximum elements"))
        else emptyList()
    }
}

object MaxItemsRuleParser: RuleParser {

    private val KEY = "maxItems"
    private val FORMAT_ERROR = Error("maxItems must be an integer")

    override fun canParse(element: JsonObject): Boolean = element.get(KEY) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return element.get(KEY).integer().fold(Schema(base, finalPath, FORMAT_ERROR)) { int -> Schema(base, finalPath, MaxItemsRule(int)) }
    }
}
