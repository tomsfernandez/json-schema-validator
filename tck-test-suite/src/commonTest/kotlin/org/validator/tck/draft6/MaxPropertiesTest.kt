package org.validator.tck.draft6

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class MaxPropertiesTest {
  @Test
  public fun maxProperties_validation(): Unit {
    val schema = """
        |{
        |    "maxProperties": 2
        |}
        """.trimMargin()
    val `shorter_is_valid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `shorter_is_valid`, true, "DRAFT_6")
    val `exact_length_is_valid` = """
        |{
        |    "foo": 1,
        |    "bar": 2
        |}
        """.trimMargin()
    run_test(schema, `exact_length_is_valid`, true, "DRAFT_6")
    val `too_long_is_invalid` = """
        |{
        |    "foo": 1,
        |    "bar": 2,
        |    "baz": 3
        |}
        """.trimMargin()
    run_test(schema, `too_long_is_invalid`, false, "DRAFT_6")
    val `ignores_arrays` = """
        |[
        |    1,
        |    2,
        |    3
        |]
        """.trimMargin()
    run_test(schema, `ignores_arrays`, true, "DRAFT_6")
    val `ignores_strings` = """"foobar""""
    run_test(schema, `ignores_strings`, true, "DRAFT_6")
    val `ignores_other_non_objects` = """12"""
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_6")
  }

  @Test
  public fun maxProperties___0_means_the_object_is_empty(): Unit {
    val schema = """
        |{
        |    "maxProperties": 0
        |}
        """.trimMargin()
    val `no_properties_is_valid` = """
        |{
        |}
        """.trimMargin()
    run_test(schema, `no_properties_is_valid`, true, "DRAFT_6")
    val `one_property_is_invalid` = """
        |{
        |    "foo": 1
        |}
        """.trimMargin()
    run_test(schema, `one_property_is_invalid`, false, "DRAFT_6")
  }
}
