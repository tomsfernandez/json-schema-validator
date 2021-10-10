package org.validator.rules.boolean

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class NotRule(val rule: ValidationRule): ValidationRule {
    override fun eval(element: JsonElement): List<Error> {
        val isValid = rule.eval(element).isEmpty()
        return if (isValid) listOf(Error("Element conforms with the 'not' schema"))
        else emptyList()
    }
}

data class NotRuleParser(val parserFactory: SchemaRuleParserFactory): RuleParser {

    private val key: String = "not"

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val notValue = element.get(key) !!
        return notValue.asObject().mapEither(::listOf) { objEntry -> parserFactory.make().parse(objEntry) }.map { NotRule(it) }
    }
}
