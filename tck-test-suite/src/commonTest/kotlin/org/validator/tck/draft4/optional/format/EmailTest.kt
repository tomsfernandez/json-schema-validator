package org.validator.tck.draft4.optional.format

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class EmailTest {
  @Test
  public fun validation_of_e_mail_addresses(): Unit {
    val schema = "{\"format\":\"email\"}"
    val `a_valid_e_mail_address` = "\"joe.bloggs@example.com\""
    run_test(schema, `a_valid_e_mail_address`, true, "DRAFT_4")
    val `an_invalid_e_mail_address` = "\"2962\""
    run_test(schema, `an_invalid_e_mail_address`, false, "DRAFT_4")
    val `tilde_in_local_part_is_valid` = "\"te~st@example.com\""
    run_test(schema, `tilde_in_local_part_is_valid`, true, "DRAFT_4")
    val `tilde_before_local_part_is_valid` = "\"~test@example.com\""
    run_test(schema, `tilde_before_local_part_is_valid`, true, "DRAFT_4")
    val `tilde_after_local_part_is_valid` = "\"test~@example.com\""
    run_test(schema, `tilde_after_local_part_is_valid`, true, "DRAFT_4")
    val `dot_before_local_part_is_not_valid` = "\".test@example.com\""
    run_test(schema, `dot_before_local_part_is_not_valid`, false, "DRAFT_4")
    val `dot_after_local_part_is_not_valid` = "\"test.@example.com\""
    run_test(schema, `dot_after_local_part_is_not_valid`, false, "DRAFT_4")
    val `two_separated_dots_inside_local_part_are_valid` = "\"te.s.t@example.com\""
    run_test(schema, `two_separated_dots_inside_local_part_are_valid`, true, "DRAFT_4")
    val `two_subsequent_dots_inside_local_part_are_not_valid` = "\"te..st@example.com\""
    run_test(schema, `two_subsequent_dots_inside_local_part_are_not_valid`, false, "DRAFT_4")
  }
}
