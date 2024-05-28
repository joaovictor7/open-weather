import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.domain"
}

dependencies {
    includeModules(
        AppModule.CORE_DATA,
        AppModule.CORE_DATABASE
    )
}