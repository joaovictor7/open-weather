package appconfig

internal enum class AppSigning(
    val signingName: String,
    val buildTypes: List<AppBuildType>
) {
    RELEASE("release", listOf(AppBuildType.RELEASE)),
    DEBUG("debug", listOf(AppBuildType.DEBUG, AppBuildType.STAGING));

    companion object {
        fun getAssociatedBuildType(buildType: AppBuildType) = values().find {
            buildType in it.buildTypes
        }
    }
}