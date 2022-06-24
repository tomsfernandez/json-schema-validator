package org.validator

fun enforceFragment(uri: String): String = if (uri.contains("#")) uri else "$uri#"

fun List<Schema>.combine(base: String, path: String, func: (List<SchemaRule>) -> SchemaRule, errors: List<Error> = emptyList()): Schema {
    val schema = combine(func, errors)
    val reference = enforceFragment(resolveUri(base, path))
    return schema.copy(seen = schema.seen + mapOf(reference to schema.rule))
}

fun List<Schema>.combineOnlyBase(base: String, func: (List<SchemaRule>) -> SchemaRule, errors: List<Error> = emptyList()): Schema {
    val schema = combine(func, errors)
    val reference = enforceFragment(base)
    return schema.copy(seen = schema.seen + mapOf(reference to schema.rule))
}

fun List<Schema>.combine(func: (List<SchemaRule>) -> SchemaRule, errors: List<Error> = emptyList()): Schema {
    val schemaErrors = this.flatMap { it.errors }
    val seen = this.map { it.seen }.fold(emptyMap<String, SchemaRule>()) { acc, curr -> acc + curr }
    val rules = this.map { it.rule }
    val rule = func(rules)
    return Schema(rule, schemaErrors + errors, seen)
}

fun Schema.map(base: String, path: String, func: (SchemaRule) -> SchemaRule): Schema {
    val finalRule = func(rule)
    val reference = enforceFragment(resolveUri(base, path))
    return Schema(finalRule, errors, seen + (reference to finalRule))
}

fun Schema.map(func: (SchemaRule) -> SchemaRule): Schema {
    val finalRule = func(rule)
    return Schema(finalRule, errors, seen)
}

data class Schema(val rule: SchemaRule, val errors: List<Error> = emptyList(), val seen: Map<String, SchemaRule> = emptyMap()) {
    constructor(base: String, path: String, errors: List<Error>): this(ErrorRule, errors, mapOf(enforceFragment(resolveUri(base, path)) to ErrorRule))
    constructor(base: String, path: String, error: Error): this(ErrorRule, listOf(error), mapOf(enforceFragment(resolveUri(base, path)) to ErrorRule))
    constructor(error: Error): this(ErrorRule, listOf(error))
    constructor(base: String, path: String, rule: SchemaRule): this(rule, emptyList(), mapOf(enforceFragment(resolveUri(base, path)) to rule))

    fun addSchema(path: String, schema: Schema): Schema = copy(seen = seen + schema.seen + (path to schema.rule))
    fun eval(json: JsonElement): List<RuleError> = rule.eval("", json, this)
}

object ErrorRule: SchemaRule {
    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return emptyList()
    }
}
