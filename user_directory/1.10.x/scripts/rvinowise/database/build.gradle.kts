plugins {
    groovy
	id("org.jetbrains.kotlin.jvm") version "1.7.0"
    `java-library`
	
	
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(15))
    }
}

repositories {
    mavenCentral()
}

val FREEPLANE_HOME = "C:/Program Files/Freeplane/"
dependencies {
    compileOnly(fileTree(FREEPLANE_HOME))
    compileOnly(fileTree("${FREEPLANE_HOME}/core/org.freeplane.core/lib"))
    compileOnly(fileTree("${FREEPLANE_HOME}/plugins/org.freeplane.plugin.script/lib"))

    implementation("org.codehaus.groovy", "groovy-all", "2.5.13")
	implementation("commons-io:commons-io:2.11.0")
    implementation("org.postgresql:postgresql:42.2.2")


}
