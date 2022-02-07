package org.validator.rules.array

import org.validator.*

data class UniqueItemsRule(val shouldBeUnique: Boolean): ValidationRule {

    private val ERROR = Error("Some elements in the array are not unique")

    override fun eval(element: JsonElement): List<Error> {
        if (!shouldBeUnique) return emptyList()
        return element.array().map { values ->
            if (amountOfUniqueElementsIsTheSameAsArraySize(values)) emptyList()
            else listOf(ERROR)
        }.rightOrDefault(emptyList())
    }

    private fun amountOfUniqueElementsIsTheSameAsArraySize(values: JsonArray) =
        values.elements().distinct().size == values.elements().size
}

object UniqueItemsRuleParser: RuleParser {

    private val KEY = "uniqueItems"

    override fun canParse(element: JsonObject): Boolean =  element.containsKey(KEY)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(KEY).boolean().map(::listOf) { value -> UniqueItemsRule(value) }
    }
}
