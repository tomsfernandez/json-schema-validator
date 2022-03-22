package org.validator.rules.`object`

import org.validator.*
import org.validator.Either.*
import org.validator.rules.SchemaRuleParserFactory

typealias SpecificPropertyParser = (key: String, rule: SchemaRule) -> SchemaRule

open class AbstractPropertiesRuleParser(val key: String, private val factory: SchemaRuleParserFactory, val parser: SpecificPropertyParser): RuleParser {

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, key)
        return when(val entry = element.get(key)) {
            is JsonObject -> {
                val schemas =  entry.entries().map {
                    val (key, element) = it
                    val propFinalPath = objectKey(finalPath, encodeUri(key))
                    parseProperty(base, key, finalPath, element)
                }
                return schemas.combine(base, finalPath, ::PropertiesRule)
            }
            else -> Schema(base, finalPath, Error("Properties must be an object"))
        }
    }

    private fun parseProperty(base: String, key: String, path: String, element: JsonElement): Schema {
        val finalPath = objectKey(path, encodeUri(key))
        return factory.make().parse(base, finalPath, element).map { rule -> parser(key, rule) }
    }
}

val parseProperty: SpecificPropertyParser = { key, rule -> PropertyRule(key, rule) }

val parsePatternProperty: SpecificPropertyParser = { key, rule -> PatternPropertyRule(key.toRegex(), rule)}

data class PropertiesRuleParser(val factory: SchemaRuleParserFactory): AbstractPropertiesRuleParser("properties", factory, parseProperty)

data class PatternPropertiesRuleParser(val factory: SchemaRuleParserFactory): AbstractPropertiesRuleParser("patternProperties", factory, parsePatternProperty)

data class PropertiesRule(val rules: List<SchemaRule>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return rules.flatMap { rule -> rule.eval(path, element, schema) }
    }
}

data class PatternPropertyRule(val regex: Regex, val rule: SchemaRule) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asObject().fold({ emptyList() }) { obj ->
            val properties = obj.entries(regex)
            properties.flatMap { (key, element) -> rule.eval(objectKey(path, key), element, schema)}
        }
    }
}

data class PropertyRule(val name: String, val rule: SchemaRule) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asObject().fold({ emptyList() }) { obj ->
            val property = obj.get(name)
            if(property != null) rule.eval(objectKey(path, name), property, schema)
            else emptyList()
        }
    }
}
