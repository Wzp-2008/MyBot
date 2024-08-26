val projectName = rootProject.name
val groupName: String by extra
val projectArtifactId = "mybot-api"
val projectVersion: String by extra
plugins {
    id("java")
    id("maven-publish")
}

group = "cn.wzpmc"
version = projectVersion

repositories {
    mavenCentral()
}

dependencies {}
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