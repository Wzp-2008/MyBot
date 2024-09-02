# MyBot

### A Java-Based OneBot11-Compatible Bot Framework

---

## Quick Start

### Requirements
1. Java Runtime Environment (JRE) version ≥ 11
2. Implementation of any OneBot11 protocol framework

### Steps to Use
1. Go to [GitHub Release](https://github.com/Wzp-2008/MyBot/releases/latest) or [Gitea Release](https://wzpmc.cn:3000/wzp/MyBot/releases/latest) to download the latest version of the JAR package.
2. Place the downloaded JAR package in an empty folder.
3. Start your OneBot instance.
4. Start the MyBot server using the following command:
   ```bash
   java -jar mybot.jar
   ```
5. Edit the generated configuration file and set the WebSocket address to your OneBot WebSocket address.
6. Run the following command again to start the server:
   ```bash
   java -jar mybot.jar
   ```
7. Now, you can start using MyBot!

---

## Development Guide

### Add Repositories

If you need to pull dependencies from custom repositories, add the following repository configurations to your project's build file:

#### Maven Repository Setup
In your `pom.xml`, add the following repository configuration:
```xml
<repositories>
    <repository>
        <id>wzpmc-maven-releases</id>
        <url>https://wzpmc.cn:90/repository/maven-public</url>
    </repository>
</repositories>
```

#### Gradle Repository Setup

##### Groovy DSL
```groovy
repositories {
    maven {
        url "https://wzpmc.cn:90/repository/maven-public"
    }
}
```

##### Kotlin DSL
```kotlin
repositories {
    maven {
        url = uri("https://wzpmc.cn:90/repository/maven-public")
    }
}
```

### Add Dependencies

#### Maven Build
In your `pom.xml`, add the following dependency:
```xml
<dependency>
    <groupId>cn.wzpmc</groupId>
    <artifactId>mybot-api</artifactId>
    <version>[LATEST-VERSION]</version>
</dependency>
```

#### Gradle Build

##### Groovy DSL
```groovy
implementation 'cn.wzpmc:mybot-api:[LATEST-VERSION]'
```

##### Kotlin DSL
```kotlin
implementation("cn.wzpmc:mybot-api:[LATEST-VERSION]")
```

---

With these settings, you'll be able to seamlessly integrate MyBot-API into your project and quickly build OneBot11-compatible bot applications. Enjoy!

---