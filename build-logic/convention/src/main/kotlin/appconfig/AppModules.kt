package appconfig

enum class AppModules(val moduleName: String) {
    CORE(":core"),
    ROUTER(":router"),
    FEATURE_LOGIN(":feature:login"),
    FEATURE_HOME(":feature:home")
}