package org.validator.tck.draft4.optional

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class BignumTest {
  @Test
  public fun integer(): Unit {
    val schema = "{\"type\":\"integer\"}"
    val `a_bignum_is_an_integer` = "12345678910111213141516171819202122232425262728293031"
    run_test(schema, `a_bignum_is_an_integer`, true, "DRAFT_4")
    val `a_negative_bignum_is_an_integer` = "-12345678910111213141516171819202122232425262728293031"
    run_test(schema, `a_negative_bignum_is_an_integer`, true, "DRAFT_4")
  }

  @Test
  public fun number(): Unit {
    val schema = "{\"type\":\"number\"}"
    val `a_bignum_is_a_number` = "98249283749234923498293171823948729348710298301928331"
    run_test(schema, `a_bignum_is_a_number`, true, "DRAFT_4")
    val `a_negative_bignum_is_a_number` = "-98249283749234923498293171823948729348710298301928331"
    run_test(schema, `a_negative_bignum_is_a_number`, true, "DRAFT_4")
  }

  @Test
  public fun string(): Unit {
    val schema = "{\"type\":\"string\"}"
    val `a_bignum_is_not_a_string` = "98249283749234923498293171823948729348710298301928331"
    run_test(schema, `a_bignum_is_not_a_string`, false, "DRAFT_4")
  }

  @Test
  public fun integer_comparison(): Unit {
    val schema = "{\"maximum\":18446744073709551615}"
    val `comparison_works_for_high_numbers` = "18446744073709551600"
    run_test(schema, `comparison_works_for_high_numbers`, true, "DRAFT_4")
  }

  @Test
  public fun float_comparison_with_high_precision(): Unit {
    val schema = "{\"maximum\":972783798187987123879878123.18878137,\"exclusiveMaximum\":true}"
    val `comparison_works_for_high_numbers` = "972783798187987123879878123.188781371"
    run_test(schema, `comparison_works_for_high_numbers`, false, "DRAFT_4")
  }

  @Test
  public fun float_comparison_with_high_precision_on_negative_numbers(): Unit {
    val schema = "{\"minimum\":-972783798187987123879878123.18878137,\"exclusiveMinimum\":true}"
    val `comparison_works_for_very_negative_numbers` = "-972783798187987123879878123.188781371"
    run_test(schema, `comparison_works_for_very_negative_numbers`, false, "DRAFT_4")
  }
}
