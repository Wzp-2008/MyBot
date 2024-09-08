import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

val projectName = rootProject.name
val groupName: String by extra
val projectArtifactId: String by extra
val projectVersion: String by extra

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
allprojects {
    apply(plugin = "java-library")
    val groupName by extra("cn.wzpmc")
    val projectArtifactId by extra("my-bot")
    val projectVersion by extra("1.0.3")
    repositories {
        mavenCentral()
        maven("https://libraries.minecraft.net")
    }
    dependencies {
        // https://mvnrepository.com/artifact/org.projectlombok/lombok
        compileOnly("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
        // https://mvnrepository.com/artifact/com.alibaba.fastjson2/fastjson2
        api("com.alibaba.fastjson2:fastjson2:2.0.52")
        api("com.mojang:brigadier:1.0.18")
        // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
        api("org.apache.logging.log4j:log4j-api:2.23.1")
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }
    tasks.compileJava {
        options.encoding = "UTF-8"
    }
    tasks.javadoc {
        options.encoding = "UTF-8"
    }
}
group = groupName
version = projectVersion

dependencies {
    implementation(project(":mybot-api"))
    implementation("net.minecrell:terminalconsoleappender:1.3.0") {
        exclude(group = "org.apache.logging.log4j", module = "log4j-core")
        exclude(group = "org.apache.logging.log4j", module = "log4j-api")
    }
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-jul
    implementation("org.apache.logging.log4j:log4j-jul:2.23.1")
    // https://mvnrepository.com/artifact/io.netty/netty-all
    implementation("io.netty:netty-all:4.1.112.Final")
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
tasks.test {
    useJUnitPlatform()
}