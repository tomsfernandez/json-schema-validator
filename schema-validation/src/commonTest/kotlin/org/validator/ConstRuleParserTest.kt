package org.validator

import org.validator.testing.JsonParsingHelper
import org.validator.rules.any.ConstRuleParser
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstRuleParserTest : JsonParsingHelper {

    @Test
    fun invalid_const_object() {
        test_const_rule_obj("/schemas/const/object-const.json", "/schemas/const/object-const.invalid-instance.json", false)
    }

    @Test
    fun valid_const_object() {
        test_const_rule_obj("/schemas/const/object-const.json", "/schemas/const/object-const.instance.json", true)
    }

    @Test
    fun invalid_const_string() {
        test_const_rule("/schemas/const/string-const.json", "/schemas/const/string-const.invalid-instance.json", false)
    }

    @Test
    fun valid_const_string() {
        test_const_rule("/schemas/const/string-const.json", "/schemas/const/string-const.instance.json", true)
    }

    private fun test_const_rule_obj(schemaPath: String, payloadPath: String, valid: Boolean) {
        val schema = parseJson(schemaPath)
        val payload = parseJson(payloadPath)
        val rule = ConstRuleParser.parse(schema)
        assertEquals(rule.right()?.eval(payload)?.isEmpty(), valid)
    }

    private fun test_const_rule(schemaPath: String, payloadPath: String, valid: Boolean) {
        val schema = parseJson(schemaPath)
        val payload = parseJsonScalar(payloadPath)
        val rule = ConstRuleParser.parse(schema)
        assertEquals(rule.right()?.eval(payload)?.isEmpty(), valid)
    }
}
