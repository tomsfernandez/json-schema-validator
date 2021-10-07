package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class FormatTest {
  @Test
  public fun email_format(): Unit {
    val schema = "{\"format\":\"email\"}"
    val `all_string_formats_ignore_integers` = "12"
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_4")
    val `all_string_formats_ignore_floats` = "13.7"
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_4")
    val `all_string_formats_ignore_objects` = "{}"
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_4")
    val `all_string_formats_ignore_arrays` = "[]"
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_4")
    val `all_string_formats_ignore_booleans` = "false"
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_4")
    val `all_string_formats_ignore_nulls` = "null"
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_4")
  }

  @Test
  public fun ipv4_format(): Unit {
    val schema = "{\"format\":\"ipv4\"}"
    val `all_string_formats_ignore_integers` = "12"
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_4")
    val `all_string_formats_ignore_floats` = "13.7"
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_4")
    val `all_string_formats_ignore_objects` = "{}"
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_4")
    val `all_string_formats_ignore_arrays` = "[]"
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_4")
    val `all_string_formats_ignore_booleans` = "false"
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_4")
    val `all_string_formats_ignore_nulls` = "null"
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_4")
  }

  @Test
  public fun ipv6_format(): Unit {
    val schema = "{\"format\":\"ipv6\"}"
    val `all_string_formats_ignore_integers` = "12"
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_4")
    val `all_string_formats_ignore_floats` = "13.7"
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_4")
    val `all_string_formats_ignore_objects` = "{}"
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_4")
    val `all_string_formats_ignore_arrays` = "[]"
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_4")
    val `all_string_formats_ignore_booleans` = "false"
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_4")
    val `all_string_formats_ignore_nulls` = "null"
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_4")
  }

  @Test
  public fun hostname_format(): Unit {
    val schema = "{\"format\":\"hostname\"}"
    val `all_string_formats_ignore_integers` = "12"
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_4")
    val `all_string_formats_ignore_floats` = "13.7"
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_4")
    val `all_string_formats_ignore_objects` = "{}"
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_4")
    val `all_string_formats_ignore_arrays` = "[]"
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_4")
    val `all_string_formats_ignore_booleans` = "false"
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_4")
    val `all_string_formats_ignore_nulls` = "null"
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_4")
  }

  @Test
  public fun date_time_format(): Unit {
    val schema = "{\"format\":\"date-time\"}"
    val `all_string_formats_ignore_integers` = "12"
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_4")
    val `all_string_formats_ignore_floats` = "13.7"
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_4")
    val `all_string_formats_ignore_objects` = "{}"
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_4")
    val `all_string_formats_ignore_arrays` = "[]"
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_4")
    val `all_string_formats_ignore_booleans` = "false"
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_4")
    val `all_string_formats_ignore_nulls` = "null"
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_4")
  }

  @Test
  public fun uri_format(): Unit {
    val schema = "{\"format\":\"uri\"}"
    val `all_string_formats_ignore_integers` = "12"
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_4")
    val `all_string_formats_ignore_floats` = "13.7"
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_4")
    val `all_string_formats_ignore_objects` = "{}"
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_4")
    val `all_string_formats_ignore_arrays` = "[]"
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_4")
    val `all_string_formats_ignore_booleans` = "false"
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_4")
    val `all_string_formats_ignore_nulls` = "null"
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_4")
  }
}
