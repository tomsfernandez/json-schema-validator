package org.validator.rules.numeric

import org.validator.*

object MaximumRuleParser : NumberRuleParser {

    override val KEY: String = "maximum"

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return Either.Right(MaximumRule(number))
    }
}

data class MaximumRule(val maximum: Number) : NumberRule {
    override fun eval(number: Number): List<Error> {
        return if (number.toDouble() <= maximum.toDouble()) emptyList()
        else listOf(Error("$number is bigger than $maximum"))
    }
}
