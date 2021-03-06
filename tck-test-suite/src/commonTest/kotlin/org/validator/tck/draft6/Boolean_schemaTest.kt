package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class Boolean_schemaTest {
  @Test
  public fun `boolean_schema_'true'`(): Unit {
    val schema = """true"""
    val `number_is_valid` = """1"""
    run_test(schema, `number_is_valid`, true, "DRAFT_6")
    val `string_is_valid` = """"foo""""
    run_test(schema, `string_is_valid`, true, "DRAFT_6")
    val `boolean_true_is_valid` = """true"""
    run_test(schema, `boolean_true_is_valid`, true, "DRAFT_6")
    val `boolean_false_is_valid` = """false"""
    run_test(schema, `boolean_false_is_valid`, true, "DRAFT_6")
    val `null_is_valid` = """null"""
    run_test(schema, `null_is_valid`, true, "DRAFT_6")
    val `object_is_valid` = """
        |{
        |    "foo": "bar"
        |}
        """.trimMargin()
    run_test(schema, `object_is_valid`, true, "DRAFT_6")
    val `empty_object_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_valid`, true, "DRAFT_6")
    val `array_is_valid` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `array_is_valid`, true, "DRAFT_6")
    val `empty_array_is_valid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array_is_valid`, true, "DRAFT_6")
  }

  @Test
  public fun `boolean_schema_'false'`(): Unit {
    val schema = """false"""
    val `number_is_invalid` = """1"""
    run_test(schema, `number_is_invalid`, false, "DRAFT_6")
    val `string_is_invalid` = """"foo""""
    run_test(schema, `string_is_invalid`, false, "DRAFT_6")
    val `boolean_true_is_invalid` = """true"""
    run_test(schema, `boolean_true_is_invalid`, false, "DRAFT_6")
    val `boolean_false_is_invalid` = """false"""
    run_test(schema, `boolean_false_is_invalid`, false, "DRAFT_6")
    val `null_is_invalid` = """null"""
    run_test(schema, `null_is_invalid`, false, "DRAFT_6")
    val `object_is_invalid` = """
        |{
        |    "foo": "bar"
        |}
        """.trimMargin()
    run_test(schema, `object_is_invalid`, false, "DRAFT_6")
    val `empty_object_is_invalid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `empty_object_is_invalid`, false, "DRAFT_6")
    val `array_is_invalid` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `array_is_invalid`, false, "DRAFT_6")
    val `empty_array_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array_is_invalid`, false, "DRAFT_6")
  }
}
