package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class FormatTest {
  @Test
  public fun email_format(): Unit {
    val schema = """
        |{
        |    "format": "email"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun idn_email_format(): Unit {
    val schema = """
        |{
        |    "format": "idn-email"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun regex_format(): Unit {
    val schema = """
        |{
        |    "format": "regex"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun ipv4_format(): Unit {
    val schema = """
        |{
        |    "format": "ipv4"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun ipv6_format(): Unit {
    val schema = """
        |{
        |    "format": "ipv6"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun idn_hostname_format(): Unit {
    val schema = """
        |{
        |    "format": "idn-hostname"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun hostname_format(): Unit {
    val schema = """
        |{
        |    "format": "hostname"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun date_format(): Unit {
    val schema = """
        |{
        |    "format": "date"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun date_time_format(): Unit {
    val schema = """
        |{
        |    "format": "date-time"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun time_format(): Unit {
    val schema = """
        |{
        |    "format": "time"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun json_pointer_format(): Unit {
    val schema = """
        |{
        |    "format": "json-pointer"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun relative_json_pointer_format(): Unit {
    val schema = """
        |{
        |    "format": "relative-json-pointer"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun iri_format(): Unit {
    val schema = """
        |{
        |    "format": "iri"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun iri_reference_format(): Unit {
    val schema = """
        |{
        |    "format": "iri-reference"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun uri_format(): Unit {
    val schema = """
        |{
        |    "format": "uri"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun uri_reference_format(): Unit {
    val schema = """
        |{
        |    "format": "uri-reference"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun uri_template_format(): Unit {
    val schema = """
        |{
        |    "format": "uri-template"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun uuid_format(): Unit {
    val schema = """
        |{
        |    "format": "uuid"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }

  @Test
  public fun duration_format(): Unit {
    val schema = """
        |{
        |    "format": "duration"
        |}
        """.trimMargin()
    val `all_string_formats_ignore_integers` = """12"""
    run_test(schema, `all_string_formats_ignore_integers`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_floats` = """13.7"""
    run_test(schema, `all_string_formats_ignore_floats`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_objects` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_objects`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_arrays` = """
        |[
        |]
        """.trimMargin()
    run_test(schema, `all_string_formats_ignore_arrays`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_booleans` = """false"""
    run_test(schema, `all_string_formats_ignore_booleans`, true, "DRAFT_2019_09")
    val `all_string_formats_ignore_nulls` = """null"""
    run_test(schema, `all_string_formats_ignore_nulls`, true, "DRAFT_2019_09")
  }
}
