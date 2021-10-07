package org.validator.rules.`object`

import org.validator.*

object MinPropertiesRuleParser: PropertyAmountParser() {

    override val key = "minProperties"

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return if (number.toInt() < 0) Either.Left(listOf(Error("minProperties should have a non-negative value")))
        else Either.Right(MinPropertiesRule(number.toInt()))
    }
}

data class MinPropertiesRule(val minimum: Int): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.asObject().fold({ emptyList() }) { x ->
            if (x.keys().size >= minimum) emptyList()
            else listOf(Error("Object has ${x.keys().size} but minimum is $minimum"))
        }
    }
}
