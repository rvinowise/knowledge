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
	// flatDir {
    //     dirs("${rootDir}/../Freeplane"),
	// 	dirs("${rootDir}/../Freeplane/core/org.freeplane.core/lib"),
	// 	dirs("${rootDir}/../Freeplane/plugins/org.freeplane.plugin.script/lib)"
    // }
}

dependencies {
    compileOnly(fileTree("${rootDir}/../Freeplane"))
    compileOnly(fileTree("${rootDir}/../Freeplane/core/org.freeplane.core/lib"))
    compileOnly(fileTree("${rootDir}/../Freeplane/plugins/org.freeplane.plugin.script/lib"))

    implementation("org.codehaus.groovy", "groovy-all", "2.5.13")
    //implementation("com.google.guava", "guava", "29.0-jre")
	//implementation("commons-io", "commons-io", "2.11.0")
	
    //kotlin sql
    //implementation("org.jetbrains.exposed", "exposed-core", "0.38.1")
    //implementation("org.jetbrains.exposed", "exposed-jdbc", "0.38.1")
    //implementation("org.jetbrains.exposed", "exposed-dao", "0.38.1")
	
    //compile "org.ktorm:ktorm-core:${ktorm.version}"

    implementation("org.postgresql:postgresql:42.2.2")
    //implementation("com.impossibl.pgjdbc-ng", "pgjdbc-ng", "0.8.3")


}
