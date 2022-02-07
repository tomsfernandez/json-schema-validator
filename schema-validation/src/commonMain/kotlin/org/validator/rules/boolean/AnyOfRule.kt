package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class AnyOfRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        val atLeastOneIsValid = rules.takeWhile { x -> x.eval(element).isNotEmpty() }.size != rules.size
        return if (!atLeastOneIsValid) listOf(Error("Schema doesn't conform to at least one schema in the anyOf"))
        else emptyList()
    }
}

class AnyOfRuleParser(parserFactory: SchemaRuleParserFactory) : OfRuleParser("anyOf", { schemas -> AnyOfRule(schemas)}, { element -> parserFactory.make().parse(element) })
