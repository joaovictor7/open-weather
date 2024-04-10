package appconfig

internal enum class AppBuildTypes(val buildTypeName: String, ) {
    RELEASE("release"),
    DEBUG("debug"),
    STAGING("staging");

    val isRelease: Boolean get() = this == RELEASE
    val iaDefault: Boolean get() = this in listOf(RELEASE, DEBUG)
}