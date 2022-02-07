package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class OneOfRule(val rules: List<ValidationRule>): ValidationRule {

    companion object {
        private val ERROR = Error("Schema doesn't conform to only one schema of the oneOf")
    }

    override fun eval(element: JsonElement): List<Error> {
        val nonCompliantRules = rules.filter { x -> x.eval(element).isNotEmpty() }
        val onlyOneRuleIsValid = (rules.size - nonCompliantRules.size) == 1
        return if (onlyOneRuleIsValid) emptyList()
        else listOf(ERROR)
    }
}

data class OneOfRuleParser(val parserFactory: SchemaRuleParserFactory) : OfRuleParser("oneOf", { schemas -> OneOfRule(schemas)}, { element -> parserFactory.make().parse(element) })
