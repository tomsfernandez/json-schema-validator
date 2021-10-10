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
import org.validator.rules.numeric.ConditionalExclusiveMaximumRuleParser
import org.validator.rules.numeric.ConditionalExclusiveMinimumRuleParser
import org.validator.rules.numeric.MultipleOfRuleParser
import org.validator.rules.string.MaxLengthRuleParser
import org.validator.rules.string.MinLengthRuleParser
import org.validator.rules.string.PatternRuleParser
import org.validator.rules.string.StringFormatRuleParser


fun DRAFT_4_RULES(factory: SchemaRuleParserFactory): List<RuleParser> {
    return listOf(
    ConstRuleParser,
    EnumRuleParser,
    TypeRuleParser,
    AllOfRuleParser(factory),
    AnyOfRuleParser(factory),
    NotRuleParser(factory),
    OneOfRuleParser(factory),
    IfThenElseRuleParser(factory),
    ConditionalExclusiveMaximumRuleParser,
    ConditionalExclusiveMinimumRuleParser,
    RequiredRuleParser,
    MultipleOfRuleParser,
    MinLengthRuleParser,
    MaxLengthRuleParser,
    MaxPropertiesRuleParser,
    MinPropertiesRuleParser,
    AdditionalPropertiesRuleParser(factory),
    DependenciesRuleParser(factory),
    PropertiesRuleParser(factory),
    MaxItemsRuleParser,
    MinItemsRuleParser,
    PatternRuleParser,
    ItemsRuleParser(factory),
    AdditionalItemsRuleParser(factory),
    PatternPropertiesRuleParser(factory),
    StringFormatRuleParser.default(),
    UniqueItemsRuleParser)
    }
