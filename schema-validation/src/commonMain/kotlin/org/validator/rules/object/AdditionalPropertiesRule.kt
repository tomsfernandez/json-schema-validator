package org.validator.rules.`object`

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class AdditionalPropertiesRuleParser(val factory: SchemaRuleParserFactory) : RuleParser {

    private val key = "additionalProperties"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when(val entry = element.get(key)) {
            is BooleanJsonScalar -> parseBooleanValue(entry, element)
            is JsonObject -> parseObjectValue(entry, element)
            else -> Either.Left(listOf(Error("additionalProperties should be a boolean")))
        }
    }

    private fun parseObjectValue(element: JsonObject, parent: JsonObject): Either<List<Error>, ValidationRule> {
        val parsedRule = factory.make().parse(element)
        val ignoredProperties = scanDeclaredProperties(parent)
        val pattern = scanPatternProperties(parent)
        return parsedRule.map { rule -> ObjectAdditionalPropertiesRule(rule, ignoredProperties, pattern)}
    }

    private fun parseBooleanValue(scalar: BooleanJsonScalar, parent: JsonObject): Either<List<Error>, ValidationRule> {
        val allowed = if (!scalar.value) scanDeclaredProperties(parent) else emptyList()
        val pattern = if (!scalar.value) scanPatternProperties(parent) else emptyList()
        return Either.Right(BooleanAdditionalPropertiesRule(scalar.value, allowed, pattern))
    }

    private fun scanDeclaredProperties(parent: JsonObject) =
        parent.get("properties").asObject().map { x -> x.keys().toList() }.rightOrDefault(emptyList<String>())

    private fun scanPatternProperties(parent: JsonObject) =
        parent.get("patternProperties").asObject().map { x -> x.keys().map { it.toRegex() }.toList() }.rightOrDefault(emptyList<Regex>())
}

data class BooleanAdditionalPropertiesRule(val unlimitedProperties: Boolean, val allowedList: List<String>, val patternProps: List<Regex>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return if (unlimitedProperties) emptyList()
        else {
            return when(element) {
                is JsonObject -> {
                    val explicitNotAllowed = element.keys() - allowedList.toSet()
                    val patternedNotAllowed = explicitNotAllowed.filter { key -> patternProps.none { it.containsMatchIn(key)} }
                    return patternedNotAllowed.map { Error("$it is not an allowed property") }
                }
                else -> emptyList()
            }
        }
    }
}

data class ObjectAdditionalPropertiesRule(val rule: ValidationRule, val ignoredProperties: List<String>, val patternProps: List<Regex>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return when(element) {
            is JsonObject -> {
                val notAllowed = (element.keys() - ignoredProperties.toSet()).filter { key -> !patternProps.any { it.containsMatchIn(key)} }
                return notAllowed
                    .mapNotNull { element.get(it) }
                    .map { rule.eval(it) }
                    .flatten()
            }
            else -> emptyList()
        }
    }
}
