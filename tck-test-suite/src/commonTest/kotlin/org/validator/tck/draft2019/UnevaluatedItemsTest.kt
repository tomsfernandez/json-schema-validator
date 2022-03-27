package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class UnevaluatedItemsTest {
  @Test
  public fun unevaluatedItems_true(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "unevaluatedItems": true
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_unevaluated_items` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_false(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_unevaluated_items` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_as_schema(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "unevaluatedItems": {
        |        "type": "string"
        |    }
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_valid_unevaluated_items` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `with_valid_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_invalid_unevaluated_items` = """
        |[
        |    42
        |]
        """.trimMargin()
    run_test(schema, `with_invalid_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_uniform_items(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": {
        |        "type": "string"
        |    },
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `unevaluatedItems_doesn't_apply` = """
        |[
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `unevaluatedItems_doesn't_apply`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_tuple(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": [
        |        {
        |            "type": "string"
        |        }
        |    ],
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_additionalItems(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": [
        |        {
        |            "type": "string"
        |        }
        |    ],
        |    "additionalItems": true,
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `unevaluatedItems_doesn't_apply` = """
        |[
        |    "foo",
        |    42
        |]
        """.trimMargin()
    run_test(schema, `unevaluatedItems_doesn't_apply`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_nested_tuple(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": [
        |        {
        |            "type": "string"
        |        }
        |    ],
        |    "allOf": [
        |        {
        |            "items": [
        |                true,
        |                {
        |                    "type": "number"
        |                }
        |            ]
        |        }
        |    ],
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |    "foo",
        |    42
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_unevaluated_items` = """
        |[
        |    "foo",
        |    42,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_nested_additionalItems(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "allOf": [
        |        {
        |            "items": [
        |                {
        |                    "type": "string"
        |                }
        |            ],
        |            "additionalItems": true
        |        }
        |    ],
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_no_additional_items` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `with_no_additional_items`, true, "DRAFT_2019_09")
    val `with_additional_items` = """
        |[
        |    "foo",
        |    42,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `with_additional_items`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_nested_unevaluatedItems(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "allOf": [
        |        {
        |            "items": [
        |                {
        |                    "type": "string"
        |                }
        |            ]
        |        },
        |        {
        |            "unevaluatedItems": true
        |        }
        |    ],
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_no_additional_items` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `with_no_additional_items`, true, "DRAFT_2019_09")
    val `with_additional_items` = """
        |[
        |    "foo",
        |    42,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `with_additional_items`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_anyOf(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": [
        |        {
        |            "const": "foo"
        |        }
        |    ],
        |    "anyOf": [
        |        {
        |            "items": [
        |                true,
        |                {
        |                    "const": "bar"
        |                }
        |            ]
        |        },
        |        {
        |            "items": [
        |                true,
        |                true,
        |                {
        |                    "const": "baz"
        |                }
        |            ]
        |        }
        |    ],
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `when_one_schema_matches_and_has_no_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `when_one_schema_matches_and_has_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `when_one_schema_matches_and_has_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar",
        |    42
        |]
        """.trimMargin()
    run_test(schema, `when_one_schema_matches_and_has_unevaluated_items`, false, "DRAFT_2019_09")
    val `when_two_schemas_match_and_has_no_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar",
        |    "baz"
        |]
        """.trimMargin()
    run_test(schema, `when_two_schemas_match_and_has_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `when_two_schemas_match_and_has_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar",
        |    "baz",
        |    42
        |]
        """.trimMargin()
    run_test(schema, `when_two_schemas_match_and_has_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_oneOf(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": [
        |        {
        |            "const": "foo"
        |        }
        |    ],
        |    "oneOf": [
        |        {
        |            "items": [
        |                true,
        |                {
        |                    "const": "bar"
        |                }
        |            ]
        |        },
        |        {
        |            "items": [
        |                true,
        |                {
        |                    "const": "baz"
        |                }
        |            ]
        |        }
        |    ],
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar",
        |    42
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_not(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": [
        |        {
        |            "const": "foo"
        |        }
        |    ],
        |    "not": {
        |        "not": {
        |            "items": [
        |                true,
        |                {
        |                    "const": "bar"
        |                }
        |            ]
        |        }
        |    },
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_if_then_else(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "items": [
        |        {
        |            "const": "foo"
        |        }
        |    ],
        |    "if": {
        |        "items": [
        |            true,
        |            {
        |                "const": "bar"
        |            }
        |        ]
        |    },
        |    "then": {
        |        "items": [
        |            true,
        |            true,
        |            {
        |                "const": "then"
        |            }
        |        ]
        |    },
        |    "else": {
        |        "items": [
        |            true,
        |            true,
        |            true,
        |            {
        |                "const": "else"
        |            }
        |        ]
        |    },
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `when_if_matches_and_it_has_no_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar",
        |    "then"
        |]
        """.trimMargin()
    run_test(schema, `when_if_matches_and_it_has_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `when_if_matches_and_it_has_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar",
        |    "then",
        |    "else"
        |]
        """.trimMargin()
    run_test(schema, `when_if_matches_and_it_has_unevaluated_items`, false, "DRAFT_2019_09")
    val `when_if_doesn't_match_and_it_has_no_unevaluated_items` = """
        |[
        |    "foo",
        |    42,
        |    42,
        |    "else"
        |]
        """.trimMargin()
    run_test(schema, `when_if_doesn't_match_and_it_has_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `when_if_doesn't_match_and_it_has_unevaluated_items` = """
        |[
        |    "foo",
        |    42,
        |    42,
        |    "else",
        |    42
        |]
        """.trimMargin()
    run_test(schema, `when_if_doesn't_match_and_it_has_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedItems_with_boolean_schemas(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "allOf": [
        |        true
        |    ],
        |    "unevaluatedItems": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_unevaluated_items` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `unevaluatedItems_with_$ref`(): Unit {
    val schema = """
        |{
        |    "type": "array",
        |    "${'$'}ref": "#/${'$'}defs/bar",
        |    "items": [
        |        {
        |            "type": "string"
        |        }
        |    ],
        |    "unevaluatedItems": false,
        |    "${'$'}defs": {
        |        "bar": {
        |            "items": [
        |                true,
        |                {
        |                    "type": "string"
        |                }
        |            ]
        |        }
        |    }
        |}
        """.trimMargin()
    val `with_no_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_items`, true, "DRAFT_2019_09")
    val `with_unevaluated_items` = """
        |[
        |    "foo",
        |    "bar",
        |    "baz"
        |]
        """.trimMargin()
    run_test(schema, `with_unevaluated_items`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `unevaluatedItems_can't_see_inside_cousins`(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "items": [
        |                true
        |            ]
        |        },
        |        {
        |            "unevaluatedItems": false
        |        }
        |    ]
        |}
        """.trimMargin()
    val `always_fails` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `always_fails`, false, "DRAFT_2019_09")
  }

  @Test
  public fun item_is_evaluated_in_an_uncle_schema_to_unevaluatedItems(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "array",
        |            "items": [
        |                {
        |                    "type": "string"
        |                }
        |            ],
        |            "unevaluatedItems": false
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                    "items": [
        |                        true,
        |                        {
        |                            "type": "string"
        |                        }
        |                    ]
        |                }
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    val `no_extra_items` = """
        |{
        |    "foo": [
        |        "test"
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `no_extra_items`, true, "DRAFT_2019_09")
    val `uncle_keyword_evaluation_is_not_significant` = """
        |{
        |    "foo": [
        |        "test",
        |        "test"
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `uncle_keyword_evaluation_is_not_significant`, false, "DRAFT_2019_09")
  }
}
