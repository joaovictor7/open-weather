package appconfig

enum class AppModule(val moduleName: String) {
    CORE_TEST(":core:test"),
    CORE_UI(":core:ui"),
    CORE_ROUTER(":core:router"),
    CORE_DATA(":core:data"),
    CORE_DESIGNSYSTEM(":core:designsystem"),
    CORE_UTILITY(":core:utility"),
    FEATURE_LOGIN(":feature:login"),
    FEATURE_HOME(":feature:home")
}