package org.validator.rules.numeric

import org.validator.*

interface NumberRule : ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        val number = getNumber(element)
        return if (number != null) eval(number)
        else emptyList()
    }

    fun eval(number: Number): List<Error>

    fun getNumber(element: JsonElement): Double? {
        return element.double().fold({null}) { it }
    }
}

interface NumberRuleParser : RuleParser {

    val KEY: String

    override fun canParse(element: JsonObject) = element.containsKey(KEY)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(KEY).double().mapEither(::listOf, ::parse)
    }

    fun parse(number: Number): Either<List<Error>, ValidationRule>
}
