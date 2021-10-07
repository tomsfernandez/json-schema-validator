package org.validator.tck.draft4.optional

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class `Float-overflowTest` {
  @Test
  public fun all_integers_are_multiples_of_0_5__if_overflow_is_handled(): Unit {
    val schema = "{\"type\":\"number\",\"multipleOf\":0.5}"
    val `valid_if_optional_overflow_handling_is_implemented` = "1e308"
    run_test(schema, `valid_if_optional_overflow_handling_is_implemented`, true, "DRAFT_4")
  }
}
