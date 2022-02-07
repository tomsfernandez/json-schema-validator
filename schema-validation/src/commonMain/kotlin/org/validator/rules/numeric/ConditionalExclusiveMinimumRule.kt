package org.validator.rules.numeric

import org.validator.*
import org.validator.Either.*

object ConditionalExclusiveMinimumRuleParser: ConditionalExclusiveRuleParser {

    override val KEY = "minimum"
    override val CONDITIONAL_KEY = "exclusiveMinimum"

    override fun rule(integer: Double, exclusive: Boolean): ValidationRule {
        return ConditionalExclusiveMinimumRule(integer, exclusive)
    }
}

data class ConditionalExclusiveMinimumRule(val minimum: Number, val exclusive: Boolean): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.double().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(number: Number): List<Error> {
        return if (exclusive && number.toDouble() <= minimum.toDouble()) listOf(Error("$number is equal or smaller than $minimum"))
        else if (number.toDouble() >= minimum.toDouble()) emptyList()
        else listOf(Error("$number is smaller than $minimum"))
    }
}
