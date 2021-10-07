package org.validator.testing

import kotlinx.serialization.json.*
import org.validator.*
import kotlinx.serialization.json.JsonPrimitive as KotlinXJsonPrimitive
import kotlinx.serialization.json.JsonArray as KotlinXJsonArray
import kotlinx.serialization.json.JsonObject as KotlinXJsonObject
import kotlinx.serialization.json.JsonElement as KotlinXJsonElement


fun adapt(json: String): JsonElement {
    val element = Json.parseToJsonElement(json)
    return KotlinXJsonElementAdapter.adapt(element)
}

object KotlinXJsonElementAdapter {

    fun adapt(element: KotlinXJsonElement): JsonElement {
        return when(element) {
            is KotlinXJsonObject -> adaptObject(element)
            is KotlinXJsonArray -> adaptArray(element)
            is KotlinXJsonPrimitive -> adaptScalar(element)
            else -> throw RuntimeException("asdasdasdasdasd")
        }
    }

    private fun adaptObject(obj: KotlinXJsonObject): JsonElement {
        val pairs = obj.entries.map { entry -> Pair(entry.key, adapt(entry.value)) }
        return DefaultJsonObject(pairs.toMap())
    }

    private fun adaptArray(array: KotlinXJsonArray): JsonElement {
        val elements = array.toList().map { x -> adapt(x) }
        return DefaultJsonArray(elements)
    }

    private fun adaptScalar(scalar: KotlinXJsonPrimitive): JsonElement {
        return when(scalar) {
            is JsonNull -> DefaultNullElement
            else -> {
                return when {
                    scalar.intOrNull != null -> DefaultJsonScalar(scalar.int)
                    scalar.booleanOrNull != null -> DefaultJsonScalar(scalar.boolean)
                    scalar.doubleOrNull != null -> DefaultJsonScalar(scalar.double)
                    else -> DefaultJsonScalar(scalar.content)
                }
            }
        }
    }
}
