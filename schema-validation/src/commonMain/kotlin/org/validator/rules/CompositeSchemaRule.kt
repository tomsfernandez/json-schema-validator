package org.validator.rules

import org.validator.*
import org.validator.Either.*

object DraftParsers {
    val DRAFT_4 = SchemaRuleParser(DRAFT_4_RULES(Draft4ParserFactory))
    val DRAFT_4_ROOT = SchemaRuleParser(DRAFT_4_RULES(Draft4ParserFactory) + DefinitionsRuleParser(Draft4ParserFactory))
}

interface SchemaRuleParserFactory {
    fun make(): SchemaRuleParser
}

object Draft4ParserFactory : SchemaRuleParserFactory {
    override fun make(): SchemaRuleParser {
        return SchemaRuleParser(DRAFT_4_RULES(this))
    }
}

data class SchemaRuleParser(val parsers: List<RuleParser>): RuleParser {
    override fun canParse(element: JsonObject): Boolean = true
    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val (nextBase, nextPath) = when(val maybeBase = element.get("id").string()) {
            is Left -> Pair(base, path)
            is Right -> Pair(resolveUri(base, maybeBase.r), "")
        }
        val applicableParsers = parsers.filter { it.canParse(element) }
        val parsed = applicableParsers.map { it.parse(nextBase, path.ifEmpty { "#" }, element) }
        return if (nextPath.isEmpty()) parsed.combineOnlyBase(nextBase, ::CompositeSchemaRule)
        else parsed.combine(nextBase, nextPath, ::CompositeSchemaRule)
    }
}


data class CompositeSchemaRule(val rules: List<SchemaRule>): SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return rules.flatMap { x -> x.eval(path, element, schema) }
    }
}
