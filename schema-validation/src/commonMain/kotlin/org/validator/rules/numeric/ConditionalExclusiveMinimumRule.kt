package org.validator.rules.numeric

import org.validator.*
import org.validator.Either.*

object ConditionalExclusiveMinimumRuleParser: RuleParser {

    private val key = "minimum"
    private val conditionalKey = "exclusiveMinimum"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null || element.get(conditionalKey) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val minimum = element.get(key)
        val exclusive = element.get(conditionalKey)

        if (minimum == null && exclusive != null) return Left(listOf(Error("minimum key must exist to use exclusiveMinimum")))

        return when(val minInt = minimum.asNumber()) {
            is Left -> minInt.mapLeft(::listOf)
            is Right -> {
                if (exclusive == null) return Right(ConditionalExclusiveMinimumRule(minInt.r, false))
                return when(val exclusiveBool = exclusive.asBoolean()) {
                    is Left -> exclusiveBool.mapLeft(::listOf)
                    is Right -> Right(ConditionalExclusiveMinimumRule(minInt.r, exclusiveBool.r))
                }
            }
        }
    }
}

data class ConditionalExclusiveMinimumRule(val minimum: Number, val exclusive: Boolean): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asNumber().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(number: Number): List<Error> {
        return if (exclusive && number.toDouble() <= minimum.toDouble()) listOf(Error("$number is equal or smaller than $minimum"))
        else if (number.toDouble() >= minimum.toDouble()) emptyList()
        else listOf(Error("$number is smaller than $minimum"))
    }
}
