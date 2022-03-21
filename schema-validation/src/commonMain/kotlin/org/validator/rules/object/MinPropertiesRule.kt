package org.validator.rules.`object`

import org.validator.*

object MinPropertiesRuleParser: PropertyAmountParser() {

    override val KEY = "minProperties"
    private val ERROR = Error("minProperties should have a non-negative value")

    override fun parse(base: String, path: String, number: Number): Schema {
        val finalPath = objectKey(path, KEY)
        return if (number.toInt() < 0) Schema(base, finalPath, ERROR)
        else Schema(base, finalPath, MinPropertiesRule(number.toInt()))
    }
}

data class MinPropertiesRule(val minimum: Int): SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asObject().fold({ emptyList() }) { x ->
            if (x.keys().size >= minimum) emptyList()
            else listOf(RuleError(path, "Object has ${x.keys().size} but minimum is $minimum"))
        }
    }
}
