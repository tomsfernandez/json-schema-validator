package org.validator.tck.draft7

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MaxItemsTest {
  @Test
  public fun maxItems_validation(): Unit {
    val schema = """
        |{
        |    "maxItems": 2
        |}
        """.trimMargin()
    val `shorter_is_valid` = """
        |[
        |    1
        |]
        """.trimMargin()
    run_test(schema, `shorter_is_valid`, true, "DRAFT_7")
    val `exact_length_is_valid` = """
        |[
        |    1,
        |    2
        |]
        """.trimMargin()
    run_test(schema, `exact_length_is_valid`, true, "DRAFT_7")
    val `too_long_is_invalid` = """
        |[
        |    1,
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `too_long_is_invalid`, false, "DRAFT_7")
    val `ignores_non_arrays` = """"foobar""""
    run_test(schema, `ignores_non_arrays`, true, "DRAFT_7")
  }
}
