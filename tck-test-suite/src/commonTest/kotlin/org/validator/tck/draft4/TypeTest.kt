package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class TypeTest {
  @Test
  public fun integer_type_matches_integers(): Unit {
    val schema = """
        |{
        |    "type": "integer"
        |}
        """.trimMargin()
    val `an_integer_is_an_integer` = """1"""
    run_test(schema, `an_integer_is_an_integer`, true, "DRAFT_4")
    val `a_float_is_not_an_integer` = """1.1"""
    run_test(schema, `a_float_is_not_an_integer`, false, "DRAFT_4")
    val `a_string_is_not_an_integer` = """"foo""""
    run_test(schema, `a_string_is_not_an_integer`, false, "DRAFT_4")
    val `a_string_is_still_not_an_integer__even_if_it_looks_like_one` = """"1""""
    run_test(schema, `a_string_is_still_not_an_integer__even_if_it_looks_like_one`, false,
        "DRAFT_4")
    val `an_object_is_not_an_integer` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_not_an_integer`, false, "DRAFT_4")
    val `an_array_is_not_an_integer` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_not_an_integer`, false, "DRAFT_4")
    val `a_boolean_is_not_an_integer` = """true"""
    run_test(schema, `a_boolean_is_not_an_integer`, false, "DRAFT_4")
    val `null_is_not_an_integer` = """null"""
    run_test(schema, `null_is_not_an_integer`, false, "DRAFT_4")
  }

  @Test
  public fun number_type_matches_numbers(): Unit {
    val schema = """
        |{
        |    "type": "number"
        |}
        """.trimMargin()
    val `an_integer_is_a_number` = """1"""
    run_test(schema, `an_integer_is_a_number`, true, "DRAFT_4")
    val `a_float_with_zero_fractional_part_is_a_number` = """1.0"""
    run_test(schema, `a_float_with_zero_fractional_part_is_a_number`, true, "DRAFT_4")
    val `a_float_is_a_number` = """1.1"""
    run_test(schema, `a_float_is_a_number`, true, "DRAFT_4")
    val `a_string_is_not_a_number` = """"foo""""
    run_test(schema, `a_string_is_not_a_number`, false, "DRAFT_4")
    val `a_string_is_still_not_a_number__even_if_it_looks_like_one` = """"1""""
    run_test(schema, `a_string_is_still_not_a_number__even_if_it_looks_like_one`, false, "DRAFT_4")
    val `an_object_is_not_a_number` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_not_a_number`, false, "DRAFT_4")
    val `an_array_is_not_a_number` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_not_a_number`, false, "DRAFT_4")
    val `a_boolean_is_not_a_number` = """true"""
    run_test(schema, `a_boolean_is_not_a_number`, false, "DRAFT_4")
    val `null_is_not_a_number` = """null"""
    run_test(schema, `null_is_not_a_number`, false, "DRAFT_4")
  }

  @Test
  public fun string_type_matches_strings(): Unit {
    val schema = """
        |{
        |    "type": "string"
        |}
        """.trimMargin()
    val `1_is_not_a_string` = """1"""
    run_test(schema, `1_is_not_a_string`, false, "DRAFT_4")
    val `a_float_is_not_a_string` = """1.1"""
    run_test(schema, `a_float_is_not_a_string`, false, "DRAFT_4")
    val `a_string_is_a_string` = """"foo""""
    run_test(schema, `a_string_is_a_string`, true, "DRAFT_4")
    val `a_string_is_still_a_string__even_if_it_looks_like_a_number` = """"1""""
    run_test(schema, `a_string_is_still_a_string__even_if_it_looks_like_a_number`, true, "DRAFT_4")
    val `an_empty_string_is_still_a_string` = """"""""
    run_test(schema, `an_empty_string_is_still_a_string`, true, "DRAFT_4")
    val `an_object_is_not_a_string` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_not_a_string`, false, "DRAFT_4")
    val `an_array_is_not_a_string` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_not_a_string`, false, "DRAFT_4")
    val `a_boolean_is_not_a_string` = """true"""
    run_test(schema, `a_boolean_is_not_a_string`, false, "DRAFT_4")
    val `null_is_not_a_string` = """null"""
    run_test(schema, `null_is_not_a_string`, false, "DRAFT_4")
  }

  @Test
  public fun object_type_matches_objects(): Unit {
    val schema = """
        |{
        |    "type": "object"
        |}
        """.trimMargin()
    val `an_integer_is_not_an_object` = """1"""
    run_test(schema, `an_integer_is_not_an_object`, false, "DRAFT_4")
    val `a_float_is_not_an_object` = """1.1"""
    run_test(schema, `a_float_is_not_an_object`, false, "DRAFT_4")
    val `a_string_is_not_an_object` = """"foo""""
    run_test(schema, `a_string_is_not_an_object`, false, "DRAFT_4")
    val `an_object_is_an_object` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_an_object`, true, "DRAFT_4")
    val `an_array_is_not_an_object` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_not_an_object`, false, "DRAFT_4")
    val `a_boolean_is_not_an_object` = """true"""
    run_test(schema, `a_boolean_is_not_an_object`, false, "DRAFT_4")
    val `null_is_not_an_object` = """null"""
    run_test(schema, `null_is_not_an_object`, false, "DRAFT_4")
  }

  @Test
  public fun array_type_matches_arrays(): Unit {
    val schema = """
        |{
        |    "type": "array"
        |}
        """.trimMargin()
    val `an_integer_is_not_an_array` = """1"""
    run_test(schema, `an_integer_is_not_an_array`, false, "DRAFT_4")
    val `a_float_is_not_an_array` = """1.1"""
    run_test(schema, `a_float_is_not_an_array`, false, "DRAFT_4")
    val `a_string_is_not_an_array` = """"foo""""
    run_test(schema, `a_string_is_not_an_array`, false, "DRAFT_4")
    val `an_object_is_not_an_array` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_not_an_array`, false, "DRAFT_4")
    val `an_array_is_an_array` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_an_array`, true, "DRAFT_4")
    val `a_boolean_is_not_an_array` = """true"""
    run_test(schema, `a_boolean_is_not_an_array`, false, "DRAFT_4")
    val `null_is_not_an_array` = """null"""
    run_test(schema, `null_is_not_an_array`, false, "DRAFT_4")
  }

  @Test
  public fun boolean_type_matches_booleans(): Unit {
    val schema = """
        |{
        |    "type": "boolean"
        |}
        """.trimMargin()
    val `an_integer_is_not_a_boolean` = """1"""
    run_test(schema, `an_integer_is_not_a_boolean`, false, "DRAFT_4")
    val `zero_is_not_a_boolean` = """0"""
    run_test(schema, `zero_is_not_a_boolean`, false, "DRAFT_4")
    val `a_float_is_not_a_boolean` = """1.1"""
    run_test(schema, `a_float_is_not_a_boolean`, false, "DRAFT_4")
    val `a_string_is_not_a_boolean` = """"foo""""
    run_test(schema, `a_string_is_not_a_boolean`, false, "DRAFT_4")
    val `an_empty_string_is_not_a_boolean` = """"""""
    run_test(schema, `an_empty_string_is_not_a_boolean`, false, "DRAFT_4")
    val `an_object_is_not_a_boolean` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_not_a_boolean`, false, "DRAFT_4")
    val `an_array_is_not_a_boolean` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_not_a_boolean`, false, "DRAFT_4")
    val `true_is_a_boolean` = """true"""
    run_test(schema, `true_is_a_boolean`, true, "DRAFT_4")
    val `false_is_a_boolean` = """false"""
    run_test(schema, `false_is_a_boolean`, true, "DRAFT_4")
    val `null_is_not_a_boolean` = """null"""
    run_test(schema, `null_is_not_a_boolean`, false, "DRAFT_4")
  }

  @Test
  public fun null_type_matches_only_the_null_object(): Unit {
    val schema = """
        |{
        |    "type": "null"
        |}
        """.trimMargin()
    val `an_integer_is_not_null` = """1"""
    run_test(schema, `an_integer_is_not_null`, false, "DRAFT_4")
    val `a_float_is_not_null` = """1.1"""
    run_test(schema, `a_float_is_not_null`, false, "DRAFT_4")
    val `zero_is_not_null` = """0"""
    run_test(schema, `zero_is_not_null`, false, "DRAFT_4")
    val `a_string_is_not_null` = """"foo""""
    run_test(schema, `a_string_is_not_null`, false, "DRAFT_4")
    val `an_empty_string_is_not_null` = """"""""
    run_test(schema, `an_empty_string_is_not_null`, false, "DRAFT_4")
    val `an_object_is_not_null` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_not_null`, false, "DRAFT_4")
    val `an_array_is_not_null` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_not_null`, false, "DRAFT_4")
    val `true_is_not_null` = """true"""
    run_test(schema, `true_is_not_null`, false, "DRAFT_4")
    val `false_is_not_null` = """false"""
    run_test(schema, `false_is_not_null`, false, "DRAFT_4")
    val `null_is_null` = """null"""
    run_test(schema, `null_is_null`, true, "DRAFT_4")
  }

  @Test
  public fun multiple_types_can_be_specified_in_an_array(): Unit {
    val schema = """
        |{
        |    "type": [
        |        "integer",
        |        "string"
        |    ]
        |}
        """.trimMargin()
    val `an_integer_is_valid` = """1"""
    run_test(schema, `an_integer_is_valid`, true, "DRAFT_4")
    val `a_string_is_valid` = """"foo""""
    run_test(schema, `a_string_is_valid`, true, "DRAFT_4")
    val `a_float_is_invalid` = """1.1"""
    run_test(schema, `a_float_is_invalid`, false, "DRAFT_4")
    val `an_object_is_invalid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `an_object_is_invalid`, false, "DRAFT_4")
    val `an_array_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `an_array_is_invalid`, false, "DRAFT_4")
    val `a_boolean_is_invalid` = """true"""
    run_test(schema, `a_boolean_is_invalid`, false, "DRAFT_4")
    val `null_is_invalid` = """null"""
    run_test(schema, `null_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun type_as_array_with_one_item(): Unit {
    val schema = """
        |{
        |    "type": [
        |        "string"
        |    ]
        |}
        """.trimMargin()
    val `string_is_valid` = """"foo""""
    run_test(schema, `string_is_valid`, true, "DRAFT_4")
    val `number_is_invalid` = """123"""
    run_test(schema, `number_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun type__array_or_object(): Unit {
    val schema = """
        |{
        |    "type": [
        |        "array",
        |        "object"
        |    ]
        |}
        """.trimMargin()
    val `array_is_valid` = """
        |[
        |    1,
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `array_is_valid`, true, "DRAFT_4")
    val `object_is_valid` = """
        |{
        |    "foo": 123
        |}
        """.trimMargin()
    run_test(schema, `object_is_valid`, true, "DRAFT_4")
    val `number_is_invalid` = """123"""
    run_test(schema, `number_is_invalid`, false, "DRAFT_4")
    val `string_is_invalid` = """"foo""""
    run_test(schema, `string_is_invalid`, false, "DRAFT_4")
    val `null_is_invalid` = """null"""
    run_test(schema, `null_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun type__array__object_or_null(): Unit {
    val schema = """
        |{
        |    "type": [
        |        "array",
        |        "object",
        |        "null"
        |    ]
        |}
        """.trimMargin()
    val `array_is_valid` = """
        |[
        |    1,
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `array_is_valid`, true, "DRAFT_4")
    val `object_is_valid` = """
        |{
        |    "foo": 123
        |}
        """.trimMargin()
    run_test(schema, `object_is_valid`, true, "DRAFT_4")
    val `null_is_valid` = """null"""
    run_test(schema, `null_is_valid`, true, "DRAFT_4")
    val `number_is_invalid` = """123"""
    run_test(schema, `number_is_invalid`, false, "DRAFT_4")
    val `string_is_invalid` = """"foo""""
    run_test(schema, `string_is_invalid`, false, "DRAFT_4")
  }
}
