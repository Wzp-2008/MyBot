# Main Configuration File {#main-config}
## `authorization` {#authorization}
::: warning Experimental

There may be a Bug in this function (the token configuration item is not found in the author's test environment). if there is a BUG, please submit feedback to the author through [Github Issues](https://github.com/Wzp-2008/MyBot/issues) or [Gitea ticket](https://wzpmc.cn:3000/wzp/MyBot/issues)

:::
### enable
should use authorization?
- type： `boolean`
- value：`true` `false`
- example：
    ```yaml
    enable: false
    ```
### token
The token used on the authorization
- type： `string`
- can be empty when not using authorization
- example：
    ```yaml
    token: ""
    ```
---

## `fallback` {#fallback}
### command
when command executing have errors sends message
- type：`string`
- example：
  ```yaml
  command: Invalid command! 
  ```
### errorUncaught
Message sent when instruction execution fails
- type：`string`
- example：
  ```yaml
  errorUncaught: Error while command is running!
  ```
---
## `friend` {#friend}
### autoAccept
Whether to automatically apply through friends
- type：`boolean`
- values：`true` `false`
- example：
  ```yaml
  autoAccept: true
  ```
---
## `group` {#group}
### autoAccept
Automatically through group invitation
- type：`boolean`
- values：`true` `false`
- example：
  ```yaml
  autoAccept: true
  ```
---
## `websocket` {#websocket}
ws connection address
- type：`string`
- example：
  ```yaml
  websocket: ws://127.0.0.1:3001
  ```