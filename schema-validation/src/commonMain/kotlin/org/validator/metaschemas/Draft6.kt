package org.validator.metaschemas

import org.validator.JsonObject
import org.validator.asObject
import org.validator.json.JsonParser
import org.validator.rules.DraftParsers

object Draft6 {

    private val jsonSchemaAsString = """
        {
            "${'$'}schema": "http://json-schema.org/draft-06/schema#",
            "${'$'}id": "http://json-schema.org/draft-06/schema#",
            "title": "Core schema meta-schema",
            "definitions": {
                "schemaArray": {
                    "type": "array",
                    "minItems": 1,
                    "items": { "${'$'}ref": "#" }
                },
                "nonNegativeInteger": {
                    "type": "integer",
                    "minimum": 0
                },
                "nonNegativeIntegerDefault0": {
                    "allOf": [
                        { "${'$'}ref": "#/definitions/nonNegativeInteger" },
                        { "default": 0 }
                    ]
                },
                "simpleTypes": {
                    "enum": [
                        "array",
                        "boolean",
                        "integer",
                        "null",
                        "number",
                        "object",
                        "string"
                    ]
                },
                "stringArray": {
                    "type": "array",
                    "items": { "type": "string" },
                    "uniqueItems": true,
                    "default": []
                }
            },
            "type": ["object", "boolean"],
            "properties": {
                "${'$'}id": {
                    "type": "string",
                    "format": "uri-reference"
                },
                "${'$'}schema": {
                    "type": "string",
                    "format": "uri"
                },
                "${'$'}ref": {
                    "type": "string",
                    "format": "uri-reference"
                },
                "title": {
                    "type": "string"
                },
                "description": {
                    "type": "string"
                },
                "default": {},
                "examples": {
                    "type": "array",
                    "items": {}
                },
                "multipleOf": {
                    "type": "number",
                    "exclusiveMinimum": 0
                },
                "maximum": {
                    "type": "number"
                },
                "exclusiveMaximum": {
                    "type": "number"
                },
                "minimum": {
                    "type": "number"
                },
                "exclusiveMinimum": {
                    "type": "number"
                },
                "maxLength": { "${'$'}ref": "#/definitions/nonNegativeInteger" },
                "minLength": { "${'$'}ref": "#/definitions/nonNegativeIntegerDefault0" },
                "pattern": {
                    "type": "string",
                    "format": "regex"
                },
                "additionalItems": { "${'$'}ref": "#" },
                "items": {
                    "anyOf": [
                        { "${'$'}ref": "#" },
                        { "${'$'}ref": "#/definitions/schemaArray" }
                    ],
                    "default": {}
                },
                "maxItems": { "${'$'}ref": "#/definitions/nonNegativeInteger" },
                "minItems": { "${'$'}ref": "#/definitions/nonNegativeIntegerDefault0" },
                "uniqueItems": {
                    "type": "boolean",
                    "default": false
                },
                "contains": { "${'$'}ref": "#" },
                "maxProperties": { "${'$'}ref": "#/definitions/nonNegativeInteger" },
                "minProperties": { "${'$'}ref": "#/definitions/nonNegativeIntegerDefault0" },
                "required": { "${'$'}ref": "#/definitions/stringArray" },
                "additionalProperties": { "${'$'}ref": "#" },
                "definitions": {
                    "type": "object",
                    "additionalProperties": { "${'$'}ref": "#" },
                    "default": {}
                },
                "properties": {
                    "type": "object",
                    "additionalProperties": { "${'$'}ref": "#" },
                    "default": {}
                },
                "patternProperties": {
                    "type": "object",
                    "additionalProperties": { "${'$'}ref": "#" },
                    "propertyNames": { "format": "regex" },
                    "default": {}
                },
                "dependencies": {
                    "type": "object",
                    "additionalProperties": {
                        "anyOf": [
                            { "${'$'}ref": "#" },
                            { "${'$'}ref": "#/definitions/stringArray" }
                        ]
                    }
                },
                "propertyNames": { "${'$'}ref": "#" },
                "const": {},
                "enum": {
                    "type": "array",
                    "minItems": 1,
                    "uniqueItems": true
                },
                "type": {
                    "anyOf": [
                        { "${'$'}ref": "#/definitions/simpleTypes" },
                        {
                            "type": "array",
                            "items": { "${'$'}ref": "#/definitions/simpleTypes" },
                            "minItems": 1,
                            "uniqueItems": true
                        }
                    ]
                },
                "format": { "type": "string" },
                "allOf": { "${'$'}ref": "#/definitions/schemaArray" },
                "anyOf": { "${'$'}ref": "#/definitions/schemaArray" },
                "oneOf": { "${'$'}ref": "#/definitions/schemaArray" },
                "not": { "${'$'}ref": "#" }
            },
            "default": {}
        }

    """.trimIndent()

    private val jsonSchemaAsJson: JsonObject = JsonParser.parse(jsonSchemaAsString).asObject().right() !!

    fun schema() = DraftParsers.DRAFT_6.parse("#", "http://json-schema.org/draft-06/schema#", jsonSchemaAsJson)
}
