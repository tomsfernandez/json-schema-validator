package org.validator.rules.array

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class ContainsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val KEY = "contains"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val containsValue = element.get(KEY) !!
        val finalPath = objectKey(path, KEY)
        return factory.make().parse(base, finalPath, containsValue).map { ContainsRule(it) }
    }
}
data class ContainsRule(private val rule: SchemaRule): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array().map { array ->
            val errors = array.elements()
                .mapIndexed { index, elem -> Pair(index, elem) }
                .map {
                    val (index, elem) = it
                    val nextPath = arrayIndex(path, index)
                    rule.eval(nextPath, elem, schema)
                }
            if (errors.any { list -> list.isEmpty()}) emptyList()
            else listOf(RuleError(path, "No object could be found that matched the schema"))

        }.rightOrDefault(emptyList())
    }
}
