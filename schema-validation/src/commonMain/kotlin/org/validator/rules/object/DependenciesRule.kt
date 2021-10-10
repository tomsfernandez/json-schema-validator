package org.validator.rules.`object`

import org.validator.*
import org.validator.rules.SchemaRule
import org.validator.rules.SchemaRuleParserFactory

data class SchemaDependenciesRule(val property: String, val rule: ValidationRule): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return when(element) {
            is JsonObject -> {
                val propertyExists = element.containsKey(property)
                return if (propertyExists) rule.eval(element) else emptyList()
            } else -> emptyList()
        }
    }
}

data class DependenciesRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val key = "dependencies"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(key).asObject().mapEither(::listOf) { obj ->
            val parseResults = obj.entries().map { e -> parseRule(e.first, e.second) }
            val errors = parseResults.mapNotNull { e -> e.left() }.flatten()
            if (errors.any()) Either.Left(errors)
            else Either.Right(SchemaRule(parseResults.mapNotNull { e -> e.right() }))
        }
    }

    private fun parseRule(key: String, element: JsonElement): Either<List<Error>, ValidationRule> {
        return when(element) {
            is JsonArray -> parseRequiredRule(key, element)
            is JsonObject -> parseSchemaRule(key, element)
            else -> Either.Left(listOf(Error("Value is neither an array or an object")))
        }
    }

    private fun parseSchemaRule(key: String, obj: JsonObject): Either<List<Error>, ValidationRule> {
        val rule = factory.make().parse(obj)
        return rule.map { SchemaDependenciesRule(key, it) }
    }

    private fun parseRequiredRule(key: String, array: JsonArray): Either<List<Error>, ValidationRule> {
        val conversions = array.elements().map { it.asString() }
        val errors: List<Error> = conversions.mapNotNull { it.left() }
        return if(errors.any()) Either.Left(errors)
        else Either.Right(PropertyDependenciesRule(key, conversions.mapNotNull { it.right() }.toSet()))
    }
}

data class PropertyDependenciesRule(val property: String, val required: Set<String>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return when(element) {
            is JsonObject -> {
                val propertyValue = element.get(property)
                val missingRequiredProperties = (required - element.keys())
                return when {
                    propertyValue == null -> emptyList()
                    missingRequiredProperties.isEmpty() -> emptyList()
                    else -> missingRequiredProperties.map { x -> Error("$x is required because $property entry exists") }
                }
            } else -> emptyList()
        }
    }
}
