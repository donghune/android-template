import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.*

private const val MAJOR_VALUE = 100_000_000
private const val MINOR_VALUE = 1_000_000
private const val PATCH_VALUE = 1

private fun Project.getVersionProperty(): Properties {
    val versionProperty = Properties()
    val inputStream = FileInputStream(File(rootDir, "version.properties"))
    versionProperty.load(inputStream)
    return versionProperty
}

fun Project.buildVersionName(): String {
    val versionProperty = getVersionProperty()
    val appVersion = versionProperty["AppVersion"]
    return "$appVersion"
}

fun Project.buildVersionCode(): Int {
    val versionProperty = getVersionProperty()
    val appVersion = versionProperty["AppVersion"].toString().split(".")

    val major = (appVersion[0].toInt()) * MAJOR_VALUE
    val minor = (appVersion[1].toInt()) * MINOR_VALUE
    val patch = (appVersion[2].toInt()) * PATCH_VALUE

    return major + minor + patch
}
