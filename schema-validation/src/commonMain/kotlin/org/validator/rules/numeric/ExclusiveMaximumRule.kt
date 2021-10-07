package org.validator.rules.numeric

import org.validator.*

object ExclusiveMaximumRuleParser : NumberRuleParser {

    override val key: String = "exclusiveMaximum"

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return Either.Right(ExclusiveMaximumRule(number))
    }
}

data class ExclusiveMaximumRule(val maximum: Number) : NumberRule {
    override fun eval(number: Number): List<Error> {
        return if (number.toDouble() < maximum.toDouble()) emptyList()
        else listOf(Error("$number is equal or bigger than $maximum"))
    }
}
