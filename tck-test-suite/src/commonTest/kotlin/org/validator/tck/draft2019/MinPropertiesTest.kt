package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MinPropertiesTest {
  @Test
  public fun minProperties_validation(): Unit {
    val schema = """
        |{
        |    "minProperties": 1
        |}
        """.trimMargin()
    val `longer_is_valid` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `longer_is_valid`, true, "DRAFT_2019_09")
    val `exact_length_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `exact_length_is_valid`, true, "DRAFT_2019_09")
    val `too_short_is_invalid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `too_short_is_invalid`, false, "DRAFT_2019_09")
    val `ignores_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_2019_09")
    val `ignores_strings` = """"""""
    run_test(schema, `ignores_strings`, true, "DRAFT_2019_09")
    val `ignores_other_non_objects` = """12"""
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_2019_09")
  }
}
