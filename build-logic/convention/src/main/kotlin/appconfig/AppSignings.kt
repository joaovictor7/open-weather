package appconfig

internal enum class AppSignings(
    val signingName: String,
    val buildTypes: List<AppBuildTypes>
) {
    RELEASE("release", listOf(AppBuildTypes.RELEASE)),
    DEBUG("debug", listOf(AppBuildTypes.DEBUG, AppBuildTypes.STAGING));

    companion object {
        fun getAssociatedBuildType(buildType: AppBuildTypes) = values().find {
            buildType in it.buildTypes
        }
    }
}