package org.validator.rules.array

import org.validator.*

data class UniqueItemsRule(val shouldBeUnique: Boolean): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        if (!shouldBeUnique) return emptyList()
        return element.asArray().map { values ->
            if (values.elements().distinct().size == values.elements().size) emptyList()
            else listOf(Error("Some elements in the array are not unique"))
        }.rightOrDefault(emptyList())
    }
}

object UniqueItemsRuleParser: RuleParser {

    private val key = "uniqueItems"

    override fun canParse(element: JsonObject): Boolean =  element.containsKey(key)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(key).asBoolean().map(::listOf) { value -> UniqueItemsRule(value) }
    }
}
