package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class UniqueItemsTest {
  @Test
  public fun uniqueItems_validation(): Unit {
    val schema = """
        |{
        |    "uniqueItems": true
        |}
        """.trimMargin()
    val `unique_array_of_integers_is_valid` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_integers_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_integers_is_invalid` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_integers_is_invalid`, false, "DRAFT_2019_09")
    val `non_unique_array_of_more_than_two_integers_is_invalid` = """
        |[
        |    1,
        |    2,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_more_than_two_integers_is_invalid`, false,
        "DRAFT_2019_09")
    val `numbers_are_unique_if_mathematically_unequal` = """
        |[
        |    1.0,
        |    1.0,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `numbers_are_unique_if_mathematically_unequal`, false, "DRAFT_2019_09")
    val `false_is_not_equal_to_zero` = """
        |[
        |    0,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `false_is_not_equal_to_zero`, true, "DRAFT_2019_09")
    val `true_is_not_equal_to_one` = """
        |[
        |    1,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `true_is_not_equal_to_one`, true, "DRAFT_2019_09")
    val `unique_array_of_strings_is_valid` = """
        |[
        |    "foo",
        |    "bar",
        |    "baz"
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_strings_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_strings_is_invalid` = """
        |[
        |    "foo",
        |    "bar",
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_strings_is_invalid`, false, "DRAFT_2019_09")
    val `unique_array_of_objects_is_valid` = """
        |[
        |    {
        |        "foo": "bar"
        |    },
        |    {
        |        "foo": "baz"
        |    }
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_objects_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_objects_is_invalid` = """
        |[
        |    {
        |        "foo": "bar"
        |    },
        |    {
        |        "foo": "bar"
        |    }
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_objects_is_invalid`, false, "DRAFT_2019_09")
    val `unique_array_of_nested_objects_is_valid` = """
        |[
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": true
        |            }
        |        }
        |    },
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": false
        |            }
        |        }
        |    }
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_nested_objects_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_nested_objects_is_invalid` = """
        |[
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": true
        |            }
        |        }
        |    },
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": true
        |            }
        |        }
        |    }
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_nested_objects_is_invalid`, false, "DRAFT_2019_09")
    val `unique_array_of_arrays_is_valid` = """
        |[
        |    [
        |        "foo"
        |    ],
        |    [
        |        "bar"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_arrays_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_arrays_is_invalid` = """
        |[
        |    [
        |        "foo"
        |    ],
        |    [
        |        "foo"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_arrays_is_invalid`, false, "DRAFT_2019_09")
    val `non_unique_array_of_more_than_two_arrays_is_invalid` = """
        |[
        |    [
        |        "foo"
        |    ],
        |    [
        |        "bar"
        |    ],
        |    [
        |        "foo"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_more_than_two_arrays_is_invalid`, false, "DRAFT_2019_09")
    val `1_and_true_are_unique` = """
        |[
        |    1,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `1_and_true_are_unique`, true, "DRAFT_2019_09")
    val `0_and_false_are_unique` = """
        |[
        |    0,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `0_and_false_are_unique`, true, "DRAFT_2019_09")
    val `{1}_and_{true}_are_unique` = """
        |[
        |    [
        |        1
        |    ],
        |    [
        |        true
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `{1}_and_{true}_are_unique`, true, "DRAFT_2019_09")
    val `{0}_and_{false}_are_unique` = """
        |[
        |    [
        |        0
        |    ],
        |    [
        |        false
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `{0}_and_{false}_are_unique`, true, "DRAFT_2019_09")
    val `nested_{1}_and_{true}_are_unique` = """
        |[
        |    [
        |        [
        |            1
        |        ],
        |        "foo"
        |    ],
        |    [
        |        [
        |            true
        |        ],
        |        "foo"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `nested_{1}_and_{true}_are_unique`, true, "DRAFT_2019_09")
    val `nested_{0}_and_{false}_are_unique` = """
        |[
        |    [
        |        [
        |            0
        |        ],
        |        "foo"
        |    ],
        |    [
        |        [
        |            false
        |        ],
        |        "foo"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `nested_{0}_and_{false}_are_unique`, true, "DRAFT_2019_09")
    val `unique_heterogeneous_types_are_valid` = """
        |[
        |    {
        |    },
        |    [
        |        1
        |    ],
        |    true,
        |    null,
        |    1,
        |    "{}"
        |]
        """.trimMargin()
    run_test(schema, `unique_heterogeneous_types_are_valid`, true, "DRAFT_2019_09")
    val `non_unique_heterogeneous_types_are_invalid` = """
        |[
        |    {
        |    },
        |    [
        |        1
        |    ],
        |    true,
        |    null,
        |    {
        |    },
        |    1
        |]
        """.trimMargin()
    run_test(schema, `non_unique_heterogeneous_types_are_invalid`, false, "DRAFT_2019_09")
    val `different_objects_are_unique` = """
        |[
        |    {
        |        "a": 1,
        |        "b": 2
        |    },
        |    {
        |        "a": 2,
        |        "b": 1
        |    }
        |]
        """.trimMargin()
    run_test(schema, `different_objects_are_unique`, true, "DRAFT_2019_09")
    val `objects_are_non_unique_despite_key_order` = """
        |[
        |    {
        |        "a": 1,
        |        "b": 2
        |    },
        |    {
        |        "b": 2,
        |        "a": 1
        |    }
        |]
        """.trimMargin()
    run_test(schema, `objects_are_non_unique_despite_key_order`, false, "DRAFT_2019_09")
    val `{"a"__false}_and_{"a"__0}_are_unique` = """
        |[
        |    {
        |        "a": false
        |    },
        |    {
        |        "a": 0
        |    }
        |]
        """.trimMargin()
    run_test(schema, `{"a"__false}_and_{"a"__0}_are_unique`, true, "DRAFT_2019_09")
    val `{"a"__true}_and_{"a"__1}_are_unique` = """
        |[
        |    {
        |        "a": true
        |    },
        |    {
        |        "a": 1
        |    }
        |]
        """.trimMargin()
    run_test(schema, `{"a"__true}_and_{"a"__1}_are_unique`, true, "DRAFT_2019_09")
  }

  @Test
  public fun uniqueItems_with_an_array_of_items(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |            "type": "boolean"
        |        },
        |        {
        |            "type": "boolean"
        |        }
        |    ],
        |    "uniqueItems": true
        |}
        """.trimMargin()
    val `{false__true}_from_items_array_is_valid` = """
        |[
        |    false,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{true__false}_from_items_array_is_valid` = """
        |[
        |    true,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{false__false}_from_items_array_is_not_valid` = """
        |[
        |    false,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{false__false}_from_items_array_is_not_valid`, false, "DRAFT_2019_09")
    val `{true__true}_from_items_array_is_not_valid` = """
        |[
        |    true,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{true__true}_from_items_array_is_not_valid`, false, "DRAFT_2019_09")
    val `unique_array_extended_from_{false__true}_is_valid` = """
        |[
        |    false,
        |    true,
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `unique_array_extended_from_{false__true}_is_valid`, true, "DRAFT_2019_09")
    val `unique_array_extended_from_{true__false}_is_valid` = """
        |[
        |    true,
        |    false,
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `unique_array_extended_from_{true__false}_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_extended_from_{false__true}_is_not_valid` = """
        |[
        |    false,
        |    true,
        |    "foo",
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_extended_from_{false__true}_is_not_valid`, false,
        "DRAFT_2019_09")
    val `non_unique_array_extended_from_{true__false}_is_not_valid` = """
        |[
        |    true,
        |    false,
        |    "foo",
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_extended_from_{true__false}_is_not_valid`, false,
        "DRAFT_2019_09")
  }

  @Test
  public fun uniqueItems_with_an_array_of_items_and_additionalItems_false(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |            "type": "boolean"
        |        },
        |        {
        |            "type": "boolean"
        |        }
        |    ],
        |    "uniqueItems": true,
        |    "additionalItems": false
        |}
        """.trimMargin()
    val `{false__true}_from_items_array_is_valid` = """
        |[
        |    false,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{true__false}_from_items_array_is_valid` = """
        |[
        |    true,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{false__false}_from_items_array_is_not_valid` = """
        |[
        |    false,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{false__false}_from_items_array_is_not_valid`, false, "DRAFT_2019_09")
    val `{true__true}_from_items_array_is_not_valid` = """
        |[
        |    true,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{true__true}_from_items_array_is_not_valid`, false, "DRAFT_2019_09")
    val `extra_items_are_invalid_even_if_unique` = """
        |[
        |    false,
        |    true,
        |    null
        |]
        """.trimMargin()
    run_test(schema, `extra_items_are_invalid_even_if_unique`, false, "DRAFT_2019_09")
  }

  @Test
  public fun uniqueItems_false_validation(): Unit {
    val schema = """
        |{
        |    "uniqueItems": false
        |}
        """.trimMargin()
    val `unique_array_of_integers_is_valid` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_integers_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_integers_is_valid` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_integers_is_valid`, true, "DRAFT_2019_09")
    val `numbers_are_unique_if_mathematically_unequal` = """
        |[
        |    1.0,
        |    1.0,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `numbers_are_unique_if_mathematically_unequal`, true, "DRAFT_2019_09")
    val `false_is_not_equal_to_zero` = """
        |[
        |    0,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `false_is_not_equal_to_zero`, true, "DRAFT_2019_09")
    val `true_is_not_equal_to_one` = """
        |[
        |    1,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `true_is_not_equal_to_one`, true, "DRAFT_2019_09")
    val `unique_array_of_objects_is_valid` = """
        |[
        |    {
        |        "foo": "bar"
        |    },
        |    {
        |        "foo": "baz"
        |    }
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_objects_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_objects_is_valid` = """
        |[
        |    {
        |        "foo": "bar"
        |    },
        |    {
        |        "foo": "bar"
        |    }
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_objects_is_valid`, true, "DRAFT_2019_09")
    val `unique_array_of_nested_objects_is_valid` = """
        |[
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": true
        |            }
        |        }
        |    },
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": false
        |            }
        |        }
        |    }
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_nested_objects_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_nested_objects_is_valid` = """
        |[
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": true
        |            }
        |        }
        |    },
        |    {
        |        "foo": {
        |            "bar": {
        |                "baz": true
        |            }
        |        }
        |    }
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_nested_objects_is_valid`, true, "DRAFT_2019_09")
    val `unique_array_of_arrays_is_valid` = """
        |[
        |    [
        |        "foo"
        |    ],
        |    [
        |        "bar"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `unique_array_of_arrays_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_of_arrays_is_valid` = """
        |[
        |    [
        |        "foo"
        |    ],
        |    [
        |        "foo"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_of_arrays_is_valid`, true, "DRAFT_2019_09")
    val `1_and_true_are_unique` = """
        |[
        |    1,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `1_and_true_are_unique`, true, "DRAFT_2019_09")
    val `0_and_false_are_unique` = """
        |[
        |    0,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `0_and_false_are_unique`, true, "DRAFT_2019_09")
    val `unique_heterogeneous_types_are_valid` = """
        |[
        |    {
        |    },
        |    [
        |        1
        |    ],
        |    true,
        |    null,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `unique_heterogeneous_types_are_valid`, true, "DRAFT_2019_09")
    val `non_unique_heterogeneous_types_are_valid` = """
        |[
        |    {
        |    },
        |    [
        |        1
        |    ],
        |    true,
        |    null,
        |    {
        |    },
        |    1
        |]
        """.trimMargin()
    run_test(schema, `non_unique_heterogeneous_types_are_valid`, true, "DRAFT_2019_09")
  }

  @Test
  public fun uniqueItems_false_with_an_array_of_items(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |            "type": "boolean"
        |        },
        |        {
        |            "type": "boolean"
        |        }
        |    ],
        |    "uniqueItems": false
        |}
        """.trimMargin()
    val `{false__true}_from_items_array_is_valid` = """
        |[
        |    false,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{true__false}_from_items_array_is_valid` = """
        |[
        |    true,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{false__false}_from_items_array_is_valid` = """
        |[
        |    false,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{false__false}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{true__true}_from_items_array_is_valid` = """
        |[
        |    true,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{true__true}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `unique_array_extended_from_{false__true}_is_valid` = """
        |[
        |    false,
        |    true,
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `unique_array_extended_from_{false__true}_is_valid`, true, "DRAFT_2019_09")
    val `unique_array_extended_from_{true__false}_is_valid` = """
        |[
        |    true,
        |    false,
        |    "foo",
        |    "bar"
        |]
        """.trimMargin()
    run_test(schema, `unique_array_extended_from_{true__false}_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_extended_from_{false__true}_is_valid` = """
        |[
        |    false,
        |    true,
        |    "foo",
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_extended_from_{false__true}_is_valid`, true, "DRAFT_2019_09")
    val `non_unique_array_extended_from_{true__false}_is_valid` = """
        |[
        |    true,
        |    false,
        |    "foo",
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `non_unique_array_extended_from_{true__false}_is_valid`, true, "DRAFT_2019_09")
  }

  @Test
  public fun uniqueItems_false_with_an_array_of_items_and_additionalItems_false(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |            "type": "boolean"
        |        },
        |        {
        |            "type": "boolean"
        |        }
        |    ],
        |    "uniqueItems": false,
        |    "additionalItems": false
        |}
        """.trimMargin()
    val `{false__true}_from_items_array_is_valid` = """
        |[
        |    false,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{false__true}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{true__false}_from_items_array_is_valid` = """
        |[
        |    true,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{true__false}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{false__false}_from_items_array_is_valid` = """
        |[
        |    false,
        |    false
        |]
        """.trimMargin()
    run_test(schema, `{false__false}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `{true__true}_from_items_array_is_valid` = """
        |[
        |    true,
        |    true
        |]
        """.trimMargin()
    run_test(schema, `{true__true}_from_items_array_is_valid`, true, "DRAFT_2019_09")
    val `extra_items_are_invalid_even_if_unique` = """
        |[
        |    false,
        |    true,
        |    null
        |]
        """.trimMargin()
    run_test(schema, `extra_items_are_invalid_even_if_unique`, false, "DRAFT_2019_09")
  }
}
