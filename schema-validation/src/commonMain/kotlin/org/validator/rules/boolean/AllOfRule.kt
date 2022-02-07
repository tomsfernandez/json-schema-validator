package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class AllOfRule(val rules: List<ValidationRule>): ValidationRule {

    companion object {
        private val ERROR = Error("Schema doesn't conform to all schemas in allOf")
    }
    override fun eval(element: JsonElement): List<Error> {
        val errors = rules.map { x -> x.eval(element) }.flatten()
        val hasAnyErrors = errors.any()
        return if (hasAnyErrors) listOf(ERROR)
        else emptyList()
    }
}
data class AllOfRuleParser(val parserFactory: SchemaRuleParserFactory) : OfRuleParser("allOf", { AllOfRule(it)}, { parserFactory.make().parse(it) })

