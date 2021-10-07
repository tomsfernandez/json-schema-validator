package org.validator.rules.numeric

import org.validator.*

object ExclusiveMinimumRuleParser : NumberRuleParser {

    override val key: String = "exclusiveMinimum"

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return Either.Right(ExclusiveMinimumRule(number))
    }
}

data class ExclusiveMinimumRule(val minimum: Number) : NumberRule {
    override fun eval(number: Number): List<Error> {
        return if (number.toDouble() > minimum.toDouble()) emptyList()
        else listOf(Error("$number is equal or smaller than $minimum"))
    }
}
