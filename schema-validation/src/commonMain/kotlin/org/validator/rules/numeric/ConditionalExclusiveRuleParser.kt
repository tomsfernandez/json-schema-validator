package org.validator.rules.numeric

import org.validator.*

interface ConditionalExclusiveRuleParser : RuleParser {
    val KEY: String
    val CONDITIONAL_KEY: String

    override fun canParse(element: JsonObject): Boolean = element.keys().any { KEY == it || CONDITIONAL_KEY == it }

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        val conditional = element.get(KEY)
        val exclusive = element.get(CONDITIONAL_KEY)
        val exclusiveExists = exclusive != null

        if (conditional == null && exclusiveExists) return Schema(base, finalPath, ONLY_CONDITIONAL_ERROR())

        return conditional.double().fold({ error -> Schema(base, finalPath, error)}) { maxInt ->
            if (!exclusiveExists) Schema(base, finalPath, rule(maxInt, false))
            else exclusive.boolean().fold({ error -> Schema(base, finalPath, error)}) { exclusiveBool ->
                Schema(base, finalPath, rule(maxInt, exclusiveBool))
            }
        }
    }

    fun rule(integer: Double, exclusive: Boolean): SchemaRule

    private fun ONLY_CONDITIONAL_ERROR() = Error("$KEY key must exist to use $CONDITIONAL_KEY")
}
