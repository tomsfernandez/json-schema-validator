package org.validator.rules.any

import org.validator.*
import org.validator.Either.*

object EnumRuleParser: RuleParser {

    private const val KEY = "enum"

    override fun canParse(element: JsonObject): Boolean {
        return element.get(KEY) != null
    }

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(KEY).array().mapEither(::listOf) { array -> Right(EnumRule(array.elements())) }
    }
}

data class EnumRule(val toCompare: List<JsonElement>) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        val equalsAny = toCompare.any { x -> x.deepEquals(element) }
        return if (!equalsAny) listOf(Error("Schema doesn't equal any value in enum"))
        else emptyList()
    }
}
