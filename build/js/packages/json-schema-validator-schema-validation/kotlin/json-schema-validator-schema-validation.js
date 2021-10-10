(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Kotlin-DateTime-library-kotlinx-datetime-js-legacy'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Kotlin-DateTime-library-kotlinx-datetime-js-legacy'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-schema-validation'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'json-schema-validator-schema-validation'.");
    }if (typeof this['Kotlin-DateTime-library-kotlinx-datetime-js-legacy'] === 'undefined') {
      throw new Error("Error loading module 'json-schema-validator-schema-validation'. Its dependency 'Kotlin-DateTime-library-kotlinx-datetime-js-legacy' was not found. Please, check whether 'Kotlin-DateTime-library-kotlinx-datetime-js-legacy' is loaded prior to 'json-schema-validator-schema-validation'.");
    }root['json-schema-validator-schema-validation'] = factory(typeof this['json-schema-validator-schema-validation'] === 'undefined' ? {} : this['json-schema-validator-schema-validation'], kotlin, this['Kotlin-DateTime-library-kotlinx-datetime-js-legacy']);
  }
}(this, function (_, Kotlin, $module$Kotlin_DateTime_library_kotlinx_datetime_js_legacy) {
  'use strict';
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var equals = Kotlin.equals;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var zip = Kotlin.kotlin.collections.zip_45mdf7$;
  var getOrNull = Kotlin.kotlin.collections.getOrNull_yzln2o$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var throwCCE = Kotlin.throwCCE;
  var numberToDouble = Kotlin.numberToDouble;
  var Pair = Kotlin.kotlin.Pair;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Any = Object;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var JsMath = Math;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var flatten = Kotlin.kotlin.collections.flatten_u0ad8z$;
  var Collection = Kotlin.kotlin.collections.Collection;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var getCallableRef = Kotlin.getCallableRef;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_qfcya0$;
  var any = Kotlin.kotlin.collections.any_7wnvza$;
  var distinct = Kotlin.kotlin.collections.distinct_7wnvza$;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var toSet = Kotlin.kotlin.collections.toSet_7wnvza$;
  var minus = Kotlin.kotlin.collections.minus_khz7k3$;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var numberToInt = Kotlin.numberToInt;
  var subtract = Kotlin.kotlin.collections.subtract_q4559j$;
  var Instant = $module$Kotlin_DateTime_library_kotlinx_datetime_js_legacy.kotlinx.datetime.Instant;
  var toString = Kotlin.toString;
  var Exception = Kotlin.kotlin.Exception;
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
  PropertiesRuleParser.prototype = Object.create(AbstractPropertiesRuleParser.prototype);
  PropertiesRuleParser.prototype.constructor = PropertiesRuleParser;
  PatternPropertiesRuleParser.prototype = Object.create(AbstractPropertiesRuleParser.prototype);
  PatternPropertiesRuleParser.prototype.constructor = PatternPropertiesRuleParser;
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
  function asString($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, JsonScalar))
      tmp$ = asString_0($receiver);
    else
      tmp$ = new Either$Left(new Error_0('Element is not a scalar'));
    return tmp$;
  }
  function asBoolean($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, JsonScalar))
      tmp$ = asBoolean_0($receiver);
    else
      tmp$ = new Either$Left(new Error_0('Element is not a scalar'));
    return tmp$;
  }
  function asNumber($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, JsonScalar))
      tmp$ = asNumber_0($receiver);
    else
      tmp$ = new Either$Left(new Error_0('Element is not a scalar'));
    return tmp$;
  }
  function asInteger($receiver) {
    var tmp$;
    if (Kotlin.isType($receiver, JsonScalar))
      tmp$ = asInteger_0($receiver);
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
      destination.add_11rb$(new Pair(item.key, item.value));
    }
    return destination;
  };
  DefaultJsonObject.prototype.keys = function () {
    return this.map.keys;
  };
  DefaultJsonObject.prototype.containsKey_61zpoe$ = function (key) {
    return this.map.containsKey_11rb$(key);
  };
  DefaultJsonObject.prototype.entries_t7befh$ = function (regex) {
    var $receiver = this.entries();
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (regex.containsMatchIn_6bul2c$(element.first))
        destination.add_11rb$(element);
    }
    return destination;
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
  function asString_0($receiver) {
    var tmp$, tmp$_0;
    if (typeof $receiver.value === 'string') {
      tmp$_0 = new Either$Right(typeof (tmp$ = $receiver.value) === 'string' ? tmp$ : throwCCE());
    } else
      tmp$_0 = new Either$Left(new Error_0('Element is not a string'));
    return tmp$_0;
  }
  function asBoolean_0($receiver) {
    var tmp$, tmp$_0;
    if (typeof $receiver.value === 'boolean') {
      tmp$_0 = new Either$Right(typeof (tmp$ = $receiver.value) === 'boolean' ? tmp$ : throwCCE());
    } else
      tmp$_0 = new Either$Left(new Error_0('Element is not a boolean'));
    return tmp$_0;
  }
  function asNumber_0($receiver) {
    var tmp$, tmp$_0;
    if (Kotlin.isNumber($receiver.value)) {
      tmp$_0 = new Either$Right(Kotlin.isNumber(tmp$ = $receiver.value) ? tmp$ : throwCCE());
    } else
      tmp$_0 = new Either$Left(new Error_0('Element is not a number'));
    return tmp$_0;
  }
  function asInteger_0($receiver) {
    var tmp$, tmp$_0;
    if (typeof $receiver.value === 'number') {
      tmp$_0 = new Either$Right(typeof (tmp$ = $receiver.value) === 'number' ? tmp$ : throwCCE());
    } else
      tmp$_0 = new Either$Left(new Error_0('Element is not a number'));
    return tmp$_0;
  }
  function JsonScalar() {
  }
  JsonScalar.prototype.deepEquals_vzh9da$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, NumberJsonScalar)) {
      if (Kotlin.isType(this, NumberJsonScalar)) {
        var x = numberToDouble(element.value) - numberToDouble(this.value);
        tmp$ = JsMath.abs(x) === 0.0;
      } else if (Kotlin.isType(this, IntJsonScalar)) {
        var x_0 = numberToDouble(element.value) - this.value;
        tmp$ = JsMath.abs(x_0) === 0.0;
      } else
        tmp$ = equals(element.value, this.value);
    } else if (Kotlin.isType(element, IntJsonScalar)) {
      if (Kotlin.isType(this, NumberJsonScalar)) {
        var x_1 = element.value - numberToDouble(this.value);
        tmp$ = JsMath.abs(x_1) === 0.0;
      } else if (Kotlin.isType(this, IntJsonScalar)) {
        var x_2 = element.value - this.value;
        tmp$ = JsMath.abs(x_2) === 0.0;
      } else
        tmp$ = equals(element.value, this.value);
    } else if (Kotlin.isType(element, JsonScalar))
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
  function BooleanJsonScalar(value) {
    this.value_lxrpsx$_0 = value;
  }
  Object.defineProperty(BooleanJsonScalar.prototype, 'value', {
    get: function () {
      return this.value_lxrpsx$_0;
    }
  });
  BooleanJsonScalar.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BooleanJsonScalar',
    interfaces: [JsonScalar]
  };
  BooleanJsonScalar.prototype.component1 = function () {
    return this.value;
  };
  BooleanJsonScalar.prototype.copy_6taknv$ = function (value) {
    return new BooleanJsonScalar(value === void 0 ? this.value : value);
  };
  BooleanJsonScalar.prototype.toString = function () {
    return 'BooleanJsonScalar(value=' + Kotlin.toString(this.value) + ')';
  };
  BooleanJsonScalar.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    return result;
  };
  BooleanJsonScalar.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.value, other.value))));
  };
  function StringJsonScalar(value) {
    this.value_4423hw$_0 = value;
  }
  Object.defineProperty(StringJsonScalar.prototype, 'value', {
    get: function () {
      return this.value_4423hw$_0;
    }
  });
  StringJsonScalar.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'StringJsonScalar',
    interfaces: [JsonScalar]
  };
  StringJsonScalar.prototype.component1 = function () {
    return this.value;
  };
  StringJsonScalar.prototype.copy_61zpoe$ = function (value) {
    return new StringJsonScalar(value === void 0 ? this.value : value);
  };
  StringJsonScalar.prototype.toString = function () {
    return 'StringJsonScalar(value=' + Kotlin.toString(this.value) + ')';
  };
  StringJsonScalar.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    return result;
  };
  StringJsonScalar.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.value, other.value))));
  };
  function NumberJsonScalar(value) {
    this.value_oj18x8$_0 = value;
  }
  Object.defineProperty(NumberJsonScalar.prototype, 'value', {
    get: function () {
      return this.value_oj18x8$_0;
    }
  });
  NumberJsonScalar.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NumberJsonScalar',
    interfaces: [JsonScalar]
  };
  NumberJsonScalar.prototype.component1 = function () {
    return this.value;
  };
  NumberJsonScalar.prototype.copy_3p81yu$ = function (value) {
    return new NumberJsonScalar(value === void 0 ? this.value : value);
  };
  NumberJsonScalar.prototype.toString = function () {
    return 'NumberJsonScalar(value=' + Kotlin.toString(this.value) + ')';
  };
  NumberJsonScalar.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    return result;
  };
  NumberJsonScalar.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.value, other.value))));
  };
  function IntJsonScalar(value) {
    this.value_d7u1kq$_0 = value;
  }
  Object.defineProperty(IntJsonScalar.prototype, 'value', {
    get: function () {
      return this.value_d7u1kq$_0;
    }
  });
  IntJsonScalar.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IntJsonScalar',
    interfaces: [JsonScalar]
  };
  IntJsonScalar.prototype.component1 = function () {
    return this.value;
  };
  IntJsonScalar.prototype.copy_za3lpa$ = function (value) {
    return new IntJsonScalar(value === void 0 ? this.value : value);
  };
  IntJsonScalar.prototype.toString = function () {
    return 'IntJsonScalar(value=' + Kotlin.toString(this.value) + ')';
  };
  IntJsonScalar.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    return result;
  };
  IntJsonScalar.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.value, other.value))));
  };
  function identity(t) {
    return t;
  }
  function partitionList($receiver) {
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var tmp$_0;
      if ((tmp$_0 = element.left()) != null) {
        destination.add_11rb$(tmp$_0);
      }}
    var lefts = destination;
    var destination_0 = ArrayList_init();
    var tmp$_1;
    tmp$_1 = $receiver.iterator();
    while (tmp$_1.hasNext()) {
      var element_0 = tmp$_1.next();
      var tmp$_0_0;
      if ((tmp$_0_0 = element_0.right()) != null) {
        destination_0.add_11rb$(tmp$_0_0);
      }}
    var rights = destination_0;
    return new Pair(lefts, rights);
  }
  function partitionPairList($receiver) {
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (element.second.left() != null)
        destination.add_11rb$(element);
    }
    var destination_0 = ArrayList_init_0(collectionSizeOrDefault(destination, 10));
    var tmp$_0;
    tmp$_0 = destination.iterator();
    while (tmp$_0.hasNext()) {
      var item = tmp$_0.next();
      destination_0.add_11rb$(new Pair(item.first, ensureNotNull(item.second.left())));
    }
    var lefts = destination_0;
    var destination_1 = ArrayList_init();
    var tmp$_1;
    tmp$_1 = $receiver.iterator();
    while (tmp$_1.hasNext()) {
      var element_0 = tmp$_1.next();
      if (element_0.second.right() != null)
        destination_1.add_11rb$(element_0);
    }
    var destination_2 = ArrayList_init_0(collectionSizeOrDefault(destination_1, 10));
    var tmp$_2;
    tmp$_2 = destination_1.iterator();
    while (tmp$_2.hasNext()) {
      var item_0 = tmp$_2.next();
      destination_2.add_11rb$(new Pair(item_0.first, ensureNotNull(item_0.second.right())));
    }
    var rights = destination_2;
    return new Pair(lefts, rights);
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
  Either.prototype.fold_hgftkq$ = function (left, fnR) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = left;
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = fnR(this.r);
    else
      tmp$ = Kotlin.noWhenBranchMatched();
    return tmp$;
  };
  Either.prototype.rightOrDefault_mh5how$ = function (left) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(this, Either$Left))
      tmp$_0 = left;
    else if (Kotlin.isType(this, Either$Right))
      tmp$_0 = (tmp$ = this.r) == null || Kotlin.isType(tmp$, Any) ? tmp$ : throwCCE();
    else
      tmp$_0 = Kotlin.noWhenBranchMatched();
    return tmp$_0;
  };
  Either.prototype.mapEither_ik7j40$ = function (fnL, fnR) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = new Either$Left(fnL(this.l));
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = fnR(this.r);
    else
      tmp$ = Kotlin.noWhenBranchMatched();
    return tmp$;
  };
  Either.prototype.map_apdcv9$ = function (fnL, fnR) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = new Either$Left(fnL(this.l));
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = new Either$Right(fnR(this.r));
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
  Either.prototype.mapLeft_2o04qz$ = function (fn) {
    var tmp$;
    if (Kotlin.isType(this, Either$Left))
      tmp$ = new Either$Left(fn(this.l));
    else if (Kotlin.isType(this, Either$Right))
      tmp$ = this;
    else
      tmp$ = Kotlin.noWhenBranchMatched();
    return tmp$;
  };
  Either.prototype.left = function () {
    if (Kotlin.isType(this, Either$Left))
      return this.l;
    else if (Kotlin.isType(this, Either$Right))
      return null;
    else
      return Kotlin.noWhenBranchMatched();
  };
  Either.prototype.right = function () {
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
  function SchemaResult() {
  }
  SchemaResult.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SchemaResult',
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
  function SchemaParseResult(rule, errors, refs, index) {
    this.rule = rule;
    this.errors = errors;
    this.refs = refs;
    this.index = index;
  }
  SchemaParseResult.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SchemaParseResult',
    interfaces: []
  };
  SchemaParseResult.prototype.component1 = function () {
    return this.rule;
  };
  SchemaParseResult.prototype.component2 = function () {
    return this.errors;
  };
  SchemaParseResult.prototype.component3 = function () {
    return this.refs;
  };
  SchemaParseResult.prototype.component4 = function () {
    return this.index;
  };
  SchemaParseResult.prototype.copy_kffptr$ = function (rule, errors, refs, index) {
    return new SchemaParseResult(rule === void 0 ? this.rule : rule, errors === void 0 ? this.errors : errors, refs === void 0 ? this.refs : refs, index === void 0 ? this.index : index);
  };
  SchemaParseResult.prototype.toString = function () {
    return 'SchemaParseResult(rule=' + Kotlin.toString(this.rule) + (', errors=' + Kotlin.toString(this.errors)) + (', refs=' + Kotlin.toString(this.refs)) + (', index=' + Kotlin.toString(this.index)) + ')';
  };
  SchemaParseResult.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    result = result * 31 + Kotlin.hashCode(this.errors) | 0;
    result = result * 31 + Kotlin.hashCode(this.refs) | 0;
    result = result * 31 + Kotlin.hashCode(this.index) | 0;
    return result;
  };
  SchemaParseResult.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.rule, other.rule) && Kotlin.equals(this.errors, other.errors) && Kotlin.equals(this.refs, other.refs) && Kotlin.equals(this.index, other.index)))));
  };
  function DraftParsers() {
    DraftParsers_instance = this;
    this.DRAFT_4 = new SchemaRuleParser(listOf([ConstRuleParser_getInstance(), EnumRuleParser_getInstance(), TypeRuleParser_getInstance(), new AllOfRuleParser(Draft4ParserFactory_getInstance()), new AnyOfRuleParser(Draft4ParserFactory_getInstance()), new NotRuleParser(Draft4ParserFactory_getInstance()), new OneOfRuleParser(Draft4ParserFactory_getInstance()), new IfThenElseRuleParser(Draft4ParserFactory_getInstance()), ConditionalExclusiveMaximumRuleParser_getInstance(), ConditionalExclusiveMinimumRuleParser_getInstance(), RequiredRuleParser_getInstance(), MultipleOfRuleParser_getInstance(), MinLengthRuleParser_getInstance(), MaxLengthRuleParser_getInstance(), MaxPropertiesRuleParser_getInstance(), MinPropertiesRuleParser_getInstance(), new AdditionalPropertiesRuleParser(Draft4ParserFactory_getInstance()), new DependenciesRuleParser(Draft4ParserFactory_getInstance()), new PropertiesRuleParser(Draft4ParserFactory_getInstance()), MaxItemsRuleParser_getInstance(), MinItemsRuleParser_getInstance(), PatternRuleParser_getInstance(), new ItemsRuleParser(Draft4ParserFactory_getInstance()), new AdditionalItemsRuleParser(Draft4ParserFactory_getInstance()), new PatternPropertiesRuleParser(Draft4ParserFactory_getInstance()), StringFormatRuleParser$Companion_getInstance().default(), UniqueItemsRuleParser_getInstance()]));
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
      if ((tmp$_0_0 = element_1.left()) != null) {
        destination_1.add_11rb$(tmp$_0_0);
      }}
    var errors = flatten(destination_1);
    var destination_2 = ArrayList_init();
    var tmp$_2;
    tmp$_2 = eitherList.iterator();
    while (tmp$_2.hasNext()) {
      var element_2 = tmp$_2.next();
      var tmp$_0_1;
      if ((tmp$_0_1 = element_2.right()) != null) {
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
    var results = destination;
    return results;
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
    return asArray(element.get_61zpoe$('enum')).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
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
      tmp$ = this.parseScalarElement_0(typeEntry).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
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
      if ((tmp$_0_0 = element.left()) != null) {
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
        if ((tmp$_0_1 = element_0.right()) != null) {
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
    return asScalar(element).mapEither_ik7j40$(getCallableRef('identity', function (p1) {
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
    return asString_0(scalar).fold_hfmbsx$(TypeRuleParser$parseScalarElement$lambda_0, TypeRuleParser$parseScalarElement$lambda_1(this));
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
    return asString_0(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
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
    return asBoolean_0(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
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
    return asNumber_0(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
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
    return numberToDouble(num) % 1.0 === 0.0 ? emptyList() : listOf_0(new Error_0('Value is not an integer'));
  }
  IntegerRule.prototype.evalInteger_0 = function (scalar) {
    return asNumber_0(scalar).fold_hfmbsx$(getCallableRef('listOf', function (p1) {
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
  function BooleanAdditionalItemsRule(itemsKeyExists, itemsIsObject, amountOfItems, allowAdditional) {
    this.itemsKeyExists = itemsKeyExists;
    this.itemsIsObject = itemsIsObject;
    this.amountOfItems = amountOfItems;
    this.allowAdditional = allowAdditional;
  }
  function BooleanAdditionalItemsRule$eval$lambda(this$BooleanAdditionalItemsRule) {
    return function (it) {
      return this$BooleanAdditionalItemsRule.eval_0(it);
    };
  }
  BooleanAdditionalItemsRule.prototype.eval_vzh9da$ = function (element) {
    return asArray(element).map_r1ursk$(BooleanAdditionalItemsRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  BooleanAdditionalItemsRule.prototype.eval_0 = function (array) {
    return this.itemsIsObject || !this.itemsKeyExists ? emptyList() : !this.allowAdditional && array.elements().size > this.amountOfItems ? listOf_0(new Error_0('Array has ' + (array.elements().size > this.amountOfItems) + ' more items')) : emptyList();
  };
  BooleanAdditionalItemsRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BooleanAdditionalItemsRule',
    interfaces: [ValidationRule]
  };
  BooleanAdditionalItemsRule.prototype.component1 = function () {
    return this.itemsKeyExists;
  };
  BooleanAdditionalItemsRule.prototype.component2 = function () {
    return this.itemsIsObject;
  };
  BooleanAdditionalItemsRule.prototype.component3 = function () {
    return this.amountOfItems;
  };
  BooleanAdditionalItemsRule.prototype.component4 = function () {
    return this.allowAdditional;
  };
  BooleanAdditionalItemsRule.prototype.copy_aoxpz9$ = function (itemsKeyExists, itemsIsObject, amountOfItems, allowAdditional) {
    return new BooleanAdditionalItemsRule(itemsKeyExists === void 0 ? this.itemsKeyExists : itemsKeyExists, itemsIsObject === void 0 ? this.itemsIsObject : itemsIsObject, amountOfItems === void 0 ? this.amountOfItems : amountOfItems, allowAdditional === void 0 ? this.allowAdditional : allowAdditional);
  };
  BooleanAdditionalItemsRule.prototype.toString = function () {
    return 'BooleanAdditionalItemsRule(itemsKeyExists=' + Kotlin.toString(this.itemsKeyExists) + (', itemsIsObject=' + Kotlin.toString(this.itemsIsObject)) + (', amountOfItems=' + Kotlin.toString(this.amountOfItems)) + (', allowAdditional=' + Kotlin.toString(this.allowAdditional)) + ')';
  };
  BooleanAdditionalItemsRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.itemsKeyExists) | 0;
    result = result * 31 + Kotlin.hashCode(this.itemsIsObject) | 0;
    result = result * 31 + Kotlin.hashCode(this.amountOfItems) | 0;
    result = result * 31 + Kotlin.hashCode(this.allowAdditional) | 0;
    return result;
  };
  BooleanAdditionalItemsRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.itemsKeyExists, other.itemsKeyExists) && Kotlin.equals(this.itemsIsObject, other.itemsIsObject) && Kotlin.equals(this.amountOfItems, other.amountOfItems) && Kotlin.equals(this.allowAdditional, other.allowAdditional)))));
  };
  function ObjectAdditionalItemsRule(itemsKeyExists, itemsIsObject, amountOfItems, rule) {
    this.itemsKeyExists = itemsKeyExists;
    this.itemsIsObject = itemsIsObject;
    this.amountOfItems = amountOfItems;
    this.rule = rule;
  }
  function ObjectAdditionalItemsRule$eval$lambda(this$ObjectAdditionalItemsRule) {
    return function (it) {
      return this$ObjectAdditionalItemsRule.eval_0(it);
    };
  }
  ObjectAdditionalItemsRule.prototype.eval_vzh9da$ = function (element) {
    return asArray(element).map_r1ursk$(ObjectAdditionalItemsRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  ObjectAdditionalItemsRule.prototype.eval_0 = function (array) {
    var tmp$;
    if (this.itemsIsObject || !this.itemsKeyExists)
      tmp$ = emptyList();
    else if (array.elements().size > this.amountOfItems) {
      var startIndex = array.elements().size - this.amountOfItems | 0;
      var elementsToTest = array.elements().subList_vux9f0$(startIndex, array.elements().size);
      var destination = ArrayList_init();
      var tmp$_0;
      tmp$_0 = elementsToTest.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        var list = this.rule.eval_vzh9da$(element);
        addAll(destination, list);
      }
      tmp$ = destination;
    } else
      tmp$ = emptyList();
    return tmp$;
  };
  ObjectAdditionalItemsRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ObjectAdditionalItemsRule',
    interfaces: [ValidationRule]
  };
  ObjectAdditionalItemsRule.prototype.component1 = function () {
    return this.itemsKeyExists;
  };
  ObjectAdditionalItemsRule.prototype.component2 = function () {
    return this.itemsIsObject;
  };
  ObjectAdditionalItemsRule.prototype.component3 = function () {
    return this.amountOfItems;
  };
  ObjectAdditionalItemsRule.prototype.component4 = function () {
    return this.rule;
  };
  ObjectAdditionalItemsRule.prototype.copy_rjis05$ = function (itemsKeyExists, itemsIsObject, amountOfItems, rule) {
    return new ObjectAdditionalItemsRule(itemsKeyExists === void 0 ? this.itemsKeyExists : itemsKeyExists, itemsIsObject === void 0 ? this.itemsIsObject : itemsIsObject, amountOfItems === void 0 ? this.amountOfItems : amountOfItems, rule === void 0 ? this.rule : rule);
  };
  ObjectAdditionalItemsRule.prototype.toString = function () {
    return 'ObjectAdditionalItemsRule(itemsKeyExists=' + Kotlin.toString(this.itemsKeyExists) + (', itemsIsObject=' + Kotlin.toString(this.itemsIsObject)) + (', amountOfItems=' + Kotlin.toString(this.amountOfItems)) + (', rule=' + Kotlin.toString(this.rule)) + ')';
  };
  ObjectAdditionalItemsRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.itemsKeyExists) | 0;
    result = result * 31 + Kotlin.hashCode(this.itemsIsObject) | 0;
    result = result * 31 + Kotlin.hashCode(this.amountOfItems) | 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    return result;
  };
  ObjectAdditionalItemsRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.itemsKeyExists, other.itemsKeyExists) && Kotlin.equals(this.itemsIsObject, other.itemsIsObject) && Kotlin.equals(this.amountOfItems, other.amountOfItems) && Kotlin.equals(this.rule, other.rule)))));
  };
  function AdditionalItemsRuleParser(factory) {
    this.factory = factory;
    this.key_0 = 'additionalItems';
  }
  AdditionalItemsRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.containsKey_61zpoe$(this.key_0);
  };
  function AdditionalItemsRuleParser$parse$lambda(closure$itemsProp, closure$itemsIsObject, closure$itemsArraySize) {
    return function (it) {
      return new ObjectAdditionalItemsRule(closure$itemsProp != null, closure$itemsIsObject, closure$itemsArraySize, it);
    };
  }
  AdditionalItemsRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var itemsProp = element.get_61zpoe$('items');
    var itemsIsObject = Kotlin.isType(itemsProp, JsonObject);
    var itemsArraySize = Kotlin.isType(itemsProp, JsonArray) ? itemsProp.elements().size : 0;
    var entry = element.get_61zpoe$(this.key_0);
    if (Kotlin.isType(entry, BooleanJsonScalar))
      tmp$ = new Either$Right(new BooleanAdditionalItemsRule(itemsProp != null, itemsIsObject, itemsArraySize, entry.value));
    else if (Kotlin.isType(entry, JsonObject))
      tmp$ = this.factory.make().parse_3boyfh$(entry).map_r1ursk$(AdditionalItemsRuleParser$parse$lambda(itemsProp, itemsIsObject, itemsArraySize));
    else
      tmp$ = new Either$Left(listOf_0(new Error_0("'additionalItems' key must be boolean or object")));
    return tmp$;
  };
  AdditionalItemsRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AdditionalItemsRuleParser',
    interfaces: [RuleParser]
  };
  AdditionalItemsRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  AdditionalItemsRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new AdditionalItemsRuleParser(factory === void 0 ? this.factory : factory);
  };
  AdditionalItemsRuleParser.prototype.toString = function () {
    return 'AdditionalItemsRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  AdditionalItemsRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  AdditionalItemsRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.factory, other.factory))));
  };
  function SingleItemsRule(rule) {
    this.rule = rule;
  }
  function SingleItemsRule$eval$lambda(this$SingleItemsRule) {
    return function (array) {
      var $receiver = array.elements();
      var destination = ArrayList_init();
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var list = this$SingleItemsRule.rule.eval_vzh9da$(element);
        addAll(destination, list);
      }
      return destination;
    };
  }
  SingleItemsRule.prototype.eval_vzh9da$ = function (element) {
    return asArray(element).map_r1ursk$(SingleItemsRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  SingleItemsRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SingleItemsRule',
    interfaces: [ValidationRule]
  };
  SingleItemsRule.prototype.component1 = function () {
    return this.rule;
  };
  SingleItemsRule.prototype.copy_qx94pn$ = function (rule) {
    return new SingleItemsRule(rule === void 0 ? this.rule : rule);
  };
  SingleItemsRule.prototype.toString = function () {
    return 'SingleItemsRule(rule=' + Kotlin.toString(this.rule) + ')';
  };
  SingleItemsRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    return result;
  };
  SingleItemsRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rule, other.rule))));
  };
  function TupleRule(rules) {
    this.rules = rules;
  }
  function TupleRule$eval$lambda(this$TupleRule) {
    return function (array) {
      var $receiver = zip(array.elements(), this$TupleRule.rules);
      var destination = ArrayList_init();
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var list = element.second.eval_vzh9da$(element.first);
        addAll(destination, list);
      }
      return destination;
    };
  }
  TupleRule.prototype.eval_vzh9da$ = function (element) {
    return asArray(element).map_r1ursk$(TupleRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  TupleRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TupleRule',
    interfaces: [ValidationRule]
  };
  TupleRule.prototype.component1 = function () {
    return this.rules;
  };
  TupleRule.prototype.copy_6vpe0g$ = function (rules) {
    return new TupleRule(rules === void 0 ? this.rules : rules);
  };
  TupleRule.prototype.toString = function () {
    return 'TupleRule(rules=' + Kotlin.toString(this.rules) + ')';
  };
  TupleRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rules) | 0;
    return result;
  };
  TupleRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.rules, other.rules))));
  };
  function ItemsRuleParser(factory) {
    this.factory = factory;
    this.key_0 = 'items';
  }
  ItemsRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.containsKey_61zpoe$(this.key_0);
  };
  ItemsRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var entry = element.get_61zpoe$(this.key_0);
    if (Kotlin.isType(entry, JsonObject))
      tmp$ = this.parseSingleItems_0(entry);
    else if (Kotlin.isType(entry, JsonArray))
      tmp$ = this.parseTuple_0(entry);
    else
      tmp$ = new Either$Left(listOf_0(new Error_0("'items' is neither an object or an array")));
    return tmp$;
  };
  function ItemsRuleParser$parseSingleItems$lambda(rule) {
    return new SingleItemsRule(rule);
  }
  ItemsRuleParser.prototype.parseSingleItems_0 = function (obj) {
    return this.factory.make().parse_3boyfh$(obj).map_r1ursk$(ItemsRuleParser$parseSingleItems$lambda);
  };
  ItemsRuleParser.prototype.parseTuple_0 = function (array) {
    var schemaParser = this.factory.make();
    var $receiver = array.elements();
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(asObject(item));
    }
    var tmp$_0 = partitionList(destination);
    var errors = tmp$_0.component1()
    , objs = tmp$_0.component2();
    if (any(errors))
      return new Either$Left(errors);
    var destination_0 = ArrayList_init_0(collectionSizeOrDefault(objs, 10));
    var tmp$_1;
    tmp$_1 = objs.iterator();
    while (tmp$_1.hasNext()) {
      var item_0 = tmp$_1.next();
      destination_0.add_11rb$(schemaParser.parse_3boyfh$(item_0));
    }
    var tmp$_2 = partitionList(destination_0);
    var parseErrors = tmp$_2.component1()
    , schemas = tmp$_2.component2();
    if (any(parseErrors))
      return new Either$Left(flatten(parseErrors));
    return new Either$Right(new TupleRule(schemas));
  };
  ItemsRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ItemsRuleParser',
    interfaces: [RuleParser]
  };
  ItemsRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  ItemsRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new ItemsRuleParser(factory === void 0 ? this.factory : factory);
  };
  ItemsRuleParser.prototype.toString = function () {
    return 'ItemsRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  ItemsRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  ItemsRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.factory, other.factory))));
  };
  function MaxItemsRule(maximum) {
    this.maximum = maximum;
  }
  function MaxItemsRule$eval$lambda(this$MaxItemsRule) {
    return function (it) {
      return this$MaxItemsRule.assertSize_0(it);
    };
  }
  MaxItemsRule.prototype.eval_vzh9da$ = function (element) {
    return asArray(element).map_r1ursk$(MaxItemsRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  MaxItemsRule.prototype.assertSize_0 = function (array) {
    return array.elements().size > this.maximum ? listOf_0(new Error_0('Array must have less than ' + this.maximum + ' elements')) : emptyList();
  };
  MaxItemsRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaxItemsRule',
    interfaces: [ValidationRule]
  };
  MaxItemsRule.prototype.component1 = function () {
    return this.maximum;
  };
  MaxItemsRule.prototype.copy_za3lpa$ = function (maximum) {
    return new MaxItemsRule(maximum === void 0 ? this.maximum : maximum);
  };
  MaxItemsRule.prototype.toString = function () {
    return 'MaxItemsRule(maximum=' + Kotlin.toString(this.maximum) + ')';
  };
  MaxItemsRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.maximum) | 0;
    return result;
  };
  MaxItemsRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.maximum, other.maximum))));
  };
  function MaxItemsRuleParser() {
    MaxItemsRuleParser_instance = this;
    this.key_0 = 'maxItems';
  }
  MaxItemsRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  MaxItemsRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var entry = element.get_61zpoe$(this.key_0);
    if (Kotlin.isType(entry, IntJsonScalar))
      tmp$ = new Either$Right(new MaxItemsRule(entry.value));
    else
      tmp$ = new Either$Left(listOf_0(new Error_0('maxItems must be an integer')));
    return tmp$;
  };
  MaxItemsRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MaxItemsRuleParser',
    interfaces: [RuleParser]
  };
  var MaxItemsRuleParser_instance = null;
  function MaxItemsRuleParser_getInstance() {
    if (MaxItemsRuleParser_instance === null) {
      new MaxItemsRuleParser();
    }return MaxItemsRuleParser_instance;
  }
  function MinItemsRule(minimum) {
    this.minimum = minimum;
  }
  function MinItemsRule$eval$lambda(this$MinItemsRule) {
    return function (it) {
      return this$MinItemsRule.assertSize_0(it);
    };
  }
  MinItemsRule.prototype.eval_vzh9da$ = function (element) {
    return asArray(element).map_r1ursk$(MinItemsRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  MinItemsRule.prototype.assertSize_0 = function (array) {
    return array.elements().size < this.minimum ? listOf_0(new Error_0('Array must have more than ' + this.minimum + ' elements')) : emptyList();
  };
  MinItemsRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinItemsRule',
    interfaces: [ValidationRule]
  };
  MinItemsRule.prototype.component1 = function () {
    return this.minimum;
  };
  MinItemsRule.prototype.copy_za3lpa$ = function (minimum) {
    return new MinItemsRule(minimum === void 0 ? this.minimum : minimum);
  };
  MinItemsRule.prototype.toString = function () {
    return 'MinItemsRule(minimum=' + Kotlin.toString(this.minimum) + ')';
  };
  MinItemsRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minimum) | 0;
    return result;
  };
  MinItemsRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.minimum, other.minimum))));
  };
  function MinItemsRuleParser() {
    MinItemsRuleParser_instance = this;
    this.key_0 = 'minItems';
  }
  MinItemsRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  MinItemsRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var entry = element.get_61zpoe$(this.key_0);
    if (Kotlin.isType(entry, IntJsonScalar))
      tmp$ = new Either$Right(new MinItemsRule(entry.value));
    else
      tmp$ = new Either$Left(listOf_0(new Error_0('minItems must be an integer')));
    return tmp$;
  };
  MinItemsRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MinItemsRuleParser',
    interfaces: [RuleParser]
  };
  var MinItemsRuleParser_instance = null;
  function MinItemsRuleParser_getInstance() {
    if (MinItemsRuleParser_instance === null) {
      new MinItemsRuleParser();
    }return MinItemsRuleParser_instance;
  }
  function UniqueItemsRule(shouldBeUnique) {
    this.shouldBeUnique = shouldBeUnique;
  }
  function UniqueItemsRule$eval$lambda(values) {
    return distinct(values.elements()).size === values.elements().size ? emptyList() : listOf_0(new Error_0('Some elements in the array are not unique'));
  }
  UniqueItemsRule.prototype.eval_vzh9da$ = function (element) {
    if (!this.shouldBeUnique)
      return emptyList();
    return asArray(element).map_r1ursk$(UniqueItemsRule$eval$lambda).rightOrDefault_mh5how$(emptyList());
  };
  UniqueItemsRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UniqueItemsRule',
    interfaces: [ValidationRule]
  };
  UniqueItemsRule.prototype.component1 = function () {
    return this.shouldBeUnique;
  };
  UniqueItemsRule.prototype.copy_6taknv$ = function (shouldBeUnique) {
    return new UniqueItemsRule(shouldBeUnique === void 0 ? this.shouldBeUnique : shouldBeUnique);
  };
  UniqueItemsRule.prototype.toString = function () {
    return 'UniqueItemsRule(shouldBeUnique=' + Kotlin.toString(this.shouldBeUnique) + ')';
  };
  UniqueItemsRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.shouldBeUnique) | 0;
    return result;
  };
  UniqueItemsRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.shouldBeUnique, other.shouldBeUnique))));
  };
  function UniqueItemsRuleParser() {
    UniqueItemsRuleParser_instance = this;
    this.key_0 = 'uniqueItems';
  }
  UniqueItemsRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.containsKey_61zpoe$(this.key_0);
  };
  function UniqueItemsRuleParser$parse$lambda(value) {
    return new UniqueItemsRule(value);
  }
  UniqueItemsRuleParser.prototype.parse_3boyfh$ = function (element) {
    return asBoolean(element.get_61zpoe$(this.key_0)).map_apdcv9$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), UniqueItemsRuleParser$parse$lambda);
  };
  UniqueItemsRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'UniqueItemsRuleParser',
    interfaces: [RuleParser]
  };
  var UniqueItemsRuleParser_instance = null;
  function UniqueItemsRuleParser_getInstance() {
    if (UniqueItemsRuleParser_instance === null) {
      new UniqueItemsRuleParser();
    }return UniqueItemsRuleParser_instance;
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
    var hasAnyErrors = any(flatten(destination));
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
  function AllOfRuleParser(parserFactory) {
    ArrayOfRuleParser.call(this, 'allOf', AllOfRuleParser_init$lambda, AllOfRuleParser_init$lambda_0(parserFactory));
    this.parserFactory = parserFactory;
  }
  function AllOfRuleParser_init$lambda(schemas) {
    return new AllOfRule(schemas);
  }
  function AllOfRuleParser_init$lambda_0(closure$parserFactory) {
    return function (element) {
      return closure$parserFactory.make().parse_3boyfh$(element);
    };
  }
  AllOfRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AllOfRuleParser',
    interfaces: [ArrayOfRuleParser]
  };
  AllOfRuleParser.prototype.component1 = function () {
    return this.parserFactory;
  };
  AllOfRuleParser.prototype.copy_im5jht$ = function (parserFactory) {
    return new AllOfRuleParser(parserFactory === void 0 ? this.parserFactory : parserFactory);
  };
  AllOfRuleParser.prototype.toString = function () {
    return 'AllOfRuleParser(parserFactory=' + Kotlin.toString(this.parserFactory) + ')';
  };
  AllOfRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.parserFactory) | 0;
    return result;
  };
  AllOfRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.parserFactory, other.parserFactory))));
  };
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
        destination.add_11rb$(this$ArrayOfRuleParser.parser(ensureNotNull(asObject(item).right())));
      }
      var listOfEithers = destination;
      return this$ArrayOfRuleParser.flatten_k34c7c$_0(listOfEithers).map_r1ursk$(ArrayOfRuleParser$parse$lambda$lambda(this$ArrayOfRuleParser));
    };
  }
  ArrayOfRuleParser.prototype.parse_3boyfh$ = function (element) {
    return asArray(element.get_61zpoe$(this.key_6qpk8v$_0)).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
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
      if (element.left() != null) {
        first.add_11rb$(element);
      } else {
        second.add_11rb$(element);
      }
    }
    var tmp$_0 = new Pair(first, second);
    var errors = tmp$_0.component1()
    , schemas = tmp$_0.component2();
    var tmp$_1;
    if (errors.isEmpty()) {
      var destination = ArrayList_init_0(collectionSizeOrDefault(schemas, 10));
      var tmp$_2;
      tmp$_2 = schemas.iterator();
      while (tmp$_2.hasNext()) {
        var item = tmp$_2.next();
        destination.add_11rb$(ensureNotNull(item.right()));
      }
      tmp$_1 = new Either$Right(destination);
    } else {
      var destination_0 = ArrayList_init_0(collectionSizeOrDefault(errors, 10));
      var tmp$_3;
      tmp$_3 = errors.iterator();
      while (tmp$_3.hasNext()) {
        var item_0 = tmp$_3.next();
        destination_0.add_11rb$(ensureNotNull(item_0.left()));
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
  function NotRuleParser$parse$lambda_0(it) {
    return new NotRule(it);
  }
  NotRuleParser.prototype.parse_3boyfh$ = function (element) {
    var notValue = ensureNotNull(element.get_61zpoe$(this.key_0));
    return asObject(notValue).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), NotRuleParser$parse$lambda(this)).map_r1ursk$(NotRuleParser$parse$lambda_0);
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
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element_0 = tmp$.next();
      if (!element_0.eval_vzh9da$(element).isEmpty())
        destination.add_11rb$(element_0);
    }
    var nonCompliantRules = destination;
    var onlyOneRuleIsValid = (this.rules.size - nonCompliantRules.size | 0) === 1;
    return onlyOneRuleIsValid ? emptyList() : listOf_0(new Error_0("Schema doesn't conform to only one schema of the oneOf"));
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
  function OneOfRuleParser(parserFactory) {
    ArrayOfRuleParser.call(this, 'oneOf', OneOfRuleParser_init$lambda, OneOfRuleParser_init$lambda_0(parserFactory));
    this.parserFactory = parserFactory;
  }
  function OneOfRuleParser_init$lambda(schemas) {
    return new OneOfRule(schemas);
  }
  function OneOfRuleParser_init$lambda_0(closure$parserFactory) {
    return function (element) {
      return closure$parserFactory.make().parse_3boyfh$(element);
    };
  }
  OneOfRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OneOfRuleParser',
    interfaces: [ArrayOfRuleParser]
  };
  OneOfRuleParser.prototype.component1 = function () {
    return this.parserFactory;
  };
  OneOfRuleParser.prototype.copy_im5jht$ = function (parserFactory) {
    return new OneOfRuleParser(parserFactory === void 0 ? this.parserFactory : parserFactory);
  };
  OneOfRuleParser.prototype.toString = function () {
    return 'OneOfRuleParser(parserFactory=' + Kotlin.toString(this.parserFactory) + ')';
  };
  OneOfRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.parserFactory) | 0;
    return result;
  };
  OneOfRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.parserFactory, other.parserFactory))));
  };
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
      tmp$ = new Either$Right(new IfThenElseRule(ifSchemaEither.right(), thenSchemaEither.right(), elseSchemaEither.right()));
    } else
      tmp$ = new Either$Left(errors);
    return tmp$;
  };
  IfThenElseRuleParser.prototype.leftOrEmptyList_0 = function (either) {
    var tmp$;
    return (tmp$ = either.left()) != null ? tmp$ : emptyList();
  };
  function IfThenElseRuleParser$parse$lambda(this$IfThenElseRuleParser) {
    return function (x) {
      return this$IfThenElseRuleParser.factory.make().parse_3boyfh$(x);
    };
  }
  IfThenElseRuleParser.prototype.parse_0 = function (element) {
    var tmp$, tmp$_0;
    return (tmp$_0 = (tmp$ = element != null ? asObject(element) : null) != null ? tmp$.mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
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
  function ConditionalExclusiveMaximumRuleParser() {
    ConditionalExclusiveMaximumRuleParser_instance = this;
    this.key_0 = 'maximum';
    this.conditionalKey_0 = 'exclusiveMaximum';
  }
  ConditionalExclusiveMaximumRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null || element.get_61zpoe$(this.conditionalKey_0) != null;
  };
  ConditionalExclusiveMaximumRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$, tmp$_0;
    var maximum = element.get_61zpoe$(this.key_0);
    var exclusive = element.get_61zpoe$(this.conditionalKey_0);
    if (maximum == null && exclusive != null)
      return new Either$Left(listOf_0(new Error_0('maximum key must exist to use exclusiveMaximum')));
    var maxInt = asNumber(maximum);
    if (Kotlin.isType(maxInt, Either$Left))
      tmp$_0 = maxInt.mapLeft_2o04qz$(getCallableRef('listOf', function (p1) {
        return listOf_0(p1);
      }));
    else if (Kotlin.isType(maxInt, Either$Right)) {
      if (exclusive == null)
        return new Either$Right(new ConditionalExclusiveMaximumRule(maxInt.r, false));
      var exclusiveBool = asBoolean(exclusive);
      if (Kotlin.isType(exclusiveBool, Either$Left))
        tmp$ = exclusiveBool.mapLeft_2o04qz$(getCallableRef('listOf', function (p1) {
          return listOf_0(p1);
        }));
      else if (Kotlin.isType(exclusiveBool, Either$Right))
        tmp$ = new Either$Right(new ConditionalExclusiveMaximumRule(maxInt.r, exclusiveBool.r));
      else
        tmp$ = Kotlin.noWhenBranchMatched();
      return tmp$;
    } else
      tmp$_0 = Kotlin.noWhenBranchMatched();
    return tmp$_0;
  };
  ConditionalExclusiveMaximumRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ConditionalExclusiveMaximumRuleParser',
    interfaces: [RuleParser]
  };
  var ConditionalExclusiveMaximumRuleParser_instance = null;
  function ConditionalExclusiveMaximumRuleParser_getInstance() {
    if (ConditionalExclusiveMaximumRuleParser_instance === null) {
      new ConditionalExclusiveMaximumRuleParser();
    }return ConditionalExclusiveMaximumRuleParser_instance;
  }
  function ConditionalExclusiveMaximumRule(maximum, exclusive) {
    this.maximum = maximum;
    this.exclusive = exclusive;
  }
  function ConditionalExclusiveMaximumRule$eval$lambda(this$ConditionalExclusiveMaximumRule) {
    return function (it) {
      return this$ConditionalExclusiveMaximumRule.eval_0(it);
    };
  }
  ConditionalExclusiveMaximumRule.prototype.eval_vzh9da$ = function (element) {
    return asNumber(element).map_r1ursk$(ConditionalExclusiveMaximumRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  ConditionalExclusiveMaximumRule.prototype.eval_0 = function (number) {
    return this.exclusive && numberToDouble(number) >= numberToDouble(this.maximum) ? listOf_0(new Error_0(number.toString() + ' is equal or bigger than ' + this.maximum.toString())) : numberToDouble(number) <= numberToDouble(this.maximum) ? emptyList() : listOf_0(new Error_0(number.toString() + ' is bigger than ' + this.maximum.toString()));
  };
  ConditionalExclusiveMaximumRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ConditionalExclusiveMaximumRule',
    interfaces: [ValidationRule]
  };
  ConditionalExclusiveMaximumRule.prototype.component1 = function () {
    return this.maximum;
  };
  ConditionalExclusiveMaximumRule.prototype.component2 = function () {
    return this.exclusive;
  };
  ConditionalExclusiveMaximumRule.prototype.copy_a41xm7$ = function (maximum, exclusive) {
    return new ConditionalExclusiveMaximumRule(maximum === void 0 ? this.maximum : maximum, exclusive === void 0 ? this.exclusive : exclusive);
  };
  ConditionalExclusiveMaximumRule.prototype.toString = function () {
    return 'ConditionalExclusiveMaximumRule(maximum=' + Kotlin.toString(this.maximum) + (', exclusive=' + Kotlin.toString(this.exclusive)) + ')';
  };
  ConditionalExclusiveMaximumRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.maximum) | 0;
    result = result * 31 + Kotlin.hashCode(this.exclusive) | 0;
    return result;
  };
  ConditionalExclusiveMaximumRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.maximum, other.maximum) && Kotlin.equals(this.exclusive, other.exclusive)))));
  };
  function ConditionalExclusiveMinimumRuleParser() {
    ConditionalExclusiveMinimumRuleParser_instance = this;
    this.key_0 = 'minimum';
    this.conditionalKey_0 = 'exclusiveMinimum';
  }
  ConditionalExclusiveMinimumRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null || element.get_61zpoe$(this.conditionalKey_0) != null;
  };
  ConditionalExclusiveMinimumRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$, tmp$_0;
    var minimum = element.get_61zpoe$(this.key_0);
    var exclusive = element.get_61zpoe$(this.conditionalKey_0);
    if (minimum == null && exclusive != null)
      return new Either$Left(listOf_0(new Error_0('minimum key must exist to use exclusiveMinimum')));
    var minInt = asNumber(minimum);
    if (Kotlin.isType(minInt, Either$Left))
      tmp$_0 = minInt.mapLeft_2o04qz$(getCallableRef('listOf', function (p1) {
        return listOf_0(p1);
      }));
    else if (Kotlin.isType(minInt, Either$Right)) {
      if (exclusive == null)
        return new Either$Right(new ConditionalExclusiveMinimumRule(minInt.r, false));
      var exclusiveBool = asBoolean(exclusive);
      if (Kotlin.isType(exclusiveBool, Either$Left))
        tmp$ = exclusiveBool.mapLeft_2o04qz$(getCallableRef('listOf', function (p1) {
          return listOf_0(p1);
        }));
      else if (Kotlin.isType(exclusiveBool, Either$Right))
        tmp$ = new Either$Right(new ConditionalExclusiveMinimumRule(minInt.r, exclusiveBool.r));
      else
        tmp$ = Kotlin.noWhenBranchMatched();
      return tmp$;
    } else
      tmp$_0 = Kotlin.noWhenBranchMatched();
    return tmp$_0;
  };
  ConditionalExclusiveMinimumRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ConditionalExclusiveMinimumRuleParser',
    interfaces: [RuleParser]
  };
  var ConditionalExclusiveMinimumRuleParser_instance = null;
  function ConditionalExclusiveMinimumRuleParser_getInstance() {
    if (ConditionalExclusiveMinimumRuleParser_instance === null) {
      new ConditionalExclusiveMinimumRuleParser();
    }return ConditionalExclusiveMinimumRuleParser_instance;
  }
  function ConditionalExclusiveMinimumRule(minimum, exclusive) {
    this.minimum = minimum;
    this.exclusive = exclusive;
  }
  function ConditionalExclusiveMinimumRule$eval$lambda(this$ConditionalExclusiveMinimumRule) {
    return function (it) {
      return this$ConditionalExclusiveMinimumRule.eval_0(it);
    };
  }
  ConditionalExclusiveMinimumRule.prototype.eval_vzh9da$ = function (element) {
    return asNumber(element).map_r1ursk$(ConditionalExclusiveMinimumRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  ConditionalExclusiveMinimumRule.prototype.eval_0 = function (number) {
    return this.exclusive && numberToDouble(number) <= numberToDouble(this.minimum) ? listOf_0(new Error_0(number.toString() + ' is equal or smaller than ' + this.minimum.toString())) : numberToDouble(number) >= numberToDouble(this.minimum) ? emptyList() : listOf_0(new Error_0(number.toString() + ' is smaller than ' + this.minimum.toString()));
  };
  ConditionalExclusiveMinimumRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ConditionalExclusiveMinimumRule',
    interfaces: [ValidationRule]
  };
  ConditionalExclusiveMinimumRule.prototype.component1 = function () {
    return this.minimum;
  };
  ConditionalExclusiveMinimumRule.prototype.component2 = function () {
    return this.exclusive;
  };
  ConditionalExclusiveMinimumRule.prototype.copy_a41xm7$ = function (minimum, exclusive) {
    return new ConditionalExclusiveMinimumRule(minimum === void 0 ? this.minimum : minimum, exclusive === void 0 ? this.exclusive : exclusive);
  };
  ConditionalExclusiveMinimumRule.prototype.toString = function () {
    return 'ConditionalExclusiveMinimumRule(minimum=' + Kotlin.toString(this.minimum) + (', exclusive=' + Kotlin.toString(this.exclusive)) + ')';
  };
  ConditionalExclusiveMinimumRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minimum) | 0;
    result = result * 31 + Kotlin.hashCode(this.exclusive) | 0;
    return result;
  };
  ConditionalExclusiveMinimumRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.minimum, other.minimum) && Kotlin.equals(this.exclusive, other.exclusive)))));
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
    return numberToDouble(number) / numberToDouble(this.multipleOf) % 1 === 0.0 ? emptyList() : listOf_0(new Error_0(number.toString() + ' is not multiple of ' + this.multipleOf.toString()));
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
    return asNumber_0(scalar);
  }
  function NumberRule$getNumber$lambda_1(it) {
    return null;
  }
  function NumberRule$getNumber$lambda_2(x) {
    return x;
  }
  NumberRule_0.prototype.getNumber_vzh9da$ = function (element) {
    return asScalar(element).mapEither_ik7j40$(NumberRule$getNumber$lambda, NumberRule$getNumber$lambda_0).fold_hfmbsx$(NumberRule$getNumber$lambda_1, NumberRule$getNumber$lambda_2);
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
      return asNumber_0(scalar).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
        return listOf_0(p1);
      }), getCallableRef('parse', function ($receiver, p1) {
        return $receiver.parse_3p81yu$(p1);
      }.bind(null, this$NumberRuleParser)));
    };
  }
  NumberRuleParser.prototype.parse_3boyfh$ = function (element) {
    var constElement = ensureNotNull(element.get_61zpoe$(this.key));
    return asScalar(constElement).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), NumberRuleParser$parse$lambda(this));
  };
  NumberRuleParser.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NumberRuleParser',
    interfaces: [RuleParser]
  };
  function AdditionalPropertiesRuleParser(factory) {
    this.factory = factory;
    this.key_0 = 'additionalProperties';
  }
  AdditionalPropertiesRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  AdditionalPropertiesRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var entry = element.get_61zpoe$(this.key_0);
    if (Kotlin.isType(entry, BooleanJsonScalar))
      tmp$ = this.parseBooleanValue_0(entry, element);
    else if (Kotlin.isType(entry, JsonObject))
      tmp$ = this.parseObjectValue_0(entry, element);
    else
      tmp$ = new Either$Left(listOf_0(new Error_0('additionalProperties should be a boolean')));
    return tmp$;
  };
  function AdditionalPropertiesRuleParser$parseObjectValue$lambda(closure$ignoredProperties, closure$pattern) {
    return function (rule) {
      return new ObjectAdditionalPropertiesRule(rule, closure$ignoredProperties, closure$pattern);
    };
  }
  AdditionalPropertiesRuleParser.prototype.parseObjectValue_0 = function (element, parent) {
    var parsedRule = this.factory.make().parse_3boyfh$(element);
    var ignoredProperties = this.scanDeclaredProperties_0(parent);
    var pattern = this.scanPatternProperties_0(parent);
    return parsedRule.map_r1ursk$(AdditionalPropertiesRuleParser$parseObjectValue$lambda(ignoredProperties, pattern));
  };
  AdditionalPropertiesRuleParser.prototype.parseBooleanValue_0 = function (scalar, parent) {
    var allowed = !scalar.value ? this.scanDeclaredProperties_0(parent) : emptyList();
    var pattern = !scalar.value ? this.scanPatternProperties_0(parent) : emptyList();
    return new Either$Right(new BooleanAdditionalPropertiesRule(scalar.value, allowed, pattern));
  };
  function AdditionalPropertiesRuleParser$scanDeclaredProperties$lambda(x) {
    return toList(x.keys());
  }
  AdditionalPropertiesRuleParser.prototype.scanDeclaredProperties_0 = function (parent) {
    return asObject(parent.get_61zpoe$('properties')).map_r1ursk$(AdditionalPropertiesRuleParser$scanDeclaredProperties$lambda).rightOrDefault_mh5how$(emptyList());
  };
  function AdditionalPropertiesRuleParser$scanPatternProperties$lambda(x) {
    var $receiver = x.keys();
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(Regex_init(item));
    }
    return toList(destination);
  }
  AdditionalPropertiesRuleParser.prototype.scanPatternProperties_0 = function (parent) {
    return asObject(parent.get_61zpoe$('patternProperties')).map_r1ursk$(AdditionalPropertiesRuleParser$scanPatternProperties$lambda).rightOrDefault_mh5how$(emptyList());
  };
  AdditionalPropertiesRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AdditionalPropertiesRuleParser',
    interfaces: [RuleParser]
  };
  AdditionalPropertiesRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  AdditionalPropertiesRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new AdditionalPropertiesRuleParser(factory === void 0 ? this.factory : factory);
  };
  AdditionalPropertiesRuleParser.prototype.toString = function () {
    return 'AdditionalPropertiesRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  AdditionalPropertiesRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  AdditionalPropertiesRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.factory, other.factory))));
  };
  function BooleanAdditionalPropertiesRule(unlimitedProperties, allowedList, patternProps) {
    this.unlimitedProperties = unlimitedProperties;
    this.allowedList = allowedList;
    this.patternProps = patternProps;
  }
  BooleanAdditionalPropertiesRule.prototype.eval_vzh9da$ = function (element) {
    var tmp$, tmp$_0;
    if (this.unlimitedProperties)
      tmp$_0 = emptyList();
    else {
      if (Kotlin.isType(element, JsonObject)) {
        var explicitNotAllowed = minus(element.keys(), toSet(this.allowedList));
        var destination = ArrayList_init();
        var tmp$_1;
        tmp$_1 = explicitNotAllowed.iterator();
        loop_label: while (tmp$_1.hasNext()) {
          var element_0 = tmp$_1.next();
          var $receiver = this.patternProps;
          var none$result;
          none$break: do {
            var tmp$_2;
            if (Kotlin.isType($receiver, Collection) && $receiver.isEmpty()) {
              none$result = true;
              break none$break;
            }tmp$_2 = $receiver.iterator();
            while (tmp$_2.hasNext()) {
              var element_1 = tmp$_2.next();
              if (element_1.containsMatchIn_6bul2c$(element_0)) {
                none$result = false;
                break none$break;
              }}
            none$result = true;
          }
           while (false);
          if (none$result)
            destination.add_11rb$(element_0);
        }
        var patternedNotAllowed = destination;
        var destination_0 = ArrayList_init_0(collectionSizeOrDefault(patternedNotAllowed, 10));
        var tmp$_3;
        tmp$_3 = patternedNotAllowed.iterator();
        while (tmp$_3.hasNext()) {
          var item = tmp$_3.next();
          destination_0.add_11rb$(new Error_0(item + ' is not an allowed property'));
        }
        return destination_0;
      } else
        tmp$ = emptyList();
      return tmp$;
    }
    return tmp$_0;
  };
  BooleanAdditionalPropertiesRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BooleanAdditionalPropertiesRule',
    interfaces: [ValidationRule]
  };
  BooleanAdditionalPropertiesRule.prototype.component1 = function () {
    return this.unlimitedProperties;
  };
  BooleanAdditionalPropertiesRule.prototype.component2 = function () {
    return this.allowedList;
  };
  BooleanAdditionalPropertiesRule.prototype.component3 = function () {
    return this.patternProps;
  };
  BooleanAdditionalPropertiesRule.prototype.copy_za1qz8$ = function (unlimitedProperties, allowedList, patternProps) {
    return new BooleanAdditionalPropertiesRule(unlimitedProperties === void 0 ? this.unlimitedProperties : unlimitedProperties, allowedList === void 0 ? this.allowedList : allowedList, patternProps === void 0 ? this.patternProps : patternProps);
  };
  BooleanAdditionalPropertiesRule.prototype.toString = function () {
    return 'BooleanAdditionalPropertiesRule(unlimitedProperties=' + Kotlin.toString(this.unlimitedProperties) + (', allowedList=' + Kotlin.toString(this.allowedList)) + (', patternProps=' + Kotlin.toString(this.patternProps)) + ')';
  };
  BooleanAdditionalPropertiesRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.unlimitedProperties) | 0;
    result = result * 31 + Kotlin.hashCode(this.allowedList) | 0;
    result = result * 31 + Kotlin.hashCode(this.patternProps) | 0;
    return result;
  };
  BooleanAdditionalPropertiesRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.unlimitedProperties, other.unlimitedProperties) && Kotlin.equals(this.allowedList, other.allowedList) && Kotlin.equals(this.patternProps, other.patternProps)))));
  };
  function ObjectAdditionalPropertiesRule(rule, ignoredProperties, patternProps) {
    this.rule = rule;
    this.ignoredProperties = ignoredProperties;
    this.patternProps = patternProps;
  }
  ObjectAdditionalPropertiesRule.prototype.eval_vzh9da$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, JsonObject)) {
      var $receiver = minus(element.keys(), toSet(this.ignoredProperties));
      var destination = ArrayList_init();
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      loop_label: while (tmp$_0.hasNext()) {
        var element_0 = tmp$_0.next();
        var $receiver_0 = this.patternProps;
        var any$result;
        any$break: do {
          var tmp$_1;
          if (Kotlin.isType($receiver_0, Collection) && $receiver_0.isEmpty()) {
            any$result = false;
            break any$break;
          }tmp$_1 = $receiver_0.iterator();
          while (tmp$_1.hasNext()) {
            var element_1 = tmp$_1.next();
            if (element_1.containsMatchIn_6bul2c$(element_0)) {
              any$result = true;
              break any$break;
            }}
          any$result = false;
        }
         while (false);
        if (!any$result)
          destination.add_11rb$(element_0);
      }
      var notAllowed = destination;
      var destination_0 = ArrayList_init();
      var tmp$_2;
      tmp$_2 = notAllowed.iterator();
      while (tmp$_2.hasNext()) {
        var element_2 = tmp$_2.next();
        var tmp$_0_0;
        if ((tmp$_0_0 = element.get_61zpoe$(element_2)) != null) {
          destination_0.add_11rb$(tmp$_0_0);
        }}
      var destination_1 = ArrayList_init_0(collectionSizeOrDefault(destination_0, 10));
      var tmp$_3;
      tmp$_3 = destination_0.iterator();
      while (tmp$_3.hasNext()) {
        var item = tmp$_3.next();
        destination_1.add_11rb$(this.rule.eval_vzh9da$(item));
      }
      return flatten(destination_1);
    } else
      tmp$ = emptyList();
    return tmp$;
  };
  ObjectAdditionalPropertiesRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ObjectAdditionalPropertiesRule',
    interfaces: [ValidationRule]
  };
  ObjectAdditionalPropertiesRule.prototype.component1 = function () {
    return this.rule;
  };
  ObjectAdditionalPropertiesRule.prototype.component2 = function () {
    return this.ignoredProperties;
  };
  ObjectAdditionalPropertiesRule.prototype.component3 = function () {
    return this.patternProps;
  };
  ObjectAdditionalPropertiesRule.prototype.copy_5fe7ro$ = function (rule, ignoredProperties, patternProps) {
    return new ObjectAdditionalPropertiesRule(rule === void 0 ? this.rule : rule, ignoredProperties === void 0 ? this.ignoredProperties : ignoredProperties, patternProps === void 0 ? this.patternProps : patternProps);
  };
  ObjectAdditionalPropertiesRule.prototype.toString = function () {
    return 'ObjectAdditionalPropertiesRule(rule=' + Kotlin.toString(this.rule) + (', ignoredProperties=' + Kotlin.toString(this.ignoredProperties)) + (', patternProps=' + Kotlin.toString(this.patternProps)) + ')';
  };
  ObjectAdditionalPropertiesRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    result = result * 31 + Kotlin.hashCode(this.ignoredProperties) | 0;
    result = result * 31 + Kotlin.hashCode(this.patternProps) | 0;
    return result;
  };
  ObjectAdditionalPropertiesRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.rule, other.rule) && Kotlin.equals(this.ignoredProperties, other.ignoredProperties) && Kotlin.equals(this.patternProps, other.patternProps)))));
  };
  function SchemaDependenciesRule(property, rule) {
    this.property = property;
    this.rule = rule;
  }
  SchemaDependenciesRule.prototype.eval_vzh9da$ = function (element) {
    var tmp$;
    if (Kotlin.isType(element, JsonObject)) {
      var propertyExists = element.containsKey_61zpoe$(this.property);
      return propertyExists ? this.rule.eval_vzh9da$(element) : emptyList();
    } else
      tmp$ = emptyList();
    return tmp$;
  };
  SchemaDependenciesRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SchemaDependenciesRule',
    interfaces: [ValidationRule]
  };
  SchemaDependenciesRule.prototype.component1 = function () {
    return this.property;
  };
  SchemaDependenciesRule.prototype.component2 = function () {
    return this.rule;
  };
  SchemaDependenciesRule.prototype.copy_itxqo9$ = function (property, rule) {
    return new SchemaDependenciesRule(property === void 0 ? this.property : property, rule === void 0 ? this.rule : rule);
  };
  SchemaDependenciesRule.prototype.toString = function () {
    return 'SchemaDependenciesRule(property=' + Kotlin.toString(this.property) + (', rule=' + Kotlin.toString(this.rule)) + ')';
  };
  SchemaDependenciesRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.property) | 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    return result;
  };
  SchemaDependenciesRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.property, other.property) && Kotlin.equals(this.rule, other.rule)))));
  };
  function DependenciesRuleParser(factory) {
    this.factory = factory;
    this.key_0 = 'dependencies';
  }
  DependenciesRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  function DependenciesRuleParser$parse$lambda(this$DependenciesRuleParser) {
    return function (obj) {
      var $receiver = obj.entries();
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(this$DependenciesRuleParser.parseRule_0(item.first, item.second));
      }
      var parseResults = destination;
      var destination_0 = ArrayList_init();
      var tmp$_0;
      tmp$_0 = parseResults.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        var tmp$_0_0;
        if ((tmp$_0_0 = element.left()) != null) {
          destination_0.add_11rb$(tmp$_0_0);
        }}
      var errors = flatten(destination_0);
      var tmp$_1;
      if (any(errors))
        tmp$_1 = new Either$Left(errors);
      else {
        var destination_1 = ArrayList_init();
        var tmp$_2;
        tmp$_2 = parseResults.iterator();
        while (tmp$_2.hasNext()) {
          var element_0 = tmp$_2.next();
          var tmp$_0_1;
          if ((tmp$_0_1 = element_0.right()) != null) {
            destination_1.add_11rb$(tmp$_0_1);
          }}
        tmp$_1 = new Either$Right(new SchemaRule(destination_1));
      }
      return tmp$_1;
    };
  }
  DependenciesRuleParser.prototype.parse_3boyfh$ = function (element) {
    return asObject(element.get_61zpoe$(this.key_0)).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), DependenciesRuleParser$parse$lambda(this));
  };
  DependenciesRuleParser.prototype.parseRule_0 = function (key, element) {
    var tmp$;
    if (Kotlin.isType(element, JsonArray))
      tmp$ = this.parseRequiredRule_0(key, element);
    else if (Kotlin.isType(element, JsonObject))
      tmp$ = this.parseSchemaRule_0(key, element);
    else
      tmp$ = new Either$Left(listOf_0(new Error_0('Value is neither an array or an object')));
    return tmp$;
  };
  function DependenciesRuleParser$parseSchemaRule$lambda(closure$key) {
    return function (it) {
      return new SchemaDependenciesRule(closure$key, it);
    };
  }
  DependenciesRuleParser.prototype.parseSchemaRule_0 = function (key, obj) {
    var rule = this.factory.make().parse_3boyfh$(obj);
    return rule.map_r1ursk$(DependenciesRuleParser$parseSchemaRule$lambda(key));
  };
  DependenciesRuleParser.prototype.parseRequiredRule_0 = function (key, array) {
    var $receiver = array.elements();
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(asString(item));
    }
    var conversions = destination;
    var destination_0 = ArrayList_init();
    var tmp$_0;
    tmp$_0 = conversions.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      var tmp$_0_0;
      if ((tmp$_0_0 = element.left()) != null) {
        destination_0.add_11rb$(tmp$_0_0);
      }}
    var errors = destination_0;
    var tmp$_1;
    if (any(errors))
      tmp$_1 = new Either$Left(errors);
    else {
      var destination_1 = ArrayList_init();
      var tmp$_2;
      tmp$_2 = conversions.iterator();
      while (tmp$_2.hasNext()) {
        var element_0 = tmp$_2.next();
        var tmp$_0_1;
        if ((tmp$_0_1 = element_0.right()) != null) {
          destination_1.add_11rb$(tmp$_0_1);
        }}
      tmp$_1 = new Either$Right(new PropertyDependenciesRule(key, toSet(destination_1)));
    }
    return tmp$_1;
  };
  DependenciesRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DependenciesRuleParser',
    interfaces: [RuleParser]
  };
  DependenciesRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  DependenciesRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new DependenciesRuleParser(factory === void 0 ? this.factory : factory);
  };
  DependenciesRuleParser.prototype.toString = function () {
    return 'DependenciesRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  DependenciesRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  DependenciesRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.factory, other.factory))));
  };
  function PropertyDependenciesRule(property, required) {
    this.property = property;
    this.required = required;
  }
  PropertyDependenciesRule.prototype.eval_vzh9da$ = function (element) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(element, JsonObject)) {
      var propertyValue = element.get_61zpoe$(this.property);
      var missingRequiredProperties = minus(this.required, element.keys());
      if (propertyValue == null)
        tmp$ = emptyList();
      else if (missingRequiredProperties.isEmpty())
        tmp$ = emptyList();
      else {
        var destination = ArrayList_init_0(collectionSizeOrDefault(missingRequiredProperties, 10));
        var tmp$_1;
        tmp$_1 = missingRequiredProperties.iterator();
        while (tmp$_1.hasNext()) {
          var item = tmp$_1.next();
          destination.add_11rb$(new Error_0(item + ' is required because ' + this.property + ' entry exists'));
        }
        tmp$ = destination;
      }
      return tmp$;
    } else
      tmp$_0 = emptyList();
    return tmp$_0;
  };
  PropertyDependenciesRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PropertyDependenciesRule',
    interfaces: [ValidationRule]
  };
  PropertyDependenciesRule.prototype.component1 = function () {
    return this.property;
  };
  PropertyDependenciesRule.prototype.component2 = function () {
    return this.required;
  };
  PropertyDependenciesRule.prototype.copy_qwyf15$ = function (property, required) {
    return new PropertyDependenciesRule(property === void 0 ? this.property : property, required === void 0 ? this.required : required);
  };
  PropertyDependenciesRule.prototype.toString = function () {
    return 'PropertyDependenciesRule(property=' + Kotlin.toString(this.property) + (', required=' + Kotlin.toString(this.required)) + ')';
  };
  PropertyDependenciesRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.property) | 0;
    result = result * 31 + Kotlin.hashCode(this.required) | 0;
    return result;
  };
  PropertyDependenciesRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.property, other.property) && Kotlin.equals(this.required, other.required)))));
  };
  function PropertyAmountParser() {
  }
  PropertyAmountParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key) != null;
  };
  function PropertyAmountParser$parse$lambda$lambda(this$PropertyAmountParser) {
    return function (number) {
      return numberToInt(number) < 0 ? new Either$Left(listOf_0(new Error_0('maxProperties should have a non-negative value'))) : this$PropertyAmountParser.parse_3p81yu$(number);
    };
  }
  function PropertyAmountParser$parse$lambda(this$PropertyAmountParser) {
    return function (scalar) {
      return asNumber_0(scalar).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
        return listOf_0(p1);
      }), PropertyAmountParser$parse$lambda$lambda(this$PropertyAmountParser));
    };
  }
  PropertyAmountParser.prototype.parse_3boyfh$ = function (element) {
    return asScalar(element.get_61zpoe$(this.key)).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), PropertyAmountParser$parse$lambda(this));
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
  function AbstractPropertiesRuleParser(key, factory, parser) {
    this.key = key;
    this.factory_g22d18$_0 = factory;
    this.parser = parser;
  }
  AbstractPropertiesRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key) != null;
  };
  AbstractPropertiesRuleParser.prototype.parse_3boyfh$ = function (element) {
    var tmp$;
    var entry = element.get_61zpoe$(this.key);
    if (Kotlin.isType(entry, JsonObject)) {
      var $receiver = entry.entries();
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        destination.add_11rb$(new Pair(item.first, asObject(item.second)));
      }
      var maybeObjects = destination;
      var tmp$_1 = partitionPairList(maybeObjects);
      var errors = tmp$_1.component1()
      , conversions = tmp$_1.component2();
      if (any(errors)) {
        var destination_0 = ArrayList_init_0(collectionSizeOrDefault(errors, 10));
        var tmp$_2;
        tmp$_2 = errors.iterator();
        while (tmp$_2.hasNext()) {
          var item_0 = tmp$_2.next();
          destination_0.add_11rb$(item_0.second);
        }
        return new Either$Left(destination_0);
      }var destination_1 = ArrayList_init_0(collectionSizeOrDefault(conversions, 10));
      var tmp$_3;
      tmp$_3 = conversions.iterator();
      while (tmp$_3.hasNext()) {
        var item_1 = tmp$_3.next();
        destination_1.add_11rb$(this.parseProperty_315dgn$_0(item_1.first, item_1.second));
      }
      var tmp$_4 = partitionList(destination_1);
      var parseErrors = tmp$_4.component1()
      , rules = tmp$_4.component2();
      var tmp$_5;
      if (any(parseErrors)) {
        var destination_2 = ArrayList_init_0(collectionSizeOrDefault(errors, 10));
        var tmp$_6;
        tmp$_6 = errors.iterator();
        while (tmp$_6.hasNext()) {
          var item_2 = tmp$_6.next();
          destination_2.add_11rb$(item_2.second);
        }
        tmp$_5 = new Either$Left(destination_2);
      } else
        tmp$_5 = new Either$Right(new PropertiesRule(rules));
      return tmp$_5;
    } else
      tmp$ = new Either$Left(listOf_0(new Error_0('Properties must be an object')));
    return tmp$;
  };
  function AbstractPropertiesRuleParser$parseProperty$lambda(this$AbstractPropertiesRuleParser, closure$key) {
    return function (rule) {
      return this$AbstractPropertiesRuleParser.parser(closure$key, rule);
    };
  }
  AbstractPropertiesRuleParser.prototype.parseProperty_315dgn$_0 = function (key, obj) {
    return this.factory_g22d18$_0.make().parse_3boyfh$(obj).map_r1ursk$(AbstractPropertiesRuleParser$parseProperty$lambda(this, key));
  };
  AbstractPropertiesRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AbstractPropertiesRuleParser',
    interfaces: [RuleParser]
  };
  function parseProperty$lambda(key, rule) {
    return new PropertyRule(key, rule);
  }
  var parseProperty;
  function parsePatternProperty$lambda(key, rule) {
    return new PatternPropertyRule(Regex_init(key), rule);
  }
  var parsePatternProperty;
  function PropertiesRuleParser(factory) {
    AbstractPropertiesRuleParser.call(this, 'properties', factory, parseProperty);
    this.factory = factory;
  }
  PropertiesRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PropertiesRuleParser',
    interfaces: [AbstractPropertiesRuleParser]
  };
  PropertiesRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  PropertiesRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new PropertiesRuleParser(factory === void 0 ? this.factory : factory);
  };
  PropertiesRuleParser.prototype.toString = function () {
    return 'PropertiesRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  PropertiesRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  PropertiesRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.factory, other.factory))));
  };
  function PatternPropertiesRuleParser(factory) {
    AbstractPropertiesRuleParser.call(this, 'patternProperties', factory, parsePatternProperty);
    this.factory = factory;
  }
  PatternPropertiesRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PatternPropertiesRuleParser',
    interfaces: [AbstractPropertiesRuleParser]
  };
  PatternPropertiesRuleParser.prototype.component1 = function () {
    return this.factory;
  };
  PatternPropertiesRuleParser.prototype.copy_im5jht$ = function (factory) {
    return new PatternPropertiesRuleParser(factory === void 0 ? this.factory : factory);
  };
  PatternPropertiesRuleParser.prototype.toString = function () {
    return 'PatternPropertiesRuleParser(factory=' + Kotlin.toString(this.factory) + ')';
  };
  PatternPropertiesRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.factory) | 0;
    return result;
  };
  PatternPropertiesRuleParser.prototype.equals = function (other) {
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
  PropertiesRule.prototype.copy_6vpe0g$ = function (rules) {
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
  function PatternPropertyRule(regex, rule) {
    this.regex = regex;
    this.rule = rule;
  }
  function PatternPropertyRule$eval$lambda(it) {
    return emptyList();
  }
  function PatternPropertyRule$eval$lambda_0(this$PatternPropertyRule) {
    return function (obj) {
      var properties = obj.entries_t7befh$(this$PatternPropertyRule.regex);
      var destination = ArrayList_init();
      var tmp$;
      tmp$ = properties.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var list = this$PatternPropertyRule.rule.eval_vzh9da$(element.second);
        addAll(destination, list);
      }
      return destination;
    };
  }
  PatternPropertyRule.prototype.eval_vzh9da$ = function (element) {
    return asObject(element).fold_hfmbsx$(PatternPropertyRule$eval$lambda, PatternPropertyRule$eval$lambda_0(this));
  };
  PatternPropertyRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PatternPropertyRule',
    interfaces: [ValidationRule]
  };
  PatternPropertyRule.prototype.component1 = function () {
    return this.regex;
  };
  PatternPropertyRule.prototype.component2 = function () {
    return this.rule;
  };
  PatternPropertyRule.prototype.copy_q6dnnw$ = function (regex, rule) {
    return new PatternPropertyRule(regex === void 0 ? this.regex : regex, rule === void 0 ? this.rule : rule);
  };
  PatternPropertyRule.prototype.toString = function () {
    return 'PatternPropertyRule(regex=' + Kotlin.toString(this.regex) + (', rule=' + Kotlin.toString(this.rule)) + ')';
  };
  PatternPropertyRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.regex) | 0;
    result = result * 31 + Kotlin.hashCode(this.rule) | 0;
    return result;
  };
  PatternPropertyRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.regex, other.regex) && Kotlin.equals(this.rule, other.rule)))));
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
        if ((tmp$_0_0 = asScalar(element_0).right()) != null) {
          destination.add_11rb$(tmp$_0_0);
        }}
      var destination_0 = ArrayList_init();
      var tmp$_1;
      tmp$_1 = destination.iterator();
      while (tmp$_1.hasNext()) {
        var element_1 = tmp$_1.next();
        var tmp$_0_1;
        if ((tmp$_0_1 = asString_0(element_1).right()) != null) {
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
  function RequiredRule$eval$lambda(it) {
    return emptyList();
  }
  function RequiredRule$eval$lambda_0(this$RequiredRule) {
    return function (x) {
      var differentMembers = subtract(this$RequiredRule.members, x.keys());
      return differentMembers.isEmpty() ? emptyList() : this$RequiredRule.createErrors_0(this$RequiredRule.members);
    };
  }
  RequiredRule.prototype.eval_vzh9da$ = function (element) {
    return asObject(element).fold_hfmbsx$(RequiredRule$eval$lambda, RequiredRule$eval$lambda_0(this));
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
  function MaxLengthRuleParser() {
    MaxLengthRuleParser_instance = this;
    this.key_gjcwos$_0 = 'maxLength';
  }
  Object.defineProperty(MaxLengthRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_gjcwos$_0;
    }
  });
  MaxLengthRuleParser.prototype.parse_za3lpa$ = function (amount) {
    return new Either$Right(new MaxLengthRule(amount));
  };
  MaxLengthRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MaxLengthRuleParser',
    interfaces: [StringLengthRuleParser]
  };
  var MaxLengthRuleParser_instance = null;
  function MaxLengthRuleParser_getInstance() {
    if (MaxLengthRuleParser_instance === null) {
      new MaxLengthRuleParser();
    }return MaxLengthRuleParser_instance;
  }
  function MaxLengthRule(maximum) {
    this.maximum = maximum;
  }
  MaxLengthRule.prototype.eval_61zpoe$ = function (string) {
    return string.length > this.maximum ? listOf_0(new Error_0('String should be longer than ' + this.maximum)) : emptyList();
  };
  MaxLengthRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MaxLengthRule',
    interfaces: [StringLengthRule]
  };
  MaxLengthRule.prototype.component1 = function () {
    return this.maximum;
  };
  MaxLengthRule.prototype.copy_za3lpa$ = function (maximum) {
    return new MaxLengthRule(maximum === void 0 ? this.maximum : maximum);
  };
  MaxLengthRule.prototype.toString = function () {
    return 'MaxLengthRule(maximum=' + Kotlin.toString(this.maximum) + ')';
  };
  MaxLengthRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.maximum) | 0;
    return result;
  };
  MaxLengthRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.maximum, other.maximum))));
  };
  function MinLengthRuleParser() {
    MinLengthRuleParser_instance = this;
    this.key_c0a46i$_0 = 'minLength';
  }
  Object.defineProperty(MinLengthRuleParser.prototype, 'key', {
    configurable: true,
    get: function () {
      return this.key_c0a46i$_0;
    }
  });
  MinLengthRuleParser.prototype.parse_za3lpa$ = function (amount) {
    return new Either$Right(new MinLengthRule(amount));
  };
  MinLengthRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MinLengthRuleParser',
    interfaces: [StringLengthRuleParser]
  };
  var MinLengthRuleParser_instance = null;
  function MinLengthRuleParser_getInstance() {
    if (MinLengthRuleParser_instance === null) {
      new MinLengthRuleParser();
    }return MinLengthRuleParser_instance;
  }
  function MinLengthRule(minimum) {
    this.minimum = minimum;
  }
  MinLengthRule.prototype.eval_61zpoe$ = function (string) {
    return string.length < this.minimum ? listOf_0(new Error_0('String should be shorter than ' + this.minimum)) : emptyList();
  };
  MinLengthRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinLengthRule',
    interfaces: [StringLengthRule]
  };
  MinLengthRule.prototype.component1 = function () {
    return this.minimum;
  };
  MinLengthRule.prototype.copy_za3lpa$ = function (minimum) {
    return new MinLengthRule(minimum === void 0 ? this.minimum : minimum);
  };
  MinLengthRule.prototype.toString = function () {
    return 'MinLengthRule(minimum=' + Kotlin.toString(this.minimum) + ')';
  };
  MinLengthRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minimum) | 0;
    return result;
  };
  MinLengthRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.minimum, other.minimum))));
  };
  function PatternRule(pattern) {
    this.pattern = pattern;
  }
  function PatternRule$eval$lambda(this$PatternRule) {
    return function (it) {
      var matches = this$PatternRule.pattern.containsMatchIn_6bul2c$(it);
      return !matches ? listOf_0(new Error_0("Value doesn't match regex")) : emptyList();
    };
  }
  PatternRule.prototype.eval_vzh9da$ = function (element) {
    return asString(element).map_r1ursk$(PatternRule$eval$lambda(this)).rightOrDefault_mh5how$(emptyList());
  };
  PatternRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PatternRule',
    interfaces: [ValidationRule]
  };
  PatternRule.prototype.component1 = function () {
    return this.pattern;
  };
  PatternRule.prototype.copy_t7befh$ = function (pattern) {
    return new PatternRule(pattern === void 0 ? this.pattern : pattern);
  };
  PatternRule.prototype.toString = function () {
    return 'PatternRule(pattern=' + Kotlin.toString(this.pattern) + ')';
  };
  PatternRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.pattern) | 0;
    return result;
  };
  PatternRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.pattern, other.pattern))));
  };
  function PatternRuleParser() {
    PatternRuleParser_instance = this;
    this.key_0 = 'pattern';
  }
  PatternRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key_0) != null;
  };
  function PatternRuleParser$parse$lambda(it) {
    return new PatternRule(Regex_init(it));
  }
  PatternRuleParser.prototype.parse_3boyfh$ = function (element) {
    return asString(element.get_61zpoe$(this.key_0)).map_apdcv9$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), PatternRuleParser$parse$lambda);
  };
  PatternRuleParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'PatternRuleParser',
    interfaces: [RuleParser]
  };
  var PatternRuleParser_instance = null;
  function PatternRuleParser_getInstance() {
    if (PatternRuleParser_instance === null) {
      new PatternRuleParser();
    }return PatternRuleParser_instance;
  }
  function StringFormatRule(formatter) {
    this.formatter = formatter;
  }
  function StringFormatRule$eval$lambda(maybeError) {
    return maybeError != null ? listOf_0(maybeError) : emptyList();
  }
  StringFormatRule.prototype.eval_vzh9da$ = function (element) {
    return asString(element).map_r1ursk$(this.formatter).map_r1ursk$(StringFormatRule$eval$lambda).rightOrDefault_mh5how$(emptyList());
  };
  StringFormatRule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'StringFormatRule',
    interfaces: [ValidationRule]
  };
  StringFormatRule.prototype.component1 = function () {
    return this.formatter;
  };
  StringFormatRule.prototype.copy_asyhrz$ = function (formatter) {
    return new StringFormatRule(formatter === void 0 ? this.formatter : formatter);
  };
  StringFormatRule.prototype.toString = function () {
    return 'StringFormatRule(formatter=' + Kotlin.toString(this.formatter) + ')';
  };
  StringFormatRule.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.formatter) | 0;
    return result;
  };
  StringFormatRule.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.formatter, other.formatter))));
  };
  function StringFormatRuleParser(formatters) {
    StringFormatRuleParser$Companion_getInstance();
    this.formatters = formatters;
    this.key_0 = 'format';
  }
  function StringFormatRuleParser$Companion() {
    StringFormatRuleParser$Companion_instance = this;
  }
  StringFormatRuleParser$Companion.prototype.default = function () {
    return new StringFormatRuleParser(KnownFormatValidators_getInstance().default());
  };
  StringFormatRuleParser$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var StringFormatRuleParser$Companion_instance = null;
  function StringFormatRuleParser$Companion_getInstance() {
    if (StringFormatRuleParser$Companion_instance === null) {
      new StringFormatRuleParser$Companion();
    }return StringFormatRuleParser$Companion_instance;
  }
  StringFormatRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.containsKey_61zpoe$(this.key_0);
  };
  function StringFormatRuleParser$parse$lambda(this$StringFormatRuleParser) {
    return function (format) {
      var maybeFormatter = this$StringFormatRuleParser.formatters.get_11rb$(format);
      return maybeFormatter == null ? new Either$Left(listOf_0(new Error_0("Couldn't find formatter for format: " + format))) : new Either$Right(new StringFormatRule(maybeFormatter));
    };
  }
  StringFormatRuleParser.prototype.parse_3boyfh$ = function (element) {
    return asString(element.get_61zpoe$(this.key_0)).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), StringFormatRuleParser$parse$lambda(this));
  };
  StringFormatRuleParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'StringFormatRuleParser',
    interfaces: [RuleParser]
  };
  StringFormatRuleParser.prototype.component1 = function () {
    return this.formatters;
  };
  StringFormatRuleParser.prototype.copy_5zqefa$ = function (formatters) {
    return new StringFormatRuleParser(formatters === void 0 ? this.formatters : formatters);
  };
  StringFormatRuleParser.prototype.toString = function () {
    return 'StringFormatRuleParser(formatters=' + Kotlin.toString(this.formatters) + ')';
  };
  StringFormatRuleParser.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.formatters) | 0;
    return result;
  };
  StringFormatRuleParser.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.formatters, other.formatters))));
  };
  function KnownFormatValidators() {
    KnownFormatValidators_instance = this;
  }
  KnownFormatValidators.prototype.default = function () {
    return mapOf([to('date-time', dateTimeValidator), to('email', emailValidator), to('hostname', hostNameValidator), to('ipv4', ipv4Validator), to('ipv6', ipv6Validator), to('uri', uriValidator)]);
  };
  KnownFormatValidators.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'KnownFormatValidators',
    interfaces: []
  };
  var KnownFormatValidators_instance = null;
  function KnownFormatValidators_getInstance() {
    if (KnownFormatValidators_instance === null) {
      new KnownFormatValidators();
    }return KnownFormatValidators_instance;
  }
  var uriRegex;
  var ipv6Regex;
  var ipv4Regex;
  var hostNameRegex;
  var emailRegex;
  function uriValidator$lambda(value) {
    return uriRegex.matches_6bul2c$(value) ? null : new Error_0(value + ' is not in uri format');
  }
  var uriValidator;
  function ipv6Validator$lambda(value) {
    return ipv6Regex.matches_6bul2c$(value) ? null : new Error_0(value + ' is not in ipv6 format');
  }
  var ipv6Validator;
  function ipv4Validator$lambda(value) {
    return ipv4Regex.matches_6bul2c$(value) ? null : new Error_0(value + ' is not in ipv4 format');
  }
  var ipv4Validator;
  function hostNameValidator$lambda(value) {
    return hostNameRegex.matches_6bul2c$(value) ? null : new Error_0(value + ' is not in hostname format');
  }
  var hostNameValidator;
  function emailValidator$lambda(value) {
    return emailRegex.matches_6bul2c$(value) ? null : new Error_0(value + ' is not in email format');
  }
  var emailValidator;
  function dateTimeValidator$lambda(value) {
    try {
      Instant.Companion.parse_61zpoe$(value);
      return null;
    } catch (exception) {
      if (Kotlin.isType(exception, Exception)) {
        var message = value + ' is not date-time because: ' + toString(exception.message);
        return new Error_0(message);
      } else
        throw exception;
    }
  }
  var dateTimeValidator;
  function StringLengthRule() {
  }
  StringLengthRule.prototype.eval_vzh9da$ = function (element) {
    var tmp$;
    var string = this.getString_vzh9da$(element);
    if (string != null) {
      tmp$ = this.eval_61zpoe$(string);
    } else
      tmp$ = emptyList();
    return tmp$;
  };
  function StringLengthRule$getString$lambda(it) {
    return null;
  }
  function StringLengthRule$getString$lambda_0(scalar) {
    return asString_0(scalar);
  }
  function StringLengthRule$getString$lambda_1(it) {
    return null;
  }
  function StringLengthRule$getString$lambda_2(x) {
    return x;
  }
  StringLengthRule.prototype.getString_vzh9da$ = function (element) {
    return asScalar(element).mapEither_ik7j40$(StringLengthRule$getString$lambda, StringLengthRule$getString$lambda_0).fold_hfmbsx$(StringLengthRule$getString$lambda_1, StringLengthRule$getString$lambda_2);
  };
  StringLengthRule.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'StringLengthRule',
    interfaces: [ValidationRule]
  };
  function StringLengthRuleParser() {
  }
  StringLengthRuleParser.prototype.canParse_3boyfh$ = function (element) {
    return element.get_61zpoe$(this.key) != null;
  };
  function StringLengthRuleParser$parse$lambda$lambda(it) {
    return numberToInt(it);
  }
  function StringLengthRuleParser$parse$lambda(this$StringLengthRuleParser) {
    return function (scalar) {
      return asNumber_0(scalar).map_r1ursk$(StringLengthRuleParser$parse$lambda$lambda).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
        return listOf_0(p1);
      }), getCallableRef('parse', function ($receiver, p1) {
        return $receiver.parse_za3lpa$(p1);
      }.bind(null, this$StringLengthRuleParser)));
    };
  }
  StringLengthRuleParser.prototype.parse_3boyfh$ = function (element) {
    var constElement = ensureNotNull(element.get_61zpoe$(this.key));
    return asScalar(constElement).mapEither_ik7j40$(getCallableRef('listOf', function (p1) {
      return listOf_0(p1);
    }), StringLengthRuleParser$parse$lambda(this));
  };
  StringLengthRuleParser.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'StringLengthRuleParser',
    interfaces: [RuleParser]
  };
  var package$org = _.org || (_.org = {});
  var package$validator = package$org.validator || (package$org.validator = {});
  package$validator.JsonElement = JsonElement;
  package$validator.asObject_ia0mbm$ = asObject;
  package$validator.asScalar_ia0mbm$ = asScalar;
  package$validator.asString_ia0mbm$ = asString;
  package$validator.asBoolean_ia0mbm$ = asBoolean;
  package$validator.asNumber_ia0mbm$ = asNumber;
  package$validator.asInteger_ia0mbm$ = asInteger;
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
  package$validator.asString_k22kqd$ = asString_0;
  package$validator.asBoolean_k22kqd$ = asBoolean_0;
  package$validator.asNumber_k22kqd$ = asNumber_0;
  package$validator.asInteger_k22kqd$ = asInteger_0;
  package$validator.JsonScalar = JsonScalar;
  package$validator.BooleanJsonScalar = BooleanJsonScalar;
  package$validator.StringJsonScalar = StringJsonScalar;
  package$validator.NumberJsonScalar = NumberJsonScalar;
  package$validator.IntJsonScalar = IntJsonScalar;
  package$validator.identity_mh5how$ = identity;
  package$validator.partitionList_oc11nf$ = partitionList;
  package$validator.partitionPairList_an3zhs$ = partitionPairList;
  Either.Left = Either$Left;
  Either.Right = Either$Right;
  package$validator.Either = Either;
  package$validator.SchemaResult = SchemaResult;
  package$validator.ValidationRule = ValidationRule;
  package$validator.RuleParser = RuleParser;
  Object.defineProperty(package$validator, 'NothingValidationRule', {
    get: NothingValidationRule_getInstance
  });
  package$validator.OrValidationRule = OrValidationRule;
  package$validator.Error = Error_0;
  var package$rules = package$validator.rules || (package$validator.rules = {});
  package$rules.SchemaParseResult = SchemaParseResult;
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
  var package$array = package$rules.array || (package$rules.array = {});
  package$array.BooleanAdditionalItemsRule = BooleanAdditionalItemsRule;
  package$array.ObjectAdditionalItemsRule = ObjectAdditionalItemsRule;
  package$array.AdditionalItemsRuleParser = AdditionalItemsRuleParser;
  package$array.SingleItemsRule = SingleItemsRule;
  package$array.TupleRule = TupleRule;
  package$array.ItemsRuleParser = ItemsRuleParser;
  package$array.MaxItemsRule = MaxItemsRule;
  Object.defineProperty(package$array, 'MaxItemsRuleParser', {
    get: MaxItemsRuleParser_getInstance
  });
  package$array.MinItemsRule = MinItemsRule;
  Object.defineProperty(package$array, 'MinItemsRuleParser', {
    get: MinItemsRuleParser_getInstance
  });
  package$array.UniqueItemsRule = UniqueItemsRule;
  Object.defineProperty(package$array, 'UniqueItemsRuleParser', {
    get: UniqueItemsRuleParser_getInstance
  });
  var package$boolean = package$rules.boolean || (package$rules.boolean = {});
  package$boolean.AllOfRule = AllOfRule;
  package$boolean.AllOfRuleParser = AllOfRuleParser;
  package$boolean.AnyOfRule = AnyOfRule;
  package$boolean.AnyOfRuleParser = AnyOfRuleParser;
  package$boolean.ArrayOfRuleParser = ArrayOfRuleParser;
  package$boolean.NotRule = NotRule;
  package$boolean.NotRuleParser = NotRuleParser;
  package$boolean.OneOfRule = OneOfRule;
  package$boolean.OneOfRuleParser = OneOfRuleParser;
  var package$conditional = package$rules.conditional || (package$rules.conditional = {});
  package$conditional.IfThenElseRule = IfThenElseRule;
  package$conditional.IfThenElseRuleParser = IfThenElseRuleParser;
  var package$numeric = package$rules.numeric || (package$rules.numeric = {});
  Object.defineProperty(package$numeric, 'ConditionalExclusiveMaximumRuleParser', {
    get: ConditionalExclusiveMaximumRuleParser_getInstance
  });
  package$numeric.ConditionalExclusiveMaximumRule = ConditionalExclusiveMaximumRule;
  Object.defineProperty(package$numeric, 'ConditionalExclusiveMinimumRuleParser', {
    get: ConditionalExclusiveMinimumRuleParser_getInstance
  });
  package$numeric.ConditionalExclusiveMinimumRule = ConditionalExclusiveMinimumRule;
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
  package$object.AdditionalPropertiesRuleParser = AdditionalPropertiesRuleParser;
  package$object.BooleanAdditionalPropertiesRule = BooleanAdditionalPropertiesRule;
  package$object.ObjectAdditionalPropertiesRule = ObjectAdditionalPropertiesRule;
  package$object.SchemaDependenciesRule = SchemaDependenciesRule;
  package$object.DependenciesRuleParser = DependenciesRuleParser;
  package$object.PropertyDependenciesRule = PropertyDependenciesRule;
  package$object.PropertyAmountParser = PropertyAmountParser;
  Object.defineProperty(package$object, 'MaxPropertiesRuleParser', {
    get: MaxPropertiesRuleParser_getInstance
  });
  package$object.MaxPropertiesRule = MaxPropertiesRule;
  Object.defineProperty(package$object, 'MinPropertiesRuleParser', {
    get: MinPropertiesRuleParser_getInstance
  });
  package$object.MinPropertiesRule = MinPropertiesRule;
  package$object.AbstractPropertiesRuleParser = AbstractPropertiesRuleParser;
  Object.defineProperty(package$object, 'parseProperty', {
    get: function () {
      return parseProperty;
    }
  });
  Object.defineProperty(package$object, 'parsePatternProperty', {
    get: function () {
      return parsePatternProperty;
    }
  });
  package$object.PropertiesRuleParser = PropertiesRuleParser;
  package$object.PatternPropertiesRuleParser = PatternPropertiesRuleParser;
  package$object.PropertiesRule = PropertiesRule;
  package$object.PatternPropertyRule = PatternPropertyRule;
  package$object.PropertyRule = PropertyRule;
  Object.defineProperty(package$object, 'RequiredRuleParser', {
    get: RequiredRuleParser_getInstance
  });
  package$object.RequiredRule = RequiredRule;
  var package$string = package$rules.string || (package$rules.string = {});
  Object.defineProperty(package$string, 'MaxLengthRuleParser', {
    get: MaxLengthRuleParser_getInstance
  });
  package$string.MaxLengthRule = MaxLengthRule;
  Object.defineProperty(package$string, 'MinLengthRuleParser', {
    get: MinLengthRuleParser_getInstance
  });
  package$string.MinLengthRule = MinLengthRule;
  package$string.PatternRule = PatternRule;
  Object.defineProperty(package$string, 'PatternRuleParser', {
    get: PatternRuleParser_getInstance
  });
  package$string.StringFormatRule = StringFormatRule;
  Object.defineProperty(StringFormatRuleParser, 'Companion', {
    get: StringFormatRuleParser$Companion_getInstance
  });
  package$string.StringFormatRuleParser = StringFormatRuleParser;
  Object.defineProperty(package$string, 'KnownFormatValidators', {
    get: KnownFormatValidators_getInstance
  });
  Object.defineProperty(package$string, 'uriRegex', {
    get: function () {
      return uriRegex;
    }
  });
  Object.defineProperty(package$string, 'ipv6Regex', {
    get: function () {
      return ipv6Regex;
    }
  });
  Object.defineProperty(package$string, 'ipv4Regex', {
    get: function () {
      return ipv4Regex;
    }
  });
  Object.defineProperty(package$string, 'hostNameRegex', {
    get: function () {
      return hostNameRegex;
    }
  });
  Object.defineProperty(package$string, 'emailRegex', {
    get: function () {
      return emailRegex;
    }
  });
  Object.defineProperty(package$string, 'uriValidator', {
    get: function () {
      return uriValidator;
    }
  });
  Object.defineProperty(package$string, 'ipv6Validator', {
    get: function () {
      return ipv6Validator;
    }
  });
  Object.defineProperty(package$string, 'ipv4Validator', {
    get: function () {
      return ipv4Validator;
    }
  });
  Object.defineProperty(package$string, 'hostNameValidator', {
    get: function () {
      return hostNameValidator;
    }
  });
  Object.defineProperty(package$string, 'emailValidator', {
    get: function () {
      return emailValidator;
    }
  });
  Object.defineProperty(package$string, 'dateTimeValidator', {
    get: function () {
      return dateTimeValidator;
    }
  });
  package$string.StringLengthRule = StringLengthRule;
  package$string.StringLengthRuleParser = StringLengthRuleParser;
  DefaultJsonObject.prototype.deepEquals_vzh9da$ = JsonObject.prototype.deepEquals_vzh9da$;
  DefaultJsonObject.prototype.hasSameChildren_mbcpqv$_0 = JsonObject.prototype.hasSameChildren_mbcpqv$_0;
  DefaultJsonObject.prototype.hasSameKeys_xksyj8$_0 = JsonObject.prototype.hasSameKeys_xksyj8$_0;
  DefaultJsonArray.prototype.deepEquals_vzh9da$ = JsonArray.prototype.deepEquals_vzh9da$;
  DefaultJsonArray.prototype.hasSameElements_hlw545$_0 = JsonArray.prototype.hasSameElements_hlw545$_0;
  DefaultNullElement.prototype.deepEquals_vzh9da$ = NullElement.prototype.deepEquals_vzh9da$;
  BooleanJsonScalar.prototype.deepEquals_vzh9da$ = JsonScalar.prototype.deepEquals_vzh9da$;
  StringJsonScalar.prototype.deepEquals_vzh9da$ = JsonScalar.prototype.deepEquals_vzh9da$;
  NumberJsonScalar.prototype.deepEquals_vzh9da$ = JsonScalar.prototype.deepEquals_vzh9da$;
  IntJsonScalar.prototype.deepEquals_vzh9da$ = JsonScalar.prototype.deepEquals_vzh9da$;
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
  MaxLengthRuleParser.prototype.parse_3boyfh$ = StringLengthRuleParser.prototype.parse_3boyfh$;
  MaxLengthRuleParser.prototype.canParse_3boyfh$ = StringLengthRuleParser.prototype.canParse_3boyfh$;
  MaxLengthRule.prototype.eval_vzh9da$ = StringLengthRule.prototype.eval_vzh9da$;
  MaxLengthRule.prototype.getString_vzh9da$ = StringLengthRule.prototype.getString_vzh9da$;
  MinLengthRuleParser.prototype.parse_3boyfh$ = StringLengthRuleParser.prototype.parse_3boyfh$;
  MinLengthRuleParser.prototype.canParse_3boyfh$ = StringLengthRuleParser.prototype.canParse_3boyfh$;
  MinLengthRule.prototype.eval_vzh9da$ = StringLengthRule.prototype.eval_vzh9da$;
  MinLengthRule.prototype.getString_vzh9da$ = StringLengthRule.prototype.getString_vzh9da$;
  parseProperty = parseProperty$lambda;
  parsePatternProperty = parsePatternProperty$lambda;
  uriRegex = Regex_init("^([a-z0-9+.-]+):(?://(?:((?:[a-z0-9-._~!$&'()*+,;=:]|%[0-9A-F]{2})*)@)?((?:[a-z0-9-._~!$&'()*+,;=]|%[0-9A-F]{2})*)(?::(\\d*))?(/(?:[a-z0-9-._~!$&'()*+,;=:@/]|%[0-9A-F]{2})*)?|(/?(?:[a-z0-9-._~!$&'()*+,;=:@]|%[0-9A-F]{2})+(?:[a-z0-9-._~!$&'()*+,;=:@/]|%[0-9A-F]{2})*)?)(?:\\?((?:[a-z0-9-._~!$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?(?:#((?:[a-z0-9-._~!$&'()*+,;=:/?@]|%[0-9A-F]{2})*))?$");
  ipv6Regex = Regex_init('^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$');
  ipv4Regex = Regex_init('^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$');
  hostNameRegex = Regex_init('^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)+([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$');
  emailRegex = Regex_init('(?:[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*|"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])');
  uriValidator = uriValidator$lambda;
  ipv6Validator = ipv6Validator$lambda;
  ipv4Validator = ipv4Validator$lambda;
  hostNameValidator = hostNameValidator$lambda;
  emailValidator = emailValidator$lambda;
  dateTimeValidator = dateTimeValidator$lambda;
  Kotlin.defineModule('json-schema-validator-schema-validation', _);
  return _;
}));

//# sourceMappingURL=json-schema-validator-schema-validation.js.map
