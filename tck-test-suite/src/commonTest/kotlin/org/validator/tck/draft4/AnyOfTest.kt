package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class AnyOfTest {
  @Test
  public fun anyOf(): Unit {
    val schema = "{\"anyOf\":[{\"type\":\"integer\"},{\"minimum\":2}]}"
    val `first_anyOf_valid` = "1"
    run_test(schema, `first_anyOf_valid`, true, "DRAFT_4")
    val `second_anyOf_valid` = "2.5"
    run_test(schema, `second_anyOf_valid`, true, "DRAFT_4")
    val `both_anyOf_valid` = "3"
    run_test(schema, `both_anyOf_valid`, true, "DRAFT_4")
    val `neither_anyOf_valid` = "1.5"
    run_test(schema, `neither_anyOf_valid`, false, "DRAFT_4")
  }

  @Test
  public fun anyOf_with_base_schema(): Unit {
    val schema = "{\"type\":\"string\",\"anyOf\":[{\"maxLength\":2},{\"minLength\":4}]}"
    val `mismatch_base_schema` = "3"
    run_test(schema, `mismatch_base_schema`, false, "DRAFT_4")
    val `one_anyOf_valid` = "\"foobar\""
    run_test(schema, `one_anyOf_valid`, true, "DRAFT_4")
    val `both_anyOf_invalid` = "\"foo\""
    run_test(schema, `both_anyOf_invalid`, false, "DRAFT_4")
  }

  @Test
  public fun anyOf_complex_types(): Unit {
    val schema =
        "{\"anyOf\":[{\"properties\":{\"bar\":{\"type\":\"integer\"}},\"required\":[\"bar\"]},{\"properties\":{\"foo\":{\"type\":\"string\"}},\"required\":[\"foo\"]}]}"
    val `first_anyOf_valid_complex` = "{\"bar\":2}"
    run_test(schema, `first_anyOf_valid_complex`, true, "DRAFT_4")
    val `second_anyOf_valid_complex` = "{\"foo\":\"baz\"}"
    run_test(schema, `second_anyOf_valid_complex`, true, "DRAFT_4")
    val `both_anyOf_valid_complex` = "{\"foo\":\"baz\",\"bar\":2}"
    run_test(schema, `both_anyOf_valid_complex`, true, "DRAFT_4")
    val `neither_anyOf_valid_complex` = "{\"foo\":2,\"bar\":\"quux\"}"
    run_test(schema, `neither_anyOf_valid_complex`, false, "DRAFT_4")
  }

  @Test
  public fun anyOf_with_one_empty_schema(): Unit {
    val schema = "{\"anyOf\":[{\"type\":\"number\"},{}]}"
    val `string_is_valid` = "\"foo\""
    run_test(schema, `string_is_valid`, true, "DRAFT_4")
    val `number_is_valid` = "123"
    run_test(schema, `number_is_valid`, true, "DRAFT_4")
  }

  @Test
  public fun nested_anyOf__to_check_validation_semantics(): Unit {
    val schema = "{\"anyOf\":[{\"anyOf\":[{\"type\":\"null\"}]}]}"
    val `null_is_valid` = "null"
    run_test(schema, `null_is_valid`, true, "DRAFT_4")
    val `anything_non_null_is_invalid` = "123"
    run_test(schema, `anything_non_null_is_invalid`, false, "DRAFT_4")
  }
}
