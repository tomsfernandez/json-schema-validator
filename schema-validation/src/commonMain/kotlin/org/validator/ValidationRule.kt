package org.validator

interface ValidationRule {

    fun eval(element: JsonElement): List<Error>
}

interface RuleParser {
    fun canParse(element: JsonObject): Boolean
    fun parse(element: JsonObject): Either<List<Error>, ValidationRule>
}

object NothingValidationRule : ValidationRule {
    override fun eval(element: JsonElement) = emptyList<Error>()
}

data class OrValidationRule(val rules: List<ValidationRule>) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        val errors = rules.map { x -> x.eval(element) }
        val atLeastOneValid = errors.any { it.isEmpty() }
        return if (atLeastOneValid) emptyList() else errors.flatten()
    }
}


data class Error(val reason: String)
