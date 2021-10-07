package org.validator.tck

import org.validator.JsonObject
import org.validator.RuleParser
import org.validator.rules.DraftParsers
import org.validator.testing.adapt
import kotlin.test.assertEquals
import kotlin.test.assertTrue

fun provideParser(draft: String): RuleParser {
    return when(draft) {
        "DRAFT_4" -> DraftParsers.DRAFT_4
        else -> throw RuntimeException("something")
    }
}

fun run_test(schemaAsString: String, payloadAsString: String, conforms: Boolean, draft: String) {
    val schema = adapt(schemaAsString) as JsonObject
    val maybeRule = provideParser(draft).parse(schema)
    assertEquals(null, maybeRule.toLeftValueOrNull())

    val rule = maybeRule.toRightValueOrNull() !!

    val payloadAsObj = adapt(payloadAsString)
    val results = rule.eval(payloadAsObj)
    assertTrue { results.isEmpty() == conforms }
}
