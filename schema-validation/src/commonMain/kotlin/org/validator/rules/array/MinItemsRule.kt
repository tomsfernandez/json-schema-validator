package org.validator.rules.array

import org.validator.*
import org.validator.Either.*

data class MinItemsRule(val minimum: Int) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.array().map { assertSize(it) }.rightOrDefault(emptyList())
    }

    private fun assertSize(array: JsonArray): List<Error> {
        return if (array.elements().size < minimum) listOf(Error("Array must have more than $minimum elements"))
        else emptyList()
    }
}

object MinItemsRuleParser: RuleParser {

    private val KEY = "minItems"
    private val FORMAT_ERROR = Error("minItems must be an integer")

    override fun canParse(element: JsonObject): Boolean = element.get(KEY) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(KEY).integer().map({ listOf(FORMAT_ERROR) }) { int -> MinItemsRule(int) }
    }
}
