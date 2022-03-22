package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DefinitionsTest {
  @Test
  public fun validate_definition_against_metaschema(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "http://json-schema.org/draft-06/schema#"
        |}
        """.trimMargin()
    val `valid_definition_schema` = """
        |{
        |    "definitions": {
        |        "foo": {
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `valid_definition_schema`, true, "DRAFT_6")
    val `invalid_definition_schema` = """
        |{
        |    "definitions": {
        |        "foo": {
        |            "type": 1
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `invalid_definition_schema`, false, "DRAFT_6")
  }
}
