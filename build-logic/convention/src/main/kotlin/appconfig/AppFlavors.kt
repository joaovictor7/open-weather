package appconfig

internal enum class AppFlavors(val flavorName: String) {
    FULL("full"),
    FREE("free");

    val isDefault get() = this == FULL
}