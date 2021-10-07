package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class PatternPropertiesTest {
  @Test
  public fun patternProperties_validates_properties_matching_a_regex(): Unit {
    val schema = "{\"patternProperties\":{\"f.*o\":{\"type\":\"integer\"}}}"
    val `a_single_valid_match_is_valid` = "{\"foo\":1}"
    run_test(schema, `a_single_valid_match_is_valid`, true, "DRAFT_4")
    val `multiple_valid_matches_is_valid` = "{\"foo\":1,\"foooooo\":2}"
    run_test(schema, `multiple_valid_matches_is_valid`, true, "DRAFT_4")
    val `a_single_invalid_match_is_invalid` = "{\"foo\":\"bar\",\"fooooo\":2}"
    run_test(schema, `a_single_invalid_match_is_invalid`, false, "DRAFT_4")
    val `multiple_invalid_matches_is_invalid` = "{\"foo\":\"bar\",\"foooooo\":\"baz\"}"
    run_test(schema, `multiple_invalid_matches_is_invalid`, false, "DRAFT_4")
    val `ignores_arrays` = "[]"
    run_test(schema, `ignores_arrays`, true, "DRAFT_4")
    val `ignores_strings` = "\"\""
    run_test(schema, `ignores_strings`, true, "DRAFT_4")
    val `ignores_other_non_objects` = "12"
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_4")
  }

  @Test
  public fun multiple_simultaneous_patternProperties_are_validated(): Unit {
    val schema = "{\"patternProperties\":{\"a*\":{\"type\":\"integer\"},\"aaa*\":{\"maximum\":20}}}"
    val `a_single_valid_match_is_valid` = "{\"a\":21}"
    run_test(schema, `a_single_valid_match_is_valid`, true, "DRAFT_4")
    val `a_simultaneous_match_is_valid` = "{\"aaaa\":18}"
    run_test(schema, `a_simultaneous_match_is_valid`, true, "DRAFT_4")
    val `multiple_matches_is_valid` = "{\"a\":21,\"aaaa\":18}"
    run_test(schema, `multiple_matches_is_valid`, true, "DRAFT_4")
    val `an_invalid_due_to_one_is_invalid` = "{\"a\":\"bar\"}"
    run_test(schema, `an_invalid_due_to_one_is_invalid`, false, "DRAFT_4")
    val `an_invalid_due_to_the_other_is_invalid` = "{\"aaaa\":31}"
    run_test(schema, `an_invalid_due_to_the_other_is_invalid`, false, "DRAFT_4")
    val `an_invalid_due_to_both_is_invalid` = "{\"aaa\":\"foo\",\"aaaa\":31}"
    run_test(schema, `an_invalid_due_to_both_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun regexes_are_not_anchored_by_default_and_are_case_sensitive(): Unit {
    val schema =
        "{\"patternProperties\":{\"[0-9]{2,}\":{\"type\":\"boolean\"},\"X_\":{\"type\":\"string\"}}}"
    val `non_recognized_members_are_ignored` = "{\"answer 1\":\"42\"}"
    run_test(schema, `non_recognized_members_are_ignored`, true, "DRAFT_4")
    val `recognized_members_are_accounted_for` = "{\"a31b\":null}"
    run_test(schema, `recognized_members_are_accounted_for`, false, "DRAFT_4")
    val `regexes_are_case_sensitive` = "{\"a_x_3\":3}"
    run_test(schema, `regexes_are_case_sensitive`, true, "DRAFT_4")
    val `regexes_are_case_sensitive__2` = "{\"a_X_3\":3}"
    run_test(schema, `regexes_are_case_sensitive__2`, false, "DRAFT_4")
  }
}
