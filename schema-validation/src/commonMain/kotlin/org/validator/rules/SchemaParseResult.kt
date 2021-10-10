package org.validator.rules

import org.validator.ValidationRule

data class SchemaParseResult(val rule: ValidationRule?, val errors: List<Error>, val refs: List<String>, val index: Map<String, ValidationRule>)
