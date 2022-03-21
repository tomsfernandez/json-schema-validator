package org.validator.rules.`object`

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class AdditionalPropertiesRuleParser(val factory: SchemaRuleParserFactory) : RuleParser {

    private val KEY = "additionalProperties"

    override fun canParse(element: JsonObject): Boolean = element.get(KEY) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return when(val entry = element.get(KEY)) {
            is JsonBoolean -> parseBooleanValue(base, finalPath, entry, element)
            is JsonObject -> parseObjectValue(base, finalPath, entry, element)
            else -> Schema(base, finalPath, Error("additionalProperties should be a boolean"))
        }
    }

    private fun parseObjectValue(base: String, finalPath: String, element: JsonObject, parent: JsonObject): Schema {
        val parsedRule = factory.make().parse(base, finalPath, element)
        val ignoredProperties = scanDeclaredProperties(parent)
        val pattern = scanPatternProperties(parent)
        return parsedRule.map(base, finalPath) { rule -> ObjectAdditionalPropertiesRule(rule, ignoredProperties, pattern)}
    }

    private fun parseBooleanValue(base: String, finalPath: String, scalar: JsonBoolean, parent: JsonObject): Schema {
        val allowed = if (!scalar.value) scanDeclaredProperties(parent) else emptyList()
        val pattern = if (!scalar.value) scanPatternProperties(parent) else emptyList()
        return Schema(base, finalPath, BooleanAdditionalPropertiesRule(scalar.value, allowed, pattern))
    }

    private fun scanDeclaredProperties(parent: JsonObject) =
        parent.get("properties").asObject().map { x -> x.keys().toList() }.rightOrDefault(emptyList<String>())

    private fun scanPatternProperties(parent: JsonObject) =
        parent.get("patternProperties").asObject().map { x -> x.keys().map { it.toRegex() }.toList() }.rightOrDefault(emptyList<Regex>())
}

data class BooleanAdditionalPropertiesRule(val unlimitedProperties: Boolean, val allowedList: List<String>, val patternProps: List<Regex>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return if (unlimitedProperties) emptyList()
        else {
            return when(element) {
                is JsonObject -> {
                    val explicitNotAllowed = element.keys() - allowedList.toSet()
                    val patternedNotAllowed = explicitNotAllowed.filter { key -> patternProps.none { it.containsMatchIn(key)} }
                    return patternedNotAllowed.map { RuleError(objectKey(path, it), "$it is not an allowed property") }
                }
                else -> emptyList()
            }
        }
    }
}

data class ObjectAdditionalPropertiesRule(val rule: SchemaRule, val ignoredProperties: List<String>, val patternProps: List<Regex>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return when(element) {
            is JsonObject -> {
                val notAllowed = (element.keys() - ignoredProperties.toSet()).filter { key -> !patternProps.any { it.containsMatchIn(key)} }
                return notAllowed
                    .map { Pair(it, element.get(it) !!) }
                    .map { (key, element) -> rule.eval(objectKey(path, key), element, schema) }
                    .flatten()
            }
            else -> emptyList()
        }
    }
}
