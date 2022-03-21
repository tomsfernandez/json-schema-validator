package org.validator.rules.any

import org.validator.*

object ConstRuleParser: RuleParser {

    private const val KEY = "const"

    override fun canParse(element: JsonObject): Boolean {
        return element.get(KEY) != null
    }

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val constElement = element.get(KEY) !!
        val rule = ConstRule(constElement)
        return Schema(base, objectKey(path, KEY), rule)
    }
}

data class ConstRule(val toCompare: JsonElement) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return if (!toCompare.deepEquals(element)) listOf(RuleError(path, "Schemas are not equal"))
        else emptyList()
    }
}
