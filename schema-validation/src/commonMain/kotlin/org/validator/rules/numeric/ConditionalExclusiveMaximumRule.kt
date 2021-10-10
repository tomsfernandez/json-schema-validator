package org.validator.rules.numeric

import org.validator.*
import org.validator.Either.*

object ConditionalExclusiveMaximumRuleParser: RuleParser {

    private val key = "maximum"
    private val conditionalKey = "exclusiveMaximum"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null || element.get(conditionalKey) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val maximum = element.get(key)
        val exclusive = element.get(conditionalKey)

        if (maximum == null && exclusive != null) return Left(listOf(Error("maximum key must exist to use exclusiveMaximum")))

        return when(val maxInt = maximum.asNumber()) {
            is Left -> maxInt.mapLeft(::listOf)
            is Right -> {
                if (exclusive == null) return Right(ConditionalExclusiveMaximumRule(maxInt.r, false))
                return when(val exclusiveBool = exclusive.asBoolean()) {
                    is Left -> exclusiveBool.mapLeft(::listOf)
                    is Right -> Right(ConditionalExclusiveMaximumRule(maxInt.r, exclusiveBool.r))
                }
            }
        }
    }
}

data class ConditionalExclusiveMaximumRule(val maximum: Number, val exclusive: Boolean): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asNumber().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(number: Number): List<Error> {
        return if (exclusive && number.toDouble() >= maximum.toDouble()) listOf(Error("$number is equal or bigger than $maximum"))
        else if (number.toDouble() <= maximum.toDouble()) emptyList()
        else listOf(Error("$number is bigger than $maximum"))
    }
}
