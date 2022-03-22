package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class ContainsTest {
  @Test
  public fun contains_keyword_validation(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "minimum": 5
        |    }
        |}
        """.trimMargin()
    val `array_with_item_matching_schema_5_is_valid` = """
        |[
        |    3,
        |    4,
        |    5
        |]
        """.trimMargin()
    run_test(schema, `array_with_item_matching_schema_5_is_valid`, true, "DRAFT_6")
    val `array_with_item_matching_schema_6_is_valid` = """
        |[
        |    3,
        |    4,
        |    6
        |]
        """.trimMargin()
    run_test(schema, `array_with_item_matching_schema_6_is_valid`, true, "DRAFT_6")
    val `array_with_two_items_matching_schema_5__6_is_valid` = """
        |[
        |    3,
        |    4,
        |    5,
        |    6
        |]
        """.trimMargin()
    run_test(schema, `array_with_two_items_matching_schema_5__6_is_valid`, true, "DRAFT_6")
    val `array_without_items_matching_schema_is_invalid` = """
        |[
        |    2,
        |    3,
        |    4
        |]
        """.trimMargin()
    run_test(schema, `array_without_items_matching_schema_is_invalid`, false, "DRAFT_6")
    val `empty_array_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array_is_invalid`, false, "DRAFT_6")
    val `not_array_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `not_array_is_valid`, true, "DRAFT_6")
  }

  @Test
  public fun contains_keyword_with_const_keyword(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 5
        |    }
        |}
        """.trimMargin()
    val `array_with_item_5_is_valid` = """
        |[
        |    3,
        |    4,
        |    5
        |]
        """.trimMargin()
    run_test(schema, `array_with_item_5_is_valid`, true, "DRAFT_6")
    val `array_with_two_items_5_is_valid` = """
        |[
        |    3,
        |    4,
        |    5,
        |    5
        |]
        """.trimMargin()
    run_test(schema, `array_with_two_items_5_is_valid`, true, "DRAFT_6")
    val `array_without_item_5_is_invalid` = """
        |[
        |    1,
        |    2,
        |    3,
        |    4
        |]
        """.trimMargin()
    run_test(schema, `array_without_item_5_is_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun contains_keyword_with_boolean_schema_true(): Unit {
    val schema = """
        |{
        |    "contains": true
        |}
        """.trimMargin()
    val `any_non_empty_array_is_valid` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `any_non_empty_array_is_valid`, true, "DRAFT_6")
    val `empty_array_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array_is_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun contains_keyword_with_boolean_schema_false(): Unit {
    val schema = """
        |{
        |    "contains": false
        |}
        """.trimMargin()
    val `any_non_empty_array_is_invalid` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `any_non_empty_array_is_invalid`, false, "DRAFT_6")
    val `empty_array_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array_is_invalid`, false, "DRAFT_6")
    val `non_arrays_are_valid` = """"contains does not apply to strings""""
    run_test(schema, `non_arrays_are_valid`, true, "DRAFT_6")
  }

  @Test
  public fun `items_contains`(): Unit {
    val schema = """
        |{
        |    "items": {
        |        "multipleOf": 2
        |    },
        |    "contains": {
        |        "multipleOf": 3
        |    }
        |}
        """.trimMargin()
    val `matches_items__does_not_match_contains` = """
        |[
        |    2,
        |    4,
        |    8
        |]
        """.trimMargin()
    run_test(schema, `matches_items__does_not_match_contains`, false, "DRAFT_6")
    val `does_not_match_items__matches_contains` = """
        |[
        |    3,
        |    6,
        |    9
        |]
        """.trimMargin()
    run_test(schema, `does_not_match_items__matches_contains`, false, "DRAFT_6")
    val `matches_both_items_and_contains` = """
        |[
        |    6,
        |    12
        |]
        """.trimMargin()
    run_test(schema, `matches_both_items_and_contains`, true, "DRAFT_6")
    val `matches_neither_items_nor_contains` = """
        |[
        |    1,
        |    5
        |]
        """.trimMargin()
    run_test(schema, `matches_neither_items_nor_contains`, false, "DRAFT_6")
  }

  @Test
  public fun contains_with_false_if_subschema(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "if": false,
        |        "else": true
        |    }
        |}
        """.trimMargin()
    val `any_non_empty_array_is_valid` = """
        |[
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `any_non_empty_array_is_valid`, true, "DRAFT_6")
    val `empty_array_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array_is_invalid`, false, "DRAFT_6")
  }
}
