package org.validator.tck.draft4.optional

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class ZeroTerminatedFloatsTest {
  @Test
  public fun some_languages_do_not_distinguish_between_different_types_of_numeric_value(): Unit {
    val schema = "{\"type\":\"integer\"}"
    val `a_float_is_not_an_integer_even_without_fractional_part` = "1.0"
    run_test(schema, `a_float_is_not_an_integer_even_without_fractional_part`, false, "DRAFT_4")
  }
}
