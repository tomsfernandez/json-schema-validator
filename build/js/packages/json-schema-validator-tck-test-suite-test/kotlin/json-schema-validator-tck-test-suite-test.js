(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'json-schema-validator-schema-validation', 'kotlin-test', 'kotlinx-serialization-kotlinx-serialization-json-js-legacy'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('json-schema-validator-schema-validation'), require('kotlin-test'), require('kotlinx-serialization-kotlinx-serialization-json-js-legacy'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-tck-test-suite-test'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'json-schema-validator-tck-test-suite-test'.");
    }if (typeof this['json-schema-validator-schema-validation'] === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-tck-test-suite-test'. Its dependency 'json-schema-validator-schema-validation' was not found. Please, check whether 'json-schema-validator-schema-validation' is loaded prior to 'json-schema-validator-tck-test-suite-test'.");
    }if (typeof this['kotlin-test'] === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-tck-test-suite-test'. Its dependency 'kotlin-test' was not found. Please, check whether 'kotlin-test' is loaded prior to 'json-schema-validator-tck-test-suite-test'.");
    }if (typeof this['kotlinx-serialization-kotlinx-serialization-json-js-legacy'] === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-tck-test-suite-test'. Its dependency 'kotlinx-serialization-kotlinx-serialization-json-js-legacy' was not found. Please, check whether 'kotlinx-serialization-kotlinx-serialization-json-js-legacy' is loaded prior to 'json-schema-validator-tck-test-suite-test'.");
    }root['json-schema-validator-tck-test-suite-test'] = factory(typeof this['json-schema-validator-tck-test-suite-test'] === 'undefined' ? {} : this['json-schema-validator-tck-test-suite-test'], kotlin, this['json-schema-validator-schema-validation'], this['kotlin-test'], this['kotlinx-serialization-kotlinx-serialization-json-js-legacy']);
  }
}(this, function (_, Kotlin, $module$json_schema_validator_schema_validation, $module$kotlin_test, $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var rules = $module$json_schema_validator_schema_validation.org.validator.rules;
  var equals = Kotlin.equals;
  var RuntimeException_init = Kotlin.kotlin.RuntimeException_init_pdl1vj$;
  var JsonObject = $module$json_schema_validator_schema_validation.org.validator.JsonObject;
  var throwCCE = Kotlin.throwCCE;
  var assertEquals = $module$kotlin_test.kotlin.test.assertEquals_3m0tl5$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var assertTrue = $module$kotlin_test.kotlin.test.assertTrue_ifx8ge$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var test = $module$kotlin_test.kotlin.test.test;
  var suite = $module$kotlin_test.kotlin.test.suite;
  var StringJsonScalar = $module$json_schema_validator_schema_validation.org.validator.StringJsonScalar;
  var first = Kotlin.kotlin.text.first_gw00vp$;
  var last = Kotlin.kotlin.text.last_gw00vp$;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Json = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.Json;
  var JsonObject_0 = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.JsonObject;
  var JsonArray = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.JsonArray;
  var JsonPrimitive = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.JsonPrimitive;
  var Pair = Kotlin.kotlin.Pair;
  var toMap = Kotlin.kotlin.collections.toMap_6hr0sd$;
  var DefaultJsonObject = $module$json_schema_validator_schema_validation.org.validator.DefaultJsonObject;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var DefaultJsonArray = $module$json_schema_validator_schema_validation.org.validator.DefaultJsonArray;
  var validator = $module$json_schema_validator_schema_validation.org.validator;
  var json = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json;
  var get_int = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_int_59esu7$;
  var IntJsonScalar = $module$json_schema_validator_schema_validation.org.validator.IntJsonScalar;
  var get_intOrNull = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_intOrNull_59esu7$;
  var get_boolean = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_boolean_59esu7$;
  var BooleanJsonScalar = $module$json_schema_validator_schema_validation.org.validator.BooleanJsonScalar;
  var get_booleanOrNull = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_booleanOrNull_59esu7$;
  var get_double = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_double_59esu7$;
  var NumberJsonScalar = $module$json_schema_validator_schema_validation.org.validator.NumberJsonScalar;
  var get_doubleOrNull = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_doubleOrNull_59esu7$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  function provideParser(draft) {
    var tmp$;
    if (equals(draft, 'DRAFT_4'))
      tmp$ = rules.DraftParsers.DRAFT_4;
    else
      throw RuntimeException_init('something');
    return tmp$;
  }
  function run_test(schemaAsString, payloadAsString, conforms, draft) {
    var tmp$;
    var schema = Kotlin.isType(tmp$ = adapt(schemaAsString), JsonObject) ? tmp$ : throwCCE();
    var maybeRule = provideParser(draft).parse_3boyfh$(schema);
    assertEquals(null, maybeRule.left());
    var rule = ensureNotNull(maybeRule.right());
    var payloadAsObj = adapt(payloadAsString);
    var results = rule.eval_vzh9da$(payloadAsObj);
    assertTrue(results.isEmpty() === conforms, null);
  }
  function AdditionalItemsTest() {
  }
  AdditionalItemsTest.prototype.additionalItems_as_schema = function () {
    var schema = '{"items":[{}],"additionalItems":{"type":"integer"}}';
    var additional_items_match_schema = '[null,2,3,4]';
    run_test(schema, additional_items_match_schema, true, 'DRAFT_4');
    var additional_items_do_not_match_schema = '[null,2,3,"foo"]';
    run_test(schema, additional_items_do_not_match_schema, false, 'DRAFT_4');
  };
  AdditionalItemsTest.prototype.when_items_is_schema__additionalItems_does_nothing = function () {
    var schema = '{"items":{},"additionalItems":false}';
    var all_items_match_schema = '[1,2,3,4,5]';
    run_test(schema, all_items_match_schema, true, 'DRAFT_4');
  };
  AdditionalItemsTest.prototype.array_of_items_with_no_additionalItems_permitted = function () {
    var schema = '{"items":[{},{},{}],"additionalItems":false}';
    var empty_array = '[]';
    run_test(schema, empty_array, true, 'DRAFT_4');
    var fewer_number_of_items_present_1 = '[1]';
    run_test(schema, fewer_number_of_items_present_1, true, 'DRAFT_4');
    var fewer_number_of_items_present_2 = '[1,2]';
    run_test(schema, fewer_number_of_items_present_2, true, 'DRAFT_4');
    var equal_number_of_items_present = '[1,2,3]';
    run_test(schema, equal_number_of_items_present, true, 'DRAFT_4');
    var additional_items_are_not_permitted = '[1,2,3,4]';
    run_test(schema, additional_items_are_not_permitted, false, 'DRAFT_4');
  };
  AdditionalItemsTest.prototype.additionalItems_as_false_without_items = function () {
    var schema = '{"additionalItems":false}';
    var items_defaults_to_empty_schema_so_everything_is_valid = '[1,2,3,4,5]';
    run_test(schema, items_defaults_to_empty_schema_so_everything_is_valid, true, 'DRAFT_4');
    var ignores_non_arrays = '{"foo":"bar"}';
    run_test(schema, ignores_non_arrays, true, 'DRAFT_4');
  };
  AdditionalItemsTest.prototype.additionalItems_are_allowed_by_default = function () {
    var schema = '{"items":[{"type":"integer"}]}';
    var only_the_first_item_is_validated = '[1,"foo",false]';
    run_test(schema, only_the_first_item_is_validated, true, 'DRAFT_4');
  };
  AdditionalItemsTest.prototype.additionalItems_should_not_look_in_applicators__valid_case = function () {
    var schema = '{"allOf":[{"items":[{"type":"integer"}]}],"additionalItems":{"type":"boolean"}}';
    var items_defined_in_allOf_are_not_examined = '[1,null]';
    run_test(schema, items_defined_in_allOf_are_not_examined, true, 'DRAFT_4');
  };
  AdditionalItemsTest.prototype.additionalItems_should_not_look_in_applicators__invalid_case = function () {
    var schema = '{"allOf":[{"items":[{"type":"integer"},{"type":"string"}]}],"items":[{"type":"integer"}],"additionalItems":{"type":"boolean"}}';
    var items_defined_in_allOf_are_not_examined = '[1,"hello"]';
    run_test(schema, items_defined_in_allOf_are_not_examined, false, 'DRAFT_4');
  };
  AdditionalItemsTest.prototype.items_validation_adjusts_the_starting_index_for_additionalItems = function () {
    var schema = '{"items":[{"type":"string"}],"additionalItems":{"type":"integer"}}';
    var valid_items = '["x",2,3]';
    run_test(schema, valid_items, true, 'DRAFT_4');
    var wrong_type_of_second_item = '["x","y"]';
    run_test(schema, wrong_type_of_second_item, false, 'DRAFT_4');
  };
  AdditionalItemsTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AdditionalItemsTest',
    interfaces: []
  };
  function AdditionalPropertiesTest() {
  }
  AdditionalPropertiesTest.prototype.additionalProperties_being_false_does_not_allow_other_properties = function () {
    var schema = '{"properties":{"foo":{},"bar":{}},"patternProperties":{"^v":{}},"additionalProperties":false}';
    var no_additional_properties_is_valid = '{"foo":1}';
    run_test(schema, no_additional_properties_is_valid, true, 'DRAFT_4');
    var an_additional_property_is_invalid = '{"foo":1,"bar":2,"quux":"boom"}';
    run_test(schema, an_additional_property_is_invalid, false, 'DRAFT_4');
    var ignores_arrays = '[1,2,3]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_strings = '"foobarbaz"';
    run_test(schema, ignores_strings, true, 'DRAFT_4');
    var ignores_other_non_objects = '12';
    run_test(schema, ignores_other_non_objects, true, 'DRAFT_4');
    var patternProperties_are_not_additional_properties = '{"foo":1,"vroom":2}';
    run_test(schema, patternProperties_are_not_additional_properties, true, 'DRAFT_4');
  };
  AdditionalPropertiesTest.prototype.non_ASCII_pattern_with_additionalProperties = function () {
    var schema = '{"patternProperties":{"^\xE1":{}},"additionalProperties":false}';
    var matching_the_pattern_is_valid = '{"\xE1rm\xE1nyos":2}';
    run_test(schema, matching_the_pattern_is_valid, true, 'DRAFT_4');
    var not_matching_the_pattern_is_invalid = '{"\xE9lm\xE9ny":2}';
    run_test(schema, not_matching_the_pattern_is_invalid, false, 'DRAFT_4');
  };
  AdditionalPropertiesTest.prototype.additionalProperties_allows_a_schema_which_should_validate = function () {
    var schema = '{"properties":{"foo":{},"bar":{}},"additionalProperties":{"type":"boolean"}}';
    var no_additional_properties_is_valid = '{"foo":1}';
    run_test(schema, no_additional_properties_is_valid, true, 'DRAFT_4');
    var an_additional_valid_property_is_valid = '{"foo":1,"bar":2,"quux":true}';
    run_test(schema, an_additional_valid_property_is_valid, true, 'DRAFT_4');
    var an_additional_invalid_property_is_invalid = '{"foo":1,"bar":2,"quux":12}';
    run_test(schema, an_additional_invalid_property_is_invalid, false, 'DRAFT_4');
  };
  AdditionalPropertiesTest.prototype.additionalProperties_can_exist_by_itself = function () {
    var schema = '{"additionalProperties":{"type":"boolean"}}';
    var an_additional_valid_property_is_valid = '{"foo":true}';
    run_test(schema, an_additional_valid_property_is_valid, true, 'DRAFT_4');
    var an_additional_invalid_property_is_invalid = '{"foo":1}';
    run_test(schema, an_additional_invalid_property_is_invalid, false, 'DRAFT_4');
  };
  AdditionalPropertiesTest.prototype.additionalProperties_are_allowed_by_default = function () {
    var schema = '{"properties":{"foo":{},"bar":{}}}';
    var additional_properties_are_allowed = '{"foo":1,"bar":2,"quux":true}';
    run_test(schema, additional_properties_are_allowed, true, 'DRAFT_4');
  };
  AdditionalPropertiesTest.prototype.additionalProperties_should_not_look_in_applicators = function () {
    var schema = '{"allOf":[{"properties":{"foo":{}}}],"additionalProperties":{"type":"boolean"}}';
    var properties_defined_in_allOf_are_not_examined = '{"foo":1,"bar":true}';
    run_test(schema, properties_defined_in_allOf_are_not_examined, false, 'DRAFT_4');
  };
  AdditionalPropertiesTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AdditionalPropertiesTest',
    interfaces: []
  };
  function AllOfTest() {
  }
  AllOfTest.prototype.allOf = function () {
    var schema = '{"allOf":[{"properties":{"bar":{"type":"integer"}},"required":["bar"]},{"properties":{"foo":{"type":"string"}},"required":["foo"]}]}';
    var allOf = '{"foo":"baz","bar":2}';
    run_test(schema, allOf, true, 'DRAFT_4');
    var mismatch_second = '{"foo":"baz"}';
    run_test(schema, mismatch_second, false, 'DRAFT_4');
    var mismatch_first = '{"bar":2}';
    run_test(schema, mismatch_first, false, 'DRAFT_4');
    var wrong_type = '{"foo":"baz","bar":"quux"}';
    run_test(schema, wrong_type, false, 'DRAFT_4');
  };
  AllOfTest.prototype.allOf_with_base_schema = function () {
    var schema = '{"properties":{"bar":{"type":"integer"}},"required":["bar"],"allOf":[{"properties":{"foo":{"type":"string"}},"required":["foo"]},{"properties":{"baz":{"type":"null"}},"required":["baz"]}]}';
    var valid = '{"foo":"quux","bar":2,"baz":null}';
    run_test(schema, valid, true, 'DRAFT_4');
    var mismatch_base_schema = '{"foo":"quux","baz":null}';
    run_test(schema, mismatch_base_schema, false, 'DRAFT_4');
    var mismatch_first_allOf = '{"bar":2,"baz":null}';
    run_test(schema, mismatch_first_allOf, false, 'DRAFT_4');
    var mismatch_second_allOf = '{"foo":"quux","bar":2}';
    run_test(schema, mismatch_second_allOf, false, 'DRAFT_4');
    var mismatch_both = '{"bar":2}';
    run_test(schema, mismatch_both, false, 'DRAFT_4');
  };
  AllOfTest.prototype.allOf_simple_types = function () {
    var schema = '{"allOf":[{"maximum":30},{"minimum":20}]}';
    var valid = '25';
    run_test(schema, valid, true, 'DRAFT_4');
    var mismatch_one = '35';
    run_test(schema, mismatch_one, false, 'DRAFT_4');
  };
  AllOfTest.prototype.allOf_with_one_empty_schema = function () {
    var schema = '{"allOf":[{}]}';
    var any_data_is_valid = '1';
    run_test(schema, any_data_is_valid, true, 'DRAFT_4');
  };
  AllOfTest.prototype.allOf_with_two_empty_schemas = function () {
    var schema = '{"allOf":[{},{}]}';
    var any_data_is_valid = '1';
    run_test(schema, any_data_is_valid, true, 'DRAFT_4');
  };
  AllOfTest.prototype.allOf_with_the_first_empty_schema = function () {
    var schema = '{"allOf":[{},{"type":"number"}]}';
    var number_is_valid = '1';
    run_test(schema, number_is_valid, true, 'DRAFT_4');
    var string_is_invalid = '"foo"';
    run_test(schema, string_is_invalid, false, 'DRAFT_4');
  };
  AllOfTest.prototype.allOf_with_the_last_empty_schema = function () {
    var schema = '{"allOf":[{"type":"number"},{}]}';
    var number_is_valid = '1';
    run_test(schema, number_is_valid, true, 'DRAFT_4');
    var string_is_invalid = '"foo"';
    run_test(schema, string_is_invalid, false, 'DRAFT_4');
  };
  AllOfTest.prototype.nested_allOf__to_check_validation_semantics = function () {
    var schema = '{"allOf":[{"allOf":[{"type":"null"}]}]}';
    var null_is_valid = 'null';
    run_test(schema, null_is_valid, true, 'DRAFT_4');
    var anything_non_null_is_invalid = '123';
    run_test(schema, anything_non_null_is_invalid, false, 'DRAFT_4');
  };
  AllOfTest.prototype.allOf_combined_with_anyOf__oneOf = function () {
    var schema = '{"allOf":[{"multipleOf":2}],"anyOf":[{"multipleOf":3}],"oneOf":[{"multipleOf":5}]}';
    var allOf__false__anyOf__false__oneOf__false = '1';
    run_test(schema, allOf__false__anyOf__false__oneOf__false, false, 'DRAFT_4');
    var allOf__false__anyOf__false__oneOf__true = '5';
    run_test(schema, allOf__false__anyOf__false__oneOf__true, false, 'DRAFT_4');
    var allOf__false__anyOf__true__oneOf__false = '3';
    run_test(schema, allOf__false__anyOf__true__oneOf__false, false, 'DRAFT_4');
    var allOf__false__anyOf__true__oneOf__true = '15';
    run_test(schema, allOf__false__anyOf__true__oneOf__true, false, 'DRAFT_4');
    var allOf__true__anyOf__false__oneOf__false = '2';
    run_test(schema, allOf__true__anyOf__false__oneOf__false, false, 'DRAFT_4');
    var allOf__true__anyOf__false__oneOf__true = '10';
    run_test(schema, allOf__true__anyOf__false__oneOf__true, false, 'DRAFT_4');
    var allOf__true__anyOf__true__oneOf__false = '6';
    run_test(schema, allOf__true__anyOf__true__oneOf__false, false, 'DRAFT_4');
    var allOf__true__anyOf__true__oneOf__true = '30';
    run_test(schema, allOf__true__anyOf__true__oneOf__true, true, 'DRAFT_4');
  };
  AllOfTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AllOfTest',
    interfaces: []
  };
  function AnyOfTest() {
  }
  AnyOfTest.prototype.anyOf = function () {
    var schema = '{"anyOf":[{"type":"integer"},{"minimum":2}]}';
    var first_anyOf_valid = '1';
    run_test(schema, first_anyOf_valid, true, 'DRAFT_4');
    var second_anyOf_valid = '2.5';
    run_test(schema, second_anyOf_valid, true, 'DRAFT_4');
    var both_anyOf_valid = '3';
    run_test(schema, both_anyOf_valid, true, 'DRAFT_4');
    var neither_anyOf_valid = '1.5';
    run_test(schema, neither_anyOf_valid, false, 'DRAFT_4');
  };
  AnyOfTest.prototype.anyOf_with_base_schema = function () {
    var schema = '{"type":"string","anyOf":[{"maxLength":2},{"minLength":4}]}';
    var mismatch_base_schema = '3';
    run_test(schema, mismatch_base_schema, false, 'DRAFT_4');
    var one_anyOf_valid = '"foobar"';
    run_test(schema, one_anyOf_valid, true, 'DRAFT_4');
    var both_anyOf_invalid = '"foo"';
    run_test(schema, both_anyOf_invalid, false, 'DRAFT_4');
  };
  AnyOfTest.prototype.anyOf_complex_types = function () {
    var schema = '{"anyOf":[{"properties":{"bar":{"type":"integer"}},"required":["bar"]},{"properties":{"foo":{"type":"string"}},"required":["foo"]}]}';
    var first_anyOf_valid_complex = '{"bar":2}';
    run_test(schema, first_anyOf_valid_complex, true, 'DRAFT_4');
    var second_anyOf_valid_complex = '{"foo":"baz"}';
    run_test(schema, second_anyOf_valid_complex, true, 'DRAFT_4');
    var both_anyOf_valid_complex = '{"foo":"baz","bar":2}';
    run_test(schema, both_anyOf_valid_complex, true, 'DRAFT_4');
    var neither_anyOf_valid_complex = '{"foo":2,"bar":"quux"}';
    run_test(schema, neither_anyOf_valid_complex, false, 'DRAFT_4');
  };
  AnyOfTest.prototype.anyOf_with_one_empty_schema = function () {
    var schema = '{"anyOf":[{"type":"number"},{}]}';
    var string_is_valid = '"foo"';
    run_test(schema, string_is_valid, true, 'DRAFT_4');
    var number_is_valid = '123';
    run_test(schema, number_is_valid, true, 'DRAFT_4');
  };
  AnyOfTest.prototype.nested_anyOf__to_check_validation_semantics = function () {
    var schema = '{"anyOf":[{"anyOf":[{"type":"null"}]}]}';
    var null_is_valid = 'null';
    run_test(schema, null_is_valid, true, 'DRAFT_4');
    var anything_non_null_is_invalid = '123';
    run_test(schema, anything_non_null_is_invalid, false, 'DRAFT_4');
  };
  AnyOfTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AnyOfTest',
    interfaces: []
  };
  function DefaultTest() {
  }
  DefaultTest.prototype.invalid_type_for_default = function () {
    var schema = '{"properties":{"foo":{"type":"integer","default":[]}}}';
    var valid_when_property_is_specified = '{"foo":13}';
    run_test(schema, valid_when_property_is_specified, true, 'DRAFT_4');
    var still_valid_when_the_invalid_default_is_used = '{}';
    run_test(schema, still_valid_when_the_invalid_default_is_used, true, 'DRAFT_4');
  };
  DefaultTest.prototype.invalid_string_value_for_default = function () {
    var schema = '{"properties":{"bar":{"type":"string","minLength":4,"default":"bad"}}}';
    var valid_when_property_is_specified = '{"bar":"good"}';
    run_test(schema, valid_when_property_is_specified, true, 'DRAFT_4');
    var still_valid_when_the_invalid_default_is_used = '{}';
    run_test(schema, still_valid_when_the_invalid_default_is_used, true, 'DRAFT_4');
  };
  DefaultTest.prototype.the_default_keyword_does_not_do_anything_if_the_property_is_missing = function () {
    var schema = '{"type":"object","properties":{"alpha":{"type":"number","maximum":3,"default":5}}}';
    var an_explicit_property_value_is_checked_against_maximum_passing = '{"alpha":1}';
    run_test(schema, an_explicit_property_value_is_checked_against_maximum_passing, true, 'DRAFT_4');
    var an_explicit_property_value_is_checked_against_maximum_failing = '{"alpha":5}';
    run_test(schema, an_explicit_property_value_is_checked_against_maximum_failing, false, 'DRAFT_4');
    var missing_properties_are_not_filled_in_with_the_default = '{}';
    run_test(schema, missing_properties_are_not_filled_in_with_the_default, true, 'DRAFT_4');
  };
  DefaultTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultTest',
    interfaces: []
  };
  function DependenciesTest() {
  }
  DependenciesTest.prototype.dependencies = function () {
    var schema = '{"dependencies":{"bar":["foo"]}}';
    var neither = '{}';
    run_test(schema, neither, true, 'DRAFT_4');
    var nondependant = '{"foo":1}';
    run_test(schema, nondependant, true, 'DRAFT_4');
    var with_dependency = '{"foo":1,"bar":2}';
    run_test(schema, with_dependency, true, 'DRAFT_4');
    var missing_dependency = '{"bar":2}';
    run_test(schema, missing_dependency, false, 'DRAFT_4');
    var ignores_arrays = '["bar"]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_strings = '"foobar"';
    run_test(schema, ignores_strings, true, 'DRAFT_4');
    var ignores_other_non_objects = '12';
    run_test(schema, ignores_other_non_objects, true, 'DRAFT_4');
  };
  DependenciesTest.prototype.multiple_dependencies = function () {
    var schema = '{"dependencies":{"quux":["foo","bar"]}}';
    var neither = '{}';
    run_test(schema, neither, true, 'DRAFT_4');
    var nondependants = '{"foo":1,"bar":2}';
    run_test(schema, nondependants, true, 'DRAFT_4');
    var with_dependencies = '{"foo":1,"bar":2,"quux":3}';
    run_test(schema, with_dependencies, true, 'DRAFT_4');
    var missing_dependency = '{"foo":1,"quux":2}';
    run_test(schema, missing_dependency, false, 'DRAFT_4');
    var missing_other_dependency = '{"bar":1,"quux":2}';
    run_test(schema, missing_other_dependency, false, 'DRAFT_4');
    var missing_both_dependencies = '{"quux":1}';
    run_test(schema, missing_both_dependencies, false, 'DRAFT_4');
  };
  DependenciesTest.prototype.multiple_dependencies_subschema = function () {
    var schema = '{"dependencies":{"bar":{"properties":{"foo":{"type":"integer"},"bar":{"type":"integer"}}}}}';
    var valid = '{"foo":1,"bar":2}';
    run_test(schema, valid, true, 'DRAFT_4');
    var no_dependency = '{"foo":"quux"}';
    run_test(schema, no_dependency, true, 'DRAFT_4');
    var wrong_type = '{"foo":"quux","bar":2}';
    run_test(schema, wrong_type, false, 'DRAFT_4');
    var wrong_type_other = '{"foo":2,"bar":"quux"}';
    run_test(schema, wrong_type_other, false, 'DRAFT_4');
    var wrong_type_both = '{"foo":"quux","bar":"quux"}';
    run_test(schema, wrong_type_both, false, 'DRAFT_4');
  };
  DependenciesTest.prototype.dependencies_with_escaped_characters = function () {
    var schema = '{"dependencies":{"foo\\nbar":["foo\\rbar"],"foo\\tbar":{"minProperties":4},"foo\'bar":{"required":["foo\\"bar"]},"foo\\"bar":["foo\'bar"]}}';
    var valid_object_1 = '{"foo\\nbar":1,"foo\\rbar":2}';
    run_test(schema, valid_object_1, true, 'DRAFT_4');
    var valid_object_2 = '{"foo\\tbar":1,"a":2,"b":3,"c":4}';
    run_test(schema, valid_object_2, true, 'DRAFT_4');
    var valid_object_3 = '{"foo\'bar":1,"foo\\"bar":2}';
    run_test(schema, valid_object_3, true, 'DRAFT_4');
    var invalid_object_1 = '{"foo\\nbar":1,"foo":2}';
    run_test(schema, invalid_object_1, false, 'DRAFT_4');
    var invalid_object_2 = '{"foo\\tbar":1,"a":2}';
    run_test(schema, invalid_object_2, false, 'DRAFT_4');
    var invalid_object_3 = '{"foo\'bar":1}';
    run_test(schema, invalid_object_3, false, 'DRAFT_4');
    var invalid_object_4 = '{"foo\\"bar":2}';
    run_test(schema, invalid_object_4, false, 'DRAFT_4');
  };
  DependenciesTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DependenciesTest',
    interfaces: []
  };
  function EnumTest() {
  }
  EnumTest.prototype.simple_enum_validation = function () {
    var schema = '{"enum":[1,2,3]}';
    var one_of_the_enum_is_valid = '1';
    run_test(schema, one_of_the_enum_is_valid, true, 'DRAFT_4');
    var something_else_is_invalid = '4';
    run_test(schema, something_else_is_invalid, false, 'DRAFT_4');
  };
  EnumTest.prototype.heterogeneous_enum_validation = function () {
    var schema = '{"enum":[6,"foo",[],true,{"foo":12}]}';
    var one_of_the_enum_is_valid = '[]';
    run_test(schema, one_of_the_enum_is_valid, true, 'DRAFT_4');
    var something_else_is_invalid = 'null';
    run_test(schema, something_else_is_invalid, false, 'DRAFT_4');
    var objects_are_deep_compared = '{"foo":false}';
    run_test(schema, objects_are_deep_compared, false, 'DRAFT_4');
    var valid_object_matches = '{"foo":12}';
    run_test(schema, valid_object_matches, true, 'DRAFT_4');
    var extra_properties_in_object_is_invalid = '{"foo":12,"boo":42}';
    run_test(schema, extra_properties_in_object_is_invalid, false, 'DRAFT_4');
  };
  EnumTest.prototype.heterogeneous_enum_with_null_validation = function () {
    var schema = '{"enum":[6,null]}';
    var null_is_valid = 'null';
    run_test(schema, null_is_valid, true, 'DRAFT_4');
    var number_is_valid = '6';
    run_test(schema, number_is_valid, true, 'DRAFT_4');
    var something_else_is_invalid = '"test"';
    run_test(schema, something_else_is_invalid, false, 'DRAFT_4');
  };
  EnumTest.prototype.enums_in_properties = function () {
    var schema = '{"type":"object","properties":{"foo":{"enum":["foo"]},"bar":{"enum":["bar"]}},"required":["bar"]}';
    var both_properties_are_valid = '{"foo":"foo","bar":"bar"}';
    run_test(schema, both_properties_are_valid, true, 'DRAFT_4');
    var wrong_foo_value = '{"foo":"foot","bar":"bar"}';
    run_test(schema, wrong_foo_value, false, 'DRAFT_4');
    var wrong_bar_value = '{"foo":"foo","bar":"bart"}';
    run_test(schema, wrong_bar_value, false, 'DRAFT_4');
    var missing_optional_property_is_valid = '{"bar":"bar"}';
    run_test(schema, missing_optional_property_is_valid, true, 'DRAFT_4');
    var missing_required_property_is_invalid = '{"foo":"foo"}';
    run_test(schema, missing_required_property_is_invalid, false, 'DRAFT_4');
    var missing_all_properties_is_invalid = '{}';
    run_test(schema, missing_all_properties_is_invalid, false, 'DRAFT_4');
  };
  EnumTest.prototype.enum_with_escaped_characters = function () {
    var schema = '{"enum":["foo\\nbar","foo\\rbar"]}';
    var member_1_is_valid = '"foo\\nbar"';
    run_test(schema, member_1_is_valid, true, 'DRAFT_4');
    var member_2_is_valid = '"foo\\rbar"';
    run_test(schema, member_2_is_valid, true, 'DRAFT_4');
    var another_string_is_invalid = '"abc"';
    run_test(schema, another_string_is_invalid, false, 'DRAFT_4');
  };
  EnumTest.prototype.enum_with_false_does_not_match_0 = function () {
    var schema = '{"enum":[false]}';
    var false_is_valid = 'false';
    run_test(schema, false_is_valid, true, 'DRAFT_4');
    var integer_zero_is_invalid = '0';
    run_test(schema, integer_zero_is_invalid, false, 'DRAFT_4');
    var float_zero_is_invalid = '0.0';
    run_test(schema, float_zero_is_invalid, false, 'DRAFT_4');
  };
  EnumTest.prototype.enum_with_true_does_not_match_1 = function () {
    var schema = '{"enum":[true]}';
    var true_is_valid = 'true';
    run_test(schema, true_is_valid, true, 'DRAFT_4');
    var integer_one_is_invalid = '1';
    run_test(schema, integer_one_is_invalid, false, 'DRAFT_4');
    var float_one_is_invalid = '1.0';
    run_test(schema, float_one_is_invalid, false, 'DRAFT_4');
  };
  EnumTest.prototype.enum_with_0_does_not_match_false = function () {
    var schema = '{"enum":[0]}';
    var false_is_invalid = 'false';
    run_test(schema, false_is_invalid, false, 'DRAFT_4');
    var integer_zero_is_valid = '0';
    run_test(schema, integer_zero_is_valid, true, 'DRAFT_4');
    var float_zero_is_valid = '0.0';
    run_test(schema, float_zero_is_valid, true, 'DRAFT_4');
  };
  EnumTest.prototype.enum_with_1_does_not_match_true = function () {
    var schema = '{"enum":[1]}';
    var true_is_invalid = 'true';
    run_test(schema, true_is_invalid, false, 'DRAFT_4');
    var integer_one_is_valid = '1';
    run_test(schema, integer_one_is_valid, true, 'DRAFT_4');
    var float_one_is_valid = '1.0';
    run_test(schema, float_one_is_valid, true, 'DRAFT_4');
  };
  EnumTest.prototype.nul_characters_in_strings = function () {
    var schema = '{"enum":["hello\\u0000there"]}';
    var match_string_with_nul = '"hello\\u0000there"';
    run_test(schema, match_string_with_nul, true, 'DRAFT_4');
    var do_not_match_string_lacking_nul = '"hellothere"';
    run_test(schema, do_not_match_string_lacking_nul, false, 'DRAFT_4');
  };
  EnumTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EnumTest',
    interfaces: []
  };
  function FormatTest() {
  }
  FormatTest.prototype.email_format = function () {
    var schema = '{"format":"email"}';
    var all_string_formats_ignore_integers = '12';
    run_test(schema, all_string_formats_ignore_integers, true, 'DRAFT_4');
    var all_string_formats_ignore_floats = '13.7';
    run_test(schema, all_string_formats_ignore_floats, true, 'DRAFT_4');
    var all_string_formats_ignore_objects = '{}';
    run_test(schema, all_string_formats_ignore_objects, true, 'DRAFT_4');
    var all_string_formats_ignore_arrays = '[]';
    run_test(schema, all_string_formats_ignore_arrays, true, 'DRAFT_4');
    var all_string_formats_ignore_booleans = 'false';
    run_test(schema, all_string_formats_ignore_booleans, true, 'DRAFT_4');
    var all_string_formats_ignore_nulls = 'null';
    run_test(schema, all_string_formats_ignore_nulls, true, 'DRAFT_4');
  };
  FormatTest.prototype.ipv4_format = function () {
    var schema = '{"format":"ipv4"}';
    var all_string_formats_ignore_integers = '12';
    run_test(schema, all_string_formats_ignore_integers, true, 'DRAFT_4');
    var all_string_formats_ignore_floats = '13.7';
    run_test(schema, all_string_formats_ignore_floats, true, 'DRAFT_4');
    var all_string_formats_ignore_objects = '{}';
    run_test(schema, all_string_formats_ignore_objects, true, 'DRAFT_4');
    var all_string_formats_ignore_arrays = '[]';
    run_test(schema, all_string_formats_ignore_arrays, true, 'DRAFT_4');
    var all_string_formats_ignore_booleans = 'false';
    run_test(schema, all_string_formats_ignore_booleans, true, 'DRAFT_4');
    var all_string_formats_ignore_nulls = 'null';
    run_test(schema, all_string_formats_ignore_nulls, true, 'DRAFT_4');
  };
  FormatTest.prototype.ipv6_format = function () {
    var schema = '{"format":"ipv6"}';
    var all_string_formats_ignore_integers = '12';
    run_test(schema, all_string_formats_ignore_integers, true, 'DRAFT_4');
    var all_string_formats_ignore_floats = '13.7';
    run_test(schema, all_string_formats_ignore_floats, true, 'DRAFT_4');
    var all_string_formats_ignore_objects = '{}';
    run_test(schema, all_string_formats_ignore_objects, true, 'DRAFT_4');
    var all_string_formats_ignore_arrays = '[]';
    run_test(schema, all_string_formats_ignore_arrays, true, 'DRAFT_4');
    var all_string_formats_ignore_booleans = 'false';
    run_test(schema, all_string_formats_ignore_booleans, true, 'DRAFT_4');
    var all_string_formats_ignore_nulls = 'null';
    run_test(schema, all_string_formats_ignore_nulls, true, 'DRAFT_4');
  };
  FormatTest.prototype.hostname_format = function () {
    var schema = '{"format":"hostname"}';
    var all_string_formats_ignore_integers = '12';
    run_test(schema, all_string_formats_ignore_integers, true, 'DRAFT_4');
    var all_string_formats_ignore_floats = '13.7';
    run_test(schema, all_string_formats_ignore_floats, true, 'DRAFT_4');
    var all_string_formats_ignore_objects = '{}';
    run_test(schema, all_string_formats_ignore_objects, true, 'DRAFT_4');
    var all_string_formats_ignore_arrays = '[]';
    run_test(schema, all_string_formats_ignore_arrays, true, 'DRAFT_4');
    var all_string_formats_ignore_booleans = 'false';
    run_test(schema, all_string_formats_ignore_booleans, true, 'DRAFT_4');
    var all_string_formats_ignore_nulls = 'null';
    run_test(schema, all_string_formats_ignore_nulls, true, 'DRAFT_4');
  };
  FormatTest.prototype.date_time_format = function () {
    var schema = '{"format":"date-time"}';
    var all_string_formats_ignore_integers = '12';
    run_test(schema, all_string_formats_ignore_integers, true, 'DRAFT_4');
    var all_string_formats_ignore_floats = '13.7';
    run_test(schema, all_string_formats_ignore_floats, true, 'DRAFT_4');
    var all_string_formats_ignore_objects = '{}';
    run_test(schema, all_string_formats_ignore_objects, true, 'DRAFT_4');
    var all_string_formats_ignore_arrays = '[]';
    run_test(schema, all_string_formats_ignore_arrays, true, 'DRAFT_4');
    var all_string_formats_ignore_booleans = 'false';
    run_test(schema, all_string_formats_ignore_booleans, true, 'DRAFT_4');
    var all_string_formats_ignore_nulls = 'null';
    run_test(schema, all_string_formats_ignore_nulls, true, 'DRAFT_4');
  };
  FormatTest.prototype.uri_format = function () {
    var schema = '{"format":"uri"}';
    var all_string_formats_ignore_integers = '12';
    run_test(schema, all_string_formats_ignore_integers, true, 'DRAFT_4');
    var all_string_formats_ignore_floats = '13.7';
    run_test(schema, all_string_formats_ignore_floats, true, 'DRAFT_4');
    var all_string_formats_ignore_objects = '{}';
    run_test(schema, all_string_formats_ignore_objects, true, 'DRAFT_4');
    var all_string_formats_ignore_arrays = '[]';
    run_test(schema, all_string_formats_ignore_arrays, true, 'DRAFT_4');
    var all_string_formats_ignore_booleans = 'false';
    run_test(schema, all_string_formats_ignore_booleans, true, 'DRAFT_4');
    var all_string_formats_ignore_nulls = 'null';
    run_test(schema, all_string_formats_ignore_nulls, true, 'DRAFT_4');
  };
  FormatTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FormatTest',
    interfaces: []
  };
  function ItemsTest() {
  }
  ItemsTest.prototype.a_schema_given_for_items = function () {
    var schema = '{"items":{"type":"integer"}}';
    var valid_items = '[1,2,3]';
    run_test(schema, valid_items, true, 'DRAFT_4');
    var wrong_type_of_items = '[1,"x"]';
    run_test(schema, wrong_type_of_items, false, 'DRAFT_4');
    var ignores_non_arrays = '{"foo":"bar"}';
    run_test(schema, ignores_non_arrays, true, 'DRAFT_4');
    var JavaScript_pseudo_array_is_valid = '{"0":"invalid","length":1}';
    run_test(schema, JavaScript_pseudo_array_is_valid, true, 'DRAFT_4');
  };
  ItemsTest.prototype.an_array_of_schemas_for_items = function () {
    var schema = '{"items":[{"type":"integer"},{"type":"string"}]}';
    var correct_types = '[1,"foo"]';
    run_test(schema, correct_types, true, 'DRAFT_4');
    var wrong_types = '["foo",1]';
    run_test(schema, wrong_types, false, 'DRAFT_4');
    var incomplete_array_of_items = '[1]';
    run_test(schema, incomplete_array_of_items, true, 'DRAFT_4');
    var array_with_additional_items = '[1,"foo",true]';
    run_test(schema, array_with_additional_items, true, 'DRAFT_4');
    var empty_array = '[]';
    run_test(schema, empty_array, true, 'DRAFT_4');
    var JavaScript_pseudo_array_is_valid = '{"0":"invalid","1":"valid","length":2}';
    run_test(schema, JavaScript_pseudo_array_is_valid, true, 'DRAFT_4');
  };
  ItemsTest.prototype.items_and_subitems = function () {
    var schema = '{"definitions":{"item":{"type":"array","additionalItems":false,"items":[{"$ref":"#/definitions/sub-item"},{"$ref":"#/definitions/sub-item"}]},"sub-item":{"type":"object","required":["foo"]}},"type":"array","additionalItems":false,"items":[{"$ref":"#/definitions/item"},{"$ref":"#/definitions/item"},{"$ref":"#/definitions/item"}]}';
    var valid_items = '[[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}]]';
    run_test(schema, valid_items, true, 'DRAFT_4');
    var too_many_items = '[[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}]]';
    run_test(schema, too_many_items, false, 'DRAFT_4');
    var too_many_sub_items = '[[{"foo":null},{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}]]';
    run_test(schema, too_many_sub_items, false, 'DRAFT_4');
    var wrong_item = '[{"foo":null},[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}]]';
    run_test(schema, wrong_item, false, 'DRAFT_4');
    var wrong_sub_item = '[[{},{"foo":null}],[{"foo":null},{"foo":null}],[{"foo":null},{"foo":null}]]';
    run_test(schema, wrong_sub_item, false, 'DRAFT_4');
    var fewer_items_is_valid = '[[{"foo":null}],[{"foo":null}]]';
    run_test(schema, fewer_items_is_valid, true, 'DRAFT_4');
  };
  ItemsTest.prototype.nested_items = function () {
    var schema = '{"type":"array","items":{"type":"array","items":{"type":"array","items":{"type":"array","items":{"type":"number"}}}}}';
    var valid_nested_array = '[[[[1]],[[2],[3]]],[[[4],[5],[6]]]]';
    run_test(schema, valid_nested_array, true, 'DRAFT_4');
    var nested_array_with_invalid_type = '[[[["1"]],[[2],[3]]],[[[4],[5],[6]]]]';
    run_test(schema, nested_array_with_invalid_type, false, 'DRAFT_4');
    var not_deep_enough = '[[[1],[2],[3]],[[4],[5],[6]]]';
    run_test(schema, not_deep_enough, false, 'DRAFT_4');
  };
  ItemsTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ItemsTest',
    interfaces: []
  };
  function MaxItemsTest() {
  }
  MaxItemsTest.prototype.maxItems_validation = function () {
    var schema = '{"maxItems":2}';
    var shorter_is_valid = '[1]';
    run_test(schema, shorter_is_valid, true, 'DRAFT_4');
    var exact_length_is_valid = '[1,2]';
    run_test(schema, exact_length_is_valid, true, 'DRAFT_4');
    var too_long_is_invalid = '[1,2,3]';
    run_test(schema, too_long_is_invalid, false, 'DRAFT_4');
    var ignores_non_arrays = '"foobar"';
    run_test(schema, ignores_non_arrays, true, 'DRAFT_4');
  };
  MaxItemsTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaxItemsTest',
    interfaces: []
  };
  function MaxLengthTest() {
  }
  MaxLengthTest.prototype.maxLength_validation = function () {
    var schema = '{"maxLength":2}';
    var shorter_is_valid = '"f"';
    run_test(schema, shorter_is_valid, true, 'DRAFT_4');
    var exact_length_is_valid = '"fo"';
    run_test(schema, exact_length_is_valid, true, 'DRAFT_4');
    var too_long_is_invalid = '"foo"';
    run_test(schema, too_long_is_invalid, false, 'DRAFT_4');
    var ignores_non_strings = '100';
    run_test(schema, ignores_non_strings, true, 'DRAFT_4');
  };
  MaxLengthTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaxLengthTest',
    interfaces: []
  };
  function MaxPropertiesTest() {
  }
  MaxPropertiesTest.prototype.maxProperties_validation = function () {
    var schema = '{"maxProperties":2}';
    var shorter_is_valid = '{"foo":1}';
    run_test(schema, shorter_is_valid, true, 'DRAFT_4');
    var exact_length_is_valid = '{"foo":1,"bar":2}';
    run_test(schema, exact_length_is_valid, true, 'DRAFT_4');
    var too_long_is_invalid = '{"foo":1,"bar":2,"baz":3}';
    run_test(schema, too_long_is_invalid, false, 'DRAFT_4');
    var ignores_arrays = '[1,2,3]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_strings = '"foobar"';
    run_test(schema, ignores_strings, true, 'DRAFT_4');
    var ignores_other_non_objects = '12';
    run_test(schema, ignores_other_non_objects, true, 'DRAFT_4');
  };
  MaxPropertiesTest.prototype.maxProperties___0_means_the_object_is_empty = function () {
    var schema = '{"maxProperties":0}';
    var no_properties_is_valid = '{}';
    run_test(schema, no_properties_is_valid, true, 'DRAFT_4');
    var one_property_is_invalid = '{"foo":1}';
    run_test(schema, one_property_is_invalid, false, 'DRAFT_4');
  };
  MaxPropertiesTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaxPropertiesTest',
    interfaces: []
  };
  function MaximumTest() {
  }
  MaximumTest.prototype.maximum_validation = function () {
    var schema = '{"maximum":3.0}';
    var below_the_maximum_is_valid = '2.6';
    run_test(schema, below_the_maximum_is_valid, true, 'DRAFT_4');
    var boundary_point_is_valid = '3.0';
    run_test(schema, boundary_point_is_valid, true, 'DRAFT_4');
    var above_the_maximum_is_invalid = '3.5';
    run_test(schema, above_the_maximum_is_invalid, false, 'DRAFT_4');
    var ignores_non_numbers = '"x"';
    run_test(schema, ignores_non_numbers, true, 'DRAFT_4');
  };
  MaximumTest.prototype.maximum_validation_with_unsigned_integer = function () {
    var schema = '{"maximum":300}';
    var below_the_maximum_is_invalid = '299.97';
    run_test(schema, below_the_maximum_is_invalid, true, 'DRAFT_4');
    var boundary_point_integer_is_valid = '300';
    run_test(schema, boundary_point_integer_is_valid, true, 'DRAFT_4');
    var boundary_point_float_is_valid = '300.00';
    run_test(schema, boundary_point_float_is_valid, true, 'DRAFT_4');
    var above_the_maximum_is_invalid = '300.5';
    run_test(schema, above_the_maximum_is_invalid, false, 'DRAFT_4');
  };
  MaximumTest.prototype.maximum_validation_explicit_false_exclusivity = function () {
    var schema = '{"maximum":3.0,"exclusiveMaximum":false}';
    var below_the_maximum_is_valid = '2.6';
    run_test(schema, below_the_maximum_is_valid, true, 'DRAFT_4');
    var boundary_point_is_valid = '3.0';
    run_test(schema, boundary_point_is_valid, true, 'DRAFT_4');
    var above_the_maximum_is_invalid = '3.5';
    run_test(schema, above_the_maximum_is_invalid, false, 'DRAFT_4');
    var ignores_non_numbers = '"x"';
    run_test(schema, ignores_non_numbers, true, 'DRAFT_4');
  };
  MaximumTest.prototype.exclusiveMaximum_validation = function () {
    var schema = '{"maximum":3.0,"exclusiveMaximum":true}';
    var below_the_maximum_is_still_valid = '2.2';
    run_test(schema, below_the_maximum_is_still_valid, true, 'DRAFT_4');
    var boundary_point_is_invalid = '3.0';
    run_test(schema, boundary_point_is_invalid, false, 'DRAFT_4');
  };
  MaximumTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaximumTest',
    interfaces: []
  };
  function MinItemsTest() {
  }
  MinItemsTest.prototype.minItems_validation = function () {
    var schema = '{"minItems":1}';
    var longer_is_valid = '[1,2]';
    run_test(schema, longer_is_valid, true, 'DRAFT_4');
    var exact_length_is_valid = '[1]';
    run_test(schema, exact_length_is_valid, true, 'DRAFT_4');
    var too_short_is_invalid = '[]';
    run_test(schema, too_short_is_invalid, false, 'DRAFT_4');
    var ignores_non_arrays = '""';
    run_test(schema, ignores_non_arrays, true, 'DRAFT_4');
  };
  MinItemsTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinItemsTest',
    interfaces: []
  };
  function MinLengthTest() {
  }
  MinLengthTest.prototype.minLength_validation = function () {
    var schema = '{"minLength":2}';
    var longer_is_valid = '"foo"';
    run_test(schema, longer_is_valid, true, 'DRAFT_4');
    var exact_length_is_valid = '"fo"';
    run_test(schema, exact_length_is_valid, true, 'DRAFT_4');
    var too_short_is_invalid = '"f"';
    run_test(schema, too_short_is_invalid, false, 'DRAFT_4');
    var ignores_non_strings = '1';
    run_test(schema, ignores_non_strings, true, 'DRAFT_4');
  };
  MinLengthTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinLengthTest',
    interfaces: []
  };
  function MinPropertiesTest() {
  }
  MinPropertiesTest.prototype.minProperties_validation = function () {
    var schema = '{"minProperties":1}';
    var longer_is_valid = '{"foo":1,"bar":2}';
    run_test(schema, longer_is_valid, true, 'DRAFT_4');
    var exact_length_is_valid = '{"foo":1}';
    run_test(schema, exact_length_is_valid, true, 'DRAFT_4');
    var too_short_is_invalid = '{}';
    run_test(schema, too_short_is_invalid, false, 'DRAFT_4');
    var ignores_arrays = '[]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_strings = '""';
    run_test(schema, ignores_strings, true, 'DRAFT_4');
    var ignores_other_non_objects = '12';
    run_test(schema, ignores_other_non_objects, true, 'DRAFT_4');
  };
  MinPropertiesTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinPropertiesTest',
    interfaces: []
  };
  function MinimumTest() {
  }
  MinimumTest.prototype.minimum_validation = function () {
    var schema = '{"minimum":1.1}';
    var above_the_minimum_is_valid = '2.6';
    run_test(schema, above_the_minimum_is_valid, true, 'DRAFT_4');
    var boundary_point_is_valid = '1.1';
    run_test(schema, boundary_point_is_valid, true, 'DRAFT_4');
    var below_the_minimum_is_invalid = '0.6';
    run_test(schema, below_the_minimum_is_invalid, false, 'DRAFT_4');
    var ignores_non_numbers = '"x"';
    run_test(schema, ignores_non_numbers, true, 'DRAFT_4');
  };
  MinimumTest.prototype.minimum_validation_explicit_false_exclusivity = function () {
    var schema = '{"minimum":1.1,"exclusiveMinimum":false}';
    var above_the_minimum_is_valid = '2.6';
    run_test(schema, above_the_minimum_is_valid, true, 'DRAFT_4');
    var boundary_point_is_valid = '1.1';
    run_test(schema, boundary_point_is_valid, true, 'DRAFT_4');
    var below_the_minimum_is_invalid = '0.6';
    run_test(schema, below_the_minimum_is_invalid, false, 'DRAFT_4');
    var ignores_non_numbers = '"x"';
    run_test(schema, ignores_non_numbers, true, 'DRAFT_4');
  };
  MinimumTest.prototype.exclusiveMinimum_validation = function () {
    var schema = '{"minimum":1.1,"exclusiveMinimum":true}';
    var above_the_minimum_is_still_valid = '1.2';
    run_test(schema, above_the_minimum_is_still_valid, true, 'DRAFT_4');
    var boundary_point_is_invalid = '1.1';
    run_test(schema, boundary_point_is_invalid, false, 'DRAFT_4');
  };
  MinimumTest.prototype.minimum_validation_with_signed_integer = function () {
    var schema = '{"minimum":-2}';
    var negative_above_the_minimum_is_valid = '-1';
    run_test(schema, negative_above_the_minimum_is_valid, true, 'DRAFT_4');
    var positive_above_the_minimum_is_valid = '0';
    run_test(schema, positive_above_the_minimum_is_valid, true, 'DRAFT_4');
    var boundary_point_is_valid = '-2';
    run_test(schema, boundary_point_is_valid, true, 'DRAFT_4');
    var boundary_point_with_float_is_valid = '-2.0';
    run_test(schema, boundary_point_with_float_is_valid, true, 'DRAFT_4');
    var float_below_the_minimum_is_invalid = '-2.0001';
    run_test(schema, float_below_the_minimum_is_invalid, false, 'DRAFT_4');
    var int_below_the_minimum_is_invalid = '-3';
    run_test(schema, int_below_the_minimum_is_invalid, false, 'DRAFT_4');
    var ignores_non_numbers = '"x"';
    run_test(schema, ignores_non_numbers, true, 'DRAFT_4');
  };
  MinimumTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinimumTest',
    interfaces: []
  };
  function MultipleOfTest() {
  }
  MultipleOfTest.prototype.by_int = function () {
    var schema = '{"multipleOf":2}';
    var int_by_int = '10';
    run_test(schema, int_by_int, true, 'DRAFT_4');
    var int_by_int_fail = '7';
    run_test(schema, int_by_int_fail, false, 'DRAFT_4');
    var ignores_non_numbers = '"foo"';
    run_test(schema, ignores_non_numbers, true, 'DRAFT_4');
  };
  MultipleOfTest.prototype.by_number = function () {
    var schema = '{"multipleOf":1.5}';
    var zero_is_multiple_of_anything = '0';
    run_test(schema, zero_is_multiple_of_anything, true, 'DRAFT_4');
    var __5_is_multiple_of_1_5 = '4.5';
    run_test(schema, __5_is_multiple_of_1_5, true, 'DRAFT_4');
    var _5_is_not_multiple_of_1_5 = '35';
    run_test(schema, _5_is_not_multiple_of_1_5, false, 'DRAFT_4');
  };
  MultipleOfTest.prototype.by_small_number = function () {
    var schema = '{"multipleOf":0.0001}';
    var __0075_is_multiple_of_0_0001 = '0.0075';
    run_test(schema, __0075_is_multiple_of_0_0001, true, 'DRAFT_4');
    var __00751_is_not_multiple_of_0_0001 = '0.00751';
    run_test(schema, __00751_is_not_multiple_of_0_0001, false, 'DRAFT_4');
  };
  MultipleOfTest.prototype.invalid_instance_should_not_raise_error_when_float_division___inf = function () {
    var schema = '{"type":"integer","multipleOf":0.123456789}';
    var always_invalid__but_naive_implementations_may_raise_an_overflow_error = '1e308';
    run_test(schema, always_invalid__but_naive_implementations_may_raise_an_overflow_error, false, 'DRAFT_4');
  };
  MultipleOfTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MultipleOfTest',
    interfaces: []
  };
  function NotTest() {
  }
  NotTest.prototype.not = function () {
    var schema = '{"not":{"type":"integer"}}';
    var allowed = '"foo"';
    run_test(schema, allowed, true, 'DRAFT_4');
    var disallowed = '1';
    run_test(schema, disallowed, false, 'DRAFT_4');
  };
  NotTest.prototype.not_multiple_types = function () {
    var schema = '{"not":{"type":["integer","boolean"]}}';
    var valid = '"foo"';
    run_test(schema, valid, true, 'DRAFT_4');
    var mismatch = '1';
    run_test(schema, mismatch, false, 'DRAFT_4');
    var other_mismatch = 'true';
    run_test(schema, other_mismatch, false, 'DRAFT_4');
  };
  NotTest.prototype.not_more_complex_schema = function () {
    var schema = '{"not":{"type":"object","properties":{"foo":{"type":"string"}}}}';
    var match = '1';
    run_test(schema, match, true, 'DRAFT_4');
    var other_match = '{"foo":1}';
    run_test(schema, other_match, true, 'DRAFT_4');
    var mismatch = '{"foo":"bar"}';
    run_test(schema, mismatch, false, 'DRAFT_4');
  };
  NotTest.prototype.forbidden_property = function () {
    var schema = '{"properties":{"foo":{"not":{}}}}';
    var property_present = '{"foo":1,"bar":2}';
    run_test(schema, property_present, false, 'DRAFT_4');
    var property_absent = '{"bar":1,"baz":2}';
    run_test(schema, property_absent, true, 'DRAFT_4');
  };
  NotTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotTest',
    interfaces: []
  };
  function OneOfTest() {
  }
  OneOfTest.prototype.oneOf = function () {
    var schema = '{"oneOf":[{"type":"integer"},{"minimum":2}]}';
    var first_oneOf_valid = '1';
    run_test(schema, first_oneOf_valid, true, 'DRAFT_4');
    var second_oneOf_valid = '2.5';
    run_test(schema, second_oneOf_valid, true, 'DRAFT_4');
    var both_oneOf_valid = '3';
    run_test(schema, both_oneOf_valid, false, 'DRAFT_4');
    var neither_oneOf_valid = '1.5';
    run_test(schema, neither_oneOf_valid, false, 'DRAFT_4');
  };
  OneOfTest.prototype.oneOf_with_base_schema = function () {
    var schema = '{"type":"string","oneOf":[{"minLength":2},{"maxLength":4}]}';
    var mismatch_base_schema = '3';
    run_test(schema, mismatch_base_schema, false, 'DRAFT_4');
    var one_oneOf_valid = '"foobar"';
    run_test(schema, one_oneOf_valid, true, 'DRAFT_4');
    var both_oneOf_valid = '"foo"';
    run_test(schema, both_oneOf_valid, false, 'DRAFT_4');
  };
  OneOfTest.prototype.oneOf_complex_types = function () {
    var schema = '{"oneOf":[{"properties":{"bar":{"type":"integer"}},"required":["bar"]},{"properties":{"foo":{"type":"string"}},"required":["foo"]}]}';
    var first_oneOf_valid_complex = '{"bar":2}';
    run_test(schema, first_oneOf_valid_complex, true, 'DRAFT_4');
    var second_oneOf_valid_complex = '{"foo":"baz"}';
    run_test(schema, second_oneOf_valid_complex, true, 'DRAFT_4');
    var both_oneOf_valid_complex = '{"foo":"baz","bar":2}';
    run_test(schema, both_oneOf_valid_complex, false, 'DRAFT_4');
    var neither_oneOf_valid_complex = '{"foo":2,"bar":"quux"}';
    run_test(schema, neither_oneOf_valid_complex, false, 'DRAFT_4');
  };
  OneOfTest.prototype.oneOf_with_empty_schema = function () {
    var schema = '{"oneOf":[{"type":"number"},{}]}';
    var one_valid___valid = '"foo"';
    run_test(schema, one_valid___valid, true, 'DRAFT_4');
    var both_valid___invalid = '123';
    run_test(schema, both_valid___invalid, false, 'DRAFT_4');
  };
  OneOfTest.prototype.oneOf_with_required = function () {
    var schema = '{"type":"object","oneOf":[{"required":["foo","bar"]},{"required":["foo","baz"]}]}';
    var both_invalid___invalid = '{"bar":2}';
    run_test(schema, both_invalid___invalid, false, 'DRAFT_4');
    var first_valid___valid = '{"foo":1,"bar":2}';
    run_test(schema, first_valid___valid, true, 'DRAFT_4');
    var second_valid___valid = '{"foo":1,"baz":3}';
    run_test(schema, second_valid___valid, true, 'DRAFT_4');
    var both_valid___invalid = '{"foo":1,"bar":2,"baz":3}';
    run_test(schema, both_valid___invalid, false, 'DRAFT_4');
  };
  OneOfTest.prototype.oneOf_with_missing_optional_property = function () {
    var schema = '{"oneOf":[{"properties":{"bar":{},"baz":{}},"required":["bar"]},{"properties":{"foo":{}},"required":["foo"]}]}';
    var first_oneOf_valid = '{"bar":8}';
    run_test(schema, first_oneOf_valid, true, 'DRAFT_4');
    var second_oneOf_valid = '{"foo":"foo"}';
    run_test(schema, second_oneOf_valid, true, 'DRAFT_4');
    var both_oneOf_valid = '{"foo":"foo","bar":8}';
    run_test(schema, both_oneOf_valid, false, 'DRAFT_4');
    var neither_oneOf_valid = '{"baz":"quux"}';
    run_test(schema, neither_oneOf_valid, false, 'DRAFT_4');
  };
  OneOfTest.prototype.nested_oneOf__to_check_validation_semantics = function () {
    var schema = '{"oneOf":[{"oneOf":[{"type":"null"}]}]}';
    var null_is_valid = 'null';
    run_test(schema, null_is_valid, true, 'DRAFT_4');
    var anything_non_null_is_invalid = '123';
    run_test(schema, anything_non_null_is_invalid, false, 'DRAFT_4');
  };
  OneOfTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OneOfTest',
    interfaces: []
  };
  function PatternPropertiesTest() {
  }
  PatternPropertiesTest.prototype.patternProperties_validates_properties_matching_a_regex = function () {
    var schema = '{"patternProperties":{"f.*o":{"type":"integer"}}}';
    var a_single_valid_match_is_valid = '{"foo":1}';
    run_test(schema, a_single_valid_match_is_valid, true, 'DRAFT_4');
    var multiple_valid_matches_is_valid = '{"foo":1,"foooooo":2}';
    run_test(schema, multiple_valid_matches_is_valid, true, 'DRAFT_4');
    var a_single_invalid_match_is_invalid = '{"foo":"bar","fooooo":2}';
    run_test(schema, a_single_invalid_match_is_invalid, false, 'DRAFT_4');
    var multiple_invalid_matches_is_invalid = '{"foo":"bar","foooooo":"baz"}';
    run_test(schema, multiple_invalid_matches_is_invalid, false, 'DRAFT_4');
    var ignores_arrays = '[]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_strings = '""';
    run_test(schema, ignores_strings, true, 'DRAFT_4');
    var ignores_other_non_objects = '12';
    run_test(schema, ignores_other_non_objects, true, 'DRAFT_4');
  };
  PatternPropertiesTest.prototype.multiple_simultaneous_patternProperties_are_validated = function () {
    var schema = '{"patternProperties":{"a*":{"type":"integer"},"aaa*":{"maximum":20}}}';
    var a_single_valid_match_is_valid = '{"a":21}';
    run_test(schema, a_single_valid_match_is_valid, true, 'DRAFT_4');
    var a_simultaneous_match_is_valid = '{"aaaa":18}';
    run_test(schema, a_simultaneous_match_is_valid, true, 'DRAFT_4');
    var multiple_matches_is_valid = '{"a":21,"aaaa":18}';
    run_test(schema, multiple_matches_is_valid, true, 'DRAFT_4');
    var an_invalid_due_to_one_is_invalid = '{"a":"bar"}';
    run_test(schema, an_invalid_due_to_one_is_invalid, false, 'DRAFT_4');
    var an_invalid_due_to_the_other_is_invalid = '{"aaaa":31}';
    run_test(schema, an_invalid_due_to_the_other_is_invalid, false, 'DRAFT_4');
    var an_invalid_due_to_both_is_invalid = '{"aaa":"foo","aaaa":31}';
    run_test(schema, an_invalid_due_to_both_is_invalid, false, 'DRAFT_4');
  };
  PatternPropertiesTest.prototype.regexes_are_not_anchored_by_default_and_are_case_sensitive = function () {
    var schema = '{"patternProperties":{"[0-9]{2,}":{"type":"boolean"},"X_":{"type":"string"}}}';
    var non_recognized_members_are_ignored = '{"answer 1":"42"}';
    run_test(schema, non_recognized_members_are_ignored, true, 'DRAFT_4');
    var recognized_members_are_accounted_for = '{"a31b":null}';
    run_test(schema, recognized_members_are_accounted_for, false, 'DRAFT_4');
    var regexes_are_case_sensitive = '{"a_x_3":3}';
    run_test(schema, regexes_are_case_sensitive, true, 'DRAFT_4');
    var regexes_are_case_sensitive__2 = '{"a_X_3":3}';
    run_test(schema, regexes_are_case_sensitive__2, false, 'DRAFT_4');
  };
  PatternPropertiesTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PatternPropertiesTest',
    interfaces: []
  };
  function PatternTest() {
  }
  PatternTest.prototype.pattern_validation = function () {
    var schema = '{"pattern":"^a*$"}';
    var a_matching_pattern_is_valid = '"aaa"';
    run_test(schema, a_matching_pattern_is_valid, true, 'DRAFT_4');
    var a_non_matching_pattern_is_invalid = '"abc"';
    run_test(schema, a_non_matching_pattern_is_invalid, false, 'DRAFT_4');
    var ignores_booleans = 'true';
    run_test(schema, ignores_booleans, true, 'DRAFT_4');
    var ignores_integers = '123';
    run_test(schema, ignores_integers, true, 'DRAFT_4');
    var ignores_floats = '1.0';
    run_test(schema, ignores_floats, true, 'DRAFT_4');
    var ignores_objects = '{}';
    run_test(schema, ignores_objects, true, 'DRAFT_4');
    var ignores_arrays = '[]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_null = 'null';
    run_test(schema, ignores_null, true, 'DRAFT_4');
  };
  PatternTest.prototype.pattern_is_not_anchored = function () {
    var schema = '{"pattern":"a+"}';
    var matches_a_substring = '"xxaayy"';
    run_test(schema, matches_a_substring, true, 'DRAFT_4');
  };
  PatternTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PatternTest',
    interfaces: []
  };
  function PropertiesTest() {
  }
  PropertiesTest.prototype.object_properties_validation = function () {
    var schema = '{"properties":{"foo":{"type":"integer"},"bar":{"type":"string"}}}';
    var both_properties_present_and_valid_is_valid = '{"foo":1,"bar":"baz"}';
    run_test(schema, both_properties_present_and_valid_is_valid, true, 'DRAFT_4');
    var one_property_invalid_is_invalid = '{"foo":1,"bar":{}}';
    run_test(schema, one_property_invalid_is_invalid, false, 'DRAFT_4');
    var both_properties_invalid_is_invalid = '{"foo":[],"bar":{}}';
    run_test(schema, both_properties_invalid_is_invalid, false, 'DRAFT_4');
    var doesn_t_invalidate_other_properties = '{"quux":[]}';
    run_test(schema, doesn_t_invalidate_other_properties, true, 'DRAFT_4');
    var ignores_arrays = '[]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_other_non_objects = '12';
    run_test(schema, ignores_other_non_objects, true, 'DRAFT_4');
  };
  PropertiesTest.prototype.properties__patternProperties__additionalProperties_interaction = function () {
    var schema = '{"properties":{"foo":{"type":"array","maxItems":3},"bar":{"type":"array"}},"patternProperties":{"f.o":{"minItems":2}},"additionalProperties":{"type":"integer"}}';
    var property_validates_property = '{"foo":[1,2]}';
    run_test(schema, property_validates_property, true, 'DRAFT_4');
    var property_invalidates_property = '{"foo":[1,2,3,4]}';
    run_test(schema, property_invalidates_property, false, 'DRAFT_4');
    var patternProperty_invalidates_property = '{"foo":[]}';
    run_test(schema, patternProperty_invalidates_property, false, 'DRAFT_4');
    var patternProperty_validates_nonproperty = '{"fxo":[1,2]}';
    run_test(schema, patternProperty_validates_nonproperty, true, 'DRAFT_4');
    var patternProperty_invalidates_nonproperty = '{"fxo":[]}';
    run_test(schema, patternProperty_invalidates_nonproperty, false, 'DRAFT_4');
    var additionalProperty_ignores_property = '{"bar":[]}';
    run_test(schema, additionalProperty_ignores_property, true, 'DRAFT_4');
    var additionalProperty_validates_others = '{"quux":3}';
    run_test(schema, additionalProperty_validates_others, true, 'DRAFT_4');
    var additionalProperty_invalidates_others = '{"quux":"foo"}';
    run_test(schema, additionalProperty_invalidates_others, false, 'DRAFT_4');
  };
  PropertiesTest.prototype.properties_with_escaped_characters = function () {
    var schema = '{"properties":{"foo\\nbar":{"type":"number"},"foo\\"bar":{"type":"number"},"foo\\\\bar":{"type":"number"},"foo\\rbar":{"type":"number"},"foo\\tbar":{"type":"number"},"foo\\fbar":{"type":"number"}}}';
    var object_with_all_numbers_is_valid = '{"foo\\nbar":1,"foo\\"bar":1,"foo\\\\bar":1,"foo\\rbar":1,"foo\\tbar":1,"foo\\fbar":1}';
    run_test(schema, object_with_all_numbers_is_valid, true, 'DRAFT_4');
    var object_with_strings_is_invalid = '{"foo\\nbar":"1","foo\\"bar":"1","foo\\\\bar":"1","foo\\rbar":"1","foo\\tbar":"1","foo\\fbar":"1"}';
    run_test(schema, object_with_strings_is_invalid, false, 'DRAFT_4');
  };
  PropertiesTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PropertiesTest',
    interfaces: []
  };
  function RequiredTest() {
  }
  RequiredTest.prototype.required_validation = function () {
    var schema = '{"properties":{"foo":{},"bar":{}},"required":["foo"]}';
    var present_required_property_is_valid = '{"foo":1}';
    run_test(schema, present_required_property_is_valid, true, 'DRAFT_4');
    var non_present_required_property_is_invalid = '{"bar":1}';
    run_test(schema, non_present_required_property_is_invalid, false, 'DRAFT_4');
    var ignores_arrays = '[]';
    run_test(schema, ignores_arrays, true, 'DRAFT_4');
    var ignores_strings = '""';
    run_test(schema, ignores_strings, true, 'DRAFT_4');
    var ignores_other_non_objects = '12';
    run_test(schema, ignores_other_non_objects, true, 'DRAFT_4');
  };
  RequiredTest.prototype.required_default_validation = function () {
    var schema = '{"properties":{"foo":{}}}';
    var not_required_by_default = '{}';
    run_test(schema, not_required_by_default, true, 'DRAFT_4');
  };
  RequiredTest.prototype.required_with_escaped_characters = function () {
    var schema = '{"required":["foo\\nbar","foo\\"bar","foo\\\\bar","foo\\rbar","foo\\tbar","foo\\fbar"]}';
    var object_with_all_properties_present_is_valid = '{"foo\\nbar":1,"foo\\"bar":1,"foo\\\\bar":1,"foo\\rbar":1,"foo\\tbar":1,"foo\\fbar":1}';
    run_test(schema, object_with_all_properties_present_is_valid, true, 'DRAFT_4');
    var object_with_some_properties_missing_is_invalid = '{"foo\\nbar":"1","foo\\"bar":"1"}';
    run_test(schema, object_with_some_properties_missing_is_invalid, false, 'DRAFT_4');
  };
  RequiredTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RequiredTest',
    interfaces: []
  };
  function TypeTest() {
  }
  TypeTest.prototype.integer_type_matches_integers = function () {
    var schema = '{"type":"integer"}';
    var an_integer_is_an_integer = '1';
    run_test(schema, an_integer_is_an_integer, true, 'DRAFT_4');
    var a_float_is_not_an_integer = '1.1';
    run_test(schema, a_float_is_not_an_integer, false, 'DRAFT_4');
    var a_string_is_not_an_integer = '"foo"';
    run_test(schema, a_string_is_not_an_integer, false, 'DRAFT_4');
    var a_string_is_still_not_an_integer__even_if_it_looks_like_one = '"1"';
    run_test(schema, a_string_is_still_not_an_integer__even_if_it_looks_like_one, false, 'DRAFT_4');
    var an_object_is_not_an_integer = '{}';
    run_test(schema, an_object_is_not_an_integer, false, 'DRAFT_4');
    var an_array_is_not_an_integer = '[]';
    run_test(schema, an_array_is_not_an_integer, false, 'DRAFT_4');
    var a_boolean_is_not_an_integer = 'true';
    run_test(schema, a_boolean_is_not_an_integer, false, 'DRAFT_4');
    var null_is_not_an_integer = 'null';
    run_test(schema, null_is_not_an_integer, false, 'DRAFT_4');
  };
  TypeTest.prototype.number_type_matches_numbers = function () {
    var schema = '{"type":"number"}';
    var an_integer_is_a_number = '1';
    run_test(schema, an_integer_is_a_number, true, 'DRAFT_4');
    var a_float_with_zero_fractional_part_is_a_number = '1.0';
    run_test(schema, a_float_with_zero_fractional_part_is_a_number, true, 'DRAFT_4');
    var a_float_is_a_number = '1.1';
    run_test(schema, a_float_is_a_number, true, 'DRAFT_4');
    var a_string_is_not_a_number = '"foo"';
    run_test(schema, a_string_is_not_a_number, false, 'DRAFT_4');
    var a_string_is_still_not_a_number__even_if_it_looks_like_one = '"1"';
    run_test(schema, a_string_is_still_not_a_number__even_if_it_looks_like_one, false, 'DRAFT_4');
    var an_object_is_not_a_number = '{}';
    run_test(schema, an_object_is_not_a_number, false, 'DRAFT_4');
    var an_array_is_not_a_number = '[]';
    run_test(schema, an_array_is_not_a_number, false, 'DRAFT_4');
    var a_boolean_is_not_a_number = 'true';
    run_test(schema, a_boolean_is_not_a_number, false, 'DRAFT_4');
    var null_is_not_a_number = 'null';
    run_test(schema, null_is_not_a_number, false, 'DRAFT_4');
  };
  TypeTest.prototype.string_type_matches_strings = function () {
    var schema = '{"type":"string"}';
    var __is_not_a_string = '1';
    run_test(schema, __is_not_a_string, false, 'DRAFT_4');
    var a_float_is_not_a_string = '1.1';
    run_test(schema, a_float_is_not_a_string, false, 'DRAFT_4');
    var a_string_is_a_string = '"foo"';
    run_test(schema, a_string_is_a_string, true, 'DRAFT_4');
    var a_string_is_still_a_string__even_if_it_looks_like_a_number = '"1"';
    run_test(schema, a_string_is_still_a_string__even_if_it_looks_like_a_number, true, 'DRAFT_4');
    var an_empty_string_is_still_a_string = '""';
    run_test(schema, an_empty_string_is_still_a_string, true, 'DRAFT_4');
    var an_object_is_not_a_string = '{}';
    run_test(schema, an_object_is_not_a_string, false, 'DRAFT_4');
    var an_array_is_not_a_string = '[]';
    run_test(schema, an_array_is_not_a_string, false, 'DRAFT_4');
    var a_boolean_is_not_a_string = 'true';
    run_test(schema, a_boolean_is_not_a_string, false, 'DRAFT_4');
    var null_is_not_a_string = 'null';
    run_test(schema, null_is_not_a_string, false, 'DRAFT_4');
  };
  TypeTest.prototype.object_type_matches_objects = function () {
    var schema = '{"type":"object"}';
    var an_integer_is_not_an_object = '1';
    run_test(schema, an_integer_is_not_an_object, false, 'DRAFT_4');
    var a_float_is_not_an_object = '1.1';
    run_test(schema, a_float_is_not_an_object, false, 'DRAFT_4');
    var a_string_is_not_an_object = '"foo"';
    run_test(schema, a_string_is_not_an_object, false, 'DRAFT_4');
    var an_object_is_an_object = '{}';
    run_test(schema, an_object_is_an_object, true, 'DRAFT_4');
    var an_array_is_not_an_object = '[]';
    run_test(schema, an_array_is_not_an_object, false, 'DRAFT_4');
    var a_boolean_is_not_an_object = 'true';
    run_test(schema, a_boolean_is_not_an_object, false, 'DRAFT_4');
    var null_is_not_an_object = 'null';
    run_test(schema, null_is_not_an_object, false, 'DRAFT_4');
  };
  TypeTest.prototype.array_type_matches_arrays = function () {
    var schema = '{"type":"array"}';
    var an_integer_is_not_an_array = '1';
    run_test(schema, an_integer_is_not_an_array, false, 'DRAFT_4');
    var a_float_is_not_an_array = '1.1';
    run_test(schema, a_float_is_not_an_array, false, 'DRAFT_4');
    var a_string_is_not_an_array = '"foo"';
    run_test(schema, a_string_is_not_an_array, false, 'DRAFT_4');
    var an_object_is_not_an_array = '{}';
    run_test(schema, an_object_is_not_an_array, false, 'DRAFT_4');
    var an_array_is_an_array = '[]';
    run_test(schema, an_array_is_an_array, true, 'DRAFT_4');
    var a_boolean_is_not_an_array = 'true';
    run_test(schema, a_boolean_is_not_an_array, false, 'DRAFT_4');
    var null_is_not_an_array = 'null';
    run_test(schema, null_is_not_an_array, false, 'DRAFT_4');
  };
  TypeTest.prototype.boolean_type_matches_booleans = function () {
    var schema = '{"type":"boolean"}';
    var an_integer_is_not_a_boolean = '1';
    run_test(schema, an_integer_is_not_a_boolean, false, 'DRAFT_4');
    var zero_is_not_a_boolean = '0';
    run_test(schema, zero_is_not_a_boolean, false, 'DRAFT_4');
    var a_float_is_not_a_boolean = '1.1';
    run_test(schema, a_float_is_not_a_boolean, false, 'DRAFT_4');
    var a_string_is_not_a_boolean = '"foo"';
    run_test(schema, a_string_is_not_a_boolean, false, 'DRAFT_4');
    var an_empty_string_is_not_a_boolean = '""';
    run_test(schema, an_empty_string_is_not_a_boolean, false, 'DRAFT_4');
    var an_object_is_not_a_boolean = '{}';
    run_test(schema, an_object_is_not_a_boolean, false, 'DRAFT_4');
    var an_array_is_not_a_boolean = '[]';
    run_test(schema, an_array_is_not_a_boolean, false, 'DRAFT_4');
    var true_is_a_boolean = 'true';
    run_test(schema, true_is_a_boolean, true, 'DRAFT_4');
    var false_is_a_boolean = 'false';
    run_test(schema, false_is_a_boolean, true, 'DRAFT_4');
    var null_is_not_a_boolean = 'null';
    run_test(schema, null_is_not_a_boolean, false, 'DRAFT_4');
  };
  TypeTest.prototype.null_type_matches_only_the_null_object = function () {
    var schema = '{"type":"null"}';
    var an_integer_is_not_null = '1';
    run_test(schema, an_integer_is_not_null, false, 'DRAFT_4');
    var a_float_is_not_null = '1.1';
    run_test(schema, a_float_is_not_null, false, 'DRAFT_4');
    var zero_is_not_null = '0';
    run_test(schema, zero_is_not_null, false, 'DRAFT_4');
    var a_string_is_not_null = '"foo"';
    run_test(schema, a_string_is_not_null, false, 'DRAFT_4');
    var an_empty_string_is_not_null = '""';
    run_test(schema, an_empty_string_is_not_null, false, 'DRAFT_4');
    var an_object_is_not_null = '{}';
    run_test(schema, an_object_is_not_null, false, 'DRAFT_4');
    var an_array_is_not_null = '[]';
    run_test(schema, an_array_is_not_null, false, 'DRAFT_4');
    var true_is_not_null = 'true';
    run_test(schema, true_is_not_null, false, 'DRAFT_4');
    var false_is_not_null = 'false';
    run_test(schema, false_is_not_null, false, 'DRAFT_4');
    var null_is_null = 'null';
    run_test(schema, null_is_null, true, 'DRAFT_4');
  };
  TypeTest.prototype.multiple_types_can_be_specified_in_an_array = function () {
    var schema = '{"type":["integer","string"]}';
    var an_integer_is_valid = '1';
    run_test(schema, an_integer_is_valid, true, 'DRAFT_4');
    var a_string_is_valid = '"foo"';
    run_test(schema, a_string_is_valid, true, 'DRAFT_4');
    var a_float_is_invalid = '1.1';
    run_test(schema, a_float_is_invalid, false, 'DRAFT_4');
    var an_object_is_invalid = '{}';
    run_test(schema, an_object_is_invalid, false, 'DRAFT_4');
    var an_array_is_invalid = '[]';
    run_test(schema, an_array_is_invalid, false, 'DRAFT_4');
    var a_boolean_is_invalid = 'true';
    run_test(schema, a_boolean_is_invalid, false, 'DRAFT_4');
    var null_is_invalid = 'null';
    run_test(schema, null_is_invalid, false, 'DRAFT_4');
  };
  TypeTest.prototype.type_as_array_with_one_item = function () {
    var schema = '{"type":["string"]}';
    var string_is_valid = '"foo"';
    run_test(schema, string_is_valid, true, 'DRAFT_4');
    var number_is_invalid = '123';
    run_test(schema, number_is_invalid, false, 'DRAFT_4');
  };
  TypeTest.prototype.type__array_or_object = function () {
    var schema = '{"type":["array","object"]}';
    var array_is_valid = '[1,2,3]';
    run_test(schema, array_is_valid, true, 'DRAFT_4');
    var object_is_valid = '{"foo":123}';
    run_test(schema, object_is_valid, true, 'DRAFT_4');
    var number_is_invalid = '123';
    run_test(schema, number_is_invalid, false, 'DRAFT_4');
    var string_is_invalid = '"foo"';
    run_test(schema, string_is_invalid, false, 'DRAFT_4');
    var null_is_invalid = 'null';
    run_test(schema, null_is_invalid, false, 'DRAFT_4');
  };
  TypeTest.prototype.type__array__object_or_null = function () {
    var schema = '{"type":["array","object","null"]}';
    var array_is_valid = '[1,2,3]';
    run_test(schema, array_is_valid, true, 'DRAFT_4');
    var object_is_valid = '{"foo":123}';
    run_test(schema, object_is_valid, true, 'DRAFT_4');
    var null_is_valid = 'null';
    run_test(schema, null_is_valid, true, 'DRAFT_4');
    var number_is_invalid = '123';
    run_test(schema, number_is_invalid, false, 'DRAFT_4');
    var string_is_invalid = '"foo"';
    run_test(schema, string_is_invalid, false, 'DRAFT_4');
  };
  TypeTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TypeTest',
    interfaces: []
  };
  function BignumTest() {
  }
  BignumTest.prototype.integer = function () {
    var schema = '{"type":"integer"}';
    var a_bignum_is_an_integer = '12345678910111213141516171819202122232425262728293031';
    run_test(schema, a_bignum_is_an_integer, true, 'DRAFT_4');
    var a_negative_bignum_is_an_integer = '-12345678910111213141516171819202122232425262728293031';
    run_test(schema, a_negative_bignum_is_an_integer, true, 'DRAFT_4');
  };
  BignumTest.prototype.number = function () {
    var schema = '{"type":"number"}';
    var a_bignum_is_a_number = '98249283749234923498293171823948729348710298301928331';
    run_test(schema, a_bignum_is_a_number, true, 'DRAFT_4');
    var a_negative_bignum_is_a_number = '-98249283749234923498293171823948729348710298301928331';
    run_test(schema, a_negative_bignum_is_a_number, true, 'DRAFT_4');
  };
  BignumTest.prototype.string = function () {
    var schema = '{"type":"string"}';
    var a_bignum_is_not_a_string = '98249283749234923498293171823948729348710298301928331';
    run_test(schema, a_bignum_is_not_a_string, false, 'DRAFT_4');
  };
  BignumTest.prototype.integer_comparison = function () {
    var schema = '{"maximum":18446744073709551615}';
    var comparison_works_for_high_numbers = '18446744073709551600';
    run_test(schema, comparison_works_for_high_numbers, true, 'DRAFT_4');
  };
  BignumTest.prototype.float_comparison_with_high_precision = function () {
    var schema = '{"maximum":972783798187987123879878123.18878137,"exclusiveMaximum":true}';
    var comparison_works_for_high_numbers = '972783798187987123879878123.188781371';
    run_test(schema, comparison_works_for_high_numbers, false, 'DRAFT_4');
  };
  BignumTest.prototype.float_comparison_with_high_precision_on_negative_numbers = function () {
    var schema = '{"minimum":-972783798187987123879878123.18878137,"exclusiveMinimum":true}';
    var comparison_works_for_very_negative_numbers = '-972783798187987123879878123.188781371';
    run_test(schema, comparison_works_for_very_negative_numbers, false, 'DRAFT_4');
  };
  BignumTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BignumTest',
    interfaces: []
  };
  function Ecmascript_regexTest() {
  }
  Ecmascript_regexTest.prototype.ECMA_262_regex_$_does_not_match_trailing_newline = function () {
    var schema = '{"type":"string","pattern":"^abc$"}';
    var matches_in_Python__but_should_not_in_jsonschema = '"abc\\\\n"';
    run_test(schema, matches_in_Python__but_should_not_in_jsonschema, false, 'DRAFT_4');
    var should_match = '"abc"';
    run_test(schema, should_match, true, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262_regex_converts__t_to_horizontal_tab = function () {
    var schema = '{"type":"string","pattern":"^\\\\t$"}';
    var does_not_match = '"\\\\t"';
    run_test(schema, does_not_match, false, 'DRAFT_4');
    var matches = '"\\t"';
    run_test(schema, matches, true, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262_regex_escapes_control_codes_with__c_and_upper_letter = function () {
    var schema = '{"type":"string","pattern":"^\\\\cC$"}';
    var does_not_match = '"\\\\cC"';
    run_test(schema, does_not_match, false, 'DRAFT_4');
    var matches = '"\\u0003"';
    run_test(schema, matches, true, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262_regex_escapes_control_codes_with__c_and_lower_letter = function () {
    var schema = '{"type":"string","pattern":"^\\\\cc$"}';
    var does_not_match = '"\\\\cc"';
    run_test(schema, does_not_match, false, 'DRAFT_4');
    var matches = '"\\u0003"';
    run_test(schema, matches, true, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262__d_matches_ascii_digits_only = function () {
    var schema = '{"type":"string","pattern":"^\\\\d$"}';
    var ASCII_zero_matches = '"0"';
    run_test(schema, ASCII_zero_matches, true, 'DRAFT_4');
    var NKO_DIGIT_ZERO_does_not_match_unlike_e_g__Python = '"\u07C0"';
    run_test(schema, NKO_DIGIT_ZERO_does_not_match_unlike_e_g__Python, false, 'DRAFT_4');
    var NKO_DIGIT_ZERO_as__u_escape_does_not_match = '"\u07C0"';
    run_test(schema, NKO_DIGIT_ZERO_as__u_escape_does_not_match, false, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262__D_matches_everything_but_ascii_digits = function () {
    var schema = '{"type":"string","pattern":"^\\\\D$"}';
    var ASCII_zero_does_not_match = '"0"';
    run_test(schema, ASCII_zero_does_not_match, false, 'DRAFT_4');
    var NKO_DIGIT_ZERO_matches_unlike_e_g__Python = '"\u07C0"';
    run_test(schema, NKO_DIGIT_ZERO_matches_unlike_e_g__Python, true, 'DRAFT_4');
    var NKO_DIGIT_ZERO_as__u_escape_matches = '"\u07C0"';
    run_test(schema, NKO_DIGIT_ZERO_as__u_escape_matches, true, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262__w_matches_ascii_letters_only = function () {
    var schema = '{"type":"string","pattern":"^\\\\w$"}';
    var ASCII__a__matches = '"a"';
    run_test(schema, ASCII__a__matches, true, 'DRAFT_4');
    var latin_1_e_acute_does_not_match_unlike_e_g__Python = '"\xE9"';
    run_test(schema, latin_1_e_acute_does_not_match_unlike_e_g__Python, false, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262__W_matches_everything_but_ascii_letters = function () {
    var schema = '{"type":"string","pattern":"^\\\\W$"}';
    var ASCII__a__does_not_match = '"a"';
    run_test(schema, ASCII__a__does_not_match, false, 'DRAFT_4');
    var latin_1_e_acute_matches_unlike_e_g__Python = '"\xE9"';
    run_test(schema, latin_1_e_acute_matches_unlike_e_g__Python, true, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262__s_matches_whitespace = function () {
    var schema = '{"type":"string","pattern":"^\\\\s$"}';
    var ASCII_space_matches = '" "';
    run_test(schema, ASCII_space_matches, true, 'DRAFT_4');
    var Character_tabulation_matches = '"\\t"';
    run_test(schema, Character_tabulation_matches, true, 'DRAFT_4');
    var Line_tabulation_matches = '"\\u000b"';
    run_test(schema, Line_tabulation_matches, true, 'DRAFT_4');
    var Form_feed_matches = '"\\f"';
    run_test(schema, Form_feed_matches, true, 'DRAFT_4');
    var latin_1_non_breaking_space_matches = '"\xA0"';
    run_test(schema, latin_1_non_breaking_space_matches, true, 'DRAFT_4');
    var zero_width_whitespace_matches = '"\uFEFF"';
    run_test(schema, zero_width_whitespace_matches, true, 'DRAFT_4');
    var line_feed_matches_line_terminator = '"\\n"';
    run_test(schema, line_feed_matches_line_terminator, true, 'DRAFT_4');
    var paragraph_separator_matches_line_terminator = '"\u2029"';
    run_test(schema, paragraph_separator_matches_line_terminator, true, 'DRAFT_4');
    var EM_SPACE_matches_Space_Separator = '"\u2003"';
    run_test(schema, EM_SPACE_matches_Space_Separator, true, 'DRAFT_4');
    var Non_whitespace_control_does_not_match = '"\\u0001"';
    run_test(schema, Non_whitespace_control_does_not_match, false, 'DRAFT_4');
    var Non_whitespace_does_not_match = '"\u2013"';
    run_test(schema, Non_whitespace_does_not_match, false, 'DRAFT_4');
  };
  Ecmascript_regexTest.prototype.ECMA_262__S_matches_everything_but_whitespace = function () {
    var schema = '{"type":"string","pattern":"^\\\\S$"}';
    var ASCII_space_does_not_match = '" "';
    run_test(schema, ASCII_space_does_not_match, false, 'DRAFT_4');
    var Character_tabulation_does_not_match = '"\\t"';
    run_test(schema, Character_tabulation_does_not_match, false, 'DRAFT_4');
    var Line_tabulation_does_not_match = '"\\u000b"';
    run_test(schema, Line_tabulation_does_not_match, false, 'DRAFT_4');
    var Form_feed_does_not_match = '"\\f"';
    run_test(schema, Form_feed_does_not_match, false, 'DRAFT_4');
    var latin_1_non_breaking_space_does_not_match = '"\xA0"';
    run_test(schema, latin_1_non_breaking_space_does_not_match, false, 'DRAFT_4');
    var zero_width_whitespace_does_not_match = '"\uFEFF"';
    run_test(schema, zero_width_whitespace_does_not_match, false, 'DRAFT_4');
    var line_feed_does_not_match_line_terminator = '"\\n"';
    run_test(schema, line_feed_does_not_match_line_terminator, false, 'DRAFT_4');
    var paragraph_separator_does_not_match_line_terminator = '"\u2029"';
    run_test(schema, paragraph_separator_does_not_match_line_terminator, false, 'DRAFT_4');
    var EM_SPACE_does_not_match_Space_Separator = '"\u2003"';
    run_test(schema, EM_SPACE_does_not_match_Space_Separator, false, 'DRAFT_4');
    var Non_whitespace_control_matches = '"\\u0001"';
    run_test(schema, Non_whitespace_control_matches, true, 'DRAFT_4');
    var Non_whitespace_matches = '"\u2013"';
    run_test(schema, Non_whitespace_matches, true, 'DRAFT_4');
  };
  Ecmascript_regexTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Ecmascript_regexTest',
    interfaces: []
  };
  function Float_overflowTest() {
  }
  Float_overflowTest.prototype.all_integers_are_multiples_of_0_5__if_overflow_is_handled = function () {
    var schema = '{"type":"number","multipleOf":0.5}';
    var valid_if_optional_overflow_handling_is_implemented = '1e308';
    run_test(schema, valid_if_optional_overflow_handling_is_implemented, true, 'DRAFT_4');
  };
  Float_overflowTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Float_overflowTest',
    interfaces: []
  };
  function NonBmpRegexTest() {
  }
  NonBmpRegexTest.prototype.Proper_UTF_16_surrogate_pair_handling__pattern = function () {
    var schema = '{"pattern":"^\uD83D\uDC32*$"}';
    var matches_empty = '""';
    run_test(schema, matches_empty, true, 'DRAFT_4');
    var matches_single = '"\uD83D\uDC32"';
    run_test(schema, matches_single, true, 'DRAFT_4');
    var matches_two = '"\uD83D\uDC32\uD83D\uDC32"';
    run_test(schema, matches_two, true, 'DRAFT_4');
    var doesn_t_match_one = '"\uD83D\uDC09"';
    run_test(schema, doesn_t_match_one, false, 'DRAFT_4');
    var doesn_t_match_two = '"\uD83D\uDC09\uD83D\uDC09"';
    run_test(schema, doesn_t_match_two, false, 'DRAFT_4');
    var doesn_t_match_one_ASCII = '"D"';
    run_test(schema, doesn_t_match_one_ASCII, false, 'DRAFT_4');
    var doesn_t_match_two_ASCII = '"DD"';
    run_test(schema, doesn_t_match_two_ASCII, false, 'DRAFT_4');
  };
  NonBmpRegexTest.prototype.Proper_UTF_16_surrogate_pair_handling__patternProperties = function () {
    var schema = '{"patternProperties":{"^\uD83D\uDC32*$":{"type":"integer"}}}';
    var matches_empty = '{"":1}';
    run_test(schema, matches_empty, true, 'DRAFT_4');
    var matches_single = '{"\uD83D\uDC32":1}';
    run_test(schema, matches_single, true, 'DRAFT_4');
    var matches_two = '{"\uD83D\uDC32\uD83D\uDC32":1}';
    run_test(schema, matches_two, true, 'DRAFT_4');
    var doesn_t_match_one = '{"\uD83D\uDC32":"hello"}';
    run_test(schema, doesn_t_match_one, false, 'DRAFT_4');
    var doesn_t_match_two = '{"\uD83D\uDC32\uD83D\uDC32":"hello"}';
    run_test(schema, doesn_t_match_two, false, 'DRAFT_4');
  };
  NonBmpRegexTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NonBmpRegexTest',
    interfaces: []
  };
  function UnicodeTest() {
  }
  UnicodeTest.prototype.unicode_semantics_should_be_used_for_all_pattern_matching = function () {
    var schema = '{"pattern":"\\\\wcole"}';
    var literal_unicode_character_in_json_string = '"Les hivers de mon enfance \xE9taient des saisons longues, longues. Nous vivions en trois lieux: l\'\xE9cole, l\'\xE9glise et la patinoire; mais la vraie vie \xE9tait sur la patinoire."';
    run_test(schema, literal_unicode_character_in_json_string, true, 'DRAFT_4');
    var unicode_character_in_hex_format_in_string = '"Les hivers de mon enfance \xE9taient des saisons longues, longues. Nous vivions en trois lieux: l\'\xE9cole, l\'\xE9glise et la patinoire; mais la vraie vie \xE9tait sur la patinoire."';
    run_test(schema, unicode_character_in_hex_format_in_string, true, 'DRAFT_4');
    var unicode_matching_is_case_sensitive = '"LES HIVERS DE MON ENFANCE \xC9TAIENT DES SAISONS LONGUES, LONGUES. NOUS VIVIONS EN TROIS LIEUX: L\'\xC9COLE, L\'\xC9GLISE ET LA PATINOIRE; MAIS LA VRAIE VIE \xC9TAIT SUR LA PATINOIRE."';
    run_test(schema, unicode_matching_is_case_sensitive, false, 'DRAFT_4');
  };
  UnicodeTest.prototype.unicode_characters_do_not_match_ascii_ranges = function () {
    var schema = '{"pattern":"[a-z]cole"}';
    var literal_unicode_character_in_json_string = '"Les hivers de mon enfance \xE9taient des saisons longues, longues. Nous vivions en trois lieux: l\'\xE9cole, l\'\xE9glise et la patinoire; mais la vraie vie \xE9tait sur la patinoire."';
    run_test(schema, literal_unicode_character_in_json_string, false, 'DRAFT_4');
    var unicode_character_in_hex_format_in_string = '"Les hivers de mon enfance \xE9taient des saisons longues, longues. Nous vivions en trois lieux: l\'\xE9cole, l\'\xE9glise et la patinoire; mais la vraie vie \xE9tait sur la patinoire."';
    run_test(schema, unicode_character_in_hex_format_in_string, false, 'DRAFT_4');
    var ascii_characters_match = '"Les hivers de mon enfance etaient des saisons longues, longues. Nous vivions en trois lieux: l\'ecole, l\'eglise et la patinoire; mais la vraie vie etait sur la patinoire."';
    run_test(schema, ascii_characters_match, true, 'DRAFT_4');
  };
  UnicodeTest.prototype.unicode_digits_are_more_than_0_through_9 = function () {
    var schema = '{"pattern":"^\\\\d+$"}';
    var ascii_digits = '"42"';
    run_test(schema, ascii_digits, true, 'DRAFT_4');
    var ascii_non_digits = '"-%#"';
    run_test(schema, ascii_non_digits, false, 'DRAFT_4');
    var non_ascii_digits_BENGALI_DIGIT_FOUR__BENGALI_DIGIT_TWO = '"\u09EA\u09E8"';
    run_test(schema, non_ascii_digits_BENGALI_DIGIT_FOUR__BENGALI_DIGIT_TWO, true, 'DRAFT_4');
  };
  UnicodeTest.prototype.unicode_semantics_should_be_used_for_all_patternProperties_matching = function () {
    var schema = '{"type":"object","patternProperties":{"\\\\wcole":{}},"additionalProperties":false}';
    var literal_unicode_character_in_json_string = '{"l\'\xE9cole":"pas de vraie vie"}';
    run_test(schema, literal_unicode_character_in_json_string, true, 'DRAFT_4');
    var unicode_character_in_hex_format_in_string = '{"l\'\xE9cole":"pas de vraie vie"}';
    run_test(schema, unicode_character_in_hex_format_in_string, true, 'DRAFT_4');
    var unicode_matching_is_case_sensitive = '{"L\'\xC9COLE":"PAS DE VRAIE VIE"}';
    run_test(schema, unicode_matching_is_case_sensitive, false, 'DRAFT_4');
  };
  UnicodeTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnicodeTest',
    interfaces: []
  };
  function ZeroTerminatedFloatsTest() {
  }
  ZeroTerminatedFloatsTest.prototype.some_languages_do_not_distinguish_between_different_types_of_numeric_value = function () {
    var schema = '{"type":"integer"}';
    var a_float_is_not_an_integer_even_without_fractional_part = '1.0';
    run_test(schema, a_float_is_not_an_integer_even_without_fractional_part, false, 'DRAFT_4');
  };
  ZeroTerminatedFloatsTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ZeroTerminatedFloatsTest',
    interfaces: []
  };
  function Date_timeTest() {
  }
  Date_timeTest.prototype.validation_of_date_time_strings = function () {
    var schema = '{"format":"date-time"}';
    var a_valid_date_time_string = '"1963-06-19T08:30:06.283185Z"';
    run_test(schema, a_valid_date_time_string, true, 'DRAFT_4');
    var a_valid_date_time_string_without_second_fraction = '"1963-06-19T08:30:06Z"';
    run_test(schema, a_valid_date_time_string_without_second_fraction, true, 'DRAFT_4');
    var a_valid_date_time_string_with_plus_offset = '"1937-01-01T12:00:27.87+00:20"';
    run_test(schema, a_valid_date_time_string_with_plus_offset, true, 'DRAFT_4');
    var a_valid_date_time_string_with_minus_offset = '"1990-12-31T15:59:50.123-08:00"';
    run_test(schema, a_valid_date_time_string_with_minus_offset, true, 'DRAFT_4');
    var a_invalid_day_in_date_time_string = '"1990-02-31T15:59:60.123-08:00"';
    run_test(schema, a_invalid_day_in_date_time_string, false, 'DRAFT_4');
    var an_invalid_offset_in_date_time_string = '"1990-12-31T15:59:60-24:00"';
    run_test(schema, an_invalid_offset_in_date_time_string, false, 'DRAFT_4');
    var an_invalid_date_time_string = '"06/19/1963 08:30:06 PST"';
    run_test(schema, an_invalid_date_time_string, false, 'DRAFT_4');
    var case_insensitive_T_and_Z = '"1963-06-19t08:30:06.283185z"';
    run_test(schema, case_insensitive_T_and_Z, true, 'DRAFT_4');
    var only_RFC3339_not_all_of_ISO_8601_are_valid = '"2013-350T01:01:01"';
    run_test(schema, only_RFC3339_not_all_of_ISO_8601_are_valid, false, 'DRAFT_4');
    var invalid_non_padded_month_dates = '"1963-6-19T08:30:06.283185Z"';
    run_test(schema, invalid_non_padded_month_dates, false, 'DRAFT_4');
    var invalid_non_padded_day_dates = '"1963-06-1T08:30:06.283185Z"';
    run_test(schema, invalid_non_padded_day_dates, false, 'DRAFT_4');
    var non_ascii_digits_should_be_rejected_in_the_date_portion = '"1963-06-1\u09EAT00:00:00Z"';
    run_test(schema, non_ascii_digits_should_be_rejected_in_the_date_portion, false, 'DRAFT_4');
    var non_ascii_digits_should_be_rejected_in_the_time_portion = '"1963-06-11T0\u09EA:00:00Z"';
    run_test(schema, non_ascii_digits_should_be_rejected_in_the_time_portion, false, 'DRAFT_4');
  };
  Date_timeTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Date_timeTest',
    interfaces: []
  };
  function EmailTest() {
  }
  EmailTest.prototype.validation_of_e_mail_addresses = function () {
    var schema = '{"format":"email"}';
    var a_valid_e_mail_address = '"joe.bloggs@example.com"';
    run_test(schema, a_valid_e_mail_address, true, 'DRAFT_4');
    var an_invalid_e_mail_address = '"2962"';
    run_test(schema, an_invalid_e_mail_address, false, 'DRAFT_4');
    var tilde_in_local_part_is_valid = '"te~st@example.com"';
    run_test(schema, tilde_in_local_part_is_valid, true, 'DRAFT_4');
    var tilde_before_local_part_is_valid = '"~test@example.com"';
    run_test(schema, tilde_before_local_part_is_valid, true, 'DRAFT_4');
    var tilde_after_local_part_is_valid = '"test~@example.com"';
    run_test(schema, tilde_after_local_part_is_valid, true, 'DRAFT_4');
    var dot_before_local_part_is_not_valid = '".test@example.com"';
    run_test(schema, dot_before_local_part_is_not_valid, false, 'DRAFT_4');
    var dot_after_local_part_is_not_valid = '"test.@example.com"';
    run_test(schema, dot_after_local_part_is_not_valid, false, 'DRAFT_4');
    var two_separated_dots_inside_local_part_are_valid = '"te.s.t@example.com"';
    run_test(schema, two_separated_dots_inside_local_part_are_valid, true, 'DRAFT_4');
    var two_subsequent_dots_inside_local_part_are_not_valid = '"te..st@example.com"';
    run_test(schema, two_subsequent_dots_inside_local_part_are_not_valid, false, 'DRAFT_4');
  };
  EmailTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmailTest',
    interfaces: []
  };
  function HostnameTest() {
  }
  HostnameTest.prototype.validation_of_host_names = function () {
    var schema = '{"format":"hostname"}';
    var a_valid_host_name = '"www.example.com"';
    run_test(schema, a_valid_host_name, true, 'DRAFT_4');
    var a_host_name_starting_with_an_illegal_character = '"-a-host-name-that-starts-with--"';
    run_test(schema, a_host_name_starting_with_an_illegal_character, false, 'DRAFT_4');
    var a_host_name_containing_illegal_characters = '"not_a_valid_host_name"';
    run_test(schema, a_host_name_containing_illegal_characters, false, 'DRAFT_4');
    var a_host_name_with_a_component_too_long = '"a-vvvvvvvvvvvvvvvveeeeeeeeeeeeeeeerrrrrrrrrrrrrrrryyyyyyyyyyyyyyyy-long-host-name-component"';
    run_test(schema, a_host_name_with_a_component_too_long, false, 'DRAFT_4');
    var starts_with_hyphen = '"-hostname"';
    run_test(schema, starts_with_hyphen, false, 'DRAFT_4');
    var ends_with_hyphen = '"hostname-"';
    run_test(schema, ends_with_hyphen, false, 'DRAFT_4');
    var starts_with_underscore = '"_hostname"';
    run_test(schema, starts_with_underscore, false, 'DRAFT_4');
    var ends_with_underscore = '"hostname_"';
    run_test(schema, ends_with_underscore, false, 'DRAFT_4');
    var contains_underscore = '"host_name"';
    run_test(schema, contains_underscore, false, 'DRAFT_4');
    var maximum_label_length = '"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijk.com"';
    run_test(schema, maximum_label_length, true, 'DRAFT_4');
    var exceeds_maximum_label_length = '"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijkl.com"';
    run_test(schema, exceeds_maximum_label_length, false, 'DRAFT_4');
  };
  HostnameTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HostnameTest',
    interfaces: []
  };
  function Ipv4Test() {
  }
  Ipv4Test.prototype.validation_of_IP_addresses = function () {
    var schema = '{"format":"ipv4"}';
    var a_valid_IP_address = '"192.168.0.1"';
    run_test(schema, a_valid_IP_address, true, 'DRAFT_4');
    var an_IP_address_with_too_many_components = '"127.0.0.0.1"';
    run_test(schema, an_IP_address_with_too_many_components, false, 'DRAFT_4');
    var an_IP_address_with_out_of_range_values = '"256.256.256.256"';
    run_test(schema, an_IP_address_with_out_of_range_values, false, 'DRAFT_4');
    var an_IP_address_without_4_components = '"127.0"';
    run_test(schema, an_IP_address_without_4_components, false, 'DRAFT_4');
    var an_IP_address_as_an_integer = '"0x7f000001"';
    run_test(schema, an_IP_address_as_an_integer, false, 'DRAFT_4');
    var an_IP_address_as_an_integer_decimal = '"2130706433"';
    run_test(schema, an_IP_address_as_an_integer_decimal, false, 'DRAFT_4');
    var leading_zeroes_should_be_rejected__as_they_are_treated_as_octals = '"087.10.0.1"';
    run_test(schema, leading_zeroes_should_be_rejected__as_they_are_treated_as_octals, false, 'DRAFT_4');
    var value_without_leading_zero_is_valid = '"87.10.0.1"';
    run_test(schema, value_without_leading_zero_is_valid, true, 'DRAFT_4');
    var non_ascii_digits_should_be_rejected = '"1\u09E87.0.0.1"';
    run_test(schema, non_ascii_digits_should_be_rejected, false, 'DRAFT_4');
  };
  Ipv4Test.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Ipv4Test',
    interfaces: []
  };
  function Ipv6Test() {
  }
  Ipv6Test.prototype.validation_of_IPv6_addresses = function () {
    var schema = '{"format":"ipv6"}';
    var a_valid_IPv6_address = '"::1"';
    run_test(schema, a_valid_IPv6_address, true, 'DRAFT_4');
    var an_IPv6_address_with_out_of_range_values = '"12345::"';
    run_test(schema, an_IPv6_address_with_out_of_range_values, false, 'DRAFT_4');
    var an_IPv6_address_with_too_many_components = '"1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1"';
    run_test(schema, an_IPv6_address_with_too_many_components, false, 'DRAFT_4');
    var an_IPv6_address_containing_illegal_characters = '"::laptop"';
    run_test(schema, an_IPv6_address_containing_illegal_characters, false, 'DRAFT_4');
    var no_digits_is_valid = '"::"';
    run_test(schema, no_digits_is_valid, true, 'DRAFT_4');
    var leading_colons_is_valid = '"::42:ff:1"';
    run_test(schema, leading_colons_is_valid, true, 'DRAFT_4');
    var trailing_colons_is_valid = '"d6::"';
    run_test(schema, trailing_colons_is_valid, true, 'DRAFT_4');
    var missing_leading_octet_is_invalid = '":2:3:4:5:6:7:8"';
    run_test(schema, missing_leading_octet_is_invalid, false, 'DRAFT_4');
    var missing_trailing_octet_is_invalid = '"1:2:3:4:5:6:7:"';
    run_test(schema, missing_trailing_octet_is_invalid, false, 'DRAFT_4');
    var missing_leading_octet_with_omitted_octets_later = '":2:3:4::8"';
    run_test(schema, missing_leading_octet_with_omitted_octets_later, false, 'DRAFT_4');
    var two_sets_of_double_colons_is_invalid = '"1::d6::42"';
    run_test(schema, two_sets_of_double_colons_is_invalid, false, 'DRAFT_4');
    var mixed_format_with_the_ipv4_section_as_decimal_octets = '"1::d6:192.168.0.1"';
    run_test(schema, mixed_format_with_the_ipv4_section_as_decimal_octets, true, 'DRAFT_4');
    var mixed_format_with_double_colons_between_the_sections = '"1:2::192.168.0.1"';
    run_test(schema, mixed_format_with_double_colons_between_the_sections, true, 'DRAFT_4');
    var mixed_format_with_ipv4_section_with_octet_out_of_range = '"1::2:192.168.256.1"';
    run_test(schema, mixed_format_with_ipv4_section_with_octet_out_of_range, false, 'DRAFT_4');
    var mixed_format_with_ipv4_section_with_a_hex_octet = '"1::2:192.168.ff.1"';
    run_test(schema, mixed_format_with_ipv4_section_with_a_hex_octet, false, 'DRAFT_4');
    var mixed_format_with_leading_double_colons_ipv4_mapped_ipv6_address = '"::ffff:192.168.0.1"';
    run_test(schema, mixed_format_with_leading_double_colons_ipv4_mapped_ipv6_address, true, 'DRAFT_4');
    var triple_colons_is_invalid = '"1:2:3:4:5:::8"';
    run_test(schema, triple_colons_is_invalid, false, 'DRAFT_4');
    var __octets = '"1:2:3:4:5:6:7:8"';
    run_test(schema, __octets, true, 'DRAFT_4');
    var insufficient_octets_without_double_colons = '"1:2:3:4:5:6:7"';
    run_test(schema, insufficient_octets_without_double_colons, false, 'DRAFT_4');
    var no_colons_is_invalid = '"1"';
    run_test(schema, no_colons_is_invalid, false, 'DRAFT_4');
    var ipv4_is_not_ipv6 = '"127.0.0.1"';
    run_test(schema, ipv4_is_not_ipv6, false, 'DRAFT_4');
    var ipv4_segment_must_have_4_octets = '"1:2:3:4:1.2.3"';
    run_test(schema, ipv4_segment_must_have_4_octets, false, 'DRAFT_4');
    var leading_whitespace_is_invalid = '"  ::1"';
    run_test(schema, leading_whitespace_is_invalid, false, 'DRAFT_4');
    var trailing_whitespace_is_invalid = '"::1  "';
    run_test(schema, trailing_whitespace_is_invalid, false, 'DRAFT_4');
    var netmask_is_not_a_part_of_ipv6_address = '"fe80::/64"';
    run_test(schema, netmask_is_not_a_part_of_ipv6_address, false, 'DRAFT_4');
    var zone_id_is_not_a_part_of_ipv6_address = '"fe80::a%eth1"';
    run_test(schema, zone_id_is_not_a_part_of_ipv6_address, false, 'DRAFT_4');
    var a_long_valid_ipv6 = '"1000:1000:1000:1000:1000:1000:255.255.255.255"';
    run_test(schema, a_long_valid_ipv6, true, 'DRAFT_4');
    var a_long_invalid_ipv6__below_length_limit__first = '"100:100:100:100:100:100:255.255.255.255.255"';
    run_test(schema, a_long_invalid_ipv6__below_length_limit__first, false, 'DRAFT_4');
    var a_long_invalid_ipv6__below_length_limit__second = '"100:100:100:100:100:100:100:255.255.255.255"';
    run_test(schema, a_long_invalid_ipv6__below_length_limit__second, false, 'DRAFT_4');
    var non_ascii_digits_should_be_rejected = '"1:2:3:4:5:6:7:\u09EA"';
    run_test(schema, non_ascii_digits_should_be_rejected, false, 'DRAFT_4');
    var non_ascii_digits_should_be_rejected_in_the_ipv4_portion_also = '"1:2::192.16\u09EA.0.1"';
    run_test(schema, non_ascii_digits_should_be_rejected_in_the_ipv4_portion_also, false, 'DRAFT_4');
  };
  Ipv6Test.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Ipv6Test',
    interfaces: []
  };
  function UriTest() {
  }
  UriTest.prototype.validation_of_URIs = function () {
    var schema = '{"format":"uri"}';
    var a_valid_URL_with_anchor_tag = '"http://foo.bar/?baz=qux#quux"';
    run_test(schema, a_valid_URL_with_anchor_tag, true, 'DRAFT_4');
    var a_valid_URL_with_anchor_tag_and_parentheses = '"http://foo.com/blah_(wikipedia)_blah#cite-1"';
    run_test(schema, a_valid_URL_with_anchor_tag_and_parentheses, true, 'DRAFT_4');
    var a_valid_URL_with_URL_encoded_stuff = '"http://foo.bar/?q=Test%20URL-encoded%20stuff"';
    run_test(schema, a_valid_URL_with_URL_encoded_stuff, true, 'DRAFT_4');
    var a_valid_puny_coded_URL_ = '"http://xn--nw2a.xn--j6w193g/"';
    run_test(schema, a_valid_puny_coded_URL_, true, 'DRAFT_4');
    var a_valid_URL_with_many_special_characters = '"http://-.~_!$&\'()*+,;=:%40:80%2f::::::@example.com"';
    run_test(schema, a_valid_URL_with_many_special_characters, true, 'DRAFT_4');
    var a_valid_URL_based_on_IPv4 = '"http://223.255.255.254"';
    run_test(schema, a_valid_URL_based_on_IPv4, true, 'DRAFT_4');
    var a_valid_URL_with_ftp_scheme = '"ftp://ftp.is.co.za/rfc/rfc1808.txt"';
    run_test(schema, a_valid_URL_with_ftp_scheme, true, 'DRAFT_4');
    var a_valid_URL_for_a_simple_text_file = '"http://www.ietf.org/rfc/rfc2396.txt"';
    run_test(schema, a_valid_URL_for_a_simple_text_file, true, 'DRAFT_4');
    var a_valid_URL_ = '"ldap://[2001:db8::7]/c=GB?objectClass?one"';
    run_test(schema, a_valid_URL_, true, 'DRAFT_4');
    var a_valid_mailto_URI = '"mailto:John.Doe@example.com"';
    run_test(schema, a_valid_mailto_URI, true, 'DRAFT_4');
    var a_valid_newsgroup_URI = '"news:comp.infosystems.www.servers.unix"';
    run_test(schema, a_valid_newsgroup_URI, true, 'DRAFT_4');
    var a_valid_tel_URI = '"tel:+1-816-555-1212"';
    run_test(schema, a_valid_tel_URI, true, 'DRAFT_4');
    var a_valid_URN = '"urn:oasis:names:specification:docbook:dtd:xml:4.1.2"';
    run_test(schema, a_valid_URN, true, 'DRAFT_4');
    var an_invalid_protocol_relative_URI_Reference = '"//foo.bar/?baz=qux#quux"';
    run_test(schema, an_invalid_protocol_relative_URI_Reference, false, 'DRAFT_4');
    var an_invalid_relative_URI_Reference = '"/abc"';
    run_test(schema, an_invalid_relative_URI_Reference, false, 'DRAFT_4');
    var an_invalid_URI = '"\\\\\\\\WINDOWS\\\\fileshare"';
    run_test(schema, an_invalid_URI, false, 'DRAFT_4');
    var an_invalid_URI_though_valid_URI_reference = '"abc"';
    run_test(schema, an_invalid_URI_though_valid_URI_reference, false, 'DRAFT_4');
    var an_invalid_URI_with_spaces = '"http:// shouldfail.com"';
    run_test(schema, an_invalid_URI_with_spaces, false, 'DRAFT_4');
    var an_invalid_URI_with_spaces_and_missing_scheme = '":// should fail"';
    run_test(schema, an_invalid_URI_with_spaces_and_missing_scheme, false, 'DRAFT_4');
    var an_invalid_URI_with_comma_in_scheme = '"bar,baz:foo"';
    run_test(schema, an_invalid_URI_with_comma_in_scheme, false, 'DRAFT_4');
  };
  UriTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UriTest',
    interfaces: []
  };
  function JsonParsingHelper() {
  }
  JsonParsingHelper.prototype.parseJson_61zpoe$ = function (schemaFile) {
    var tmp$;
    var schemaAsString = loadResourceText(schemaFile);
    return Kotlin.isType(tmp$ = adapt(schemaAsString), JsonObject) ? tmp$ : throwCCE();
  };
  JsonParsingHelper.prototype.parseJsonScalar_61zpoe$ = function (schemaFile) {
    var schemaAsString = loadResourceText(schemaFile);
    return new StringJsonScalar(this.removeLeadingEscapedQuotes_gf9z08$_0(schemaAsString.trim()));
  };
  JsonParsingHelper.prototype.removeLeadingEscapedQuotes_gf9z08$_0 = function (value) {
    var tmp$;
    if (first(value) === 34 && last(value) === 34) {
      var endIndex = value.length - 1 | 0;
      tmp$ = value.substring(1, endIndex);
    } else {
      var tmp$_0;
      if (first(value) === 39 && last(value) === 39) {
        var endIndex_0 = value.length - 1 | 0;
        tmp$_0 = value.substring(1, endIndex_0);
      } else
        tmp$_0 = value;
      tmp$ = tmp$_0;
    }
    return tmp$;
  };
  JsonParsingHelper.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'JsonParsingHelper',
    interfaces: []
  };
  function adapt(json) {
    var element = Json.Default.parseToJsonElement_61zpoe$(json);
    return KotlinXJsonElementAdapter_getInstance().adapt_qiw0cd$(element);
  }
  function KotlinXJsonElementAdapter() {
    KotlinXJsonElementAdapter_instance = this;
  }
  KotlinXJsonElementAdapter.prototype.adapt_qiw0cd$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, JsonObject_0))
      tmp$ = this.adaptObject_0(element);
    else if (Kotlin.isType(element, JsonArray))
      tmp$ = this.adaptArray_0(element);
    else if (Kotlin.isType(element, JsonPrimitive))
      tmp$ = this.adaptScalar_0(element);
    else
      throw RuntimeException_init('asdasdasdasdasd');
    return tmp$;
  };
  KotlinXJsonElementAdapter.prototype.adaptObject_0 = function (obj) {
    var $receiver = obj.entries;
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new Pair(item.key, this.adapt_qiw0cd$(item.value)));
    }
    var pairs = destination;
    return new DefaultJsonObject(toMap(pairs));
  };
  KotlinXJsonElementAdapter.prototype.adaptArray_0 = function (array) {
    var $receiver = toList(array);
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(this.adapt_qiw0cd$(item));
    }
    var elements = destination;
    return new DefaultJsonArray(elements);
  };
  KotlinXJsonElementAdapter.prototype.adaptScalar_0 = function (scalar) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(scalar, Object.getPrototypeOf(json.JsonNull).constructor))
      tmp$_0 = validator.DefaultNullElement;
    else {
      if (scalar.isString)
        tmp$ = new StringJsonScalar(scalar.content);
      else if (get_intOrNull(scalar) != null)
        tmp$ = new IntJsonScalar(get_int(scalar));
      else if (get_booleanOrNull(scalar) != null)
        tmp$ = new BooleanJsonScalar(get_boolean(scalar));
      else if (get_doubleOrNull(scalar) != null)
        tmp$ = new NumberJsonScalar(get_double(scalar));
      else
        tmp$ = new StringJsonScalar(scalar.content);
      return tmp$;
    }
    return tmp$_0;
  };
  KotlinXJsonElementAdapter.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'KotlinXJsonElementAdapter',
    interfaces: []
  };
  var KotlinXJsonElementAdapter_instance = null;
  function KotlinXJsonElementAdapter_getInstance() {
    if (KotlinXJsonElementAdapter_instance === null) {
      new KotlinXJsonElementAdapter();
    }return KotlinXJsonElementAdapter_instance;
  }
  var fs;
  var jsPath;
  function loadResourceText(path) {
    var finalPath = jsPath.join(__dirname, '../../../..', 'processedResources', 'js', 'test', path);
    return fs.readFileSync(finalPath, 'utf8');
  }
  function PlatformLoaderTest() {
  }
  PlatformLoaderTest.prototype.can_load_resource_file = function () {
    var something = loadResourceText('myTestResource.txt');
    assertEquals('some text\n', something);
  };
  PlatformLoaderTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PlatformLoaderTest',
    interfaces: []
  };
  var package$org = _.org || (_.org = {});
  var package$validator = package$org.validator || (package$org.validator = {});
  var package$tck = package$validator.tck || (package$validator.tck = {});
  package$tck.provideParser_61zpoe$ = provideParser;
  $$importsForInline$$['kotlin-test'] = $module$kotlin_test;
  package$tck.run_test_f372nr$ = run_test;
  var package$draft4 = package$tck.draft4 || (package$tck.draft4 = {});
  package$draft4.AdditionalItemsTest = AdditionalItemsTest;
  package$draft4.AdditionalPropertiesTest = AdditionalPropertiesTest;
  package$draft4.AllOfTest = AllOfTest;
  package$draft4.AnyOfTest = AnyOfTest;
  package$draft4.DefaultTest = DefaultTest;
  package$draft4.DependenciesTest = DependenciesTest;
  package$draft4.EnumTest = EnumTest;
  package$draft4.FormatTest = FormatTest;
  package$draft4.ItemsTest = ItemsTest;
  package$draft4.MaxItemsTest = MaxItemsTest;
  package$draft4.MaxLengthTest = MaxLengthTest;
  package$draft4.MaxPropertiesTest = MaxPropertiesTest;
  package$draft4.MaximumTest = MaximumTest;
  package$draft4.MinItemsTest = MinItemsTest;
  package$draft4.MinLengthTest = MinLengthTest;
  package$draft4.MinPropertiesTest = MinPropertiesTest;
  package$draft4.MinimumTest = MinimumTest;
  package$draft4.MultipleOfTest = MultipleOfTest;
  package$draft4.NotTest = NotTest;
  package$draft4.OneOfTest = OneOfTest;
  package$draft4.PatternPropertiesTest = PatternPropertiesTest;
  package$draft4.PatternTest = PatternTest;
  package$draft4.PropertiesTest = PropertiesTest;
  package$draft4.RequiredTest = RequiredTest;
  package$draft4.TypeTest = TypeTest;
  var package$optional = package$draft4.optional || (package$draft4.optional = {});
  package$optional.BignumTest = BignumTest;
  package$optional.Ecmascript_regexTest = Ecmascript_regexTest;
  package$optional.Float_overflowTest = Float_overflowTest;
  package$optional.NonBmpRegexTest = NonBmpRegexTest;
  package$optional.UnicodeTest = UnicodeTest;
  package$optional.ZeroTerminatedFloatsTest = ZeroTerminatedFloatsTest;
  var package$format = package$optional.format || (package$optional.format = {});
  package$format.Date_timeTest = Date_timeTest;
  package$format.EmailTest = EmailTest;
  package$format.HostnameTest = HostnameTest;
  package$format.Ipv4Test = Ipv4Test;
  package$format.Ipv6Test = Ipv6Test;
  package$format.UriTest = UriTest;
  var package$testing = package$validator.testing || (package$validator.testing = {});
  package$testing.JsonParsingHelper = JsonParsingHelper;
  package$testing.adapt_61zpoe$ = adapt;
  Object.defineProperty(package$testing, 'KotlinXJsonElementAdapter', {
    get: KotlinXJsonElementAdapter_getInstance
  });
  Object.defineProperty(package$testing, 'fs', {
    get: function () {
      return fs;
    }
  });
  Object.defineProperty(package$testing, 'jsPath', {
    get: function () {
      return jsPath;
    }
  });
  package$testing.loadResourceText_61zpoe$ = loadResourceText;
  package$testing.PlatformLoaderTest = PlatformLoaderTest;
  fs = require('fs');
  jsPath = require('path');
  suite('org.validator.tck.draft4', false, function () {
    suite('AdditionalItemsTest', false, function () {
      test('additionalItems_as_schema', false, function () {
        return (new AdditionalItemsTest()).additionalItems_as_schema();
      });
      test('when_items_is_schema__additionalItems_does_nothing', false, function () {
        return (new AdditionalItemsTest()).when_items_is_schema__additionalItems_does_nothing();
      });
      test('array_of_items_with_no_additionalItems_permitted', false, function () {
        return (new AdditionalItemsTest()).array_of_items_with_no_additionalItems_permitted();
      });
      test('additionalItems_as_false_without_items', false, function () {
        return (new AdditionalItemsTest()).additionalItems_as_false_without_items();
      });
      test('additionalItems_are_allowed_by_default', false, function () {
        return (new AdditionalItemsTest()).additionalItems_are_allowed_by_default();
      });
      test('additionalItems_should_not_look_in_applicators__valid_case', false, function () {
        return (new AdditionalItemsTest()).additionalItems_should_not_look_in_applicators__valid_case();
      });
      test('additionalItems_should_not_look_in_applicators__invalid_case', false, function () {
        return (new AdditionalItemsTest()).additionalItems_should_not_look_in_applicators__invalid_case();
      });
      test('items_validation_adjusts_the_starting_index_for_additionalItems', false, function () {
        return (new AdditionalItemsTest()).items_validation_adjusts_the_starting_index_for_additionalItems();
      });
    });
    suite('AdditionalPropertiesTest', false, function () {
      test('additionalProperties_being_false_does_not_allow_other_properties', false, function () {
        return (new AdditionalPropertiesTest()).additionalProperties_being_false_does_not_allow_other_properties();
      });
      test('non_ASCII_pattern_with_additionalProperties', false, function () {
        return (new AdditionalPropertiesTest()).non_ASCII_pattern_with_additionalProperties();
      });
      test('additionalProperties_allows_a_schema_which_should_validate', false, function () {
        return (new AdditionalPropertiesTest()).additionalProperties_allows_a_schema_which_should_validate();
      });
      test('additionalProperties_can_exist_by_itself', false, function () {
        return (new AdditionalPropertiesTest()).additionalProperties_can_exist_by_itself();
      });
      test('additionalProperties_are_allowed_by_default', false, function () {
        return (new AdditionalPropertiesTest()).additionalProperties_are_allowed_by_default();
      });
      test('additionalProperties_should_not_look_in_applicators', false, function () {
        return (new AdditionalPropertiesTest()).additionalProperties_should_not_look_in_applicators();
      });
    });
    suite('AllOfTest', false, function () {
      test('allOf', false, function () {
        return (new AllOfTest()).allOf();
      });
      test('allOf_with_base_schema', false, function () {
        return (new AllOfTest()).allOf_with_base_schema();
      });
      test('allOf_simple_types', false, function () {
        return (new AllOfTest()).allOf_simple_types();
      });
      test('allOf_with_one_empty_schema', false, function () {
        return (new AllOfTest()).allOf_with_one_empty_schema();
      });
      test('allOf_with_two_empty_schemas', false, function () {
        return (new AllOfTest()).allOf_with_two_empty_schemas();
      });
      test('allOf_with_the_first_empty_schema', false, function () {
        return (new AllOfTest()).allOf_with_the_first_empty_schema();
      });
      test('allOf_with_the_last_empty_schema', false, function () {
        return (new AllOfTest()).allOf_with_the_last_empty_schema();
      });
      test('nested_allOf__to_check_validation_semantics', false, function () {
        return (new AllOfTest()).nested_allOf__to_check_validation_semantics();
      });
      test('allOf_combined_with_anyOf__oneOf', false, function () {
        return (new AllOfTest()).allOf_combined_with_anyOf__oneOf();
      });
    });
    suite('AnyOfTest', false, function () {
      test('anyOf', false, function () {
        return (new AnyOfTest()).anyOf();
      });
      test('anyOf_with_base_schema', false, function () {
        return (new AnyOfTest()).anyOf_with_base_schema();
      });
      test('anyOf_complex_types', false, function () {
        return (new AnyOfTest()).anyOf_complex_types();
      });
      test('anyOf_with_one_empty_schema', false, function () {
        return (new AnyOfTest()).anyOf_with_one_empty_schema();
      });
      test('nested_anyOf__to_check_validation_semantics', false, function () {
        return (new AnyOfTest()).nested_anyOf__to_check_validation_semantics();
      });
    });
    suite('DefaultTest', false, function () {
      test('invalid_type_for_default', false, function () {
        return (new DefaultTest()).invalid_type_for_default();
      });
      test('invalid_string_value_for_default', false, function () {
        return (new DefaultTest()).invalid_string_value_for_default();
      });
      test('the_default_keyword_does_not_do_anything_if_the_property_is_missing', false, function () {
        return (new DefaultTest()).the_default_keyword_does_not_do_anything_if_the_property_is_missing();
      });
    });
    suite('DependenciesTest', false, function () {
      test('dependencies', false, function () {
        return (new DependenciesTest()).dependencies();
      });
      test('multiple_dependencies', false, function () {
        return (new DependenciesTest()).multiple_dependencies();
      });
      test('multiple_dependencies_subschema', false, function () {
        return (new DependenciesTest()).multiple_dependencies_subschema();
      });
      test('dependencies_with_escaped_characters', false, function () {
        return (new DependenciesTest()).dependencies_with_escaped_characters();
      });
    });
    suite('EnumTest', false, function () {
      test('simple_enum_validation', false, function () {
        return (new EnumTest()).simple_enum_validation();
      });
      test('heterogeneous_enum_validation', false, function () {
        return (new EnumTest()).heterogeneous_enum_validation();
      });
      test('heterogeneous_enum_with_null_validation', false, function () {
        return (new EnumTest()).heterogeneous_enum_with_null_validation();
      });
      test('enums_in_properties', false, function () {
        return (new EnumTest()).enums_in_properties();
      });
      test('enum_with_escaped_characters', false, function () {
        return (new EnumTest()).enum_with_escaped_characters();
      });
      test('enum_with_false_does_not_match_0', false, function () {
        return (new EnumTest()).enum_with_false_does_not_match_0();
      });
      test('enum_with_true_does_not_match_1', false, function () {
        return (new EnumTest()).enum_with_true_does_not_match_1();
      });
      test('enum_with_0_does_not_match_false', false, function () {
        return (new EnumTest()).enum_with_0_does_not_match_false();
      });
      test('enum_with_1_does_not_match_true', false, function () {
        return (new EnumTest()).enum_with_1_does_not_match_true();
      });
      test('nul_characters_in_strings', false, function () {
        return (new EnumTest()).nul_characters_in_strings();
      });
    });
    suite('FormatTest', false, function () {
      test('email_format', false, function () {
        return (new FormatTest()).email_format();
      });
      test('ipv4_format', false, function () {
        return (new FormatTest()).ipv4_format();
      });
      test('ipv6_format', false, function () {
        return (new FormatTest()).ipv6_format();
      });
      test('hostname_format', false, function () {
        return (new FormatTest()).hostname_format();
      });
      test('date_time_format', false, function () {
        return (new FormatTest()).date_time_format();
      });
      test('uri_format', false, function () {
        return (new FormatTest()).uri_format();
      });
    });
    suite('ItemsTest', false, function () {
      test('a_schema_given_for_items', false, function () {
        return (new ItemsTest()).a_schema_given_for_items();
      });
      test('an_array_of_schemas_for_items', false, function () {
        return (new ItemsTest()).an_array_of_schemas_for_items();
      });
      test('items_and_subitems', false, function () {
        return (new ItemsTest()).items_and_subitems();
      });
      test('nested_items', false, function () {
        return (new ItemsTest()).nested_items();
      });
    });
    suite('MaxItemsTest', false, function () {
      test('maxItems_validation', false, function () {
        return (new MaxItemsTest()).maxItems_validation();
      });
    });
    suite('MaxLengthTest', false, function () {
      test('maxLength_validation', false, function () {
        return (new MaxLengthTest()).maxLength_validation();
      });
    });
    suite('MaxPropertiesTest', false, function () {
      test('maxProperties_validation', false, function () {
        return (new MaxPropertiesTest()).maxProperties_validation();
      });
      test('maxProperties___0_means_the_object_is_empty', false, function () {
        return (new MaxPropertiesTest()).maxProperties___0_means_the_object_is_empty();
      });
    });
    suite('MaximumTest', false, function () {
      test('maximum_validation', false, function () {
        return (new MaximumTest()).maximum_validation();
      });
      test('maximum_validation_with_unsigned_integer', false, function () {
        return (new MaximumTest()).maximum_validation_with_unsigned_integer();
      });
      test('maximum_validation_explicit_false_exclusivity', false, function () {
        return (new MaximumTest()).maximum_validation_explicit_false_exclusivity();
      });
      test('exclusiveMaximum_validation', false, function () {
        return (new MaximumTest()).exclusiveMaximum_validation();
      });
    });
    suite('MinItemsTest', false, function () {
      test('minItems_validation', false, function () {
        return (new MinItemsTest()).minItems_validation();
      });
    });
    suite('MinLengthTest', false, function () {
      test('minLength_validation', false, function () {
        return (new MinLengthTest()).minLength_validation();
      });
    });
    suite('MinPropertiesTest', false, function () {
      test('minProperties_validation', false, function () {
        return (new MinPropertiesTest()).minProperties_validation();
      });
    });
    suite('MinimumTest', false, function () {
      test('minimum_validation', false, function () {
        return (new MinimumTest()).minimum_validation();
      });
      test('minimum_validation_explicit_false_exclusivity', false, function () {
        return (new MinimumTest()).minimum_validation_explicit_false_exclusivity();
      });
      test('exclusiveMinimum_validation', false, function () {
        return (new MinimumTest()).exclusiveMinimum_validation();
      });
      test('minimum_validation_with_signed_integer', false, function () {
        return (new MinimumTest()).minimum_validation_with_signed_integer();
      });
    });
    suite('MultipleOfTest', false, function () {
      test('by_int', false, function () {
        return (new MultipleOfTest()).by_int();
      });
      test('by_number', false, function () {
        return (new MultipleOfTest()).by_number();
      });
      test('by_small_number', false, function () {
        return (new MultipleOfTest()).by_small_number();
      });
      test('invalid_instance_should_not_raise_error_when_float_division___inf', false, function () {
        return (new MultipleOfTest()).invalid_instance_should_not_raise_error_when_float_division___inf();
      });
    });
    suite('NotTest', false, function () {
      test('not', false, function () {
        return (new NotTest()).not();
      });
      test('not_multiple_types', false, function () {
        return (new NotTest()).not_multiple_types();
      });
      test('not_more_complex_schema', false, function () {
        return (new NotTest()).not_more_complex_schema();
      });
      test('forbidden_property', false, function () {
        return (new NotTest()).forbidden_property();
      });
    });
    suite('OneOfTest', false, function () {
      test('oneOf', false, function () {
        return (new OneOfTest()).oneOf();
      });
      test('oneOf_with_base_schema', false, function () {
        return (new OneOfTest()).oneOf_with_base_schema();
      });
      test('oneOf_complex_types', false, function () {
        return (new OneOfTest()).oneOf_complex_types();
      });
      test('oneOf_with_empty_schema', false, function () {
        return (new OneOfTest()).oneOf_with_empty_schema();
      });
      test('oneOf_with_required', false, function () {
        return (new OneOfTest()).oneOf_with_required();
      });
      test('oneOf_with_missing_optional_property', false, function () {
        return (new OneOfTest()).oneOf_with_missing_optional_property();
      });
      test('nested_oneOf__to_check_validation_semantics', false, function () {
        return (new OneOfTest()).nested_oneOf__to_check_validation_semantics();
      });
    });
    suite('PatternPropertiesTest', false, function () {
      test('patternProperties_validates_properties_matching_a_regex', false, function () {
        return (new PatternPropertiesTest()).patternProperties_validates_properties_matching_a_regex();
      });
      test('multiple_simultaneous_patternProperties_are_validated', false, function () {
        return (new PatternPropertiesTest()).multiple_simultaneous_patternProperties_are_validated();
      });
      test('regexes_are_not_anchored_by_default_and_are_case_sensitive', false, function () {
        return (new PatternPropertiesTest()).regexes_are_not_anchored_by_default_and_are_case_sensitive();
      });
    });
    suite('PatternTest', false, function () {
      test('pattern_validation', false, function () {
        return (new PatternTest()).pattern_validation();
      });
      test('pattern_is_not_anchored', false, function () {
        return (new PatternTest()).pattern_is_not_anchored();
      });
    });
    suite('PropertiesTest', false, function () {
      test('object_properties_validation', false, function () {
        return (new PropertiesTest()).object_properties_validation();
      });
      test('properties__patternProperties__additionalProperties_interaction', false, function () {
        return (new PropertiesTest()).properties__patternProperties__additionalProperties_interaction();
      });
      test('properties_with_escaped_characters', false, function () {
        return (new PropertiesTest()).properties_with_escaped_characters();
      });
    });
    suite('RequiredTest', false, function () {
      test('required_validation', false, function () {
        return (new RequiredTest()).required_validation();
      });
      test('required_default_validation', false, function () {
        return (new RequiredTest()).required_default_validation();
      });
      test('required_with_escaped_characters', false, function () {
        return (new RequiredTest()).required_with_escaped_characters();
      });
    });
    suite('TypeTest', false, function () {
      test('integer_type_matches_integers', false, function () {
        return (new TypeTest()).integer_type_matches_integers();
      });
      test('number_type_matches_numbers', false, function () {
        return (new TypeTest()).number_type_matches_numbers();
      });
      test('string_type_matches_strings', false, function () {
        return (new TypeTest()).string_type_matches_strings();
      });
      test('object_type_matches_objects', false, function () {
        return (new TypeTest()).object_type_matches_objects();
      });
      test('array_type_matches_arrays', false, function () {
        return (new TypeTest()).array_type_matches_arrays();
      });
      test('boolean_type_matches_booleans', false, function () {
        return (new TypeTest()).boolean_type_matches_booleans();
      });
      test('null_type_matches_only_the_null_object', false, function () {
        return (new TypeTest()).null_type_matches_only_the_null_object();
      });
      test('multiple_types_can_be_specified_in_an_array', false, function () {
        return (new TypeTest()).multiple_types_can_be_specified_in_an_array();
      });
      test('type_as_array_with_one_item', false, function () {
        return (new TypeTest()).type_as_array_with_one_item();
      });
      test('type__array_or_object', false, function () {
        return (new TypeTest()).type__array_or_object();
      });
      test('type__array__object_or_null', false, function () {
        return (new TypeTest()).type__array__object_or_null();
      });
    });
  });
  suite('org.validator.tck.draft4.optional', false, function () {
    suite('BignumTest', false, function () {
      test('integer', false, function () {
        return (new BignumTest()).integer();
      });
      test('number', false, function () {
        return (new BignumTest()).number();
      });
      test('string', false, function () {
        return (new BignumTest()).string();
      });
      test('integer_comparison', false, function () {
        return (new BignumTest()).integer_comparison();
      });
      test('float_comparison_with_high_precision', false, function () {
        return (new BignumTest()).float_comparison_with_high_precision();
      });
      test('float_comparison_with_high_precision_on_negative_numbers', false, function () {
        return (new BignumTest()).float_comparison_with_high_precision_on_negative_numbers();
      });
    });
    suite('Ecmascript_regexTest', false, function () {
      test('ECMA_262_regex_$_does_not_match_trailing_newline', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262_regex_$_does_not_match_trailing_newline();
      });
      test('ECMA_262_regex_converts__t_to_horizontal_tab', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262_regex_converts__t_to_horizontal_tab();
      });
      test('ECMA_262_regex_escapes_control_codes_with__c_and_upper_letter', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262_regex_escapes_control_codes_with__c_and_upper_letter();
      });
      test('ECMA_262_regex_escapes_control_codes_with__c_and_lower_letter', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262_regex_escapes_control_codes_with__c_and_lower_letter();
      });
      test('ECMA_262__d_matches_ascii_digits_only', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262__d_matches_ascii_digits_only();
      });
      test('ECMA_262__D_matches_everything_but_ascii_digits', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262__D_matches_everything_but_ascii_digits();
      });
      test('ECMA_262__w_matches_ascii_letters_only', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262__w_matches_ascii_letters_only();
      });
      test('ECMA_262__W_matches_everything_but_ascii_letters', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262__W_matches_everything_but_ascii_letters();
      });
      test('ECMA_262__s_matches_whitespace', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262__s_matches_whitespace();
      });
      test('ECMA_262__S_matches_everything_but_whitespace', false, function () {
        return (new Ecmascript_regexTest()).ECMA_262__S_matches_everything_but_whitespace();
      });
    });
    suite('Float_overflowTest', false, function () {
      test('all_integers_are_multiples_of_0_5__if_overflow_is_handled', false, function () {
        return (new Float_overflowTest()).all_integers_are_multiples_of_0_5__if_overflow_is_handled();
      });
    });
    suite('NonBmpRegexTest', false, function () {
      test('Proper_UTF_16_surrogate_pair_handling__pattern', false, function () {
        return (new NonBmpRegexTest()).Proper_UTF_16_surrogate_pair_handling__pattern();
      });
      test('Proper_UTF_16_surrogate_pair_handling__patternProperties', false, function () {
        return (new NonBmpRegexTest()).Proper_UTF_16_surrogate_pair_handling__patternProperties();
      });
    });
    suite('UnicodeTest', false, function () {
      test('unicode_semantics_should_be_used_for_all_pattern_matching', false, function () {
        return (new UnicodeTest()).unicode_semantics_should_be_used_for_all_pattern_matching();
      });
      test('unicode_characters_do_not_match_ascii_ranges', false, function () {
        return (new UnicodeTest()).unicode_characters_do_not_match_ascii_ranges();
      });
      test('unicode_digits_are_more_than_0_through_9', false, function () {
        return (new UnicodeTest()).unicode_digits_are_more_than_0_through_9();
      });
      test('unicode_semantics_should_be_used_for_all_patternProperties_matching', false, function () {
        return (new UnicodeTest()).unicode_semantics_should_be_used_for_all_patternProperties_matching();
      });
    });
    suite('ZeroTerminatedFloatsTest', false, function () {
      test('some_languages_do_not_distinguish_between_different_types_of_numeric_value', false, function () {
        return (new ZeroTerminatedFloatsTest()).some_languages_do_not_distinguish_between_different_types_of_numeric_value();
      });
    });
  });
  suite('org.validator.tck.draft4.optional.format', false, function () {
    suite('Date_timeTest', false, function () {
      test('validation_of_date_time_strings', false, function () {
        return (new Date_timeTest()).validation_of_date_time_strings();
      });
    });
    suite('EmailTest', false, function () {
      test('validation_of_e_mail_addresses', false, function () {
        return (new EmailTest()).validation_of_e_mail_addresses();
      });
    });
    suite('HostnameTest', false, function () {
      test('validation_of_host_names', false, function () {
        return (new HostnameTest()).validation_of_host_names();
      });
    });
    suite('Ipv4Test', false, function () {
      test('validation_of_IP_addresses', false, function () {
        return (new Ipv4Test()).validation_of_IP_addresses();
      });
    });
    suite('Ipv6Test', false, function () {
      test('validation_of_IPv6_addresses', false, function () {
        return (new Ipv6Test()).validation_of_IPv6_addresses();
      });
    });
    suite('UriTest', false, function () {
      test('validation_of_URIs', false, function () {
        return (new UriTest()).validation_of_URIs();
      });
    });
  });
  suite('org.validator.testing', false, function () {
    suite('PlatformLoaderTest', false, function () {
      test('can_load_resource_file', false, function () {
        return (new PlatformLoaderTest()).can_load_resource_file();
      });
    });
  });
  Kotlin.defineModule('json-schema-validator-tck-test-suite-test', _);
  return _;
}));

//# sourceMappingURL=json-schema-validator-tck-test-suite-test.js.map
