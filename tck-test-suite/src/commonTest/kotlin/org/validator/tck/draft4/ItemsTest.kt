package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test
import kotlin.test.Ignore

public class ItemsTest {
  @Test
  public fun a_schema_given_for_items(): Unit {
    val schema = "{\"items\":{\"type\":\"integer\"}}"
    val `valid_items` = "[1,2,3]"
    run_test(schema, `valid_items`, true, "DRAFT_4")
    val `wrong_type_of_items` = "[1,\"x\"]"
    run_test(schema, `wrong_type_of_items`, false, "DRAFT_4")
    val `ignores_non_arrays` = "{\"foo\":\"bar\"}"
    run_test(schema, `ignores_non_arrays`, true, "DRAFT_4")
    val `JavaScript_pseudo_array_is_valid` = "{\"0\":\"invalid\",\"length\":1}"
    run_test(schema, `JavaScript_pseudo_array_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun an_array_of_schemas_for_items(): Unit {
    val schema = "{\"items\":[{\"type\":\"integer\"},{\"type\":\"string\"}]}"
    val `correct_types` = "[1,\"foo\"]"
    run_test(schema, `correct_types`, true, "DRAFT_4")
    val `wrong_types` = "[\"foo\",1]"
    run_test(schema, `wrong_types`, false, "DRAFT_4")
    val `incomplete_array_of_items` = "[1]"
    run_test(schema, `incomplete_array_of_items`, true, "DRAFT_4")
    val `array_with_additional_items` = "[1,\"foo\",true]"
    run_test(schema, `array_with_additional_items`, true, "DRAFT_4")
    val `empty_array` = "[]"
    run_test(schema, `empty_array`, true, "DRAFT_4")
    val `JavaScript_pseudo_array_is_valid` = "{\"0\":\"invalid\",\"1\":\"valid\",\"length\":2}"
    run_test(schema, `JavaScript_pseudo_array_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun items_and_subitems(): Unit {
    val schema =
        "{\"definitions\":{\"item\":{\"type\":\"array\",\"additionalItems\":false,\"items\":[{\"${'$'}ref\":\"#/definitions/sub-item\"},{\"${'$'}ref\":\"#/definitions/sub-item\"}]},\"sub-item\":{\"type\":\"object\",\"required\":[\"foo\"]}},\"type\":\"array\",\"additionalItems\":false,\"items\":[{\"${'$'}ref\":\"#/definitions/item\"},{\"${'$'}ref\":\"#/definitions/item\"},{\"${'$'}ref\":\"#/definitions/item\"}]}"
    val `valid_items` =
        "[[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}]]"
    run_test(schema, `valid_items`, true, "DRAFT_4")
    val `too_many_items` =
        "[[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}]]"
    run_test(schema, `too_many_items`, false, "DRAFT_4")
    val `too_many_sub_items` =
        "[[{\"foo\":null},{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}]]"
    run_test(schema, `too_many_sub_items`, false, "DRAFT_4")
    val `wrong_item` =
        "[{\"foo\":null},[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}]]"
    run_test(schema, `wrong_item`, false, "DRAFT_4")
    val `wrong_sub_item` =
        "[[{},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}],[{\"foo\":null},{\"foo\":null}]]"
    run_test(schema, `wrong_sub_item`, false, "DRAFT_4")
    val `fewer_items_is_valid` = "[[{\"foo\":null}],[{\"foo\":null}]]"
    run_test(schema, `fewer_items_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun nested_items(): Unit {
    val schema =
        "{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"number\"}}}}}"
    val `valid_nested_array` = "[[[[1]],[[2],[3]]],[[[4],[5],[6]]]]"
    run_test(schema, `valid_nested_array`, true, "DRAFT_4")
    val `nested_array_with_invalid_type` = "[[[[\"1\"]],[[2],[3]]],[[[4],[5],[6]]]]"
    run_test(schema, `nested_array_with_invalid_type`, false, "DRAFT_4")
    val `not_deep_enough` = "[[[1],[2],[3]],[[4],[5],[6]]]"
    run_test(schema, `not_deep_enough`, false, "DRAFT_4")
  }
}
