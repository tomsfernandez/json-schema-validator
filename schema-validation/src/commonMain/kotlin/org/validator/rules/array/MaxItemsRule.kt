package org.validator.rules.array

import org.validator.*

data class MaxItemsRule(val maximum: Int) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asArray().map { assertSize(it) }.rightOrDefault(emptyList())
    }

    private fun assertSize(array: JsonArray): List<Error> {
        return if (array.elements().size > maximum) listOf(Error("Array must have less than $maximum elements"))
        else emptyList()
    }
}

object MaxItemsRuleParser: RuleParser {

    private val key = "maxItems"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when(val entry = element.get(key)) {
            is IntJsonScalar -> Either.Right(MaxItemsRule(entry.value))
            else -> Either.Left(listOf(Error("maxItems must be an integer")))
        }
    }
}
