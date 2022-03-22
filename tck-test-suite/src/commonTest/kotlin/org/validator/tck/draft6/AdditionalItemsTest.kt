package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class AdditionalItemsTest {
  @Test
  public fun additionalItems_as_schema(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |        }
        |    ],
        |    "additionalItems": {
        |        "type": "integer"
        |    }
        |}
        """.trimMargin()
    val `additional_items_match_schema` = """
        |[
        |    null,
        |    2,
        |    3,
        |    4
        |]
        """.trimMargin()
    run_test(schema, `additional_items_match_schema`, true, "DRAFT_6")
    val `additional_items_do_not_match_schema` = """
        |[
        |    null,
        |    2,
        |    3,
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `additional_items_do_not_match_schema`, false, "DRAFT_6")
  }

  @Test
  public fun when_items_is_schema__additionalItems_does_nothing(): Unit {
    val schema = """
        |{
        |    "items": {
        |    },
        |    "additionalItems": false
        |}
        """.trimMargin()
    val `all_items_match_schema` = """
        |[
        |    1,
        |    2,
        |    3,
        |    4,
        |    5
        |]
        """.trimMargin()
    run_test(schema, `all_items_match_schema`, true, "DRAFT_6")
  }

  @Test
  public fun array_of_items_with_no_additionalItems_permitted(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |        },
        |        {
        |        },
        |        {
        |        }
        |    ],
        |    "additionalItems": false
        |}
        """.trimMargin()
    val `empty_array` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_array`, true, "DRAFT_6")
    val `fewer_number_of_items_present_1` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `fewer_number_of_items_present_1`, true, "DRAFT_6")
    val `fewer_number_of_items_present_2` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `fewer_number_of_items_present_2`, true, "DRAFT_6")
    val `equal_number_of_items_present` = """
        |[
        |    1,
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `equal_number_of_items_present`, true, "DRAFT_6")
    val `additional_items_are_not_permitted` = """
        |[
        |    1,
        |    2,
        |    3,
        |    4
        |]
        """.trimMargin()
    run_test(schema, `additional_items_are_not_permitted`, false, "DRAFT_6")
  }

  @Test
  public fun additionalItems_as_false_without_items(): Unit {
    val schema = """
        |{
        |    "additionalItems": false
        |}
        """.trimMargin()
    val `items_defaults_to_empty_schema_so_everything_is_valid` = """
        |[
        |    1,
        |    2,
        |    3,
        |    4,
        |    5
        |]
        """.trimMargin()
    run_test(schema, `items_defaults_to_empty_schema_so_everything_is_valid`, true, "DRAFT_6")
    val `ignores_non_arrays` = """
        |{
        |    "foo": "bar"
        |}
        """.trimMargin()
    run_test(schema, `ignores_non_arrays`, true, "DRAFT_6")
  }

  @Test
  public fun additionalItems_are_allowed_by_default(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |            "type": "integer"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `only_the_first_item_is_validated` = """
        |[
        |    1,
        |    "foo",
        |    false
        |]
        """.trimMargin()
    run_test(schema, `only_the_first_item_is_validated`, true, "DRAFT_6")
  }

  @Test
  public fun additionalItems_should_not_look_in_applicators__valid_case(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "items": [
        |                {
        |                    "type": "integer"
        |                }
        |            ]
        |        }
        |    ],
        |    "additionalItems": {
        |        "type": "boolean"
        |    }
        |}
        """.trimMargin()
    val `items_defined_in_allOf_are_not_examined` = """
        |[
        |    1,
        |    null
        |]
        """.trimMargin()
    run_test(schema, `items_defined_in_allOf_are_not_examined`, true, "DRAFT_6")
  }

  @Test
  public fun additionalItems_should_not_look_in_applicators__invalid_case(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "items": [
        |                {
        |                    "type": "integer"
        |                },
        |                {
        |                    "type": "string"
        |                }
        |            ]
        |        }
        |    ],
        |    "items": [
        |        {
        |            "type": "integer"
        |        }
        |    ],
        |    "additionalItems": {
        |        "type": "boolean"
        |    }
        |}
        """.trimMargin()
    val `items_defined_in_allOf_are_not_examined` = """
        |[
        |    1,
        |    "hello"
        |]
        """.trimMargin()
    run_test(schema, `items_defined_in_allOf_are_not_examined`, false, "DRAFT_6")
  }

  @Test
  public fun items_validation_adjusts_the_starting_index_for_additionalItems(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |            "type": "string"
        |        }
        |    ],
        |    "additionalItems": {
        |        "type": "integer"
        |    }
        |}
        """.trimMargin()
    val `valid_items` = """
        |[
        |    "x",
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `valid_items`, true, "DRAFT_6")
    val `wrong_type_of_second_item` = """
        |[
        |    "x",
        |    "y"
        |]
        """.trimMargin()
    run_test(schema, `wrong_type_of_second_item`, false, "DRAFT_6")
  }
}
