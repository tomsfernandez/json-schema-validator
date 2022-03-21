package org.validator.rules.array

import org.validator.*

data class UniqueItemsRule(val shouldBeUnique: Boolean): SchemaRule {

    private fun ERROR(path: String) = RuleError(path, "Some elements in the array are not unique")

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        if (!shouldBeUnique) return emptyList()
        return element.array().map { values ->
            if (amountOfUniqueElementsIsTheSameAsArraySize(values)) emptyList()
            else listOf(ERROR(path))
        }.rightOrDefault(emptyList())
    }

    private fun amountOfUniqueElementsIsTheSameAsArraySize(values: JsonArray) =
        values.elements().distinct().size == values.elements().size
}

object UniqueItemsRuleParser: RuleParser {

    private val KEY = "uniqueItems"

    override fun canParse(element: JsonObject): Boolean =  element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return element.get(KEY).boolean().fold({ error -> Schema(base, finalPath, error)}) { value -> Schema(base, finalPath, UniqueItemsRule(value)) }
    }
}
