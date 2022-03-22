package org.validator.rules

import org.validator.*


object ReferenceRuleParser: RuleParser {

    private val KEY = "\$ref"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        return element.get(KEY).string().fold(::Schema) { ref ->
            val decoded = refDecodeUri(ref)
            Schema(base, path, ReferenceRule(base, decoded))
        }
    }

    private fun refDecodeUri(ref: String): String {
        return ref.replace("~0", "~").replace("~1", "/")
    }
}

data class ReferenceRule(val base: String, val ref: String): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        val finalRef = enforceFragment(resolveUri(base, ref))
        val maybeSchema = schema.seen[finalRef]
        return maybeSchema?.eval(path, element, schema) ?: listOf(RuleError(path, "Reference: couldn't find $finalRef"))
    }
}
