package org.validator.rules.array

import org.validator.*
import org.validator.rules.SchemaRule
import org.validator.rules.SchemaRuleParserFactory

data class BooleanAdditionalItemsRule(val itemsKeyExists: Boolean, val itemsIsObject: Boolean, val amountOfItems: Int, val allowAdditional: Boolean): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.asArray().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(array: JsonArray): List<Error> {
        return if(itemsIsObject || !itemsKeyExists) emptyList()
            else if (!allowAdditional && array.elements().size > amountOfItems) listOf(Error("Array has ${array.elements().size > amountOfItems} more items"))
            else emptyList()
    }
}

data class ObjectAdditionalItemsRule(val itemsKeyExists: Boolean, val itemsIsObject: Boolean, val amountOfItems: Int, val rule: ValidationRule): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.asArray().map { eval(it) }.rightOrDefault(emptyList())
    }

    private fun eval(array: JsonArray): List<Error> {
        return if(itemsIsObject || !itemsKeyExists) emptyList()
        else if (array.elements().size > amountOfItems) {
            val startIndex = array.elements().size - amountOfItems
            val elementsToTest = array.elements().subList(startIndex, array.elements().size)
            elementsToTest.flatMap { elem -> rule.eval(elem) }
        }
        else emptyList()
    }
}

data class AdditionalItemsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val key = "additionalItems"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(key)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val itemsProp = element.get("items")
        val itemsIsObject = itemsProp is JsonObject
        val itemsArraySize = if (itemsProp is JsonArray) itemsProp.elements().size else 0
        return when(val entry = element.get(key)) {
            is BooleanJsonScalar -> Either.Right(BooleanAdditionalItemsRule(itemsProp != null, itemsIsObject, itemsArraySize, entry.value))
            is JsonObject -> {
                factory.make().parse(entry).map { ObjectAdditionalItemsRule(itemsProp != null, itemsIsObject, itemsArraySize, it) }
            }
            else -> Either.Left(listOf(Error("'additionalItems' key must be boolean or object")))
        }
    }
}
