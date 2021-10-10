package org.validator

import org.validator.rules.any.TypeRuleParser
import org.validator.testing.JsonParsingHelper
import kotlin.test.Test
import kotlin.test.assertEquals

class TypeRuleTest : JsonParsingHelper {

    @Test
    fun test_valid_object() {
        test_rule_validates_correctly("/schemas/type/object-type.json", "/schemas/type/object-type.instance.json", true)
    }

    private fun test_rule_validates_correctly(schemaFile: String, payloadFile: String, expected: Boolean) {
        val schema = parseJson(schemaFile)
        val payload = parseJson(payloadFile)
        val rule = TypeRuleParser.parse(schema)
        assertEquals(rule.right()?.eval(payload)?.isEmpty(), expected)
    }
}
