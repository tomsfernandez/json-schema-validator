package org.validator.tck.draft7

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MaximumTest {
  @Test
  public fun maximum_validation(): Unit {
    val schema = """
        |{
        |    "maximum": 3.0
        |}
        """.trimMargin()
    val `below_the_maximum_is_valid` = """2.6"""
    run_test(schema, `below_the_maximum_is_valid`, true, "DRAFT_7")
    val `boundary_point_is_valid` = """3.0"""
    run_test(schema, `boundary_point_is_valid`, true, "DRAFT_7")
    val `above_the_maximum_is_invalid` = """3.5"""
    run_test(schema, `above_the_maximum_is_invalid`, false, "DRAFT_7")
    val `ignores_non_numbers` = """"x""""
    run_test(schema, `ignores_non_numbers`, true, "DRAFT_7")
  }

  @Test
  public fun maximum_validation_with_unsigned_integer(): Unit {
    val schema = """
        |{
        |    "maximum": 300
        |}
        """.trimMargin()
    val `below_the_maximum_is_invalid` = """299.97"""
    run_test(schema, `below_the_maximum_is_invalid`, true, "DRAFT_7")
    val `boundary_point_integer_is_valid` = """300"""
    run_test(schema, `boundary_point_integer_is_valid`, true, "DRAFT_7")
    val `boundary_point_float_is_valid` = """300.0"""
    run_test(schema, `boundary_point_float_is_valid`, true, "DRAFT_7")
    val `above_the_maximum_is_invalid` = """300.5"""
    run_test(schema, `above_the_maximum_is_invalid`, false, "DRAFT_7")
  }
}
