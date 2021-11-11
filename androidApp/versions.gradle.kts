mapOf(
    "navVersion" to "2.3.5",
    "glideVersion" to "4.12.0"
).forEach { (name, version) ->
    dependencies.extra.set(name, version)
}