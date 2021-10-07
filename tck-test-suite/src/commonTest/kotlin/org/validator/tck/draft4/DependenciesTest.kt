package org.validator.tck.draft4

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class DependenciesTest {
  @Test
  public fun dependencies(): Unit {
    val schema = "{\"dependencies\":{\"bar\":[\"foo\"]}}"
    val `neither` = "{}"
    run_test(schema, `neither`, true, "DRAFT_4")
    val `nondependant` = "{\"foo\":1}"
    run_test(schema, `nondependant`, true, "DRAFT_4")
    val `with_dependency` = "{\"foo\":1,\"bar\":2}"
    run_test(schema, `with_dependency`, true, "DRAFT_4")
    val `missing_dependency` = "{\"bar\":2}"
    run_test(schema, `missing_dependency`, false, "DRAFT_4")
    val `ignores_arrays` = "[\"bar\"]"
    run_test(schema, `ignores_arrays`, true, "DRAFT_4")
    val `ignores_strings` = "\"foobar\""
    run_test(schema, `ignores_strings`, true, "DRAFT_4")
    val `ignores_other_non_objects` = "12"
    run_test(schema, `ignores_other_non_objects`, true, "DRAFT_4")
  }

  @Test
  public fun multiple_dependencies(): Unit {
    val schema = "{\"dependencies\":{\"quux\":[\"foo\",\"bar\"]}}"
    val `neither` = "{}"
    run_test(schema, `neither`, true, "DRAFT_4")
    val `nondependants` = "{\"foo\":1,\"bar\":2}"
    run_test(schema, `nondependants`, true, "DRAFT_4")
    val `with_dependencies` = "{\"foo\":1,\"bar\":2,\"quux\":3}"
    run_test(schema, `with_dependencies`, true, "DRAFT_4")
    val `missing_dependency` = "{\"foo\":1,\"quux\":2}"
    run_test(schema, `missing_dependency`, false, "DRAFT_4")
    val `missing_other_dependency` = "{\"bar\":1,\"quux\":2}"
    run_test(schema, `missing_other_dependency`, false, "DRAFT_4")
    val `missing_both_dependencies` = "{\"quux\":1}"
    run_test(schema, `missing_both_dependencies`, false, "DRAFT_4")
  }

  @Test
  public fun multiple_dependencies_subschema(): Unit {
    val schema =
        "{\"dependencies\":{\"bar\":{\"properties\":{\"foo\":{\"type\":\"integer\"},\"bar\":{\"type\":\"integer\"}}}}}"
    val `valid` = "{\"foo\":1,\"bar\":2}"
    run_test(schema, `valid`, true, "DRAFT_4")
    val `no_dependency` = "{\"foo\":\"quux\"}"
    run_test(schema, `no_dependency`, true, "DRAFT_4")
    val `wrong_type` = "{\"foo\":\"quux\",\"bar\":2}"
    run_test(schema, `wrong_type`, false, "DRAFT_4")
    val `wrong_type_other` = "{\"foo\":2,\"bar\":\"quux\"}"
    run_test(schema, `wrong_type_other`, false, "DRAFT_4")
    val `wrong_type_both` = "{\"foo\":\"quux\",\"bar\":\"quux\"}"
    run_test(schema, `wrong_type_both`, false, "DRAFT_4")
  }

  @Test
  public fun dependencies_with_escaped_characters(): Unit {
    val schema =
        "{\"dependencies\":{\"foo\\nbar\":[\"foo\\rbar\"],\"foo\\tbar\":{\"minProperties\":4},\"foo'bar\":{\"required\":[\"foo\\\"bar\"]},\"foo\\\"bar\":[\"foo'bar\"]}}"
    val `valid_object_1` = "{\"foo\\nbar\":1,\"foo\\rbar\":2}"
    run_test(schema, `valid_object_1`, true, "DRAFT_4")
    val `valid_object_2` = "{\"foo\\tbar\":1,\"a\":2,\"b\":3,\"c\":4}"
    run_test(schema, `valid_object_2`, true, "DRAFT_4")
    val `valid_object_3` = "{\"foo'bar\":1,\"foo\\\"bar\":2}"
    run_test(schema, `valid_object_3`, true, "DRAFT_4")
    val `invalid_object_1` = "{\"foo\\nbar\":1,\"foo\":2}"
    run_test(schema, `invalid_object_1`, false, "DRAFT_4")
    val `invalid_object_2` = "{\"foo\\tbar\":1,\"a\":2}"
    run_test(schema, `invalid_object_2`, false, "DRAFT_4")
    val `invalid_object_3` = "{\"foo'bar\":1}"
    run_test(schema, `invalid_object_3`, false, "DRAFT_4")
    val `invalid_object_4` = "{\"foo\\\"bar\":2}"
    run_test(schema, `invalid_object_4`, false, "DRAFT_4")
  }
}
