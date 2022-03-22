package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DependenciesTest {
  @Test
  public fun dependencies(): Unit {
    val schema = """
        |{
        |    "dependencies": {
        |        "bar": [
        |            "foo"
        |        ]
        |    }
        |}
        """.trimMargin()
    val `neither` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `neither`, true, "DRAFT_6")
    val `nondependant` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `nondependant`, true, "DRAFT_6")
    val `with_dependency` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `with_dependency`, true, "DRAFT_6")
    val `missing_dependency` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `missing_dependency`, false, "DRAFT_6")
    val `ignores_arrays` = """
        |[
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_6")
    val `ignores_strings` = """"foobar""""
    run_test(schema, `ignores_strings`, true, "DRAFT_6")
    val `ignores_other_non_objects` = """12"""
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_6")
  }

  @Test
  public fun dependencies_with_empty_array(): Unit {
    val schema = """
        |{
        |    "dependencies": {
        |        "bar": [
        |        ]
        |    }
        |}
        """.trimMargin()
    val `empty_object` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object`, true, "DRAFT_6")
    val `object_with_one_property` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_one_property`, true, "DRAFT_6")
    val `non_object_is_valid` = """1"""
    run_test(schema, `non_object_is_valid`, true, "DRAFT_6")
  }

  @Test
  public fun multiple_dependencies(): Unit {
    val schema = """
        |{
        |    "dependencies": {
        |        "quux": [
        |            "foo",
        |            "bar"
        |        ]
        |    }
        |}
        """.trimMargin()
    val `neither` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `neither`, true, "DRAFT_6")
    val `nondependants` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `nondependants`, true, "DRAFT_6")
    val `with_dependencies` = """
        |{
        |    "foo": 1,
        |    "bar": 2,
        |    "quux": 3
        |}
        """.trimMargin()
    run_test(schema, `with_dependencies`, true, "DRAFT_6")
    val `missing_dependency` = """
        |{
        |    "foo": 1,
        |    "quux": 2
        |}
        """.trimMargin()
    run_test(schema, `missing_dependency`, false, "DRAFT_6")
    val `missing_other_dependency` = """
        |{
        |    "bar": 1,
        |    "quux": 2
        |}
        """.trimMargin()
    run_test(schema, `missing_other_dependency`, false, "DRAFT_6")
    val `missing_both_dependencies` = """
        |{
        |    "quux": 1
        |}
        """.trimMargin()
    run_test(schema, `missing_both_dependencies`, false, "DRAFT_6")
  }

  @Test
  public fun multiple_dependencies_subschema(): Unit {
    val schema = """
        |{
        |    "dependencies": {
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
    run_test(schema, `valid`, true, "DRAFT_6")
    val `no_dependency` = """
        |{
        |    "foo": "quux"
        |}
        """.trimMargin()
    run_test(schema, `no_dependency`, true, "DRAFT_6")
    val `wrong_type` = """
        |{
        |    "foo": "quux",
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `wrong_type`, false, "DRAFT_6")
    val `wrong_type_other` = """
        |{
        |    "foo": 2,
        |    "bar": "quux"
        |}
        """.trimMargin()
    run_test(schema, `wrong_type_other`, false, "DRAFT_6")
    val `wrong_type_both` = """
        |{
        |    "foo": "quux",
        |    "bar": "quux"
        |}
        """.trimMargin()
    run_test(schema, `wrong_type_both`, false, "DRAFT_6")
  }

  @Test
  public fun dependencies_with_boolean_subschemas(): Unit {
    val schema = """
        |{
        |    "dependencies": {
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
    run_test(schema, `object_with_property_having_schema_true_is_valid`, true, "DRAFT_6")
    val `object_with_property_having_schema_false_is_invalid` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_property_having_schema_false_is_invalid`, false, "DRAFT_6")
    val `object_with_both_properties_is_invalid` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_both_properties_is_invalid`, false, "DRAFT_6")
    val `empty_object_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_valid`, true, "DRAFT_6")
  }

  @Test
  public fun dependencies_with_escaped_characters(): Unit {
    val schema = """
        |{
        |    "dependencies": {
        |        "foo\nbar": [
        |            "foo\rbar"
        |        ],
        |        "foo\tbar": {
        |            "minProperties": 4
        |        },
        |        "foo'bar": {
        |            "required": [
        |                "foo\"bar"
        |            ]
        |        },
        |        "foo\"bar": [
        |            "foo'bar"
        |        ]
        |    }
        |}
        """.trimMargin()
    val `valid_object_1` = """
        |{
        |    "foo\nbar": 1,
        |    "foo\rbar": 2
        |}
        """.trimMargin()
    run_test(schema, `valid_object_1`, true, "DRAFT_6")
    val `valid_object_2` = """
        |{
        |    "foo\tbar": 1,
        |    "a": 2,
        |    "b": 3,
        |    "c": 4
        |}
        """.trimMargin()
    run_test(schema, `valid_object_2`, true, "DRAFT_6")
    val `valid_object_3` = """
        |{
        |    "foo'bar": 1,
        |    "foo\"bar": 2
        |}
        """.trimMargin()
    run_test(schema, `valid_object_3`, true, "DRAFT_6")
    val `invalid_object_1` = """
        |{
        |    "foo\nbar": 1,
        |    "foo": 2
        |}
        """.trimMargin()
    run_test(schema, `invalid_object_1`, false, "DRAFT_6")
    val `invalid_object_2` = """
        |{
        |    "foo\tbar": 1,
        |    "a": 2
        |}
        """.trimMargin()
    run_test(schema, `invalid_object_2`, false, "DRAFT_6")
    val `invalid_object_3` = """
        |{
        |    "foo'bar": 1
        |}
        """.trimMargin()
    run_test(schema, `invalid_object_3`, false, "DRAFT_6")
    val `invalid_object_4` = """
        |{
        |    "foo\"bar": 2
        |}
        """.trimMargin()
    run_test(schema, `invalid_object_4`, false, "DRAFT_6")
  }
}
