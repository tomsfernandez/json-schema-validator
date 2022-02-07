package org.validator.rules.array

import org.validator.*
import org.validator.Either.*
import org.validator.rules.SchemaRuleParserFactory

data class BooleanAdditionalItemsRule(val itemsKeyExists: Boolean, val amountOfItems: Int, val allowAdditional: Boolean): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.array().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(array: JsonArray): List<Error> {
        return if(!itemsKeyExists) emptyList()
            else if (itemsIsSchema()) emptyList()
            else if (!allowAdditional && array.elements().size > amountOfItems) listOf(error(array))
            else emptyList()
    }

    private fun itemsIsSchema() = amountOfItems == 0

    private fun error(array: JsonArray): Error = Error("Array has ${array.elements().size - amountOfItems} more items")
}

data class ObjectAdditionalItemsRule(val itemsKeyExists: Boolean, val amountOfItems: Int, val rule: ValidationRule): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.array().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(array: JsonArray): List<Error> {
        return if(!itemsKeyExists) emptyList()
        else if (array.elements().size > amountOfItems) {
            val startIndex = array.elements().size - amountOfItems
            val elementsToTest = array.elements().subList(startIndex, array.elements().size)
            elementsToTest.flatMap { elem -> rule.eval(elem) }
        }
        else emptyList()
    }
}

data class AdditionalItemsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val KEY = "additionalItems"
    private val ITEMS_KEY = "items"

    companion object {
        private val VALUE_ERROR = Error("'additionalItems' key must be boolean or object")
    }

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val itemsProp = element.get(ITEMS_KEY)
        val itemsArraySize = itemsProp.array().fold(0) { it.elements().size }
        val itemsPropExists = itemsProp != null
        return when(val entry = element.get(KEY)) {
            is JsonBoolean -> Right(BooleanAdditionalItemsRule(itemsPropExists, itemsArraySize, entry.value))
            is JsonObject -> factory.make()
                .parse(entry)
                .map { ObjectAdditionalItemsRule(itemsPropExists, itemsArraySize, it) }
            else -> Left(listOf(VALUE_ERROR))
        }
    }
}
