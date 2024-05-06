package appconfig

internal enum class AppBuildTypes(val buildTypeName: String) {
    RELEASE("release"),
    DEBUG("debug"),
    STAGING("staging");

    val isDefault get() = this == DEBUG
    val isInternal get() = this in listOf(RELEASE, DEBUG)
    val isDebuggable get() = this in listOf(DEBUG, STAGING)

    fun getApplicationIdSuffix(
        onRelease: () -> Unit,
        onNonRelease: (String) -> Unit,
    ) = when(this) {
        RELEASE -> onRelease.invoke()
        DEBUG -> onNonRelease.invoke(DEV_APP_ID_SUFFIX)
        else -> onNonRelease.invoke(buildTypeName)
    }

    private companion object {
        const val DEV_APP_ID_SUFFIX = "dev"
    }
}