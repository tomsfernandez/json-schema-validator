package org.validator.rules.`object`

import org.validator.*
import org.validator.rules.CompositeSchemaRule
import org.validator.rules.SchemaRuleParserFactory

data class SchemaDependenciesRule(val property: String, val rule: SchemaRule): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return when(element) {
            is JsonObject -> {
                val propertyExists = element.containsKey(property)
                return if (propertyExists) rule.eval(objectKey(path, property), element, schema) else emptyList()
            } else -> emptyList()
        }
    }
}

open class DependenciesRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val key = "dependencies"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, key)
        return element.get(key).asObject().fold({ error -> Schema(base, finalPath, error)}) { obj ->
            val parseResults = obj.entries().map { e -> parseRule(base, e.first, path, e.second) }
            parseResults.combine(base, finalPath, ::CompositeSchemaRule)
        }
    }

    private fun parseRule(base: String, key: String, path: String, element: JsonElement): Schema {
        return when(element) {
            is JsonArray -> DependentRequiredRuleParser.parseRequiredRule(key, path, element)
            else -> DependentSchemasRuleParser(factory).parseSchemaRule(base, key, path, element)
        }
    }
}

data class DependentSchemasRuleParser(val factory: SchemaRuleParserFactory): RuleParser {
    private val key = "dependentSchemas"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(key)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, key)
        return element.get(key).asObject().fold({ error -> Schema(base, finalPath, error)}) { obj ->
            val parseResults = obj.entries().map { (key, element) -> parseSchemaRule(base, key, path, element) }
            parseResults.combine(base, finalPath, ::CompositeSchemaRule)
        }
    }

    fun parseSchemaRule(base: String, key: String, path: String, element: JsonElement): Schema {
        val finalPath = objectKey(path, encodeUri(key))
        val rule = factory.make().parse(base, path, element)
        return rule.map(base, finalPath) { SchemaDependenciesRule(key, it) }
    }
}

object DependentRequiredRuleParser: RuleParser {
    private val key = "dependentRequired"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(key)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, key)
        return element.get(key).asObject().fold({ error -> Schema(base, finalPath, error)}) { obj ->
            val parseResults = obj.entries().map { (key, element) -> parseRequiredRule(key, path, element) }
            parseResults.combine(base, finalPath, ::CompositeSchemaRule)
        }
    }

    fun parseRequiredRule(key: String, path: String, element: JsonElement): Schema {
        return element.array().fold(::Schema) { array ->
            val (errors, conversions) = array.elements().map { it.string() }.partitionList()
            Schema(PropertyDependenciesRule(key, conversions.toSet()), errors)
        }
    }
}

data class PropertyDependenciesRule(val property: String, val required: Set<String>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return when(element) {
            is JsonObject -> {
                val propertyValue = element.get(property)
                val missingRequiredProperties = (required - element.keys())
                return when {
                    propertyValue == null -> emptyList()
                    missingRequiredProperties.isEmpty() -> emptyList()
                    else -> missingRequiredProperties.map { x -> RuleError(path, "$x is required because $property entry exists") }
                }
            } else -> emptyList()
        }
    }
}
