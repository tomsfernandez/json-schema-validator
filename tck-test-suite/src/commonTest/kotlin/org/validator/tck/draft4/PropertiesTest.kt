package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class PropertiesTest {
  @Test
  public fun object_properties_validation(): Unit {
    val schema = "{\"properties\":{\"foo\":{\"type\":\"integer\"},\"bar\":{\"type\":\"string\"}}}"
    val `both_properties_present_and_valid_is_valid` = "{\"foo\":1,\"bar\":\"baz\"}"
    run_test(schema, `both_properties_present_and_valid_is_valid`, true, "DRAFT_4")
    val `one_property_invalid_is_invalid` = "{\"foo\":1,\"bar\":{}}"
    run_test(schema, `one_property_invalid_is_invalid`, false, "DRAFT_4")
    val `both_properties_invalid_is_invalid` = "{\"foo\":[],\"bar\":{}}"
    run_test(schema, `both_properties_invalid_is_invalid`, false, "DRAFT_4")
    val `doesn't_invalidate_other_properties` = "{\"quux\":[]}"
    run_test(schema, `doesn't_invalidate_other_properties`, true, "DRAFT_4")
    val `ignores_arrays` = "[]"
    run_test(schema, `ignores_arrays`, true, "DRAFT_4")
    val `ignores_other_non_objects` = "12"
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_4")
  }

  @Test
  public fun properties__patternProperties__additionalProperties_interaction(): Unit {
    val schema =
        "{\"properties\":{\"foo\":{\"type\":\"array\",\"maxItems\":3},\"bar\":{\"type\":\"array\"}},\"patternProperties\":{\"f.o\":{\"minItems\":2}},\"additionalProperties\":{\"type\":\"integer\"}}"
    val `property_validates_property` = "{\"foo\":[1,2]}"
    run_test(schema, `property_validates_property`, true, "DRAFT_4")
    val `property_invalidates_property` = "{\"foo\":[1,2,3,4]}"
    run_test(schema, `property_invalidates_property`, false, "DRAFT_4")
    val `patternProperty_invalidates_property` = "{\"foo\":[]}"
    run_test(schema, `patternProperty_invalidates_property`, false, "DRAFT_4")
    val `patternProperty_validates_nonproperty` = "{\"fxo\":[1,2]}"
    run_test(schema, `patternProperty_validates_nonproperty`, true, "DRAFT_4")
    val `patternProperty_invalidates_nonproperty` = "{\"fxo\":[]}"
    run_test(schema, `patternProperty_invalidates_nonproperty`, false, "DRAFT_4")
    val `additionalProperty_ignores_property` = "{\"bar\":[]}"
    run_test(schema, `additionalProperty_ignores_property`, true, "DRAFT_4")
    val `additionalProperty_validates_others` = "{\"quux\":3}"
    run_test(schema, `additionalProperty_validates_others`, true, "DRAFT_4")
    val `additionalProperty_invalidates_others` = "{\"quux\":\"foo\"}"
    run_test(schema, `additionalProperty_invalidates_others`, false, "DRAFT_4")
  }

  @Test
  public fun properties_with_escaped_characters(): Unit {
    val schema =
        "{\"properties\":{\"foo\\nbar\":{\"type\":\"number\"},\"foo\\\"bar\":{\"type\":\"number\"},\"foo\\\\bar\":{\"type\":\"number\"},\"foo\\rbar\":{\"type\":\"number\"},\"foo\\tbar\":{\"type\":\"number\"},\"foo\\fbar\":{\"type\":\"number\"}}}"
    val `object_with_all_numbers_is_valid` =
        "{\"foo\\nbar\":1,\"foo\\\"bar\":1,\"foo\\\\bar\":1,\"foo\\rbar\":1,\"foo\\tbar\":1,\"foo\\fbar\":1}"
    run_test(schema, `object_with_all_numbers_is_valid`, true, "DRAFT_4")
    val `object_with_strings_is_invalid` =
        "{\"foo\\nbar\":\"1\",\"foo\\\"bar\":\"1\",\"foo\\\\bar\":\"1\",\"foo\\rbar\":\"1\",\"foo\\tbar\":\"1\",\"foo\\fbar\":\"1\"}"
    run_test(schema, `object_with_strings_is_invalid`, false, "DRAFT_4")
  }
}
