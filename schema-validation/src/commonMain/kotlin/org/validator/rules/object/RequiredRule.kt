package org.validator.rules.`object`

import org.validator.*
import org.validator.Either.*

object RequiredRuleParser : RuleParser {

    private const val KEY = "required"
    private val INVALID_VALUE_ERROR = Error("Some elements in the array are not strings")
    private val NOT_ARRAY_ERROR = Error("'required' key is not an array")

    override fun canParse(element: JsonObject): Boolean = element.get(KEY) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
       return  when(val elem = element.get(KEY)) {
            is JsonArray -> {
                val requiredKeys = elem.elements().mapNotNull { x -> x.string().right() }
                if (requiredKeys.size != elem.elements().size) Left(listOf(INVALID_VALUE_ERROR))
                else Right(RequiredRule(requiredKeys))
            }
            else -> Left(listOf(NOT_ARRAY_ERROR))
        }
    }
}

data class RequiredRule(val members: List<String>) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.asObject().fold({ emptyList() }) { x ->
            val differentMembers = members subtract x.keys()
            if (differentMembers.isEmpty()) emptyList()
            else createErrors(members)
        }
    }

    private fun createErrors(keys: List<String>): List<Error> {
        return keys.map { member -> Error("$member is required but not present in object") }
    }
}
