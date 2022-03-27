package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DependentRequiredTest {
  @Test
  public fun single_dependency(): Unit {
    val schema = """
        |{
        |    "dependentRequired": {
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
    run_test(schema, `neither`, true, "DRAFT_2019_09")
    val `nondependant` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `nondependant`, true, "DRAFT_2019_09")
    val `with_dependency` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `with_dependency`, true, "DRAFT_2019_09")
    val `missing_dependency` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `missing_dependency`, false, "DRAFT_2019_09")
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
  public fun empty_dependents(): Unit {
    val schema = """
        |{
        |    "dependentRequired": {
        |        "bar": [
        |        ]
        |    }
        |}
        """.trimMargin()
    val `empty_object` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object`, true, "DRAFT_2019_09")
    val `object_with_one_property` = """
        |{
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `object_with_one_property`, true, "DRAFT_2019_09")
    val `non_object_is_valid` = """1"""
    run_test(schema, `non_object_is_valid`, true, "DRAFT_2019_09")
  }

  @Test
  public fun multiple_dependents_required(): Unit {
    val schema = """
        |{
        |    "dependentRequired": {
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
    run_test(schema, `neither`, true, "DRAFT_2019_09")
    val `nondependants` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `nondependants`, true, "DRAFT_2019_09")
    val `with_dependencies` = """
        |{
        |    "foo": 1,
        |    "bar": 2,
        |    "quux": 3
        |}
        """.trimMargin()
    run_test(schema, `with_dependencies`, true, "DRAFT_2019_09")
    val `missing_dependency` = """
        |{
        |    "foo": 1,
        |    "quux": 2
        |}
        """.trimMargin()
    run_test(schema, `missing_dependency`, false, "DRAFT_2019_09")
    val `missing_other_dependency` = """
        |{
        |    "bar": 1,
        |    "quux": 2
        |}
        """.trimMargin()
    run_test(schema, `missing_other_dependency`, false, "DRAFT_2019_09")
    val `missing_both_dependencies` = """
        |{
        |    "quux": 1
        |}
        """.trimMargin()
    run_test(schema, `missing_both_dependencies`, false, "DRAFT_2019_09")
  }

  @Test
  public fun dependencies_with_escaped_characters(): Unit {
    val schema = """
        |{
        |    "dependentRequired": {
        |        "foo\nbar": [
        |            "foo\rbar"
        |        ],
        |        "foo\"bar": [
        |            "foo'bar"
        |        ]
        |    }
        |}
        """.trimMargin()
    val `CRLF` = """
        |{
        |    "foo\nbar": 1,
        |    "foo\rbar": 2
        |}
        """.trimMargin()
    run_test(schema, `CRLF`, true, "DRAFT_2019_09")
    val `quoted_quotes` = """
        |{
        |    "foo'bar": 1,
        |    "foo\"bar": 2
        |}
        """.trimMargin()
    run_test(schema, `quoted_quotes`, true, "DRAFT_2019_09")
    val `CRLF_missing_dependent` = """
        |{
        |    "foo\nbar": 1,
        |    "foo": 2
        |}
        """.trimMargin()
    run_test(schema, `CRLF_missing_dependent`, false, "DRAFT_2019_09")
    val `quoted_quotes_missing_dependent` = """
        |{
        |    "foo\"bar": 2
        |}
        """.trimMargin()
    run_test(schema, `quoted_quotes_missing_dependent`, false, "DRAFT_2019_09")
  }
}
