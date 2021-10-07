package org.validator.tck.draft4.optional.format

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class Ipv6Test {
  @Test
  public fun validation_of_IPv6_addresses(): Unit {
    val schema = "{\"format\":\"ipv6\"}"
    val `a_valid_IPv6_address` = "\"::1\""
    run_test(schema, `a_valid_IPv6_address`, true, "DRAFT_4")
    val `an_IPv6_address_with_out_of_range_values` = "\"12345::\""
    run_test(schema, `an_IPv6_address_with_out_of_range_values`, false, "DRAFT_4")
    val `an_IPv6_address_with_too_many_components` = "\"1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1\""
    run_test(schema, `an_IPv6_address_with_too_many_components`, false, "DRAFT_4")
    val `an_IPv6_address_containing_illegal_characters` = "\"::laptop\""
    run_test(schema, `an_IPv6_address_containing_illegal_characters`, false, "DRAFT_4")
    val `no_digits_is_valid` = "\"::\""
    run_test(schema, `no_digits_is_valid`, true, "DRAFT_4")
    val `leading_colons_is_valid` = "\"::42:ff:1\""
    run_test(schema, `leading_colons_is_valid`, true, "DRAFT_4")
    val `trailing_colons_is_valid` = "\"d6::\""
    run_test(schema, `trailing_colons_is_valid`, true, "DRAFT_4")
    val `missing_leading_octet_is_invalid` = "\":2:3:4:5:6:7:8\""
    run_test(schema, `missing_leading_octet_is_invalid`, false, "DRAFT_4")
    val `missing_trailing_octet_is_invalid` = "\"1:2:3:4:5:6:7:\""
    run_test(schema, `missing_trailing_octet_is_invalid`, false, "DRAFT_4")
    val `missing_leading_octet_with_omitted_octets_later` = "\":2:3:4::8\""
    run_test(schema, `missing_leading_octet_with_omitted_octets_later`, false, "DRAFT_4")
    val `two_sets_of_double_colons_is_invalid` = "\"1::d6::42\""
    run_test(schema, `two_sets_of_double_colons_is_invalid`, false, "DRAFT_4")
    val `mixed_format_with_the_ipv4_section_as_decimal_octets` = "\"1::d6:192.168.0.1\""
    run_test(schema, `mixed_format_with_the_ipv4_section_as_decimal_octets`, true, "DRAFT_4")
    val `mixed_format_with_double_colons_between_the_sections` = "\"1:2::192.168.0.1\""
    run_test(schema, `mixed_format_with_double_colons_between_the_sections`, true, "DRAFT_4")
    val `mixed_format_with_ipv4_section_with_octet_out_of_range` = "\"1::2:192.168.256.1\""
    run_test(schema, `mixed_format_with_ipv4_section_with_octet_out_of_range`, false, "DRAFT_4")
    val `mixed_format_with_ipv4_section_with_a_hex_octet` = "\"1::2:192.168.ff.1\""
    run_test(schema, `mixed_format_with_ipv4_section_with_a_hex_octet`, false, "DRAFT_4")
    val `mixed_format_with_leading_double_colons_ipv4_mapped_ipv6_address` =
        "\"::ffff:192.168.0.1\""
    run_test(schema, `mixed_format_with_leading_double_colons_ipv4_mapped_ipv6_address`, true,
        "DRAFT_4")
    val `triple_colons_is_invalid` = "\"1:2:3:4:5:::8\""
    run_test(schema, `triple_colons_is_invalid`, false, "DRAFT_4")
    val `8_octets` = "\"1:2:3:4:5:6:7:8\""
    run_test(schema, `8_octets`, true, "DRAFT_4")
    val `insufficient_octets_without_double_colons` = "\"1:2:3:4:5:6:7\""
    run_test(schema, `insufficient_octets_without_double_colons`, false, "DRAFT_4")
    val `no_colons_is_invalid` = "\"1\""
    run_test(schema, `no_colons_is_invalid`, false, "DRAFT_4")
    val `ipv4_is_not_ipv6` = "\"127.0.0.1\""
    run_test(schema, `ipv4_is_not_ipv6`, false, "DRAFT_4")
    val `ipv4_segment_must_have_4_octets` = "\"1:2:3:4:1.2.3\""
    run_test(schema, `ipv4_segment_must_have_4_octets`, false, "DRAFT_4")
    val `leading_whitespace_is_invalid` = "\"  ::1\""
    run_test(schema, `leading_whitespace_is_invalid`, false, "DRAFT_4")
    val `trailing_whitespace_is_invalid` = "\"::1  \""
    run_test(schema, `trailing_whitespace_is_invalid`, false, "DRAFT_4")
    val `netmask_is_not_a_part_of_ipv6_address` = "\"fe80::/64\""
    run_test(schema, `netmask_is_not_a_part_of_ipv6_address`, false, "DRAFT_4")
    val `zone_id_is_not_a_part_of_ipv6_address` = "\"fe80::a%eth1\""
    run_test(schema, `zone_id_is_not_a_part_of_ipv6_address`, false, "DRAFT_4")
    val `a_long_valid_ipv6` = "\"1000:1000:1000:1000:1000:1000:255.255.255.255\""
    run_test(schema, `a_long_valid_ipv6`, true, "DRAFT_4")
    val `a_long_invalid_ipv6__below_length_limit__first` =
        "\"100:100:100:100:100:100:255.255.255.255.255\""
    run_test(schema, `a_long_invalid_ipv6__below_length_limit__first`, false, "DRAFT_4")
    val `a_long_invalid_ipv6__below_length_limit__second` =
        "\"100:100:100:100:100:100:100:255.255.255.255\""
    run_test(schema, `a_long_invalid_ipv6__below_length_limit__second`, false, "DRAFT_4")
    val `non_ascii_digits_should_be_rejected` = "\"1:2:3:4:5:6:7:৪\""
    run_test(schema, `non_ascii_digits_should_be_rejected`, false, "DRAFT_4")
    val `non_ascii_digits_should_be_rejected_in_the_ipv4_portion_also` = "\"1:2::192.16৪.0.1\""
    run_test(schema, `non_ascii_digits_should_be_rejected_in_the_ipv4_portion_also`, false,
        "DRAFT_4")
  }
}
