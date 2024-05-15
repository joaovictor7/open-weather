package appconfig

enum class AppModule(val moduleName: String) {
    CORE_TEST(":core:test"),
    CORE_UI(":core:ui"),
    CORE_ROUTER(":core:router"),
    CORE_DATA(":core:data"),
    COMMON_DESIGNSYSTEM(":common:designsystem"),
    COMMON_UTILITY(":common:utility"),
    FEATURE_LOGIN(":feature:login"),
    FEATURE_HOME(":feature:home")
}