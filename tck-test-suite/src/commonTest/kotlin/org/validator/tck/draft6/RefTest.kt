package org.validator.tck.draft6

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
    run_test(schema, `match`, true, "DRAFT_6")
    val `recursive_match` = """
        |{
        |    "foo": {
        |        "foo": false
        |    }
        |}
        """.trimMargin()
    run_test(schema, `recursive_match`, true, "DRAFT_6")
    val `mismatch` = """
        |{
        |    "bar": false
        |}
        """.trimMargin()
    run_test(schema, `mismatch`, false, "DRAFT_6")
    val `recursive_mismatch` = """
        |{
        |    "foo": {
        |        "bar": false
        |    }
        |}
        """.trimMargin()
    run_test(schema, `recursive_mismatch`, false, "DRAFT_6")
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
    run_test(schema, `match`, true, "DRAFT_6")
    val `mismatch` = """
        |{
        |    "bar": true
        |}
        """.trimMargin()
    run_test(schema, `mismatch`, false, "DRAFT_6")
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
    run_test(schema, `match_array`, true, "DRAFT_6")
    val `mismatch_array` = """
        |[
        |    1,
        |    "foo"
        |]
        """.trimMargin()
    run_test(schema, `mismatch_array`, false, "DRAFT_6")
  }

  @Test
  public fun escaped_pointer_ref(): Unit {
    val schema = """
        |{
        |    "definitions": {
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
        |            "${'$'}ref": "#/definitions/tilde~0field"
        |        },
        |        "slash": {
        |            "${'$'}ref": "#/definitions/slash~1field"
        |        },
        |        "percent": {
        |            "${'$'}ref": "#/definitions/percent%25field"
        |        }
        |    }
        |}
        """.trimMargin()
    val `slash_invalid` = """
        |{
        |    "slash": "aoeu"
        |}
        """.trimMargin()
    run_test(schema, `slash_invalid`, false, "DRAFT_6")
    val `tilde_invalid` = """
        |{
        |    "tilde": "aoeu"
        |}
        """.trimMargin()
    run_test(schema, `tilde_invalid`, false, "DRAFT_6")
    val `percent_invalid` = """
        |{
        |    "percent": "aoeu"
        |}
        """.trimMargin()
    run_test(schema, `percent_invalid`, false, "DRAFT_6")
    val `slash_valid` = """
        |{
        |    "slash": 123
        |}
        """.trimMargin()
    run_test(schema, `slash_valid`, true, "DRAFT_6")
    val `tilde_valid` = """
        |{
        |    "tilde": 123
        |}
        """.trimMargin()
    run_test(schema, `tilde_valid`, true, "DRAFT_6")
    val `percent_valid` = """
        |{
        |    "percent": 123
        |}
        """.trimMargin()
    run_test(schema, `percent_valid`, true, "DRAFT_6")
  }

  @Test
  public fun nested_refs(): Unit {
    val schema = """
        |{
        |    "definitions": {
        |        "a": {
        |            "type": "integer"
        |        },
        |        "b": {
        |            "${'$'}ref": "#/definitions/a"
        |        },
        |        "c": {
        |            "${'$'}ref": "#/definitions/b"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "${'$'}ref": "#/definitions/c"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `nested_ref_valid` = """5"""
    run_test(schema, `nested_ref_valid`, true, "DRAFT_6")
    val `nested_ref_invalid` = """"a""""
    run_test(schema, `nested_ref_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun ref_overrides_any_sibling_keywords(): Unit {
    val schema = """
        |{
        |    "definitions": {
        |        "reffed": {
        |            "type": "array"
        |        }
        |    },
        |    "properties": {
        |        "foo": {
        |            "${'$'}ref": "#/definitions/reffed",
        |            "maxItems": 2
        |        }
        |    }
        |}
        """.trimMargin()
    val `ref_valid` = """
        |{
        |    "foo": [
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `ref_valid`, true, "DRAFT_6")
    val `ref_valid__maxItems_ignored` = """
        |{
        |    "foo": [
        |        1,
        |        2,
        |        3
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `ref_valid__maxItems_ignored`, true, "DRAFT_6")
    val `ref_invalid` = """
        |{
        |    "foo": "string"
        |}
        """.trimMargin()
    run_test(schema, `ref_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun `$ref_prevents_a_sibling_$id_from_changing_the_base_uri`(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/sibling_id/base/",
        |    "definitions": {
        |        "foo": {
        |            "${'$'}id": "http://localhost:1234/sibling_id/foo.json",
        |            "minimum": 2
        |        },
        |        "base_foo": {
        |            "${'$'}comment": "this canonical uri is http://localhost:1234/sibling_id/base/foo.json",
        |            "${'$'}id": "foo.json",
        |            "minimum": 5
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "${'$'}comment": "${'$'}ref resolves to http://localhost:1234/sibling_id/base/foo.json, not ttp://localhost:1234/sibling_id/foo.json",
        |            "${'$'}id": "http://localhost:1234/sibling_id/",
        |            "${'$'}ref": "foo.json"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `$ref_resolves_to__definitions_foo__data_validates` = """10"""
    run_test(schema, `$ref_resolves_to__definitions_foo__data_validates`, true, "DRAFT_6")
    val `$ref_resolves_to__definitions_foo__data_does_not_validate` = """1"""
    run_test(schema, `$ref_resolves_to__definitions_foo__data_does_not_validate`, false, "DRAFT_6")
  }

  @Test
  public fun remote_ref__containing_refs_itself(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "http://json-schema.org/draft-06/schema#"
        |}
        """.trimMargin()
    val `remote_ref_valid` = """
        |{
        |    "minLength": 1
        |}
        """.trimMargin()
    run_test(schema, `remote_ref_valid`, true, "DRAFT_6")
    val `remote_ref_invalid` = """
        |{
        |    "minLength": -1
        |}
        """.trimMargin()
    run_test(schema, `remote_ref_invalid`, false, "DRAFT_6")
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
    run_test(schema, `property_named_$ref_valid`, true, "DRAFT_6")
    val `property_named_$ref_invalid` = """
        |{
        |    "${'$'}ref": 2
        |}
        """.trimMargin()
    run_test(schema, `property_named_$ref_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun `property_named_$ref__containing_an_actual_$ref`(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "${'$'}ref": {
        |            "${'$'}ref": "#/definitions/is-string"
        |        }
        |    },
        |    "definitions": {
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
    run_test(schema, `property_named_$ref_valid`, true, "DRAFT_6")
    val `property_named_$ref_invalid` = """
        |{
        |    "${'$'}ref": 2
        |}
        """.trimMargin()
    run_test(schema, `property_named_$ref_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun `$ref_to_boolean_schema_true`(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "${'$'}ref": "#/definitions/bool"
        |        }
        |    ],
        |    "definitions": {
        |        "bool": true
        |    }
        |}
        """.trimMargin()
    val `any_value_is_valid` = """"foo""""
    run_test(schema, `any_value_is_valid`, true, "DRAFT_6")
  }

  @Test
  public fun `$ref_to_boolean_schema_false`(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "${'$'}ref": "#/definitions/bool"
        |        }
        |    ],
        |    "definitions": {
        |        "bool": false
        |    }
        |}
        """.trimMargin()
    val `any_value_is_invalid` = """"foo""""
    run_test(schema, `any_value_is_invalid`, false, "DRAFT_6")
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
        |    "definitions": {
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
    run_test(schema, `valid_tree`, true, "DRAFT_6")
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
    run_test(schema, `invalid_tree`, false, "DRAFT_6")
  }

  @Test
  public fun refs_with_quote(): Unit {
    val schema = """
        |{
        |    "properties": {
        |        "foo\"bar": {
        |            "${'$'}ref": "#/definitions/foo%22bar"
        |        }
        |    },
        |    "definitions": {
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
    run_test(schema, `object_with_numbers_is_valid`, true, "DRAFT_6")
    val `object_with_strings_is_invalid` = """
        |{
        |    "foo\"bar": "1"
        |}
        """.trimMargin()
    run_test(schema, `object_with_strings_is_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun Location_independent_identifier(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "${'$'}ref": "#foo"
        |        }
        |    ],
        |    "definitions": {
        |        "A": {
        |            "${'$'}id": "#foo",
        |            "type": "integer"
        |        }
        |    }
        |}
        """.trimMargin()
    val `match` = """1"""
    run_test(schema, `match`, true, "DRAFT_6")
    val `mismatch` = """"a""""
    run_test(schema, `mismatch`, false, "DRAFT_6")
  }

  @Test
  public fun Location_independent_identifier_with_base_URI_change_in_subschema(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/root",
        |    "allOf": [
        |        {
        |            "${'$'}ref": "http://localhost:1234/nested.json#foo"
        |        }
        |    ],
        |    "definitions": {
        |        "A": {
        |            "${'$'}id": "nested.json",
        |            "definitions": {
        |                "B": {
        |                    "${'$'}id": "#foo",
        |                    "type": "integer"
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `match` = """1"""
    run_test(schema, `match`, true, "DRAFT_6")
    val `mismatch` = """"a""""
    run_test(schema, `mismatch`, false, "DRAFT_6")
  }

  @Test
  public fun `naive_replacement_of_$ref_with_its_destination_is_not_correct`(): Unit {
    val schema = """
        |{
        |    "definitions": {
        |        "a_string": {
        |            "type": "string"
        |        }
        |    },
        |    "enum": [
        |        {
        |            "${'$'}ref": "#/definitions/a_string"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `do_not_evaluate_the_$ref_inside_the_enum__matching_any_string` = """"this is a string""""
    run_test(schema, `do_not_evaluate_the_$ref_inside_the_enum__matching_any_string`, false,
        "DRAFT_6")
    val `do_not_evaluate_the_$ref_inside_the_enum__definition_exact_match` = """
        |{
        |    "type": "string"
        |}
        """.trimMargin()
    run_test(schema, `do_not_evaluate_the_$ref_inside_the_enum__definition_exact_match`, false,
        "DRAFT_6")
    val `match_the_enum_exactly` = """
        |{
        |    "${'$'}ref": "#/definitions/a_string"
        |}
        """.trimMargin()
    run_test(schema, `match_the_enum_exactly`, true, "DRAFT_6")
  }

  @Test
  public fun refs_with_relative_uris_and_defs(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://example.com/schema-relative-uri-defs1.json",
        |    "properties": {
        |        "foo": {
        |            "${'$'}id": "schema-relative-uri-defs2.json",
        |            "definitions": {
        |                "inner": {
        |                    "properties": {
        |                        "bar": {
        |                            "type": "string"
        |                        }
        |                    }
        |                }
        |            },
        |            "allOf": [
        |                {
        |                    "${'$'}ref": "#/definitions/inner"
        |                }
        |            ]
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "${'$'}ref": "schema-relative-uri-defs2.json"
        |        }
        |    ]
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
    run_test(schema, `invalid_on_inner_field`, false, "DRAFT_6")
    val `invalid_on_outer_field` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `invalid_on_outer_field`, false, "DRAFT_6")
    val `valid_on_both_fields` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": "a"
        |}
        """.trimMargin()
    run_test(schema, `valid_on_both_fields`, true, "DRAFT_6")
  }

  @Test
  public fun relative_refs_with_absolute_uris_and_defs(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://example.com/schema-refs-absolute-uris-defs1.json",
        |    "properties": {
        |        "foo": {
        |            "${'$'}id": "http://example.com/schema-refs-absolute-uris-defs2.json",
        |            "definitions": {
        |                "inner": {
        |                    "properties": {
        |                        "bar": {
        |                            "type": "string"
        |                        }
        |                    }
        |                }
        |            },
        |            "allOf": [
        |                {
        |                    "${'$'}ref": "#/definitions/inner"
        |                }
        |            ]
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "${'$'}ref": "schema-refs-absolute-uris-defs2.json"
        |        }
        |    ]
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
    run_test(schema, `invalid_on_inner_field`, false, "DRAFT_6")
    val `invalid_on_outer_field` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `invalid_on_outer_field`, false, "DRAFT_6")
    val `valid_on_both_fields` = """
        |{
        |    "foo": {
        |        "bar": "a"
        |    },
        |    "bar": "a"
        |}
        """.trimMargin()
    run_test(schema, `valid_on_both_fields`, true, "DRAFT_6")
  }
}
