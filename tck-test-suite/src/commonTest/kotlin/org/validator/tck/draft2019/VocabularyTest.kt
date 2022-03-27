package org.validator.tck.draft2019

import kotlin.Unit
import kotlin.test.Test
import org.validator.tck.run_test

public class VocabularyTest {
  @Test
  public fun schema_that_uses_custom_metaschema_with_with_no_validation_vocabulary(): Unit {
    val schema = """
        |{
        |    "${'$'}id": "https://schema/using/no/validation",
        |    "${'$'}schema": "http://localhost:1234/draft2019-09/metaschema-no-validation.json",
        |    "properties": {
        |        "badProperty": false,
        |        "numberProperty": {
        |            "minimum": 10
        |        }
        |    }
        |}
        """.trimMargin()
    val `applicator_vocabulary_still_works` = """
        |{
        |    "badProperty": "this property should not exist"
        |}
        """.trimMargin()
    run_test(schema, `applicator_vocabulary_still_works`, false, "DRAFT_2019_09")
    val `no_validation__valid_number` = """
        |{
        |    "numberProperty": 20
        |}
        """.trimMargin()
    run_test(schema, `no_validation__valid_number`, true, "DRAFT_2019_09")
    val `no_validation__invalid_number__but_it_still_validates` = """
        |{
        |    "numberProperty": 1
        |}
        """.trimMargin()
    run_test(schema, `no_validation__invalid_number__but_it_still_validates`, true, "DRAFT_2019_09")
  }
}
