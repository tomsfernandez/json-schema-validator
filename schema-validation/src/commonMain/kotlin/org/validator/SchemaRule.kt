package org.validator

interface SchemaRule {
    fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError>
    fun objectKey(path: String, key: String): String = "$path/$key"
    fun arrayIndex(path: String, index: Int): String = "$path/$index"
}

interface RuleParser {
    fun canParse(element: JsonObject): Boolean
    fun parse(base: String, path: String, element: JsonObject): Schema

    fun objectKey(path: String, key: String): String = "$path/$key"
    fun arrayIndex(path: String, index: Int): String = "$path/$index"
}

data class OrRule(val rules: List<SchemaRule>) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val errors = rules.map { x -> x.eval(path, element, schema) }
        val atLeastOneValid = errors.any { it.isEmpty() }
        return if (atLeastOneValid) emptyList() else errors.flatten()
    }
}


data class RuleError(val path: String, val reason: String, val related: List<RuleError> = emptyList())
data class Error(val reason: String)
