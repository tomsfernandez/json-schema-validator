package org.validator.rules.numeric

import org.validator.*

object MultipleOfRuleParser : NumberRuleParser {

    override val KEY: String = "multipleOf"

    override fun parse(number: Number): Schema {
        return if (number == 0) Schema(Error("0 is not a valid multipleOf anything"))
        else Schema(MultipleOfRule(number))
    }
}

data class MultipleOfRule(val multipleOf: Number) : NumberRule {

    override fun eval(path: String, number: Number): List<RuleError> {
        return if ((number.toDouble() / multipleOf.toDouble()) % 1 == 0.0) emptyList()
        else listOf(RuleError(path, "$number is not multiple of $multipleOf"))
    }
}
