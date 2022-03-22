package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class PatternPropertiesTest {
  @Test
  public fun patternProperties_validates_properties_matching_a_regex(): Unit {
    val schema = """
        |{
        |    "patternProperties": {
        |        "f.*o": {
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    val `a_single_valid_match_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `a_single_valid_match_is_valid`, true, "DRAFT_6")
    val `multiple_valid_matches_is_valid` = """
        |{
        |    "foo": 1,
        |    "foooooo": 2
        |}
        """.trimMargin()
    run_test(schema, `multiple_valid_matches_is_valid`, true, "DRAFT_6")
    val `a_single_invalid_match_is_invalid` = """
        |{
        |    "foo": "bar",
        |    "fooooo": 2
        |}
        """.trimMargin()
    run_test(schema, `a_single_invalid_match_is_invalid`, false, "DRAFT_6")
    val `multiple_invalid_matches_is_invalid` = """
        |{
        |    "foo": "bar",
        |    "foooooo": "baz"
        |}
        """.trimMargin()
    run_test(schema, `multiple_invalid_matches_is_invalid`, false, "DRAFT_6")
    val `ignores_arrays` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_6")
    val `ignores_strings` = """"foo""""
    run_test(schema, `ignores_strings`, true, "DRAFT_6")
    val `ignores_other_non_objects` = """12"""
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_6")
  }

  @Test
  public fun multiple_simultaneous_patternProperties_are_validated(): Unit {
    val schema = """
        |{
        |    "patternProperties": {
        |        "a*": {
        |            "type": "integer"
        |        },
        |        "aaa*": {
        |            "maximum": 20
        |        }
        |    }
        |}
        """.trimMargin()
    val `a_single_valid_match_is_valid` = """
        |{
        |    "a": 21
        |}
        """.trimMargin()
    run_test(schema, `a_single_valid_match_is_valid`, true, "DRAFT_6")
    val `a_simultaneous_match_is_valid` = """
        |{
        |    "aaaa": 18
        |}
        """.trimMargin()
    run_test(schema, `a_simultaneous_match_is_valid`, true, "DRAFT_6")
    val `multiple_matches_is_valid` = """
        |{
        |    "a": 21,
        |    "aaaa": 18
        |}
        """.trimMargin()
    run_test(schema, `multiple_matches_is_valid`, true, "DRAFT_6")
    val `an_invalid_due_to_one_is_invalid` = """
        |{
        |    "a": "bar"
        |}
        """.trimMargin()
    run_test(schema, `an_invalid_due_to_one_is_invalid`, false, "DRAFT_6")
    val `an_invalid_due_to_the_other_is_invalid` = """
        |{
        |    "aaaa": 31
        |}
        """.trimMargin()
    run_test(schema, `an_invalid_due_to_the_other_is_invalid`, false, "DRAFT_6")
    val `an_invalid_due_to_both_is_invalid` = """
        |{
        |    "aaa": "foo",
        |    "aaaa": 31
        |}
        """.trimMargin()
    run_test(schema, `an_invalid_due_to_both_is_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun regexes_are_not_anchored_by_default_and_are_case_sensitive(): Unit {
    val schema = """
        |{
        |    "patternProperties": {
        |        "[0-9]{2,}": {
        |            "type": "boolean"
        |        },
        |        "X_": {
        |            "type": "string"
        |        }
        |    }
        |}
        """.trimMargin()
    val `non_recognized_members_are_ignored` = """
        |{
        |    "answer 1": "42"
        |}
        """.trimMargin()
    run_test(schema, `non_recognized_members_are_ignored`, true, "DRAFT_6")
    val `recognized_members_are_accounted_for` = """
        |{
        |    "a31b": null
        |}
        """.trimMargin()
    run_test(schema, `recognized_members_are_accounted_for`, false, "DRAFT_6")
    val `regexes_are_case_sensitive` = """
        |{
        |    "a_x_3": 3
        |}
        """.trimMargin()
    run_test(schema, `regexes_are_case_sensitive`, true, "DRAFT_6")
    val `regexes_are_case_sensitive__2` = """
        |{
        |    "a_X_3": 3
        |}
        """.trimMargin()
    run_test(schema, `regexes_are_case_sensitive__2`, false, "DRAFT_6")
  }

  @Test
  public fun patternProperties_with_boolean_schemas(): Unit {
    val schema = """
        |{
        |    "patternProperties": {
        |        "f.*": true,
        |        "b.*": false
        |    }
        |}
        """.trimMargin()
    val `object_with_property_matching_schema_true_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `object_with_property_matching_schema_true_is_valid`, true, "DRAFT_6")
    val `object_with_property_matching_schema_false_is_invalid` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_property_matching_schema_false_is_invalid`, false, "DRAFT_6")
    val `object_with_both_properties_is_invalid` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_both_properties_is_invalid`, false, "DRAFT_6")
    val `object_with_a_property_matching_both_true_and_false_is_invalid` = """
        |{
        |    "foobar": 1
        |}
        """.trimMargin()
    run_test(schema, `object_with_a_property_matching_both_true_and_false_is_invalid`, false,
        "DRAFT_6")
    val `empty_object_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_valid`, true, "DRAFT_6")
  }
}
