package appconfig

enum class AppModule(val moduleName: String) {
    CORE(":core"),
    FEATURE_LOGIN(":feature:login")
}