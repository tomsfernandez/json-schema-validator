package org.validator.tck

import org.validator.JsonSchemaParserBuilder
import org.validator.metaschemas.Draft2019
import org.validator.metaschemas.Draft4
import org.validator.metaschemas.Draft6
import org.validator.metaschemas.Draft7
import org.validator.rules.DraftParsers
import org.validator.rules.DraftParsers.DRAFT_2019
import org.validator.rules.DraftParsers.DRAFT_4
import org.validator.rules.DraftParsers.DRAFT_6
import org.validator.rules.DraftParsers.DRAFT_7
import org.validator.rules.SchemaParser
import org.validator.testing.adapt
import kotlin.test.assertTrue

fun provideParser(draft: String): SchemaParser {
    return when(draft) {
        "DRAFT_4" -> JsonSchemaParserBuilder().setDefault(DRAFT_4).build()
        "DRAFT_6" -> JsonSchemaParserBuilder().setDefault(DRAFT_6).build()
        "DRAFT_7" -> JsonSchemaParserBuilder().setDefault(DRAFT_7).build()
        "DRAFT_2019_09" -> JsonSchemaParserBuilder().setDefault(DRAFT_2019).build()
        else -> throw RuntimeException("Unexpected draft. Supported drafts are: Draft 4, Draft 6, Draft 7, Draft 2019-09")
    }
}

fun run_test(schemaAsString: String, payloadAsString: String, conforms: Boolean, draft: String) {
    val json = adapt(schemaAsString)
    val schema = provideParser(draft).parse(json)
        .addSchema("http://json-schema.org/draft-04/schema#", Draft4.schema())
        .addSchema("http://json-schema.org/draft-06/schema#", Draft6.schema())
        .addSchema("http://json-schema.org/draft-07/schema#", Draft7.schema())
        .addSchema("https://json-schema.org/draft/2019-09/schema#", Draft2019.schema())
    assertTrue("Schema results: ${schema.errors}") { schema.errors.isEmpty() }

    val payloadAsObj = adapt(payloadAsString)
    val results = schema.eval(payloadAsObj)
    if (results.isEmpty() != conforms) println("Expected: $conforms, got: $results")
    assertTrue("Evaluation results: $results") { results.isEmpty() == conforms }
}
