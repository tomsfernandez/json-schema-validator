package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MaxContainsTest {
  @Test
  public fun maxContains_without_contains_is_ignored(): Unit {
    val schema = """
        |{
        |    "maxContains": 1
        |}
        """.trimMargin()
    val `one_item_valid_against_lone_maxContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `one_item_valid_against_lone_maxContains`, true, "DRAFT_2019_09")
    val `two_items_still_valid_against_lone_maxContains` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `two_items_still_valid_against_lone_maxContains`, true, "DRAFT_2019_09")
  }

  @Test
  public fun maxContains_with_contains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "maxContains": 1
        |}
        """.trimMargin()
    val `empty_data` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `empty_data`, false, "DRAFT_2019_09")
    val `all_elements_match__valid_maxContains` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__valid_maxContains`, true, "DRAFT_2019_09")
    val `all_elements_match__invalid_maxContains` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `all_elements_match__invalid_maxContains`, false, "DRAFT_2019_09")
    val `some_elements_match__valid_maxContains` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `some_elements_match__valid_maxContains`, true, "DRAFT_2019_09")
    val `some_elements_match__invalid_maxContains` = """
        |[
        |    1,
        |    2,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `some_elements_match__invalid_maxContains`, false, "DRAFT_2019_09")
  }

  @Test
  public fun minContains_less_than_sign_maxContains(): Unit {
    val schema = """
        |{
        |    "contains": {
        |        "const": 1
        |    },
        |    "minContains": 1,
        |    "maxContains": 3
        |}
        """.trimMargin()
    val `actual_less_than_sign_minContains_less_than_sign_maxContains` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `actual_less_than_sign_minContains_less_than_sign_maxContains`, false,
        "DRAFT_2019_09")
    val `minContains_less_than_sign_actual_less_than_sign_maxContains` = """
        |[
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `minContains_less_than_sign_actual_less_than_sign_maxContains`, true,
        "DRAFT_2019_09")
    val `minContains_less_than_sign_maxContains_less_than_sign_actual` = """
        |[
        |    1,
        |    1,
        |    1,
        |    1
        |]
        """.trimMargin()
    run_test(schema, `minContains_less_than_sign_maxContains_less_than_sign_actual`, false,
        "DRAFT_2019_09")
  }
}
