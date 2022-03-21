package org.validator.rules.string

import org.validator.*

object MaxLengthRuleParser: StringLengthRuleParser {

    override val key: String = "maxLength"

    override fun parse(base: String, finalPath: String, amount: Int): Schema {
        return Schema(base, finalPath, MaxLengthRule(amount))
    }
}

data class MaxLengthRule(val maximum: Int): StringLengthRule {
    override fun eval(path: String, string: String): List<RuleError> {
        return if (characterCount(string) > maximum) listOf(RuleError(path, "String should be longer than $maximum"))
        else emptyList()
    }
}
