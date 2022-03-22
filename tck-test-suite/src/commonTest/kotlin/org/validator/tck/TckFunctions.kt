package org.validator.tck

import org.validator.metaschemas.Draft4
import org.validator.metaschemas.Draft6
import org.validator.rules.DraftParsers
import org.validator.rules.SchemaParser
import org.validator.testing.adapt
import kotlin.test.assertTrue

fun provideParser(draft: String): SchemaParser {
    return when(draft) {
        "DRAFT_4" -> DraftParsers.DRAFT_4_ROOT
        "DRAFT_6" -> DraftParsers.DRAFT_6_ROOT
        else -> throw RuntimeException("something")
    }
}

fun run_test(schemaAsString: String, payloadAsString: String, conforms: Boolean, draft: String) {
    val json = adapt(schemaAsString)
    val schema = provideParser(draft).parse("#", "#", json)
        .addSchema("http://json-schema.org/draft-04/schema#", Draft4.schema())
        .addSchema("http://json-schema.org/draft-06/schema#", Draft6.schema())
    assertTrue("Schema results: ${schema.errors}") { schema.errors.isEmpty() }

    val payloadAsObj = adapt(payloadAsString)
    val results = schema.rule.eval("", payloadAsObj, schema)
    if (results.isEmpty() != conforms) println("Expected: $conforms, got: $results")
    assertTrue("Evaluation results: $results") { results.isEmpty() == conforms }
}
