package appconfig

enum class AppModule(val moduleName: String) {
    CORE(":core"),
    CORE_TEST(":core:test"),
    CORE_UI(":core:ui"),
    ROUTER(":router"),
    FEATURE_LOGIN(":feature:login"),
    FEATURE_HOME(":feature:home")
}