package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class UnevaluatedPropertiesTest {
  @Test
  public fun unevaluatedProperties_true(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "unevaluatedProperties": true
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_schema(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "unevaluatedProperties": {
        |        "type": "string",
        |        "minLength": 3
        |    }
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_valid_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_valid_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_invalid_unevaluated_properties` = """
        |{
        |    "foo": "fo"
        |}
        """.trimMargin()
    run_test(schema, `with_invalid_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_false(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_adjacent_properties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_adjacent_patternProperties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "patternProperties": {
        |        "^foo": {
        |            "type": "string"
        |        }
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_adjacent_additionalProperties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "additionalProperties": true,
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_additional_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_additional_properties`, true, "DRAFT_2019_09")
    val `with_additional_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_additional_properties`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_nested_properties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "properties": {
        |                "bar": {
        |                    "type": "string"
        |                }
        |            }
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_additional_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_no_additional_properties`, true, "DRAFT_2019_09")
    val `with_additional_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `with_additional_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_nested_patternProperties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "patternProperties": {
        |                "^bar": {
        |                    "type": "string"
        |                }
        |            }
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_additional_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_no_additional_properties`, true, "DRAFT_2019_09")
    val `with_additional_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `with_additional_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_nested_additionalProperties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "additionalProperties": true
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_additional_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_additional_properties`, true, "DRAFT_2019_09")
    val `with_additional_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_additional_properties`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_nested_unevaluatedProperties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "unevaluatedProperties": true
        |        }
        |    ],
        |    "unevaluatedProperties": {
        |        "type": "string",
        |        "maxLength": 2
        |    }
        |}
        """.trimMargin()
    val `with_no_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_nested_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_nested_unevaluated_properties`, true, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_anyOf(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "properties": {
        |                "bar": {
        |                    "const": "bar"
        |                }
        |            },
        |            "required": [
        |                "bar"
        |            ]
        |        },
        |        {
        |            "properties": {
        |                "baz": {
        |                    "const": "baz"
        |                }
        |            },
        |            "required": [
        |                "baz"
        |            ]
        |        },
        |        {
        |            "properties": {
        |                "quux": {
        |                    "const": "quux"
        |                }
        |            },
        |            "required": [
        |                "quux"
        |            ]
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `when_one_matches_and_has_no_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `when_one_matches_and_has_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `when_one_matches_and_has_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar",
        |    "baz": "not-baz"
        |}
        """.trimMargin()
    run_test(schema, `when_one_matches_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
    val `when_two_match_and_has_no_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_two_match_and_has_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `when_two_match_and_has_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar",
        |    "baz": "baz",
        |    "quux": "not-quux"
        |}
        """.trimMargin()
    run_test(schema, `when_two_match_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_oneOf(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "oneOf": [
        |        {
        |            "properties": {
        |                "bar": {
        |                    "const": "bar"
        |                }
        |            },
        |            "required": [
        |                "bar"
        |            ]
        |        },
        |        {
        |            "properties": {
        |                "baz": {
        |                    "const": "baz"
        |                }
        |            },
        |            "required": [
        |                "baz"
        |            ]
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar",
        |    "quux": "quux"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_not(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "not": {
        |        "not": {
        |            "properties": {
        |                "bar": {
        |                    "const": "bar"
        |                }
        |            },
        |            "required": [
        |                "bar"
        |            ]
        |        }
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_if_then_else(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "if": {
        |        "properties": {
        |            "foo": {
        |                "const": "then"
        |            }
        |        },
        |        "required": [
        |            "foo"
        |        ]
        |    },
        |    "then": {
        |        "properties": {
        |            "bar": {
        |                "type": "string"
        |            }
        |        },
        |        "required": [
        |            "bar"
        |        ]
        |    },
        |    "else": {
        |        "properties": {
        |            "baz": {
        |                "type": "string"
        |            }
        |        },
        |        "required": [
        |            "baz"
        |        ]
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `when_if_is_true_and_has_no_unevaluated_properties` = """
        |{
        |    "foo": "then",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_true_and_has_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `when_if_is_true_and_has_unevaluated_properties` = """
        |{
        |    "foo": "then",
        |    "bar": "bar",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_true_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
    val `when_if_is_false_and_has_no_unevaluated_properties` = """
        |{
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_false_and_has_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `when_if_is_false_and_has_unevaluated_properties` = """
        |{
        |    "foo": "else",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_false_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_if_then_else__then_not_defined(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "if": {
        |        "properties": {
        |            "foo": {
        |                "const": "then"
        |            }
        |        },
        |        "required": [
        |            "foo"
        |        ]
        |    },
        |    "else": {
        |        "properties": {
        |            "baz": {
        |                "type": "string"
        |            }
        |        },
        |        "required": [
        |            "baz"
        |        ]
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `when_if_is_true_and_has_no_unevaluated_properties` = """
        |{
        |    "foo": "then",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_true_and_has_no_unevaluated_properties`, false, "DRAFT_2019_09")
    val `when_if_is_true_and_has_unevaluated_properties` = """
        |{
        |    "foo": "then",
        |    "bar": "bar",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_true_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
    val `when_if_is_false_and_has_no_unevaluated_properties` = """
        |{
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_false_and_has_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `when_if_is_false_and_has_unevaluated_properties` = """
        |{
        |    "foo": "else",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_false_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_if_then_else__else_not_defined(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "if": {
        |        "properties": {
        |            "foo": {
        |                "const": "then"
        |            }
        |        },
        |        "required": [
        |            "foo"
        |        ]
        |    },
        |    "then": {
        |        "properties": {
        |            "bar": {
        |                "type": "string"
        |            }
        |        },
        |        "required": [
        |            "bar"
        |        ]
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `when_if_is_true_and_has_no_unevaluated_properties` = """
        |{
        |    "foo": "then",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_true_and_has_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `when_if_is_true_and_has_unevaluated_properties` = """
        |{
        |    "foo": "then",
        |    "bar": "bar",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_true_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
    val `when_if_is_false_and_has_no_unevaluated_properties` = """
        |{
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_false_and_has_no_unevaluated_properties`, false, "DRAFT_2019_09")
    val `when_if_is_false_and_has_unevaluated_properties` = """
        |{
        |    "foo": "else",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `when_if_is_false_and_has_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_dependentSchemas(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "dependentSchemas": {
        |        "foo": {
        |            "properties": {
        |                "bar": {
        |                    "const": "bar"
        |                }
        |            },
        |            "required": [
        |                "bar"
        |            ]
        |        }
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun unevaluatedProperties_with_boolean_schemas(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "allOf": [
        |        true
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `unevaluatedProperties_with_$ref`(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "${'$'}ref": "#/${'$'}defs/bar",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "unevaluatedProperties": false,
        |    "${'$'}defs": {
        |        "bar": {
        |            "properties": {
        |                "bar": {
        |                    "type": "string"
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `with_no_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_no_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar",
        |    "baz": "baz"
        |}
        """.trimMargin()
    run_test(schema, `with_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `unevaluatedProperties_can't_see_inside_cousins`(): Unit {
    val schema = """
        |{
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": true
        |            }
        |        },
        |        {
        |            "unevaluatedProperties": false
        |        }
        |    ]
        |}
        """.trimMargin()
    val `always_fails` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `always_fails`, false, "DRAFT_2019_09")
  }

  @Test
  public fun nested_unevaluatedProperties__outer_false__inner_true__properties_outside(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "unevaluatedProperties": true
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_nested_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_nested_unevaluated_properties`, true, "DRAFT_2019_09")
  }

  @Test
  public fun nested_unevaluatedProperties__outer_false__inner_true__properties_inside(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                    "type": "string"
        |                }
        |            },
        |            "unevaluatedProperties": true
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `with_no_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_nested_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_nested_unevaluated_properties`, true, "DRAFT_2019_09")
  }

  @Test
  public fun nested_unevaluatedProperties__outer_true__inner_false__properties_outside(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "string"
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "unevaluatedProperties": false
        |        }
        |    ],
        |    "unevaluatedProperties": true
        |}
        """.trimMargin()
    val `with_no_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_nested_unevaluated_properties`, false, "DRAFT_2019_09")
    val `with_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_nested_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun nested_unevaluatedProperties__outer_true__inner_false__properties_inside(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                    "type": "string"
        |                }
        |            },
        |            "unevaluatedProperties": false
        |        }
        |    ],
        |    "unevaluatedProperties": true
        |}
        """.trimMargin()
    val `with_no_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_nested_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_nested_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun cousin_unevaluatedProperties__true_and_false__true_with_properties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                    "type": "string"
        |                }
        |            },
        |            "unevaluatedProperties": true
        |        },
        |        {
        |            "unevaluatedProperties": false
        |        }
        |    ]
        |}
        """.trimMargin()
    val `with_no_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_nested_unevaluated_properties`, false, "DRAFT_2019_09")
    val `with_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_nested_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun cousin_unevaluatedProperties__true_and_false__false_with_properties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "allOf": [
        |        {
        |            "unevaluatedProperties": true
        |        },
        |        {
        |            "properties": {
        |                "foo": {
        |                    "type": "string"
        |                }
        |            },
        |            "unevaluatedProperties": false
        |        }
        |    ]
        |}
        """.trimMargin()
    val `with_no_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo"
        |}
        """.trimMargin()
    run_test(schema, `with_no_nested_unevaluated_properties`, true, "DRAFT_2019_09")
    val `with_nested_unevaluated_properties` = """
        |{
        |    "foo": "foo",
        |    "bar": "bar"
        |}
        """.trimMargin()
    run_test(schema, `with_nested_unevaluated_properties`, false, "DRAFT_2019_09")
  }

  @Test
  public fun property_is_evaluated_in_an_uncle_schema_to_unevaluatedProperties(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "foo": {
        |            "type": "object",
        |            "properties": {
        |                "bar": {
        |                    "type": "string"
        |                }
        |            },
        |            "unevaluatedProperties": false
        |        }
        |    },
        |    "anyOf": [
        |        {
        |            "properties": {
        |                "foo": {
        |                    "properties": {
        |                        "faz": {
        |                            "type": "string"
        |                        }
        |                    }
        |                }
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    val `no_extra_properties` = """
        |{
        |    "foo": {
        |        "bar": "test"
        |    }
        |}
        """.trimMargin()
    run_test(schema, `no_extra_properties`, true, "DRAFT_2019_09")
    val `uncle_keyword_evaluation_is_not_significant` = """
        |{
        |    "foo": {
        |        "bar": "test",
        |        "faz": "test"
        |    }
        |}
        """.trimMargin()
    run_test(schema, `uncle_keyword_evaluation_is_not_significant`, false, "DRAFT_2019_09")
  }

  @Test
  public fun in_place_applicator_siblings__allOf_has_unevaluated(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": true
        |            },
        |            "unevaluatedProperties": false
        |        }
        |    ],
        |    "anyOf": [
        |        {
        |            "properties": {
        |                "bar": true
        |            }
        |        }
        |    ]
        |}
        """.trimMargin()
    val `base_case__both_properties_present` = """
        |{
        |    "foo": 1,
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `base_case__both_properties_present`, false, "DRAFT_2019_09")
    val `in_place_applicator_siblings__bar_is_missing` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `in_place_applicator_siblings__bar_is_missing`, true, "DRAFT_2019_09")
    val `in_place_applicator_siblings__foo_is_missing` = """
        |{
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `in_place_applicator_siblings__foo_is_missing`, false, "DRAFT_2019_09")
  }

  @Test
  public fun in_place_applicator_siblings__anyOf_has_unevaluated(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "allOf": [
        |        {
        |            "properties": {
        |                "foo": true
        |            }
        |        }
        |    ],
        |    "anyOf": [
        |        {
        |            "properties": {
        |                "bar": true
        |            },
        |            "unevaluatedProperties": false
        |        }
        |    ]
        |}
        """.trimMargin()
    val `base_case__both_properties_present` = """
        |{
        |    "foo": 1,
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `base_case__both_properties_present`, false, "DRAFT_2019_09")
    val `in_place_applicator_siblings__bar_is_missing` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `in_place_applicator_siblings__bar_is_missing`, false, "DRAFT_2019_09")
    val `in_place_applicator_siblings__foo_is_missing` = """
        |{
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `in_place_applicator_siblings__foo_is_missing`, true, "DRAFT_2019_09")
  }

  @Test
  public fun `unevaluatedProperties_+_single_cyclic_ref`(): Unit {
    val schema = """
        |{
        |    "type": "object",
        |    "properties": {
        |        "x": {
        |            "${'$'}ref": "#"
        |        }
        |    },
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `Empty_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `Empty_is_valid`, true, "DRAFT_2019_09")
    val `Single_is_valid` = """
        |{
        |    "x": {
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Single_is_valid`, true, "DRAFT_2019_09")
    val `Unevaluated_on_1st_level_is_invalid` = """
        |{
        |    "x": {
        |    },
        |    "y": {
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Unevaluated_on_1st_level_is_invalid`, false, "DRAFT_2019_09")
    val `Nested_is_valid` = """
        |{
        |    "x": {
        |        "x": {
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Nested_is_valid`, true, "DRAFT_2019_09")
    val `Unevaluated_on_2nd_level_is_invalid` = """
        |{
        |    "x": {
        |        "x": {
        |        },
        |        "y": {
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Unevaluated_on_2nd_level_is_invalid`, false, "DRAFT_2019_09")
    val `Deep_nested_is_valid` = """
        |{
        |    "x": {
        |        "x": {
        |            "x": {
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Deep_nested_is_valid`, true, "DRAFT_2019_09")
    val `Unevaluated_on_3rd_level_is_invalid` = """
        |{
        |    "x": {
        |        "x": {
        |            "x": {
        |            },
        |            "y": {
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    run_test(schema, `Unevaluated_on_3rd_level_is_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun `unevaluatedProperties_+_ref_inside_allOf___oneOf`(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "one": {
        |            "properties": {
        |                "a": true
        |            }
        |        },
        |        "two": {
        |            "required": [
        |                "x"
        |            ],
        |            "properties": {
        |                "x": true
        |            }
        |        }
        |    },
        |    "allOf": [
        |        {
        |            "${'$'}ref": "#/${'$'}defs/one"
        |        },
        |        {
        |            "properties": {
        |                "b": true
        |            }
        |        },
        |        {
        |            "oneOf": [
        |                {
        |                    "${'$'}ref": "#/${'$'}defs/two"
        |                },
        |                {
        |                    "required": [
        |                        "y"
        |                    ],
        |                    "properties": {
        |                        "y": true
        |                    }
        |                }
        |            ]
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `Empty_is_invalid_no_x_or_y` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `Empty_is_invalid_no_x_or_y`, false, "DRAFT_2019_09")
    val `a_and_b_are_invalid_no_x_or_y` = """
        |{
        |    "a": 1,
        |    "b": 1
        |}
        """.trimMargin()
    run_test(schema, `a_and_b_are_invalid_no_x_or_y`, false, "DRAFT_2019_09")
    val `x_and_y_are_invalid` = """
        |{
        |    "x": 1,
        |    "y": 1
        |}
        """.trimMargin()
    run_test(schema, `x_and_y_are_invalid`, false, "DRAFT_2019_09")
    val `a_and_x_are_valid` = """
        |{
        |    "a": 1,
        |    "x": 1
        |}
        """.trimMargin()
    run_test(schema, `a_and_x_are_valid`, true, "DRAFT_2019_09")
    val `a_and_y_are_valid` = """
        |{
        |    "a": 1,
        |    "y": 1
        |}
        """.trimMargin()
    run_test(schema, `a_and_y_are_valid`, true, "DRAFT_2019_09")
    val `a_and_b_and_x_are_valid` = """
        |{
        |    "a": 1,
        |    "b": 1,
        |    "x": 1
        |}
        """.trimMargin()
    run_test(schema, `a_and_b_and_x_are_valid`, true, "DRAFT_2019_09")
    val `a_and_b_and_y_are_valid` = """
        |{
        |    "a": 1,
        |    "b": 1,
        |    "y": 1
        |}
        """.trimMargin()
    run_test(schema, `a_and_b_and_y_are_valid`, true, "DRAFT_2019_09")
    val `a_and_b_and_x_and_y_are_invalid` = """
        |{
        |    "a": 1,
        |    "b": 1,
        |    "x": 1,
        |    "y": 1
        |}
        """.trimMargin()
    run_test(schema, `a_and_b_and_x_and_y_are_invalid`, false, "DRAFT_2019_09")
  }

  @Test
  public fun dynamic_evalation_inside_nested_refs(): Unit {
    val schema = """
        |{
        |    "${'$'}defs": {
        |        "one": {
        |            "oneOf": [
        |                {
        |                    "${'$'}ref": "#/${'$'}defs/two"
        |                },
        |                {
        |                    "required": [
        |                        "b"
        |                    ],
        |                    "properties": {
        |                        "b": true
        |                    }
        |                },
        |                {
        |                    "required": [
        |                        "xx"
        |                    ],
        |                    "patternProperties": {
        |                        "x": true
        |                    }
        |                },
        |                {
        |                    "required": [
        |                        "all"
        |                    ],
        |                    "unevaluatedProperties": true
        |                }
        |            ]
        |        },
        |        "two": {
        |            "oneOf": [
        |                {
        |                    "required": [
        |                        "c"
        |                    ],
        |                    "properties": {
        |                        "c": true
        |                    }
        |                },
        |                {
        |                    "required": [
        |                        "d"
        |                    ],
        |                    "properties": {
        |                        "d": true
        |                    }
        |                }
        |            ]
        |        }
        |    },
        |    "oneOf": [
        |        {
        |            "${'$'}ref": "#/${'$'}defs/one"
        |        },
        |        {
        |            "required": [
        |                "a"
        |            ],
        |            "properties": {
        |                "a": true
        |            }
        |        }
        |    ],
        |    "unevaluatedProperties": false
        |}
        """.trimMargin()
    val `Empty_is_invalid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `Empty_is_invalid`, false, "DRAFT_2019_09")
    val `a_is_valid` = """
        |{
        |    "a": 1
        |}
        """.trimMargin()
    run_test(schema, `a_is_valid`, true, "DRAFT_2019_09")
    val `b_is_valid` = """
        |{
        |    "b": 1
        |}
        """.trimMargin()
    run_test(schema, `b_is_valid`, true, "DRAFT_2019_09")
    val `c_is_valid` = """
        |{
        |    "c": 1
        |}
        """.trimMargin()
    run_test(schema, `c_is_valid`, true, "DRAFT_2019_09")
    val `d_is_valid` = """
        |{
        |    "d": 1
        |}
        """.trimMargin()
    run_test(schema, `d_is_valid`, true, "DRAFT_2019_09")
    val `a_+_b_is_invalid` = """
        |{
        |    "a": 1,
        |    "b": 1
        |}
        """.trimMargin()
    run_test(schema, `a_+_b_is_invalid`, false, "DRAFT_2019_09")
    val `a_+_c_is_invalid` = """
        |{
        |    "a": 1,
        |    "c": 1
        |}
        """.trimMargin()
    run_test(schema, `a_+_c_is_invalid`, false, "DRAFT_2019_09")
    val `a_+_d_is_invalid` = """
        |{
        |    "a": 1,
        |    "d": 1
        |}
        """.trimMargin()
    run_test(schema, `a_+_d_is_invalid`, false, "DRAFT_2019_09")
    val `b_+_c_is_invalid` = """
        |{
        |    "b": 1,
        |    "c": 1
        |}
        """.trimMargin()
    run_test(schema, `b_+_c_is_invalid`, false, "DRAFT_2019_09")
    val `b_+_d_is_invalid` = """
        |{
        |    "b": 1,
        |    "d": 1
        |}
        """.trimMargin()
    run_test(schema, `b_+_d_is_invalid`, false, "DRAFT_2019_09")
    val `c_+_d_is_invalid` = """
        |{
        |    "c": 1,
        |    "d": 1
        |}
        """.trimMargin()
    run_test(schema, `c_+_d_is_invalid`, false, "DRAFT_2019_09")
    val `xx_is_valid` = """
        |{
        |    "xx": 1
        |}
        """.trimMargin()
    run_test(schema, `xx_is_valid`, true, "DRAFT_2019_09")
    val `xx_+_foox_is_valid` = """
        |{
        |    "xx": 1,
        |    "foox": 1
        |}
        """.trimMargin()
    run_test(schema, `xx_+_foox_is_valid`, true, "DRAFT_2019_09")
    val `xx_+_foo_is_invalid` = """
        |{
        |    "xx": 1,
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `xx_+_foo_is_invalid`, false, "DRAFT_2019_09")
    val `xx_+_a_is_invalid` = """
        |{
        |    "xx": 1,
        |    "a": 1
        |}
        """.trimMargin()
    run_test(schema, `xx_+_a_is_invalid`, false, "DRAFT_2019_09")
    val `xx_+_b_is_invalid` = """
        |{
        |    "xx": 1,
        |    "b": 1
        |}
        """.trimMargin()
    run_test(schema, `xx_+_b_is_invalid`, false, "DRAFT_2019_09")
    val `xx_+_c_is_invalid` = """
        |{
        |    "xx": 1,
        |    "c": 1
        |}
        """.trimMargin()
    run_test(schema, `xx_+_c_is_invalid`, false, "DRAFT_2019_09")
    val `xx_+_d_is_invalid` = """
        |{
        |    "xx": 1,
        |    "d": 1
        |}
        """.trimMargin()
    run_test(schema, `xx_+_d_is_invalid`, false, "DRAFT_2019_09")
    val `all_is_valid` = """
        |{
        |    "all": 1
        |}
        """.trimMargin()
    run_test(schema, `all_is_valid`, true, "DRAFT_2019_09")
    val `all_+_foo_is_valid` = """
        |{
        |    "all": 1,
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `all_+_foo_is_valid`, true, "DRAFT_2019_09")
    val `all_+_a_is_invalid` = """
        |{
        |    "all": 1,
        |    "a": 1
        |}
        """.trimMargin()
    run_test(schema, `all_+_a_is_invalid`, false, "DRAFT_2019_09")
  }
}
