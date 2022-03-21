package org.validator.rules.numeric

import org.validator.*

object MinimumRuleParser : NumberRuleParser {

    override val KEY: String = "minimum"

    override fun parse(number: Number): Schema {
        return Schema(MinimumRule(number))
    }
}

data class MinimumRule(val minimum: Number) : NumberRule {
    override fun eval(path: String, number: Number): List<RuleError> {
        return if (number.toDouble() >= minimum.toDouble()) emptyList()
        else listOf(RuleError(path, "$number is smaller than $minimum"))
    }
}
