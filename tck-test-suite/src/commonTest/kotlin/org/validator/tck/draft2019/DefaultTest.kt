package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DefaultTest {
  @Test
  public fun invalid_type_for_default(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |            "type": "integer",
        |            "default": [
        |            ]
        |        }
        |    }
        |}
        """.trimMargin()
    val `valid_when_property_is_specified` = """
        |{
        |    "foo": 13
        |}
        """.trimMargin()
    run_test(schema, `valid_when_property_is_specified`, true, "DRAFT_2019_09")
    val `still_valid_when_the_invalid_default_is_used` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `still_valid_when_the_invalid_default_is_used`, true, "DRAFT_2019_09")
  }

  @Test
  public fun invalid_string_value_for_default(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "bar": {
        |            "type": "string",
        |            "minLength": 4,
        |            "default": "bad"
        |        }
        |    }
        |}
        """.trimMargin()
    val `valid_when_property_is_specified` = """
        |{
        |    "bar": "good"
        |}
        """.trimMargin()
    run_test(schema, `valid_when_property_is_specified`, true, "DRAFT_2019_09")
    val `still_valid_when_the_invalid_default_is_used` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `still_valid_when_the_invalid_default_is_used`, true, "DRAFT_2019_09")
  }

  @Test
  public fun the_default_keyword_does_not_do_anything_if_the_property_is_missing(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "alpha": {
        |            "type": "number",
        |            "maximum": 3,
        |            "default": 5
        |        }
        |    }
        |}
        """.trimMargin()
    val `an_explicit_property_value_is_checked_against_maximum_passing` = """
        |{
        |    "alpha": 1
        |}
        """.trimMargin()
    run_test(schema, `an_explicit_property_value_is_checked_against_maximum_passing`, true,
        "DRAFT_2019_09")
    val `an_explicit_property_value_is_checked_against_maximum_failing` = """
        |{
        |    "alpha": 5
        |}
        """.trimMargin()
    run_test(schema, `an_explicit_property_value_is_checked_against_maximum_failing`, false,
        "DRAFT_2019_09")
    val `missing_properties_are_not_filled_in_with_the_default` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `missing_properties_are_not_filled_in_with_the_default`, true, "DRAFT_2019_09")
  }
}
