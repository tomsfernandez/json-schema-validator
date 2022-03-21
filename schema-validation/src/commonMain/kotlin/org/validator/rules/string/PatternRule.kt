package org.validator.rules.string

import org.validator.*

data class PatternRule(val pattern: Regex) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.string().map {
            val matches = pattern.containsMatchIn(it)
            if (!matches) listOf(RuleError(path, "Value doesn't match regex"))
            else emptyList()
        }.rightOrDefault(emptyList())
    }
}

object PatternRuleParser : RuleParser {

    private const val key = "pattern"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, key)
        return element.get(key).string().fold({ error -> Schema(base, finalPath, error)}) { Schema(base, finalPath, PatternRule(it.toRegex())) }
    }
}
