package org.validator.rules.numeric

import org.validator.*

object ConditionalExclusiveMaximumRuleParser: ConditionalExclusiveRuleParser {

    override val KEY = "maximum"
    override val CONDITIONAL_KEY = "exclusiveMaximum"

    override fun rule(integer: Double, exclusive: Boolean): SchemaRule {
        return ConditionalExclusiveMaximumRule(integer, exclusive)
    }
}

data class ConditionalExclusiveMaximumRule(val maximum: Number, val exclusive: Boolean): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.double().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(number: Double): List<Error> {
        return if (exclusive && biggerOrEqualsThan(number)) listOf(Error("$number is equal or bigger than $maximum"))
        else if(!exclusive && biggerThan(number)) listOf(Error("$number is bigger than $maximum"))
        else emptyList()
    }

    private fun biggerThan(number: Double): Boolean = number > maximum.toDouble()

    private fun biggerOrEqualsThan(number: Number) = number.toDouble() >= maximum.toDouble()
}
