package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class AdditionalItemsTest {
  @Test
  public fun additionalItems_as_schema(): Unit {
    val schema = "{\"items\":[{}],\"additionalItems\":{\"type\":\"integer\"}}"
    val `additional_items_match_schema` = "[null,2,3,4]"
    run_test(schema, `additional_items_match_schema`, true, "DRAFT_4")
    val `additional_items_do_not_match_schema` = "[null,2,3,\"foo\"]"
    run_test(schema, `additional_items_do_not_match_schema`, false, "DRAFT_4")
  }

  @Test
  public fun when_items_is_schema__additionalItems_does_nothing(): Unit {
    val schema = "{\"items\":{},\"additionalItems\":false}"
    val `all_items_match_schema` = "[1,2,3,4,5]"
    run_test(schema, `all_items_match_schema`, true, "DRAFT_4")
  }

  @Test
  public fun array_of_items_with_no_additionalItems_permitted(): Unit {
    val schema = "{\"items\":[{},{},{}],\"additionalItems\":false}"
    val `empty_array` = "[]"
    run_test(schema, `empty_array`, true, "DRAFT_4")
    val `fewer_number_of_items_present_1` = "[1]"
    run_test(schema, `fewer_number_of_items_present_1`, true, "DRAFT_4")
    val `fewer_number_of_items_present_2` = "[1,2]"
    run_test(schema, `fewer_number_of_items_present_2`, true, "DRAFT_4")
    val `equal_number_of_items_present` = "[1,2,3]"
    run_test(schema, `equal_number_of_items_present`, true, "DRAFT_4")
    val `additional_items_are_not_permitted` = "[1,2,3,4]"
    run_test(schema, `additional_items_are_not_permitted`, false, "DRAFT_4")
  }

  @Test
  public fun additionalItems_as_false_without_items(): Unit {
    val schema = "{\"additionalItems\":false}"
    val `items_defaults_to_empty_schema_so_everything_is_valid` = "[1,2,3,4,5]"
    run_test(schema, `items_defaults_to_empty_schema_so_everything_is_valid`, true, "DRAFT_4")
    val `ignores_non_arrays` = "{\"foo\":\"bar\"}"
    run_test(schema, `ignores_non_arrays`, true, "DRAFT_4")
  }

  @Test
  public fun additionalItems_are_allowed_by_default(): Unit {
    val schema = "{\"items\":[{\"type\":\"integer\"}]}"
    val `only_the_first_item_is_validated` = "[1,\"foo\",false]"
    run_test(schema, `only_the_first_item_is_validated`, true, "DRAFT_4")
  }

  @Test
  public fun additionalItems_should_not_look_in_applicators__valid_case(): Unit {
    val schema =
        "{\"allOf\":[{\"items\":[{\"type\":\"integer\"}]}],\"additionalItems\":{\"type\":\"boolean\"}}"
    val `items_defined_in_allOf_are_not_examined` = "[1,null]"
    run_test(schema, `items_defined_in_allOf_are_not_examined`, true, "DRAFT_4")
  }

  @Test
  public fun additionalItems_should_not_look_in_applicators__invalid_case(): Unit {
    val schema =
        "{\"allOf\":[{\"items\":[{\"type\":\"integer\"},{\"type\":\"string\"}]}],\"items\":[{\"type\":\"integer\"}],\"additionalItems\":{\"type\":\"boolean\"}}"
    val `items_defined_in_allOf_are_not_examined` = "[1,\"hello\"]"
    run_test(schema, `items_defined_in_allOf_are_not_examined`, false, "DRAFT_4")
  }

  @Test
  public fun items_validation_adjusts_the_starting_index_for_additionalItems(): Unit {
    val schema = "{\"items\":[{\"type\":\"string\"}],\"additionalItems\":{\"type\":\"integer\"}}"
    val `valid_items` = "[\"x\",2,3]"
    run_test(schema, `valid_items`, true, "DRAFT_4")
    val `wrong_type_of_second_item` = "[\"x\",\"y\"]"
    run_test(schema, `wrong_type_of_second_item`, false, "DRAFT_4")
  }
}
