package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class RefTest {
  @Test
  public fun root_pointer_ref(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |            "${'$'}ref": "#"
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
  public fun relative_pointer_ref_to_object(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo": {
        |            "type": "integer"
        |        },
        |        "bar": {
        |            "${'$'}ref": "#/properties/foo"
        |        }
        |    }
        |}
        """.trimMargin()
    val `match` = """
        |{
        |    "bar": 3
        |}
        """.trimMargin()
    run_test(schema, `match`, true, "DRAFT_2019_09")
    val `mismatch` = """
        |{
        |    "bar": true
        |}
        """.trimMargin()
    run_test(schema, `mismatch`, false, "DRAFT_2019_09")
  }

  @Test
  public fun relative_pointer_ref_to_array(): Unit {
    val schema = """
        |{
        |    "items": [
        |        {
        |            "type": "integer"
        |        },
        |        {
        |            "${'$'}ref": "#/items/0"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `match_array` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `match_array`, true, "DRAFT_2019_09")
    val `mismatch_array` = """
        |[
        |    1,
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `mismatch_array`, false, "DRAFT_2019_09")
  }

  @Test
  public fun escaped_pointer_ref(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "tilde~field": {
        |            "type": "integer"
        |        },
        |        "slash/field": {
        |            "type": "integer"
        |        },
        |        "percent%field": {
        |            "type": "integer"
        |        }
        |    },
        |    "properties": {
        |        "tilde": {
        |            "${'$'}ref": "#/${'$'}defs/tilde~0field"
        |        },
        |        "slash": {
        |            "${'$'}ref": "#/${'$'}defs/slash~1field"
        |        },
        |        "percent": {
        |            "${'$'}ref": "#/${'$'}defs/percent%25field"
        |        }
        |    }
        |}
        """.trimMargin()
    val `slash_invalid` = """
        |{
        |    "slash": "aoeu"
        |}
        """.trimMargin()
    run_test(schema, `slash_invalid`, false, "DRAFT_2019_09")
    val `tilde_invalid` = """
        |{
        |    "tilde": "aoeu"
        |}
        """.trimMargin()
    run_test(schema, `tilde_invalid`, false, "DRAFT_2019_09")
    val `percent_invalid` = """
        |{
        |    "percent": "aoeu"
        |}
        """.trimMargin()
    run_test(schema, `percent_invalid`, false, "DRAFT_2019_09")
    val `slash_valid` = """
        |{
        |    "slash": 123
        |}
        """.trimMargin()
    run_test(schema, `slash_valid`, true, "DRAFT_2019_09")
    val `tilde_valid` = """
        |{
        |    "tilde": 123
        |}
        """.trimMargin()
    run_test(schema, `tilde_valid`, true, "DRAFT_2019_09")
    val `percent_valid` = """
        |{
        |    "percent": 123
        |}
        """.trimMargin()
    run_test(schema, `percent_valid`, true, "DRAFT_2019_09")
  }

  @Test
  public fun nested_refs(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "a": {
        |            "type": "integer"
        |        },
        |        "b": {
        |            "${'$'}ref": "#/${'$'}defs/a"
        |        },
        |        "c": {
        |            "${'$'}ref": "#/${'$'}defs/b"
        |        }
        |    },
        |    "${'$'}ref": "#/${'$'}defs/c"
        |}
        """.trimMargin()
    val `nested_ref_valid` = """5"""
    run_test(schema, `nested_ref_valid`, true, "DRAFT_2019_09")
    val `nested_ref_invalid` = """"a""""
    run_test(schema, `nested_ref_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun ref_applies_alongside_sibling_keywords(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "reffed": {
        |            "type": "array"
        |        }
        |    },
        |    "properties": {
        |        "foo": {
        |            "${'$'}ref": "#/${'$'}defs/reffed",
        |            "maxItems": 2
        |        }
        |    }
        |}
        """.trimMargin()
    val `ref_valid__maxItems_valid` = """
        |{
        |    "foo": [
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `ref_valid__maxItems_valid`, true, "DRAFT_2019_09")
    val `ref_valid__maxItems_invalid` = """
        |{
        |    "foo": [
        |        1,
        |        2,
        |        3
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `ref_valid__maxItems_invalid`, false, "DRAFT_2019_09")
    val `ref_invalid` = """
        |{
        |    "foo": "string"
        |}
        """.trimMargin()
    run_test(schema, `ref_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun remote_ref__containing_refs_itself(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "https://json-schema.org/draft/2019-09/schema"
        |}
        """.trimMargin()
    val `remote_ref_valid` = """
        |{
        |    "minLength": 1
        |}
        """.trimMargin()
    run_test(schema, `remote_ref_valid`, true, "DRAFT_2019_09")
    val `remote_ref_invalid` = """
        |{
        |    "minLength": -1
        |}
        """.trimMargin()
    run_test(schema, `remote_ref_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `property_named_$ref_that_is_not_a_reference`(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "${'$'}ref": {
        |            "type": "string"
        |        }
        |    }
        |}
        """.trimMargin()
    val `property_named_$ref_valid` = """
        |{
        |    "${'$'}ref": "a"
        |}
        """.trimMargin()
    run_test(schema, `property_named_$ref_valid`, true, "DRAFT_2019_09")
    val `property_named_$ref_invalid` = """
        |{
        |    "${'$'}ref": 2
        |}
        """.trimMargin()
    run_test(schema, `property_named_$ref_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `property_named_$ref__containing_an_actual_$ref`(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "${'$'}ref": {
        |            "${'$'}ref": "#/${'$'}defs/is-string"
        |        }
        |    },
        |    "${'$'}defs": {
        |        "is-string": {
        |            "type": "string"
        |        }
        |    }
        |}
        """.trimMargin()
    val `property_named_$ref_valid` = """
        |{
        |    "${'$'}ref": "a"
        |}
        """.trimMargin()
    run_test(schema, `property_named_$ref_valid`, true, "DRAFT_2019_09")
    val `property_named_$ref_invalid` = """
        |{
        |    "${'$'}ref": 2
        |}
        """.trimMargin()
    run_test(schema, `property_named_$ref_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `$ref_to_boolean_schema_true`(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "#/${'$'}defs/bool",
        |    "${'$'}defs": {
        |        "bool": true
        |    }
        |}
        """.trimMargin()
    val `any_value_is_valid` = """"foo""""
    run_test(schema, `any_value_is_valid`, true, "DRAFT_2019_09")
  }

  @Test
  public fun `$ref_to_boolean_schema_false`(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "#/${'$'}defs/bool",
        |    "${'$'}defs": {
        |        "bool": false
        |    }
        |}
        """.trimMargin()
    val `any_value_is_invalid` = """"foo""""
    run_test(schema, `any_value_is_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun Recursive_references_between_schemas(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/tree",
        |    "description": "tree of nodes",
        |    "type": "object",
        |    "properties": {
        |        "meta": {
        |            "type": "string"
        |        },
        |        "nodes": {
        |            "type": "array",
        |            "items": {
        |                "${'$'}ref": "node"
        |            }
        |        }
        |    },
        |    "required": [
        |        "meta",
        |        "nodes"
        |    ],
        |    "${'$'}defs": {
        |        "node": {
        |            "${'$'}id": "http://localhost:1234/node",
        |            "description": "node",
        |            "type": "object",
        |            "properties": {
        |                "value": {
        |                    "type": "number"
        |                },
        |                "subtree": {
        |                    "${'$'}ref": "tree"
        |                }
        |            },
        |            "required": [
        |                "value"
        |            ]
        |        }
        |    }
        |}
        """.trimMargin()
    val `valid_tree` = """
        |{
        |    "meta": "root",
        |    "nodes": [
        |        {
        |            "value": 1,
        |            "subtree": {
        |                "meta": "child",
        |                "nodes": [
        |                    {
        |                        "value": 1.1
        |                    },
        |                    {
        |                        "value": 1.2
        |                    }
        |                ]
        |            }
        |        },
        |        {
        |            "value": 2,
        |            "subtree": {
        |                "meta": "child",
        |                "nodes": [
        |                    {
        |                        "value": 2.1
        |                    },
        |                    {
        |                        "value": 2.2
        |                    }
        |                ]
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `valid_tree`, true, "DRAFT_2019_09")
    val `invalid_tree` = """
        |{
        |    "meta": "root",
        |    "nodes": [
        |        {
        |            "value": 1,
        |            "subtree": {
        |                "meta": "child",
        |                "nodes": [
        |                    {
        |                        "value": "string is invalid"
        |                    },
        |                    {
        |                        "value": 1.2
        |                    }
        |                ]
        |            }
        |        },
        |        {
        |            "value": 2,
        |            "subtree": {
        |                "meta": "child",
        |                "nodes": [
        |                    {
        |                        "value": 2.1
        |                    },
        |                    {
        |                        "value": 2.2
        |                    }
        |                ]
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `invalid_tree`, false, "DRAFT_2019_09")
  }

  @Test
  public fun refs_with_quote(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo\"bar": {
        |            "${'$'}ref": "#/${'$'}defs/foo%22bar"
        |        }
        |    },
        |    "${'$'}defs": {
        |        "foo\"bar": {
        |            "type": "number"
        |        }
        |    }
        |}
        """.trimMargin()
    val `object_with_numbers_is_valid` = """
        |{
        |    "foo\"bar": 1
        |}
        """.trimMargin()
    run_test(schema, `object_with_numbers_is_valid`, true, "DRAFT_2019_09")
    val `object_with_strings_is_invalid` = """
        |{
        |    "foo\"bar": "1"
        |}
        """.trimMargin()
    run_test(schema, `object_with_strings_is_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun ref_creates_new_scope_when_adjacent_to_keywords(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "A": {
        |            "unevaluatedProperties": false
        |        }
        |    },
        |    "properties": {
        |        "prop1": {
        |            "type": "string"
        |        }
        |    },
        |    "${'$'}ref": "#/${'$'}defs/A"
        |}
        """.trimMargin()
    val `referenced_subschema_doesn't_see_annotations_from_properties` = """
        |{
        |    "prop1": "match"
        |}
        """.trimMargin()
    run_test(schema, `referenced_subschema_doesn't_see_annotations_from_properties`, false,
        "DRAFT_2019_09")
  }

  @Test
  public fun `naive_replacement_of_$ref_with_its_destination_is_not_correct`(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "a_string": {
        |            "type": "string"
        |        }
        |    },
        |    "enum": [
        |        {
        |            "${'$'}ref": "#/${'$'}defs/a_string"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `do_not_evaluate_the_$ref_inside_the_enum__matching_any_string` = """"this is a string""""
    run_test(schema, `do_not_evaluate_the_$ref_inside_the_enum__matching_any_string`, false,
        "DRAFT_2019_09")
    val `do_not_evaluate_the_$ref_inside_the_enum__definition_exact_match` = """
        |{
        |    "type": "string"
        |}
        """.trimMargin()
    run_test(schema, `do_not_evaluate_the_$ref_inside_the_enum__definition_exact_match`, false,
        "DRAFT_2019_09")
    val `match_the_enum_exactly` = """
        |{
        |    "${'$'}ref": "#/${'$'}defs/a_string"
        |}
        """.trimMargin()
    run_test(schema, `match_the_enum_exactly`, true, "DRAFT_2019_09")
  }

  @Test
  public fun refs_with_relative_uris_and_defs(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://example.com/schema-relative-uri-defs1.json",
        |    "properties": {
        |        "foo": {
        |            "${'$'}id": "schema-relative-uri-defs2.json",
        |            "${'$'}defs": {
        |                "inner": {
        |                    "properties": {
        |                        "bar": {
        |                            "type": "string"
        |                        }
        |                    }
        |                }
        |            },
        |            "${'$'}ref": "#/${'$'}defs/inner"
        |        }
        |    },
        |    "${'$'}ref": "schema-relative-uri-defs2.json"
        |}
        """.trimMargin()
    val `invalid_on_inner_field` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    },
        |    "bar": "a"
        |}
        """.trimMargin()
    run_test(schema, `invalid_on_inner_field`, false, "DRAFT_2019_09")
    val `invalid_on_outer_field` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `invalid_on_outer_field`, false, "DRAFT_2019_09")
    val `valid_on_both_fields` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": "a"
        |}
        """.trimMargin()
    run_test(schema, `valid_on_both_fields`, true, "DRAFT_2019_09")
  }

  @Test
  public fun relative_refs_with_absolute_uris_and_defs(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://example.com/schema-refs-absolute-uris-defs1.json",
        |    "properties": {
        |        "foo": {
        |            "${'$'}id": "http://example.com/schema-refs-absolute-uris-defs2.json",
        |            "${'$'}defs": {
        |                "inner": {
        |                    "properties": {
        |                        "bar": {
        |                            "type": "string"
        |                        }
        |                    }
        |                }
        |            },
        |            "${'$'}ref": "#/${'$'}defs/inner"
        |        }
        |    },
        |    "${'$'}ref": "schema-refs-absolute-uris-defs2.json"
        |}
        """.trimMargin()
    val `invalid_on_inner_field` = """
        |{
        |    "foo": {
        |        "bar": 1
        |    },
        |    "bar": "a"
        |}
        """.trimMargin()
    run_test(schema, `invalid_on_inner_field`, false, "DRAFT_2019_09")
    val `invalid_on_outer_field` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `invalid_on_outer_field`, false, "DRAFT_2019_09")
    val `valid_on_both_fields` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": "a"
        |}
        """.trimMargin()
    run_test(schema, `valid_on_both_fields`, true, "DRAFT_2019_09")
  }

  @Test
  public fun `$id_must_be_resolved_against_nearest_parent__not_just_immediate_parent`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://example.com/a.json",
        |    "${'$'}defs": {
        |        "x": {
        |            "${'$'}id": "http://example.com/b/c.json",
        |            "not": {
        |                "${'$'}defs": {
        |                    "y": {
        |                        "${'$'}id": "d.json",
        |                        "type": "number"
        |                    }
        |                }
        |            }
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "${'$'}ref": "http://example.com/b/d.json"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `number_should_pass` = """1"""
    run_test(schema, `number_should_pass`, true, "DRAFT_2019_09")
    val `non_number_should_fail` = """"a""""
    run_test(schema, `non_number_should_fail`, false, "DRAFT_2019_09")
  }
}
