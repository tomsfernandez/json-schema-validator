(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'json-schema-validator'.");
    }root['json-schema-validator'] = factory(typeof this['json-schema-validator'] === 'undefined' ? {} : this['json-schema-validator'], kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var equals = Kotlin.equals;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var zip = Kotlin.kotlin.collections.zip_45mdf7$;
  var getOrNull = Kotlin.kotlin.collections.getOrNull_yzln2o$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var throwCCE = Kotlin.throwCCE;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var Pair_init = Kotlin.kotlin.Pair;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var flatten = Kotlin.kotlin.collections.flatten_u0ad8z$;
  var Collection = Kotlin.kotlin.collections.Collection;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var getCallableRef = Kotlin.getCallableRef;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_qfcya0$;
  var numberToInt = Kotlin.numberToInt;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var numberToDouble = Kotlin.numberToDouble;
  var subtract = Kotlin.kotlin.collections.subtract_q4559j$;
  Either$Left.prototype = Object.create(Either.prototype);
  Either$Left.prototype.constructor = Either$Left;
  Either$Right.prototype = Object.create(Either.prototype);
  Either$Right.prototype.constructor = Either$Right;
  AllOfRuleParser.prototype = Object.create(ArrayOfRuleParser.prototype);
  AllOfRuleParser.prototype.constructor = AllOfRuleParser;
  AnyOfRuleParser.prototype = Object.create(ArrayOfRuleParser.prototype);
  AnyOfRuleParser.prototype.constructor = AnyOfRuleParser;
  OneOfRuleParser.prototype = Object.create(ArrayOfRuleParser.prototype);
  OneOfRuleParser.prototype.constructor = OneOfRuleParser;
  MaxPropertiesRuleParser.prototype = Object.create(PropertyAmountParser.prototype);
  MaxPropertiesRuleParser.prototype.constructor = MaxPropertiesRuleParser;
  MinPropertiesRuleParser.prototype = Object.create(PropertyAmountParser.prototype);
  MinPropertiesRuleParser.prototype.constructor = MinPropertiesRuleParser;
  function JsonElement() {
  }
  JsonElement.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'JsonElement',
    interfaces: []
  };
  function asObject($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, JsonObject))
      tmp$ = new Either$Right($receiver);
    else
      tmp$ = new Either$Left(new Error_0('Element is not an object'));
    return tmp$;
  }
  function asScalar($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, JsonScalar))
      tmp$ = new Either$Right($receiver);
    else
      tmp$ = new Either$Left(new Error_0('Element is not a scalar'));
    return tmp$;
  }
  function asArray($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, JsonArray))
      tmp$ = new Either$Right($receiver);
    else
      tmp$ = new Either$Left(new Error_0('Element it not an array'));
    return tmp$;
  }
  function asNull($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, NullElement))
      tmp$ = new Either$Right($receiver);
    else
      tmp$ = new Either$Left(new Error_0('Element it not null'));
    return tmp$;
  }
  function JsonObject() {
  }
  JsonObject.prototype.deepEquals_vzh9da$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, JsonObject))
      tmp$ = (this.hasSameKeys_xksyj8$_0(element) && this.hasSameChildren_mbcpqv$_0(element));
    else
      tmp$ = false;
    return tmp$;
  };
  JsonObject.prototype.hasSameChildren_mbcpqv$_0 = function (element) {
    var $receiver = element.entries();
    var tmp$;
    var list = ArrayList_init();
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var tmp$_0;
      var value = this.get_61zpoe$(item.first);
      if (!((tmp$_0 = value != null ? value.deepEquals_vzh9da$(item.second) : null) != null ? tmp$_0 : false))
        break;
      list.add_11rb$(item);
    }
    var equalElements = list;
    return element.keys().size === equalElements.size;
  };
  JsonObject.prototype.hasSameKeys_xksyj8$_0 = function (element) {
    return equals(element.keys(), this.keys());
  };
  JsonObject.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'JsonObject',
    interfaces: [JsonElement]
  };
  function DefaultJsonObject(map) {
    this.map = map;
  }
  DefaultJsonObject.prototype.get_61zpoe$ = function (name) {
    return this.map.get_11rb$(name);
  };
  DefaultJsonObject.prototype.entries = function () {
    var $receiver = this.map.entries;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new Pair_init(item.key, item.value));
    }
    return destination;
  };
  DefaultJsonObject.prototype.keys = function () {
    return this.map.keys;
  };
  DefaultJsonObject.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultJsonObject',
    interfaces: [JsonObject]
  };
  DefaultJsonObject.prototype.component1 = function () {
    return this.map;
  };
  DefaultJsonObject.prototype.copy_cmt3if$ = function (map) {
    return new DefaultJsonObject(map === void 0 ? this.map : map);
  };
  DefaultJsonObject.prototype.toString = function () {
    return 'DefaultJsonObject(map=' + Kotlin.toString(this.map) + ')';
  };
  DefaultJsonObject.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.map) | 0;
    return result;
  };
  DefaultJsonObject.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.map, other.map))));
  };
  function JsonArray() {
  }
  JsonArray.prototype.deepEquals_vzh9da$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, JsonArray))
      tmp$ = this.hasSameElements_hlw545$_0(element);
    else
      tmp$ = false;
    return tmp$;
  };
  JsonArray.prototype.hasSameElements_hlw545$_0 = function (array) {
    var $receiver = zip(array.elements(), this.elements());
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.first.deepEquals_vzh9da$(item.second));
    }
    var tmp$_0;
    var accumulator = true;
    tmp$_0 = destination.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      accumulator = (accumulator && element);
    }
    return accumulator;
  };
  JsonArray.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'JsonArray',
    interfaces: [JsonElement]
  };
  function DefaultJsonArray(elements) {
    this.elements_0 = elements;
  }
  DefaultJsonArray.prototype.get_za3lpa$ = function (index) {
    return getOrNull(this.elements_0, index);
  };
  DefaultJsonArray.prototype.elements = function () {
    return this.elements_0;
  };
  DefaultJsonArray.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultJsonArray',
    interfaces: [JsonArray]
  };
  DefaultJsonArray.prototype.component1_0 = function () {
    return this.elements_0;
  };
  DefaultJsonArray.prototype.copy_4nq5dv$ = function (elements) {
    return new DefaultJsonArray(elements === void 0 ? this.elements_0 : elements);
  };
  DefaultJsonArray.prototype.toString = function () {
    return 'DefaultJsonArray(elements=' + Kotlin.toString(this.elements_0) + ')';
  };
  DefaultJsonArray.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.elements_0) | 0;
    return result;
  };
  DefaultJsonArray.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.elements_0, other.elements_0))));
  };
  function NullElement() {
  }
  NullElement.prototype.deepEquals_vzh9da$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, NullElement))
      tmp$ = true;
    else
      tmp$ = false;
    return tmp$;
  };
  NullElement.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NullElement',
    interfaces: [JsonElement]
  };
  function DefaultNullElement() {
    DefaultNullElement_instance = this;
  }
  DefaultNullElement.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DefaultNullElement',
    interfaces: [NullElement]
  };
  var DefaultNullElement_instance = null;
  function DefaultNullElement_getInstance() {
    if (DefaultNullElement_instance === null) {
      new DefaultNullElement();
    }return DefaultNullElement_instance;
  }
  function asString($receiver) {
    var tmp$, tmp$_0;
    if (typeof $receiver.value === 'string') {
      tmp$_0 = new Either$Right(typeof (tmp$ = $receiver.value) === 'string' ? tmp$ : throwCCE());
    } else
      tmp$_0 = new Either$Left(new Error_0('Element is not a string'));
    return tmp$_0;
  }
  function asBoolean($receiver) {
    var tmp$, tmp$_0;
    if (typeof $receiver.value === 'boolean') {
      tmp$_0 = new Either$Right(typeof (tmp$ = $receiver.value) === 'boolean' ? tmp$ : throwCCE());
    } else
      tmp$_0 = new Either$Left(new Error_0('Element is not a boolean'));
    return tmp$_0;
  }
  function asNumber($receiver) {
    var tmp$, tmp$_0;
    if (Kotlin.isNumber($receiver.value)) {
      tmp$_0 = new Either$Right(Kotlin.isNumber(tmp$ = $receiver.value) ? tmp$ : throwCCE());
    } else
      tmp$_0 = new Either$Left(new Error_0('Element is not a number'));
    return tmp$_0;
  }
  function JsonScalar() {
  }
  JsonScalar.prototype.deepEquals_vzh9da$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, JsonScalar))
      tmp$ = equals(element.value, this.value);
    else
      tmp$ = false;
    return tmp$;
  };
  JsonScalar.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'JsonScalar',
    interfaces: [JsonElement]
  };
  function DefaultJsonScalar(value) {
    this.value_254tig$_0 = value;
  }
  Object.defineProperty(DefaultJsonScalar.prototype, 'value', {
    get: function () {
      return this.value_254tig$_0;
    }
  });
  DefaultJsonScalar.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultJsonScalar',
    interfaces: [JsonScalar]
  };
  DefaultJsonScalar.prototype.component1 = function () {
    return this.value;
  };
  DefaultJsonScalar.prototype.copy_za3rmp$ = function (value) {
    return new DefaultJsonScalar(value === void 0 ? this.value : value);
  };
  DefaultJsonScalar.prototype.toString = function () {
    return 'DefaultJsonScalar(value=' + Kotlin.toString(this.value) + ')';
  };
  DefaultJsonScalar.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    return result;
  };
  DefaultJsonScalar.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.value, other.value))));
  };
  function identity(t) {
    return t;
  }
  function Either() {
  }
  function Either$Left(l) {
    Either.call(this);
    this.l = l;
  }
  Either$Left.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Left',
    interfaces: [Either]
  };
  Either$Left.prototype.component1 = function () {
    return this.l;
  };
  Either$Left.prototype.copy_11rb$ = function (l) {
    return new Either$Left(l === void 0 ? this.l : l);
  };
  Either$Left.prototype.toString = function () {
    return 'Left(l=' + Kotlin.toString(this.l) + ')';
  };
  Either$Left.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.l) | 0;
    return result;
  };
  Either$Left.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.l, other.l))));
  };
  function Either$Right(r) {
    Either.call(this);
    this.r = r;
  }
  Either$Right.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Right',
    interfaces: [Either]
  };
  Either$Right.prototype.component1 = function () {
    return this.r;
  };
  Either$Right.prototype.copy_11rb$ = function (r) {
    return new Either$Right(r === void 0 ? this.r : r);
  };
  Either$Right.prototype.toString = function () {
    return 'Right(r=' + Kotlin.toString(this.r) + ')';
  };
  Either$Right.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.r) | 0;
    return result;
  };
  Either$Right.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.r, other.r))));
  };
  Either.prototype.fold_hfmbsx$ = function (fnL, fnR) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = fnL(this.l);
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = fnR(this.r);
    else
      tmp$ = Kotlin.noWhenBranchMatched();
    return tmp$;
  };
  Either.prototype.foldRight_r1ursk$ = function (fn) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = this;
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = new Either$Right(fn(this.r));
    else
      tmp$ = Kotlin.noWhenBranchMatched();
    return tmp$;
  };
  Either.prototype.map_ik7j40$ = function (fnL, fnR) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = new Either$Left(fnL(this.l));
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = fnR(this.r);
    else
      tmp$ = Kotlin.noWhenBranchMatched();
    return tmp$;
  };
  Either.prototype.map_r1ursk$ = function (fn) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = this;
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = new Either$Right(fn(this.r));
    else
      tmp$ = Kotlin.noWhenBranchMatched();
    return tmp$;
  };
  Either.prototype.toLeftValueOrNull = function () {
    if (Kotlin.isType(this, Either$Left))
      return this.l;
    else if (Kotlin.isType(this, Either$Right))
      return null;
    else
      return Kotlin.noWhenBranchMatched();
  };
  Either.prototype.toRightValueOrNull = function () {
    if (Kotlin.isType(this, Either$Left))
      return null;
    else if (Kotlin.isType(this, Either$Right))
      return this.r;
    else
      return Kotlin.noWhenBranchMatched();
  };
  Either.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Either',
    interfaces: []
  };
  function ValidationRule() {
  }
  ValidationRule.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ValidationRule',
    interfaces: []
  };
  function RuleParser() {
  }
  RuleParser.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RuleParser',
    interfaces: []
  };
  function NothingValidationRule() {
    NothingValidationRule_instance = this;
  }
  NothingValidationRule.prototype.eval_vzh9da$ = function (element) {
    return emptyList();
  };
  NothingValidationRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NothingValidationRule',
    interfaces: [ValidationRule]
  };
  var NothingValidationRule_instance = null;
  function NothingValidationRule_getInstance() {
    if (NothingValidationRule_instance === null) {
      new NothingValidationRule();
    }return NothingValidationRule_instance;
  }
  function OrValidationRule(rules) {
    this.rules = rules;
  }
  OrValidationRule.prototype.eval_vzh9da$ = function (element) {
    var $receiver = this.rules;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.eval_vzh9da$(element));
    }
    var errors = destination;
    var any$result;
    any$break: do {
      var tmp$_0;
      if (Kotlin.isType(errors, Collection) && errors.isEmpty()) {
        any$result = false;
        break any$break;
      }tmp$_0 = errors.iterator();
      while (tmp$_0.hasNext()) {
        var element_0 = tmp$_0.next();
        if (element_0.isEmpty()) {
          any$result = true;
          break any$break;
        }}
      any$result = false;
    }
     while (false);
    var atLeastOneValid = any$result;
    return atLeastOneValid ? emptyList() : flatten(errors);
  };
  OrValidationRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OrValidationRule',
    interfaces: [ValidationRule]
  };
  OrValidationRule.prototype.component1 = function () {
    return this.rules;
  };
  OrValidationRule.prototype.copy_6vpe0g$ = function (rules) {
    return new OrValidationRule(rules === void 0 ? this.rules : rules);
  };
  OrValidationRule.prototype.toString = function () {
    return 'OrValidationRule(rules=' + Kotlin.toString(this.rules) + ')';
  };
  OrValidationRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rules) | 0;
    return result;
  };
  OrValidationRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rules, other.rules))));
  };
  function Error_0(reason) {
    this.reason = reason;
  }
  Error_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Error',
    interfaces: []
  };
  Error_0.prototype.component1 = function () {
    return this.reason;
  };
  Error_0.prototype.copy_61zpoe$ = function (reason) {
    return new Error_0(reason === void 0 ? this.reason : reason);
  };
  Error_0.prototype.toString = function () {
    return 'Error(reason=' + Kotlin.toString(this.reason) + ')';
  };
  Error_0.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.reason) | 0;
    return result;
  };
  Error_0.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.reason, other.reason))));
  };
  function DraftParsers() {
    DraftParsers_instance = this;
    this.DRAFT_4 = new SchemaRuleParser(listOf([ConstRuleParser_getInstance(), EnumRuleParser_getInstance(), TypeRuleParser_getInstance(), AllOfRuleParser_getInstance(), new AnyOfRuleParser(Draft4ParserFactory_getInstance()), new NotRuleParser(Draft4ParserFactory_getInstance()), OneOfRuleParser_getInstance(), new IfThenElseRuleParser(Draft4ParserFactory_getInstance()), ExclusiveMaximumRuleParser_getInstance(), ExclusiveMinimumRuleParser_getInstance(), MinimumRuleParser_getInstance(), MaximumRuleParser_getInstance(), RequiredRuleParser_getInstance()]));
  }
  DraftParsers.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DraftParsers',
    interfaces: []
  };
  var DraftParsers_instance = null;
  function DraftParsers_getInstance() {
    if (DraftParsers_instance === null) {
      new DraftParsers();
    }return DraftParsers_instance;
  }
  function SchemaRuleParserFactory() {
  }
  SchemaRuleParserFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SchemaRuleParserFactory',
    interfaces: []
  };
  function Draft4ParserFactory() {
    Draft4ParserFactory_instance = this;
  }
  Draft4ParserFactory.prototype.make = function () {
    return DraftParsers_getInstance().DRAFT_4;
  };
  Draft4ParserFactory.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Draft4ParserFactory',
    interfaces: [SchemaRuleParserFactory]
  };
  var Draft4ParserFactory_instance = null;
  function Draft4ParserFactory_getInstance() {
    if (Draft4ParserFactory_instance === null) {
      new Draft4ParserFactory();
    }return Draft4ParserFactory_instance;
  }
  function SchemaRuleParser(parsers) {
    this.parsers = parsers;
  }
  SchemaRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return true;
  };
  SchemaRuleParser.prototype.parse_3boyfh$ = function (element) {
    var $receiver = this.parsers;
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element_0 = tmp$.next();
      if (element_0.canParse_3boyfh$(element))
        destination.add_11rb$(element_0);
    }
    var applicableParsers = destination;
    var destination_0 = ArrayList_init_0(collectionSizeOrDefault(applicableParsers, 10));
    var tmp$_0;
    tmp$_0 = applicableParsers.iterator();
    while (tmp$_0.hasNext()) {
      var item = tmp$_0.next();
      destination_0.add_11rb$(item.parse_3boyfh$(element));
    }
    var eitherList = destination_0;
    var destination_1 = ArrayList_init();
    var tmp$_1;
    tmp$_1 = eitherList.iterator();
    while (tmp$_1.hasNext()) {
      var element_1 = tmp$_1.next();
      var tmp$_0_0;
      if ((tmp$_0_0 = element_1.toLeftValueOrNull()) != null) {
        destination_1.add_11rb$(tmp$_0_0);
      }}
    var errors = flatten(destination_1);
    var destination_2 = ArrayList_init();
    var tmp$_2;
    tmp$_2 = eitherList.iterator();
    while (tmp$_2.hasNext()) {
      var element_2 = tmp$_2.next();
      var tmp$_0_1;
      if ((tmp$_0_1 = element_2.toRightValueOrNull()) != null) {
        destination_2.add_11rb$(tmp$_0_1);
      }}
    var schemas = destination_2;
    return !errors.isEmpty() ? new Either$Left(errors) : new Either$Right(new SchemaRule(schemas));
  };
  SchemaRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SchemaRuleParser',
    interfaces: [RuleParser]
  };
  SchemaRuleParser.prototype.component1 = function () {
    return this.parsers;
  };
  SchemaRuleParser.prototype.copy_apf80a$ = function (parsers) {
    return new SchemaRuleParser(parsers === void 0 ? this.parsers : parsers);
  };
  SchemaRuleParser.prototype.toString = function () {
    return 'SchemaRuleParser(parsers=' + Kotlin.toString(this.parsers) + ')';
  };
  SchemaRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.parsers) | 0;
    return result;
  };
  SchemaRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.parsers, other.parsers))));
  };
  function SchemaRule(rules) {
    this.rules = rules;
  }
  SchemaRule.prototype.eval_vzh9da$ = function (element) {
    var $receiver = this.rules;
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element_0 = tmp$.next();
      var list = element_0.eval_vzh9da$(element);
      addAll(destination, list);
    }
    return destination;
  };
  SchemaRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SchemaRule',
    interfaces: [ValidationRule]
  };
  SchemaRule.prototype.component1 = function () {
    return this.rules;
  };
  SchemaRule.prototype.copy_6vpe0g$ = function (rules) {
    return new SchemaRule(rules === void 0 ? this.rules : rules);
  };
  SchemaRule.prototype.toString = function () {
    return 'SchemaRule(rules=' + Kotlin.toString(this.rules) + ')';
  };
  SchemaRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rules) | 0;
    return result;
  };
  SchemaRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rules, other.rules))));
  };
  function ConstRuleParser() {
    ConstRuleParser_instance = this;
  }
  ConstRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$('const') != null;
  };
  ConstRuleParser.prototype.parse_3boyfh$ = function (element) {
    var constElement = ensureNotNull(element.get_61zpoe$('const'));
    return new Either$Right(new ConstRule(constElement));
  };
  ConstRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ConstRuleParser',
    interfaces: [RuleParser]
  };
  var ConstRuleParser_instance = null;
  function ConstRuleParser_getInstance() {
    if (ConstRuleParser_instance === null) {
      new ConstRuleParser();
    }return ConstRuleParser_instance;
  }
  function ConstRule(toCompare) {
    this.toCompare = toCompare;
  }
  ConstRule.prototype.eval_vzh9da$ = function (element) {
    return !this.toCompare.deepEquals_vzh9da$(element) ? listOf_0(new Error_0('Schemas are not equal')) : emptyList();
  };
  ConstRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ConstRule',
    interfaces: [ValidationRule]
  };
  ConstRule.prototype.component1 = function () {
    return this.toCompare;
  };
  ConstRule.prototype.copy_vzh9da$ = function (toCompare) {
    return new ConstRule(toCompare === void 0 ? this.toCompare : toCompare);
  };
  ConstRule.prototype.toString = function () {
    return 'ConstRule(toCompare=' + Kotlin.toString(this.toCompare) + ')';
  };
  ConstRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.toCompare) | 0;
    return result;
  };
  ConstRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.toCompare, other.toCompare))));
  };
  function EnumRuleParser() {
    EnumRuleParser_instance = this;
  }
  EnumRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$('enum') != null;
  };
  function EnumRuleParser$parse$lambda(array) {
    return new Either$Right(new EnumRule(array.elements()));
  }
  EnumRuleParser.prototype.parse_3boyfh$ = function (element) {
    return asArray(element.get_61zpoe$('enum')).map_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), EnumRuleParser$parse$lambda);
  };
  EnumRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EnumRuleParser',
    interfaces: [RuleParser]
  };
  var EnumRuleParser_instance = null;
  function EnumRuleParser_getInstance() {
    if (EnumRuleParser_instance === null) {
      new EnumRuleParser();
    }return EnumRuleParser_instance;
  }
  function EnumRule(toCompare) {
    this.toCompare = toCompare;
  }
  EnumRule.prototype.eval_vzh9da$ = function (element) {
    var $receiver = this.toCompare;
    var any$result;
    any$break: do {
      var tmp$;
      if (Kotlin.isType($receiver, Collection) && $receiver.isEmpty()) {
        any$result = false;
        break any$break;
      }tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element_0 = tmp$.next();
        if (element_0.deepEquals_vzh9da$(element)) {
          any$result = true;
          break any$break;
        }}
      any$result = false;
    }
     while (false);
    var equalsAny = any$result;
    return !equalsAny ? listOf_0(new Error_0("Schema doesn't equal any value in enum")) : emptyList();
  };
  EnumRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EnumRule',
    interfaces: [ValidationRule]
  };
  EnumRule.prototype.component1 = function () {
    return this.toCompare;
  };
  EnumRule.prototype.copy_4nq5dv$ = function (toCompare) {
    return new EnumRule(toCompare === void 0 ? this.toCompare : toCompare);
  };
  EnumRule.prototype.toString = function () {
    return 'EnumRule(toCompare=' + Kotlin.toString(this.toCompare) + ')';
  };
  EnumRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.toCompare) | 0;
    return result;
  };
  EnumRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.toCompare, other.toCompare))));
  };
  function TypeRuleParser() {
    TypeRuleParser_instance = this;
    this.ruleMap_0 = mapOf([to('array', ArrayRule_getInstance()), to('object', ObjectRule_getInstance()), to('null', NullRule_getInstance()), to('string', StringRule_getInstance()), to('number', NumberRule_getInstance()), to('boolean', BooleanRule_getInstance()), to('integer', IntegerRule_getInstance())]);
  }
  TypeRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$('type') != null;
  };
  function TypeRuleParser$parse$lambda(x) {
    return new Either$Right(x);
  }
  TypeRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var typeEntry = element.get_61zpoe$('type');
    if (Kotlin.isType(typeEntry, JsonArray))
      tmp$ = this.parseArrayElements_0(typeEntry);
    else if (Kotlin.isType(typeEntry, JsonScalar))
      tmp$ = this.parseScalarElement_0(typeEntry).map_ik7j40$(getCallableRef('listOf', function (p1) {
        return listOf_0(p1);
      }), TypeRuleParser$parse$lambda);
    else
      tmp$ = new Either$Left(listOf_0(new Error_0('Element is neither an array or a scalar')));
    return tmp$;
  };
  TypeRuleParser.prototype.parseArrayElements_0 = function (array) {
    var tmp$;
    var $receiver = array.elements();
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$_0;
    tmp$_0 = $receiver.iterator();
    while (tmp$_0.hasNext()) {
      var item = tmp$_0.next();
      destination.add_11rb$(this.parseScalarElement_1(item));
    }
    var schemaOrErrors = destination;
    var destination_0 = ArrayList_init();
    var tmp$_1;
    tmp$_1 = schemaOrErrors.iterator();
    while (tmp$_1.hasNext()) {
      var element = tmp$_1.next();
      var tmp$_0_0;
      if ((tmp$_0_0 = element.toLeftValueOrNull()) != null) {
        destination_0.add_11rb$(tmp$_0_0);
      }}
    var errors = destination_0;
    if (errors.isEmpty()) {
      var destination_1 = ArrayList_init();
      var tmp$_2;
      tmp$_2 = schemaOrErrors.iterator();
      while (tmp$_2.hasNext()) {
        var element_0 = tmp$_2.next();
        var tmp$_0_1;
        if ((tmp$_0_1 = element_0.toRightValueOrNull()) != null) {
          destination_1.add_11rb$(tmp$_0_1);
        }}
      var rules = new OrValidationRule(destination_1);
      tmp$ = new Either$Right(rules);
    } else
      tmp$ = new Either$Left(errors);
    return tmp$;
  };
  function TypeRuleParser$parseScalarElement$lambda(this$TypeRuleParser) {
    return function (x) {
      return this$TypeRuleParser.parseScalarElement_0(x);
    };
  }
  TypeRuleParser.prototype.parseScalarElement_1 = function (element) {
    return asScalar(element).map_ik7j40$(getCallableRef('identity', function (p1) {
      return identity(p1);
    }), TypeRuleParser$parseScalarElement$lambda(this));
  };
  function TypeRuleParser$parseScalarElement$lambda_0(x) {
    return new Either$Left(x);
  }
  function TypeRuleParser$parseScalarElement$lambda_1(this$TypeRuleParser) {
    return function (x) {
      var value = this$TypeRuleParser.ruleMap_0.get_11rb$(x);
      return value != null ? new Either$Right(value) : new Either$Left(new Error_0(x + ' is not amount allowed values'));
    };
  }
  TypeRuleParser.prototype.parseScalarElement_0 = function (scalar) {
    return asString(scalar).fold_hfmbsx$(TypeRuleParser$parseScalarElement$lambda_0, TypeRuleParser$parseScalarElement$lambda_1(this));
  };
  TypeRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'TypeRuleParser',
    interfaces: [RuleParser]
  };
  var TypeRuleParser_instance = null;
  function TypeRuleParser_getInstance() {
    if (TypeRuleParser_instance === null) {
      new TypeRuleParser();
    }return TypeRuleParser_instance;
  }
  function ObjectRule() {
    ObjectRule_instance = this;
  }
  function ObjectRule$eval$lambda(it) {
    return emptyList();
  }
  ObjectRule.prototype.eval_vzh9da$ = function (element) {
    return asObject(element).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), ObjectRule$eval$lambda);
  };
  ObjectRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ObjectRule',
    interfaces: [ValidationRule]
  };
  var ObjectRule_instance = null;
  function ObjectRule_getInstance() {
    if (ObjectRule_instance === null) {
      new ObjectRule();
    }return ObjectRule_instance;
  }
  function ArrayRule() {
    ArrayRule_instance = this;
  }
  function ArrayRule$eval$lambda(it) {
    return emptyList();
  }
  ArrayRule.prototype.eval_vzh9da$ = function (element) {
    return asArray(element).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), ArrayRule$eval$lambda);
  };
  ArrayRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ArrayRule',
    interfaces: [ValidationRule]
  };
  var ArrayRule_instance = null;
  function ArrayRule_getInstance() {
    if (ArrayRule_instance === null) {
      new ArrayRule();
    }return ArrayRule_instance;
  }
  function StringRule() {
    StringRule_instance = this;
  }
  function StringRule$eval$lambda(this$StringRule) {
    return function (x) {
      return this$StringRule.evalString_0(x);
    };
  }
  StringRule.prototype.eval_vzh9da$ = function (element) {
    return asScalar(element).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), StringRule$eval$lambda(this));
  };
  function StringRule$evalString$lambda(it) {
    return emptyList();
  }
  StringRule.prototype.evalString_0 = function (scalar) {
    return asString(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), StringRule$evalString$lambda);
  };
  StringRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'StringRule',
    interfaces: [ValidationRule]
  };
  var StringRule_instance = null;
  function StringRule_getInstance() {
    if (StringRule_instance === null) {
      new StringRule();
    }return StringRule_instance;
  }
  function BooleanRule() {
    BooleanRule_instance = this;
  }
  function BooleanRule$eval$lambda(this$BooleanRule) {
    return function (x) {
      return this$BooleanRule.evalBoolean_0(x);
    };
  }
  BooleanRule.prototype.eval_vzh9da$ = function (element) {
    return asScalar(element).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), BooleanRule$eval$lambda(this));
  };
  function BooleanRule$evalBoolean$lambda(it) {
    return emptyList();
  }
  BooleanRule.prototype.evalBoolean_0 = function (scalar) {
    return asBoolean(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), BooleanRule$evalBoolean$lambda);
  };
  BooleanRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BooleanRule',
    interfaces: [ValidationRule]
  };
  var BooleanRule_instance = null;
  function BooleanRule_getInstance() {
    if (BooleanRule_instance === null) {
      new BooleanRule();
    }return BooleanRule_instance;
  }
  function NumberRule() {
    NumberRule_instance = this;
  }
  function NumberRule$eval$lambda(this$NumberRule) {
    return function (x) {
      return this$NumberRule.evalNumber_0(x);
    };
  }
  NumberRule.prototype.eval_vzh9da$ = function (element) {
    return asScalar(element).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), NumberRule$eval$lambda(this));
  };
  function NumberRule$evalNumber$lambda(it) {
    return emptyList();
  }
  NumberRule.prototype.evalNumber_0 = function (scalar) {
    return asNumber(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), NumberRule$evalNumber$lambda);
  };
  NumberRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NumberRule',
    interfaces: [ValidationRule]
  };
  var NumberRule_instance = null;
  function NumberRule_getInstance() {
    if (NumberRule_instance === null) {
      new NumberRule();
    }return NumberRule_instance;
  }
  function NullRule() {
    NullRule_instance = this;
  }
  function NullRule$eval$lambda(it) {
    return emptyList();
  }
  NullRule.prototype.eval_vzh9da$ = function (element) {
    return asNull(element).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), NullRule$eval$lambda);
  };
  NullRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NullRule',
    interfaces: [ValidationRule]
  };
  var NullRule_instance = null;
  function NullRule_getInstance() {
    if (NullRule_instance === null) {
      new NullRule();
    }return NullRule_instance;
  }
  function IntegerRule() {
    IntegerRule_instance = this;
  }
  function IntegerRule$eval$lambda(this$IntegerRule) {
    return function (it) {
      return this$IntegerRule.evalInteger_0(it);
    };
  }
  IntegerRule.prototype.eval_vzh9da$ = function (element) {
    return asScalar(element).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), IntegerRule$eval$lambda(this));
  };
  function IntegerRule$evalInteger$lambda(num) {
    return equals(numberToInt(num), num) ? emptyList() : listOf_0(new Error_0('Value is not an integer'));
  }
  IntegerRule.prototype.evalInteger_0 = function (scalar) {
    return asNumber(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), IntegerRule$evalInteger$lambda);
  };
  IntegerRule.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IntegerRule',
    interfaces: [ValidationRule]
  };
  var IntegerRule_instance = null;
  function IntegerRule_getInstance() {
    if (IntegerRule_instance === null) {
      new IntegerRule();
    }return IntegerRule_instance;
  }
  function AllOfRule(rules) {
    this.rules = rules;
  }
  AllOfRule.prototype.eval_vzh9da$ = function (element) {
    var $receiver = this.rules;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.eval_vzh9da$(element));
    }
    var hasAnyErrors = flatten(destination).isEmpty();
    return hasAnyErrors ? listOf_0(new Error_0("Schema doesn't conform to all schemas in allOf")) : emptyList();
  };
  AllOfRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AllOfRule',
    interfaces: [ValidationRule]
  };
  AllOfRule.prototype.component1 = function () {
    return this.rules;
  };
  AllOfRule.prototype.copy_6vpe0g$ = function (rules) {
    return new AllOfRule(rules === void 0 ? this.rules : rules);
  };
  AllOfRule.prototype.toString = function () {
    return 'AllOfRule(rules=' + Kotlin.toString(this.rules) + ')';
  };
  AllOfRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rules) | 0;
    return result;
  };
  AllOfRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rules, other.rules))));
  };
  function AllOfRuleParser() {
    AllOfRuleParser_instance = this;
    ArrayOfRuleParser.call(this, 'allOf', AllOfRuleParser_init$lambda, AllOfRuleParser_init$lambda_0);
  }
  function AllOfRuleParser_init$lambda(schemas) {
    return new AllOfRule(schemas);
  }
  function AllOfRuleParser_init$lambda_0(element) {
    return new Either$Right(new AllOfRule(emptyList()));
  }
  AllOfRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AllOfRuleParser',
    interfaces: [ArrayOfRuleParser]
  };
  var AllOfRuleParser_instance = null;
  function AllOfRuleParser_getInstance() {
    if (AllOfRuleParser_instance === null) {
      new AllOfRuleParser();
    }return AllOfRuleParser_instance;
  }
  function AnyOfRule(rules) {
    this.rules = rules;
  }
  AnyOfRule.prototype.eval_vzh9da$ = function (element) {
    var $receiver = this.rules;
    var tmp$;
    var list = ArrayList_init();
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      if (!!item.eval_vzh9da$(element).isEmpty())
        break;
      list.add_11rb$(item);
    }
    var atLeastOneIsValid = list.size !== this.rules.size;
    return !atLeastOneIsValid ? listOf_0(new Error_0("Schema doesn't conform to at least one schema in the anyOf")) : emptyList();
  };
  AnyOfRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AnyOfRule',
    interfaces: [ValidationRule]
  };
  AnyOfRule.prototype.component1 = function () {
    return this.rules;
  };
  AnyOfRule.prototype.copy_6vpe0g$ = function (rules) {
    return new AnyOfRule(rules === void 0 ? this.rules : rules);
  };
  AnyOfRule.prototype.toString = function () {
    return 'AnyOfRule(rules=' + Kotlin.toString(this.rules) + ')';
  };
  AnyOfRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rules) | 0;
    return result;
  };
  AnyOfRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rules, other.rules))));
  };
  function AnyOfRuleParser(parserFactory) {
    ArrayOfRuleParser.call(this, 'anyOf', AnyOfRuleParser_init$lambda, AnyOfRuleParser_init$lambda_0(parserFactory));
  }
  function AnyOfRuleParser_init$lambda(schemas) {
    return new AnyOfRule(schemas);
  }
  function AnyOfRuleParser_init$lambda_0(closure$parserFactory) {
    return function (element) {
      return closure$parserFactory.make().parse_3boyfh$(element);
    };
  }
  AnyOfRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AnyOfRuleParser',
    interfaces: [ArrayOfRuleParser]
  };
  function ArrayOfRuleParser(key, factory, parser) {
    this.key_6qpk8v$_0 = key;
    this.factory = factory;
    this.parser = parser;
  }
  ArrayOfRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_6qpk8v$_0) != null;
  };
  function ArrayOfRuleParser$parse$lambda$lambda(this$ArrayOfRuleParser) {
    return function (schemas) {
      return this$ArrayOfRuleParser.factory(schemas);
    };
  }
  function ArrayOfRuleParser$parse$lambda(this$ArrayOfRuleParser) {
    return function (array) {
      var $receiver = array.elements();
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(this$ArrayOfRuleParser.parser(ensureNotNull(asObject(item).toRightValueOrNull())));
      }
      var listOfEithers = destination;
      return this$ArrayOfRuleParser.flatten_k34c7c$_0(listOfEithers).map_r1ursk$(ArrayOfRuleParser$parse$lambda$lambda(this$ArrayOfRuleParser));
    };
  }
  ArrayOfRuleParser.prototype.parse_3boyfh$ = function (element) {
    return asArray(element.get_61zpoe$(this.key_6qpk8v$_0)).map_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), ArrayOfRuleParser$parse$lambda(this));
  };
  ArrayOfRuleParser.prototype.flatten_k34c7c$_0 = function (eitherList) {
    var tmp$;
    var first = ArrayList_init();
    var second = ArrayList_init();
    tmp$ = eitherList.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (element.toLeftValueOrNull() != null) {
        first.add_11rb$(element);
      } else {
        second.add_11rb$(element);
      }
    }
    var tmp$_0 = new Pair_init(first, second);
    var errors = tmp$_0.component1()
    , schemas = tmp$_0.component2();
    var tmp$_1;
    if (errors.isEmpty()) {
      var destination = ArrayList_init_0(collectionSizeOrDefault(schemas, 10));
      var tmp$_2;
      tmp$_2 = schemas.iterator();
      while (tmp$_2.hasNext()) {
        var item = tmp$_2.next();
        destination.add_11rb$(ensureNotNull(item.toRightValueOrNull()));
      }
      tmp$_1 = new Either$Right(destination);
    } else {
      var destination_0 = ArrayList_init_0(collectionSizeOrDefault(errors, 10));
      var tmp$_3;
      tmp$_3 = errors.iterator();
      while (tmp$_3.hasNext()) {
        var item_0 = tmp$_3.next();
        destination_0.add_11rb$(ensureNotNull(item_0.toLeftValueOrNull()));
      }
      tmp$_1 = new Either$Left(flatten(destination_0));
    }
    return tmp$_1;
  };
  ArrayOfRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ArrayOfRuleParser',
    interfaces: [RuleParser]
  };
  function NotRule(rule) {
    this.rule = rule;
  }
  NotRule.prototype.eval_vzh9da$ = function (element) {
    var isValid = this.rule.eval_vzh9da$(element).isEmpty();
    return isValid ? listOf_0(new Error_0("Element conforms with the 'not' schema")) : emptyList();
  };
  NotRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotRule',
    interfaces: [ValidationRule]
  };
  NotRule.prototype.component1 = function () {
    return this.rule;
  };
  NotRule.prototype.copy_qx94pn$ = function (rule) {
    return new NotRule(rule === void 0 ? this.rule : rule);
  };
  NotRule.prototype.toString = function () {
    return 'NotRule(rule=' + Kotlin.toString(this.rule) + ')';
  };
  NotRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    return result;
  };
  NotRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rule, other.rule))));
  };
  function NotRuleParser(parserFactory) {
    this.parserFactory = parserFactory;
    this.key_0 = 'not';
  }
  NotRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  function NotRuleParser$parse$lambda(this$NotRuleParser) {
    return function (objEntry) {
      return this$NotRuleParser.parserFactory.make().parse_3boyfh$(objEntry);
    };
  }
  NotRuleParser.prototype.parse_3boyfh$ = function (element) {
    var notValue = ensureNotNull(element.get_61zpoe$(this.key_0));
    return asObject(notValue).map_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), NotRuleParser$parse$lambda(this));
  };
  NotRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotRuleParser',
    interfaces: [RuleParser]
  };
  NotRuleParser.prototype.component1 = function () {
    return this.parserFactory;
  };
  NotRuleParser.prototype.copy_im5jht$ = function (parserFactory) {
    return new NotRuleParser(parserFactory === void 0 ? this.parserFactory : parserFactory);
  };
  NotRuleParser.prototype.toString = function () {
    return 'NotRuleParser(parserFactory=' + Kotlin.toString(this.parserFactory) + ')';
  };
  NotRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.parserFactory) | 0;
    return result;
  };
  NotRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.parserFactory, other.parserFactory))));
  };
  function OneOfRule(rules) {
    this.rules = rules;
  }
  OneOfRule.prototype.eval_vzh9da$ = function (element) {
    var $receiver = this.rules;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(!item.eval_vzh9da$(element).isEmpty());
    }
    var destination_0 = ArrayList_init();
    var tmp$_0;
    tmp$_0 = destination.iterator();
    while (tmp$_0.hasNext()) {
      var element_0 = tmp$_0.next();
      if (element_0)
        destination_0.add_11rb$(element_0);
    }
    var onlyOne = destination_0.size === 1;
    return onlyOne ? listOf_0(new Error_0("Schema doesn't conform to only one schema of the oneOf")) : emptyList();
  };
  OneOfRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OneOfRule',
    interfaces: [ValidationRule]
  };
  OneOfRule.prototype.component1 = function () {
    return this.rules;
  };
  OneOfRule.prototype.copy_6vpe0g$ = function (rules) {
    return new OneOfRule(rules === void 0 ? this.rules : rules);
  };
  OneOfRule.prototype.toString = function () {
    return 'OneOfRule(rules=' + Kotlin.toString(this.rules) + ')';
  };
  OneOfRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rules) | 0;
    return result;
  };
  OneOfRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rules, other.rules))));
  };
  function OneOfRuleParser() {
    OneOfRuleParser_instance = this;
    ArrayOfRuleParser.call(this, 'oneOf', OneOfRuleParser_init$lambda, OneOfRuleParser_init$lambda_0);
  }
  function OneOfRuleParser_init$lambda(schemas) {
    return new OneOfRule(schemas);
  }
  function OneOfRuleParser_init$lambda_0(element) {
    return new Either$Right(new OneOfRule(emptyList()));
  }
  OneOfRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'OneOfRuleParser',
    interfaces: [ArrayOfRuleParser]
  };
  var OneOfRuleParser_instance = null;
  function OneOfRuleParser_getInstance() {
    if (OneOfRuleParser_instance === null) {
      new OneOfRuleParser();
    }return OneOfRuleParser_instance;
  }
  function IfThenElseRule(ifSchema, thenSchema, elseSchema) {
    this.ifSchema = ifSchema;
    this.thenSchema = thenSchema;
    this.elseSchema = elseSchema;
  }
  IfThenElseRule.prototype.eval_vzh9da$ = function (element) {
    var tmp$, tmp$_0, tmp$_1;
    if (this.ifSchema != null) {
      var hasErrors = !this.ifSchema.eval_vzh9da$(element).isEmpty();
      tmp$_1 = hasErrors && this.elseSchema != null ? this.elseSchema.eval_vzh9da$(element) : (tmp$_0 = (tmp$ = this.thenSchema) != null ? tmp$.eval_vzh9da$(element) : null) != null ? tmp$_0 : emptyList();
    } else
      tmp$_1 = emptyList();
    return tmp$_1;
  };
  IfThenElseRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IfThenElseRule',
    interfaces: [ValidationRule]
  };
  IfThenElseRule.prototype.component1 = function () {
    return this.ifSchema;
  };
  IfThenElseRule.prototype.component2 = function () {
    return this.thenSchema;
  };
  IfThenElseRule.prototype.component3 = function () {
    return this.elseSchema;
  };
  IfThenElseRule.prototype.copy_77wyj0$ = function (ifSchema, thenSchema, elseSchema) {
    return new IfThenElseRule(ifSchema === void 0 ? this.ifSchema : ifSchema, thenSchema === void 0 ? this.thenSchema : thenSchema, elseSchema === void 0 ? this.elseSchema : elseSchema);
  };
  IfThenElseRule.prototype.toString = function () {
    return 'IfThenElseRule(ifSchema=' + Kotlin.toString(this.ifSchema) + (', thenSchema=' + Kotlin.toString(this.thenSchema)) + (', elseSchema=' + Kotlin.toString(this.elseSchema)) + ')';
  };
  IfThenElseRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.ifSchema) | 0;
    result = result * 31 + Kotlin.hashCode(this.thenSchema) | 0;
    result = result * 31 + Kotlin.hashCode(this.elseSchema) | 0;
    return result;
  };
  IfThenElseRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.ifSchema, other.ifSchema) && Kotlin.equals(this.thenSchema, other.thenSchema) && Kotlin.equals(this.elseSchema, other.elseSchema)))));
  };
  function IfThenElseRuleParser(factory) {
    this.factory = factory;
    this.ifKey_0 = 'if';
    this.elseKey_0 = 'else';
    this.thenKey_0 = 'then';
  }
  IfThenElseRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.ifKey_0) != null || element.get_61zpoe$(this.thenKey_0) != null || element.get_61zpoe$(this.elseKey_0) != null;
  };
  IfThenElseRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var ifSchemaEither = this.parse_0(element.get_61zpoe$('if'));
    var elseSchemaEither = this.parse_0(element.get_61zpoe$('else'));
    var thenSchemaEither = this.parse_0(element.get_61zpoe$('then'));
    var errors = plus(plus(this.leftOrEmptyList_0(ifSchemaEither), this.leftOrEmptyList_0(elseSchemaEither)), this.leftOrEmptyList_0(thenSchemaEither));
    if (errors.isEmpty()) {
      tmp$ = new Either$Right(new IfThenElseRule(ifSchemaEither.toRightValueOrNull(), thenSchemaEither.toRightValueOrNull(), elseSchemaEither.toRightValueOrNull()));
    } else
      tmp$ = new Either$Left(errors);
    return tmp$;
  };
  IfThenElseRuleParser.prototype.leftOrEmptyList_0 = function (either) {
    var tmp$;
    return (tmp$ = either.toLeftValueOrNull()) != null ? tmp$ : emptyList();
  };
  function IfThenElseRuleParser$parse$lambda(this$IfThenElseRuleParser) {
    return function (x) {
      return this$IfThenElseRuleParser.factory.make().parse_3boyfh$(x);
    };
  }
  IfThenElseRuleParser.prototype.parse_0 = function (element) {
    var tmp$, tmp$_0;
    return (tmp$_0 = (tmp$ = element != null ? asObject(element) : null) != null ? tmp$.map_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), IfThenElseRuleParser$parse$lambda(this)) : null) != null ? tmp$_0 : new Either$Right(null);
  };
  IfThenElseRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IfThenElseRuleParser',
    interfaces: [RuleParser]
  };
  IfThenElseRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  IfThenElseRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new IfThenElseRuleParser(factory === void 0 ? this.factory : factory);
  };
  IfThenElseRuleParser.prototype.toString = function () {
    return 'IfThenElseRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  IfThenElseRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  IfThenElseRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.factory, other.factory))));
  };
  function ExclusiveMaximumRuleParser() {
    ExclusiveMaximumRuleParser_instance = this;
    this.key_ob2rrm$_0 = 'exclusiveMaximum';
  }
  Object.defineProperty(ExclusiveMaximumRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_ob2rrm$_0;
    }
  });
  ExclusiveMaximumRuleParser.prototype.parse_3p81yu$ = function (number) {
    return new Either$Right(new ExclusiveMaximumRule(number));
  };
  ExclusiveMaximumRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ExclusiveMaximumRuleParser',
    interfaces: [NumberRuleParser]
  };
  var ExclusiveMaximumRuleParser_instance = null;
  function ExclusiveMaximumRuleParser_getInstance() {
    if (ExclusiveMaximumRuleParser_instance === null) {
      new ExclusiveMaximumRuleParser();
    }return ExclusiveMaximumRuleParser_instance;
  }
  function ExclusiveMaximumRule(maximum) {
    this.maximum = maximum;
  }
  ExclusiveMaximumRule.prototype.eval_3p81yu$ = function (number) {
    return numberToDouble(number) < numberToDouble(this.maximum) ? emptyList() : listOf_0(new Error_0(number.toString() + ' is equal or bigger than ' + this.maximum.toString()));
  };
  ExclusiveMaximumRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExclusiveMaximumRule',
    interfaces: [NumberRule_0]
  };
  ExclusiveMaximumRule.prototype.component1 = function () {
    return this.maximum;
  };
  ExclusiveMaximumRule.prototype.copy_3p81yu$ = function (maximum) {
    return new ExclusiveMaximumRule(maximum === void 0 ? this.maximum : maximum);
  };
  ExclusiveMaximumRule.prototype.toString = function () {
    return 'ExclusiveMaximumRule(maximum=' + Kotlin.toString(this.maximum) + ')';
  };
  ExclusiveMaximumRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.maximum) | 0;
    return result;
  };
  ExclusiveMaximumRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.maximum, other.maximum))));
  };
  function ExclusiveMinimumRuleParser() {
    ExclusiveMinimumRuleParser_instance = this;
    this.key_8i9fk$_0 = 'exclusiveMinimum';
  }
  Object.defineProperty(ExclusiveMinimumRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_8i9fk$_0;
    }
  });
  ExclusiveMinimumRuleParser.prototype.parse_3p81yu$ = function (number) {
    return new Either$Right(new ExclusiveMinimumRule(number));
  };
  ExclusiveMinimumRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ExclusiveMinimumRuleParser',
    interfaces: [NumberRuleParser]
  };
  var ExclusiveMinimumRuleParser_instance = null;
  function ExclusiveMinimumRuleParser_getInstance() {
    if (ExclusiveMinimumRuleParser_instance === null) {
      new ExclusiveMinimumRuleParser();
    }return ExclusiveMinimumRuleParser_instance;
  }
  function ExclusiveMinimumRule(minimum) {
    this.minimum = minimum;
  }
  ExclusiveMinimumRule.prototype.eval_3p81yu$ = function (number) {
    return numberToDouble(number) > numberToDouble(this.minimum) ? emptyList() : listOf_0(new Error_0(number.toString() + ' is equal or smaller than ' + this.minimum.toString()));
  };
  ExclusiveMinimumRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExclusiveMinimumRule',
    interfaces: [NumberRule_0]
  };
  ExclusiveMinimumRule.prototype.component1 = function () {
    return this.minimum;
  };
  ExclusiveMinimumRule.prototype.copy_3p81yu$ = function (minimum) {
    return new ExclusiveMinimumRule(minimum === void 0 ? this.minimum : minimum);
  };
  ExclusiveMinimumRule.prototype.toString = function () {
    return 'ExclusiveMinimumRule(minimum=' + Kotlin.toString(this.minimum) + ')';
  };
  ExclusiveMinimumRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minimum) | 0;
    return result;
  };
  ExclusiveMinimumRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.minimum, other.minimum))));
  };
  function MaximumRuleParser() {
    MaximumRuleParser_instance = this;
    this.key_7gllm4$_0 = 'maximum';
  }
  Object.defineProperty(MaximumRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_7gllm4$_0;
    }
  });
  MaximumRuleParser.prototype.parse_3p81yu$ = function (number) {
    return new Either$Right(new MaximumRule(number));
  };
  MaximumRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MaximumRuleParser',
    interfaces: [NumberRuleParser]
  };
  var MaximumRuleParser_instance = null;
  function MaximumRuleParser_getInstance() {
    if (MaximumRuleParser_instance === null) {
      new MaximumRuleParser();
    }return MaximumRuleParser_instance;
  }
  function MaximumRule(maximum) {
    this.maximum = maximum;
  }
  MaximumRule.prototype.eval_3p81yu$ = function (number) {
    return numberToDouble(number) <= numberToDouble(this.maximum) ? emptyList() : listOf_0(new Error_0(number.toString() + ' is bigger than ' + this.maximum.toString()));
  };
  MaximumRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaximumRule',
    interfaces: [NumberRule_0]
  };
  MaximumRule.prototype.component1 = function () {
    return this.maximum;
  };
  MaximumRule.prototype.copy_3p81yu$ = function (maximum) {
    return new MaximumRule(maximum === void 0 ? this.maximum : maximum);
  };
  MaximumRule.prototype.toString = function () {
    return 'MaximumRule(maximum=' + Kotlin.toString(this.maximum) + ')';
  };
  MaximumRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.maximum) | 0;
    return result;
  };
  MaximumRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.maximum, other.maximum))));
  };
  function MinimumRuleParser() {
    MinimumRuleParser_instance = this;
    this.key_w06mta$_0 = 'minimum';
  }
  Object.defineProperty(MinimumRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_w06mta$_0;
    }
  });
  MinimumRuleParser.prototype.parse_3p81yu$ = function (number) {
    return new Either$Right(new MinimumRule(number));
  };
  MinimumRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MinimumRuleParser',
    interfaces: [NumberRuleParser]
  };
  var MinimumRuleParser_instance = null;
  function MinimumRuleParser_getInstance() {
    if (MinimumRuleParser_instance === null) {
      new MinimumRuleParser();
    }return MinimumRuleParser_instance;
  }
  function MinimumRule(minimum) {
    this.minimum = minimum;
  }
  MinimumRule.prototype.eval_3p81yu$ = function (number) {
    return numberToDouble(number) >= numberToDouble(this.minimum) ? emptyList() : listOf_0(new Error_0(number.toString() + ' is smaller than ' + this.minimum.toString()));
  };
  MinimumRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinimumRule',
    interfaces: [NumberRule_0]
  };
  MinimumRule.prototype.component1 = function () {
    return this.minimum;
  };
  MinimumRule.prototype.copy_3p81yu$ = function (minimum) {
    return new MinimumRule(minimum === void 0 ? this.minimum : minimum);
  };
  MinimumRule.prototype.toString = function () {
    return 'MinimumRule(minimum=' + Kotlin.toString(this.minimum) + ')';
  };
  MinimumRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minimum) | 0;
    return result;
  };
  MinimumRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.minimum, other.minimum))));
  };
  function MultipleOfRuleParser() {
    MultipleOfRuleParser_instance = this;
    this.key_wj5mdz$_0 = 'multipleOf';
  }
  Object.defineProperty(MultipleOfRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_wj5mdz$_0;
    }
  });
  MultipleOfRuleParser.prototype.parse_3p81yu$ = function (number) {
    return equals(number, 0) ? new Either$Left(listOf_0(new Error_0('0 is not a valid multipleOf anything'))) : new Either$Right(new MultipleOfRule(number));
  };
  MultipleOfRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MultipleOfRuleParser',
    interfaces: [NumberRuleParser]
  };
  var MultipleOfRuleParser_instance = null;
  function MultipleOfRuleParser_getInstance() {
    if (MultipleOfRuleParser_instance === null) {
      new MultipleOfRuleParser();
    }return MultipleOfRuleParser_instance;
  }
  function MultipleOfRule(multipleOf) {
    this.multipleOf = multipleOf;
  }
  MultipleOfRule.prototype.eval_3p81yu$ = function (number) {
    return numberToDouble(number) % numberToDouble(this.multipleOf) === 0.0 ? emptyList() : listOf_0(new Error_0(number.toString() + ' is not multiple of ' + this.multipleOf.toString()));
  };
  MultipleOfRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MultipleOfRule',
    interfaces: [NumberRule_0]
  };
  MultipleOfRule.prototype.component1 = function () {
    return this.multipleOf;
  };
  MultipleOfRule.prototype.copy_3p81yu$ = function (multipleOf) {
    return new MultipleOfRule(multipleOf === void 0 ? this.multipleOf : multipleOf);
  };
  MultipleOfRule.prototype.toString = function () {
    return 'MultipleOfRule(multipleOf=' + Kotlin.toString(this.multipleOf) + ')';
  };
  MultipleOfRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.multipleOf) | 0;
    return result;
  };
  MultipleOfRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.multipleOf, other.multipleOf))));
  };
  function NumberRule_0() {
  }
  NumberRule_0.prototype.eval_vzh9da$ = function (element) {
    var tmp$, tmp$_0;
    var number = (tmp$ = this.getNumber_vzh9da$(element)) != null ? numberToDouble(tmp$) : null;
    if (number != null) {
      tmp$_0 = this.eval_3p81yu$(number);
    } else
      tmp$_0 = emptyList();
    return tmp$_0;
  };
  function NumberRule$getNumber$lambda(it) {
    return null;
  }
  function NumberRule$getNumber$lambda_0(scalar) {
    return asNumber(scalar);
  }
  function NumberRule$getNumber$lambda_1(it) {
    return null;
  }
  function NumberRule$getNumber$lambda_2(x) {
    return x;
  }
  NumberRule_0.prototype.getNumber_vzh9da$ = function (element) {
    return asScalar(element).map_ik7j40$(NumberRule$getNumber$lambda, NumberRule$getNumber$lambda_0).fold_hfmbsx$(NumberRule$getNumber$lambda_1, NumberRule$getNumber$lambda_2);
  };
  NumberRule_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NumberRule',
    interfaces: [ValidationRule]
  };
  function NumberRuleParser() {
  }
  NumberRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key) != null;
  };
  function NumberRuleParser$parse$lambda(this$NumberRuleParser) {
    return function (scalar) {
      return asNumber(scalar).map_ik7j40$(getCallableRef('listOf', function (p1) {
        return listOf_0(p1);
      }), getCallableRef('parse', function ($receiver, p1) {
        return $receiver.parse_3p81yu$(p1);
      }.bind(null, this$NumberRuleParser)));
    };
  }
  NumberRuleParser.prototype.parse_3boyfh$ = function (element) {
    var constElement = ensureNotNull(element.get_61zpoe$(this.key));
    return asScalar(constElement).map_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), NumberRuleParser$parse$lambda(this));
  };
  NumberRuleParser.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NumberRuleParser',
    interfaces: [RuleParser]
  };
  function PropertyAmountParser() {
  }
  PropertyAmountParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key) != null;
  };
  function PropertyAmountParser$parse$lambda$lambda(number) {
    return numberToInt(number) < 0 ? new Either$Left(listOf_0(new Error_0('maxProperties should have a non-negative value'))) : new Either$Right(new MaxPropertiesRule(numberToInt(number)));
  }
  function PropertyAmountParser$parse$lambda(scalar) {
    return asNumber(scalar).map_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), PropertyAmountParser$parse$lambda$lambda);
  }
  PropertyAmountParser.prototype.parse_3boyfh$ = function (element) {
    return asScalar(element.get_61zpoe$(this.key)).map_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), PropertyAmountParser$parse$lambda);
  };
  PropertyAmountParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PropertyAmountParser',
    interfaces: [RuleParser]
  };
  function MaxPropertiesRuleParser() {
    MaxPropertiesRuleParser_instance = this;
    PropertyAmountParser.call(this);
    this.key_j7jkyf$_0 = 'maxProperties';
  }
  Object.defineProperty(MaxPropertiesRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_j7jkyf$_0;
    }
  });
  MaxPropertiesRuleParser.prototype.parse_3p81yu$ = function (number) {
    return numberToInt(number) < 0 ? new Either$Left(listOf_0(new Error_0('maxProperties should have a non-negative value'))) : new Either$Right(new MaxPropertiesRule(numberToInt(number)));
  };
  MaxPropertiesRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MaxPropertiesRuleParser',
    interfaces: [PropertyAmountParser]
  };
  var MaxPropertiesRuleParser_instance = null;
  function MaxPropertiesRuleParser_getInstance() {
    if (MaxPropertiesRuleParser_instance === null) {
      new MaxPropertiesRuleParser();
    }return MaxPropertiesRuleParser_instance;
  }
  function MaxPropertiesRule(maximum) {
    this.maximum = maximum;
  }
  function MaxPropertiesRule$eval$lambda(it) {
    return emptyList();
  }
  function MaxPropertiesRule$eval$lambda_0(this$MaxPropertiesRule) {
    return function (x) {
      return x.keys().size <= this$MaxPropertiesRule.maximum ? emptyList() : listOf_0(new Error_0('Object has ' + x.keys().size + ' but maximum is ' + this$MaxPropertiesRule.maximum));
    };
  }
  MaxPropertiesRule.prototype.eval_vzh9da$ = function (element) {
    return asObject(element).fold_hfmbsx$(MaxPropertiesRule$eval$lambda, MaxPropertiesRule$eval$lambda_0(this));
  };
  MaxPropertiesRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaxPropertiesRule',
    interfaces: [ValidationRule]
  };
  MaxPropertiesRule.prototype.component1 = function () {
    return this.maximum;
  };
  MaxPropertiesRule.prototype.copy_za3lpa$ = function (maximum) {
    return new MaxPropertiesRule(maximum === void 0 ? this.maximum : maximum);
  };
  MaxPropertiesRule.prototype.toString = function () {
    return 'MaxPropertiesRule(maximum=' + Kotlin.toString(this.maximum) + ')';
  };
  MaxPropertiesRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.maximum) | 0;
    return result;
  };
  MaxPropertiesRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.maximum, other.maximum))));
  };
  function MinPropertiesRuleParser() {
    MinPropertiesRuleParser_instance = this;
    PropertyAmountParser.call(this);
    this.key_vutjp7$_0 = 'minProperties';
  }
  Object.defineProperty(MinPropertiesRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_vutjp7$_0;
    }
  });
  MinPropertiesRuleParser.prototype.parse_3p81yu$ = function (number) {
    return numberToInt(number) < 0 ? new Either$Left(listOf_0(new Error_0('minProperties should have a non-negative value'))) : new Either$Right(new MinPropertiesRule(numberToInt(number)));
  };
  MinPropertiesRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MinPropertiesRuleParser',
    interfaces: [PropertyAmountParser]
  };
  var MinPropertiesRuleParser_instance = null;
  function MinPropertiesRuleParser_getInstance() {
    if (MinPropertiesRuleParser_instance === null) {
      new MinPropertiesRuleParser();
    }return MinPropertiesRuleParser_instance;
  }
  function MinPropertiesRule(minimum) {
    this.minimum = minimum;
  }
  function MinPropertiesRule$eval$lambda(it) {
    return emptyList();
  }
  function MinPropertiesRule$eval$lambda_0(this$MinPropertiesRule) {
    return function (x) {
      return x.keys().size >= this$MinPropertiesRule.minimum ? emptyList() : listOf_0(new Error_0('Object has ' + x.keys().size + ' but minimum is ' + this$MinPropertiesRule.minimum));
    };
  }
  MinPropertiesRule.prototype.eval_vzh9da$ = function (element) {
    return asObject(element).fold_hfmbsx$(MinPropertiesRule$eval$lambda, MinPropertiesRule$eval$lambda_0(this));
  };
  MinPropertiesRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinPropertiesRule',
    interfaces: [ValidationRule]
  };
  MinPropertiesRule.prototype.component1 = function () {
    return this.minimum;
  };
  MinPropertiesRule.prototype.copy_za3lpa$ = function (minimum) {
    return new MinPropertiesRule(minimum === void 0 ? this.minimum : minimum);
  };
  MinPropertiesRule.prototype.toString = function () {
    return 'MinPropertiesRule(minimum=' + Kotlin.toString(this.minimum) + ')';
  };
  MinPropertiesRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minimum) | 0;
    return result;
  };
  MinPropertiesRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.minimum, other.minimum))));
  };
  function PropertyRuleParser(factory) {
    this.factory = factory;
    this.key_0 = 'properties';
  }
  PropertyRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  PropertyRuleParser.prototype.parse_3boyfh$ = function (element) {
    return new Either$Left(listOf_0(new Error_0('asdasdasd')));
  };
  function PropertyRuleParser$parseProperty$lambda(closure$key) {
    return function (x) {
      return new PropertyRule(closure$key, x);
    };
  }
  PropertyRuleParser.prototype.parseProperty_0 = function (key, element) {
    var eitherRule = this.factory.make().parse_3boyfh$(element);
    return eitherRule.map_r1ursk$(PropertyRuleParser$parseProperty$lambda(key));
  };
  PropertyRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PropertyRuleParser',
    interfaces: [RuleParser]
  };
  PropertyRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  PropertyRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new PropertyRuleParser(factory === void 0 ? this.factory : factory);
  };
  PropertyRuleParser.prototype.toString = function () {
    return 'PropertyRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  PropertyRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  PropertyRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.factory, other.factory))));
  };
  function PropertiesRule(rules) {
    this.rules = rules;
  }
  PropertiesRule.prototype.eval_vzh9da$ = function (element) {
    var $receiver = this.rules;
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element_0 = tmp$.next();
      var list = element_0.eval_vzh9da$(element);
      addAll(destination, list);
    }
    return destination;
  };
  PropertiesRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PropertiesRule',
    interfaces: [ValidationRule]
  };
  PropertiesRule.prototype.component1 = function () {
    return this.rules;
  };
  PropertiesRule.prototype.copy_yjd5o2$ = function (rules) {
    return new PropertiesRule(rules === void 0 ? this.rules : rules);
  };
  PropertiesRule.prototype.toString = function () {
    return 'PropertiesRule(rules=' + Kotlin.toString(this.rules) + ')';
  };
  PropertiesRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rules) | 0;
    return result;
  };
  PropertiesRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rules, other.rules))));
  };
  function PropertyRule(name, rule) {
    this.name = name;
    this.rule = rule;
  }
  function PropertyRule$eval$lambda(it) {
    return emptyList();
  }
  function PropertyRule$eval$lambda_0(this$PropertyRule) {
    return function (obj) {
      var property = obj.get_61zpoe$(this$PropertyRule.name);
      return property != null ? this$PropertyRule.rule.eval_vzh9da$(property) : emptyList();
    };
  }
  PropertyRule.prototype.eval_vzh9da$ = function (element) {
    return asObject(element).fold_hfmbsx$(PropertyRule$eval$lambda, PropertyRule$eval$lambda_0(this));
  };
  PropertyRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PropertyRule',
    interfaces: [ValidationRule]
  };
  PropertyRule.prototype.component1 = function () {
    return this.name;
  };
  PropertyRule.prototype.component2 = function () {
    return this.rule;
  };
  PropertyRule.prototype.copy_itxqo9$ = function (name, rule) {
    return new PropertyRule(name === void 0 ? this.name : name, rule === void 0 ? this.rule : rule);
  };
  PropertyRule.prototype.toString = function () {
    return 'PropertyRule(name=' + Kotlin.toString(this.name) + (', rule=' + Kotlin.toString(this.rule)) + ')';
  };
  PropertyRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    return result;
  };
  PropertyRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.name, other.name) && Kotlin.equals(this.rule, other.rule)))));
  };
  function RequiredRuleParser() {
    RequiredRuleParser_instance = this;
    this.key_0 = 'required';
  }
  RequiredRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  RequiredRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var elem = element.get_61zpoe$(this.key_0);
    if (Kotlin.isType(elem, JsonArray)) {
      var $receiver = elem.elements();
      var destination = ArrayList_init();
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var element_0 = tmp$_0.next();
        var tmp$_0_0;
        if ((tmp$_0_0 = asScalar(element_0).toRightValueOrNull()) != null) {
          destination.add_11rb$(tmp$_0_0);
        }}
      var destination_0 = ArrayList_init();
      var tmp$_1;
      tmp$_1 = destination.iterator();
      while (tmp$_1.hasNext()) {
        var element_1 = tmp$_1.next();
        var tmp$_0_1;
        if ((tmp$_0_1 = asString(element_1).toRightValueOrNull()) != null) {
          destination_0.add_11rb$(tmp$_0_1);
        }}
      var requiredKeys = destination_0;
      tmp$ = requiredKeys.size !== elem.elements().size ? new Either$Left(listOf_0(new Error_0('Some elements in the array are not strings'))) : new Either$Right(new RequiredRule(requiredKeys));
    } else
      tmp$ = new Either$Left(listOf_0(new Error_0("'required' key is not an array")));
    return tmp$;
  };
  RequiredRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'RequiredRuleParser',
    interfaces: [RuleParser]
  };
  var RequiredRuleParser_instance = null;
  function RequiredRuleParser_getInstance() {
    if (RequiredRuleParser_instance === null) {
      new RequiredRuleParser();
    }return RequiredRuleParser_instance;
  }
  function RequiredRule(members) {
    this.members = members;
  }
  function RequiredRule$eval$lambda(this$RequiredRule) {
    return function (it) {
      return this$RequiredRule.createErrors_0(this$RequiredRule.members);
    };
  }
  function RequiredRule$eval$lambda_0(this$RequiredRule) {
    return function (x) {
      var differentMembers = subtract(this$RequiredRule.members, x.keys());
      return differentMembers.isEmpty() ? emptyList() : this$RequiredRule.createErrors_0(this$RequiredRule.members);
    };
  }
  RequiredRule.prototype.eval_vzh9da$ = function (element) {
    return asObject(element).fold_hfmbsx$(RequiredRule$eval$lambda(this), RequiredRule$eval$lambda_0(this));
  };
  RequiredRule.prototype.createErrors_0 = function (keys) {
    var destination = ArrayList_init_0(collectionSizeOrDefault(keys, 10));
    var tmp$;
    tmp$ = keys.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new Error_0(item + ' is required but not present in object'));
    }
    return destination;
  };
  RequiredRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RequiredRule',
    interfaces: [ValidationRule]
  };
  RequiredRule.prototype.component1 = function () {
    return this.members;
  };
  RequiredRule.prototype.copy_mhpeer$ = function (members) {
    return new RequiredRule(members === void 0 ? this.members : members);
  };
  RequiredRule.prototype.toString = function () {
    return 'RequiredRule(members=' + Kotlin.toString(this.members) + ')';
  };
  RequiredRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.members) | 0;
    return result;
  };
  RequiredRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.members, other.members))));
  };
  var package$org = _.org || (_.org = {});
  var package$validator = package$org.validator || (package$org.validator = {});
  package$validator.JsonElement = JsonElement;
  package$validator.asObject_ia0mbm$ = asObject;
  package$validator.asScalar_ia0mbm$ = asScalar;
  package$validator.asArray_ia0mbm$ = asArray;
  package$validator.asNull_ia0mbm$ = asNull;
  package$validator.JsonObject = JsonObject;
  package$validator.DefaultJsonObject = DefaultJsonObject;
  package$validator.JsonArray = JsonArray;
  package$validator.DefaultJsonArray = DefaultJsonArray;
  package$validator.NullElement = NullElement;
  Object.defineProperty(package$validator, 'DefaultNullElement', {
    get: DefaultNullElement_getInstance
  });
  package$validator.asString_k22kqd$ = asString;
  package$validator.asBoolean_k22kqd$ = asBoolean;
  package$validator.asNumber_k22kqd$ = asNumber;
  package$validator.JsonScalar = JsonScalar;
  package$validator.DefaultJsonScalar = DefaultJsonScalar;
  package$validator.identity_mh5how$ = identity;
  Either.Left = Either$Left;
  Either.Right = Either$Right;
  package$validator.Either = Either;
  package$validator.ValidationRule = ValidationRule;
  package$validator.RuleParser = RuleParser;
  Object.defineProperty(package$validator, 'NothingValidationRule', {
    get: NothingValidationRule_getInstance
  });
  package$validator.OrValidationRule = OrValidationRule;
  package$validator.Error = Error_0;
  var package$rules = package$validator.rules || (package$validator.rules = {});
  Object.defineProperty(package$rules, 'DraftParsers', {
    get: DraftParsers_getInstance
  });
  package$rules.SchemaRuleParserFactory = SchemaRuleParserFactory;
  Object.defineProperty(package$rules, 'Draft4ParserFactory', {
    get: Draft4ParserFactory_getInstance
  });
  package$rules.SchemaRuleParser = SchemaRuleParser;
  package$rules.SchemaRule = SchemaRule;
  var package$any = package$rules.any || (package$rules.any = {});
  Object.defineProperty(package$any, 'ConstRuleParser', {
    get: ConstRuleParser_getInstance
  });
  package$any.ConstRule = ConstRule;
  Object.defineProperty(package$any, 'EnumRuleParser', {
    get: EnumRuleParser_getInstance
  });
  package$any.EnumRule = EnumRule;
  Object.defineProperty(package$any, 'TypeRuleParser', {
    get: TypeRuleParser_getInstance
  });
  Object.defineProperty(package$any, 'ObjectRule', {
    get: ObjectRule_getInstance
  });
  Object.defineProperty(package$any, 'ArrayRule', {
    get: ArrayRule_getInstance
  });
  Object.defineProperty(package$any, 'StringRule', {
    get: StringRule_getInstance
  });
  Object.defineProperty(package$any, 'BooleanRule', {
    get: BooleanRule_getInstance
  });
  Object.defineProperty(package$any, 'NumberRule', {
    get: NumberRule_getInstance
  });
  Object.defineProperty(package$any, 'NullRule', {
    get: NullRule_getInstance
  });
  Object.defineProperty(package$any, 'IntegerRule', {
    get: IntegerRule_getInstance
  });
  var package$boolean = package$rules.boolean || (package$rules.boolean = {});
  package$boolean.AllOfRule = AllOfRule;
  Object.defineProperty(package$boolean, 'AllOfRuleParser', {
    get: AllOfRuleParser_getInstance
  });
  package$boolean.AnyOfRule = AnyOfRule;
  package$boolean.AnyOfRuleParser = AnyOfRuleParser;
  package$boolean.ArrayOfRuleParser = ArrayOfRuleParser;
  package$boolean.NotRule = NotRule;
  package$boolean.NotRuleParser = NotRuleParser;
  package$boolean.OneOfRule = OneOfRule;
  Object.defineProperty(package$boolean, 'OneOfRuleParser', {
    get: OneOfRuleParser_getInstance
  });
  var package$conditional = package$rules.conditional || (package$rules.conditional = {});
  package$conditional.IfThenElseRule = IfThenElseRule;
  package$conditional.IfThenElseRuleParser = IfThenElseRuleParser;
  var package$numeric = package$rules.numeric || (package$rules.numeric = {});
  Object.defineProperty(package$numeric, 'ExclusiveMaximumRuleParser', {
    get: ExclusiveMaximumRuleParser_getInstance
  });
  package$numeric.ExclusiveMaximumRule = ExclusiveMaximumRule;
  Object.defineProperty(package$numeric, 'ExclusiveMinimumRuleParser', {
    get: ExclusiveMinimumRuleParser_getInstance
  });
  package$numeric.ExclusiveMinimumRule = ExclusiveMinimumRule;
  Object.defineProperty(package$numeric, 'MaximumRuleParser', {
    get: MaximumRuleParser_getInstance
  });
  package$numeric.MaximumRule = MaximumRule;
  Object.defineProperty(package$numeric, 'MinimumRuleParser', {
    get: MinimumRuleParser_getInstance
  });
  package$numeric.MinimumRule = MinimumRule;
  Object.defineProperty(package$numeric, 'MultipleOfRuleParser', {
    get: MultipleOfRuleParser_getInstance
  });
  package$numeric.MultipleOfRule = MultipleOfRule;
  package$numeric.NumberRule = NumberRule_0;
  package$numeric.NumberRuleParser = NumberRuleParser;
  var package$object = package$rules.object || (package$rules.object = {});
  package$object.PropertyAmountParser = PropertyAmountParser;
  Object.defineProperty(package$object, 'MaxPropertiesRuleParser', {
    get: MaxPropertiesRuleParser_getInstance
  });
  package$object.MaxPropertiesRule = MaxPropertiesRule;
  Object.defineProperty(package$object, 'MinPropertiesRuleParser', {
    get: MinPropertiesRuleParser_getInstance
  });
  package$object.MinPropertiesRule = MinPropertiesRule;
  package$object.PropertyRuleParser = PropertyRuleParser;
  package$object.PropertiesRule = PropertiesRule;
  package$object.PropertyRule = PropertyRule;
  Object.defineProperty(package$object, 'RequiredRuleParser', {
    get: RequiredRuleParser_getInstance
  });
  package$object.RequiredRule = RequiredRule;
  DefaultJsonObject.prototype.deepEquals_vzh9da$ = JsonObject.prototype.deepEquals_vzh9da$;
  DefaultJsonObject.prototype.hasSameChildren_mbcpqv$_0 = JsonObject.prototype.hasSameChildren_mbcpqv$_0;
  DefaultJsonObject.prototype.hasSameKeys_xksyj8$_0 = JsonObject.prototype.hasSameKeys_xksyj8$_0;
  DefaultJsonArray.prototype.deepEquals_vzh9da$ = JsonArray.prototype.deepEquals_vzh9da$;
  DefaultJsonArray.prototype.hasSameElements_hlw545$_0 = JsonArray.prototype.hasSameElements_hlw545$_0;
  DefaultNullElement.prototype.deepEquals_vzh9da$ = NullElement.prototype.deepEquals_vzh9da$;
  DefaultJsonScalar.prototype.deepEquals_vzh9da$ = JsonScalar.prototype.deepEquals_vzh9da$;
  ExclusiveMaximumRuleParser.prototype.parse_3boyfh$ = NumberRuleParser.prototype.parse_3boyfh$;
  ExclusiveMaximumRuleParser.prototype.canParse_3boyfh$ = NumberRuleParser.prototype.canParse_3boyfh$;
  ExclusiveMaximumRule.prototype.eval_vzh9da$ = NumberRule_0.prototype.eval_vzh9da$;
  ExclusiveMaximumRule.prototype.getNumber_vzh9da$ = NumberRule_0.prototype.getNumber_vzh9da$;
  ExclusiveMinimumRuleParser.prototype.parse_3boyfh$ = NumberRuleParser.prototype.parse_3boyfh$;
  ExclusiveMinimumRuleParser.prototype.canParse_3boyfh$ = NumberRuleParser.prototype.canParse_3boyfh$;
  ExclusiveMinimumRule.prototype.eval_vzh9da$ = NumberRule_0.prototype.eval_vzh9da$;
  ExclusiveMinimumRule.prototype.getNumber_vzh9da$ = NumberRule_0.prototype.getNumber_vzh9da$;
  MaximumRuleParser.prototype.parse_3boyfh$ = NumberRuleParser.prototype.parse_3boyfh$;
  MaximumRuleParser.prototype.canParse_3boyfh$ = NumberRuleParser.prototype.canParse_3boyfh$;
  MaximumRule.prototype.eval_vzh9da$ = NumberRule_0.prototype.eval_vzh9da$;
  MaximumRule.prototype.getNumber_vzh9da$ = NumberRule_0.prototype.getNumber_vzh9da$;
  MinimumRuleParser.prototype.parse_3boyfh$ = NumberRuleParser.prototype.parse_3boyfh$;
  MinimumRuleParser.prototype.canParse_3boyfh$ = NumberRuleParser.prototype.canParse_3boyfh$;
  MinimumRule.prototype.eval_vzh9da$ = NumberRule_0.prototype.eval_vzh9da$;
  MinimumRule.prototype.getNumber_vzh9da$ = NumberRule_0.prototype.getNumber_vzh9da$;
  MultipleOfRuleParser.prototype.parse_3boyfh$ = NumberRuleParser.prototype.parse_3boyfh$;
  MultipleOfRuleParser.prototype.canParse_3boyfh$ = NumberRuleParser.prototype.canParse_3boyfh$;
  MultipleOfRule.prototype.eval_vzh9da$ = NumberRule_0.prototype.eval_vzh9da$;
  MultipleOfRule.prototype.getNumber_vzh9da$ = NumberRule_0.prototype.getNumber_vzh9da$;
  Kotlin.defineModule('json-schema-validator', _);
  return _;
}));

//# sourceMappingURL=json-schema-validator.js.map
