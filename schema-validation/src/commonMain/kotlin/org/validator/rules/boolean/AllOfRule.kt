package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class AllOfRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        val hasAnyErrors = rules.map { x -> x.eval(element) }.flatten().any()
        return if (hasAnyErrors) listOf(Error("Schema doesn't conform to all schemas in allOf"))
        else emptyList()
    }
}
data class AllOfRuleParser(val parserFactory: SchemaRuleParserFactory) : ArrayOfRuleParser("allOf", { schemas -> AllOfRule(schemas)}, { element -> parserFactory.make().parse(element) })

