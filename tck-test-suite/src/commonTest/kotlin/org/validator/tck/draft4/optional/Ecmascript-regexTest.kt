package org.validator.tck.draft4.optional

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class `Ecmascript_regexTest` {
  @Test
  public fun `ECMA_262_regex_$_does_not_match_trailing_newline`(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^abc${'$'}\"}"
    val `matches_in_Python__but_should_not_in_jsonschema` = "\"abc\\\\n\""
    run_test(schema, `matches_in_Python__but_should_not_in_jsonschema`, false, "DRAFT_4")
    val `should_match` = "\"abc\""
    run_test(schema, `should_match`, true, "DRAFT_4")
  }

  @Test
  public fun ECMA_262_regex_converts__t_to_horizontal_tab(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\t${'$'}\"}"
    val `does_not_match` = "\"\\\\t\""
    run_test(schema, `does_not_match`, false, "DRAFT_4")
    val `matches` = "\"\\t\""
    run_test(schema, `matches`, true, "DRAFT_4")
  }

  @Test
  public fun ECMA_262_regex_escapes_control_codes_with__c_and_upper_letter(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\cC${'$'}\"}"
    val `does_not_match` = "\"\\\\cC\""
    run_test(schema, `does_not_match`, false, "DRAFT_4")
    val `matches` = "\"\\u0003\""
    run_test(schema, `matches`, true, "DRAFT_4")
  }

  @Test
  public fun ECMA_262_regex_escapes_control_codes_with__c_and_lower_letter(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\cc${'$'}\"}"
    val `does_not_match` = "\"\\\\cc\""
    run_test(schema, `does_not_match`, false, "DRAFT_4")
    val `matches` = "\"\\u0003\""
    run_test(schema, `matches`, true, "DRAFT_4")
  }

  @Test
  public fun ECMA_262__d_matches_ascii_digits_only(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\d${'$'}\"}"
    val `ASCII_zero_matches` = "\"0\""
    run_test(schema, `ASCII_zero_matches`, true, "DRAFT_4")
    val `NKO_DIGIT_ZERO_does_not_match_unlike_e_g__Python` = "\"߀\""
    run_test(schema, `NKO_DIGIT_ZERO_does_not_match_unlike_e_g__Python`, false, "DRAFT_4")
    val `NKO_DIGIT_ZERO_as__u_escape_does_not_match` = "\"߀\""
    run_test(schema, `NKO_DIGIT_ZERO_as__u_escape_does_not_match`, false, "DRAFT_4")
  }

  @Test
  public fun ECMA_262__D_matches_everything_but_ascii_digits(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\D${'$'}\"}"
    val `ASCII_zero_does_not_match` = "\"0\""
    run_test(schema, `ASCII_zero_does_not_match`, false, "DRAFT_4")
    val `NKO_DIGIT_ZERO_matches_unlike_e_g__Python` = "\"߀\""
    run_test(schema, `NKO_DIGIT_ZERO_matches_unlike_e_g__Python`, true, "DRAFT_4")
    val `NKO_DIGIT_ZERO_as__u_escape_matches` = "\"߀\""
    run_test(schema, `NKO_DIGIT_ZERO_as__u_escape_matches`, true, "DRAFT_4")
  }

  @Test
  public fun ECMA_262__w_matches_ascii_letters_only(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\w${'$'}\"}"
    val `ASCII_'a'_matches` = "\"a\""
    run_test(schema, `ASCII_'a'_matches`, true, "DRAFT_4")
    val `latin_1_e_acute_does_not_match_unlike_e_g__Python` = "\"é\""
    run_test(schema, `latin_1_e_acute_does_not_match_unlike_e_g__Python`, false, "DRAFT_4")
  }

  @Test
  public fun ECMA_262__W_matches_everything_but_ascii_letters(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\W${'$'}\"}"
    val `ASCII_'a'_does_not_match` = "\"a\""
    run_test(schema, `ASCII_'a'_does_not_match`, false, "DRAFT_4")
    val `latin_1_e_acute_matches_unlike_e_g__Python` = "\"é\""
    run_test(schema, `latin_1_e_acute_matches_unlike_e_g__Python`, true, "DRAFT_4")
  }

  @Test
  public fun ECMA_262__s_matches_whitespace(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\s${'$'}\"}"
    val `ASCII_space_matches` = "\" \""
    run_test(schema, `ASCII_space_matches`, true, "DRAFT_4")
    val `Character_tabulation_matches` = "\"\\t\""
    run_test(schema, `Character_tabulation_matches`, true, "DRAFT_4")
    val `Line_tabulation_matches` = "\"\\u000b\""
    run_test(schema, `Line_tabulation_matches`, true, "DRAFT_4")
    val `Form_feed_matches` = "\"\\f\""
    run_test(schema, `Form_feed_matches`, true, "DRAFT_4")
    val `latin_1_non_breaking_space_matches` = "\" \""
    run_test(schema, `latin_1_non_breaking_space_matches`, true, "DRAFT_4")
    val `zero_width_whitespace_matches` = "\"﻿\""
    run_test(schema, `zero_width_whitespace_matches`, true, "DRAFT_4")
    val `line_feed_matches_line_terminator` = "\"\\n\""
    run_test(schema, `line_feed_matches_line_terminator`, true, "DRAFT_4")
    val `paragraph_separator_matches_line_terminator` = "\" \""
    run_test(schema, `paragraph_separator_matches_line_terminator`, true, "DRAFT_4")
    val `EM_SPACE_matches_Space_Separator` = "\" \""
    run_test(schema, `EM_SPACE_matches_Space_Separator`, true, "DRAFT_4")
    val `Non_whitespace_control_does_not_match` = "\"\\u0001\""
    run_test(schema, `Non_whitespace_control_does_not_match`, false, "DRAFT_4")
    val `Non_whitespace_does_not_match` = "\"–\""
    run_test(schema, `Non_whitespace_does_not_match`, false, "DRAFT_4")
  }

  @Test
  public fun ECMA_262__S_matches_everything_but_whitespace(): Unit {
    val schema = "{\"type\":\"string\",\"pattern\":\"^\\\\S${'$'}\"}"
    val `ASCII_space_does_not_match` = "\" \""
    run_test(schema, `ASCII_space_does_not_match`, false, "DRAFT_4")
    val `Character_tabulation_does_not_match` = "\"\\t\""
    run_test(schema, `Character_tabulation_does_not_match`, false, "DRAFT_4")
    val `Line_tabulation_does_not_match` = "\"\\u000b\""
    run_test(schema, `Line_tabulation_does_not_match`, false, "DRAFT_4")
    val `Form_feed_does_not_match` = "\"\\f\""
    run_test(schema, `Form_feed_does_not_match`, false, "DRAFT_4")
    val `latin_1_non_breaking_space_does_not_match` = "\" \""
    run_test(schema, `latin_1_non_breaking_space_does_not_match`, false, "DRAFT_4")
    val `zero_width_whitespace_does_not_match` = "\"﻿\""
    run_test(schema, `zero_width_whitespace_does_not_match`, false, "DRAFT_4")
    val `line_feed_does_not_match_line_terminator` = "\"\\n\""
    run_test(schema, `line_feed_does_not_match_line_terminator`, false, "DRAFT_4")
    val `paragraph_separator_does_not_match_line_terminator` = "\" \""
    run_test(schema, `paragraph_separator_does_not_match_line_terminator`, false, "DRAFT_4")
    val `EM_SPACE_does_not_match_Space_Separator` = "\" \""
    run_test(schema, `EM_SPACE_does_not_match_Space_Separator`, false, "DRAFT_4")
    val `Non_whitespace_control_matches` = "\"\\u0001\""
    run_test(schema, `Non_whitespace_control_matches`, true, "DRAFT_4")
    val `Non_whitespace_matches` = "\"–\""
    run_test(schema, `Non_whitespace_matches`, true, "DRAFT_4")
  }
}
