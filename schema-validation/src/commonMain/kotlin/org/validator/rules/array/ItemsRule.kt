package org.validator.rules.array

import org.validator.*
import org.validator.rules.SchemaRule
import org.validator.rules.SchemaRuleParserFactory

data class SingleItemsRule(val rule: ValidationRule): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.asArray()
            .map { array -> array.elements().flatMap { elem -> rule.eval(elem) } }
            .rightOrDefault(emptyList())
    }
}

data class TupleRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asArray()
            .map { array -> array.elements().zip(rules).flatMap { pair -> pair.second.eval(pair.first) } }
            .rightOrDefault(emptyList())
    }
}

data class ItemsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val key = "items"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(key)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when(val entry = element.get(key)) {
            is JsonObject -> parseSingleItems(entry)
            is JsonArray -> parseTuple(entry)
            else -> Either.Left(listOf(Error("'items' is neither an object or an array")))
        }
    }

    private fun parseSingleItems(obj: JsonObject): Either<List<Error>, ValidationRule> {
        return factory.make().parse(obj).map { rule -> SingleItemsRule(rule) }
    }

    private fun parseTuple(array: JsonArray): Either<List<Error>, ValidationRule> {
        val schemaParser = factory.make()
        val (errors, objs) = array.elements().map { it.asObject() }.partitionList()
        if (errors.any()) return Either.Left(errors)

        val (parseErrors, schemas) = objs.map { obj -> schemaParser.parse(obj) }.partitionList()
        if (parseErrors.any()) return Either.Left(parseErrors.flatten())

        return Either.Right(TupleRule(schemas))
    }
}
