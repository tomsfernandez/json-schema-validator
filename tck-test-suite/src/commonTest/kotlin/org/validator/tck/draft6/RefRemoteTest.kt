package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class RefRemoteTest {
  @Test
  public fun remote_ref(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "http://localhost:1234/integer.json"
        |}
        """.trimMargin()
    val `remote_ref_valid` = """1"""
    run_test(schema, `remote_ref_valid`, true, "DRAFT_6")
    val `remote_ref_invalid` = """"a""""
    run_test(schema, `remote_ref_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun fragment_within_remote_ref(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "http://localhost:1234/subSchemas.json#/integer"
        |}
        """.trimMargin()
    val `remote_fragment_valid` = """1"""
    run_test(schema, `remote_fragment_valid`, true, "DRAFT_6")
    val `remote_fragment_invalid` = """"a""""
    run_test(schema, `remote_fragment_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun ref_within_remote_ref(): Unit {
    val schema = """
        |{
        |    "${'$'}ref": "http://localhost:1234/subSchemas.json#/refToInteger"
        |}
        """.trimMargin()
    val `ref_within_ref_valid` = """1"""
    run_test(schema, `ref_within_ref_valid`, true, "DRAFT_6")
    val `ref_within_ref_invalid` = """"a""""
    run_test(schema, `ref_within_ref_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun base_URI_change(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/",
        |    "items": {
        |        "${'$'}id": "baseUriChange/",
        |        "items": {
        |            "${'$'}ref": "folderInteger.json"
        |        }
        |    }
        |}
        """.trimMargin()
    val `base_URI_change_ref_valid` = """
        |[
        |    [
        |        1
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `base_URI_change_ref_valid`, true, "DRAFT_6")
    val `base_URI_change_ref_invalid` = """
        |[
        |    [
        |        "a"
        |    ]
        |]
        """.trimMargin()
    run_test(schema, `base_URI_change_ref_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun base_URI_change___change_folder(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/scope_change_defs1.json",
        |    "type": "object",
        |    "properties": {
        |        "list": {
        |            "${'$'}ref": "#/definitions/baz"
        |        }
        |    },
        |    "definitions": {
        |        "baz": {
        |            "${'$'}id": "baseUriChangeFolder/",
        |            "type": "array",
        |            "items": {
        |                "${'$'}ref": "folderInteger.json"
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `number_is_valid` = """
        |{
        |    "list": [
        |        1
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `number_is_valid`, true, "DRAFT_6")
    val `string_is_invalid` = """
        |{
        |    "list": [
        |        "a"
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `string_is_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun base_URI_change___change_folder_in_subschema(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/scope_change_defs2.json",
        |    "type": "object",
        |    "properties": {
        |        "list": {
        |            "${'$'}ref": "#/definitions/baz/definitions/bar"
        |        }
        |    },
        |    "definitions": {
        |        "baz": {
        |            "${'$'}id": "baseUriChangeFolderInSubschema/",
        |            "definitions": {
        |                "bar": {
        |                    "type": "array",
        |                    "items": {
        |                        "${'$'}ref": "folderInteger.json"
        |                    }
        |                }
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `number_is_valid` = """
        |{
        |    "list": [
        |        1
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `number_is_valid`, true, "DRAFT_6")
    val `string_is_invalid` = """
        |{
        |    "list": [
        |        "a"
        |    ]
        |}
        """.trimMargin()
    run_test(schema, `string_is_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun root_ref_in_remote_ref(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/object",
        |    "type": "object",
        |    "properties": {
        |        "name": {
        |            "${'$'}ref": "name.json#/definitions/orNull"
        |        }
        |    }
        |}
        """.trimMargin()
    val `string_is_valid` = """
        |{
        |    "name": "foo"
        |}
        """.trimMargin()
    run_test(schema, `string_is_valid`, true, "DRAFT_6")
    val `null_is_valid` = """
        |{
        |    "name": null
        |}
        """.trimMargin()
    run_test(schema, `null_is_valid`, true, "DRAFT_6")
    val `object_is_invalid` = """
        |{
        |    "name": {
        |        "name": null
        |    }
        |}
        """.trimMargin()
    run_test(schema, `object_is_invalid`, false, "DRAFT_6")
  }

  @Test
  public fun remote_ref_with_ref_to_definitions(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "http://localhost:1234/schema-remote-ref-ref-defs1.json",
        |    "allOf": [
        |        {
        |            "${'$'}ref": "ref-and-definitions.json"
        |        }
        |    ]
        |}
        """.trimMargin()
    val `invalid` = """
        |{
        |    "bar": 1
        |}
        """.trimMargin()
    run_test(schema, `invalid`, false, "DRAFT_6")
    val `valid` = """
        |{
        |    "bar": "a"
        |}
        """.trimMargin()
    run_test(schema, `valid`, true, "DRAFT_6")
  }
}
