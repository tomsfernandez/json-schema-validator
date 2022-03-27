package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class ContentTest {
  @Test
  public fun validation_of_string_encoded_content_based_on_media_type(): Unit {
    val schema = """
        |{
        |    "contentMediaType": "application/json"
        |}
        """.trimMargin()
    val `a_valid_JSON_document` = """"{\\"foo\\": \\"bar\\"}""""
    run_test(schema, `a_valid_JSON_document`, true, "DRAFT_2019_09")
    val `an_invalid_JSON_document_validates_true` = """"{:}""""
    run_test(schema, `an_invalid_JSON_document_validates_true`, true, "DRAFT_2019_09")
    val `ignores_non_strings` = """100"""
    run_test(schema, `ignores_non_strings`, true, "DRAFT_2019_09")
  }

  @Test
  public fun validation_of_binary_string_encoding(): Unit {
    val schema = """
        |{
        |    "contentEncoding": "base64"
        |}
        """.trimMargin()
    val `a_valid_base64_string` = """"eyJmb28iOiAiYmFyIn0K""""
    run_test(schema, `a_valid_base64_string`, true, "DRAFT_2019_09")
    val `an_invalid_base64_string_percent_char_is_not_a_valid_character_validates_true` =
        """"eyJmb28iOi%iYmFyIn0K""""
    run_test(schema,
        `an_invalid_base64_string_percent_char_is_not_a_valid_character_validates_true`, true,
        "DRAFT_2019_09")
    val `ignores_non_strings` = """100"""
    run_test(schema, `ignores_non_strings`, true, "DRAFT_2019_09")
  }

  @Test
  public fun validation_of_binary_encoded_media_type_documents(): Unit {
    val schema = """
        |{
        |    "contentMediaType": "application/json",
        |    "contentEncoding": "base64"
        |}
        """.trimMargin()
    val `a_valid_base64_encoded_JSON_document` = """"eyJmb28iOiAiYmFyIn0K""""
    run_test(schema, `a_valid_base64_encoded_JSON_document`, true, "DRAFT_2019_09")
    val `a_validly_encoded_invalid_JSON_document_validates_true` = """"ezp9Cg==""""
    run_test(schema, `a_validly_encoded_invalid_JSON_document_validates_true`, true,
        "DRAFT_2019_09")
    val `an_invalid_base64_string_that_is_valid_JSON_validates_true` = """"{}""""
    run_test(schema, `an_invalid_base64_string_that_is_valid_JSON_validates_true`, true,
        "DRAFT_2019_09")
    val `ignores_non_strings` = """100"""
    run_test(schema, `ignores_non_strings`, true, "DRAFT_2019_09")
  }

  @Test
  public fun validation_of_binary_encoded_media_type_documents_with_schema(): Unit {
    val schema = """
        |{
        |    "contentMediaType": "application/json",
        |    "contentEncoding": "base64",
        |    "contentSchema": {
        |        "required": [
        |            "foo"
        |        ],
        |        "properties": {
        |            "foo": {
        |                "type": "string"
        |            }
        |        }
        |    }
        |}
        """.trimMargin()
    val `a_valid_base64_encoded_JSON_document` = """"eyJmb28iOiAiYmFyIn0K""""
    run_test(schema, `a_valid_base64_encoded_JSON_document`, true, "DRAFT_2019_09")
    val `another_valid_base64_encoded_JSON_document` = """"eyJib28iOiAyMCwgImZvbyI6ICJiYXoifQ==""""
    run_test(schema, `another_valid_base64_encoded_JSON_document`, true, "DRAFT_2019_09")
    val `an_invalid_base64_encoded_JSON_document_validates_true` = """"eyJib28iOiAyMH0=""""
    run_test(schema, `an_invalid_base64_encoded_JSON_document_validates_true`, true,
        "DRAFT_2019_09")
    val `an_empty_object_as_a_base64_encoded_JSON_document_validates_true` = """"e30=""""
    run_test(schema, `an_empty_object_as_a_base64_encoded_JSON_document_validates_true`, true,
        "DRAFT_2019_09")
    val `an_empty_array_as_a_base64_encoded_JSON_document` = """"W10=""""
    run_test(schema, `an_empty_array_as_a_base64_encoded_JSON_document`, true, "DRAFT_2019_09")
    val `a_validly_encoded_invalid_JSON_document_validates_true` = """"ezp9Cg==""""
    run_test(schema, `a_validly_encoded_invalid_JSON_document_validates_true`, true,
        "DRAFT_2019_09")
    val `an_invalid_base64_string_that_is_valid_JSON_validates_true` = """"{}""""
    run_test(schema, `an_invalid_base64_string_that_is_valid_JSON_validates_true`, true,
        "DRAFT_2019_09")
    val `ignores_non_strings` = """100"""
    run_test(schema, `ignores_non_strings`, true, "DRAFT_2019_09")
  }
}
