mapOf(
    "ktorVersion" to "1.6.2",
    "serializationVersion" to "0.20.0",
    "reactiveVersion" to "1.2.0",
    "coroutinesVersion" to "1.3.2",
    "kotlinVersion" to "1.5.30"
).forEach { (name, version) ->
    project.extra.set(name, version)
}