package org.validator.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
data class Test(val description: String, val comment: String? = "", val schema: JsonElement, val tests: List<TestCase>)

@Serializable
data class TestCase(val description: String, val data: JsonElement, val valid: Boolean)
