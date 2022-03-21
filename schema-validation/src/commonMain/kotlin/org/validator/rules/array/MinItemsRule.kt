package org.validator.rules.array

import org.validator.*

data class MinItemsRule(val minimum: Int) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array().map { assertSize(it) }.rightOrDefault(emptyList())
    }

    private fun assertSize(array: JsonArray): List<Error> {
        return if (array.elements().size < minimum) listOf(Error("Array must have more than $minimum elements"))
        else emptyList()
    }
}

object MinItemsRuleParser: RuleParser {

    private val KEY = "minItems"
    private val FORMAT_ERROR = Error("minItems must be an integer")

    override fun canParse(element: JsonObject): Boolean = element.get(KEY) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return element.get(KEY).integer().fold(Schema(base, finalPath, FORMAT_ERROR)) { int -> Schema(base, finalPath, MinItemsRule(int)) }
    }
}
