package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class IdTest {
  @Test
  public fun `Invalid_use_of_fragments_in_location_independent_$id`(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "https://json-schema.org/draft/2019-09/schema"
        |}
        """.trimMargin()
    val `Identifier_name` = """
        |{
        |    "${'$'}ref": "#foo",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "#foo",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_name`, false, "DRAFT_2019_09")
    val `Identifier_name_and_no_ref` = """
        |{
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "#foo"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_name_and_no_ref`, false, "DRAFT_2019_09")
    val `Identifier_path` = """
        |{
        |    "${'$'}ref": "#/a/b",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "#/a/b",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_path`, false, "DRAFT_2019_09")
    val `Identifier_name_with_absolute_URI` = """
        |{
        |    "${'$'}ref": "http://localhost:1234/bar#foo",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/bar#foo",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_name_with_absolute_URI`, false, "DRAFT_2019_09")
    val `Identifier_path_with_absolute_URI` = """
        |{
        |    "${'$'}ref": "http://localhost:1234/bar#/a/b",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/bar#/a/b",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_path_with_absolute_URI`, false, "DRAFT_2019_09")
    val `Identifier_name_with_base_URI_change_in_subschema` = """
        |{
        |    "${'$'}id": "http://localhost:1234/root",
        |    "${'$'}ref": "http://localhost:1234/nested.json#foo",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "nested.json",
        |            "${'$'}defs": {
        |                "B": {
        |                    "${'$'}id": "#foo",
        |                    "type": "integer"
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_name_with_base_URI_change_in_subschema`, false, "DRAFT_2019_09")
    val `Identifier_path_with_base_URI_change_in_subschema` = """
        |{
        |    "${'$'}id": "http://localhost:1234/root",
        |    "${'$'}ref": "http://localhost:1234/nested.json#/a/b",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "nested.json",
        |            "${'$'}defs": {
        |                "B": {
        |                    "${'$'}id": "#/a/b",
        |                    "type": "integer"
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_path_with_base_URI_change_in_subschema`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `Valid_use_of_empty_fragments_in_location_independent_$id`(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "https://json-schema.org/draft/2019-09/schema"
        |}
        """.trimMargin()
    val `Identifier_name_with_absolute_URI` = """
        |{
        |    "${'$'}ref": "http://localhost:1234/bar",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/bar#",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_name_with_absolute_URI`, true, "DRAFT_2019_09")
    val `Identifier_name_with_base_URI_change_in_subschema` = """
        |{
        |    "${'$'}id": "http://localhost:1234/root",
        |    "${'$'}ref": "http://localhost:1234/nested.json#/${'$'}defs/B",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "nested.json",
        |            "${'$'}defs": {
        |                "B": {
        |                    "${'$'}id": "#",
        |                    "type": "integer"
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Identifier_name_with_base_URI_change_in_subschema`, true, "DRAFT_2019_09")
  }

  @Test
  public fun `Unnormalized_$ids_are_allowed_but_discouraged`(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "https://json-schema.org/draft/2019-09/schema"
        |}
        """.trimMargin()
    val `Unnormalized_identifier` = """
        |{
        |    "${'$'}ref": "http://localhost:1234/foo/baz",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/foo/bar/../baz",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Unnormalized_identifier`, true, "DRAFT_2019_09")
    val `Unnormalized_identifier_and_no_ref` = """
        |{
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/foo/bar/../baz",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Unnormalized_identifier_and_no_ref`, true, "DRAFT_2019_09")
    val `Unnormalized_identifier_with_empty_fragment` = """
        |{
        |    "${'$'}ref": "http://localhost:1234/foo/baz",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/foo/bar/../baz#",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Unnormalized_identifier_with_empty_fragment`, true, "DRAFT_2019_09")
    val `Unnormalized_identifier_with_empty_fragment_and_no_ref` = """
        |{
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/foo/bar/../baz#",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Unnormalized_identifier_with_empty_fragment_and_no_ref`, true,
        "DRAFT_2019_09")
  }

  @Test
  public fun `$id_inside_an_enum_is_not_a_real_identifier`(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "id_in_enum": {
        |            "enum": [
        |                {
        |                    "${'$'}id": "https://localhost:1234/id/my_identifier.json",
        |                    "type": "null"
        |                }
        |            ]
        |        },
        |        "real_id_in_schema": {
        |            "${'$'}id": "https://localhost:1234/id/my_identifier.json",
        |            "type": "string"
        |        },
        |        "zzz_id_in_const": {
        |            "const": {
        |                "${'$'}id": "https://localhost:1234/id/my_identifier.json",
        |                "type": "null"
        |            }
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "${'$'}ref": "#/${'$'}defs/id_in_enum"
        |        },
        |        {
        |            "${'$'}ref": "https://localhost:1234/id/my_identifier.json"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `exact_match_to_enum__and_type_matches` = """
        |{
        |    "${'$'}id": "https://localhost:1234/id/my_identifier.json",
        |    "type": "null"
        |}
        """.trimMargin()
    run_test(schema, `exact_match_to_enum__and_type_matches`, true, "DRAFT_2019_09")
    val `match_$ref_to_$id` = """"a string to match #/${'$'}defs/id_in_enum""""
    run_test(schema, `match_$ref_to_$id`, true, "DRAFT_2019_09")
    val `no_match_on_enum_or_$ref_to_$id` = """1"""
    run_test(schema, `no_match_on_enum_or_$ref_to_$id`, false, "DRAFT_2019_09")
  }
}
