package org.validator.rules.numeric

import org.validator.*

object ExclusiveMinimumRuleParser : NumberRuleParser {

    override val KEY: String = "exclusiveMinimum"

    override fun parse(number: Number): Schema {
        return Schema(ExclusiveMinimumRule(number))
    }
}

data class ExclusiveMinimumRule(val minimum: Number) : NumberRule {
    override fun eval(path: String, number: Number): List<RuleError> {
        return if (number.toDouble() > minimum.toDouble()) emptyList()
        else listOf(RuleError(path, "$number is equal or smaller than $minimum"))
    }
}
