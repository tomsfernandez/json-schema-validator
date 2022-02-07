package org.validator.rules.array

import org.validator.*

data class MaxItemsRule(val maximum: Int) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.array().map { assertSize(it) }.rightOrDefault(emptyList())
    }

    private fun assertSize(array: JsonArray): List<Error> {
        return if (array.elements().size > maximum) listOf(Error("Array must have less than $maximum elements"))
        else emptyList()
    }
}

object MaxItemsRuleParser: RuleParser {

    private val KEY = "maxItems"
    private val FORMAT_ERROR = Error("maxItems must be an integer")

    override fun canParse(element: JsonObject): Boolean = element.get(KEY) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(KEY).integer().map({ listOf(FORMAT_ERROR) }) { int -> MaxItemsRule(int) }
    }
}
