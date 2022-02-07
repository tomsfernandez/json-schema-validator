package org.validator.rules.any

import org.validator.*

object ConstRuleParser: RuleParser {

    private const val KEY = "const"

    override fun canParse(element: JsonObject): Boolean {
        return element.get(KEY) != null
    }

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val constElement = element.get(KEY) !!
        return Either.Right(ConstRule(constElement))
    }
}

data class ConstRule(val toCompare: JsonElement) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return if (!toCompare.deepEquals(element)) listOf(Error("Schemas are not equal"))
        else emptyList()
    }
}
