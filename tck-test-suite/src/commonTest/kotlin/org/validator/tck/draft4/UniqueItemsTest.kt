package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class UniqueItemsTest {
  @Test
  public fun uniqueItems_validation(): Unit {
    val schema = "{\"uniqueItems\":true}"
    val `unique_array_of_integers_is_valid` = "[1,2]"
    run_test(schema, `unique_array_of_integers_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_integers_is_invalid` = "[1,1]"
    run_test(schema, `non_unique_array_of_integers_is_invalid`, false, "DRAFT_4")
    val `numbers_are_unique_if_mathematically_unequal` = "[1.0,1.00,1]"
    run_test(schema, `numbers_are_unique_if_mathematically_unequal`, false, "DRAFT_4")
    val `false_is_not_equal_to_zero` = "[0,false]"
    run_test(schema, `false_is_not_equal_to_zero`, true, "DRAFT_4")
    val `true_is_not_equal_to_one` = "[1,true]"
    run_test(schema, `true_is_not_equal_to_one`, true, "DRAFT_4")
    val `unique_array_of_objects_is_valid` = "[{\"foo\":\"bar\"},{\"foo\":\"baz\"}]"
    run_test(schema, `unique_array_of_objects_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_objects_is_invalid` = "[{\"foo\":\"bar\"},{\"foo\":\"bar\"}]"
    run_test(schema, `non_unique_array_of_objects_is_invalid`, false, "DRAFT_4")
    val `unique_array_of_nested_objects_is_valid` =
        "[{\"foo\":{\"bar\":{\"baz\":true}}},{\"foo\":{\"bar\":{\"baz\":false}}}]"
    run_test(schema, `unique_array_of_nested_objects_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_nested_objects_is_invalid` =
        "[{\"foo\":{\"bar\":{\"baz\":true}}},{\"foo\":{\"bar\":{\"baz\":true}}}]"
    run_test(schema, `non_unique_array_of_nested_objects_is_invalid`, false, "DRAFT_4")
    val `unique_array_of_arrays_is_valid` = "[[\"foo\"],[\"bar\"]]"
    run_test(schema, `unique_array_of_arrays_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_arrays_is_invalid` = "[[\"foo\"],[\"foo\"]]"
    run_test(schema, `non_unique_array_of_arrays_is_invalid`, false, "DRAFT_4")
    val `1_and_true_are_unique` = "[1,true]"
    run_test(schema, `1_and_true_are_unique`, true, "DRAFT_4")
    val `0_and_false_are_unique` = "[0,false]"
    run_test(schema, `0_and_false_are_unique`, true, "DRAFT_4")
    val `{1}_and_{true}_are_unique` = "[[1],[true]]"
    run_test(schema, `{1}_and_{true}_are_unique`, true, "DRAFT_4")
    val `{0}_and_{false}_are_unique` = "[[0],[false]]"
    run_test(schema, `{0}_and_{false}_are_unique`, true, "DRAFT_4")
    val `nested_{1}_and_{true}_are_unique` = "[[[1],\"foo\"],[[true],\"foo\"]]"
    run_test(schema, `nested_{1}_and_{true}_are_unique`, true, "DRAFT_4")
    val `nested_{0}_and_{false}_are_unique` = "[[[0],\"foo\"],[[false],\"foo\"]]"
    run_test(schema, `nested_{0}_and_{false}_are_unique`, true, "DRAFT_4")
    val `unique_heterogeneous_types_are_valid` = "[{},[1],true,null,1,\"{}\"]"
    run_test(schema, `unique_heterogeneous_types_are_valid`, true, "DRAFT_4")
    val `non_unique_heterogeneous_types_are_invalid` = "[{},[1],true,null,{},1]"
    run_test(schema, `non_unique_heterogeneous_types_are_invalid`, false, "DRAFT_4")
    val `different_objects_are_unique` = "[{\"a\":1,\"b\":2},{\"a\":2,\"b\":1}]"
    run_test(schema, `different_objects_are_unique`, true, "DRAFT_4")
    val `objects_are_non_unique_despite_key_order` = "[{\"a\":1,\"b\":2},{\"b\":2,\"a\":1}]"
    run_test(schema, `objects_are_non_unique_despite_key_order`, false, "DRAFT_4")
    val `{"a"__false}_and_{"a"__0}_are_unique` = "[{\"a\":false},{\"a\":0}]"
    run_test(schema, `{"a"__false}_and_{"a"__0}_are_unique`, true, "DRAFT_4")
    val `{"a"__true}_and_{"a"__1}_are_unique` = "[{\"a\":true},{\"a\":1}]"
    run_test(schema, `{"a"__true}_and_{"a"__1}_are_unique`, true, "DRAFT_4")
  }

  @Test
  public fun uniqueItems_with_an_array_of_items(): Unit {
    val schema = "{\"items\":[{\"type\":\"boolean\"},{\"type\":\"boolean\"}],\"uniqueItems\":true}"
    val `{false__true}_from_items_array_is_valid` = "[false,true]"
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{true__false}_from_items_array_is_valid` = "[true,false]"
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{false__false}_from_items_array_is_not_valid` = "[false,false]"
    run_test(schema, `{false__false}_from_items_array_is_not_valid`, false, "DRAFT_4")
    val `{true__true}_from_items_array_is_not_valid` = "[true,true]"
    run_test(schema, `{true__true}_from_items_array_is_not_valid`, false, "DRAFT_4")
    val `unique_array_extended_from_{false__true}_is_valid` = "[false,true,\"foo\",\"bar\"]"
    run_test(schema, `unique_array_extended_from_{false__true}_is_valid`, true, "DRAFT_4")
    val `unique_array_extended_from_{true__false}_is_valid` = "[true,false,\"foo\",\"bar\"]"
    run_test(schema, `unique_array_extended_from_{true__false}_is_valid`, true, "DRAFT_4")
    val `non_unique_array_extended_from_{false__true}_is_not_valid` = "[false,true,\"foo\",\"foo\"]"
    run_test(schema, `non_unique_array_extended_from_{false__true}_is_not_valid`, false, "DRAFT_4")
    val `non_unique_array_extended_from_{true__false}_is_not_valid` = "[true,false,\"foo\",\"foo\"]"
    run_test(schema, `non_unique_array_extended_from_{true__false}_is_not_valid`, false, "DRAFT_4")
  }

  @Test
  public fun uniqueItems_with_an_array_of_items_and_additionalItems_false(): Unit {
    val schema =
        "{\"items\":[{\"type\":\"boolean\"},{\"type\":\"boolean\"}],\"uniqueItems\":true,\"additionalItems\":false}"
    val `{false__true}_from_items_array_is_valid` = "[false,true]"
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{true__false}_from_items_array_is_valid` = "[true,false]"
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{false__false}_from_items_array_is_not_valid` = "[false,false]"
    run_test(schema, `{false__false}_from_items_array_is_not_valid`, false, "DRAFT_4")
    val `{true__true}_from_items_array_is_not_valid` = "[true,true]"
    run_test(schema, `{true__true}_from_items_array_is_not_valid`, false, "DRAFT_4")
    val `extra_items_are_invalid_even_if_unique` = "[false,true,null]"
    run_test(schema, `extra_items_are_invalid_even_if_unique`, false, "DRAFT_4")
  }

  @Test
  public fun uniqueItems_false_validation(): Unit {
    val schema = "{\"uniqueItems\":false}"
    val `unique_array_of_integers_is_valid` = "[1,2]"
    run_test(schema, `unique_array_of_integers_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_integers_is_valid` = "[1,1]"
    run_test(schema, `non_unique_array_of_integers_is_valid`, true, "DRAFT_4")
    val `numbers_are_unique_if_mathematically_unequal` = "[1.0,1.00,1]"
    run_test(schema, `numbers_are_unique_if_mathematically_unequal`, true, "DRAFT_4")
    val `false_is_not_equal_to_zero` = "[0,false]"
    run_test(schema, `false_is_not_equal_to_zero`, true, "DRAFT_4")
    val `true_is_not_equal_to_one` = "[1,true]"
    run_test(schema, `true_is_not_equal_to_one`, true, "DRAFT_4")
    val `unique_array_of_objects_is_valid` = "[{\"foo\":\"bar\"},{\"foo\":\"baz\"}]"
    run_test(schema, `unique_array_of_objects_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_objects_is_valid` = "[{\"foo\":\"bar\"},{\"foo\":\"bar\"}]"
    run_test(schema, `non_unique_array_of_objects_is_valid`, true, "DRAFT_4")
    val `unique_array_of_nested_objects_is_valid` =
        "[{\"foo\":{\"bar\":{\"baz\":true}}},{\"foo\":{\"bar\":{\"baz\":false}}}]"
    run_test(schema, `unique_array_of_nested_objects_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_nested_objects_is_valid` =
        "[{\"foo\":{\"bar\":{\"baz\":true}}},{\"foo\":{\"bar\":{\"baz\":true}}}]"
    run_test(schema, `non_unique_array_of_nested_objects_is_valid`, true, "DRAFT_4")
    val `unique_array_of_arrays_is_valid` = "[[\"foo\"],[\"bar\"]]"
    run_test(schema, `unique_array_of_arrays_is_valid`, true, "DRAFT_4")
    val `non_unique_array_of_arrays_is_valid` = "[[\"foo\"],[\"foo\"]]"
    run_test(schema, `non_unique_array_of_arrays_is_valid`, true, "DRAFT_4")
    val `1_and_true_are_unique` = "[1,true]"
    run_test(schema, `1_and_true_are_unique`, true, "DRAFT_4")
    val `0_and_false_are_unique` = "[0,false]"
    run_test(schema, `0_and_false_are_unique`, true, "DRAFT_4")
    val `unique_heterogeneous_types_are_valid` = "[{},[1],true,null,1]"
    run_test(schema, `unique_heterogeneous_types_are_valid`, true, "DRAFT_4")
    val `non_unique_heterogeneous_types_are_valid` = "[{},[1],true,null,{},1]"
    run_test(schema, `non_unique_heterogeneous_types_are_valid`, true, "DRAFT_4")
  }

  @Test
  public fun uniqueItems_false_with_an_array_of_items(): Unit {
    val schema = "{\"items\":[{\"type\":\"boolean\"},{\"type\":\"boolean\"}],\"uniqueItems\":false}"
    val `{false__true}_from_items_array_is_valid` = "[false,true]"
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{true__false}_from_items_array_is_valid` = "[true,false]"
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{false__false}_from_items_array_is_valid` = "[false,false]"
    run_test(schema, `{false__false}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{true__true}_from_items_array_is_valid` = "[true,true]"
    run_test(schema, `{true__true}_from_items_array_is_valid`, true, "DRAFT_4")
    val `unique_array_extended_from_{false__true}_is_valid` = "[false,true,\"foo\",\"bar\"]"
    run_test(schema, `unique_array_extended_from_{false__true}_is_valid`, true, "DRAFT_4")
    val `unique_array_extended_from_{true__false}_is_valid` = "[true,false,\"foo\",\"bar\"]"
    run_test(schema, `unique_array_extended_from_{true__false}_is_valid`, true, "DRAFT_4")
    val `non_unique_array_extended_from_{false__true}_is_valid` = "[false,true,\"foo\",\"foo\"]"
    run_test(schema, `non_unique_array_extended_from_{false__true}_is_valid`, true, "DRAFT_4")
    val `non_unique_array_extended_from_{true__false}_is_valid` = "[true,false,\"foo\",\"foo\"]"
    run_test(schema, `non_unique_array_extended_from_{true__false}_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun uniqueItems_false_with_an_array_of_items_and_additionalItems_false(): Unit {
    val schema =
        "{\"items\":[{\"type\":\"boolean\"},{\"type\":\"boolean\"}],\"uniqueItems\":false,\"additionalItems\":false}"
    val `{false__true}_from_items_array_is_valid` = "[false,true]"
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{true__false}_from_items_array_is_valid` = "[true,false]"
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{false__false}_from_items_array_is_valid` = "[false,false]"
    run_test(schema, `{false__false}_from_items_array_is_valid`, true, "DRAFT_4")
    val `{true__true}_from_items_array_is_valid` = "[true,true]"
    run_test(schema, `{true__true}_from_items_array_is_valid`, true, "DRAFT_4")
    val `extra_items_are_invalid_even_if_unique` = "[false,true,null]"
    run_test(schema, `extra_items_are_invalid_even_if_unique`, false, "DRAFT_4")
  }
}
