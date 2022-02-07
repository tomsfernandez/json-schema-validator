package org.validator.rules.any

import org.validator.*
import org.validator.Either.*

object TypeRuleParser: RuleParser {

    private const val ARRAY_TYPE = "array"
    private const val OBJECT_TYPE = "object"
    private const val NULL_TYPE = "null"
    private const val STRING_TYPE = "string"
    private const val NUMBER_TYPE = "number"
    private const val BOOLEAN_TYPE = "boolean"
    private const val INTEGER_TYPE = "integer"
    private const val KEY = "type"


    private val ruleMap = mapOf(
        ARRAY_TYPE to ArrayRule,
        OBJECT_TYPE to ObjectRule,
        NULL_TYPE to NullRule,
        STRING_TYPE to StringRule,
        NUMBER_TYPE to NumberRule,
        BOOLEAN_TYPE to BooleanRule,
        INTEGER_TYPE to IntegerRule
    )

    override fun canParse(element: JsonObject): Boolean {
        return element.get(KEY) != null
    }

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return when (val typeEntry = element.get(KEY)) {
            is JsonArray -> parseTypeList(typeEntry)
            is JsonScalar -> parseType(typeEntry).mapEither(::listOf) { x -> Right(x) }
            else -> Left(listOf(Error("Element is neither an array or a scalar")))
        }
    }

    private fun parseTypeList(array: JsonArray): Either<List<Error>, ValidationRule> {
        val schemaOrErrors: List<Either<Error, ValidationRule>> = array.elements().map { x -> this.parseType(x) }
        val errors = schemaOrErrors.mapNotNull { x -> x.left() }
        return if (errors.isEmpty()) {
            val rules = OrRule(schemaOrErrors.mapNotNull { it.right() })
            Right(rules)
        } else Left(errors)
    }

    private fun parseType(element: JsonElement): Either<Error, ValidationRule> {
        return element.string().mapEither(::identity) { x -> parseType(x) }
    }

    private fun parseType(type: String): Either<Error, ValidationRule> {
        val value = ruleMap[type]
        return if (value != null) Right(value)
        else Left(Error("$type is not an allowed value"))
    }
}

object ObjectRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asObject().fold({ listOf(it)}) { emptyList() }
    }
}

object ArrayRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.array().fold({ listOf(it)}) { emptyList() }
    }
}

object StringRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.string().fold({ listOf(it)} ) { emptyList() }
    }
}

object BooleanRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.boolean().fold({ listOf(it)} ) { emptyList() }
    }
}

object NumberRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.double().fold({ listOf(it)} ) { emptyList() }
    }
}

object NullRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asNull().fold({ listOf(it)} ) { emptyList() }
    }
}

object IntegerRule : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.integer().fold({ listOf(it)} ) { emptyList() }
    }
}
