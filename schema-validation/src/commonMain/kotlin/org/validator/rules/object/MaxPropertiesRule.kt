package org.validator.rules.`object`

import org.validator.*

abstract class PropertyAmountParser : RuleParser {

    protected abstract val KEY: String

    override fun canParse(element: JsonObject) = element.get(KEY) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(KEY).asScalar().mapEither(::listOf) { scalar ->
            scalar.double().mapEither(::listOf) { number ->
                if (number.toInt() < 0) Either.Left(listOf(Error("maxProperties should have a non-negative value")))
                else parse(number)
            }
        }
    }

    protected abstract fun parse(number: Number): Either<List<Error>, ValidationRule>
}

object MaxPropertiesRuleParser: PropertyAmountParser() {

    override val KEY = "maxProperties"

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return if (number.toInt() < 0) Either.Left(listOf(Error("maxProperties should have a non-negative value")))
        else Either.Right(MaxPropertiesRule(number.toInt()))
    }
}

data class MaxPropertiesRule(val maximum: Int): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.asObject().fold({ emptyList() }) { x ->
            if (x.keys().size <= maximum) emptyList()
            else listOf(Error("Object has ${x.keys().size} but maximum is $maximum"))
        }
    }
}
