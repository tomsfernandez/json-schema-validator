package org.validator.rules.numeric

import org.validator.*

object MaximumRuleParser : NumberRuleParser {

    override val KEY: String = "maximum"

    override fun parse(number: Number): Schema {
        return Schema(MaximumRule(number))
    }
}

data class MaximumRule(val maximum: Number) : NumberRule {
    override fun eval(path: String, number: Number): List<RuleError> {
        return if (number.toDouble() <= maximum.toDouble()) emptyList()
        else listOf(RuleError(path, "$number is bigger than $maximum"))
    }
}
