package org.validator.rules.`object`

import org.validator.*
import org.validator.Either.*
import org.validator.rules.SchemaRuleParserFactory

typealias SpecificPropertyParser = (key: String, rule: ValidationRule) -> ValidationRule

open class AbstractPropertiesRuleParser(val key: String, private val factory: SchemaRuleParserFactory, val parser: SpecificPropertyParser): RuleParser {

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when(val entry = element.get(key)) {
            is JsonObject -> {
                val maybeObjects =  entry.entries().map { Pair(it.first, it.second.asObject()) }
                val (errors, conversions) = maybeObjects.partitionPairList()
                if (errors.any()) return Left(errors.map { it.second })

                val (parseErrors, rules) = conversions.map { parseProperty(it.first, it.second) }.partitionList()
                return if (parseErrors.any()) Left(errors.map { it.second })
                else Right(PropertiesRule(rules))
            }
            else -> Left(listOf(Error("Properties must be an object")))
        }
    }

    private fun parseProperty(key: String, obj: JsonObject): Either<List<Error>, ValidationRule> {
        return factory.make().parse(obj).map { rule -> parser(key, rule) }
    }
}

val parseProperty: SpecificPropertyParser = { key, rule -> PropertyRule(key, rule) }

val parsePatternProperty: SpecificPropertyParser = { key, rule -> PatternPropertyRule(key.toRegex(), rule)}

data class PropertiesRuleParser(val factory: SchemaRuleParserFactory): AbstractPropertiesRuleParser("properties", factory, parseProperty)

data class PatternPropertiesRuleParser(val factory: SchemaRuleParserFactory): AbstractPropertiesRuleParser("patternProperties", factory, parsePatternProperty)

data class PropertiesRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return rules.flatMap { rule -> rule.eval(element) }
    }
}

data class PatternPropertyRule(val regex: Regex, val rule: ValidationRule) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asObject().fold({ emptyList() }) { obj ->
            val properties = obj.entries(regex)
            properties.flatMap { prop -> rule.eval(prop.second)}
        }
    }
}

data class PropertyRule(val name: String, val rule: ValidationRule) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asObject().fold({ emptyList() }) { obj ->
            val property = obj.get(name)
            if(property != null) rule.eval(property)
            else emptyList()
        }
    }
}
