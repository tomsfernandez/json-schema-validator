package org.validator.tck

import org.validator.JsonObject
import org.validator.RuleParser
import org.validator.metaschemas.Draft4
import org.validator.rules.DraftParsers
import org.validator.testing.adapt
import kotlin.test.assertTrue

fun provideParser(draft: String): RuleParser {
    return when(draft) {
        "DRAFT_4" -> DraftParsers.DRAFT_4_ROOT
        else -> throw RuntimeException("something")
    }
}

fun run_test(schemaAsString: String, payloadAsString: String, conforms: Boolean, draft: String) {
    val obj = adapt(schemaAsString) as JsonObject
    val schema = provideParser(draft).parse("#", "#", obj).addSchema("http://json-schema.org/draft-04/schema#", Draft4.schema)
    assertTrue { schema.errors.isEmpty() }

    val payloadAsObj = adapt(payloadAsString)
    val results = schema.rule.eval("", payloadAsObj, schema)
    assertTrue { results.isEmpty() == conforms }
}
