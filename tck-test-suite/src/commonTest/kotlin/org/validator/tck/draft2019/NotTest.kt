package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class NotTest {
  @Test
  public fun not(): Unit {
    val schema = """
        |{
        |    "not": {
        |        "type": "integer"
        |    }
        |}
        """.trimMargin()
    val `allowed` = """"foo""""
    run_test(schema, `allowed`, true, "DRAFT_2019_09")
    val `disallowed` = """1"""
    run_test(schema, `disallowed`, false, "DRAFT_2019_09")
  }

  @Test
  public fun not_multiple_types(): Unit {
    val schema = """
        |{
        |    "not": {
        |        "type": [
        |            "integer",
        |            "boolean"
        |        ]
        |    }
        |}
        """.trimMargin()
    val `valid` = """"foo""""
    run_test(schema, `valid`, true, "DRAFT_2019_09")
    val `mismatch` = """1"""
    run_test(schema, `mismatch`, false, "DRAFT_2019_09")
    val `other_mismatch` = """true"""
    run_test(schema, `other_mismatch`, false, "DRAFT_2019_09")
  }

  @Test
  public fun not_more_complex_schema(): Unit {
    val schema = """
        |{
        |    "not": {
        |        "type": "object",
        |        "properties": {
        |            "foo": {
        |                "type": "string"
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `match` = """1"""
    run_test(schema, `match`, true, "DRAFT_2019_09")
    val `other_match` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `other_match`, true, "DRAFT_2019_09")
    val `mismatch` = """
        |{
        |    "foo": "bar"
        |}
        """.trimMargin()
    run_test(schema, `mismatch`, false, "DRAFT_2019_09")
  }

  @Test
  public fun forbidden_property(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |            "not": {
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `property_present` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `property_present`, false, "DRAFT_2019_09")
    val `property_absent` = """
        |{
        |    "bar": 1,
        |    "baz": 2
        |}
        """.trimMargin()
    run_test(schema, `property_absent`, true, "DRAFT_2019_09")
  }

  @Test
  public fun not_with_boolean_schema_true(): Unit {
    val schema = """
        |{
        |    "not": true
        |}
        """.trimMargin()
    val `any_value_is_invalid` = """"foo""""
    run_test(schema, `any_value_is_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun not_with_boolean_schema_false(): Unit {
    val schema = """
        |{
        |    "not": false
        |}
        """.trimMargin()
    val `any_value_is_valid` = """"foo""""
    run_test(schema, `any_value_is_valid`, true, "DRAFT_2019_09")
  }
}
