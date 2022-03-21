package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class AnyOfRule(val rules: List<SchemaRule>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val ruleEvals = rules.map { rule -> rule.eval(path, element, schema) }
        val atLeastOneIsValid = ruleEvals.any { x -> x.isEmpty() }
        return if (!atLeastOneIsValid) listOf(RuleError(path, "Schema doesn't conform to at least one schema in the anyOf", ruleEvals.flatten()))
        else emptyList()
    }
}

class AnyOfRuleParser(parserFactory: SchemaRuleParserFactory) : OfRuleParser("anyOf", { schemas -> AnyOfRule(schemas)}, { base, path, obj -> parserFactory.make().parse(base,
    path,
    obj) })
