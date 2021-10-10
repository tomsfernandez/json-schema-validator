package org.validator.rules.any

import org.validator.*

object EnumRuleParser: RuleParser {
    override fun canParse(element: JsonObject): Boolean {
        return element.get("enum") != null
    }

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get("enum").asArray().mapEither(::listOf) { array -> Either.Right(EnumRule(array.elements())) }
    }
}

data class EnumRule(val toCompare: List<JsonElement>) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        val equalsAny = toCompare.any { x -> x.deepEquals(element) }
        return if (!equalsAny) listOf(Error("Schema doesn't equal any value in enum"))
        else emptyList()
    }
}
