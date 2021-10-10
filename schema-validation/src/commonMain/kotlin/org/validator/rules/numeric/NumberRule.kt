package org.validator.rules.numeric

import org.validator.*

interface NumberRule : ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        val number = getNumber(element)?.toDouble()
        return if (number != null) { eval(number)
        } else emptyList()
    }

    fun eval(number: Number): List<Error>

    fun getNumber(element: JsonElement): Number? {
        return element.asScalar()
            .mapEither({null}) { scalar -> scalar.asNumber() }
            .fold({null}) { x -> x }
    }
}

interface NumberRuleParser : RuleParser {

    val key: String

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val constElement = element.get(key) !!
        return constElement.asScalar().mapEither(::listOf) { scalar ->
            scalar.asNumber().mapEither(::listOf, ::parse)
        }
    }

    fun parse(number: Number): Either<List<Error>, ValidationRule>
}
