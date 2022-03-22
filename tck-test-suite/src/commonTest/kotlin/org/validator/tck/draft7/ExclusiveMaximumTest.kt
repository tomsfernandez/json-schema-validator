package org.validator.tck.draft7

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class ExclusiveMaximumTest {
  @Test
  public fun exclusiveMaximum_validation(): Unit {
    val schema = """
        |{
        |    "exclusiveMaximum": 3.0
        |}
        """.trimMargin()
    val `below_the_exclusiveMaximum_is_valid` = """2.2"""
    run_test(schema, `below_the_exclusiveMaximum_is_valid`, true, "DRAFT_7")
    val `boundary_point_is_invalid` = """3.0"""
    run_test(schema, `boundary_point_is_invalid`, false, "DRAFT_7")
    val `above_the_exclusiveMaximum_is_invalid` = """3.5"""
    run_test(schema, `above_the_exclusiveMaximum_is_invalid`, false, "DRAFT_7")
    val `ignores_non_numbers` = """"x""""
    run_test(schema, `ignores_non_numbers`, true, "DRAFT_7")
  }
}
