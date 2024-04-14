package appconfig

internal enum class AppBuildTypes(val buildTypeName: String, ) {
    RELEASE("release"),
    DEBUG("debug"),
    STAGING("staging");

    val isDefault: Boolean get() = this in listOf(RELEASE, DEBUG)

    fun getApplicationIdSuffix(
        onRelease: () -> Unit,
        onNonRelease: (String) -> Unit,
    ) = when(this) {
        RELEASE -> onRelease.invoke()
        DEBUG, STAGING -> onNonRelease.invoke(DEV_APP_ID_SUFFIX)
    }

    private companion object {
        const val DEV_APP_ID_SUFFIX = "dev"
    }
}