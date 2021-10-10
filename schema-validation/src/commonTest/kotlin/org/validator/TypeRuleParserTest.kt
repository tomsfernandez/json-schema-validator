package org.validator

import org.validator.testing.adapt
import org.validator.testing.loadResourceText
import org.validator.rules.any.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TypeRuleParserTest {

    @Test
    fun parse_object_type() {
        test_rule_is_correct("/schemas/type/object-type.json", ObjectRule)
    }

    @Test
    fun parse_array_type() {
        test_rule_is_correct("/schemas/type/array-type.json", ArrayRule)
    }

    @Test
    fun parse_string_type() {
        test_rule_is_correct("/schemas/type/string-type.json", StringRule)
    }

    @Test
    fun parse_number_type() {
        test_rule_is_correct("/schemas/type/number-type.json", NumberRule)
    }

    @Test
    fun parse_boolean_type() {
        test_rule_is_correct("/schemas/type/boolean-type.json", BooleanRule)
    }

    @Test
    fun parse_null_type() {
        test_rule_is_correct("/schemas/type/null-type.json", NullRule)
    }

    @Test
    fun parse_array_of_types() {
        test_rule_is_correct("/schemas/type/array-of-types.json", OrValidationRule(listOf(StringRule, NumberRule)))
    }

    private fun test_rule_is_correct(file: String, expected: ValidationRule) {
        val schemaAsString = loadResourceText(file)
        val schema = adapt(schemaAsString) as JsonObject
        val rule = TypeRuleParser.parse(schema)
        assertEquals(rule.left(), null)
        when(rule.right()) {
            expected -> assertTrue(true)
            else -> assertTrue(false)
        }
    }
}
