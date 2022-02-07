package org.validator.rules.string

import org.validator.*

interface StringLengthRule : ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        val string = getString(element)
        return if (string != null) { eval(string)
        } else emptyList()
    }

    fun eval(string: String): List<Error>

    fun getString(element: JsonElement): String? {
        return element.asScalar()
            .mapEither({null}) { scalar -> scalar.string() }
            .fold({null}) { x -> x }
    }
}

interface StringLengthRuleParser : RuleParser {

    val key: String

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val constElement = element.get(key) !!
        return constElement.asScalar().mapEither(::listOf) { scalar ->
            scalar.double().map { it.toInt() }.mapEither(::listOf, ::parse)
        }
    }

    fun parse(amount: Int): Either<List<Error>, ValidationRule>
}
