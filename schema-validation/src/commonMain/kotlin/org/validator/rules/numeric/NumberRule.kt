package org.validator.rules.numeric

import org.validator.*

interface NumberRule : SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val number = getNumber(element)
        return if (number != null) eval(path, number)
        else emptyList()
    }

    fun eval(path: String, number: Number): List<RuleError>

    fun getNumber(element: JsonElement): Double? {
        return element.double().fold({null}) { it }
    }
}

interface NumberRuleParser : RuleParser {

    val KEY: String

    override fun canParse(element: JsonObject) = element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return element.get(KEY).double().fold({ error -> Schema(base, finalPath, error)}) { x -> parse(x) }
    }

    fun parse(number: Number): Schema
}
