package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class Infinite_loop_detectionTest {
  @Test
  public
      fun evaluating_the_same_schema_location_against_the_same_data_location_twice_is_not_a_sign_of_an_infinite_loop():
      Unit {
    val schema = """
        |{
        |    "definitions": {
        |        "int": {
        |            "type": "integer"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                    "${'$'}ref": "#/definitions/int"
        |                }
        |            }
        |        },
        |        {
        |            "additionalProperties": {
        |                "${'$'}ref": "#/definitions/int"
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    val `passing_case` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `passing_case`, true, "DRAFT_4")
    val `failing_case` = """
        |{
        |    "foo": "a string"
        |}
        """.trimMargin()
    run_test(schema, `failing_case`, false, "DRAFT_4")
  }
}
