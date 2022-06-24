package org.validator

import org.validator.rules.SchemaParser

data class JsonSchemaParserBuilder(val metaSchemas: Map<String, SchemaParser> = emptyMap(), val default: SchemaParser? = null) {

    fun addMetaSchema(uri: String, parser: SchemaParser): JsonSchemaParserBuilder {
        return copy(metaSchemas = metaSchemas + (uri to parser))
    }

    fun setDefault(parser: SchemaParser): JsonSchemaParserBuilder {
        return copy(default = parser)
    }

    fun build(): JsonSchemaParser {
        return JsonSchemaParser(metaSchemas, default)
    }
}

data class JsonSchemaParser(val metaSchemas: Map<String, SchemaParser>, val default: SchemaParser?): SchemaParser {
    override fun parse(base: String, path: String, element: JsonElement): Schema {
        return when(element) {
            is JsonObject -> {
                val schema = element.get("\$schema")
                if (schema == null && default != null) return default.parse(base, path, element)
                return schema.string().fold(Schema(Error("\$schema entry has to be a string value"))) {
                    val parser = metaSchemas.get(it)
                    parser?.parse(base, path, element) ?: Schema(Error("Unknown metaschema: $schema"))
                }
            }
            is JsonBoolean -> {
                return if (default != null)  default.parse(base, path, element)
                else Schema(Error("There isn't a default parser defined to parse this schema"))
            }
            else -> Schema(Error("Unexpected json"))
        }
    }
}
