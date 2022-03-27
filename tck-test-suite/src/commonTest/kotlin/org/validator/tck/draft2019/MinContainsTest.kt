package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MinContainsTest {
  @Test
  public fun minContains_without_contains_is_ignored(): Unit {
    val schema = """
        |{
        |    "minContains": 1
        |}
        """.trimMargin()
    val `one_item_valid_against_lone_minContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `one_item_valid_against_lone_minContains`, true, "DRAFT_2019_09")
    val `zero_items_still_valid_against_lone_minContains` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `zero_items_still_valid_against_lone_minContains`, true, "DRAFT_2019_09")
  }

  @Test
  public fun minContains_1_with_contains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "minContains": 1
        |}
        """.trimMargin()
    val `empty_data` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_data`, false, "DRAFT_2019_09")
    val `no_elements_match` = """
        |[
        |    2
        |]
        """.trimMargin()
    run_test(schema, `no_elements_match`, false, "DRAFT_2019_09")
    val `single_element_matches__valid_minContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `single_element_matches__valid_minContains`, true, "DRAFT_2019_09")
    val `some_elements_match__valid_minContains` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `some_elements_match__valid_minContains`, true, "DRAFT_2019_09")
    val `all_elements_match__valid_minContains` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__valid_minContains`, true, "DRAFT_2019_09")
  }

  @Test
  public fun minContains_2_with_contains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "minContains": 2
        |}
        """.trimMargin()
    val `empty_data` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_data`, false, "DRAFT_2019_09")
    val `all_elements_match__invalid_minContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__invalid_minContains`, false, "DRAFT_2019_09")
    val `some_elements_match__invalid_minContains` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `some_elements_match__invalid_minContains`, false, "DRAFT_2019_09")
    val `all_elements_match__valid_minContains_exactly_as_needed` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__valid_minContains_exactly_as_needed`, true,
        "DRAFT_2019_09")
    val `all_elements_match__valid_minContains_more_than_needed` = """
        |[
        |    1,
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__valid_minContains_more_than_needed`, true,
        "DRAFT_2019_09")
    val `some_elements_match__valid_minContains` = """
        |[
        |    1,
        |    2,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `some_elements_match__valid_minContains`, true, "DRAFT_2019_09")
  }

  @Test
  public fun maxContains___minContains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "maxContains": 2,
        |    "minContains": 2
        |}
        """.trimMargin()
    val `empty_data` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_data`, false, "DRAFT_2019_09")
    val `all_elements_match__invalid_minContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__invalid_minContains`, false, "DRAFT_2019_09")
    val `all_elements_match__invalid_maxContains` = """
        |[
        |    1,
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__invalid_maxContains`, false, "DRAFT_2019_09")
    val `all_elements_match__valid_maxContains_and_minContains` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__valid_maxContains_and_minContains`, true, "DRAFT_2019_09")
  }

  @Test
  public fun maxContains_less_than_sign_minContains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "maxContains": 1,
        |    "minContains": 3
        |}
        """.trimMargin()
    val `empty_data` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_data`, false, "DRAFT_2019_09")
    val `invalid_minContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `invalid_minContains`, false, "DRAFT_2019_09")
    val `invalid_maxContains` = """
        |[
        |    1,
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `invalid_maxContains`, false, "DRAFT_2019_09")
    val `invalid_maxContains_and_minContains` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `invalid_maxContains_and_minContains`, false, "DRAFT_2019_09")
  }

  @Test
  public fun minContains___0_with_no_maxContains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "minContains": 0
        |}
        """.trimMargin()
    val `empty_data` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_data`, true, "DRAFT_2019_09")
    val `minContains___0_makes_contains_always_pass` = """
        |[
        |    2
        |]
        """.trimMargin()
    run_test(schema, `minContains___0_makes_contains_always_pass`, true, "DRAFT_2019_09")
  }

  @Test
  public fun minContains___0_with_maxContains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "minContains": 0,
        |    "maxContains": 1
        |}
        """.trimMargin()
    val `empty_data` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_data`, true, "DRAFT_2019_09")
    val `not_more_than_maxContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `not_more_than_maxContains`, true, "DRAFT_2019_09")
    val `too_many` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `too_many`, false, "DRAFT_2019_09")
  }
}
