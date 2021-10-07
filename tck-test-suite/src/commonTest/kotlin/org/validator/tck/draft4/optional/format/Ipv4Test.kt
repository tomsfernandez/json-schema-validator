package org.validator.tck.draft4.optional.format

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class Ipv4Test {
  @Test
  public fun validation_of_IP_addresses(): Unit {
    val schema = "{\"format\":\"ipv4\"}"
    val `a_valid_IP_address` = "\"192.168.0.1\""
    run_test(schema, `a_valid_IP_address`, true, "DRAFT_4")
    val `an_IP_address_with_too_many_components` = "\"127.0.0.0.1\""
    run_test(schema, `an_IP_address_with_too_many_components`, false, "DRAFT_4")
    val `an_IP_address_with_out_of_range_values` = "\"256.256.256.256\""
    run_test(schema, `an_IP_address_with_out_of_range_values`, false, "DRAFT_4")
    val `an_IP_address_without_4_components` = "\"127.0\""
    run_test(schema, `an_IP_address_without_4_components`, false, "DRAFT_4")
    val `an_IP_address_as_an_integer` = "\"0x7f000001\""
    run_test(schema, `an_IP_address_as_an_integer`, false, "DRAFT_4")
    val `an_IP_address_as_an_integer_decimal` = "\"2130706433\""
    run_test(schema, `an_IP_address_as_an_integer_decimal`, false, "DRAFT_4")
    val `leading_zeroes_should_be_rejected__as_they_are_treated_as_octals` = "\"087.10.0.1\""
    run_test(schema, `leading_zeroes_should_be_rejected__as_they_are_treated_as_octals`, false,
        "DRAFT_4")
    val `value_without_leading_zero_is_valid` = "\"87.10.0.1\""
    run_test(schema, `value_without_leading_zero_is_valid`, true, "DRAFT_4")
    val `non_ascii_digits_should_be_rejected` = "\"1২7.0.0.1\""
    run_test(schema, `non_ascii_digits_should_be_rejected`, false, "DRAFT_4")
  }
}
