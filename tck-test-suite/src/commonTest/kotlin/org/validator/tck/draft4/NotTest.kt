package org.validator.tck.draft4

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
    run_test(schema, `allowed`, true, "DRAFT_4")
    val `disallowed` = """1"""
    run_test(schema, `disallowed`, false, "DRAFT_4")
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
    run_test(schema, `valid`, true, "DRAFT_4")
    val `mismatch` = """1"""
    run_test(schema, `mismatch`, false, "DRAFT_4")
    val `other_mismatch` = """true"""
    run_test(schema, `other_mismatch`, false, "DRAFT_4")
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
    run_test(schema, `match`, true, "DRAFT_4")
    val `other_match` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `other_match`, true, "DRAFT_4")
    val `mismatch` = """
        |{
        |    "foo": "bar"
        |}
        """.trimMargin()
    run_test(schema, `mismatch`, false, "DRAFT_4")
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
    run_test(schema, `property_present`, false, "DRAFT_4")
    val `property_absent` = """
        |{
        |    "bar": 1,
        |    "baz": 2
        |}
        """.trimMargin()
    run_test(schema, `property_absent`, true, "DRAFT_4")
  }
}
