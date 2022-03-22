package org.validator.rules.`object`

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class PropertyNamesRule(val inner: SchemaRule): SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asObject().map {
            val errors = it.keys().flatMap { inner.eval(objectKey(path, it), JsonString(it), schema) }
            errors
        }.rightOrDefault(emptyList())
    }
}

data class PropertyNamesRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val KEY = "propertyNames"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val propertyNamesValue = element.get(KEY) !!
        return factory.make().parse(base, objectKey(path, KEY), propertyNamesValue).map { PropertyNamesRule(it) }
    }
}
