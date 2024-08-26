# MyBot

### 一个基于 Java 的 OneBot11 兼容机器人框架

---

## 快速开始

### 环境要求
1. Java 运行环境（JRE）版本 ≥ 11
2. 任意 OneBot11 协议框架的实现

### 使用步骤
1. 前往 [GitHub Release](https://github.com/Wzp-2008/MyBot/releases/latest) 或 [Gitea Release](https://wzpmc.cn:3000/wzp/MyBot/releases/latest) 下载最新版本的 JAR 包。
2. 将下载的 JAR 包放入一个空文件夹中。
3. 启动你的 OneBot 实例。
4. 通过以下命令启动 MyBot 服务端：
   ```bash 
   java -jar mybot.jar
   ```
5. 编辑生成的配置文件，将其中的 WebSocket 地址设置为你的 OneBot WebSocket 地址。
6. 再次运行以下命令启动服务端：
   ```bash 
   java -jar mybot.jar
   ```
7. 现在，你可以开始使用 MyBot 啦！

---

## 开发指南

### 添加仓库

如果你需要从自定义仓库拉取依赖项，请在项目的构建文件中添加以下仓库配置：

#### Maven 仓库设置
在 `pom.xml` 中添加以下仓库配置：
```xml
<repositories>
    <repository>
        <id>wzpmc-maven-releases</id>
        <url>https://wzpmc.cn:90/repository/maven-releases</url>
    </repository>
</repositories>
```

#### Gradle 仓库设置

##### Groovy DSL
```groovy
repositories {
    maven {
        url "https://wzpmc.cn:90/repository/maven-releases"
    }
}
```

##### Kotlin DSL
```kotlin
repositories {
    maven {
        url = uri("https://wzpmc.cn:90/repository/maven-releases")
    }
}
```

### 添加依赖

#### Maven 构建
在你的 `pom.xml` 中添加以下依赖项：
```xml
<dependency>
    <groupId>cn.wzpmc</groupId>
    <artifactId>mybot-api</artifactId>
    <version>[LATEST-VERSION]</version>
</dependency>
```

#### Gradle 构建

##### Groovy DSL
```groovy
implementation 'cn.wzpmc:mybot-api:[LATEST-VERSION]'
```

##### Kotlin DSL
```kotlin
implementation("cn.wzpmc:mybot-api:[LATEST-VERSION]")
```

---

如此设置之后，您将轻松地将 MyBot-API 集成到您的项目中，并快速构建与 OneBot11 兼容的机器人应用。Enjoy~

---