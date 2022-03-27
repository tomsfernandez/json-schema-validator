package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class RequiredTest {
  @Test
  public fun required_validation(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |        },
        |        "bar": {
        |        }
        |    },
        |    "required": [
        |        "foo"
        |    ]
        |}
        """.trimMargin()
    val `present_required_property_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `present_required_property_is_valid`, true, "DRAFT_2019_09")
    val `non_present_required_property_is_invalid` = """
        |{
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `non_present_required_property_is_invalid`, false, "DRAFT_2019_09")
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

  @Test
  public fun required_default_validation(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |        }
        |    }
        |}
        """.trimMargin()
    val `not_required_by_default` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `not_required_by_default`, true, "DRAFT_2019_09")
  }

  @Test
  public fun required_with_empty_array(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |        }
        |    },
        |    "required": [
        |    ]
        |}
        """.trimMargin()
    val `property_not_required` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `property_not_required`, true, "DRAFT_2019_09")
  }

  @Test
  public fun required_with_escaped_characters(): Unit {
    val schema = """
        |{
        |    "required": [
        |        "foo\nbar",
        |        "foo\"bar",
        |        "foo\\bar",
        |        "foo\rbar",
        |        "foo\tbar",
        |        "foo\fbar"
        |    ]
        |}
        """.trimMargin()
    val `object_with_all_properties_present_is_valid` = """
        |{
        |    "foo\nbar": 1,
        |    "foo\"bar": 1,
        |    "foo\\bar": 1,
        |    "foo\rbar": 1,
        |    "foo\tbar": 1,
        |    "foo\fbar": 1
        |}
        """.trimMargin()
    run_test(schema, `object_with_all_properties_present_is_valid`, true, "DRAFT_2019_09")
    val `object_with_some_properties_missing_is_invalid` = """
        |{
        |    "foo\nbar": "1",
        |    "foo\"bar": "1"
        |}
        """.trimMargin()
    run_test(schema, `object_with_some_properties_missing_is_invalid`, false, "DRAFT_2019_09")
  }
}
