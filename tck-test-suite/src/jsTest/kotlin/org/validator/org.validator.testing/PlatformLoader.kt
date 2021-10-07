package org.validator.testing

external fun require(name: String): dynamic
external val __dirname: dynamic

val fs = require("fs")
val jsPath = require("path");

actual fun loadResourceText(path: String): dynamic {
    val finalPath = jsPath.join(
        __dirname,
        "../../../..",
        "processedResources",
        "js",
        "test",
        path
    )
    return fs.readFileSync(finalPath, "utf8")
}
