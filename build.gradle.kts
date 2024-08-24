import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

val projectName = rootProject.name
val groupName by extra("cn.wzpmc")
val projectArtifactId by extra("my-bot")
val projectVersion by extra("1.0.0")

plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = groupName
version = projectVersion
repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    implementation("net.minecrell:terminalconsoleappender:1.3.0") {
        exclude(group = "org.apache.logging.log4j", module = "log4j-core")
        exclude(group = "org.apache.logging.log4j", module = "log4j-api")
    }
    implementation("com.mojang:brigadier:1.0.18")
    // https://mvnrepository.com/artifact/io.netty/netty-all
    implementation("io.netty:netty-all:4.1.112.Final")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.23.1")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-jul
    implementation("org.apache.logging.log4j:log4j-jul:2.23.1")
    // https://mvnrepository.com/artifact/com.alibaba.fastjson2/fastjson2
    implementation("com.alibaba.fastjson2:fastjson2:2.0.52")
    // https://mvnrepository.com/artifact/org.yaml/snakeyaml
    implementation("org.yaml:snakeyaml:2.2")
    // https://mvnrepository.com/artifact/org.jline/jline
    implementation("org.jline:jline-terminal:3.26.3")
    implementation("org.jline:jline-reader:3.26.3")
    /*implementation("org.jline:jline-terminal-jni:3.26.3")
    implementation("org.jline:jline-terminal-ffm:3.26.3")*/
    // https://mvnrepository.com/artifact/org.jline/jline-terminal-jansi
    implementation("org.jline:jline-terminal-jansi:3.26.3")
    // https://mvnrepository.com/artifact/org.fusesource.jansi/jansi
    implementation("org.fusesource.jansi:jansi:2.4.1")
    /*// https://mvnrepository.com/artifact/org.jline/jline-terminal-jna
    implementation("org.jline:jline-terminal-jna:3.26.3")
    // https://mvnrepository.com/artifact/net.java.dev.jna/jna
    implementation("net.java.dev.jna:jna:5.14.0")*/
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.compileJava {
    options.encoding = "UTF-8"
}
tasks.javadoc {
    options.encoding = "UTF-8"
}
tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.named("javadoc"))
}
tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}
tasks.withType<ShadowJar> {
    manifest {
        attributes(
            "Main-Class" to "cn.wzpmc.Main"
        )
    }

    archiveBaseName.set("MyBot")
    archiveVersion.set(projectVersion)
    archiveClassifier.set("")
    transform(Log4j2PluginsCacheFileTransformer::class.java)
}
tasks.named("build") {
    dependsOn(tasks.named("shadowJar"))
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = groupName
            artifactId = projectArtifactId
            version = projectVersion
            artifact(tasks.named("javadocJar"))
            artifact(tasks.named("sourcesJar"))
            pom {
                name.set(projectName)
                description.set("A Java-based OneBot11-compatible robot framework")

                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("wzp")
                        name.set("wzp")
                        email.set("minecraftwzpmc@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:https://wzpmc.cn:3000/wzp/MyBot.git")
                    developerConnection.set("scm:git:https://wzpmc.cn:3000/wzp/MyBot.git")
                    url.set("https://wzpmc.cn:3000/wzp/MyBot")
                }
            }
        }
    }

    repositories {
        maven {
            val releasesRepoUrl = uri("https://wzpmc.cn:90/repository/maven-releases")
            val snapshotsRepoUrl = uri("https://wzpmc.cn:90/repository/maven-snapshots")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials {
                username = project.findProperty("repo.user") as String? ?: ""
                password = project.findProperty("repo.password") as String? ?: ""
            }
        }
    }
}
tasks.test {
    useJUnitPlatform()
}