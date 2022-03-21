package org.validator.rules.any

import org.validator.*

object EnumRuleParser: RuleParser {

    private const val KEY = "enum"

    override fun canParse(element: JsonObject): Boolean {
        return element.get(KEY) != null
    }

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return element.get(KEY).array().fold({ error -> Schema(base, finalPath, error)}) { array ->
            val rule = EnumRule(array.elements())
            Schema(base, finalPath, rule)
        }
    }
}

data class EnumRule(val toCompare: List<JsonElement>) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val equalsAny = toCompare.any { x -> x.deepEquals(element) }
        return if (!equalsAny) listOf(RuleError(path, "Schema doesn't equal any value in enum"))
        else emptyList()
    }
}
