package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MinLengthTest {
  @Test
  public fun minLength_validation(): Unit {
    val schema = """
        |{
        |    "minLength": 2
        |}
        """.trimMargin()
    val `longer_is_valid` = """"foo""""
    run_test(schema, `longer_is_valid`, true, "DRAFT_4")
    val `exact_length_is_valid` = """"fo""""
    run_test(schema, `exact_length_is_valid`, true, "DRAFT_4")
    val `too_short_is_invalid` = """"f""""
    run_test(schema, `too_short_is_invalid`, false, "DRAFT_4")
    val `ignores_non_strings` = """1"""
    run_test(schema, `ignores_non_strings`, true, "DRAFT_4")
    val `one_supplementary_Unicode_code_point_is_not_long_enough` = """"💩""""
    run_test(schema, `one_supplementary_Unicode_code_point_is_not_long_enough`, false, "DRAFT_4")
  }
}
