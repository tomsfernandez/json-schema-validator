package org.validator.tck.draft4.optional

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class UnicodeTest {
  @Test
  public fun unicode_semantics_should_be_used_for_all_pattern_matching(): Unit {
    val schema = "{\"pattern\":\"\\\\wcole\"}"
    val `literal_unicode_character_in_json_string` =
        "\"Les hivers de mon enfance étaient des saisons longues, longues. Nous vivions en trois lieux: l'école, l'église et la patinoire; mais la vraie vie était sur la patinoire.\""
    run_test(schema, `literal_unicode_character_in_json_string`, true, "DRAFT_4")
    val `unicode_character_in_hex_format_in_string` =
        "\"Les hivers de mon enfance étaient des saisons longues, longues. Nous vivions en trois lieux: l'école, l'église et la patinoire; mais la vraie vie était sur la patinoire.\""
    run_test(schema, `unicode_character_in_hex_format_in_string`, true, "DRAFT_4")
    val `unicode_matching_is_case_sensitive` =
        "\"LES HIVERS DE MON ENFANCE ÉTAIENT DES SAISONS LONGUES, LONGUES. NOUS VIVIONS EN TROIS LIEUX: L'ÉCOLE, L'ÉGLISE ET LA PATINOIRE; MAIS LA VRAIE VIE ÉTAIT SUR LA PATINOIRE.\""
    run_test(schema, `unicode_matching_is_case_sensitive`, false, "DRAFT_4")
  }

  @Test
  public fun unicode_characters_do_not_match_ascii_ranges(): Unit {
    val schema = "{\"pattern\":\"[a-z]cole\"}"
    val `literal_unicode_character_in_json_string` =
        "\"Les hivers de mon enfance étaient des saisons longues, longues. Nous vivions en trois lieux: l'école, l'église et la patinoire; mais la vraie vie était sur la patinoire.\""
    run_test(schema, `literal_unicode_character_in_json_string`, false, "DRAFT_4")
    val `unicode_character_in_hex_format_in_string` =
        "\"Les hivers de mon enfance étaient des saisons longues, longues. Nous vivions en trois lieux: l'école, l'église et la patinoire; mais la vraie vie était sur la patinoire.\""
    run_test(schema, `unicode_character_in_hex_format_in_string`, false, "DRAFT_4")
    val `ascii_characters_match` =
        "\"Les hivers de mon enfance etaient des saisons longues, longues. Nous vivions en trois lieux: l'ecole, l'eglise et la patinoire; mais la vraie vie etait sur la patinoire.\""
    run_test(schema, `ascii_characters_match`, true, "DRAFT_4")
  }

  @Test
  public fun unicode_digits_are_more_than_0_through_9(): Unit {
    val schema = "{\"pattern\":\"^\\\\d+${'$'}\"}"
    val `ascii_digits` = "\"42\""
    run_test(schema, `ascii_digits`, true, "DRAFT_4")
    val `ascii_non_digits` = "\"-%#\""
    run_test(schema, `ascii_non_digits`, false, "DRAFT_4")
    val `non_ascii_digits_BENGALI_DIGIT_FOUR__BENGALI_DIGIT_TWO` = "\"৪২\""
    run_test(schema, `non_ascii_digits_BENGALI_DIGIT_FOUR__BENGALI_DIGIT_TWO`, true, "DRAFT_4")
  }

  @Test
  public fun unicode_semantics_should_be_used_for_all_patternProperties_matching(): Unit {
    val schema =
        "{\"type\":\"object\",\"patternProperties\":{\"\\\\wcole\":{}},\"additionalProperties\":false}"
    val `literal_unicode_character_in_json_string` = "{\"l'école\":\"pas de vraie vie\"}"
    run_test(schema, `literal_unicode_character_in_json_string`, true, "DRAFT_4")
    val `unicode_character_in_hex_format_in_string` = "{\"l'école\":\"pas de vraie vie\"}"
    run_test(schema, `unicode_character_in_hex_format_in_string`, true, "DRAFT_4")
    val `unicode_matching_is_case_sensitive` = "{\"L'ÉCOLE\":\"PAS DE VRAIE VIE\"}"
    run_test(schema, `unicode_matching_is_case_sensitive`, false, "DRAFT_4")
  }
}
