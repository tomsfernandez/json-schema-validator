package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class PatternTest {
  @Test
  public fun pattern_validation(): Unit {
    val schema = "{\"pattern\":\"^a*${'$'}\"}"
    val `a_matching_pattern_is_valid` = "\"aaa\""
    run_test(schema, `a_matching_pattern_is_valid`, true, "DRAFT_4")
    val `a_non_matching_pattern_is_invalid` = "\"abc\""
    run_test(schema, `a_non_matching_pattern_is_invalid`, false, "DRAFT_4")
    val `ignores_booleans` = "true"
    run_test(schema, `ignores_booleans`, true, "DRAFT_4")
    val `ignores_integers` = "123"
    run_test(schema, `ignores_integers`, true, "DRAFT_4")
    val `ignores_floats` = "1.0"
    run_test(schema, `ignores_floats`, true, "DRAFT_4")
    val `ignores_objects` = "{}"
    run_test(schema, `ignores_objects`, true, "DRAFT_4")
    val `ignores_arrays` = "[]"
    run_test(schema, `ignores_arrays`, true, "DRAFT_4")
    val `ignores_null` = "null"
    run_test(schema, `ignores_null`, true, "DRAFT_4")
  }

  @Test
  public fun pattern_is_not_anchored(): Unit {
    val schema = "{\"pattern\":\"a+\"}"
    val `matches_a_substring` = "\"xxaayy\""
    run_test(schema, `matches_a_substring`, true, "DRAFT_4")
  }
}
