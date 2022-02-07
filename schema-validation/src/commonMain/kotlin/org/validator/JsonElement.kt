package org.validator

import org.validator.Either.*
import kotlin.math.abs

interface JsonElement {
    fun deepEquals(element: JsonElement): Boolean
}

fun JsonElement?.asObject(): Either<Error, JsonObject> {
    return when(this) {
        is JsonObject -> Right(this)
        else -> Left(Error("Element is not an object"))
    }
}

fun JsonElement?.asScalar(): Either<Error, JsonScalar> {
    return when(this) {
        is JsonScalar -> Right(this)
        else -> Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.string(): Either<Error, String> {
    return when(this) {
        is JsonScalar -> this.string()
        else -> Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.boolean(): Either<Error, Boolean> {
    return when(this) {
        is JsonScalar -> this.boolean()
        else -> Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.double(): Either<Error, Double> {
    return when(this) {
        is JsonScalar -> this.double()
        else -> Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.integer(): Either<Error, Int> {
    return when(this) {
        is JsonScalar -> this.integer()
        else -> Left(Error("Element is not a scalar"))
    }
}

fun JsonElement?.array(): Either<Error, JsonArray> {
    return when(this) {
        is JsonArray -> Right(this)
        else -> Left(Error("Element it not an array"))
    }
}

fun JsonElement?.asNull(): Either<Error, NullElement> {
    return when(this) {
        is NullElement -> Right(this)
        else -> Left(Error("Element it not null"))
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

fun JsonScalar.string(): Either<Error, String> {
    return when(value) {
        is String -> Right(value as String)
        else -> Left(Error("Element is not a string"))
    }
}

fun JsonScalar.boolean(): Either<Error, Boolean> {
    return when(value) {
        is Boolean -> Right(value as Boolean)
        else -> Left(Error("Element is not a boolean"))
    }
}

fun JsonScalar.double(): Either<Error, Double> {
    return when(value) {
        is Double -> Right(value as Double)
        is Int -> Right((value as Int).toDouble())
        else -> Left(Error("Element is not a number"))
    }
}

fun JsonScalar.integer(): Either<Error, Int> {
    return when(value) {
        is Int -> Right(value as Int)
        else -> Left(Error("Element is not a number"))
    }
}

interface JsonScalar : JsonElement {
    val value: Any

    override fun equals(other: Any?): Boolean
    override fun deepEquals(element: JsonElement): Boolean {
        return when(element) {
            is JsonDouble -> {
                when(this) {
                    is JsonDouble -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    is JsonInt -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    else -> element.value == value
                }
            }
            is JsonInt -> {
                when(this) {
                    is JsonDouble -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    is JsonInt -> abs(element.value.toDouble() - value.toDouble()) == 0.0
                    else -> element.value == value
                }
            }
            is JsonScalar -> element.value == value
            else -> false
        }
    }
}
data class JsonBoolean(override val value: Boolean) : JsonScalar
data class JsonString(override val value: String) : JsonScalar
data class JsonDouble(override val value: Double) : JsonScalar
data class JsonInt(override val value: Int) : JsonScalar

fun <T> identity(t: T): T = t

fun <L, R> Collection<Either<L, R>>.partitionList(): Pair<List<L>,List<R>> {
    val lefts = this.mapNotNull { it.left() }
    val rights = this.mapNotNull { it.right() }
    return Pair(lefts, rights)
}

fun <R> Collection<Either<Error, R>>.toEither(): Either<List<Error>, List<R>> {
    val lefts = mutableListOf<Error>()
    val rights = mutableListOf<R>()
    this.forEach {
        when(it) {
            is Left -> lefts.add(it.l)
            is Right -> rights.add(it.r)
        }
    }
    return if (lefts.isNotEmpty()) Left(lefts) else Right(rights)
}

fun <R> Collection<Either<List<Error>, R>>.toFlatEither(): Either<List<Error>, List<R>> {
    val lefts = mutableListOf<Error>()
    val rights = mutableListOf<R>()
    this.forEach {
        when(it) {
            is Left -> lefts.addAll(it.l)
            is Right -> rights.add(it.r)
        }
    }
    return if (lefts.isNotEmpty()) Left(lefts) else Right(rights)
}

fun <L, R, R2> Either<L, R>.withRight(func: (R) -> Either<L, R2> ): Either<L, R2> {
    return when(this) {
        is Left -> this
        is Right -> func(this.r)
    }
}

fun <R> Collection<Either<List<Error>, R>>.flatten(): Pair<List<Error>, List<R>> {
    val lefts = mutableListOf<Error>()
    val rights = mutableListOf<R>()
    this.forEach {
        when(it) {
            is Left -> lefts.addAll(it.l)
            is Right -> rights.add(it.r)
        }
    }
    return Pair(lefts, rights)
}

fun <R, R2> Collection<Either<Collection<Error>, R>>.bubbleErrors(func: (List<R>) -> (R2)): Either<Collection<Error>, R2> {
    val lefts = this.mapNotNull { it.left() }.flatten()
    return if (lefts.isNotEmpty()) Left(lefts)
    else {
        val rights = this.mapNotNull { it.right() }
        Right(func(rights))
    }
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
