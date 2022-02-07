package org.validator.rules.numeric

import org.validator.*

object MultipleOfRuleParser : NumberRuleParser {

    override val KEY: String = "multipleOf"

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return if (number == 0) Either.Left(listOf(Error("0 is not a valid multipleOf anything")))
        else Either.Right(MultipleOfRule(number))
    }
}

data class MultipleOfRule(val multipleOf: Number) : NumberRule {

    override fun eval(number: Number): List<Error> {
        return if ((number.toDouble() / multipleOf.toDouble()) % 1 == 0.0) emptyList()
        else listOf(Error("$number is not multiple of $multipleOf"))
    }
}
