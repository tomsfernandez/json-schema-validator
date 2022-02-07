package org.validator.rules.string

import org.validator.*

data class PatternRule(val pattern: Regex) : ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        return element.string().map {
            val matches = pattern.containsMatchIn(it)
            if (!matches) listOf(Error("Value doesn't match regex"))
            else emptyList()
        }.rightOrDefault(emptyList())
    }
}

object PatternRuleParser : RuleParser {

    private const val key = "pattern"

    override fun canParse(element: JsonObject): Boolean = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(key).string().map(::listOf) { PatternRule(it.toRegex()) }
    }
}
