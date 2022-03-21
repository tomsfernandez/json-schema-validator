package org.validator.rules.string

import org.validator.*

object MinLengthRuleParser: StringLengthRuleParser {

    override val key: String = "minLength"

    override fun parse(base: String, finalPath: String, amount: Int): Schema {
        return Schema(base, finalPath, MinLengthRule(amount))
    }
}

data class MinLengthRule(val minimum: Int): StringLengthRule {
    override fun eval(path: String, string: String): List<RuleError> {
        val stringSize = characterCount(string)
        return if (stringSize < minimum) listOf(RuleError(path, "String should be shorter than $minimum"))
        else emptyList()
    }
}
