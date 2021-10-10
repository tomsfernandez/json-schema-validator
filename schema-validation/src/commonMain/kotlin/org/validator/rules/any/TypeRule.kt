package org.validator.rules.any

import org.validator.*

object TypeRuleParser: RuleParser {

    private val ruleMap = mapOf(
        "array" to ArrayRule,
        "object" to ObjectRule,
        "null" to NullRule,
        "string" to StringRule,
        "number" to NumberRule,
        "boolean" to BooleanRule,
        "integer" to IntegerRule
    )

    override fun canParse(element: JsonObject): Boolean {
        return element.get("type") != null
    }

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when (val typeEntry = element.get("type")) {
            is JsonArray -> parseArrayElements(typeEntry)
            is JsonScalar -> parseScalarElement(typeEntry).mapEither(::listOf) { x -> Either.Right(x) }
            else -> Either.Left(listOf(Error("Element is neither an array or a scalar")))
        }
    }

    private fun parseArrayElements(array: JsonArray): Either<List<Error>, ValidationRule> {
        val schemaOrErrors: List<Either<Error, ValidationRule>> = array.elements().map { x -> this.parseScalarElement(x) }
        val errors = schemaOrErrors.mapNotNull { x -> x.left() }
        return if (errors.isEmpty()) {
            val rules = OrValidationRule(schemaOrErrors.mapNotNull { it.right() })
            Either.Right(rules)
        } else Either.Left(errors)
    }

    private fun parseScalarElement(element: JsonElement): Either<Error, ValidationRule> {
        return element.asScalar().mapEither(::identity) { x -> parseScalarElement(x) }
    }

    private fun parseScalarElement(scalar: JsonScalar): Either<Error, ValidationRule> {
        return scalar.asString().fold({x -> Either.Left(x) }) { x ->
            val value = ruleMap[x]
            if (value != null) Either.Right(value)
            else Either.Left(Error("$x is not amount allowed values"))
        }
    }
}

object ObjectRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asObject().fold(::listOf) { emptyList() }
    }
}

object ArrayRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asArray().fold(::listOf) { emptyList() }
    }
}

object StringRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asScalar().fold(::listOf) { x -> evalString(x) }
    }

    private fun evalString(scalar: JsonScalar): List<Error> = scalar.asString().fold(::listOf) { emptyList() }
}

object BooleanRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asScalar().fold(::listOf) { x -> evalBoolean(x) }
    }

    private fun evalBoolean(scalar: JsonScalar): List<Error> = scalar.asBoolean().fold(::listOf) { emptyList() }
}

object NumberRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asScalar().fold(::listOf) { x -> evalNumber(x) }
    }

    private fun evalNumber(scalar: JsonScalar): List<Error> = scalar.asNumber().fold(::listOf) { emptyList() }
}

object NullRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asNull().fold(::listOf) { emptyList() }
    }
}

object IntegerRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asScalar().fold(::listOf) { evalInteger(it) }
    }

    private fun evalInteger(scalar: JsonScalar): List<Error> = scalar.asNumber().fold(::listOf) { num ->
        if (num.toDouble() % 1.0 == 0.0) emptyList() else listOf(Error("Value is not an integer"))
    }
}
