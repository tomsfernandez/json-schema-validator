package org.validator.rules

import org.validator.*
import org.validator.rules.`object`.MaxPropertiesRuleParser
import org.validator.rules.`object`.MinPropertiesRuleParser
import org.validator.rules.`object`.RequiredRuleParser
import org.validator.rules.any.ConstRuleParser
import org.validator.rules.any.EnumRuleParser
import org.validator.rules.any.TypeRuleParser
import org.validator.rules.boolean.AllOfRuleParser
import org.validator.rules.boolean.AnyOfRuleParser
import org.validator.rules.boolean.NotRuleParser
import org.validator.rules.boolean.OneOfRuleParser
import org.validator.rules.conditional.IfThenElseRuleParser
import org.validator.rules.numeric.*
import org.validator.rules.string.MaxLengthRuleParser
import org.validator.rules.string.MinLengthRuleParser

object DraftParsers {
    val DRAFT_4 = SchemaRuleParser(listOf(
        ConstRuleParser,
        EnumRuleParser,
        TypeRuleParser,
        AllOfRuleParser(Draft4ParserFactory),
        AnyOfRuleParser(Draft4ParserFactory),
        NotRuleParser(Draft4ParserFactory),
        OneOfRuleParser(Draft4ParserFactory),
        IfThenElseRuleParser(Draft4ParserFactory),
        ExclusiveMaximumRuleParser,
        ExclusiveMinimumRuleParser,
        MinimumRuleParser,
        MaximumRuleParser,
        RequiredRuleParser,
        MultipleOfRuleParser,
        MinLengthRuleParser,
        MaxLengthRuleParser,
        MaxPropertiesRuleParser,
        MinPropertiesRuleParser
        ))
}

interface SchemaRuleParserFactory {
    fun make(): SchemaRuleParser
}

object Draft4ParserFactory : SchemaRuleParserFactory {
    override fun make(): SchemaRuleParser {
        return DraftParsers.DRAFT_4
    }
}

data class SchemaRuleParser(val parsers: List<RuleParser>): RuleParser {
    override fun canParse(element: JsonObject): Boolean = true
    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val applicableParsers = parsers.filter { it.canParse(element) }
        val eitherList = applicableParsers.map { x -> x.parse(element) }
        val errors = eitherList.mapNotNull { x -> x.toLeftValueOrNull() }.flatten()
        val schemas = eitherList.mapNotNull { x -> x.toRightValueOrNull() }
        return if (errors.isNotEmpty()) Either.Left(errors)
            else Either.Right(SchemaRule(schemas))
    }
}


data class SchemaRule(val rules: List<ValidationRule>): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return rules.flatMap { x -> x.eval(element) }
    }
}
