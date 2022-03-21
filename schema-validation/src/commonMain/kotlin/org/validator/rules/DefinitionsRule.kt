package org.validator.rules

import org.validator.*

data class DefinitionsRuleParser(private val factory: SchemaRuleParserFactory) : RuleParser {

    private val KEY = "definitions"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(KEY)

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val definitionsPath = objectKey(path, KEY)
        val definitionsObj = element.get(KEY).asObject()

        return definitionsObj.fold({ error -> Schema(base, definitionsPath, error) }) { obj ->
            obj.entries().map { (key, element) -> Pair(objectKey(definitionsPath, encodeUri(key)), element.asObject()) }
                .map { (path, objectOrError) ->
                    when (objectOrError) {
                        is Either.Left -> Schema(base, path, objectOrError.l)
                        is Either.Right -> factory.make().parse(base, path, objectOrError.r)
                    }
                }.combine(::NothingRule)
        }
    }
}

data class NothingRule(val rules: List<SchemaRule>) : SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return emptyList()
    }
}
