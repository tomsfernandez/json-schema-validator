package org.validator.rules.array

import org.validator.*
import org.validator.Either.*
import org.validator.rules.SchemaRuleParserFactory

data class SingleItemsRule(val rule: ValidationRule): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.array()
            .map { array -> array.elements().flatMap { elem -> rule.eval(elem) } }
            .rightOrDefault(emptyList())
    }
}

data class TupleRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.array()
            .map { array -> array.elements().zip(rules).flatMap { pair -> pair.second.eval(pair.first) } }
            .rightOrDefault(emptyList())
    }
}

data class ItemsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val KEY = "items"
    private val FORMAT_ERROR = Error("'items' is neither an object or an array")

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when(val entry = element.get(KEY)) {
            is JsonObject -> parseSchema(entry)
            is JsonArray -> parseTuple(entry)
            else -> Left(listOf(FORMAT_ERROR))
        }
    }

    private fun parseSchema(obj: JsonObject): Either<List<Error>, ValidationRule> {
        return factory.make().parse(obj).map { rule -> SingleItemsRule(rule) }
    }

    private fun parseTuple(array: JsonArray): Either<List<Error>, ValidationRule> {
        val schemaParser = factory.make()
        return array.elements().map { it.asObject() }
            .toEither()
            .withRight { objs -> objs.map { obj -> schemaParser.parse(obj) }.toFlatEither() }
            .map { schemas -> TupleRule(schemas) }
    }
}
