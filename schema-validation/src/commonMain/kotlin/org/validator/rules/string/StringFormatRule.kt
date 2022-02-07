package org.validator.rules.string

import kotlinx.datetime.Instant
import org.validator.*

typealias FormatValidator = (String) -> Error?

data class StringFormatRule(val formatter: FormatValidator): ValidationRule {

    override fun eval(element: JsonElement): List<Error> {
        return element.string().map(formatter)
            .map { maybeError -> if (maybeError != null) listOf(maybeError) else emptyList() }
            .rightOrDefault(emptyList())
    }
}

data class StringFormatRuleParser(val formatters: Map<String, FormatValidator>): RuleParser {

    companion object {
        fun default(): RuleParser {
            return StringFormatRuleParser(KnownFormatValidators.default())
        }
    }

    private val key = "format"

    override fun canParse(element: JsonObject): Boolean = element.containsKey(key)

    override fun parse(element: JsonObject): Either<List<Error>, ValidationRule> {
        return element.get(key).string()
            .mapEither(::listOf) { format ->
                val maybeFormatter = formatters[format]
                if (maybeFormatter == null) Either.Left(listOf(Error("Couldn't find formatter for format: $format")))
                else Either.Right(StringFormatRule(maybeFormatter))
            }
    }
}

object KnownFormatValidators {
    fun default(): Map<String, FormatValidator> {
        return mapOf(
            "date-time" to dateTimeValidator,
            "email" to emailValidator,
            "hostname" to hostNameValidator,
            "ipv4" to ipv4Validator,
            "ipv6" to ipv6Validator,
            "uri" to uriValidator
        )
    }
}

val uriRegex = "^([a-z0-9+.-]+):(?://(?:((?:[a-z0-9-._~!\$&'()*+,;=:]|%[0-9A-F]{2})*)@)?((?:[a-z0-9-._~!\$&'()*+,;=]|%[0-9A-F]{2})*)(?::(\\d*))?(/(?:[a-z0-9-._~!\$&'()*+,;=:@/]|%[0-9A-F]{2})*)?|(/?(?:[a-z0-9-._~!\$&'()*+,;=:@]|%[0-9A-F]{2})+(?:[a-z0-9-._~!\$&'()*+,;=:@/]|%[0-9A-F]{2})*)?)(?:\\?((?:[a-z0-9-._~!\$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?(?:#((?:[a-z0-9-._~!\$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?\$".toRegex()
val ipv6Regex = "^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*\$".toRegex()
val ipv4Regex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\$".toRegex()
val hostNameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)+([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])\$".toRegex()
val emailRegex = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".toRegex()

val uriValidator: FormatValidator = { value ->
    if (uriRegex.matches(value)) null
    else Error("$value is not in uri format")
}

val ipv6Validator: FormatValidator = { value ->
    if (ipv6Regex.matches(value)) null
    else Error("$value is not in ipv6 format")
}

val ipv4Validator: FormatValidator = { value ->
    if (ipv4Regex.matches(value)) null
    else Error("$value is not in ipv4 format")
}

val hostNameValidator: FormatValidator = { value ->
    if (hostNameRegex.matches(value)) null
    else Error("$value is not in hostname format")
}

val emailValidator: FormatValidator = { value ->
    if (emailRegex.matches(value)) null
    else Error("$value is not in email format")
}

val dateTimeValidator: FormatValidator = { value ->
    try {
        Instant.parse(value)
        null
    } catch (exception: Exception) {
        val message = "$value is not date-time because: ${exception.message}"
        Error(message)
    }
}
