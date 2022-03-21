package org.validator.rules.array

import org.validator.*
import org.validator.Either.*
import org.validator.rules.SchemaRuleParserFactory

data class SingleItemsRule(val rule: SchemaRule): SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array()
            .map { array -> array.elements().flatMapIndexed { index, elem -> rule.eval(arrayIndex(path, index), elem, schema) } }
            .rightOrDefault(emptyList())
    }
}

data class TupleRule(val rules: List<SchemaRule>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.array()
            .map { array -> array.elements().zip(rules).flatMapIndexed { index, pair -> pair.second.eval(arrayIndex(path, index), pair.first, schema) } }
            .rightOrDefault(emptyList())
    }
}

data class ItemsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val KEY = "items"
    private val FORMAT_ERROR = Error("'items' is neither an object or an array")

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return when(val entry = element.get(KEY)) {
            is JsonObject -> parseSchema(base, finalPath, entry)
            is JsonArray -> parseTuple(base, finalPath, entry)
            else -> Schema(base, finalPath, FORMAT_ERROR)
        }
    }

    private fun parseSchema(base: String, path: String, obj: JsonObject): Schema {
        return factory.make().parse(base, path, obj).map(base, path, ::SingleItemsRule)
    }

    private fun parseTuple(base: String, path: String, array: JsonArray): Schema {
        val schemaParser = factory.make()
        val schemas = array.elements().map { it.asObject() }.mapIndexed { index, elem ->
            val arrayPath = "$path/$index"
            when(elem) {
                is Left -> Schema(base, arrayPath, elem.l)
                is Right -> schemaParser.parse(base, arrayPath, elem.r)
            }
        }
        return schemas.combine(base, path, ::TupleRule)
    }
}
