package org.validator.tck.draft7

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class PropertyNamesTest {
  @Test
  public fun propertyNames_validation(): Unit {
    val schema = """
        |{
        |    "propertyNames": {
        |        "maxLength": 3
        |    }
        |}
        """.trimMargin()
    val `all_property_names_valid` = """
        |{
        |    "f": {
        |    },
        |    "foo": {
        |    }
        |}
        """.trimMargin()
    run_test(schema, `all_property_names_valid`, true, "DRAFT_7")
    val `some_property_names_invalid` = """
        |{
        |    "foo": {
        |    },
        |    "foobar": {
        |    }
        |}
        """.trimMargin()
    run_test(schema, `some_property_names_invalid`, false, "DRAFT_7")
    val `object_without_properties_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `object_without_properties_is_valid`, true, "DRAFT_7")
    val `ignores_arrays` = """
        |[
        |    1,
        |    2,
        |    3,
        |    4
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_7")
    val `ignores_strings` = """"foobar""""
    run_test(schema, `ignores_strings`, true, "DRAFT_7")
    val `ignores_other_non_objects` = """12"""
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_7")
  }

  @Test
  public fun propertyNames_validation_with_pattern(): Unit {
    val schema = """
        |{
        |    "propertyNames": {
        |        "pattern": "^a+${'$'}"
        |    }
        |}
        """.trimMargin()
    val `matching_property_names_valid` = """
        |{
        |    "a": {
        |    },
        |    "aa": {
        |    },
        |    "aaa": {
        |    }
        |}
        """.trimMargin()
    run_test(schema, `matching_property_names_valid`, true, "DRAFT_7")
    val `non_matching_property_name_is_invalid` = """
        |{
        |    "aaA": {
        |    }
        |}
        """.trimMargin()
    run_test(schema, `non_matching_property_name_is_invalid`, false, "DRAFT_7")
    val `object_without_properties_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `object_without_properties_is_valid`, true, "DRAFT_7")
  }

  @Test
  public fun propertyNames_with_boolean_schema_true(): Unit {
    val schema = """
        |{
        |    "propertyNames": true
        |}
        """.trimMargin()
    val `object_with_any_properties_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `object_with_any_properties_is_valid`, true, "DRAFT_7")
    val `empty_object_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_valid`, true, "DRAFT_7")
  }

  @Test
  public fun propertyNames_with_boolean_schema_false(): Unit {
    val schema = """
        |{
        |    "propertyNames": false
        |}
        """.trimMargin()
    val `object_with_any_properties_is_invalid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `object_with_any_properties_is_invalid`, false, "DRAFT_7")
    val `empty_object_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_valid`, true, "DRAFT_7")
  }
}
