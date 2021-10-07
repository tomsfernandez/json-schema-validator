package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class OneOfTest {
  @Test
  public fun oneOf(): Unit {
    val schema = "{\"oneOf\":[{\"type\":\"integer\"},{\"minimum\":2}]}"
    val `first_oneOf_valid` = "1"
    run_test(schema, `first_oneOf_valid`, true, "DRAFT_4")
    val `second_oneOf_valid` = "2.5"
    run_test(schema, `second_oneOf_valid`, true, "DRAFT_4")
    val `both_oneOf_valid` = "3"
    run_test(schema, `both_oneOf_valid`, false, "DRAFT_4")
    val `neither_oneOf_valid` = "1.5"
    run_test(schema, `neither_oneOf_valid`, false, "DRAFT_4")
  }

  @Test
  public fun oneOf_with_base_schema(): Unit {
    val schema = "{\"type\":\"string\",\"oneOf\":[{\"minLength\":2},{\"maxLength\":4}]}"
    val `mismatch_base_schema` = "3"
    run_test(schema, `mismatch_base_schema`, false, "DRAFT_4")
    val `one_oneOf_valid` = "\"foobar\""
    run_test(schema, `one_oneOf_valid`, true, "DRAFT_4")
    val `both_oneOf_valid` = "\"foo\""
    run_test(schema, `both_oneOf_valid`, false, "DRAFT_4")
  }

  @Test
  public fun oneOf_complex_types(): Unit {
    val schema =
        "{\"oneOf\":[{\"properties\":{\"bar\":{\"type\":\"integer\"}},\"required\":[\"bar\"]},{\"properties\":{\"foo\":{\"type\":\"string\"}},\"required\":[\"foo\"]}]}"
    val `first_oneOf_valid_complex` = "{\"bar\":2}"
    run_test(schema, `first_oneOf_valid_complex`, true, "DRAFT_4")
    val `second_oneOf_valid_complex` = "{\"foo\":\"baz\"}"
    run_test(schema, `second_oneOf_valid_complex`, true, "DRAFT_4")
    val `both_oneOf_valid_complex` = "{\"foo\":\"baz\",\"bar\":2}"
    run_test(schema, `both_oneOf_valid_complex`, false, "DRAFT_4")
    val `neither_oneOf_valid_complex` = "{\"foo\":2,\"bar\":\"quux\"}"
    run_test(schema, `neither_oneOf_valid_complex`, false, "DRAFT_4")
  }

  @Test
  public fun oneOf_with_empty_schema(): Unit {
    val schema = "{\"oneOf\":[{\"type\":\"number\"},{}]}"
    val `one_valid___valid` = "\"foo\""
    run_test(schema, `one_valid___valid`, true, "DRAFT_4")
    val `both_valid___invalid` = "123"
    run_test(schema, `both_valid___invalid`, false, "DRAFT_4")
  }

  @Test
  public fun oneOf_with_required(): Unit {
    val schema =
        "{\"type\":\"object\",\"oneOf\":[{\"required\":[\"foo\",\"bar\"]},{\"required\":[\"foo\",\"baz\"]}]}"
    val `both_invalid___invalid` = "{\"bar\":2}"
    run_test(schema, `both_invalid___invalid`, false, "DRAFT_4")
    val `first_valid___valid` = "{\"foo\":1,\"bar\":2}"
    run_test(schema, `first_valid___valid`, true, "DRAFT_4")
    val `second_valid___valid` = "{\"foo\":1,\"baz\":3}"
    run_test(schema, `second_valid___valid`, true, "DRAFT_4")
    val `both_valid___invalid` = "{\"foo\":1,\"bar\":2,\"baz\":3}"
    run_test(schema, `both_valid___invalid`, false, "DRAFT_4")
  }

  @Test
  public fun oneOf_with_missing_optional_property(): Unit {
    val schema =
        "{\"oneOf\":[{\"properties\":{\"bar\":{},\"baz\":{}},\"required\":[\"bar\"]},{\"properties\":{\"foo\":{}},\"required\":[\"foo\"]}]}"
    val `first_oneOf_valid` = "{\"bar\":8}"
    run_test(schema, `first_oneOf_valid`, true, "DRAFT_4")
    val `second_oneOf_valid` = "{\"foo\":\"foo\"}"
    run_test(schema, `second_oneOf_valid`, true, "DRAFT_4")
    val `both_oneOf_valid` = "{\"foo\":\"foo\",\"bar\":8}"
    run_test(schema, `both_oneOf_valid`, false, "DRAFT_4")
    val `neither_oneOf_valid` = "{\"baz\":\"quux\"}"
    run_test(schema, `neither_oneOf_valid`, false, "DRAFT_4")
  }

  @Test
  public fun nested_oneOf__to_check_validation_semantics(): Unit {
    val schema = "{\"oneOf\":[{\"oneOf\":[{\"type\":\"null\"}]}]}"
    val `null_is_valid` = "null"
    run_test(schema, `null_is_valid`, true, "DRAFT_4")
    val `anything_non_null_is_invalid` = "123"
    run_test(schema, `anything_non_null_is_invalid`, false, "DRAFT_4")
  }
}
