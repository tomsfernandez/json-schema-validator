package org.validator.rules.boolean

import org.validator.*

abstract class OfRuleParser(private val key: String, val factory: (List<ValidationRule>) -> ValidationRule, val parser: (JsonObject) -> Either<List<Error>, ValidationRule>): RuleParser {

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(key).array().mapEither(::listOf) { array ->
            array.elements()
                .map { elem -> elem.asObject() }.toEither()
                .withRight { objs -> objs.map(parser).toFlatEither() }
                .map { schemas -> factory(schemas) }
        }
    }
}
