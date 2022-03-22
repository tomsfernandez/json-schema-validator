package org.validator.tck.draft7

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class ConstTest {
  @Test
  public fun const_validation(): Unit {
    val schema = """
        |{
        |    "const": 2
        |}
        """.trimMargin()
    val `same_value_is_valid` = """2"""
    run_test(schema, `same_value_is_valid`, true, "DRAFT_7")
    val `another_value_is_invalid` = """5"""
    run_test(schema, `another_value_is_invalid`, false, "DRAFT_7")
    val `another_type_is_invalid` = """"a""""
    run_test(schema, `another_type_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun const_with_object(): Unit {
    val schema = """
        |{
        |    "const": {
        |        "foo": "bar",
        |        "baz": "bax"
        |    }
        |}
        """.trimMargin()
    val `same_object_is_valid` = """
        |{
        |    "foo": "bar",
        |    "baz": "bax"
        |}
        """.trimMargin()
    run_test(schema, `same_object_is_valid`, true, "DRAFT_7")
    val `same_object_with_different_property_order_is_valid` = """
        |{
        |    "baz": "bax",
        |    "foo": "bar"
        |}
        """.trimMargin()
    run_test(schema, `same_object_with_different_property_order_is_valid`, true, "DRAFT_7")
    val `another_object_is_invalid` = """
        |{
        |    "foo": "bar"
        |}
        """.trimMargin()
    run_test(schema, `another_object_is_invalid`, false, "DRAFT_7")
    val `another_type_is_invalid` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `another_type_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun const_with_array(): Unit {
    val schema = """
        |{
        |    "const": [
        |        {
        |            "foo": "bar"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `same_array_is_valid` = """
        |[
        |    {
        |        "foo": "bar"
        |    }
        |]
        """.trimMargin()
    run_test(schema, `same_array_is_valid`, true, "DRAFT_7")
    val `another_array_item_is_invalid` = """
        |[
        |    2
        |]
        """.trimMargin()
    run_test(schema, `another_array_item_is_invalid`, false, "DRAFT_7")
    val `array_with_additional_items_is_invalid` = """
        |[
        |    1,
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `array_with_additional_items_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun const_with_null(): Unit {
    val schema = """
        |{
        |    "const": null
        |}
        """.trimMargin()
    val `null_is_valid` = """null"""
    run_test(schema, `null_is_valid`, true, "DRAFT_7")
    val `not_null_is_invalid` = """0"""
    run_test(schema, `not_null_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun const_with_false_does_not_match_0(): Unit {
    val schema = """
        |{
        |    "const": false
        |}
        """.trimMargin()
    val `false_is_valid` = """false"""
    run_test(schema, `false_is_valid`, true, "DRAFT_7")
    val `integer_zero_is_invalid` = """0"""
    run_test(schema, `integer_zero_is_invalid`, false, "DRAFT_7")
    val `float_zero_is_invalid` = """0.0"""
    run_test(schema, `float_zero_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun const_with_true_does_not_match_1(): Unit {
    val schema = """
        |{
        |    "const": true
        |}
        """.trimMargin()
    val `true_is_valid` = """true"""
    run_test(schema, `true_is_valid`, true, "DRAFT_7")
    val `integer_one_is_invalid` = """1"""
    run_test(schema, `integer_one_is_invalid`, false, "DRAFT_7")
    val `float_one_is_invalid` = """1.0"""
    run_test(schema, `float_one_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun `const_with_{false}_does_not_match_{0}`(): Unit {
    val schema = """
        |{
        |    "const": [
        |        false
        |    ]
        |}
        """.trimMargin()
    val `{false}_is_valid` = """
        |[
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{false}_is_valid`, true, "DRAFT_7")
    val `{0}_is_invalid` = """
        |[
        |    0
        |]
        """.trimMargin()
    run_test(schema, `{0}_is_invalid`, false, "DRAFT_7")
    val `{0_0}_is_invalid` = """
        |[
        |    0.0
        |]
        """.trimMargin()
    run_test(schema, `{0_0}_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun `const_with_{true}_does_not_match_{1}`(): Unit {
    val schema = """
        |{
        |    "const": [
        |        true
        |    ]
        |}
        """.trimMargin()
    val `{true}_is_valid` = """
        |[
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{true}_is_valid`, true, "DRAFT_7")
    val `{1}_is_invalid` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `{1}_is_invalid`, false, "DRAFT_7")
    val `{1_0}_is_invalid` = """
        |[
        |    1.0
        |]
        """.trimMargin()
    run_test(schema, `{1_0}_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun `const_with_{"a"__false}_does_not_match_{"a"__0}`(): Unit {
    val schema = """
        |{
        |    "const": {
        |        "a": false
        |    }
        |}
        """.trimMargin()
    val `{"a"__false}_is_valid` = """
        |{
        |    "a": false
        |}
        """.trimMargin()
    run_test(schema, `{"a"__false}_is_valid`, true, "DRAFT_7")
    val `{"a"__0}_is_invalid` = """
        |{
        |    "a": 0
        |}
        """.trimMargin()
    run_test(schema, `{"a"__0}_is_invalid`, false, "DRAFT_7")
    val `{"a"__0_0}_is_invalid` = """
        |{
        |    "a": 0.0
        |}
        """.trimMargin()
    run_test(schema, `{"a"__0_0}_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun `const_with_{"a"__true}_does_not_match_{"a"__1}`(): Unit {
    val schema = """
        |{
        |    "const": {
        |        "a": true
        |    }
        |}
        """.trimMargin()
    val `{"a"__true}_is_valid` = """
        |{
        |    "a": true
        |}
        """.trimMargin()
    run_test(schema, `{"a"__true}_is_valid`, true, "DRAFT_7")
    val `{"a"__1}_is_invalid` = """
        |{
        |    "a": 1
        |}
        """.trimMargin()
    run_test(schema, `{"a"__1}_is_invalid`, false, "DRAFT_7")
    val `{"a"__1_0}_is_invalid` = """
        |{
        |    "a": 1.0
        |}
        """.trimMargin()
    run_test(schema, `{"a"__1_0}_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun const_with_0_does_not_match_other_zero_like_types(): Unit {
    val schema = """
        |{
        |    "const": 0
        |}
        """.trimMargin()
    val `false_is_invalid` = """false"""
    run_test(schema, `false_is_invalid`, false, "DRAFT_7")
    val `integer_zero_is_valid` = """0"""
    run_test(schema, `integer_zero_is_valid`, true, "DRAFT_7")
    val `float_zero_is_valid` = """0.0"""
    run_test(schema, `float_zero_is_valid`, true, "DRAFT_7")
    val `empty_object_is_invalid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_invalid`, false, "DRAFT_7")
    val `empty_array_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array_is_invalid`, false, "DRAFT_7")
    val `empty_string_is_invalid` = """"""""
    run_test(schema, `empty_string_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun const_with_1_does_not_match_true(): Unit {
    val schema = """
        |{
        |    "const": 1
        |}
        """.trimMargin()
    val `true_is_invalid` = """true"""
    run_test(schema, `true_is_invalid`, false, "DRAFT_7")
    val `integer_one_is_valid` = """1"""
    run_test(schema, `integer_one_is_valid`, true, "DRAFT_7")
    val `float_one_is_valid` = """1.0"""
    run_test(schema, `float_one_is_valid`, true, "DRAFT_7")
  }

  @Test
  public fun const_with__2_0_matches_integer_and_float_types(): Unit {
    val schema = """
        |{
        |    "const": -2.0
        |}
        """.trimMargin()
    val `integer__2_is_valid` = """-2"""
    run_test(schema, `integer__2_is_valid`, true, "DRAFT_7")
    val `integer_2_is_invalid` = """2"""
    run_test(schema, `integer_2_is_invalid`, false, "DRAFT_7")
    val `float__2_0_is_valid` = """-2.0"""
    run_test(schema, `float__2_0_is_valid`, true, "DRAFT_7")
    val `float_2_0_is_invalid` = """2.0"""
    run_test(schema, `float_2_0_is_invalid`, false, "DRAFT_7")
    val `float__2_00001_is_invalid` = """-2.00001"""
    run_test(schema, `float__2_00001_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun float_and_integers_are_equal_up_to_64_bit_representation_limits(): Unit {
    val schema = """
        |{
        |    "const": 9007199254740992
        |}
        """.trimMargin()
    val `integer_is_valid` = """9007199254740992"""
    run_test(schema, `integer_is_valid`, true, "DRAFT_7")
    val `integer_minus_one_is_invalid` = """9007199254740991"""
    run_test(schema, `integer_minus_one_is_invalid`, false, "DRAFT_7")
    val `float_is_valid` = """9.007199254740992E15"""
    run_test(schema, `float_is_valid`, true, "DRAFT_7")
    val `float_minus_one_is_invalid` = """9.007199254740991E15"""
    run_test(schema, `float_minus_one_is_invalid`, false, "DRAFT_7")
  }

  @Test
  public fun nul_characters_in_strings(): Unit {
    val schema = """
        |{
        |    "const": "hello\u0000there"
        |}
        """.trimMargin()
    val `match_string_with_nul` = """"hello\\u0000there""""
    run_test(schema, `match_string_with_nul`, true, "DRAFT_7")
    val `do_not_match_string_lacking_nul` = """"hellothere""""
    run_test(schema, `do_not_match_string_lacking_nul`, false, "DRAFT_7")
  }
}
