package org.validator

import com.squareup.kotlinpoet.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.validator.model.Test
import org.validator.model.TestCase
import java.io.File
import java.util.*

fun main() {
    val writeTo = "/Users/tfernandez/projects/json-schema-validator/tck-test-suite/src/commonTest/kotlin"
    val draft = "DRAFT_4"
    val baseFile =
        File("/Users/tfernandez/projects/json-schema-validator/test-generator/src/main/resources/json-schema-test-suite/draft4")
    generateTestSuites(baseFile.absolutePath, baseFile, draft, writeTo)
}

fun generateTestSuites(rootPath: String, base: File, draft: String, to: String) {
    val fileList = base.listFiles()!!
    fileList.forEach { file ->
        if (file.isDirectory) generateTestSuites(rootPath, file, draft, to)
        else if(file.absolutePath.contains("optional")) // skip
        else {
            println("Generating test suite for: ${file.absolutePath}")
            generateTest(rootPath, file, draft, to)
        }
    }
}

fun generateTest(rootPath: String, file: File, draft: String, to: String) {
    val parser = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
    val content = file.readLines().joinToString("\n")
    val obj = parser.decodeFromString<List<Test>>(content).distinct()
    val constraint = file.absolutePath.split("/").last().split(".").first()
    val subPackagePath =
        file.absolutePath.split("/").dropLast(1).joinToString("/").replace(rootPath, "").replace("/", ".")
    val basePackagePath = "org.validator.tck.draft4" + subPackagePath
    val test = writeSuite(constraint, obj, draft, basePackagePath)
    test.writeTo(File(to))
}

fun String.capitalize(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun writeSuite(constraint: String, test: List<Test>, draft: String, packageName: String): FileSpec {
    val suiteName = "${constraint.capitalize()}Test".replace("-", "_")
    return FileSpec
        .builder(packageName, suiteName)
        .addImport("org.validator.tck", "run_test")
        .addType(
            TypeSpec
                .classBuilder(suiteName)
                .addFunctions(test.distinctBy { x -> clean(x.description) }.map { writeTest(it, draft) })
                .build()
        )
        .build()
}

fun writeTest(test: Test, draft: String): FunSpec {
    return FunSpec
        .builder(clean(test.description))
        .addAnnotation(
            AnnotationSpec.builder(ClassName("kotlin.test", "Test")).build()
        ).addCode(
            writeTestCodeBlock(test, draft)
        )
        .build()
}

fun writeTestCodeBlock(test: Test, draft: String): CodeBlock {
    val render = Json {
        prettyPrint = true
    }
    val assertions = test.tests.map { writeAssertions(it, draft) }
    val builder = CodeBlock
        .builder()
        .addStatement("val schema = %P", render.encodeToString(test.schema).replace("$", "\${'$'}"))
    return assertions.fold(builder) { acc, curr -> acc.add(curr) }.build()
}

fun writeAssertions(case: TestCase, draft: String): CodeBlock {
    val render = Json {
        prettyPrint = true
    }
    val varName = clean(case.description)
    return CodeBlock
        .builder()
        .addStatement("val `$varName` = %P", render.encodeToString(case.data).replace("$", "\${'$'}"))
        .addStatement("run_test(schema, `$varName`, ${case.valid}, %S)", draft)
        .build()
}

fun clean(value: String): String {
    return value.replace(" ", "_").replace(",", "_").replace("(", "").replace(")", "").replace("-", "_")
        .replace(":", "_").replace(".", "_").replace("\\", "_").replace("/", "_")
        .replace("[", "{")
        .replace("]", "}")
        .replace("=", "_")
}
