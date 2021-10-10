package org.validator.rules.conditional

import org.validator.*
import org.validator.rules.SchemaRuleParserFactory

data class IfThenElseRule(val ifSchema: ValidationRule?, val thenSchema: ValidationRule?, val elseSchema: ValidationRule?): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return if (ifSchema != null) {
            val hasErrors = ifSchema.eval(element).isNotEmpty()
            if (hasErrors && elseSchema != null) elseSchema.eval(element)
            else thenSchema?.eval(element) ?: emptyList()
        } else emptyList()
    }
}

data class IfThenElseRuleParser(val factory: SchemaRuleParserFactory): RuleParser {

    private val ifKey: String = "if"
    private val elseKey: String = "else"
    private val thenKey: String = "then"

    override fun canParse(element: JsonObject) = element.get(ifKey) != null || element.get(thenKey) != null || element.get(elseKey) != null

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        val ifSchemaEither = parse(element.get("if"))
        val elseSchemaEither = parse(element.get("else"))
        val thenSchemaEither = parse(element.get("then"))
        val errors = leftOrEmptyList(ifSchemaEither) + leftOrEmptyList(elseSchemaEither) + leftOrEmptyList(thenSchemaEither)
        return if (errors.isEmpty()) {
            Either.Right(IfThenElseRule(ifSchemaEither.right(),
                thenSchemaEither.right(),
                elseSchemaEither.right()))
        } else Either.Left(errors)
    }

    private fun leftOrEmptyList(either: Either<List<Error>, ValidationRule?>): List<Error> {
        return either.left() ?: emptyList()
    }

    private fun parse(element: JsonElement?): Either<List<Error>, ValidationRule?> {
        return element?.asObject()?.mapEither(::listOf) { x -> factory.make().parse(x) } ?: Either.Right(null)
    }
}
