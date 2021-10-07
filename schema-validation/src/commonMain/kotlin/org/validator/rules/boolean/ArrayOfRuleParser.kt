package org.validator.rules.boolean

import org.validator.*

abstract class ArrayOfRuleParser(private val key: String, val factory: (List<ValidationRule>) -> ValidationRule, val parser: (JsonObject) -> Either<List<Error>, ValidationRule>): RuleParser {

    override fun canParse(element: JsonObject) = element.get(key) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(key).asArray().map(::listOf) { array ->
            val listOfEithers: List<Either<List<Error>, ValidationRule>> = array.elements().map { elem -> parser(elem.asObject().toRightValueOrNull() !!) }
            flatten(listOfEithers).map {schemas -> factory(schemas)}
        }
    }

    private fun flatten(eitherList: List<Either<List<Error>, ValidationRule>>): Either<List<Error>, List<ValidationRule>> {
        val (errors, schemas) = eitherList.partition { x -> x.toLeftValueOrNull() != null }
        return if (errors.isEmpty()) Either.Right(schemas.map { x -> x.toRightValueOrNull() !! })
        else Either.Left(errors.map { x -> x.toLeftValueOrNull() !! }.flatten())
    }
}
