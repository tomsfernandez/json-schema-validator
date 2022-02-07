package org.validator.rules.any

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

object DefinitionsRule: ValidationRule {

    override fun eval(element: JsonElement): List<Error> = emptyList()
}

data class DefinitionsRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val key = "definitions"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(key)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val parser = factory.make()
        return element.entries().map { entry -> entry.second.asObject() }.toEither()
            .withRight { objects -> objects.map { obj -> parser.parse(obj)}.toFlatEither() }
            .map { DefinitionsRule }
    }
}
