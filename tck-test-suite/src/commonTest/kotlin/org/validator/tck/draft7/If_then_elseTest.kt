package org.validator.tck.draft7

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class If_then_elseTest {
  @Test
  public fun ignore_if_without_then_or_else(): Unit {
    val schema = """
        |{
        |    "if": {
        |        "const": 0
        |    }
        |}
        """.trimMargin()
    val `valid_when_valid_against_lone_if` = """0"""
    run_test(schema, `valid_when_valid_against_lone_if`, true, "DRAFT_7")
    val `valid_when_invalid_against_lone_if` = """"hello""""
    run_test(schema, `valid_when_invalid_against_lone_if`, true, "DRAFT_7")
  }

  @Test
  public fun ignore_then_without_if(): Unit {
    val schema = """
        |{
        |    "then": {
        |        "const": 0
        |    }
        |}
        """.trimMargin()
    val `valid_when_valid_against_lone_then` = """0"""
    run_test(schema, `valid_when_valid_against_lone_then`, true, "DRAFT_7")
    val `valid_when_invalid_against_lone_then` = """"hello""""
    run_test(schema, `valid_when_invalid_against_lone_then`, true, "DRAFT_7")
  }

  @Test
  public fun ignore_else_without_if(): Unit {
    val schema = """
        |{
        |    "else": {
        |        "const": 0
        |    }
        |}
        """.trimMargin()
    val `valid_when_valid_against_lone_else` = """0"""
    run_test(schema, `valid_when_valid_against_lone_else`, true, "DRAFT_7")
    val `valid_when_invalid_against_lone_else` = """"hello""""
    run_test(schema, `valid_when_invalid_against_lone_else`, true, "DRAFT_7")
  }

  @Test
  public fun if_and_then_without_else(): Unit {
    val schema = """
        |{
        |    "if": {
        |        "exclusiveMaximum": 0
        |    },
        |    "then": {
        |        "minimum": -10
        |    }
        |}
        """.trimMargin()
    val `valid_through_then` = """-1"""
    run_test(schema, `valid_through_then`, true, "DRAFT_7")
    val `invalid_through_then` = """-100"""
    run_test(schema, `invalid_through_then`, false, "DRAFT_7")
    val `valid_when_if_test_fails` = """3"""
    run_test(schema, `valid_when_if_test_fails`, true, "DRAFT_7")
  }

  @Test
  public fun if_and_else_without_then(): Unit {
    val schema = """
        |{
        |    "if": {
        |        "exclusiveMaximum": 0
        |    },
        |    "else": {
        |        "multipleOf": 2
        |    }
        |}
        """.trimMargin()
    val `valid_when_if_test_passes` = """-1"""
    run_test(schema, `valid_when_if_test_passes`, true, "DRAFT_7")
    val `valid_through_else` = """4"""
    run_test(schema, `valid_through_else`, true, "DRAFT_7")
    val `invalid_through_else` = """3"""
    run_test(schema, `invalid_through_else`, false, "DRAFT_7")
  }

  @Test
  public fun validate_against_correct_branch__then_vs_else(): Unit {
    val schema = """
        |{
        |    "if": {
        |        "exclusiveMaximum": 0
        |    },
        |    "then": {
        |        "minimum": -10
        |    },
        |    "else": {
        |        "multipleOf": 2
        |    }
        |}
        """.trimMargin()
    val `valid_through_then` = """-1"""
    run_test(schema, `valid_through_then`, true, "DRAFT_7")
    val `invalid_through_then` = """-100"""
    run_test(schema, `invalid_through_then`, false, "DRAFT_7")
    val `valid_through_else` = """4"""
    run_test(schema, `valid_through_else`, true, "DRAFT_7")
    val `invalid_through_else` = """3"""
    run_test(schema, `invalid_through_else`, false, "DRAFT_7")
  }

  @Test
  public fun non_interference_across_combined_schemas(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "if": {
        |                "exclusiveMaximum": 0
        |            }
        |        },
        |        {
        |            "then": {
        |                "minimum": -10
        |            }
        |        },
        |        {
        |            "else": {
        |                "multipleOf": 2
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    val `valid__but_would_have_been_invalid_through_then` = """-100"""
    run_test(schema, `valid__but_would_have_been_invalid_through_then`, true, "DRAFT_7")
    val `valid__but_would_have_been_invalid_through_else` = """3"""
    run_test(schema, `valid__but_would_have_been_invalid_through_else`, true, "DRAFT_7")
  }

  @Test
  public fun if_with_boolean_schema_true(): Unit {
    val schema = """
        |{
        |    "if": true,
        |    "then": {
        |        "const": "then"
        |    },
        |    "else": {
        |        "const": "else"
        |    }
        |}
        """.trimMargin()
    val `boolean_schema_true_in_if_always_chooses_the_then_path_valid` = """"then""""
    run_test(schema, `boolean_schema_true_in_if_always_chooses_the_then_path_valid`, true,
        "DRAFT_7")
    val `boolean_schema_true_in_if_always_chooses_the_then_path_invalid` = """"else""""
    run_test(schema, `boolean_schema_true_in_if_always_chooses_the_then_path_invalid`, false,
        "DRAFT_7")
  }

  @Test
  public fun if_with_boolean_schema_false(): Unit {
    val schema = """
        |{
        |    "if": false,
        |    "then": {
        |        "const": "then"
        |    },
        |    "else": {
        |        "const": "else"
        |    }
        |}
        """.trimMargin()
    val `boolean_schema_false_in_if_always_chooses_the_else_path_invalid` = """"then""""
    run_test(schema, `boolean_schema_false_in_if_always_chooses_the_else_path_invalid`, false,
        "DRAFT_7")
    val `boolean_schema_false_in_if_always_chooses_the_else_path_valid` = """"else""""
    run_test(schema, `boolean_schema_false_in_if_always_chooses_the_else_path_valid`, true,
        "DRAFT_7")
  }

  @Test
  public fun if_appears_at_the_end_when_serialized_keyword_processing_sequence(): Unit {
    val schema = """
        |{
        |    "then": {
        |        "const": "yes"
        |    },
        |    "else": {
        |        "const": "other"
        |    },
        |    "if": {
        |        "maxLength": 4
        |    }
        |}
        """.trimMargin()
    val `yes_redirects_to_then_and_passes` = """"yes""""
    run_test(schema, `yes_redirects_to_then_and_passes`, true, "DRAFT_7")
    val `other_redirects_to_else_and_passes` = """"other""""
    run_test(schema, `other_redirects_to_else_and_passes`, true, "DRAFT_7")
    val `no_redirects_to_then_and_fails` = """"no""""
    run_test(schema, `no_redirects_to_then_and_fails`, false, "DRAFT_7")
    val `invalid_redirects_to_else_and_fails` = """"invalid""""
    run_test(schema, `invalid_redirects_to_else_and_fails`, false, "DRAFT_7")
  }
}
