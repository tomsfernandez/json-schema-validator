package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class ExclusiveMinimumTest {
  @Test
  public fun exclusiveMinimum_validation(): Unit {
    val schema = """
        |{
        |    "exclusiveMinimum": 1.1
        |}
        """.trimMargin()
    val `above_the_exclusiveMinimum_is_valid` = """1.2"""
    run_test(schema, `above_the_exclusiveMinimum_is_valid`, true, "DRAFT_2019_09")
    val `boundary_point_is_invalid` = """1.1"""
    run_test(schema, `boundary_point_is_invalid`, false, "DRAFT_2019_09")
    val `below_the_exclusiveMinimum_is_invalid` = """0.6"""
    run_test(schema, `below_the_exclusiveMinimum_is_invalid`, false, "DRAFT_2019_09")
    val `ignores_non_numbers` = """"x""""
    run_test(schema, `ignores_non_numbers`, true, "DRAFT_2019_09")
  }
}
