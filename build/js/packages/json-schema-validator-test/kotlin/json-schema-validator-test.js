(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'json-schema-validator', 'kotlin-test', 'kotlinx-serialization-kotlinx-serialization-json-js-legacy'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('json-schema-validator'), require('kotlin-test'), require('kotlinx-serialization-kotlinx-serialization-json-js-legacy'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-test'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'json-schema-validator-test'.");
    }if (typeof this['json-schema-validator'] === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-test'. Its dependency 'json-schema-validator' was not found. Please, check whether 'json-schema-validator' is loaded prior to 'json-schema-validator-test'.");
    }if (typeof this['kotlin-test'] === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-test'. Its dependency 'kotlin-test' was not found. Please, check whether 'kotlin-test' is loaded prior to 'json-schema-validator-test'.");
    }if (typeof this['kotlinx-serialization-kotlinx-serialization-json-js-legacy'] === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-test'. Its dependency 'kotlinx-serialization-kotlinx-serialization-json-js-legacy' was not found. Please, check whether 'kotlinx-serialization-kotlinx-serialization-json-js-legacy' is loaded prior to 'json-schema-validator-test'.");
    }root['json-schema-validator-test'] = factory(typeof this['json-schema-validator-test'] === 'undefined' ? {} : this['json-schema-validator-test'], kotlin, this['json-schema-validator'], this['kotlin-test'], this['kotlinx-serialization-kotlinx-serialization-json-js-legacy']);
  }
}(this, function (_, Kotlin, $module$json_schema_validator, $module$kotlin_test, $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var any = $module$json_schema_validator.org.validator.rules.any;
  var assertEquals = $module$kotlin_test.kotlin.test.assertEquals_3m0tl5$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var test = $module$kotlin_test.kotlin.test.test;
  var suite = $module$kotlin_test.kotlin.test.suite;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var OrValidationRule = $module$json_schema_validator.org.validator.OrValidationRule;
  var JsonObject = $module$json_schema_validator.org.validator.JsonObject;
  var throwCCE = Kotlin.throwCCE;
  var assertTrue = $module$kotlin_test.kotlin.test.assertTrue_ifx8ge$;
  var equals = Kotlin.equals;
  var rules = $module$json_schema_validator.org.validator.rules;
  var RuntimeException_init = Kotlin.kotlin.RuntimeException_init_pdl1vj$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var DefaultJsonScalar = $module$json_schema_validator.org.validator.DefaultJsonScalar;
  var first = Kotlin.kotlin.text.first_gw00vp$;
  var last = Kotlin.kotlin.text.last_gw00vp$;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Json = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.Json;
  var JsonObject_0 = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.JsonObject;
  var JsonArray = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.JsonArray;
  var JsonPrimitive = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.JsonPrimitive;
  var Pair = Kotlin.kotlin.Pair;
  var toMap = Kotlin.kotlin.collections.toMap_6hr0sd$;
  var DefaultJsonObject = $module$json_schema_validator.org.validator.DefaultJsonObject;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var DefaultJsonArray = $module$json_schema_validator.org.validator.DefaultJsonArray;
  var validator = $module$json_schema_validator.org.validator;
  var json = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json;
  var get_int = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_int_59esu7$;
  var get_intOrNull = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_intOrNull_59esu7$;
  var get_boolean = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_boolean_59esu7$;
  var get_booleanOrNull = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_booleanOrNull_59esu7$;
  var get_double = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_double_59esu7$;
  var get_doubleOrNull = $module$kotlinx_serialization_kotlinx_serialization_json_js_legacy.kotlinx.serialization.json.get_doubleOrNull_59esu7$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  function ConstRuleParserTest() {
  }
  ConstRuleParserTest.prototype.invalid_const_object = function () {
    this.test_const_rule_obj_0('/schemas/const/object-const.json', '/schemas/const/object-const.invalid-instance.json', false);
  };
  ConstRuleParserTest.prototype.valid_const_object = function () {
    this.test_const_rule_obj_0('/schemas/const/object-const.json', '/schemas/const/object-const.instance.json', true);
  };
  ConstRuleParserTest.prototype.invalid_const_string = function () {
    this.test_const_rule_0('/schemas/const/string-const.json', '/schemas/const/string-const.invalid-instance.json', false);
  };
  ConstRuleParserTest.prototype.valid_const_string = function () {
    this.test_const_rule_0('/schemas/const/string-const.json', '/schemas/const/string-const.instance.json', true);
  };
  ConstRuleParserTest.prototype.test_const_rule_obj_0 = function (schemaPath, payloadPath, valid) {
    var tmp$, tmp$_0;
    var schema = this.parseJson_61zpoe$(schemaPath);
    var payload = this.parseJson_61zpoe$(payloadPath);
    var rule = any.ConstRuleParser.parse_3boyfh$(schema);
    assertEquals((tmp$_0 = (tmp$ = rule.toRightValueOrNull()) != null ? tmp$.eval_vzh9da$(payload) : null) != null ? tmp$_0.isEmpty() : null, valid);
  };
  ConstRuleParserTest.prototype.test_const_rule_0 = function (schemaPath, payloadPath, valid) {
    var tmp$, tmp$_0;
    var schema = this.parseJson_61zpoe$(schemaPath);
    var payload = this.parseJsonScalar_61zpoe$(payloadPath);
    var rule = any.ConstRuleParser.parse_3boyfh$(schema);
    assertEquals((tmp$_0 = (tmp$ = rule.toRightValueOrNull()) != null ? tmp$.eval_vzh9da$(payload) : null) != null ? tmp$_0.isEmpty() : null, valid);
  };
  ConstRuleParserTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ConstRuleParserTest',
    interfaces: [JsonParsingHelper]
  };
  function TypeRuleParserTest() {
  }
  TypeRuleParserTest.prototype.parse_object_type = function () {
    this.test_rule_is_correct_0('/schemas/type/object-type.json', any.ObjectRule);
  };
  TypeRuleParserTest.prototype.parse_array_type = function () {
    this.test_rule_is_correct_0('/schemas/type/array-type.json', any.ArrayRule);
  };
  TypeRuleParserTest.prototype.parse_string_type = function () {
    this.test_rule_is_correct_0('/schemas/type/string-type.json', any.StringRule);
  };
  TypeRuleParserTest.prototype.parse_number_type = function () {
    this.test_rule_is_correct_0('/schemas/type/number-type.json', any.NumberRule);
  };
  TypeRuleParserTest.prototype.parse_boolean_type = function () {
    this.test_rule_is_correct_0('/schemas/type/boolean-type.json', any.BooleanRule);
  };
  TypeRuleParserTest.prototype.parse_null_type = function () {
    this.test_rule_is_correct_0('/schemas/type/null-type.json', any.NullRule);
  };
  TypeRuleParserTest.prototype.parse_array_of_types = function () {
    this.test_rule_is_correct_0('/schemas/type/array-of-types.json', new OrValidationRule(listOf([any.StringRule, any.NumberRule])));
  };
  TypeRuleParserTest.prototype.test_rule_is_correct_0 = function (file, expected) {
    var tmp$;
    var schemaAsString = loadResourceText(file);
    var schema = Kotlin.isType(tmp$ = adapt(schemaAsString), JsonObject) ? tmp$ : throwCCE();
    var rule = any.TypeRuleParser.parse_3boyfh$(schema);
    assertEquals(rule.toLeftValueOrNull(), null);
    if (equals(rule.toRightValueOrNull(), expected))
      assertTrue(true);
    else
      assertTrue(false);
  };
  TypeRuleParserTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TypeRuleParserTest',
    interfaces: []
  };
  function TypeRuleTest() {
  }
  TypeRuleTest.prototype.test_valid_object = function () {
    this.test_rule_validates_correctly_0('/schemas/type/object-type.json', '/schemas/type/object-type.instance.json', true);
  };
  TypeRuleTest.prototype.test_rule_validates_correctly_0 = function (schemaFile, payloadFile, expected) {
    var tmp$, tmp$_0;
    var schema = this.parseJson_61zpoe$(schemaFile);
    var payload = this.parseJson_61zpoe$(payloadFile);
    var rule = any.TypeRuleParser.parse_3boyfh$(schema);
    assertEquals((tmp$_0 = (tmp$ = rule.toRightValueOrNull()) != null ? tmp$.eval_vzh9da$(payload) : null) != null ? tmp$_0.isEmpty() : null, expected);
  };
  TypeRuleTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TypeRuleTest',
    interfaces: [JsonParsingHelper]
  };
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
    assertEquals(maybeRule.toLeftValueOrNull(), null);
    var rule = ensureNotNull(maybeRule.toRightValueOrNull());
    var payloadAsObj = adapt(payloadAsString);
    var results = rule.eval_vzh9da$(payloadAsObj);
    assertTrue(results.isEmpty() === conforms, null);
  }
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
  AnyOfTest.prototype.anyOf_with_boolean_schemas__all_true = function () {
    var schema = '{"anyOf":[true,true]}';
    var any_value_is_valid = '"foo"';
    run_test(schema, any_value_is_valid, true, 'DRAFT_4');
  };
  AnyOfTest.prototype.anyOf_with_boolean_schemas__some_true = function () {
    var schema = '{"anyOf":[true,false]}';
    var any_value_is_valid = '"foo"';
    run_test(schema, any_value_is_valid, true, 'DRAFT_4');
  };
  AnyOfTest.prototype.anyOf_with_boolean_schemas__all_false = function () {
    var schema = '{"anyOf":[false,false]}';
    var any_value_is_invalid = '"foo"';
    run_test(schema, any_value_is_invalid, false, 'DRAFT_4');
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
  function JsonParsingHelper() {
  }
  JsonParsingHelper.prototype.parseJson_61zpoe$ = function (schemaFile) {
    var tmp$;
    var schemaAsString = loadResourceText(schemaFile);
    return Kotlin.isType(tmp$ = adapt(schemaAsString), JsonObject) ? tmp$ : throwCCE();
  };
  JsonParsingHelper.prototype.parseJsonScalar_61zpoe$ = function (schemaFile) {
    var schemaAsString = loadResourceText(schemaFile);
    return new DefaultJsonScalar(this.removeLeadingEscapedQuotes_gf9z08$_0(schemaAsString.trim()));
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
      if (get_intOrNull(scalar) != null)
        tmp$ = new DefaultJsonScalar(get_int(scalar));
      else if (get_booleanOrNull(scalar) != null)
        tmp$ = new DefaultJsonScalar(get_boolean(scalar));
      else if (get_doubleOrNull(scalar) != null)
        tmp$ = new DefaultJsonScalar(get_double(scalar));
      else
        tmp$ = new DefaultJsonScalar(scalar.content);
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
  package$validator.ConstRuleParserTest = ConstRuleParserTest;
  package$validator.TypeRuleParserTest = TypeRuleParserTest;
  package$validator.TypeRuleTest = TypeRuleTest;
  var package$tck = package$validator.tck || (package$validator.tck = {});
  package$tck.provideParser_61zpoe$ = provideParser;
  $$importsForInline$$['kotlin-test'] = $module$kotlin_test;
  package$tck.run_test_f372nr$ = run_test;
  var package$draft4 = package$tck.draft4 || (package$tck.draft4 = {});
  package$draft4.AnyOfTest = AnyOfTest;
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
  ConstRuleParserTest.prototype.parseJson_61zpoe$ = JsonParsingHelper.prototype.parseJson_61zpoe$;
  ConstRuleParserTest.prototype.parseJsonScalar_61zpoe$ = JsonParsingHelper.prototype.parseJsonScalar_61zpoe$;
  ConstRuleParserTest.prototype.removeLeadingEscapedQuotes_gf9z08$_0 = JsonParsingHelper.prototype.removeLeadingEscapedQuotes_gf9z08$_0;
  TypeRuleTest.prototype.parseJson_61zpoe$ = JsonParsingHelper.prototype.parseJson_61zpoe$;
  TypeRuleTest.prototype.parseJsonScalar_61zpoe$ = JsonParsingHelper.prototype.parseJsonScalar_61zpoe$;
  TypeRuleTest.prototype.removeLeadingEscapedQuotes_gf9z08$_0 = JsonParsingHelper.prototype.removeLeadingEscapedQuotes_gf9z08$_0;
  fs = require('fs');
  jsPath = require('path');
  suite('org.validator', false, function () {
    suite('ConstRuleParserTest', false, function () {
      test('invalid_const_object', false, function () {
        return (new ConstRuleParserTest()).invalid_const_object();
      });
      test('valid_const_object', false, function () {
        return (new ConstRuleParserTest()).valid_const_object();
      });
      test('invalid_const_string', false, function () {
        return (new ConstRuleParserTest()).invalid_const_string();
      });
      test('valid_const_string', false, function () {
        return (new ConstRuleParserTest()).valid_const_string();
      });
    });
    suite('TypeRuleParserTest', false, function () {
      test('parse_object_type', false, function () {
        return (new TypeRuleParserTest()).parse_object_type();
      });
      test('parse_array_type', false, function () {
        return (new TypeRuleParserTest()).parse_array_type();
      });
      test('parse_string_type', false, function () {
        return (new TypeRuleParserTest()).parse_string_type();
      });
      test('parse_number_type', false, function () {
        return (new TypeRuleParserTest()).parse_number_type();
      });
      test('parse_boolean_type', false, function () {
        return (new TypeRuleParserTest()).parse_boolean_type();
      });
      test('parse_null_type', false, function () {
        return (new TypeRuleParserTest()).parse_null_type();
      });
      test('parse_array_of_types', false, function () {
        return (new TypeRuleParserTest()).parse_array_of_types();
      });
    });
    suite('TypeRuleTest', false, function () {
      test('test_valid_object', false, function () {
        return (new TypeRuleTest()).test_valid_object();
      });
    });
  });
  suite('org.validator.tck.draft4', false, function () {
    suite('AnyOfTest', false, function () {
      test('anyOf', false, function () {
        return (new AnyOfTest()).anyOf();
      });
      test('anyOf_with_base_schema', false, function () {
        return (new AnyOfTest()).anyOf_with_base_schema();
      });
      test('anyOf_with_boolean_schemas__all_true', false, function () {
        return (new AnyOfTest()).anyOf_with_boolean_schemas__all_true();
      });
      test('anyOf_with_boolean_schemas__some_true', false, function () {
        return (new AnyOfTest()).anyOf_with_boolean_schemas__some_true();
      });
      test('anyOf_with_boolean_schemas__all_false', false, function () {
        return (new AnyOfTest()).anyOf_with_boolean_schemas__all_false();
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
  });
  suite('org.validator.testing', false, function () {
    suite('PlatformLoaderTest', false, function () {
      test('can_load_resource_file', false, function () {
        return (new PlatformLoaderTest()).can_load_resource_file();
      });
    });
  });
  Kotlin.defineModule('json-schema-validator-test', _);
  return _;
}));

//# sourceMappingURL=json-schema-validator-test.js.map
