package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class RequiredTest {
  @Test
  public fun required_validation(): Unit {
    val schema = "{\"properties\":{\"foo\":{},\"bar\":{}},\"required\":[\"foo\"]}"
    val `present_required_property_is_valid` = "{\"foo\":1}"
    run_test(schema, `present_required_property_is_valid`, true, "DRAFT_4")
    val `non_present_required_property_is_invalid` = "{\"bar\":1}"
    run_test(schema, `non_present_required_property_is_invalid`, false, "DRAFT_4")
    val `ignores_arrays` = "[]"
    run_test(schema, `ignores_arrays`, true, "DRAFT_4")
    val `ignores_strings` = "\"\""
    run_test(schema, `ignores_strings`, true, "DRAFT_4")
    val `ignores_other_non_objects` = "12"
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_4")
  }

  @Test
  public fun required_default_validation(): Unit {
    val schema = "{\"properties\":{\"foo\":{}}}"
    val `not_required_by_default` = "{}"
    run_test(schema, `not_required_by_default`, true, "DRAFT_4")
  }

  @Test
  public fun required_with_escaped_characters(): Unit {
    val schema =
        "{\"required\":[\"foo\\nbar\",\"foo\\\"bar\",\"foo\\\\bar\",\"foo\\rbar\",\"foo\\tbar\",\"foo\\fbar\"]}"
    val `object_with_all_properties_present_is_valid` =
        "{\"foo\\nbar\":1,\"foo\\\"bar\":1,\"foo\\\\bar\":1,\"foo\\rbar\":1,\"foo\\tbar\":1,\"foo\\fbar\":1}"
    run_test(schema, `object_with_all_properties_present_is_valid`, true, "DRAFT_4")
    val `object_with_some_properties_missing_is_invalid` =
        "{\"foo\\nbar\":\"1\",\"foo\\\"bar\":\"1\"}"
    run_test(schema, `object_with_some_properties_missing_is_invalid`, false, "DRAFT_4")
  }
}
