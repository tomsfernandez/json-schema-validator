package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DefinitionsTest {
  @Test
  public fun validate_definition_against_metaschema(): Unit {
    val schema = "{\"${'$'}ref\":\"http://json-schema.org/draft-04/schema#\"}"
    val `valid_definition_schema` = "{\"definitions\":{\"foo\":{\"type\":\"integer\"}}}"
    run_test(schema, `valid_definition_schema`, true, "DRAFT_4")
    val `invalid_definition_schema` = "{\"definitions\":{\"foo\":{\"type\":1}}}"
    run_test(schema, `invalid_definition_schema`, false, "DRAFT_4")
  }
}
