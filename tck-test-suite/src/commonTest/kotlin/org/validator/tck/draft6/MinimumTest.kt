package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MinimumTest {
  @Test
  public fun minimum_validation(): Unit {
    val schema = """
        |{
        |    "minimum": 1.1
        |}
        """.trimMargin()
    val `above_the_minimum_is_valid` = """2.6"""
    run_test(schema, `above_the_minimum_is_valid`, true, "DRAFT_6")
    val `boundary_point_is_valid` = """1.1"""
    run_test(schema, `boundary_point_is_valid`, true, "DRAFT_6")
    val `below_the_minimum_is_invalid` = """0.6"""
    run_test(schema, `below_the_minimum_is_invalid`, false, "DRAFT_6")
    val `ignores_non_numbers` = """"x""""
    run_test(schema, `ignores_non_numbers`, true, "DRAFT_6")
  }

  @Test
  public fun minimum_validation_with_signed_integer(): Unit {
    val schema = """
        |{
        |    "minimum": -2
        |}
        """.trimMargin()
    val `negative_above_the_minimum_is_valid` = """-1"""
    run_test(schema, `negative_above_the_minimum_is_valid`, true, "DRAFT_6")
    val `positive_above_the_minimum_is_valid` = """0"""
    run_test(schema, `positive_above_the_minimum_is_valid`, true, "DRAFT_6")
    val `boundary_point_is_valid` = """-2"""
    run_test(schema, `boundary_point_is_valid`, true, "DRAFT_6")
    val `boundary_point_with_float_is_valid` = """-2.0"""
    run_test(schema, `boundary_point_with_float_is_valid`, true, "DRAFT_6")
    val `float_below_the_minimum_is_invalid` = """-2.0001"""
    run_test(schema, `float_below_the_minimum_is_invalid`, false, "DRAFT_6")
    val `int_below_the_minimum_is_invalid` = """-3"""
    run_test(schema, `int_below_the_minimum_is_invalid`, false, "DRAFT_6")
    val `ignores_non_numbers` = """"x""""
    run_test(schema, `ignores_non_numbers`, true, "DRAFT_6")
  }
}
