package org.validator.rules.`object`

import org.validator.*

object RequiredRuleParser : RuleParser {

    private const val KEY = "required"
    private val INVALID_VALUE_ERROR = Error("Some elements in the array are not strings")
    private val NOT_ARRAY_ERROR = Error("'required' key is not an array")

    override fun canParse(element: JsonObject): Boolean = element.get(KEY) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
       val finalPath = objectKey(path, KEY)
       return  when(val elem = element.get(KEY)) {
            is JsonArray -> {
                val requiredKeys = elem.elements().mapNotNull { x -> x.string().right() }
                if (requiredKeys.size != elem.elements().size) Schema(base, finalPath, INVALID_VALUE_ERROR)
                else Schema(base, finalPath, RequiredRule(requiredKeys))
            }
            else -> Schema(base, finalPath, NOT_ARRAY_ERROR)
        }
    }
}

data class RequiredRule(val members: List<String>) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asObject().fold({ emptyList() }) { x ->
            val differentMembers = members subtract x.keys()
            if (differentMembers.isEmpty()) emptyList()
            else createErrors(path, members)
        }
    }

    private fun createErrors(path: String, keys: List<String>): List<RuleError> {
        return keys.map { member -> RuleError(path, "$member is required but not present in object") }
    }
}
