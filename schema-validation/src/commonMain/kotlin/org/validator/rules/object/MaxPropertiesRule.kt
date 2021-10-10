package org.validator.rules.`object`

import org.validator.*

abstract class PropertyAmountParser : RuleParser {

    protected abstract val key: String

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(key).asScalar().mapEither(::listOf) { scalar ->
            scalar.asNumber().mapEither(::listOf) { number ->
                if (number.toInt() < 0) Either.Left(listOf(Error("maxProperties should have a non-negative value")))
                else parse(number)
            }
        }
    }

    protected abstract fun parse(number: Number): Either<List<Error>, ValidationRule>
}

object MaxPropertiesRuleParser: PropertyAmountParser() {

    override val key = "maxProperties"

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
