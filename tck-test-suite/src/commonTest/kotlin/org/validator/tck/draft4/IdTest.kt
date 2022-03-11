package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class IdTest {
  @Test
  public fun id_inside_an_enum_is_not_a_real_identifier(): Unit {
    val schema = """
        |{
        |    "definitions": {
        |        "id_in_enum": {
        |            "enum": [
        |                {
        |                    "id": "https://localhost:1234/my_identifier.json",
        |                    "type": "null"
        |                }
        |            ]
        |        },
        |        "real_id_in_schema": {
        |            "id": "https://localhost:1234/my_identifier.json",
        |            "type": "string"
        |        },
        |        "zzz_id_in_const": {
        |            "const": {
        |                "id": "https://localhost:1234/my_identifier.json",
        |                "type": "null"
        |            }
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "${'$'}ref": "#/definitions/id_in_enum"
        |        },
        |        {
        |            "${'$'}ref": "https://localhost:1234/my_identifier.json"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `exact_match_to_enum__and_type_matches` = """
        |{
        |    "id": "https://localhost:1234/my_identifier.json",
        |    "type": "null"
        |}
        """.trimMargin()
    run_test(schema, `exact_match_to_enum__and_type_matches`, true, "DRAFT_4")
    val `match_$ref_to_id` = """"a string to match #/definitions/id_in_enum""""
    run_test(schema, `match_$ref_to_id`, true, "DRAFT_4")
    val `no_match_on_enum_or_$ref_to_id` = """1"""
    run_test(schema, `no_match_on_enum_or_$ref_to_id`, false, "DRAFT_4")
  }
}
