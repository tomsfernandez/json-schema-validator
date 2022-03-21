package org.validator.rules.string

import org.validator.*

interface StringLengthRule : SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val string = getString(element)
        return if (string != null) { eval(path, string)
        } else emptyList()
    }

    fun eval(path: String, string: String): List<RuleError>

    fun getString(element: JsonElement): String? {
        return element.asScalar()
            .mapEither({null}) { scalar -> scalar.string() }
            .fold({null}) { x -> x }
    }
}

interface StringLengthRuleParser : RuleParser {

    val key: String

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val constElement = element.get(key) !!
        val finalPath = objectKey(path, key)
        return constElement.asScalar().fold({ error -> Schema(base, finalPath, error)}) { scalar ->
            scalar.double().map { it.toInt() }.fold({ error -> Schema(base, finalPath, error)}) { int -> parse(base, finalPath, int)}
        }
    }

    fun parse(base: String, finalPath: String, amount: Int): Schema
}
