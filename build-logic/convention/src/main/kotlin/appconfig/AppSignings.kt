package appconfig

internal enum class AppSignings(
    val signingName: String,
    val buildTypes: List<AppBuildTypes>
) {
    RELEASE("release", listOf(AppBuildTypes.RELEASE)),
    DEBUG("debug", listOf(AppBuildTypes.DEBUG));

    companion object {
        fun getAssociatedBuildTypes(buildType: AppBuildTypes) = values().find {
            buildType in it.buildTypes
        }
    }
}