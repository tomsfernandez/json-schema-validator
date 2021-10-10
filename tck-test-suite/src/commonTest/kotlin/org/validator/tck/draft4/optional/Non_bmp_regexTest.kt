package org.validator.tck.draft4.optional

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class Non_bmp_regexTest {
  @Test
  public fun Proper_UTF_16_surrogate_pair_handling__pattern(): Unit {
    val schema = "{\"pattern\":\"^🐲*${'$'}\"}"
    val `matches_empty` = "\"\""
    run_test(schema, `matches_empty`, true, "DRAFT_4")
    val `matches_single` = "\"🐲\""
    run_test(schema, `matches_single`, true, "DRAFT_4")
    val `matches_two` = "\"🐲🐲\""
    run_test(schema, `matches_two`, true, "DRAFT_4")
    val `doesn't_match_one` = "\"🐉\""
    run_test(schema, `doesn't_match_one`, false, "DRAFT_4")
    val `doesn't_match_two` = "\"🐉🐉\""
    run_test(schema, `doesn't_match_two`, false, "DRAFT_4")
    val `doesn't_match_one_ASCII` = "\"D\""
    run_test(schema, `doesn't_match_one_ASCII`, false, "DRAFT_4")
    val `doesn't_match_two_ASCII` = "\"DD\""
    run_test(schema, `doesn't_match_two_ASCII`, false, "DRAFT_4")
  }

  @Test
  public fun Proper_UTF_16_surrogate_pair_handling__patternProperties(): Unit {
    val schema = "{\"patternProperties\":{\"^🐲*${'$'}\":{\"type\":\"integer\"}}}"
    val `matches_empty` = "{\"\":1}"
    run_test(schema, `matches_empty`, true, "DRAFT_4")
    val `matches_single` = "{\"🐲\":1}"
    run_test(schema, `matches_single`, true, "DRAFT_4")
    val `matches_two` = "{\"🐲🐲\":1}"
    run_test(schema, `matches_two`, true, "DRAFT_4")
    val `doesn't_match_one` = "{\"🐲\":\"hello\"}"
    run_test(schema, `doesn't_match_one`, false, "DRAFT_4")
    val `doesn't_match_two` = "{\"🐲🐲\":\"hello\"}"
    run_test(schema, `doesn't_match_two`, false, "DRAFT_4")
  }
}
