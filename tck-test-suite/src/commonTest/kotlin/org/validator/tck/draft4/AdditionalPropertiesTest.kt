package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class AdditionalPropertiesTest {
  @Test
  public fun additionalProperties_being_false_does_not_allow_other_properties(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |        },
        |        "bar": {
        |        }
        |    },
        |    "patternProperties": {
        |        "^v": {
        |        }
        |    },
        |    "additionalProperties": false
        |}
        """.trimMargin()
    val `no_additional_properties_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `no_additional_properties_is_valid`, true, "DRAFT_4")
    val `an_additional_property_is_invalid` = """
        |{
        |    "foo": 1,
        |    "bar": 2,
        |    "quux": "boom"
        |}
        """.trimMargin()
    run_test(schema, `an_additional_property_is_invalid`, false, "DRAFT_4")
    val `ignores_arrays` = """
        |[
        |    1,
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_4")
    val `ignores_strings` = """"foobarbaz""""
    run_test(schema, `ignores_strings`, true, "DRAFT_4")
    val `ignores_other_non_objects` = """12"""
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_4")
    val `patternProperties_are_not_additional_properties` = """
        |{
        |    "foo": 1,
        |    "vroom": 2
        |}
        """.trimMargin()
    run_test(schema, `patternProperties_are_not_additional_properties`, true, "DRAFT_4")
  }

  @Test
  public fun non_ASCII_pattern_with_additionalProperties(): Unit {
    val schema = """
        |{
        |    "patternProperties": {
        |        "^á": {
        |        }
        |    },
        |    "additionalProperties": false
        |}
        """.trimMargin()
    val `matching_the_pattern_is_valid` = """
        |{
        |    "ármányos": 2
        |}
        """.trimMargin()
    run_test(schema, `matching_the_pattern_is_valid`, true, "DRAFT_4")
    val `not_matching_the_pattern_is_invalid` = """
        |{
        |    "élmény": 2
        |}
        """.trimMargin()
    run_test(schema, `not_matching_the_pattern_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun additionalProperties_allows_a_schema_which_should_validate(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |        },
        |        "bar": {
        |        }
        |    },
        |    "additionalProperties": {
        |        "type": "boolean"
        |    }
        |}
        """.trimMargin()
    val `no_additional_properties_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `no_additional_properties_is_valid`, true, "DRAFT_4")
    val `an_additional_valid_property_is_valid` = """
        |{
        |    "foo": 1,
        |    "bar": 2,
        |    "quux": true
        |}
        """.trimMargin()
    run_test(schema, `an_additional_valid_property_is_valid`, true, "DRAFT_4")
    val `an_additional_invalid_property_is_invalid` = """
        |{
        |    "foo": 1,
        |    "bar": 2,
        |    "quux": 12
        |}
        """.trimMargin()
    run_test(schema, `an_additional_invalid_property_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun additionalProperties_can_exist_by_itself(): Unit {
    val schema = """
        |{
        |    "additionalProperties": {
        |        "type": "boolean"
        |    }
        |}
        """.trimMargin()
    val `an_additional_valid_property_is_valid` = """
        |{
        |    "foo": true
        |}
        """.trimMargin()
    run_test(schema, `an_additional_valid_property_is_valid`, true, "DRAFT_4")
    val `an_additional_invalid_property_is_invalid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `an_additional_invalid_property_is_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun additionalProperties_are_allowed_by_default(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |        },
        |        "bar": {
        |        }
        |    }
        |}
        """.trimMargin()
    val `additional_properties_are_allowed` = """
        |{
        |    "foo": 1,
        |    "bar": 2,
        |    "quux": true
        |}
        """.trimMargin()
    run_test(schema, `additional_properties_are_allowed`, true, "DRAFT_4")
  }

  @Test
  public fun additionalProperties_should_not_look_in_applicators(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                }
        |            }
        |        }
        |    ],
        |    "additionalProperties": {
        |        "type": "boolean"
        |    }
        |}
        """.trimMargin()
    val `properties_defined_in_allOf_are_not_examined` = """
        |{
        |    "foo": 1,
        |    "bar": true
        |}
        """.trimMargin()
    run_test(schema, `properties_defined_in_allOf_are_not_examined`, false, "DRAFT_4")
  }
}
