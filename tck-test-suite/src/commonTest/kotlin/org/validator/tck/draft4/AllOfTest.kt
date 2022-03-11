package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class AllOfTest {
  @Test
  public fun allOf(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "properties": {
        |                "bar": {
        |                    "type": "integer"
        |                }
        |            },
        |            "required": [
        |                "bar"
        |            ]
        |        },
        |        {
        |            "properties": {
        |                "foo": {
        |                    "type": "string"
        |                }
        |            },
        |            "required": [
        |                "foo"
        |            ]
        |        }
        |    ]
        |}
        """.trimMargin()
    val `allOf` = """
        |{
        |    "foo": "baz",
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `allOf`, true, "DRAFT_4")
    val `mismatch_second` = """
        |{
        |    "foo": "baz"
        |}
        """.trimMargin()
    run_test(schema, `mismatch_second`, false, "DRAFT_4")
    val `mismatch_first` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `mismatch_first`, false, "DRAFT_4")
    val `wrong_type` = """
        |{
        |    "foo": "baz",
        |    "bar": "quux"
        |}
        """.trimMargin()
    run_test(schema, `wrong_type`, false, "DRAFT_4")
  }

  @Test
  public fun allOf_with_base_schema(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "bar": {
        |            "type": "integer"
        |        }
        |    },
        |    "required": [
        |        "bar"
        |    ],
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                    "type": "string"
        |                }
        |            },
        |            "required": [
        |                "foo"
        |            ]
        |        },
        |        {
        |            "properties": {
        |                "baz": {
        |                    "type": "null"
        |                }
        |            },
        |            "required": [
        |                "baz"
        |            ]
        |        }
        |    ]
        |}
        """.trimMargin()
    val `valid` = """
        |{
        |    "foo": "quux",
        |    "bar": 2,
        |    "baz": null
        |}
        """.trimMargin()
    run_test(schema, `valid`, true, "DRAFT_4")
    val `mismatch_base_schema` = """
        |{
        |    "foo": "quux",
        |    "baz": null
        |}
        """.trimMargin()
    run_test(schema, `mismatch_base_schema`, false, "DRAFT_4")
    val `mismatch_first_allOf` = """
        |{
        |    "bar": 2,
        |    "baz": null
        |}
        """.trimMargin()
    run_test(schema, `mismatch_first_allOf`, false, "DRAFT_4")
    val `mismatch_second_allOf` = """
        |{
        |    "foo": "quux",
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `mismatch_second_allOf`, false, "DRAFT_4")
    val `mismatch_both` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `mismatch_both`, false, "DRAFT_4")
  }

  @Test
  public fun allOf_simple_types(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "maximum": 30
        |        },
        |        {
        |            "minimum": 20
        |        }
        |    ]
        |}
        """.trimMargin()
    val `valid` = """25"""
    run_test(schema, `valid`, true, "DRAFT_4")
    val `mismatch_one` = """35"""
    run_test(schema, `mismatch_one`, false, "DRAFT_4")
  }

  @Test
  public fun allOf_with_one_empty_schema(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |        }
        |    ]
        |}
        """.trimMargin()
    val `any_data_is_valid` = """1"""
    run_test(schema, `any_data_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun allOf_with_two_empty_schemas(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |        },
        |        {
        |        }
        |    ]
        |}
        """.trimMargin()
    val `any_data_is_valid` = """1"""
    run_test(schema, `any_data_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun allOf_with_the_first_empty_schema(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |        },
        |        {
        |            "type": "number"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `number_is_valid` = """1"""
    run_test(schema, `number_is_valid`, true, "DRAFT_4")
    val `string_is_invalid` = """"foo""""
    run_test(schema, `string_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun allOf_with_the_last_empty_schema(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "type": "number"
        |        },
        |        {
        |        }
        |    ]
        |}
        """.trimMargin()
    val `number_is_valid` = """1"""
    run_test(schema, `number_is_valid`, true, "DRAFT_4")
    val `string_is_invalid` = """"foo""""
    run_test(schema, `string_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun nested_allOf__to_check_validation_semantics(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "allOf": [
        |                {
        |                    "type": "null"
        |                }
        |            ]
        |        }
        |    ]
        |}
        """.trimMargin()
    val `null_is_valid` = """null"""
    run_test(schema, `null_is_valid`, true, "DRAFT_4")
    val `anything_non_null_is_invalid` = """123"""
    run_test(schema, `anything_non_null_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun allOf_combined_with_anyOf__oneOf(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "multipleOf": 2
        |        }
        |    ],
        |    "anyOf": [
        |        {
        |            "multipleOf": 3
        |        }
        |    ],
        |    "oneOf": [
        |        {
        |            "multipleOf": 5
        |        }
        |    ]
        |}
        """.trimMargin()
    val `allOf__false__anyOf__false__oneOf__false` = """1"""
    run_test(schema, `allOf__false__anyOf__false__oneOf__false`, false, "DRAFT_4")
    val `allOf__false__anyOf__false__oneOf__true` = """5"""
    run_test(schema, `allOf__false__anyOf__false__oneOf__true`, false, "DRAFT_4")
    val `allOf__false__anyOf__true__oneOf__false` = """3"""
    run_test(schema, `allOf__false__anyOf__true__oneOf__false`, false, "DRAFT_4")
    val `allOf__false__anyOf__true__oneOf__true` = """15"""
    run_test(schema, `allOf__false__anyOf__true__oneOf__true`, false, "DRAFT_4")
    val `allOf__true__anyOf__false__oneOf__false` = """2"""
    run_test(schema, `allOf__true__anyOf__false__oneOf__false`, false, "DRAFT_4")
    val `allOf__true__anyOf__false__oneOf__true` = """10"""
    run_test(schema, `allOf__true__anyOf__false__oneOf__true`, false, "DRAFT_4")
    val `allOf__true__anyOf__true__oneOf__false` = """6"""
    run_test(schema, `allOf__true__anyOf__true__oneOf__false`, false, "DRAFT_4")
    val `allOf__true__anyOf__true__oneOf__true` = """30"""
    run_test(schema, `allOf__true__anyOf__true__oneOf__true`, true, "DRAFT_4")
  }
}
