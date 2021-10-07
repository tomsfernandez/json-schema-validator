package org.validator

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
interface JsonScalar : JsonElement {
    val value: Any

    override fun equals(other: Any?): Boolean
    override fun deepEquals(element: JsonElement): Boolean {
        return when(element) {
            is JsonScalar -> element == this
            else -> false
        }
    }
}
data class BooleanJsonScalar(override val value: Boolean) : JsonScalar
data class StringJsonScalar(override val value: String) : JsonScalar
data class NumberJsonScalar(override val value: Number) : JsonScalar

fun <T> identity(t: T): T = t

sealed class Either<out L, out R> {

    data class Left<out L>(val l: L) : Either<L, Nothing>()

    data class Right<out R>(val r: R) : Either<Nothing, R>()


    fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T {
        return when (this) {
            is Left -> fnL(l)
            is Right -> fnR(r)
        }
    }

    fun <T> foldRight(fn: (R) -> T): Either<L, T> {
        return when(this) {
            is Left -> this
            is Right -> Right(fn(r))
        }
    }

    fun <L2,R2> map(fnL: (L) -> L2, fnR: (R) -> Either<L2, R2>): Either<L2, R2> {
        return when (this) {
            is Left -> Left(fnL(l))
            is Right -> fnR(r)
        }
    }

    fun <R2> map(fn: (R) -> R2): Either<L, R2> {
        return when (this) {
            is Left -> this
            is Right -> Right(fn(r))
        }
    }

    fun toLeftValueOrNull(): L? = when (this) {
        is Left -> l
        is Right -> null
    }

    fun toRightValueOrNull(): R? = when (this) {
        is Left -> null
        is Right -> r
    }
}
