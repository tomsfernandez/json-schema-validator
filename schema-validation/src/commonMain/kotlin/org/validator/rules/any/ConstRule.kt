package org.validator.rules.any

import org.validator.*

object ConstRuleParser: RuleParser {

    override fun canParse(element: JsonObject): Boolean {
        return element.get("const") != null
    }

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val constElement = element.get("const") !!
        return Either.Right(ConstRule(constElement))
    }
}

data class ConstRule(val toCompare: JsonElement) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return if (!toCompare.deepEquals(element)) listOf(Error("Schemas are not equal"))
        else emptyList()
    }
}
