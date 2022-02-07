package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class NotRule(val rule: ValidationRule): ValidationRule {

    companion object {
        private val ERROR = Error("Element conforms with the 'not' schema")
    }

    override fun eval(element: JsonElement): List<Error> {
        val isValid = rule.eval(element).isEmpty()
        return if (isValid) listOf(ERROR)
        else emptyList()
    }
}

data class NotRuleParser(val parserFactory: SchemaRuleParserFactory): RuleParser {

    private val KEY: String = "not"

    override fun canParse(element: JsonObject) = element.get(KEY) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val notValue = element.get(KEY) !!
        return notValue.asObject().mapLeft(::listOf).withRight { parserFactory.make().parse(it) }.map { NotRule(it) }
    }
}
