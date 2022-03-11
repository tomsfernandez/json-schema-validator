package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class EnumTest {
  @Test
  public fun simple_enum_validation(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        1,
        |        2,
        |        3
        |    ]
        |}
        """.trimMargin()
    val `one_of_the_enum_is_valid` = """1"""
    run_test(schema, `one_of_the_enum_is_valid`, true, "DRAFT_4")
    val `something_else_is_invalid` = """4"""
    run_test(schema, `something_else_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun heterogeneous_enum_validation(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        6,
        |        "foo",
        |        [
        |        ],
        |        true,
        |        {
        |            "foo": 12
        |        }
        |    ]
        |}
        """.trimMargin()
    val `one_of_the_enum_is_valid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `one_of_the_enum_is_valid`, true, "DRAFT_4")
    val `something_else_is_invalid` = """null"""
    run_test(schema, `something_else_is_invalid`, false, "DRAFT_4")
    val `objects_are_deep_compared` = """
        |{
        |    "foo": false
        |}
        """.trimMargin()
    run_test(schema, `objects_are_deep_compared`, false, "DRAFT_4")
    val `valid_object_matches` = """
        |{
        |    "foo": 12
        |}
        """.trimMargin()
    run_test(schema, `valid_object_matches`, true, "DRAFT_4")
    val `extra_properties_in_object_is_invalid` = """
        |{
        |    "foo": 12,
        |    "boo": 42
        |}
        """.trimMargin()
    run_test(schema, `extra_properties_in_object_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun heterogeneous_enum_with_null_validation(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        6,
        |        null
        |    ]
        |}
        """.trimMargin()
    val `null_is_valid` = """null"""
    run_test(schema, `null_is_valid`, true, "DRAFT_4")
    val `number_is_valid` = """6"""
    run_test(schema, `number_is_valid`, true, "DRAFT_4")
    val `something_else_is_invalid` = """"test""""
    run_test(schema, `something_else_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun enums_in_properties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "enum": [
        |                "foo"
        |            ]
        |        },
        |        "bar": {
        |            "enum": [
        |                "bar"
        |            ]
        |        }
        |    },
        |    "required": [
        |        "bar"
        |    ]
        |}
        """.trimMargin()
    val `both_properties_are_valid` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `both_properties_are_valid`, true, "DRAFT_4")
    val `wrong_foo_value` = """
        |{
        |    "foo": "foot",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `wrong_foo_value`, false, "DRAFT_4")
    val `wrong_bar_value` = """
        |{
        |    "foo": "foo",
        |    "bar": "bart"
        |}
        """.trimMargin()
    run_test(schema, `wrong_bar_value`, false, "DRAFT_4")
    val `missing_optional_property_is_valid` = """
        |{
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `missing_optional_property_is_valid`, true, "DRAFT_4")
    val `missing_required_property_is_invalid` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `missing_required_property_is_invalid`, false, "DRAFT_4")
    val `missing_all_properties_is_invalid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `missing_all_properties_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun enum_with_escaped_characters(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        "foo\\nbar",
        |        "foo\\rbar"
        |    ]
        |}
        """.trimMargin()
    val `member_1_is_valid` = """"foo\\nbar""""
    run_test(schema, `member_1_is_valid`, true, "DRAFT_4")
    val `member_2_is_valid` = """"foo\\rbar""""
    run_test(schema, `member_2_is_valid`, true, "DRAFT_4")
    val `another_string_is_invalid` = """"abc""""
    run_test(schema, `another_string_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun enum_with_false_does_not_match_0(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        false
        |    ]
        |}
        """.trimMargin()
    val `false_is_valid` = """false"""
    run_test(schema, `false_is_valid`, true, "DRAFT_4")
    val `integer_zero_is_invalid` = """0"""
    run_test(schema, `integer_zero_is_invalid`, false, "DRAFT_4")
    val `float_zero_is_invalid` = """0.0"""
    run_test(schema, `float_zero_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun enum_with_true_does_not_match_1(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        true
        |    ]
        |}
        """.trimMargin()
    val `true_is_valid` = """true"""
    run_test(schema, `true_is_valid`, true, "DRAFT_4")
    val `integer_one_is_invalid` = """1"""
    run_test(schema, `integer_one_is_invalid`, false, "DRAFT_4")
    val `float_one_is_invalid` = """1.0"""
    run_test(schema, `float_one_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun enum_with_0_does_not_match_false(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        0
        |    ]
        |}
        """.trimMargin()
    val `false_is_invalid` = """false"""
    run_test(schema, `false_is_invalid`, false, "DRAFT_4")
    val `integer_zero_is_valid` = """0"""
    run_test(schema, `integer_zero_is_valid`, true, "DRAFT_4")
    val `float_zero_is_valid` = """0.0"""
    run_test(schema, `float_zero_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun enum_with_1_does_not_match_true(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        1
        |    ]
        |}
        """.trimMargin()
    val `true_is_invalid` = """true"""
    run_test(schema, `true_is_invalid`, false, "DRAFT_4")
    val `integer_one_is_valid` = """1"""
    run_test(schema, `integer_one_is_valid`, true, "DRAFT_4")
    val `float_one_is_valid` = """1.0"""
    run_test(schema, `float_one_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun nul_characters_in_strings(): Unit {
    val schema = """
        |{
        |    "enum": [
        |        "hello\\u0000there"
        |    ]
        |}
        """.trimMargin()
    val `match_string_with_nul` = """"hello\\u0000there""""
    run_test(schema, `match_string_with_nul`, true, "DRAFT_4")
    val `do_not_match_string_lacking_nul` = """"hellothere""""
    run_test(schema, `do_not_match_string_lacking_nul`, false, "DRAFT_4")
  }
}
