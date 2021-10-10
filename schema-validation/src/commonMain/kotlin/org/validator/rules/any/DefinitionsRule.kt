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
        val (errors, objects) = element.entries().map { entry -> entry.second.asObject() }.partitionList()

        if (errors.any()) return Either.Left(errors)

        val (parseErrors, parseResults) = objects.map { obj -> parser.parse(obj) }.partitionList()


        // TODO: do something with parseResults
        return if (parseErrors.any()) Either.Left(parseErrors.flatten())
        else Either.Right(DefinitionsRule)
    }
}
