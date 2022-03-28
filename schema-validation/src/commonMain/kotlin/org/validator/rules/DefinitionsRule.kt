package org.validator.rules

import org.validator.*

data class Draft4DefinitionsRuleParser(private val factory: SchemaRuleParserFactory): RuleParser {

    private val inner = DefinitionsRuleParser("definitions", factory)

    override fun canParse(element: JsonObject): Boolean  = inner.canParse(element)

    override fun parse(base: String, path: String, element: JsonObject): Schema = inner.parse(base, path, element)
}


data class Draft2019DefsRuleParser(private val factory: SchemaRuleParserFactory): RuleParser {

    private val inner = DefinitionsRuleParser("\$defs", factory)

    override fun canParse(element: JsonObject): Boolean  = inner.canParse(element)

    override fun parse(base: String, path: String, element: JsonObject): Schema = inner.parse(base, path, element)
}
data class DefinitionsRuleParser(private val key: String, private val factory: SchemaRuleParserFactory) : RuleParser {

    override fun canParse(element: JsonObject): Boolean = element.containsKey(key)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val definitionsPath = objectKey(path, key)
        val definitionsObj = element.get(key).asObject()

        return definitionsObj.fold({ error -> Schema(base, definitionsPath, error) }) { obj ->
            val definitions = obj.entries().map { (key, element) -> Pair(objectKey(definitionsPath, encodeUri(key)), element) }
                .map { (path, element) -> factory.make().parse(base, path, element) }
            definitions.combine(::NothingRule)
        }
    }
}

data class NothingRule(val rules: List<SchemaRule>) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return emptyList()
    }
}
