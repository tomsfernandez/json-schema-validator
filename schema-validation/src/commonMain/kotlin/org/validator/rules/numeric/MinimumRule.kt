package org.validator.rules.numeric

import org.validator.*

object MinimumRuleParser : NumberRuleParser {

    override val key: String = "minimum"

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return Either.Right(MinimumRule(number))
    }
}

data class MinimumRule(val minimum: Number) : NumberRule {
    override fun eval(number: Number): List<Error> {
        return if (number.toDouble() >= minimum.toDouble()) emptyList()
        else listOf(Error("$number is smaller than $minimum"))
    }
}
