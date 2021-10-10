package org.validator

import kotlin.math.abs

interface JsonElement {
    fun deepEquals(element: JsonElement): Boolean
}

fun JsonElement?.asObject(): Either<Error, JsonObject> {
    return when(this) {
        is JsonObject -> Either.Right(this)
        else -> Either.Left(Error("Element is not an object"))
    }
}

fun JsonElement?.asScalar(): Either<Error, JsonScalar> {
    return when(this) {
        is JsonScalar -> Either.Right(this)
        else -> Either.Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.asString(): Either<Error, String> {
    return when(this) {
        is JsonScalar -> this.asString()
        else -> Either.Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.asBoolean(): Either<Error, Boolean> {
    return when(this) {
        is JsonScalar -> this.asBoolean()
        else -> Either.Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.asNumber(): Either<Error, Number> {
    return when(this) {
        is JsonScalar -> this.asNumber()
        else -> Either.Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.asInteger(): Either<Error, Int> {
    return when(this) {
        is JsonScalar -> this.asInteger()
        else -> Either.Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.asArray(): Either<Error, JsonArray> {
    return when(this) {
        is JsonArray -> Either.Right(this)
        else -> Either.Left(Error("Element it not an array"))
    }
}

fun JsonElement?.asNull(): Either<Error, NullElement> {
    return when(this) {
        is NullElement -> Either.Right(this)
        else -> Either.Left(Error("Element it not null"))
    }
}

interface JsonObject : JsonElement {
    fun get(name: String): JsonElement?
    fun entries(): List<Pair<String, JsonElement>>
    fun keys(): Set<String>
    fun containsKey(key: String): Boolean
    fun entries(regex: Regex): List<Pair<String, JsonElement>>

    override fun deepEquals(element: JsonElement): Boolean {
        return when(element) {
            is JsonObject -> hasSameKeys(element) && hasSameChildren(element)
            else -> false
        }
    }

    private fun hasSameChildren(element: JsonObject): Boolean {
        val equalElements = element.entries().takeWhile { pair ->
            val value = get(pair.first)
            value?.deepEquals(pair.second) ?: false
        }
        return element.keys().size == equalElements.size
    }

    private fun hasSameKeys(element: JsonObject): Boolean {
        return element.keys() == keys()
    }
}

data class DefaultJsonObject(val map: Map<String, JsonElement>) : JsonObject {
    override fun get(name: String): JsonElement? {
        return map[name]
    }
    override fun entries(): List<Pair<String, JsonElement>> = map.entries.map { it.toPair() }
    override fun keys(): Set<String> = map.keys
    override fun containsKey(key: String): Boolean = map.containsKey(key)

    override fun entries(regex: Regex): List<Pair<String, JsonElement>> {
        return entries().filter { entry -> regex.containsMatchIn(entry.first) }
    }
}

interface JsonArray : JsonElement {
    fun get(index: Int): JsonElement?
    fun elements(): List<JsonElement>

    override fun deepEquals(element: JsonElement): Boolean {
        return when(element) {
            is JsonArray -> hasSameElements(element)
            else -> false
        }
    }

    private fun hasSameElements(array: JsonArray): Boolean {
        return array.elements()
            .zip(elements())
            .map { pair -> pair.first.deepEquals(pair.second) }
            .fold(true) { acc, curr -> acc && curr }
    }
}

data class DefaultJsonArray(private val elements: List<JsonElement>) : JsonArray {
    override fun get(index: Int): JsonElement? = elements.getOrNull(index)
    override fun elements(): List<JsonElement> = elements
}

interface NullElement : JsonElement {
    override fun deepEquals(element: JsonElement): Boolean {
        return when(element) {
            is NullElement -> true
            else -> false
        }
    }
}
object DefaultNullElement : NullElement

fun JsonScalar.asString(): Either<Error, String> {
    return when(value) {
        is String -> Either.Right(value as String)
        else -> Either.Left(Error("Element is not a string"))
    }
}

fun JsonScalar.asBoolean(): Either<Error, Boolean> {
    return when(value) {
        is Boolean -> Either.Right(value as Boolean)
        else -> Either.Left(Error("Element is not a boolean"))
    }
}

fun JsonScalar.asNumber(): Either<Error, Number> {
    return when(value) {
        is Number -> Either.Right(value as Number)
        else -> Either.Left(Error("Element is not a number"))
    }
}

fun JsonScalar.asInteger(): Either<Error, Int> {
    return when(value) {
        is Int -> Either.Right(value as Int)
        else -> Either.Left(Error("Element is not a number"))
    }
}

interface JsonScalar : JsonElement {
    val value: Any

    override fun equals(other: Any?): Boolean
    override fun deepEquals(element: JsonElement): Boolean {
        return when(element) {
            is NumberJsonScalar -> {
                when(this) {
                    is NumberJsonScalar -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    is IntJsonScalar -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    else -> element.value == value
                }
            }
            is IntJsonScalar -> {
                when(this) {
                    is NumberJsonScalar -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    is IntJsonScalar -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    else -> element.value == value
                }
            }
            is JsonScalar -> element.value == value
            else -> false
        }
    }
}
data class BooleanJsonScalar(override val value: Boolean) : JsonScalar
data class StringJsonScalar(override val value: String) : JsonScalar
data class NumberJsonScalar(override val value: Number) : JsonScalar
data class IntJsonScalar(override val value: Int) : JsonScalar

fun <T> identity(t: T): T = t

fun <L, R> Collection<Either<L, R>>.partitionList(): Pair<List<L>,List<R>> {
    val lefts = this.mapNotNull { it.left() }
    val rights = this.mapNotNull { it.right() }
    return Pair(lefts, rights)
}

fun <T, L, R> Collection<Pair<T, Either<L, R>>>.partitionPairList(): Pair<List<Pair<T, L>>,List<Pair<T, R>>> {
    val lefts = this.filter { it.second.left() != null }.map { Pair(it.first, it.second.left() !!) }
    val rights = this.filter { it.second.right() != null }.map { Pair(it.first, it.second.right() !!) }
    return Pair(lefts, rights)
}

sealed class Either<out L, out R> {

    data class Left<out L>(val l: L) : Either<L, Nothing>()

    data class Right<out R>(val r: R) : Either<Nothing, R>()


    fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T {
        return when (this) {
            is Left -> fnL(l)
            is Right -> fnR(r)
        }
    }

    fun <T> fold(left: T, fnR: (R) -> T): T {
        return when (this) {
            is Left -> left
            is Right -> fnR(r)
        }
    }

    fun <T> rightOrDefault(left: T): T {
        return when (this) {
            is Left -> left
            is Right -> r as T
        }
    }

    fun <L2,R2> mapEither(fnL: (L) -> L2, fnR: (R) -> Either<L2, R2>): Either<L2, R2> {
        return when (this) {
            is Left -> Left(fnL(l))
            is Right -> fnR(r)
        }
    }

    fun <L2,R2> map(fnL: (L) -> L2, fnR: (R) -> R2): Either<L2, R2> {
        return when (this) {
            is Left -> Left(fnL(l))
            is Right -> Right(fnR(r))
        }
    }

    fun <R2> map(fn: (R) -> R2): Either<L, R2> {
        return when (this) {
            is Left -> this
            is Right -> Right(fn(r))
        }
    }

    fun <L2> mapLeft(fn: (L) -> L2): Either<L2, R> {
        return when (this) {
            is Left -> Left(fn(l))
            is Right -> this
        }
    }

    fun left(): L? = when (this) {
        is Left -> l
        is Right -> null
    }

    fun right(): R? = when (this) {
        is Left -> null
        is Right -> r
    }
}
