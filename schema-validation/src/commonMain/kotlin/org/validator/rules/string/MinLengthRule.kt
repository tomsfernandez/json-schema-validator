package org.validator.rules.string

import org.validator.*

object MinLengthRuleParser: StringLengthRuleParser {

    override val key: String = "minLength"

    override fun parse(amount: Int): Either<List<Error>, ValidationRule> {
        return Either.Right(MinLengthRule(amount))
    }
}

data class MinLengthRule(val minimum: Int): StringLengthRule {
    override fun eval(string: String): List<Error> {
        val stringSize = characterCount(string)
        return if (stringSize < minimum) listOf(Error("String should be shorter than $minimum"))
        else emptyList()
    }
}

expect fun characterCount(value: String): Int
