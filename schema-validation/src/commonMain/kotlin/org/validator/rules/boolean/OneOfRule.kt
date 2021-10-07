package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class OneOfRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        val onlyOne = rules.map { x -> x.eval(element).isNotEmpty() }.filter { x -> x }.size == 1
        return if (onlyOne) listOf(Error("Schema doesn't conform to only one schema of the oneOf"))
        else emptyList()
    }
}

data class OneOfRuleParser(val parserFactory: SchemaRuleParserFactory) : ArrayOfRuleParser("oneOf", { schemas -> OneOfRule(schemas)}, { element -> parserFactory.make().parse(element) })
