package org.validator.tck.draft4.optional.format

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class HostnameTest {
  @Test
  public fun validation_of_host_names(): Unit {
    val schema = "{\"format\":\"hostname\"}"
    val `a_valid_host_name` = "\"www.example.com\""
    run_test(schema, `a_valid_host_name`, true, "DRAFT_4")
    val `a_host_name_starting_with_an_illegal_character` = "\"-a-host-name-that-starts-with--\""
    run_test(schema, `a_host_name_starting_with_an_illegal_character`, false, "DRAFT_4")
    val `a_host_name_containing_illegal_characters` = "\"not_a_valid_host_name\""
    run_test(schema, `a_host_name_containing_illegal_characters`, false, "DRAFT_4")
    val `a_host_name_with_a_component_too_long` =
        "\"a-vvvvvvvvvvvvvvvveeeeeeeeeeeeeeeerrrrrrrrrrrrrrrryyyyyyyyyyyyyyyy-long-host-name-component\""
    run_test(schema, `a_host_name_with_a_component_too_long`, false, "DRAFT_4")
    val `starts_with_hyphen` = "\"-hostname\""
    run_test(schema, `starts_with_hyphen`, false, "DRAFT_4")
    val `ends_with_hyphen` = "\"hostname-\""
    run_test(schema, `ends_with_hyphen`, false, "DRAFT_4")
    val `starts_with_underscore` = "\"_hostname\""
    run_test(schema, `starts_with_underscore`, false, "DRAFT_4")
    val `ends_with_underscore` = "\"hostname_\""
    run_test(schema, `ends_with_underscore`, false, "DRAFT_4")
    val `contains_underscore` = "\"host_name\""
    run_test(schema, `contains_underscore`, false, "DRAFT_4")
    val `maximum_label_length` =
        "\"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijk.com\""
    run_test(schema, `maximum_label_length`, true, "DRAFT_4")
    val `exceeds_maximum_label_length` =
        "\"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijkl.com\""
    run_test(schema, `exceeds_maximum_label_length`, false, "DRAFT_4")
  }
}
