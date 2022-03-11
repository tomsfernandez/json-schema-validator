package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MinItemsTest {
  @Test
  public fun minItems_validation(): Unit {
    val schema = """
        |{
        |    "minItems": 1
        |}
        """.trimMargin()
    val `longer_is_valid` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `longer_is_valid`, true, "DRAFT_4")
    val `exact_length_is_valid` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `exact_length_is_valid`, true, "DRAFT_4")
    val `too_short_is_invalid` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `too_short_is_invalid`, false, "DRAFT_4")
    val `ignores_non_arrays` = """"""""
    run_test(schema, `ignores_non_arrays`, true, "DRAFT_4")
  }
}
