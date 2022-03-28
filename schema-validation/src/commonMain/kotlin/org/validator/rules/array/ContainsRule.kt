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

data class MinMaxContainsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val MAX_KEY = "maxContains"
    private val MIN_KEY = "minContains"
    private val KEY = "contains"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val max = element.get(MAX_KEY).integer().right()
        val min = element.get(MIN_KEY).integer().rightOrDefault(1)
        val containsValue = element.get(KEY) !!
        val finalPath = objectKey(path, KEY)
        val schema = factory.make().parse(base, finalPath, containsValue)
        return schema
            .addSchema(finalPath, Schema(ContainsRule(schema.rule)))
            .map { MinMaxContainsRule(schema.rule, min, max) }
    }
}

data class MinMaxContainsRule(val rule: SchemaRule, val min: Int, val max: Int?): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array().map { array ->
            val conformAmount = array.elements().map { rule.eval(path, it, schema).isEmpty() }.count { it }
            if (min == 0 && max == null) emptyList<RuleError>()
            else {
                val errors = mutableListOf<RuleError>()
                if (max != null && conformAmount > max) errors.add(RuleError(path, "The amount of array items that matched the 'contains' node was bigger than $max"))
                if (conformAmount < min) errors.add(RuleError(path, "The amount of array items that matched the 'contains' node was smaller than $min"))
                errors
            }
        }.rightOrDefault(emptyList())
    }
}
