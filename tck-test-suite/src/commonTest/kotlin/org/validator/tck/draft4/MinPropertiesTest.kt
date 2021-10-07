package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MinPropertiesTest {
  @Test
  public fun minProperties_validation(): Unit {
    val schema = "{\"minProperties\":1}"
    val `longer_is_valid` = "{\"foo\":1,\"bar\":2}"
    run_test(schema, `longer_is_valid`, true, "DRAFT_4")
    val `exact_length_is_valid` = "{\"foo\":1}"
    run_test(schema, `exact_length_is_valid`, true, "DRAFT_4")
    val `too_short_is_invalid` = "{}"
    run_test(schema, `too_short_is_invalid`, false, "DRAFT_4")
    val `ignores_arrays` = "[]"
    run_test(schema, `ignores_arrays`, true, "DRAFT_4")
    val `ignores_strings` = "\"\""
    run_test(schema, `ignores_strings`, true, "DRAFT_4")
    val `ignores_other_non_objects` = "12"
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_4")
  }
}
