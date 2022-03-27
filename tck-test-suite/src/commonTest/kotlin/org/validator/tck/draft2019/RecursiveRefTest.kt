package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class RecursiveRefTest {
  @Test
  public fun `$recursiveRef_without_$recursiveAnchor_works_like_$ref`(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |            "${'$'}recursiveRef": "#"
        |        }
        |    },
        |    "additionalProperties": false
        |}
        """.trimMargin()
    val `match` = """
        |{
        |    "foo": false
        |}
        """.trimMargin()
    run_test(schema, `match`, true, "DRAFT_2019_09")
    val `recursive_match` = """
        |{
        |    "foo": {
        |        "foo": false
        |    }
        |}
        """.trimMargin()
    run_test(schema, `recursive_match`, true, "DRAFT_2019_09")
    val `mismatch` = """
        |{
        |    "bar": false
        |}
        """.trimMargin()
    run_test(schema, `mismatch`, false, "DRAFT_2019_09")
    val `recursive_mismatch` = """
        |{
        |    "foo": {
        |        "bar": false
        |    }
        |}
        """.trimMargin()
    run_test(schema, `recursive_mismatch`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `$recursiveRef_without_using_nesting`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:4242/recursiveRef2/schema.json",
        |    "${'$'}defs": {
        |        "myobject": {
        |            "${'$'}id": "myobject.json",
        |            "${'$'}recursiveAnchor": true,
        |            "anyOf": [
        |                {
        |                    "type": "string"
        |                },
        |                {
        |                    "type": "object",
        |                    "additionalProperties": {
        |                        "${'$'}recursiveRef": "#"
        |                    }
        |                }
        |            ]
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "type": "integer"
        |        },
        |        {
        |            "${'$'}ref": "#/${'$'}defs/myobject"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `integer_matches_at_the_outer_level` = """1"""
    run_test(schema, `integer_matches_at_the_outer_level`, true, "DRAFT_2019_09")
    val `single_level_match` = """
        |{
        |    "foo": "hi"
        |}
        """.trimMargin()
    run_test(schema, `single_level_match`, true, "DRAFT_2019_09")
    val `integer_does_not_match_as_a_property_value` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `integer_does_not_match_as_a_property_value`, false, "DRAFT_2019_09")
    val `two_levels__properties_match_with_inner_definition` = """
        |{
        |    "foo": {
        |        "bar": "hi"
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__properties_match_with_inner_definition`, true, "DRAFT_2019_09")
    val `two_levels__no_match` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__no_match`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `$recursiveRef_with_nesting`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:4242/recursiveRef3/schema.json",
        |    "${'$'}recursiveAnchor": true,
        |    "${'$'}defs": {
        |        "myobject": {
        |            "${'$'}id": "myobject.json",
        |            "${'$'}recursiveAnchor": true,
        |            "anyOf": [
        |                {
        |                    "type": "string"
        |                },
        |                {
        |                    "type": "object",
        |                    "additionalProperties": {
        |                        "${'$'}recursiveRef": "#"
        |                    }
        |                }
        |            ]
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "type": "integer"
        |        },
        |        {
        |            "${'$'}ref": "#/${'$'}defs/myobject"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `integer_matches_at_the_outer_level` = """1"""
    run_test(schema, `integer_matches_at_the_outer_level`, true, "DRAFT_2019_09")
    val `single_level_match` = """
        |{
        |    "foo": "hi"
        |}
        """.trimMargin()
    run_test(schema, `single_level_match`, true, "DRAFT_2019_09")
    val `integer_now_matches_as_a_property_value` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `integer_now_matches_as_a_property_value`, true, "DRAFT_2019_09")
    val `two_levels__properties_match_with_inner_definition` = """
        |{
        |    "foo": {
        |        "bar": "hi"
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__properties_match_with_inner_definition`, true, "DRAFT_2019_09")
    val `two_levels__properties_match_with_$recursiveRef` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__properties_match_with_$recursiveRef`, true, "DRAFT_2019_09")
  }

  @Test
  public fun `$recursiveRef_with_$recursiveAnchor__false_works_like_$ref`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:4242/recursiveRef4/schema.json",
        |    "${'$'}recursiveAnchor": false,
        |    "${'$'}defs": {
        |        "myobject": {
        |            "${'$'}id": "myobject.json",
        |            "${'$'}recursiveAnchor": false,
        |            "anyOf": [
        |                {
        |                    "type": "string"
        |                },
        |                {
        |                    "type": "object",
        |                    "additionalProperties": {
        |                        "${'$'}recursiveRef": "#"
        |                    }
        |                }
        |            ]
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "type": "integer"
        |        },
        |        {
        |            "${'$'}ref": "#/${'$'}defs/myobject"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `integer_matches_at_the_outer_level` = """1"""
    run_test(schema, `integer_matches_at_the_outer_level`, true, "DRAFT_2019_09")
    val `single_level_match` = """
        |{
        |    "foo": "hi"
        |}
        """.trimMargin()
    run_test(schema, `single_level_match`, true, "DRAFT_2019_09")
    val `integer_does_not_match_as_a_property_value` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `integer_does_not_match_as_a_property_value`, false, "DRAFT_2019_09")
    val `two_levels__properties_match_with_inner_definition` = """
        |{
        |    "foo": {
        |        "bar": "hi"
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__properties_match_with_inner_definition`, true, "DRAFT_2019_09")
    val `two_levels__integer_does_not_match_as_a_property_value` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__integer_does_not_match_as_a_property_value`, false,
        "DRAFT_2019_09")
  }

  @Test
  public fun `$recursiveRef_with_no_$recursiveAnchor_works_like_$ref`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:4242/recursiveRef5/schema.json",
        |    "${'$'}defs": {
        |        "myobject": {
        |            "${'$'}id": "myobject.json",
        |            "${'$'}recursiveAnchor": false,
        |            "anyOf": [
        |                {
        |                    "type": "string"
        |                },
        |                {
        |                    "type": "object",
        |                    "additionalProperties": {
        |                        "${'$'}recursiveRef": "#"
        |                    }
        |                }
        |            ]
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "type": "integer"
        |        },
        |        {
        |            "${'$'}ref": "#/${'$'}defs/myobject"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `integer_matches_at_the_outer_level` = """1"""
    run_test(schema, `integer_matches_at_the_outer_level`, true, "DRAFT_2019_09")
    val `single_level_match` = """
        |{
        |    "foo": "hi"
        |}
        """.trimMargin()
    run_test(schema, `single_level_match`, true, "DRAFT_2019_09")
    val `integer_does_not_match_as_a_property_value` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `integer_does_not_match_as_a_property_value`, false, "DRAFT_2019_09")
    val `two_levels__properties_match_with_inner_definition` = """
        |{
        |    "foo": {
        |        "bar": "hi"
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__properties_match_with_inner_definition`, true, "DRAFT_2019_09")
    val `two_levels__integer_does_not_match_as_a_property_value` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    }
        |}
        """.trimMargin()
    run_test(schema, `two_levels__integer_does_not_match_as_a_property_value`, false,
        "DRAFT_2019_09")
  }

  @Test
  public fun `$recursiveRef_with_no_$recursiveAnchor_in_the_initial_target_schema_resource`():
      Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:4242/recursiveRef6/base.json",
        |    "${'$'}recursiveAnchor": true,
        |    "anyOf": [
        |        {
        |            "type": "boolean"
        |        },
        |        {
        |            "type": "object",
        |            "additionalProperties": {
        |                "${'$'}id": "http://localhost:4242/recursiveRef6/inner.json",
        |                "${'$'}comment": "there is no ${'$'}recursiveAnchor: true here, so we do NOT recurse to the base",
        |                "anyOf": [
        |                    {
        |                        "type": "integer"
        |                    },
        |                    {
        |                        "type": "object",
        |                        "additionalProperties": {
        |                            "${'$'}recursiveRef": "#"
        |                        }
        |                    }
        |                ]
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    val `leaf_node_does_not_match_no_recursion` = """
        |{
        |    "foo": true
        |}
        """.trimMargin()
    run_test(schema, `leaf_node_does_not_match_no_recursion`, false, "DRAFT_2019_09")
    val `leaf_node_matches__recursion_uses_the_inner_schema` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    }
        |}
        """.trimMargin()
    run_test(schema, `leaf_node_matches__recursion_uses_the_inner_schema`, true, "DRAFT_2019_09")
    val `leaf_node_does_not_match__recursion_uses_the_inner_schema` = """
        |{
        |    "foo": {
        |        "bar": true
        |    }
        |}
        """.trimMargin()
    run_test(schema, `leaf_node_does_not_match__recursion_uses_the_inner_schema`, false,
        "DRAFT_2019_09")
  }

  @Test
  public fun `$recursiveRef_with_no_$recursiveAnchor_in_the_outer_schema_resource`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:4242/recursiveRef7/base.json",
        |    "anyOf": [
        |        {
        |            "type": "boolean"
        |        },
        |        {
        |            "type": "object",
        |            "additionalProperties": {
        |                "${'$'}id": "http://localhost:4242/recursiveRef7/inner.json",
        |                "${'$'}recursiveAnchor": true,
        |                "anyOf": [
        |                    {
        |                        "type": "integer"
        |                    },
        |                    {
        |                        "type": "object",
        |                        "additionalProperties": {
        |                            "${'$'}recursiveRef": "#"
        |                        }
        |                    }
        |                ]
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    val `leaf_node_does_not_match_no_recursion` = """
        |{
        |    "foo": true
        |}
        """.trimMargin()
    run_test(schema, `leaf_node_does_not_match_no_recursion`, false, "DRAFT_2019_09")
    val `leaf_node_matches__recursion_only_uses_inner_schema` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    }
        |}
        """.trimMargin()
    run_test(schema, `leaf_node_matches__recursion_only_uses_inner_schema`, true, "DRAFT_2019_09")
    val `leaf_node_does_not_match__recursion_only_uses_inner_schema` = """
        |{
        |    "foo": {
        |        "bar": true
        |    }
        |}
        """.trimMargin()
    run_test(schema, `leaf_node_does_not_match__recursion_only_uses_inner_schema`, false,
        "DRAFT_2019_09")
  }

  @Test
  public fun `multiple_dynamic_paths_to_the_$recursiveRef_keyword`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "recursiveRef8_main.json",
        |    "${'$'}defs": {
        |        "inner": {
        |            "${'$'}id": "recursiveRef8_inner.json",
        |            "${'$'}recursiveAnchor": true,
        |            "title": "inner",
        |            "additionalProperties": {
        |                "${'$'}recursiveRef": "#"
        |            }
        |        }
        |    },
        |    "if": {
        |        "propertyNames": {
        |            "pattern": "^[a-m]"
        |        }
        |    },
        |    "then": {
        |        "title": "any type of node",
        |        "${'$'}id": "recursiveRef8_anyLeafNode.json",
        |        "${'$'}recursiveAnchor": true,
        |        "${'$'}ref": "recursiveRef8_inner.json"
        |    },
        |    "else": {
        |        "title": "integer node",
        |        "${'$'}id": "recursiveRef8_integerNode.json",
        |        "${'$'}recursiveAnchor": true,
        |        "type": [
        |            "object",
        |            "integer"
        |        ],
        |        "${'$'}ref": "recursiveRef8_inner.json"
        |    }
        |}
        """.trimMargin()
    val `recurse_to_anyLeafNode___floats_are_allowed` = """
        |{
        |    "alpha": 1.1
        |}
        """.trimMargin()
    run_test(schema, `recurse_to_anyLeafNode___floats_are_allowed`, true, "DRAFT_2019_09")
    val `recurse_to_integerNode___floats_are_not_allowed` = """
        |{
        |    "november": 1.1
        |}
        """.trimMargin()
    run_test(schema, `recurse_to_integerNode___floats_are_not_allowed`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `dynamic_$recursiveRef_destination_not_predictable_at_schema_compile_time`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "main.json",
        |    "${'$'}defs": {
        |        "inner": {
        |            "${'$'}id": "inner.json",
        |            "${'$'}recursiveAnchor": true,
        |            "title": "inner",
        |            "additionalProperties": {
        |                "${'$'}recursiveRef": "#"
        |            }
        |        }
        |    },
        |    "if": {
        |        "propertyNames": {
        |            "pattern": "^[a-m]"
        |        }
        |    },
        |    "then": {
        |        "title": "any type of node",
        |        "${'$'}id": "anyLeafNode.json",
        |        "${'$'}recursiveAnchor": true,
        |        "${'$'}ref": "main.json#/${'$'}defs/inner"
        |    },
        |    "else": {
        |        "title": "integer node",
        |        "${'$'}id": "integerNode.json",
        |        "${'$'}recursiveAnchor": true,
        |        "type": [
        |            "object",
        |            "integer"
        |        ],
        |        "${'$'}ref": "main.json#/${'$'}defs/inner"
        |    }
        |}
        """.trimMargin()
    val `numeric_node` = """
        |{
        |    "alpha": 1.1
        |}
        """.trimMargin()
    run_test(schema, `numeric_node`, true, "DRAFT_2019_09")
    val `integer_node` = """
        |{
        |    "november": 1.1
        |}
        """.trimMargin()
    run_test(schema, `integer_node`, false, "DRAFT_2019_09")
  }
}
