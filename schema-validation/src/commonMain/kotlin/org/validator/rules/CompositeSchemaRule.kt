package org.validator.rules

import org.validator.*
import org.validator.Either.*

object DraftParsers {
    val DRAFT_4 = Draft4SchemaParser(DRAFT_4_RULES(Draft4ParserFactory))
    val DRAFT_6 = Draft6SchemaParser(DRAFT_6_RULES(Draft6ParserFactory))
    val DRAFT_7 = Draft6SchemaParser(DRAFT_7_RULES(Draft7ParserFactory))
    val DRAFT_2019 = Draft2019SchemaParser(DRAFT_2019_RULES(Draft2019ParserFactory))
}

interface SchemaRuleParserFactory {
    fun make(): SchemaParser
}

object Draft4ParserFactory : SchemaRuleParserFactory {
    override fun make(): SchemaParser {
        return Draft4SchemaParser(DRAFT_4_RULES(this))
    }
}

object Draft6ParserFactory : SchemaRuleParserFactory {
    override fun make(): SchemaParser {
        return Draft6SchemaParser(DRAFT_6_RULES(this))
    }
}

object Draft7ParserFactory : SchemaRuleParserFactory {
    override fun make(): SchemaParser {
        return Draft7SchemaParser(DRAFT_7_RULES(this))
    }
}

object Draft2019ParserFactory: SchemaRuleParserFactory {
    override fun make(): SchemaParser {
        return Draft7SchemaParser(DRAFT_2019_RULES(this))
    }
}

interface SchemaParser {
    fun parse(base: String, path: String, element: JsonElement): Schema
}

interface SchemaUriResolver {
    fun nextUris(base: String, path: String, element: JsonObject): Pair<String, String>
}

object Draft4SchemaUriResolver : SchemaUriResolver {
    override fun nextUris(base: String, path: String, element: JsonObject): Pair<String, String> {
        return  when(val maybeBase = element.get("id").string()) {
            is Left -> Pair(base, path)
            is Right -> Pair(resolveUri(base, maybeBase.r), "")
        }
    }
}

object Draft6SchemaUriResolver : SchemaUriResolver {
    override fun nextUris(base: String, path: String, element: JsonObject): Pair<String, String> {
        return  when(val maybeBase = element.get("\$id").string()) {
            is Left -> Pair(base, path)
            is Right -> Pair(resolveUri(base, maybeBase.r), "")
        }
    }
}

data class Draft2019SchemaParser(val parsers: List<RuleParser>): SchemaParser {

    private val uriResolver: SchemaUriResolver = Draft6SchemaUriResolver

    override fun parse(base: String, path: String, element: JsonElement): Schema {
        return when(element) {
            is JsonBoolean -> parseBooleanSchema(base, path, element)
            is JsonObject -> parseObjectSchema(base, path, element)
            else -> Schema(base, path, Error("Schema is neither a JSON object or boolean"))
        }
    }

    private fun parseBooleanSchema(base: String, path: String, element: JsonBoolean): Schema {
        val rule = BooleanSchemaRule(element.value)
        return Schema(base, path, rule)
    }

    private fun parseObjectSchema(base: String, path: String, element: JsonObject): Schema {
        return element.asObject().fold(::Schema) { jsonObject ->
            val (nextBase, nextPath) = uriResolver.nextUris(base, path, jsonObject)
            val applicableParsers = parsers.filter { it.canParse(jsonObject) }
            val parsed = applicableParsers.map { it.parse(nextBase, path.ifEmpty { "#" }, jsonObject) }
            if (nextPath.isEmpty()) parsed.combineOnlyBase(nextBase, ::CompositeSchemaRule)
            else parsed.combine(nextBase, nextPath, ::CompositeSchemaRule)
        }
    }
}

data class Draft7SchemaParser(val parsers: List<RuleParser>): SchemaParser {
    override fun parse(base: String, path: String, element: JsonElement): Schema {
        return Draft6SchemaParser(parsers).parse(base, path, element)
    }
}

data class Draft4SchemaParser(val parsers: List<RuleParser>): SchemaParser {
    override fun parse(base: String, path: String, element: JsonElement): Schema {
        return CommonSchemaParser(parsers, Draft4SchemaUriResolver).parse(base, path, element)
    }
}

data class CommonSchemaParser(val parsers: List<RuleParser>, val uriResolver: SchemaUriResolver): SchemaParser {

    override fun parse(base: String, path: String, element: JsonElement): Schema {
        return element.asObject().fold(::Schema) { jsonObject ->
            val (nextBase, nextPath) = uriResolver.nextUris(base, path, jsonObject)
            if (jsonObject.containsKey("\$ref")) ReferenceRuleParser.parse(base, path, jsonObject)
            else {
                val applicableParsers = parsers.filter { it.canParse(jsonObject) }
                val parsed = applicableParsers.map { it.parse(nextBase, path.ifEmpty { "#" }, jsonObject) }
                if (nextPath.isEmpty()) parsed.combineOnlyBase(nextBase, ::CompositeSchemaRule)
                else parsed.combine(nextBase, nextPath, ::CompositeSchemaRule)
            }
        }
    }
}

data class Draft6SchemaParser(val parsers: List<RuleParser>): SchemaParser {

    override fun parse(base: String, path: String, element: JsonElement): Schema {
        return when(element) {
            is JsonBoolean -> parseBooleanSchema(base, path, element)
            is JsonObject -> parseObjectSchema(base, path, element)
            else -> Schema(base, path, Error("Schema is neither a JSON object or boolean"))
        }
    }

    private fun parseBooleanSchema(base: String, path: String, element: JsonBoolean): Schema {
        val rule = BooleanSchemaRule(element.value)
        return Schema(base, path, rule)
    }

    private fun parseObjectSchema(base: String, path: String, element: JsonObject): Schema {
        return CommonSchemaParser(parsers, Draft6SchemaUriResolver).parse(base, path, element)
    }
}

data class CompositeSchemaRule(val rules: List<SchemaRule>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return rules.flatMap { x -> x.eval(path, element, schema) }
    }
}

data class BooleanSchemaRule(val value: Boolean): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return if (!value) listOf(RuleError(path, "Nothing is valid for a boolean schema with value false"))
        else emptyList()
    }
}
