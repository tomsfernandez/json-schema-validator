package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DefsTest {
  @Test
  public fun validate_definition_against_metaschema(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "https://json-schema.org/draft/2019-09/schema"
        |}
        """.trimMargin()
    val `valid_definition_schema` = """
        |{
        |    "${'$'}defs": {
        |        "foo": {
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `valid_definition_schema`, true, "DRAFT_2019_09")
    val `invalid_definition_schema` = """
        |{
        |    "${'$'}defs": {
        |        "foo": {
        |            "type": 1
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `invalid_definition_schema`, false, "DRAFT_2019_09")
  }
}
