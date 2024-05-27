import appconfig.AppModule
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.database"
}

dependencies {
    implementation(libs.room)
    ksp(libs.roomCompile)
    includeModules(AppModule.CORE_SECURITY)
}