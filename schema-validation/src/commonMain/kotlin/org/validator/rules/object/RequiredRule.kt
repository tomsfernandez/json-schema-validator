package org.validator.rules.`object`

import org.validator.*

object RequiredRuleParser : RuleParser {

    private val key = "required"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
       return  when(val elem = element.get(key)) {
            is JsonArray -> {
                val requiredKeys = elem.elements().mapNotNull { x -> x.asScalar().toRightValueOrNull() }.mapNotNull { scalar ->
                    scalar.asString().toRightValueOrNull()
                }
                if (requiredKeys.size != elem.elements().size) Either.Left(listOf(Error("Some elements in the array are not strings")))
                else Either.Right(RequiredRule(requiredKeys))
            }
            else -> Either.Left(listOf(Error("'required' key is not an array")))
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
