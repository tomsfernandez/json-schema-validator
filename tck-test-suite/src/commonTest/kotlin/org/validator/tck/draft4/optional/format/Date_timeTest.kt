package org.validator.tck.draft4.optional.format

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class Date_timeTest {
  @Test
  public fun validation_of_date_time_strings(): Unit {
    val schema = "{\"format\":\"date-time\"}"
    val `a_valid_date_time_string` = "\"1963-06-19T08:30:06.283185Z\""
    run_test(schema, `a_valid_date_time_string`, true, "DRAFT_4")
    val `a_valid_date_time_string_without_second_fraction` = "\"1963-06-19T08:30:06Z\""
    run_test(schema, `a_valid_date_time_string_without_second_fraction`, true, "DRAFT_4")
    val `a_valid_date_time_string_with_plus_offset` = "\"1937-01-01T12:00:27.87+00:20\""
    run_test(schema, `a_valid_date_time_string_with_plus_offset`, true, "DRAFT_4")
    val `a_valid_date_time_string_with_minus_offset` = "\"1990-12-31T15:59:50.123-08:00\""
    run_test(schema, `a_valid_date_time_string_with_minus_offset`, true, "DRAFT_4")
    val `a_invalid_day_in_date_time_string` = "\"1990-02-31T15:59:60.123-08:00\""
    run_test(schema, `a_invalid_day_in_date_time_string`, false, "DRAFT_4")
    val `an_invalid_offset_in_date_time_string` = "\"1990-12-31T15:59:60-24:00\""
    run_test(schema, `an_invalid_offset_in_date_time_string`, false, "DRAFT_4")
    val `an_invalid_date_time_string` = "\"06/19/1963 08:30:06 PST\""
    run_test(schema, `an_invalid_date_time_string`, false, "DRAFT_4")
    val `case_insensitive_T_and_Z` = "\"1963-06-19t08:30:06.283185z\""
    run_test(schema, `case_insensitive_T_and_Z`, true, "DRAFT_4")
    val `only_RFC3339_not_all_of_ISO_8601_are_valid` = "\"2013-350T01:01:01\""
    run_test(schema, `only_RFC3339_not_all_of_ISO_8601_are_valid`, false, "DRAFT_4")
    val `invalid_non_padded_month_dates` = "\"1963-6-19T08:30:06.283185Z\""
    run_test(schema, `invalid_non_padded_month_dates`, false, "DRAFT_4")
    val `invalid_non_padded_day_dates` = "\"1963-06-1T08:30:06.283185Z\""
    run_test(schema, `invalid_non_padded_day_dates`, false, "DRAFT_4")
    val `non_ascii_digits_should_be_rejected_in_the_date_portion` = "\"1963-06-1৪T00:00:00Z\""
    run_test(schema, `non_ascii_digits_should_be_rejected_in_the_date_portion`, false, "DRAFT_4")
    val `non_ascii_digits_should_be_rejected_in_the_time_portion` = "\"1963-06-11T0৪:00:00Z\""
    run_test(schema, `non_ascii_digits_should_be_rejected_in_the_time_portion`, false, "DRAFT_4")
  }
}
