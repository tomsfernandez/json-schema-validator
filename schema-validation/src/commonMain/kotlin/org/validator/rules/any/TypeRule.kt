package org.validator.rules.any

import org.validator.*

object TypeRuleParser: RuleParser {

    private const val ARRAY_TYPE = "array"
    private const val OBJECT_TYPE = "object"
    private const val NULL_TYPE = "null"
    private const val STRING_TYPE = "string"
    private const val NUMBER_TYPE = "number"
    private const val BOOLEAN_TYPE = "boolean"
    private const val INTEGER_TYPE = "integer"
    private const val KEY = "type"


    private val ruleMap = mapOf(
        ARRAY_TYPE to ArrayRule,
        OBJECT_TYPE to ObjectRule,
        NULL_TYPE to NullRule,
        STRING_TYPE to StringRule,
        NUMBER_TYPE to NumberRule,
        BOOLEAN_TYPE to BooleanRule,
        INTEGER_TYPE to IntegerRule
    )

    override fun canParse(element: JsonObject): Boolean {
        return element.get(KEY) != null
    }

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val schemaPath = objectKey(path, KEY)
        return when (val typeEntry = element.get(KEY)) {
            is JsonArray -> parseTypeList(base, schemaPath, typeEntry)
            is JsonScalar -> parseType(base, schemaPath, typeEntry)
            else -> Schema(base, schemaPath, Error("Element is neither an array or a scalar"))
        }
    }

    private fun parseTypeList(base: String, path: String, array: JsonArray): Schema {
        val schemas = array.elements().map { x -> this.parseType(base, path, x) }
        return schemas.combine(base, path, ::OrRule)
    }

    private fun parseType(base: String, path: String, element: JsonElement): Schema {
        return element.string().fold({ error -> Schema(base, path, error)}) { x -> parseType(base, path, x) }
    }

    private fun parseType(base: String, path: String, type: String): Schema {
        val value = ruleMap[type]
        return if (value != null) Schema(value)
        else Schema(base, path, Error("$type is not an allowed value"))
    }
}

object ObjectRule : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asObject().fold({ listOf(RuleError(path, it.reason)) }) { emptyList() }
    }
}

object ArrayRule : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array().fold({ listOf(RuleError(path, it.reason)) }) { emptyList() }
    }
}

object StringRule : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.string().fold({ listOf(RuleError(path, it.reason)) } ) { emptyList() }
    }
}

object BooleanRule : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.boolean().fold({ listOf(RuleError(path, it.reason)) } ) { emptyList() }
    }
}

object NumberRule : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.double().fold({ listOf(RuleError(path, it.reason)) } ) { emptyList() }
    }
}

object NullRule : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asNull().fold({ listOf(RuleError(path, it.reason)) } ) { emptyList() }
    }
}

object IntegerRule : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.integer().fold({ listOf(RuleError(path, it.reason)) } ) { emptyList() }
    }
}
