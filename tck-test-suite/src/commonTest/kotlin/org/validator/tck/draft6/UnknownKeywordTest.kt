package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class UnknownKeywordTest {
  @Test
  public fun `$id_inside_an_unknown_keyword_is_not_a_real_identifier`(): Unit {
    val schema = """
        |{
        |    "definitions": {
        |        "id_in_unknown0": {
        |            "not": {
        |                "array_of_schemas": [
        |                    {
        |                        "${'$'}id": "https://localhost:1234/unknownKeyword/my_identifier.json",
        |                        "type": "null"
        |                    }
        |                ]
        |            }
        |        },
        |        "real_id_in_schema": {
        |            "${'$'}id": "https://localhost:1234/unknownKeyword/my_identifier.json",
        |            "type": "string"
        |        },
        |        "id_in_unknown1": {
        |            "not": {
        |                "object_of_schemas": {
        |                    "foo": {
        |                        "${'$'}id": "https://localhost:1234/unknownKeyword/my_identifier.json",
        |                        "type": "integer"
        |                    }
        |                }
        |            }
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "${'$'}ref": "#/definitions/id_in_unknown0"
        |        },
        |        {
        |            "${'$'}ref": "#/definitions/id_in_unknown1"
        |        },
        |        {
        |            "${'$'}ref": "https://localhost:1234/unknownKeyword/my_identifier.json"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `type_matches_second_anyOf__which_has_a_real_schema_in_it` = """"a string""""
    run_test(schema, `type_matches_second_anyOf__which_has_a_real_schema_in_it`, true, "DRAFT_6")
    val `type_matches_non_schema_in_first_anyOf` = """null"""
    run_test(schema, `type_matches_non_schema_in_first_anyOf`, false, "DRAFT_6")
    val `type_matches_non_schema_in_third_anyOf` = """1"""
    run_test(schema, `type_matches_non_schema_in_third_anyOf`, false, "DRAFT_6")
  }
}
