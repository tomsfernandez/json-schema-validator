package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DependentSchemasTest {
  @Test
  public fun single_dependency(): Unit {
    val schema = """
        |{
        |    "dependentSchemas": {
        |        "bar": {
        |            "properties": {
        |                "foo": {
        |                    "type": "integer"
        |                },
        |                "bar": {
        |                    "type": "integer"
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `valid` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `valid`, true, "DRAFT_2019_09")
    val `no_dependency` = """
        |{
        |    "foo": "quux"
        |}
        """.trimMargin()
    run_test(schema, `no_dependency`, true, "DRAFT_2019_09")
    val `wrong_type` = """
        |{
        |    "foo": "quux",
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `wrong_type`, false, "DRAFT_2019_09")
    val `wrong_type_other` = """
        |{
        |    "foo": 2,
        |    "bar": "quux"
        |}
        """.trimMargin()
    run_test(schema, `wrong_type_other`, false, "DRAFT_2019_09")
    val `wrong_type_both` = """
        |{
        |    "foo": "quux",
        |    "bar": "quux"
        |}
        """.trimMargin()
    run_test(schema, `wrong_type_both`, false, "DRAFT_2019_09")
    val `ignores_arrays` = """
        |[
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_2019_09")
    val `ignores_strings` = """"foobar""""
    run_test(schema, `ignores_strings`, true, "DRAFT_2019_09")
    val `ignores_other_non_objects` = """12"""
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_2019_09")
  }

  @Test
  public fun boolean_subschemas(): Unit {
    val schema = """
        |{
        |    "dependentSchemas": {
        |        "foo": true,
        |        "bar": false
        |    }
        |}
        """.trimMargin()
    val `object_with_property_having_schema_true_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `object_with_property_having_schema_true_is_valid`, true, "DRAFT_2019_09")
    val `object_with_property_having_schema_false_is_invalid` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_property_having_schema_false_is_invalid`, false, "DRAFT_2019_09")
    val `object_with_both_properties_is_invalid` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_both_properties_is_invalid`, false, "DRAFT_2019_09")
    val `empty_object_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_valid`, true, "DRAFT_2019_09")
  }

  @Test
  public fun dependencies_with_escaped_characters(): Unit {
    val schema = """
        |{
        |    "dependentSchemas": {
        |        "foo\tbar": {
        |            "minProperties": 4
        |        },
        |        "foo'bar": {
        |            "required": [
        |                "foo\"bar"
        |            ]
        |        }
        |    }
        |}
        """.trimMargin()
    val `quoted_tab` = """
        |{
        |    "foo\tbar": 1,
        |    "a": 2,
        |    "b": 3,
        |    "c": 4
        |}
        """.trimMargin()
    run_test(schema, `quoted_tab`, true, "DRAFT_2019_09")
    val `quoted_quote` = """
        |{
        |    "foo'bar": {
        |        "foo\"bar": 1
        |    }
        |}
        """.trimMargin()
    run_test(schema, `quoted_quote`, false, "DRAFT_2019_09")
    val `quoted_tab_invalid_under_dependent_schema` = """
        |{
        |    "foo\tbar": 1,
        |    "a": 2
        |}
        """.trimMargin()
    run_test(schema, `quoted_tab_invalid_under_dependent_schema`, false, "DRAFT_2019_09")
    val `quoted_quote_invalid_under_dependent_schema` = """
        |{
        |    "foo'bar": 1
        |}
        """.trimMargin()
    run_test(schema, `quoted_quote_invalid_under_dependent_schema`, false, "DRAFT_2019_09")
  }
}
