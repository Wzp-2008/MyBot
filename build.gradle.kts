val projectName = rootProject.name
val groupName by extra("cn.wzpmc")
val projectArtifactId by extra("my-bot")
val projectVersion by extra("0.0.1-dev")

plugins {
    id("java")
    id("maven-publish")
}

group = groupName
version = projectVersion

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/io.netty/netty-all
    implementation("io.netty:netty-all:4.1.112.Final")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    // https://mvnrepository.com/artifact/com.alibaba.fastjson2/fastjson2
    implementation("com.alibaba.fastjson2:fastjson2:2.0.52")
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
            val releasesRepoUrl = uri("http://server.wzpmc.cn:8081/repository/maven-releases")
            val snapshotsRepoUrl = uri("http://server.wzpmc.cn:8081/repository/maven-snapshots")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials {
                username = project.findProperty("repo.user") as String? ?: ""
                password = project.findProperty("repo.password") as String? ?: ""
            }
            isAllowInsecureProtocol = true
        }
    }
}
tasks.test {
    useJUnitPlatform()
}