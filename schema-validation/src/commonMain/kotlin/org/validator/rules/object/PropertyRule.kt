package org.validator.rules.`object`

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class PropertyRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val key = "properties"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return Either.Left(listOf(Error("asdasdasd")))
    }

    private fun parseProperty(key: String, element: JsonObject): Either<List<Error>, PropertyRule> {
        val eitherRule = factory.make().parse(element)
        return eitherRule.map { x -> PropertyRule(key, x) }
    }
}

data class PropertiesRule(val rules: List<PropertyRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return rules.flatMap { rule -> rule.eval(element) }
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
