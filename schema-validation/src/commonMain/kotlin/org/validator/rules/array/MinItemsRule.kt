package org.validator.rules.array

import org.validator.*

data class MinItemsRule(val minimum: Int) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asArray().map { assertSize(it) }.rightOrDefault(emptyList())
    }

    private fun assertSize(array: JsonArray): List<Error> {
        return if (array.elements().size < minimum) listOf(Error("Array must have more than $minimum elements"))
        else emptyList()
    }
}

object MinItemsRuleParser: RuleParser {

    private val key = "minItems"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when(val entry = element.get(key)) {
            is IntJsonScalar -> Either.Right(MinItemsRule(entry.value))
            else -> Either.Left(listOf(Error("minItems must be an integer")))
        }
    }
}
