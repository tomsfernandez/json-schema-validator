package org.validator.rules.`object`

import org.validator.*

abstract class PropertyAmountParser : RuleParser {

    protected abstract val KEY: String

    override fun canParse(element: JsonObject) = element.get(KEY) != null

    override fun parse(base: String, path: String, element: JsonObject): Schema {
        val finalPath = objectKey(path, KEY)
        return element.get(KEY).asScalar().fold({ error -> Schema(base, finalPath, error)}) { scalar ->
            scalar.double().fold({ error -> Schema(base, finalPath, error)}) { number ->
                if (number.toInt() < 0) Schema(base, finalPath, Error("maxProperties should have a non-negative value"))
                else parse(base, path, number)
            }
        }
    }

    protected abstract fun parse(base: String, path: String, number: Number): Schema
}

object MaxPropertiesRuleParser: PropertyAmountParser() {

    override val KEY = "maxProperties"

    override fun parse(base: String, path: String, number: Number): Schema {
        val finalPath = objectKey(path, KEY)
        return if (number.toInt() < 0) Schema(base, finalPath, Error("maxProperties should have a non-negative value"))
        else Schema(base, finalPath, MaxPropertiesRule(number.toInt()))
    }
}

data class MaxPropertiesRule(val maximum: Int): SchemaRule {

    override fun eval(path: String, element: JsonElement, schema: Schema): List<RuleError> {
        return element.asObject().fold({ emptyList() }) { x ->
            if (x.keys().size <= maximum) emptyList()
            else listOf(RuleError(path, "Object has ${x.keys().size} but maximum is $maximum"))
        }
    }
}
