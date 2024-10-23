# 环境要求
- Java 11(最低) / 17(推荐)

MyBot的可执行文件可以在 [Github Release](https://github.com/Wzp-2008/MyBot/releases/latest) 或 [Gitea Release](https://wzpmc.cn:3000/wzp/MyBot/releases/latest) 下载到

你需要准备一个空文件夹来运行MyBot：
::: code-group
```bat [windows (cmd)]
md mybot
cd mybot
```
```powershell [windows (powershell)]
New-Item mybot
cd mybot
```
```bash [linux / macos]
mkdir mybot
cd mybot
```
:::

并将jar文件放置到其中

接下来通过此命令首次启动MyBot

```bash 
/path/to/java -jar MyBot-XXXXXX.jar
```
::: tip 注意

将/path/to/java替换为你的java可执行程序路径

:::

启动完成后，将会在MyBot文件夹中创建一个config.yml，即主配置文件

关于配置文件的修改，请参阅文档中的[配置](/config)一栏

完成配置文件的修改后，再次使用命令

```bash 
/path/to/java -jar MyBot-XXXXXX.jar
```

启动MyBot

至此，Bot已完成启动~