package org.validator.rules.array

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class BooleanAdditionalItemsRule(val itemsKeyExists: Boolean, val amountOfItems: Int, val allowAdditional: Boolean): SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array().map { eval(path, it) }.rightOrDefault(emptyList())
    }

    private fun eval(path: String, array: JsonArray): List<RuleError> {
        return if(!itemsKeyExists) emptyList()
            else if (itemsIsSchema()) emptyList()
            else if (!allowAdditional && array.elements().size > amountOfItems) listOf(error(path, array))
            else emptyList()
    }

    private fun itemsIsSchema() = amountOfItems == 0

    private fun error(path: String, array: JsonArray): RuleError = RuleError(path, "Array has ${array.elements().size - amountOfItems} more items")
}

data class ObjectAdditionalItemsRule(val itemsKeyExists: Boolean, val amountOfItems: Int, val rule: SchemaRule): SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array().map { eval(path, it, schema) }.rightOrDefault(emptyList())
    }

    private fun eval(path: String, array: JsonArray, schema: Schema): List<RuleError> {
        return if(!itemsKeyExists) emptyList()
        else if (array.elements().size > amountOfItems) {
            val startIndex = array.elements().size - amountOfItems
            val elementsToTest = array.elements().subList(startIndex, array.elements().size)
            elementsToTest.flatMapIndexed { index, elem -> rule.eval(arrayIndex(path, index + startIndex), elem, schema) }
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

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        val itemsProp = element.get(ITEMS_KEY)
        val itemsArraySize = itemsProp.array().fold(0) { it.elements().size }
        val itemsPropExists = itemsProp != null
        return when(val entry = element.get(KEY)) {
            is JsonBoolean -> Schema(base, finalPath, BooleanAdditionalItemsRule(itemsPropExists, itemsArraySize, entry.value))
            is JsonObject -> factory.make()
                .parse(base, path, entry)
                .map(base, finalPath) { ObjectAdditionalItemsRule(itemsPropExists, itemsArraySize, it) }
            else -> Schema(base, finalPath, VALUE_ERROR)
        }
    }
}
