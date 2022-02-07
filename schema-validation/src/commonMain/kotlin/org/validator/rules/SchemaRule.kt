package org.validator.rules

import org.validator.*
import org.validator.rules.any.*

object DraftParsers {
    val DRAFT_4_ROOT = SchemaRuleParser(DRAFT_4_RULES(Draft4ParserFactory) + DefinitionsRuleParser(Draft4ParserFactory))
    val DRAFT_4 = SchemaRuleParser(DRAFT_4_RULES(Draft4ParserFactory))
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
    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val applicableParsers = parsers.filter { it.canParse(element) }
        return applicableParsers.map { x -> x.parse(element) }
            .toFlatEither()
            .map { SchemaRule(it) }
    }
}


data class SchemaRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return rules.flatMap { x -> x.eval(element) }
    }
}
