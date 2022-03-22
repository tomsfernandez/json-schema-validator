package org.validator.rules

import org.validator.RuleParser
import org.validator.rules.`object`.*
import org.validator.rules.any.ConstRuleParser
import org.validator.rules.any.EnumRuleParser
import org.validator.rules.any.TypeRuleParser
import org.validator.rules.array.*
import org.validator.rules.boolean.AllOfRuleParser
import org.validator.rules.boolean.AnyOfRuleParser
import org.validator.rules.boolean.NotRuleParser
import org.validator.rules.boolean.OneOfRuleParser
import org.validator.rules.conditional.IfThenElseRuleParser
import org.validator.rules.numeric.*
import org.validator.rules.string.MaxLengthRuleParser
import org.validator.rules.string.MinLengthRuleParser
import org.validator.rules.string.PatternRuleParser
import org.validator.rules.string.StringFormatRuleParser

fun DRAFT_4_RULES(factory: SchemaRuleParserFactory): List<RuleParser> {
    return COMMON_RULES(factory) + listOf(
        ConditionalExclusiveMaximumRuleParser,
        ConditionalExclusiveMinimumRuleParser
    )
}

fun COMMON_RULES(factory: SchemaRuleParserFactory): List<RuleParser> {
    return listOf(
        ConstRuleParser,
        EnumRuleParser,
        TypeRuleParser,
        AllOfRuleParser(factory),
        AnyOfRuleParser(factory),
        NotRuleParser(factory),
        OneOfRuleParser(factory),
        IfThenElseRuleParser(factory),
        RequiredRuleParser,
        MultipleOfRuleParser,
        MinLengthRuleParser,
        MaxLengthRuleParser,
        MaxPropertiesRuleParser,
        MinPropertiesRuleParser,
        AdditionalPropertiesRuleParser(factory),
        DefinitionsRuleParser(factory),
        PropertiesRuleParser(factory),
        MaxItemsRuleParser,
        MinItemsRuleParser,
        PatternRuleParser,
        ItemsRuleParser(factory),
        AdditionalItemsRuleParser(factory),
        PatternPropertiesRuleParser(factory),
        StringFormatRuleParser.default(),
        UniqueItemsRuleParser,
        DependenciesRuleParser(factory),
        ReferenceRuleParser)
}

fun DRAFT_6_RULES(factory: SchemaRuleParserFactory): List<RuleParser> {
    return COMMON_RULES(factory) + listOf(
        ContainsRuleParser(factory),
        ExclusiveMaximumRuleParser,
        ExclusiveMinimumRuleParser,
        MinimumRuleParser,
        MaximumRuleParser,
        PropertyNamesRuleParser(factory)
    )
}

fun DRAFT_7_RULES(factory: SchemaRuleParserFactory): List<RuleParser> {
    return DRAFT_6_RULES(factory) + listOf(
        IfThenElseRuleParser(factory)
    )
}
