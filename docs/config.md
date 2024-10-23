# 主配置文件 {#main-config}
## 服务器验证 `authorization` {#authorization}
::: warning 实验性功能

此功能可能出现Bug（作者的测试环境未找到token配置项），若出现BUG，请通过[Github Issues](https://github.com/Wzp-2008/MyBot/issues)或[Gitea 工单](https://wzpmc.cn:3000/wzp/MyBot/issues)向作者提交反馈

:::
### enable
是否启动验证
- 类型： `boolean`
- 值：`true` `false`
- 样例：
    ```yaml
    enable: false
    ```
### token
验证所使用的token
- 类型： `string`
- 不启动验证则留空
- 样例：
    ```yaml
    token: ""
    ```
---

## 指令执行失败提示 `fallback` {#fallback}
### command
当指令无效时发送的消息
- 类型：`string`
- 样例：
  ```yaml
  command: 无效的指令！ 
  ```
### errorUncaught
当指令执行错误时发送的消息
- 类型：`string`
- 样例：
  ```yaml
  errorUncaught: 指令运行时出现错误！
  ```
---
## 好友相关配置 `friend` {#friend}
### autoAccept
是否自动通过好友申请
- 类型：`boolean`
- 值：`true` `false`
- 样例：
  ```yaml
  autoAccept: true
  ```
---
## 好友相关配置 `group` {#group}
### autoAccept
是否自动通过群邀请
- 类型：`boolean`
- 值：`true` `false`
- 样例：
  ```yaml
  autoAccept: true
  ```
---
## ws连接地址 `websocket` {#websocket}
ws连接地址
- 类型：`string`
- 样例：
  ```yaml
  websocket: ws://127.0.0.1:3001
  ```