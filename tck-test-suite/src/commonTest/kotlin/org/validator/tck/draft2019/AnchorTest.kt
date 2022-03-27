package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class AnchorTest {
  @Test
  public fun Location_independent_identifier(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "#foo",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}anchor": "foo",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    val `match` = """1"""
    run_test(schema, `match`, true, "DRAFT_2019_09")
    val `mismatch` = """"a""""
    run_test(schema, `mismatch`, false, "DRAFT_2019_09")
  }

  @Test
  public fun Location_independent_identifier_with_absolute_URI(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "http://localhost:1234/bar#foo",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "http://localhost:1234/bar",
        |            "${'$'}anchor": "foo",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    val `match` = """1"""
    run_test(schema, `match`, true, "DRAFT_2019_09")
    val `mismatch` = """"a""""
    run_test(schema, `mismatch`, false, "DRAFT_2019_09")
  }

  @Test
  public fun Location_independent_identifier_with_base_URI_change_in_subschema(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/root",
        |    "${'$'}ref": "http://localhost:1234/nested.json#foo",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "nested.json",
        |            "${'$'}defs": {
        |                "B": {
        |                    "${'$'}anchor": "foo",
        |                    "type": "integer"
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `match` = """1"""
    run_test(schema, `match`, true, "DRAFT_2019_09")
    val `mismatch` = """"a""""
    run_test(schema, `mismatch`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `$anchor_inside_an_enum_is_not_a_real_identifier`(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "anchor_in_enum": {
        |            "enum": [
        |                {
        |                    "${'$'}anchor": "my_anchor",
        |                    "type": "null"
        |                }
        |            ]
        |        },
        |        "real_identifier_in_schema": {
        |            "${'$'}anchor": "my_anchor",
        |            "type": "string"
        |        },
        |        "zzz_anchor_in_const": {
        |            "const": {
        |                "${'$'}anchor": "my_anchor",
        |                "type": "null"
        |            }
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "${'$'}ref": "#/${'$'}defs/anchor_in_enum"
        |        },
        |        {
        |            "${'$'}ref": "#my_anchor"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `exact_match_to_enum__and_type_matches` = """
        |{
        |    "${'$'}anchor": "my_anchor",
        |    "type": "null"
        |}
        """.trimMargin()
    run_test(schema, `exact_match_to_enum__and_type_matches`, true, "DRAFT_2019_09")
    val `in_implementations_that_strip_$anchor__this_may_match_either_$def` = """
        |{
        |    "type": "null"
        |}
        """.trimMargin()
    run_test(schema, `in_implementations_that_strip_$anchor__this_may_match_either_$def`, false,
        "DRAFT_2019_09")
    val `match_$ref_to_$anchor` = """"a string to match #/${'$'}defs/anchor_in_enum""""
    run_test(schema, `match_$ref_to_$anchor`, true, "DRAFT_2019_09")
    val `no_match_on_enum_or_$ref_to_$anchor` = """1"""
    run_test(schema, `no_match_on_enum_or_$ref_to_$anchor`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `same_$anchor_with_different_base_uri`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/foobar",
        |    "${'$'}defs": {
        |        "A": {
        |            "${'$'}id": "child1",
        |            "allOf": [
        |                {
        |                    "${'$'}id": "child2",
        |                    "${'$'}anchor": "my_anchor",
        |                    "type": "number"
        |                },
        |                {
        |                    "${'$'}anchor": "my_anchor",
        |                    "type": "string"
        |                }
        |            ]
        |        }
        |    },
        |    "${'$'}ref": "child1#my_anchor"
        |}
        """.trimMargin()
    val `$ref_should_resolve_to__$defs_A_allOf_1` = """"a""""
    run_test(schema, `$ref_should_resolve_to__$defs_A_allOf_1`, true, "DRAFT_2019_09")
    val `$ref_should_not_resolve_to__$defs_A_allOf_0` = """1"""
    run_test(schema, `$ref_should_not_resolve_to__$defs_A_allOf_0`, false, "DRAFT_2019_09")
  }
}
