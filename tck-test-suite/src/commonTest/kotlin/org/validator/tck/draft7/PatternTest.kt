package org.validator.tck.draft7

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class PatternTest {
  @Test
  public fun pattern_validation(): Unit {
    val schema = """
        |{
        |    "pattern": "^a*${'$'}"
        |}
        """.trimMargin()
    val `a_matching_pattern_is_valid` = """"aaa""""
    run_test(schema, `a_matching_pattern_is_valid`, true, "DRAFT_7")
    val `a_non_matching_pattern_is_invalid` = """"abc""""
    run_test(schema, `a_non_matching_pattern_is_invalid`, false, "DRAFT_7")
    val `ignores_booleans` = """true"""
    run_test(schema, `ignores_booleans`, true, "DRAFT_7")
    val `ignores_integers` = """123"""
    run_test(schema, `ignores_integers`, true, "DRAFT_7")
    val `ignores_floats` = """1.0"""
    run_test(schema, `ignores_floats`, true, "DRAFT_7")
    val `ignores_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `ignores_objects`, true, "DRAFT_7")
    val `ignores_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_7")
    val `ignores_null` = """null"""
    run_test(schema, `ignores_null`, true, "DRAFT_7")
  }

  @Test
  public fun pattern_is_not_anchored(): Unit {
    val schema = """
        |{
        |    "pattern": "a+"
        |}
        """.trimMargin()
    val `matches_a_substring` = """"xxaayy""""
    run_test(schema, `matches_a_substring`, true, "DRAFT_7")
  }
}
