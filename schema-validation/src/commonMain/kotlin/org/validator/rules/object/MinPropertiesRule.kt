package org.validator.rules.`object`

import org.validator.*
import org.validator.Either.*

object MinPropertiesRuleParser: PropertyAmountParser() {

    override val KEY = "minProperties"
    private val ERROR = Error("minProperties should have a non-negative value")

    override fun parse(number: Number): Either<List<Error>, ValidationRule> {
        return if (number.toInt() < 0) Left(listOf(ERROR))
        else Right(MinPropertiesRule(number.toInt()))
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
