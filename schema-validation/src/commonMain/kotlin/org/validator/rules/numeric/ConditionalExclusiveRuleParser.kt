package org.validator.rules.numeric

import org.validator.*

interface ConditionalExclusiveRuleParser : RuleParser {
    val KEY: String
    val CONDITIONAL_KEY: String

    override fun canParse(element: JsonObject): Boolean = element.keys().any { KEY == it || CONDITIONAL_KEY == it }

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val conditional = element.get(KEY)
        val exclusive = element.get(CONDITIONAL_KEY)

        if (conditional == null && exclusive != null) return Either.Left(listOf(ONLY_CONDITIONAL_ERROR()))

        return conditional.double().mapLeft(::listOf).withRight { maxInt ->
            if (exclusive == null) Either.Right(rule(maxInt, false))
            else exclusive.boolean().map(::listOf) { exclusiveBool ->
                rule(maxInt, exclusiveBool)
            }
        }
    }

    fun rule(integer: Double, exclusive: Boolean): ValidationRule

    private fun ONLY_CONDITIONAL_ERROR() = Error("$KEY key must exist to use $CONDITIONAL_KEY")
}
