package org.validator.rules.string

import org.validator.*

object MaxLengthRuleParser: StringLengthRuleParser {

    override val key: String = "maxLength"

    override fun parse(amount: Int): Either<List<Error>, ValidationRule> {
        return Either.Right(MaxLengthRule(amount))
    }
}

data class MaxLengthRule(val maximum: Int): StringLengthRule {
    override fun eval(string: String): List<Error> {
        return if (string.length > maximum) listOf(Error("String should be longer than $maximum"))
        else emptyList()
    }
}
