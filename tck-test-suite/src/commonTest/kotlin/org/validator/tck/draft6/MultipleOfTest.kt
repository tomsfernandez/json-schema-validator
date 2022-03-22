package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MultipleOfTest {
  @Test
  public fun by_int(): Unit {
    val schema = """
        |{
        |    "multipleOf": 2
        |}
        """.trimMargin()
    val `int_by_int` = """10"""
    run_test(schema, `int_by_int`, true, "DRAFT_6")
    val `int_by_int_fail` = """7"""
    run_test(schema, `int_by_int_fail`, false, "DRAFT_6")
    val `ignores_non_numbers` = """"foo""""
    run_test(schema, `ignores_non_numbers`, true, "DRAFT_6")
  }

  @Test
  public fun by_number(): Unit {
    val schema = """
        |{
        |    "multipleOf": 1.5
        |}
        """.trimMargin()
    val `zero_is_multiple_of_anything` = """0"""
    run_test(schema, `zero_is_multiple_of_anything`, true, "DRAFT_6")
    val `4_5_is_multiple_of_1_5` = """4.5"""
    run_test(schema, `4_5_is_multiple_of_1_5`, true, "DRAFT_6")
    val `35_is_not_multiple_of_1_5` = """35"""
    run_test(schema, `35_is_not_multiple_of_1_5`, false, "DRAFT_6")
  }

  @Test
  public fun by_small_number(): Unit {
    val schema = """
        |{
        |    "multipleOf": 1.0E-4
        |}
        """.trimMargin()
    val `0_0075_is_multiple_of_0_0001` = """0.0075"""
    run_test(schema, `0_0075_is_multiple_of_0_0001`, true, "DRAFT_6")
    val `0_00751_is_not_multiple_of_0_0001` = """0.00751"""
    run_test(schema, `0_00751_is_not_multiple_of_0_0001`, false, "DRAFT_6")
  }

  @Test
  public fun invalid_instance_should_not_raise_error_when_float_division___inf(): Unit {
    val schema = """
        |{
        |    "type": "integer",
        |    "multipleOf": 0.123456789
        |}
        """.trimMargin()
    val `always_invalid__but_naive_implementations_may_raise_an_overflow_error` = """1.0E308"""
    run_test(schema, `always_invalid__but_naive_implementations_may_raise_an_overflow_error`, false,
        "DRAFT_6")
  }
}
