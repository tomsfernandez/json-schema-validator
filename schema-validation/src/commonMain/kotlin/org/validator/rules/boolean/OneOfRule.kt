package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class OneOfRule(val rules: List<SchemaRule>): SchemaRule {

    companion object {
        private fun ERROR(path: String) = RuleError(path, "Schema doesn't conform to only one schema of the oneOf")
    }

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val ruleEvals = rules.map { it.eval(path, element, schema) }
        val nonCompliantRules = ruleEvals.filter { it.isNotEmpty() }
        val onlyOneRuleIsValid = (rules.size - nonCompliantRules.size) == 1
        return if (onlyOneRuleIsValid) emptyList()
        else listOf(ERROR(path))
    }
}

data class OneOfRuleParser(val parserFactory: SchemaRuleParserFactory) : OfRuleParser("oneOf", { schemas -> OneOfRule(schemas)}, { base, path, obj -> parserFactory.make().parse(base,
    path,
    obj) })
