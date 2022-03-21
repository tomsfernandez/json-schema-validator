package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class AllOfRule(val rules: List<SchemaRule>): SchemaRule {

    companion object {
        private fun ERROR(path: String, subErrors: List<RuleError>) = RuleError(path,"Schema doesn't conform to all schemas in allOf", subErrors)
    }
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val errors = rules.map { rule -> rule.eval(path, element, schema) }.flatten()
        val hasAnyErrors = errors.any()
        return if (hasAnyErrors) listOf(ERROR(path, errors))
        else emptyList()
    }
}

data class AllOfRuleParser(val parserFactory: SchemaRuleParserFactory) : OfRuleParser("allOf", ::AllOfRule , { base, path, obj -> parserFactory.make().parse(base,
    path,
    obj) })
