package org.validator.rules.boolean

import org.validator.*

abstract class OfRuleParser(private val key: String, private val factory: (List<SchemaRule>) -> SchemaRule, private val parser: (String, String, JsonElement) -> Schema): RuleParser {

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, key)
        return element.get(key).array().fold({ error -> Schema(base, finalPath, error)}) { array ->
            val schemas = array.elements().map { element -> parser(base, path, element) }
            schemas.combine(base, finalPath, factory)
        }
    }
}
