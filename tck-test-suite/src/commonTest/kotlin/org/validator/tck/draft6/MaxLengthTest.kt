package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MaxLengthTest {
  @Test
  public fun maxLength_validation(): Unit {
    val schema = """
        |{
        |    "maxLength": 2
        |}
        """.trimMargin()
    val `shorter_is_valid` = """"f""""
    run_test(schema, `shorter_is_valid`, true, "DRAFT_6")
    val `exact_length_is_valid` = """"fo""""
    run_test(schema, `exact_length_is_valid`, true, "DRAFT_6")
    val `too_long_is_invalid` = """"foo""""
    run_test(schema, `too_long_is_invalid`, false, "DRAFT_6")
    val `ignores_non_strings` = """100"""
    run_test(schema, `ignores_non_strings`, true, "DRAFT_6")
    val `two_supplementary_Unicode_code_points_is_long_enough` = """"💩💩""""
    run_test(schema, `two_supplementary_Unicode_code_points_is_long_enough`, true, "DRAFT_6")
  }
}
