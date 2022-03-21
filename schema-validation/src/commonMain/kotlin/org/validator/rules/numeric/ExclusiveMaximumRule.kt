package org.validator.rules.numeric

import org.validator.*

object ExclusiveMaximumRuleParser : NumberRuleParser {

    override val KEY: String = "exclusiveMaximum"

    override fun parse(number: Number): Schema {
        return Schema(ExclusiveMaximumRule(number))
    }
}

data class ExclusiveMaximumRule(val maximum: Number) : NumberRule {
    override fun eval(path: String, number: Number): List<RuleError> {
        return if (number.toDouble() < maximum.toDouble()) emptyList()
        else listOf(RuleError(path, "$number is equal or bigger than $maximum"))
    }
}
