package org.validator.rules.conditional

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class IfThenElseRule(val ifSchema: SchemaRule?, val thenSchema: SchemaRule?, val elseSchema: SchemaRule?): SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return if (ifSchema != null) {
            val hasErrors = ifSchema.eval(path, element, schema).isNotEmpty()
            if (hasErrors && elseSchema != null) elseSchema.eval(path, element, schema)
            else thenSchema?.eval(path, element, schema) ?: emptyList()
        } else emptyList()
    }
}

data class IfThenElseRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val IF_KEY: String = "if"
    private val ELSE_KEY: String = "else"
    private val THEN_KEY: String = "then"

    override fun canParse(element: JsonObject) = element.keys().any { IF_KEY == it || ELSE_KEY == it || THEN_KEY == it }

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val ifSchemaEither = parse(base, path, IF_KEY, element.get(IF_KEY))
        val elseSchemaEither = parse(base, path, ELSE_KEY, element.get(ELSE_KEY))
        val thenSchemaEither = parse(base, path, THEN_KEY, element.get(THEN_KEY))
        val errors = errorsOrEmpty(ifSchemaEither) + errorsOrEmpty(elseSchemaEither) + errorsOrEmpty(thenSchemaEither)
        val rule = IfThenElseRule(ifSchemaEither?.rule, thenSchemaEither?.rule, elseSchemaEither?.rule)
        return Schema(rule, errors)
    }

    private fun errorsOrEmpty(schema: Schema?): List<Error> {
        return schema?.errors ?: emptyList()
    }

    private fun parse(base: String, key: String, path: String, element: JsonElement?): Schema? {
        val finalPath = objectKey(path, key)
        return element?.asObject()?.fold({ error -> Schema(base, finalPath, error)}) { x -> factory.make().parse(base, finalPath, x) }
    }
}
