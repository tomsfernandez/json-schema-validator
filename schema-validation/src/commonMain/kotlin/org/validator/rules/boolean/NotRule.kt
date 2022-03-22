package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class NotRule(val rule: SchemaRule): SchemaRule {

    companion object {
        private fun ERROR(path: String) = RuleError(path, "Element conforms with the 'not' schema")
    }

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val isValid = rule.eval(path, element, schema).isEmpty()
        return if (isValid) listOf(ERROR(path))
        else emptyList()
    }
}

data class NotRuleParser(val parserFactory: SchemaRuleParserFactory): RuleParser {

    private val KEY: String = "not"

    override fun canParse(element: JsonObject) = element.get(KEY) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        val notValue = element.get(KEY) !!
        return parserFactory.make().parse(base, path, notValue).map(base, finalPath) { NotRule(it) }
    }
}
